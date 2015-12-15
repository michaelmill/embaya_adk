/*
 * Copyright 2012-15 Fraunhofer ISE
 *
 * This file is part of jDLMS. For more information visit http://www.openmuc.org
 *
 * jDLMS is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * jDLMS is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with jDLMS. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jdlms.util;

// package org.openmuc.jdlms.client.test.util;
//
// import gnu.io.UnsupportedCommOperationException;
//
// import java.io.IOException;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.TooManyListenersException;
//
// import org.openmuc.jdlms.internal.client.hdlc.physical.IPhysicalConnection;
// import org.openmuc.jdlms.internal.client.hdlc.physical.IPhysicalConnectionListener;
//
// public class LoopbackPhysicalConnection implements IPhysicalConnection {
// protected List<byte[]> sentMessages = new LinkedList<byte[]>();
// protected List<byte[]> messagesToReceive = new LinkedList<byte[]>();
// private boolean isConnected = true;
//
// protected IPhysicalConnectionListener listener = null;
//
// public void reset() {
// sentMessages.clear();
// messagesToReceive.clear();
// listener = null;
// }
//
// public void clearSentMessages() {
// sentMessages.clear();
// }
//
// public void onSentReceiveMessage(byte[] message) {
// messagesToReceive.add(message);
// }
//
// public int numberOfSentMessages() {
// return sentMessages.size();
// }
//
// public byte[] nextSentMessage() {
// if (sentMessages.size() == 0) {
// return null;
// }
// return sentMessages.remove(0);
// }
//
// @Override
// public void send(byte[] data) throws IOException {
// if (isConnected == false) {
// throw new IOException("Connection closed prior to send");
// }
// sentMessages.add(data);
//
// if (messagesToReceive.size() > 0) {
// byte[] nextMessage = messagesToReceive.remove(0);
// if (listener != null) {
// listener.dataReceived(nextMessage, nextMessage.length);
// }
// }
// }
//
// @Override
// public void close() {
// isConnected = false;
// }
//
// @Override
// public void setSerialParams(int baud, int databits, int stopbits, int parity)
// throws UnsupportedCommOperationException {
// }
//
// @Override
// public void registerListener(IPhysicalConnectionListener listener) throws TooManyListenersException {
// this.listener = listener;
// }
//
// @Override
// public void removeListener() {
// listener = null;
// }
//
// public void sendSynchrounous(byte[] data, int timeout) throws IOException {
// send(data);
// }
//
// @Override
// public boolean isClosed() {
// return false;
// }
// }
