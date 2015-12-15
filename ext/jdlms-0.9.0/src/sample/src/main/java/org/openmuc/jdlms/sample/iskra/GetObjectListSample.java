package org.openmuc.jdlms.sample.iskra;

import java.io.IOException;
import java.util.List;
import java.util.ServiceLoader;

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

/**
 * Sample application that reads a list of all accessible objects from from a Iskraemeco meter via HDLC.
 * 
 * @author Karsten Mueller-Bier
 */
public class GetObjectListSample {

	public static void main(String[] args) {
		// Settings to connect to smart meter
		// - Serial Port: USB0 on Linux
		// - Client Address: 16, one byte
		// - Server Address: Logical 16, Physical 17, 2 Byte
		// - Referencing: Logical Name
		HdlcClientConnectionSettings settings = new HdlcClientConnectionSettings("/dev/ttyUSB0", new HdlcAddress(16),
				new HdlcAddress(16, 17, 2), ReferencingMethod.LN);

		IClientConnectionFactory factory = ServiceLoader.load(IClientConnectionFactory.class).iterator().next();
		IClientConnection connection = null;
		try {
			connection = factory.createClientConnection(settings);

			connection.connect(3000);
			System.out.println("\nConnected to smart meter\n");

			// Get parameter to read object list
			// - Interface class: 15 (AssociationLN)
			// - Obis code: 0:0:40:0:0:255 (current association)
			// - Attribute ID: 2 (Object list)
			GetRequest getParam = new GetRequest(15, new ObisCode(0, 0, 40, 0, 0, 255), 2);

			// The resulting list is too big to be transferred in one go, so it
			// is automatically transferred into several datablocks and fused
			// together before returning the result.
			List<GetResult> getResults = connection.get(15000, getParam);
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
		else if (data.getChoiceIndex() == Choices.ENUMERATE) {
			System.out.println("Enum: " + data.getNumber());
		}
		else if (data.getChoiceIndex() == Choices.BOOL) {
			System.out.println("Bool: " + data.getBoolean());
		}
		else if (data.getChoiceIndex() == Choices.NULL_DATA) {
			System.out.println("");// ignore
		}
		else {
			System.out.println("Unknown Format: " + data.getChoiceIndex());
		}
	}
}
