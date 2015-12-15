package org.openmuc.jdlms.sample.landis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.ServiceLoader;

import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.Data;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.ip.TcpClientConnectionSettings;

/**
 * Sample application that reads a list of all accessible objects from from a Landis+Gyr meter via TCP/IP.
 * 
 * @author Karsten Mueller-Bier
 */
public class GetObjectListSample {

	public static void main(String[] args) {
		// Settings to connect to smart meter
		// - IP Destination: 192.168.200.25 (may vary)
		// - TCP Port: 4059 (DLMS standard)
		// - WPort of smart meter: 1 (Management Logical Device)
		// - WPort of client: 16 (Public client)
		// - Referencing: Logical Name
		TcpClientConnectionSettings settings = new TcpClientConnectionSettings(new InetSocketAddress("192.168.200.25",
				4059), 1, 16, ReferencingMethod.LN);

		// Alternatively, new ClientConnectionFactory() can be used to
		// instantiate factory. Note that this does not work in OSGi!
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
			List<GetResult> getResults = connection.get(15000, false, getParam);
			printResult(getResults.get(0));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null && connection.isConnected()) {
				connection.disconnect();
				System.out.println("\nDisconnected from smart meter\n");
			}
		}
	}

	private static void printResult(GetResult result) {
		System.out.println("Received data:");
		printClassList(result.getResultData());
	}

	private static void printClassList(Data data) {
		for (Data sub : data.getComplex()) {
			printClassInfo(sub);
		}
	}

	private static void printClassInfo(Data classData) {
		System.out.println("{");
		System.out.println("  ClassId: " + classData.getComplex().get(0).getNumber());
		System.out.println("  Version: " + classData.getComplex().get(1).getNumber());
		System.out.print("  Logical Name: ");
		for (Byte b : classData.getComplex().get(2).getByteArray()) {
			System.out.print(Integer.toString(b & 0xFF) + " ");
		}
		System.out.println();

		System.out.println("  Attribute Access Rights:");
		for (Data accessRight : classData.getComplex().get(3).getComplex().get(0).getComplex()) {
			System.out.println("    AttributeId: " + accessRight.getComplex().get(0).getNumber());
			System.out.print("    Access Right: ");
			switch (accessRight.getComplex().get(1).getNumber().intValue()) {
			case 0:
				System.out.println("No access");
				break;
			case 1:
				System.out.println("Read only");
				break;
			case 2:
				System.out.println("Write only");
				break;
			case 3:
				System.out.println("Read Write");
				break;
			}
			if (accessRight.getComplex().get(2).getChoiceIndex() == Data.Choices.ARRAY) {
				System.out.print("    Access Selector: ");
				for (Data selector : accessRight.getComplex().get(2).getComplex()) {
					System.out.print(selector.getNumber() + ", ");
				}
				System.out.println();
			}
		}

		System.out.println("  Method Access Rights:");
		for (Data accessRight : classData.getComplex().get(3).getComplex().get(1).getComplex()) {
			System.out.println("    MethodId: " + accessRight.getComplex().get(0).getNumber());
		}
		System.out.println("}");
	}
}
