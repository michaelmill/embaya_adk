package org.openmuc.jdlms.sample.iskra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.Data;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.SelectiveAccessDescription;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.HdlcClientConnectionSettings;

/**
 * Sample application that reads only the last 31 days of the event log of an Iskraemeco smart meter. This is a sample
 * how the Selective Access Parameter is used in a get request
 * 
 * @author Karsten Mueller-Bier
 */
public class SelectiveAccessSample {

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

			// Get parameter to read complete event log
			// - Interface class: 7 (Generic Profile)
			// - Obis code: 1:0:99:98:0:255 (event log)
			// - Attribute ID: 2 (buffer)
			GetRequest getLog = new GetRequest(7, new ObisCode(1, 0, 99, 98, 0, 255), 2);

			// Read complete event log
			List<GetResult> getResults = connection.get(0, getLog);
			System.out.println("\nRead Complete Event Log from smart meter");
			printLog(getResults.get(0));
			System.out.println();

			// Preparation for selective access description
			// The amount of preparation may vary according to the attribute
			// that is read

			// Prepare for selective access of last 31 days
			// Structure defining the range object
			List<Data> innerData = new ArrayList<Data>(4);
			innerData.add(new Data());
			innerData.add(new Data());
			innerData.add(new Data());
			innerData.add(new Data());
			// ClassId of range object = 8 (Clock)
			innerData.get(0).setUnsigned16(8);
			// Logical name of the current clock
			innerData.get(1).setOctetString(new byte[] { 0x00, 0x00, 0x01, 0x00, 0x00, (byte) 0xFF });
			// Attribute Id = 2
			innerData.get(2).setInteger8(2);
			// Data Index set to 0 because attribute type is primitive
			innerData.get(3).setUnsigned16(0);

			Data innerStruct = new Data();
			innerStruct.setStructure(innerData);

			Calendar from = Calendar.getInstance();
			from.add(Calendar.DAY_OF_MONTH, -31);

			List<Data> outerData = new ArrayList<Data>(4);
			// First element of the selective access is the above definition
			outerData.add(innerStruct);
			outerData.add(new Data());
			outerData.add(new Data());
			outerData.add(new Data());
			// Second attribute is the starting time (today - 31 days)
			outerData.get(1).setDateTime(from, false);
			// Third attribute is the end time (now)
			outerData.get(2).setDateTime(Calendar.getInstance(), false);
			// Fourth attribute is empty, we want to read all events
			outerData.get(3).setArray(new LinkedList<Data>());

			// Finally defining the selective access description and adding it
			// the get parameter used in first get
			Data outerStruct = new Data();
			outerStruct.setStructure(outerData);
			getLog.setAccessSelection(new SelectiveAccessDescription(1, outerStruct));

			// Reading events from last 31 days only
			getResults = connection.get(0, getLog);
			System.out.println("\nRead Selective Event Log from last 31 days out of smart meter");
			if (getResults.get(0).isSuccess()) {
				printLog(getResults.get(0));
			}
			else {
				System.out.println("Error on reading: " + getResults.get(0).getResultCode());
			}
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
