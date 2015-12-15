package org.openmuc.jdlms.sample.iskra;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

import org.openmuc.jdlms.client.AccessResultCode;
import org.openmuc.jdlms.client.ClientConnectionSettings.Authentication;
import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.SetRequest;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.HdlcClientConnectionSettings;

/**
 * Sample application that reads the current time from a Iskraemeco meter via HDLC and adds +1 hour to the time after
 * reading it, setting the new value on the smart meter.
 * 
 * @author Karsten Mueller-Bier
 */
public class ReadWriteSample {

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
			System.out.println("Connected\n");

			// Get parameter to read current time
			// - Interface class: 8 (Clock)
			// - Obis code: 0:0:1:0:0:255 (current time)
			// - Attribute ID: 2 (time)
			GetRequest getClock = new GetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 2);

			// Read time from meter
			List<GetResult> getResults = connection.get(2000, getClock);
			System.out.println("\nRead time from smart meter");
			System.out.println("Time: " + getClockString(getResults.get(0).getResultData().getByteArray()));

			// Set parameter to add +1 hour to read time
			// - Interface class: 8 (Clock)
			// - Obis code: 0:0:1:0:0:255 (current time)
			// - Attribute ID: 2 (time)
			byte[] value = getResults.get(0).getResultData().getByteArray();
			value[5] = (byte) ((value[5] + 1) % 12);
			SetRequest setClock = getClock.toSetRequest();
			setClock.data().setOctetString(value);

			// Write new value to smart meter
			List<AccessResultCode> setResults = connection.set(3000, setClock);
			System.out.println("Setting time on smart meter");
			System.out.println("Time: " + setResults.get(0) + "\n");

			// Read current time from smart meter again
			getResults = connection.get(2000, getClock);
			System.out.println("Time: " + getClockString(getResults.get(0).getResultData().getByteArray()) + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null && connection.isConnected()) {
				connection.disconnect(false);
				System.out.println("\nDisconnected from smart meter\n");
			}
		}
	}

	private static String getClockString(byte[] bytes) {
		int year = 1900 + (bytes[0] << 8 + bytes[1]);
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
			dayOfWeek = "Staurday";
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
