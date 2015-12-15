package org.openmuc.jdlms.sample.kamstrup;

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
import org.openmuc.jdlms.client.Data.Choices;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.HdlcClientConnectionSettings;

public class ReadMultipleSample {
	public static void main(String[] args) throws InterruptedException {
		// Settings to connect to smart meter
		// - Serial Port: USB0 on Linux
		// - Client Address: 18, one byte
		// - Server Address: Logical 16, 1 Byte
		// - Referencing: Logical Name
		HdlcClientConnectionSettings settings = new HdlcClientConnectionSettings("/dev/ttyUSB0", new HdlcAddress(18),
				new HdlcAddress(16), ReferencingMethod.LN);
		settings.setBaudrate(9600).setUseHandshake(false).setAuthentication(Authentication.LOW);

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

			GetRequest getParam = new GetRequest(3, new ObisCode(1, 1, 1, 8, 0, 255), 2);
			GetRequest getScalar = getParam.changeAttributeId(3);

			List<GetResult> getResults = connection.get(1000, false, getParam);
			if (getResults.get(0).isSuccess()) {
				printResult(getResults.get(0));
			}
			else {
				System.out.println("Error on reading object list: " + getResults.get(0).getResultCode());
			}

			getResults = connection.get(1000, getScalar);
			if (getResults.get(0).isSuccess()) {
				printResult(getResults.get(0));
			}
			else {
				System.out.println("Error on reading object list: " + getResults.get(0).getResultCode());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null && connection.isConnected()) {
				connection.disconnect(false);
				System.out.println("\nDisconnected from smart meter\n");
			}
		}
	}

	private static void printResult(GetResult result) {
		System.out.println("Received data:");
		printData(result.getResultData(), 0);
	}

	private static void printData(Data data, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("   ");
		}
		if (data.getChoiceIndex() == Choices.ARRAY) {
			System.out.println("Array:");
			for (Data subData : data.getComplex()) {
				printData(subData, indent + 1);
			}
		}
		else if (data.getChoiceIndex() == Choices.STRUCTURE) {
			System.out.println("Structure:");
			for (Data subData : data.getComplex()) {
				printData(subData, indent + 1);
			}
		}
		else if (data.getChoiceIndex() == Choices.LONG_UNSIGNED) {
			System.out.println("Unsigned16: " + data.getNumber());
		}
		else if (data.getChoiceIndex() == Choices.UNSIGNED) {
			System.out.println("Unsigned: " + data.getNumber());
		}
		else if (data.getChoiceIndex() == Choices.OCTET_STRING) {
			System.out.print("Octet String: ");
			for (byte b : data.getByteArray()) {
				String hexString = Integer.toHexString(b & 0xFF);
				hexString = hexString.length() == 1 ? "0" + hexString : hexString;
				System.out.print(hexString + " ");
			}
			System.out.println("");
		}
		else if (data.getChoiceIndex() == Choices.INTEGER) {
			System.out.println("Integer: " + data.getNumber());
		}
		else if (data.getChoiceIndex() == Choices.DOUBLE_LONG_UNSIGNED) {
			System.out.println("Unsigned64: " + data.getNumber());
		}
		else if (data.getChoiceIndex() == Choices.ENUMERATE) {
			System.out.println("Enum: " + data.getNumber());
		}
		else if (data.getChoiceIndex() == Choices.BOOL) {
			System.out.println("Bool: " + data.getBoolean());
		}
		else if (data.getChoiceIndex() == Choices.NULL_DATA) {
			// ignore
		}
		else {
			System.out.println("Unknown Format: " + data.getChoiceIndex());
		}
	}
}
