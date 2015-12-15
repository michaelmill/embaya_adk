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
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.HdlcClientConnectionSettings;

public class ReadSAP {

	public static void main(String[] args) throws InterruptedException {
		// Settings to connect to smart meter
		// - Serial Port: USB0 on Linux
		// - Client Address: 18, one byte
		// - Server Address: Logical 16, 1 Byte
		// - Referencing: Logical Name
		HdlcClientConnectionSettings settings = new HdlcClientConnectionSettings("/dev/ttyUSB0", new HdlcAddress(17),
				new HdlcAddress(1), ReferencingMethod.LN);
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

			GetRequest getSAP = new GetRequest(17, new ObisCode(0, 0, 41, 0, 0, 255), 2);

			List<GetResult> getResults = connection.get(10000, false, getSAP);
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
		printSAPList(result.getResultData());
	}

	private static void printSAPList(Data data) {
		for (Data sap : data.getComplex()) {
			System.out.println("Logical Device SAP: " + sap.getComplex().get(0).getNumber() + "; Name: "
					+ new String(sap.getComplex().get(1).getByteArray(), Charset.forName("US-ASCII")));
		}
	}

}
