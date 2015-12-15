/*
 * Copyright 2014 Fraunhofer ISE
 *
 * This file is part of j60870.
 * For more information visit http://www.openmuc.org
 *
 * j60870 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * j60870 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with j60870.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jdlms;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.Test;

public class ClientServerITest implements ServerEventListener, ClientConnectionEventListener {

	String host = "127.0.0.1";
	TcpConnectionBuilder connectionBuilder;
	TcpServerSap serverSap = null;
	int counter = 0;
	int serverCounter = 0;
	int counter2 = 0;
	Exception exception = null;
	volatile long clientTimestamp = 0;
	volatile long serverTimestamp = 0;

	@Test
	public void testClientServerCom() throws Exception {

		connectionBuilder = new TcpConnectionBuilder(InetAddress.getByName("127.0.0.1"));

		serverSap = new TcpServerSap(this);
		// serverSap.startListening();

		byte[] authenticationKey = new byte[] { 0x77, (byte) 0xDC, 0x17, (byte) 0xA2, (byte) 0xAC, 0x16, (byte) 0xAE,
				(byte) 0x83, (byte) 0x9C, (byte) 0xFB, (byte) 0x9F, 0x74, (byte) 0x8F, 0x0D, (byte) 0xD8, 0x5F };
		byte[] encryptionKey = new byte[] { 0x77, (byte) 0xDC, 0x17, (byte) 0xA2, (byte) 0xAC, 0x16, (byte) 0xAE,
				(byte) 0x83, (byte) 0x9C, (byte) 0xFB, (byte) 0x9F, 0x74, (byte) 0x8F, 0x0D, (byte) 0xD8, 0x5F };

		connectionBuilder.useGmacAuthentication(authenticationKey, encryptionKey);

		// ClientConnection clientConnection = clientSap.connect();

		// clientConnection.close();

	}

	@Override
	public void serverStoppedListeningIndication(IOException e) {
	}

	@Override
	public void connectionClosed(IOException e) {

	}

	@Override
	public void onEventReceived(EventNotification event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionAttemptFailed(IOException e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void readRequest() {
		// TODO Auto-generated method stub

	}

}
