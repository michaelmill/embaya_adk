/*
 * Copyright 2012-15 Fraunhofer ISE
 *
 * This file is part of jDLMS.
 * For more information visit http://www.openmuc.org
 *
 * jDLMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jDLMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jDLMS.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jdlms;

import java.io.IOException;

import org.openmuc.jdlms.internal.transportlayer.tcp.TcpTConnectionListener;
import org.openmuc.jdlms.internal.transportlayer.tcp.TcpTransportLayerConnection;

final class TcpTConnectionListenerFriend implements TcpTConnectionListener {

	TcpServerSap serverSap;

	TcpTConnectionListenerFriend(TcpServerSap serverSap) {
		this.serverSap = serverSap;
	}

	@Override
	public void connectionIndication(TcpTransportLayerConnection connection, byte[] tSdu) {
		serverSap.connectionIndication(connection, tSdu);

	}

	@Override
	public void serverStoppedListeningIndication(IOException e) {
		serverSap.serverStoppedListeningIndication(e);
	}

	@Override
	public void connectionAttemptFailed(IOException e) {
		serverSap.serverStoppedListeningIndication(e);

	}

}
