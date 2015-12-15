package org.openmuc.jdlms.sample.landis;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.openmuc.jdlms.client.ClientConnectionSettings;
import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.ip.TcpClientConnectionSettings;

/**
 * Sample application that reads the current time with timezone and clock base of the internal clock from a Landis+Gyr
 * meter via TCP/IP.
 * 
 * @author Karsten Mueller-Bier
 */
public class ReadSample {

	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		// Settings to connect to smart meter
		// - IP Destination: 192.168.200.25 (may vary)
		// - TCP Port: 4059 (DLMS standard)
		// - WPort of smart meter: 1 (Management Logical Device)
		// - WPort of client: 16 (Public client)
		// - Referencing: Logical Name
		TcpClientConnectionSettings settings = new TcpClientConnectionSettings(new InetSocketAddress(
				InetAddress.getByName("192.168.200.25"), 4059), 1, 16, ReferencingMethod.LN);

		// Alternatively, new ClientConnectionFactory() can be used to
		// instantiate factory. Note that this does not work in OSGi!
		IClientConnectionFactory factory = ClientConnectionSettings.getFactory();
		IClientConnection connection = null;
		try {
			connection = factory.createClientConnection(settings);

			connection.connect(3000);
			System.out.println("\nConnected to smart meter\n");

			// Get parameter to read current time
			// - Interface class: 8 (Clock)
			// - Obis code: 0:0:1:0:0:255 (current time)
			// - Attribute ID: 2 (time)
			GetRequest getClock = new GetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 2);

			// Get parameter to read timezone
			// - Attribute ID: 3 (timezone)
			// - Interface class & Obis code see above
			GetRequest getTimezone = getClock.changeAttributeId(3);

			// Get parameter to read clock base
			// - Attribute ID: 9 (clock base)
			// - Interface class & Obis code see above
			GetRequest getClockBase = getClock.changeAttributeId(9);

			// Read all three parameter with a single operation
			List<GetResult> getResults = connection.get(1000, getClock, getTimezone, getClockBase);

			System.out.println("\nRead time, timezone and clock base from smart meter simultaniously");
			System.out.println("Time: " + getClockString(getResults.get(0).getResultData().getByteArray()));
			System.out.println("Timezone: " + getResults.get(1).getResultData().getNumber());
			System.out.println("Clock Base: " + getResults.get(2).getResultData().getNumber() + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null && connection.isConnected()) {
				connection.disconnect();
				System.out.println("\nDisconnected from smart meter\n");
			}
		}
	}

	private static String getClockString(byte[] bytes) {
		int year = ((bytes[0] << 8) & 0xFF00) + (bytes[1] & 0xFF);
		int month = bytes[2];
		int dayOfMonth = bytes[3];

		String dayOfWeek = "";
		switch (bytes[4]) {
		case 1:
			dayOfWeek = "Monday";
			break;
		case 2:
			dayOfWeek = "Tuesday";
			break;
		case 3:
			dayOfWeek = "Wednesday";
			break;
		case 4:
			dayOfWeek = "Thursday";
			break;
		case 5:
			dayOfWeek = "Friday";
			break;
		case 6:
			dayOfWeek = "Saturday";
			break;
		case 7:
			dayOfWeek = "Sunday";
			break;
		}

		int hour = bytes[5];
		int min = bytes[6];
		int sec = bytes[7];

		return dayOfWeek + " " + year + "/" + month + "/" + dayOfMonth + " " + hour + ":" + min + ":" + sec;
	}
}
