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
package org.openmuc.jdlms.integration;

// package org.openmuc.jdlms.client.integration.test;
//
// import gnu.io.NoSuchPortException;
// import gnu.io.PortInUseException;
// import gnu.io.UnsupportedCommOperationException;
//
// import java.io.IOException;
// import java.util.List;
//
// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import org.openmuc.jdlms.client.GetRequest;
// import org.openmuc.jdlms.client.GetResult;
// import org.openmuc.jdlms.client.IClientConnection;
// import org.openmuc.jdlms.client.ObisCode;
// import org.openmuc.jdlms.client.hdlc.HdlcAddress;
// import org.openmuc.jdlms.client.test.util.LoopbackPhysicalConnection;
// import org.openmuc.jdlms.client.test.util.StringBytesHelper;
// import org.openmuc.jdlms.internal.client.cosem.context.MechanismName;
// import org.openmuc.jdlms.internal.client.hdlc.impl.HdlcClientLayer;
// import org.openmuc.jdlms.internal.client.hdlc.physical.IPhysicalConnection;
// import org.openmuc.jdlms.internal.client.hdlc.physical.LocalDataExchangeClient;
// import org.openmuc.jdlms.internal.client.hdlc.physical.PhysicalConnectionFactory;
// import org.openmuc.jdlms.internal.client.hdlc.states.Disconnected;
// import org.openmuc.jdlms.internal.client.impl.ConnectModule;
// import org.openmuc.jdlms.internal.client.impl.LNConnection;
//
public class CommunicationTest {
	//
	// private final StringBytesHelper helper = new StringBytesHelper();
	//
	// private final LoopbackPhysicalConnection loopback = new LoopbackPhysicalConnection();
	// private LocalDataExchangeClient localLayer;
	// private HdlcClientLayer hdlcLayer;
	// private IClientConnection cosemLayer;
	//
	// private class LoopbackFactory extends PhysicalConnectionFactory {
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
	// localLayer = new LocalDataExchangeClient("", new LoopbackFactory(), 0, true);
	// hdlcLayer = new HdlcClientLayer(localLayer, new HdlcAddress(16), new HdlcAddress(16, 17, 2),
	// Disconnected.instance, true);
	// cosemLayer = new LNConnection(true, MechanismName.LOWEST, hdlcLayer, new ConnectModule());
	// }
	//
	// @Test
	// public void test() throws IOException, InterruptedException {
	// loopback.onSentReceiveMessage(helper.getBytesFromString("2F49534B355C324D543337322D333130370D0A"));
	// loopback.onSentReceiveMessage(helper.getBytesFromString("063235320D0A"));
	// loopback.onSentReceiveMessage(helper
	// .getBytesFromString("7EA01F21202373657181801205017E06017E0704000000010804000000015F757E"));
	// loopback.onSentReceiveMessage(helper
	// .getBytesFromString("7EA03921202330F35AE6E700612AA109060760857405080101A203020100A305A103020100BE11040F080100065F1F0400007C1F04000007194A7E"));
	// loopback.onSentReceiveMessage(helper
	// .getBytesFromString("7EA019212023327019E6E700C401010009060000010000FF0BEB7E"));
	//
	// cosemLayer.connect(0);
	//
	// GetRequest getParam = new GetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 1);
	// List<GetResult> getResults = cosemLayer.get(0, true, getParam);
	//
	// Assert.assertEquals(5, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(helper.getBytesFromString("2F3F210D0A"), loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString("063235320D0A"), loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString("7EA0082023219398D27E"), loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString("7ea02c202321108a29e6e600601da109060760857405080101be10040e01000000065f1f040000bc3fffff0dc97e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString("7ea01a20232132d3c4e6e600c0010300080000010000ff010034c47e"),
	// loopback.nextSentMessage());
	//
	// Assert.assertArrayEquals(helper.getBytesFromString(getParam.getObisCode()), getResults.get(0).getResultData()
	// .getByteArray());
	// while (loopback.numberOfSentMessages() > 0) {
	// helper.printBytes(loopback.nextSentMessage());
	// }
	// }
}
