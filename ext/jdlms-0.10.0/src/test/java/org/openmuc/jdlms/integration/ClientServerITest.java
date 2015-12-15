/// *
// * Copyright 2012-15 Fraunhofer ISE
// *
// * This file is part of jDLMS.
// * For more information visit http://www.openmuc.org
// *
// * jDLMS is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * jDLMS is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with jDLMS. If not, see <http://www.gnu.org/licenses/>.
// *
// */
package org.openmuc.jdlms.integration;

//
// import java.io.BufferedInputStream;
// import java.io.BufferedOutputStream;
// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.IOException;
// import java.net.InetAddress;
// import java.net.InetSocketAddress;
// import java.net.ServerSocket;
// import java.net.Socket;
//
// import javax.net.ServerSocketFactory;
//
// import org.junit.Assert;
// import org.junit.Test;
// import org.openmuc.jdlms.TcpClientSap;
// import org.openmuc.jdlms.internal.impl.ClientConnection;
// import org.openmuc.jdlms.internal.impl.ClientSap.ReferencingMethod;
// import org.openmuc.jdlms.util.HexConverter;
//
public class ClientServerITest {
	//
	// private final static int PORT = 4059;
	//
	// private class DummyDlmsServer extends Thread {
	//
	// @Override
	// public void run() {
	//
	// ServerSocket serverSocket;
	// try {
	// serverSocket = ServerSocketFactory.getDefault().createServerSocket(PORT);
	// } catch (IOException e2) {
	// throw new RuntimeException(e2);
	// }
	//
	// Socket clientSocket = null;
	//
	// try {
	// clientSocket = serverSocket.accept();
	// } catch (IOException e) {
	// throw new RuntimeException(e);
	// }
	//
	// DataOutputStream os;
	// DataInputStream is;
	//
	// try {
	// os = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
	// } catch (IOException e) {
	// try {
	// clientSocket.close();
	// } catch (IOException e1) {
	// }
	// throw new RuntimeException(e);
	// }
	// try {
	// is = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
	// } catch (IOException e) {
	// try {
	// // this will also close the socket
	// os.close();
	// } catch (Exception e1) {
	// }
	// throw new RuntimeException(e);
	// }
	//
	// System.out.println("reading data");
	//
	// byte[] inputData = new byte[39];
	// try {
	// is.readFully(inputData);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println("reading data finished");
	//
	// byte[] expectedInputData = HexConverter
	// .getByteArrayFromShortHexString("000100100001001f601da109060760857405080101be10040e01000000065f1f040000bc3fffff");
	//
	// Assert.assertArrayEquals(expectedInputData, inputData);
	//
	// System.out.println("3");
	//
	// String responseString =
	// / "000100010010002b6129a109060760857405080101a203020100a305a103020100be10040e0800065f1f0400001c1f05dc0007";
	//
	// try {
	// os.write(HexConverter.getByteArrayFromShortHexString(responseString));
	// os.flush();
	// } catch (NumberFormatException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// Thread.sleep(2000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println("thread finished");
	//
	// }
	//
	// }
	//
	// // public static void main(String[] args) throws IOException {
	// // ServerSap serverSap;
	// // serverSap = new ServerSap(new ClientServerITest());
	// // serverSap.startListening();
	// // }
	//
	// @Test
	// public void testClientServerComm() throws Exception {
	//
	// DummyDlmsServer dummyDlmsServer = new DummyDlmsServer();
	// dummyDlmsServer.start();
	//
	// // Settings to connect to smart meter
	// // - IP Destination: 192.168.200.25 (may vary)
	// // - TCP Port: 4059 (DLMS standard)
	// // - WPort of smart meter: 1 (Management Logical Device)
	// // - WPort of client: 16 (Public client)
	// // - Referencing: Logical Name
	// TcpClientSap clientSap = new TcpClientSap(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), PORT), 1,
	// ReferencingMethod.LN, 16);
	//
	// // Alternatively, new ClientConnectionFactory() can be used to
	// // instantiate factory. Note that this does not work in OSGi!
	//
	// ClientConnection connection = null;
	// try {
	// connection = clientSap.connect(3000);
	//
	// Thread.sleep(1000);
	//
	// // connection.connect(3000);
	// System.out.println("\nConnected to smart meter\n");
	//
	// connection.close();
	// System.out.println("\nDisconnected from smart meter\n");
	// } catch (IOException e) {
	// throw e;
	// } finally {
	// if (connection != null && connection.isConnected()) {
	// connection.close();
	// System.out.println("\nDisconnected from smart meter\n");
	// }
	// }
	//
	// System.out.println("end");
	//
	// }
	//
}
