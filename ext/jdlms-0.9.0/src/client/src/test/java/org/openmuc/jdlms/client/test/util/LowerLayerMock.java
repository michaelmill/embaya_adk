package org.openmuc.jdlms.client.test.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.openmuc.jdlms.client.communication.ILowerLayer;
import org.openmuc.jdlms.client.communication.IUpperLayer;

public class LowerLayerMock<E> implements ILowerLayer<E> {

	private boolean connected = false;
	private IUpperLayer receivingListener = null;

	private final List<byte[]> sentMessages = new LinkedList<byte[]>();
	private final List<byte[]> receiveBuffer = new LinkedList<byte[]>();

	@Override
	public void connect(long timeout) throws IOException {
		if (connected) {
			throw new IOException();
		}

		connected = true;
	}

	@Override
	public void send(byte[] data) throws IOException {
		sentMessages.add(data);
		if (receiveBuffer.size() > 0) {
			receiveData(receiveBuffer.remove(0));
		}
	}

	@Override
	public void disconnect() throws IOException {
		if (connected == false) {
			throw new IOException();
		}

		connected = false;
	}

	@Override
	public void registerReceivingListener(Object key, IUpperLayer listener) throws IllegalArgumentException {
		if (receivingListener != null) {
			throw new IllegalArgumentException();
		}

		receivingListener = listener;
	}

	@Override
	public void removeReceivingListener(IUpperLayer listener) {
		if (receivingListener == listener) {
			receivingListener = null;
		}
	}

	public void clearSentMessages() {
		sentMessages.clear();
	}

	public int numberOfSentMessages() {
		return sentMessages.size();
	}

	public byte[] nextSentMessage() {
		return sentMessages.remove(0);
	}

	public byte[] peekNextSentMessage() {
		return sentMessages.get(0);
	}

	public void receiveData(byte[] data) {
		receivingListener.dataReceived(data);
	}

	public void remoteDisconnect() {
		receivingListener.remoteDisconnect();
	}

	public boolean isConnected() {
		return connected;
	}

	public void reset() {
		sentMessages.clear();
		connected = false;
		receivingListener = null;
		receiveBuffer.clear();
	}

	public void onSentReceive(byte[] data) {
		receiveBuffer.add(data);
	}

	@Override
	public void discardMessage(byte[] data) {
	}
}
