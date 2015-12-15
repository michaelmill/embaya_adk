package org.openmuc.jdlms.sample.iskra;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

import org.openmuc.jdlms.client.ClientConnectionSettings.Authentication;
import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.Data;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.MethodRequest;
import org.openmuc.jdlms.client.MethodResult;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.HdlcClientConnectionSettings;

/**
 * Sample application that calls a method on an Iskraemeco smart meter to erase the event log.
 * 
 * @author Karsten Mueller-Bier
 */
public class CallMethodSample {

	public static void main(String[] args) {
		// Settings to connect to smart meter
		// - Serial Port: USB0 on Linux
		// - Client Address: 16, one byte
		// - Server Address: Logical 1, Physical 17, 2 Byte
		// - Referencing: Logical Name
		// - Authorization: LOW (password needed)
		HdlcClientConnectionSettings settings = new HdlcClientConnectionSettings("/dev/ttyUSB0", new HdlcAddress(16),
				new HdlcAddress(1, 17, 2), ReferencingMethod.LN);
		settings.setAuthentication(Authentication.LOW);

		IClientConnectionFactory factory = ServiceLoader.load(IClientConnectionFactory.class).iterator().next();
		IClientConnection connection = null;
		try {
			connection = factory.createClientConnection(settings);

			System.out.print("Enter the password to connect with the smart meter: ");
			byte pw[] = null;
			if (System.console() != null) {
				char[] input = System.console().readPassword();
				CharBuffer buffer = CharBuffer.wrap(input);
				Charset ascii = Charset.forName("US-ASCII");
				ByteBuffer bytes = ascii.encode(buffer);
				pw = new byte[bytes.limit()];
				bytes.get(pw);
			}
			else {
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine();
				pw = input.getBytes("US-ASCII");
			}

			connection.connect(3000, pw);
			System.out.println("\nConnected to smart meter\n");

			// Get parameter to read complete event log
			// - Interface class: 7 (Generic Profile)
			// - Obis code: 1:0:99:98:0:255 (event log)
			// - Attribute ID: 2 (buffer)
			ObisCode eventLogAddress = new ObisCode(1, 0, 99, 98, 0, 255);
			GetRequest getLog = new GetRequest(7, eventLogAddress, 2);

			// Read entire event log
			List<GetResult> getResults = connection.get(0, getLog);
			System.out.println("\nRead Event Log from smart meter");
			printLog(getResults.get(0));
			System.out.println();

			// Action parameter to reset log
			// - Interface class: 7 (Generic Profile)
			// - Obis code: 1:0:99:98:0:255 (event log)
			// - Method ID: 1 (reset)
			// - Method Parameter: None
			MethodRequest resetLog = new MethodRequest(7, eventLogAddress, 1);
			resetLog.data().setInteger8(0);

			// Call remote method
			System.out.println("Resetting Log");
			List<MethodResult> actionResults = connection.action(3000, resetLog);
			System.out.println(actionResults.get(0).getResultCode());
			System.out.println();

			// Read event log again, should only contain one entry
			getResults = connection.get(3000, getLog);
			System.out.println("\nRead Event Log from smart meter");
			printLog(getResults.get(0));
			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null && connection.isConnected()) {
				connection.disconnect(false);
				System.out.println("\nDisconnected from smart meter\n");
			}
		}
	}

	private static void printLog(GetResult logObject) {
		printData(logObject.getResultData(), 0);
	}

	private static void printData(Data data, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("   ");
		}

		switch (data.getChoiceIndex()) {
		case ARRAY:
			System.out.println("Array");
			for (Data subData : data.getComplex()) {
				printData(subData, indent + 1);
			}
			break;
		case COMPACT_ARRAY:
			System.out.println("Compact Array");
			break;
		case STRUCTURE:
			System.out.println("Structure");
			for (Data subData : data.getComplex()) {
				printData(subData, indent + 1);
			}
			break;
		case OCTET_STRING:
			System.out.print("Octet String: ");
			for (byte b : data.getByteArray()) {
				String hex = Integer.toHexString(b & 0xff);
				hex = hex.length() == 1 ? "0" + hex : hex;
				System.out.print(hex + " ");
			}
			System.out.println();
			break;
		case LONG_UNSIGNED:
			System.out.println("Long Unsigned: " + data.getNumber());
			break;
		default:
			System.out.println("Unknown type: " + data.getChoiceIndex());
		}
	}
}
