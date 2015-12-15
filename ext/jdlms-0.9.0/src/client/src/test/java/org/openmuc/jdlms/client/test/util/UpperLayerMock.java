package org.openmuc.jdlms.client.test.util;

import java.util.LinkedList;
import java.util.List;

import org.openmuc.jdlms.client.communication.IUpperLayer;

public class UpperLayerMock implements IUpperLayer {

	private final List<byte[]> receivedMessages = new LinkedList<byte[]>();
	private boolean remoteDisconnectHappened = false;

	@Override
	public void dataReceived(byte[] data) {
		receivedMessages.add(data);
	}

	@Override
	public void remoteDisconnect() {
		remoteDisconnectHappened = true;
	}

	public boolean hasRemoteDisconnectHappened() {
		return remoteDisconnectHappened;
	}

	public int numberOfReceivedMessages() {
		return receivedMessages.size();
	}

	public byte[] nextReceivedMessage() {
		return receivedMessages.remove(0);
	}

	public byte[] peekNextReceivedMessage() {
		return receivedMessages.get(0);
	}

	public void reset() {
		receivedMessages.clear();
		remoteDisconnectHappened = false;
	}
}
