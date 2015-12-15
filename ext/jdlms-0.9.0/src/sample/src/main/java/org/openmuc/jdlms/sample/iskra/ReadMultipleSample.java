package org.openmuc.jdlms.sample.iskra;

import java.io.IOException;
import java.util.List;
import java.util.ServiceLoader;

import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.Data;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IClientConnectionFactory;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.HdlcClientConnectionSettings;

/**
 * Sample application that reads the Total positive active energy value, scalar and unit simultaneously from a
 * Iscraemeco meter via HDLC
 * 
 * @author Karsten Mueller-Bier
 */
public class ReadMultipleSample {

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

			// Get parameter to read current time
			// - Interface class: 3 (Register)
			// - Obis code: 1:0:1:8:0:255 (positive active energy Total)
			// - Attribute ID: 2 (value)
			GetRequest getSumLiActivePowerValue = new GetRequest(3, new ObisCode(1, 0, 1, 8, 0, 255), 2);

			// Copy get parameter with new attribute ID
			// - Attribute ID: 3 (scalar and unit)
			GetRequest getSumLiActivePowerScaler = getSumLiActivePowerValue.changeAttributeId(3);

			// Read both attributes simultaneously
			List<GetResult> getResults = connection.get(2000, getSumLiActivePowerValue, getSumLiActivePowerScaler);

			System.out.println("\nRead positive active energy Total+");
			System.out.println("Value: " + getResults.get(0).getResultData().getNumber());

			// Attribute 3 is a complex type data.
			List<Data> childItems = getResults.get(1).getResultData().getComplex();
			// The first entry is its scalar (ten based).
			System.out.println("Scaler: " + childItems.get(0).getNumber());
			// The second is its unit as OBIS value. Consult IEC 62056-62 for a list of units
			System.out.println("Unit: " + childItems.get(1).getNumber());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null && connection.isConnected()) {
				connection.disconnect();
				System.out.println("\nDisconnected");
			}
		}
	}
}
