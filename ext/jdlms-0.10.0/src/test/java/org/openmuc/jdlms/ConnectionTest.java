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
package org.openmuc.jdlms;

//
// import org.junit.Assert;
// import org.junit.BeforeClass;
// import org.junit.Test;
// import org.mockito.Mockito;
// import org.openmuc.jdlms.internal.impl.ClientConnection;
// import org.openmuc.jdlms.util.LowerLayerMock;
// import org.openmuc.jdlms.util.StringBytesHelper;
// import org.powermock.api.mockito.PowerMockito;
//
public class ConnectionTest {
	//
	// // /**
	// // * Stup class to test abstract class Connection
	// // *
	// // * @author Karsten Mueller-Bier
	// // */
	// // private class NoConnection extends ClientConnection {
	// //
	// // protected NoConnection(boolean confirmedMode, MechanismName authName, ApplicationContext appContext,
	// // LowerLayer<Object> lowerLayer, ConnectModule connectModule) {
	// // super(confirmedMode, authName, appContext, lowerLayer, connectModule);
	// // }
	// //
	// // @Override
	// // public List<GetResult> get(long timeout, GetRequest... params) throws IOException {
	// // return null;
	// // }
	// //
	// // @Override
	// // public List<GetResult> get(long timeout, boolean highPriority, GetRequest... params) throws IOException {
	// // return null;
	// // }
	// //
	// // @Override
	// // public List<AccessResultCode> set(long timeout, boolean highPriority, SetRequest... params) throws IOException
	// {
	// // return null;
	// // }
	// //
	// // @Override
	// // public List<MethodResult> action(long timeout, boolean highPriority, MethodRequest... params)
	// // throws IOException {
	// // return null;
	// // }
	// //
	// // @Override
	// // public void registerEventListener(IEventListener listener) {
	// // }
	// //
	// // @Override
	// // public void removeEventListener(IEventListener listener) {
	// //
	// // }
	// //
	// // @Override
	// // protected Conformance getProposedConformance() {
	// // return new Conformance(new byte[] { (byte) 0x00, (byte) 0xA0, (byte) 0x1F }, 24);
	// // }
	// //
	// // @Override
	// // protected void processPdu(COSEMpdu pdu) {
	// // }
	// //
	// // @Override
	// // public void connect(long timeout) throws IOException {
	// // connect(timeout, null, null);
	// // }
	// //
	// // @Override
	// // public void connect(long timeout, byte[] secret) throws IOException {
	// // connect(timeout, secret, null);
	// // }
	// //
	// // @Override
	// // public void connect(long timeout, byte[] secret, HlsSecretProcessor processor) throws IOException {
	// // establishConnection(timeout, secret, processor);
	// // }
	// //
	// // @Override
	// // public byte[] hlsAuthentication(byte[] processedChallenge, long timeout) throws IOException {
	// // return new byte[0];
	// // }
	// //
	// // }
	//
	// private LowerLayerMock<Object> lowerLayer = new LowerLayerMock<Object>();
	// private static ClientConnection connection;
	//
	// @BeforeClass
	// public static void setUp() throws Exception {
	// if (connection != null) {
	// connection.disconnect(false);
	// }
	//
	// // InetSocketAddress serverAddress = new InetSocketAddress("", 10);
	// // int logicalDeviceAddress = 10;
	// // ReferencingMethod referencingMethod = ReferencingMethod.LN;
	// // int clientApAddress = 0;
	//
	// }
	//
	// @Test
	// public void connectTest() throws Exception {
	// lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString(
	// "6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
	// // connection.connect(0);
	//
	// TcpClientSap tcpClientSap = PowerMockito.mock(TcpClientSap.class);
	// // ClientSap clientSap = new TcpClientSap("test", 99, 1, 1, ReferencingMethod.LN);
	//
	// PowerMockito.when(tcpClientSap, "newLowerLayerFactory").then(null);
	// PowerMockito.when(tcpClientSap.connect(Mockito.anyLong())).then(null);
	//
	// tcpClientSap.connect(0);
	//
	// Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// StringBytesHelper.getBytesFromString("601DA109060760857405080101BE10040E01000000065F1F040000A01FFFFF"),
	// lowerLayer.nextSentMessage());
	// }
	//
	// // @Test
	// // public void disconnectTest() throws IOException {
	// // lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString(
	// // "6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
	// // lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString("6303800100"));
	// // connection.connect(0);
	// // connection.disconnect(true);
	// //
	// // Assert.assertEquals(2, lowerLayer.numberOfSentMessages());
	// // lowerLayer.nextSentMessage();
	// // Assert.assertArrayEquals(StringBytesHelper.getBytesFromString("6203800100"), lowerLayer.nextSentMessage());
	// // Assert.assertEquals(false, connection.isConnected());
	// // Assert.assertEquals(false, lowerLayer.isConnected());
	// // }
	// //
	// // @Test
	// // public void disconnectLowerLayerTest() throws IOException {
	// // lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString(
	// // "6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
	// // connection.connect(0);
	// // connection.disconnect(false);
	// //
	// // Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
	// // Assert.assertEquals(false, connection.isConnected());
	// // Assert.assertEquals(false, lowerLayer.isConnected());
	// // }
	// //
	// // @Test
	// // public void disconnectFirstTest() {
	// // connection.disconnect(true);
	// //
	// // Assert.assertEquals(0, lowerLayer.numberOfSentMessages());
	// // Assert.assertEquals(false, connection.isConnected());
	// // Assert.assertEquals(false, lowerLayer.isConnected());
	// //
	// // }
}
