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
package org.openmuc.jdlms;

// package org.openmuc.jdlms.hdlc.client.test;
//
// import gnu.io.NoSuchPortException;
// import gnu.io.PortInUseException;
// import gnu.io.UnsupportedCommOperationException;
//
// import java.io.IOException;
//
// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import org.openmuc.jdlms.client.hdlc.HdlcAddress;
// import org.openmuc.jdlms.client.test.util.LoopbackPhysicalConnection;
// import org.openmuc.jdlms.client.test.util.StringBytesHelper;
// import org.openmuc.jdlms.client.test.util.UpperLayerMock;
// import org.openmuc.jdlms.internal.client.hdlc.common.HdlcAddressPair;
// import org.openmuc.jdlms.internal.client.hdlc.physical.IPhysicalConnection;
// import org.openmuc.jdlms.internal.client.hdlc.physical.LocalDataExchangeClient;
// import org.openmuc.jdlms.internal.client.hdlc.physical.PhysicalConnectionFactory;
//
public class LocalMeterExchangeClientTest {
	//
	// private final StringBytesHelper helper = new StringBytesHelper();
	//
	// private final LoopbackPhysicalConnection loopback = new LoopbackPhysicalConnection();
	// private final UpperLayerMock upperLayer = new UpperLayerMock();
	// private LocalDataExchangeClient client;
	//
	// private class LoopbackFactory extends PhysicalConnectionFactory {
	//
	// @Override
	// public IPhysicalConnection acquireSerialPort(String portName) throws NoSuchPortException, PortInUseException,
	// IOException, UnsupportedCommOperationException {
	// return loopback;
	// }
	// }
	//
	// @Before
	// public void setUp() throws Exception {
	// loopback.reset();
	// upperLayer.reset();
	// client = new LocalDataExchangeClient("", new LoopbackFactory(), 0, true);
	// }
	//
	// @Test
	// public void testConnect() throws IOException {
	// loopback.onSentReceiveMessage(helper.getBytesFromString("2F49534B355C324D543337322D333130370D0A"));
	// loopback.onSentReceiveMessage(helper.getBytesFromString("063235320D0A"));
	//
	// client.connect(0);
	//
	// Assert.assertEquals(2, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(helper.getBytesFromString("2F3F210D0A"), loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString("063235320D0A"), loopback.nextSentMessage());
	// }
	//
	// @Test(expected = IOException.class)
	// public void testConnectWithIncompatibleServer() throws IOException {
	// loopback.onSentReceiveMessage(helper.getBytesFromString("2F49534B3500004D543337322D333130370D0A"));
	//
	// client.connect(0);
	// }
	//
	// @Test
	// public void receiveScatteredHdlcFrameTest() throws IOException {
	// LocalDataExchangeClient client = new LocalDataExchangeClient("", new LoopbackFactory() {
	// @Override
	// public IPhysicalConnection acquireSerialPort(String portName) throws NoSuchPortException,
	// PortInUseException, IOException, UnsupportedCommOperationException {
	// return new LoopbackPhysicalConnection() {
	// @Override
	// public void send(byte[] data) throws IOException {
	// listener.dataReceived(helper.getBytesFromString("7EA0152521DAFFF1E6E700C401C20006"),
	// helper.getBytesFromString("7EA0152521DAFFF1E6E700C401C20006").length);
	// listener.dataReceived(helper.getBytesFromString("0000001AC80F7E"),
	// helper.getBytesFromString("0000001AC80F7E").length);
	// }
	// };
	// }
	// }, 0, false);
	//
	// UpperLayerMock upperLayer = new UpperLayerMock();
	// client.registerReceivingListener(new HdlcAddressPair(new HdlcAddress(18), new HdlcAddress(16)), upperLayer);
	//
	// client.connect(0);
	//
	// client.send(helper.getBytesFromString("7EA0192125BACC01E6E600C001C200030101010800FF020054097e"));
	// }
	//
	// @Test
	// public void receiveGarbageTest() throws IOException {
	// loopback.onSentReceiveMessage(helper.getBytesFromString("2F49534B355C324D543337322D333130370D0A"));
	// loopback.onSentReceiveMessage(helper.getBytesFromString("063235320D0A"));
	// loopback.onSentReceiveMessage(helper
	// .getBytesFromString("7ea03825215263f3e6e700c4010200010202021200010a0c4b414d4d313436343533353002021200100a0c4b414d453134363435333530b6817e"));
	// loopback.onSentReceiveMessage(helper
	// .getBytesFromString("7ea03825215263fde6e700c4010200010202021200010a0c4b414d4d313436343533353002021200100a0c4b414d453134363435333530b6817e"));
	//
	// client.connect(0);
	// loopback.clearSentMessages();
	// client.registerReceivingListener(new HdlcAddressPair(new HdlcAddress(18), new HdlcAddress(16)), upperLayer);
	//
	// client.send(helper.getBytesFromString("7ea0192125328c09e6e600c0010200110000290000ff020056d27e"));
	// client.send(helper.getBytesFromString("7ea0192125328c09e6e600c0010200110000290000ff020056d27e"));
	//
	// Assert.assertEquals(1, upperLayer.numberOfReceivedMessages());
	// }
	//
	// @Test
	// public void firstByteNotFlagTest() throws IOException {
	// loopback.onSentReceiveMessage(helper.getBytesFromString("2F49534B355C324D543337322D333130370D0A"));
	// loopback.onSentReceiveMessage(helper.getBytesFromString("063235320D0A"));
	// loopback.onSentReceiveMessage(helper
	// .getBytesFromString("fe7ea02025217391eb81801405020074060200740704000000010804000000016a0b7e"));
	//
	// client.connect(10);
	//
	// client.registerReceivingListener(new HdlcAddressPair(new HdlcAddress(18), new HdlcAddress(16)), upperLayer);
	// client.send(helper.getBytesFromString("7ea007212593ecd07e"));
	//
	// Assert.assertEquals(1, upperLayer.numberOfReceivedMessages());
	//
	// byte[] receivedMessage = new byte[32];
	// System.arraycopy(upperLayer.nextReceivedMessage(), 0, receivedMessage, 0, 32);
	// Assert.assertArrayEquals(
	// helper.getBytesFromString("a02025217391eb81801405020074060200740704000000010804000000016a0b"),
	// receivedMessage);
	// }
	//
	// @Test
	// public void doubleFlagByteTest() throws IOException {
	// loopback.onSentReceiveMessage(helper.getBytesFromString("2F49534B355C324D543337322D333130370D0A"));
	// loopback.onSentReceiveMessage(helper.getBytesFromString("063235320D0A"));
	// loopback.onSentReceiveMessage(helper
	// .getBytesFromString("7ea019212576ac0de6e600c0012200030101030800ff020096bc7e7ea0152521969779e6e700c401220006000006e5f8767e"));
	//
	// client.connect(10);
	//
	// client.registerReceivingListener(new HdlcAddressPair(new HdlcAddress(18), new HdlcAddress(16)), upperLayer);
	// client.send(helper.getBytesFromString("7ea019212576ac0de6e600c0012200030101030800ff020096bc7e"));
	//
	// Assert.assertEquals(1, upperLayer.numberOfReceivedMessages());
	//
	// byte[] receivedMessage = new byte[21];
	// System.arraycopy(upperLayer.nextReceivedMessage(), 0, receivedMessage, 0, 21);
	// Assert.assertArrayEquals(helper.getBytesFromString("a0152521969779e6e700c401220006000006e5f876"),
	// receivedMessage);
	// }
}
