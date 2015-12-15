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
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;
//
// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import org.openmuc.jdlms.GetRequest;
// import org.openmuc.jdlms.HdlcAddress;
// import org.openmuc.jdlms.JData;
// import org.openmuc.jdlms.MethodRequest;
// import org.openmuc.jdlms.ObisCode;
// import org.openmuc.jdlms.SelectiveAccessDescription;
// import org.openmuc.jdlms.SetRequest;
// import org.openmuc.jdlms.internal.cosem.context.MechanismName;
// import org.openmuc.jdlms.internal.hdlc.common.HdlcAddressPair;
// import org.openmuc.jdlms.internal.hdlc.impl.HdlcClientLayer;
// import org.openmuc.jdlms.internal.hdlc.states.Disconnected;
// import org.openmuc.jdlms.internal.impl.ClientConnection;
// import org.openmuc.jdlms.internal.impl.ConnectModule;
// import org.openmuc.jdlms.util.LowerLayerMock;
// import org.openmuc.jdlms.util.StringBytesHelper;
//
public class JdlmsClientOverHdlcTest {
	//
	// private final LowerLayerMock<HdlcAddressPair> loopback = new LowerLayerMock<HdlcAddressPair>();
	// private HdlcClientLayer lowerLayer = null;
	// private ClientConnection testee = null;
	//
	// private final HdlcAddress clientAddress = HdlcAddress.ReservedAddresses.CLIENT_PUBLIC_CLIENT;
	// private final HdlcAddress serverAddress = new HdlcAddress(1, 17, 2);
	//
	// @Before
	// public void setUp() throws IOException {
	// loopback.reset();
	// lowerLayer = new HdlcClientLayer(loopback, clientAddress, serverAddress, Disconnected.instance, true);
	// testee = new LNConnection(true, MechanismName.LOW, lowerLayer, new ConnectModule());
	//
	// loopback.onSentReceive(StringBytesHelper.getBytesFromString(
	// "a0 1f 21 02 23 73 e6 c7 81 80 12 05 01 7e 06 01 7e 07 04 00 00 00 01 08 04 00 00 00 01"));
	// loopback.onSentReceive(StringBytesHelper.getBytesFromString(
	// "a0 39 21 02 23 30 70 ec e6 e7 00 61 2a a1 09 06 07 60 85 74 05 08 01 01 a2 03 02 01 00 a3 05 a1 03 02 01 00 be
	// 11 04
	// / 0f 08 01 00 06 5f 1f 04 00 00 bc 1f 04 00 00 07 6a 4d"));
	//
	// testee.connect(0, new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 });
	//
	// loopback.clearSentMessages();
	// }
	//
	// @Test
	// public void testConnect() throws IOException {
	// loopback.reset();
	// lowerLayer = new HdlcClientLayer(loopback, clientAddress, serverAddress, Disconnected.instance, true);
	// testee = new LNConnection(true, MechanismName.LOW, lowerLayer, new ConnectModule());
	//
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1f 21 02 23 73 e6 c7 81 80 12 05 01 7e 06 01 7e 07 04 00 00 00 01 08 04 00 00 00 01"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 39 21 02 23 30 70 ec e6 e7 00 61 2a a1 09 06 07 60 85 74 05 08 01 01 a2 03 02 01 00 a3 05 a1 03 02 01 00 be
	// 11 04
	// / 0f 08 01 00 06 5f 1f 04 00 00 bc 1f 04 00 00 07 6a 4d"));
	//
	// testee.connect(0, new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 });
	//
	// Assert.assertTrue(testee.isConnected());
	// Assert.assertEquals(2, loopback.numberOfSentMessages());
	// loopback.nextSentMessage();
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// "7e a0 45 02 23 21 10 78 6f e6 e6 00 60 36 a1 09 06 07 60 85 74 05 08 01 01 8a 02 06 80 8b 07 60 85 74 05 08 02
	// 01 ac
	// / 0a 80 08 31 32 33 34 35 36 37 38 be 10 04 0e 01 00 00 00 06 5f 1f 04 00 00 bc 3f ff ff c0 48 7e"),
	// loopback.nextSentMessage());
	// }
	//
	// @Test
	// public void testGet() throws IOException {
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1f 21 02 23 52 6d f7 e6 e7 00 c4 01 02 00 09 0c 07 dc 0c 1f 01 02 10 2d 00 00 3c 00 57 a1"));
	//
	// GetRequest getClock = new GetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 2);
	//
	// testee.get(0, false, getClock);
	//
	// Assert.assertEquals(1, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// "7e a0 1a 02 23 21 32 f6 72 e6 e6 00 c0 01 02 00 08 00 00 01 00 00 ff 02 00 cd bb 7e"),
	// loopback.nextSentMessage());
	// }
	//
	// @Test
	// public void testGetList() throws IOException {
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 27 21 02 23 52 9c 79 e6 e7 00 c4 03 02 03 00 09 0c 07 dc 0c 1f 01 02 13 29 00 00 3c 00 00 10 00 3c 00 16 01
	// / 0f"));
	//
	// ObisCode clockAddress = new ObisCode(0, 0, 1, 0, 0, 255);
	// GetRequest getClock = new GetRequest(8, clockAddress, 2);
	// GetRequest getTimezone = getClock.changeAttributeId(3);
	// GetRequest getClockBase = getClock.changeAttributeId(9);
	//
	// testee.get(0, false, getClock, getTimezone, getClockBase);
	//
	// Assert.assertEquals(1, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// "7e a0 2f 02 23 21 32 73 80 e6 e6 00 c0 03 02 03 00 08 00 00 01 00 00 ff 02 00 00 08 00 00 01 00 00 ff 03 00 00
	// 08 00
	// / 00 01 00 00 ff 09 00 e5 31 7e"),
	// loopback.nextSentMessage());
	// }
	//
	// @Test
	// public void testGetSelectiveAccess() throws IOException {
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 3f 21 02 23 52 fc 97 e6 e7 00 c4 01 02 00 01 03 02 02 09 0c 07 dc 0c 03 01 05 2f 2a 00 00 3c 00 12 20 00 02
	// 02 09
	// / 0c 07 dc 0c 03 01 0d 00 34 00 00 3c 00 12 80 01 02 02 00 12 00 80 e6 f0"));
	//
	// List<JData> innerData = new ArrayList<JData>(4);
	// innerData.add(new JData());
	// innerData.add(new JData());
	// innerData.add(new JData());
	// innerData.add(new JData());
	// innerData.get(0).setUnsigned16(8);
	// innerData.get(1).setOctetString(new byte[] { 0x00, 0x00, 0x01, 0x00, 0x00, (byte) 0xFF });
	// innerData.get(2).setInteger8(2);
	// innerData.get(3).setUnsigned16(0);
	// JData innerStruct = new JData();
	// innerStruct.setStructure(innerData);
	//
	// List<JData> outerData = new ArrayList<JData>(4);
	// outerData.add(innerStruct);
	// outerData.add(new JData());
	// outerData.add(new JData());
	// outerData.add(new JData());
	// outerData.get(1).setOctetString(
	// new byte[] { 0x07, (byte) 0xDC, 0x0C, 0x01, 0x06, 0x0F, 0x35, 0x00, 0x00, (byte) 0x80, 0x00, 0x00 });
	// outerData.get(2).setOctetString(
	// new byte[] { 0x07, (byte) 0xDC, 0x0C, 0x03, 0x01, 0x0F, 0x35, 0x00, 0x00, (byte) 0x80, 0x00, 0x00 });
	// outerData.get(3).setArray(new LinkedList<JData>());
	// JData outerStruct = new JData();
	// outerStruct.setStructure(outerData);
	//
	// GetRequest getLog = new GetRequest(7, new ObisCode(1, 0, 99, 98, 0, 255), 2);
	// getLog.setAccessSelection(new SelectiveAccessDescription(1, outerStruct));
	//
	// testee.get(0, false, getLog);
	//
	// Assert.assertEquals(1, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// "7e a0 4d 02 23 21 32 48 37 e6 e6 00 c0 01 02 00 07 01 00 63 62 00 ff 02 01 01 02 04 02 04 12 00 08 09 06 00 00
	// 01 00
	// / 00 ff 0f 02 12 00 00 09 0c 07 dc 0c 01 06 0f 35 00 00 80 00 00 09 0c 07 dc 0c 03 01 0f 35 00 00 80 00 00 01 00
	// 53 74
	// / 7e"),
	// loopback.nextSentMessage());
	// }
	//
	// @Test
	// public void testGetDatablock() throws IOException {
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 52 fc d8 e6 e7 00 c4 02 02 00 00 00 00 00 00 82 03 f4 01 82 01 83 02 04 12 00 08 11 00 09 06 00
	// 00 01
	// / 00 00 ff 02 02 01 09 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16 01 00 02 03
	// 0f 05
	// / 16 03 00 02 03 0f 06 16 03 00 02 03 0f 07 16 01 00 02 03 0f 08 16 03 00 02 03 0f 09 16 01 00 01 06 02 02 0f 01
	// 03 01
	// / 02 02 0f 02 03 01 02 02 0f 03 03 01 02 02 0f 04 03 a0 61"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 54 ca bd 01 02 02 0f 05 03 01 02 02 0f 06 03 01 02 04 12 00 1b 11 00 09 06 00 00 02 00 00 ff 02
	// 02 01
	// / 04 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16 01 00 01 00 02 04 12 00 1d 11
	// 00 09
	// / 06 00 00 02 01 00 ff 02 02 01 06 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16
	// 03 00
	// / 02 03 0f 05 16 03 00 02 03 0f 06 16 03 00 01 00 02 72 26"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 56 d8 9e 04 12 00 09 11 00 09 06 00 00 0a 00 64 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02
	// 16 03
	// / 00 01 01 02 02 0f 01 03 01 02 04 12 00 09 11 00 09 06 00 00 0a 01 fb ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 09 11 00 09 06 00 00 0a 01 fc ff 02 02 01 02 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 38 79"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 58 a6 77 09 11 00 09 06 00 00 0a 32 80 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00
	// 01 01
	// / 02 02 0f 01 03 01 02 04 12 00 09 11 00 09 06 00 00 0a 32 81 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 01 00
	// / 01 01 02 02 0f 01 03 01 02 04 12 00 09 11 00 09 06 00 00 0a 32 82 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f
	// 02 16
	// / 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 0b 11 00 ec f9"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5a b4 54 09 06 00 00 0b 00 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 01 02
	// 02 0f
	// / 01 03 01 02 04 12 00 14 11 00 09 06 00 00 0d 00 00 ff 02 02 01 0a 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02
	// 03 0f
	// / 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 02 03 0f 06 16 03 00 02 03 0f 07 16 03 00 02 03 0f 08 16
	// 03 00
	// / 02 03 0f 09 16 03 00 02 03 0f 0a 16 03 00 01 01 02 3d c3"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5c 82 31 02 0f 01 03 01 02 04 12 00 06 11 00 09 06 00 00 0e 00 00 ff 02 02 01 04 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 01 00 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 01 03 02 02 0f 01 03 01 02 02 0f 02 03 01 02 02
	// 0f 03
	// / 03 01 02 04 12 00 16 11 00 09 06 00 00 0f 00 00 ff 02 02 01 04 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03
	// 0f 03
	// / 16 01 00 02 03 0f 04 16 03 00 01 00 02 04 12 00 16 b4 da"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5e 90 12 11 00 09 06 00 00 0f 01 00 ff 02 02 01 04 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02
	// 03 0f
	// / 03 16 01 00 02 03 0f 04 16 03 00 01 00 02 04 12 00 13 11 00 09 06 00 00 14 00 00 ff 02 02 01 09 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06 16 03 00 02 03
	// 0f 07
	// / 16 03 00 02 03 0f 08 16 03 00 02 03 0f 09 16 03 00 4d e6"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 50 ee fb 01 00 02 04 12 00 07 11 01 09 06 00 00 15 00 00 ff 02 02 01 08 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 01 01 02 0f 01 0f 02 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06 16 03 00
	// 02 03
	// / 0f 07 16 01 00 02 03 0f 08 16 01 00 01 02 02 02 0f 01 03 01 02 02 0f 02 03 01 02 04 12 00 07 11 01 09 06 00 00
	// 15 00
	// / 01 ff 02 02 01 08 02 03 0f 01 16 01 00 02 03 0f 02 c2 b4"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 52 e5 e1 16 01 01 02 0f 01 0f 02 02 03 0f 03 16 03 00 02 03 0f 04 bd 4e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 74 c8 9c e6 e7 00 c4 02 02 00 00 00 00 01 00 82 03 f4 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06
	// 16 03
	// / 00 02 03 0f 07 16 01 00 02 03 0f 08 16 01 00 01 02 02 02 0f 01 03 01 02 02 0f 02 03 01 02 04 12 00 07 11 01 09
	// 06 00
	// / 00 15 00 02 ff 02 02 01 08 02 03 0f 01 16 01 00 02 03 0f 02 16 01 01 02 0f 01 0f 02 02 03 0f 03 16 03 00 02 03
	// 0f 04
	// / 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06 16 03 00 2e 47"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 76 da bf 02 03 0f 07 16 01 00 02 03 0f 08 16 01 00 01 02 02 02 0f 01 03 01 02 02 0f 02 03 01 02
	// 04 12
	// / 00 17 11 00 09 06 00 00 16 00 00 ff 02 02 01 09 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00
	// 02 03
	// / 0f 04 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06 16 03 00 02 03 0f 07 16 03 00 02 03 0f 08 16 03 00 02 03 0f 09
	// 16 03
	// / 00 01 00 02 04 12 00 29 11 00 09 06 00 00 19 00 00 35 04"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 78 a4 56 ff 02 02 01 06 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f
	// 04 16
	// / 03 00 02 03 0f 05 16 03 00 02 03 0f 06 16 03 00 01 00 02 04 12 00 2a 11 00 09 06 00 00 19 01 00 ff 02 02 01 0a
	// 02 03
	// / 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06
	// 16 03
	// / 00 02 03 0f 07 16 03 00 02 03 0f 08 16 03 00 02 03 aa d9"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 7a b6 75 0f 09 16 03 00 02 03 0f 0a 16 03 00 01 00 02 04 12 00 2c 11 00 09 06 00 00 19 03 00 ff
	// 02 02
	// / 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03 00
	// 01 00
	// / 02 04 12 00 2d 11 00 09 06 00 00 19 04 00 ff 02 02 01 04 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03
	// 16 03
	// / 00 02 03 0f 04 16 03 00 01 00 02 04 12 00 0f 11 00 cf 0e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 7c 80 10 09 06 00 00 28 00 00 ff 02 02 01 08 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f
	// 03 16
	// / 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 02 03 0f 06 16 01 00 02 03 0f 07 16 01 00 02 03 0f 08 16 01 00
	// 01 04
	// / 02 02 0f 01 03 01 02 02 0f 02 03 01 02 02 0f 03 03 01 02 02 0f 04 03 01 02 04 12 00 11 11 00 09 06 00 00 29 00
	// 00 ff
	// / 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 bf ac"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 7e 92 33 00 01 01 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 00 00 2a 00 00 ff 02 02 01 02 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 01 00 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 01 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 40 0a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 70 ec da 01 07 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11
	// 00 09
	// / 06 00 00 60 01 08 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00
	// 00 60
	// / 01 09 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 02 00
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 b6 2a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 72 fe f9 01 00 02 04 12 00 01 11 00 09 06 00 00 60 02 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 03 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 01 00
	// / 01 00 02 04 12 00 01 11 00 09 06 00 00 60 03 02 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 00 60 07 00 ff 02 02 01 02 af 0a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 74 d1 a5 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 33 a8"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 96 d4 58 e6 e7 00 c4 02 02 00 00 00 00 02 00 82 03 f4 00 01 11 00 09 06 00 00 60 07 01 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 02 ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 03 ff 02 02 01 02 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 73 61"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 98 aa b1 00 00 60 07 05 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 00 60 07 06 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 60 07 07 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00
	// 60 07
	// / 08 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 31 4a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 9a b8 92 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 09 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 0a ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 0b ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01
	// 00 01
	// / 00 02 04 12 00 01 11 00 09 06 00 00 60 07 0c ff 02 5d 83"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 9c 8e f7 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00
	// 00 60
	// / 07 0d ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 0e
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 0f ff 02 02 01
	// 02 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 28 3b"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 9e 9c d4 12 00 01 11 00 09 06 00 00 60 07 10 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 01 00
	// / 01 00 02 04 12 00 01 11 00 09 06 00 00 60 07 11 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 00 60 07 12 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00
	// 01 11
	// / 00 09 06 00 00 60 07 13 ff 02 02 01 02 02 03 0f 01 19 90"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 90 e2 3d 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 0a 80 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 60 60 00 ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 61 61 00 ff 02 02 01 02 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 67 be"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 92 f0 1e 00 00 80 06 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 00 80 06 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 80 07 0b ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00
	// 80 07
	// / 0c ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 06 fd"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 94 c6 7b 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 0d ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 0e ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 0f ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 01
	// / 00 02 04 12 00 01 11 00 09 06 00 00 80 07 10 ff 02 4b b8"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 96 cd 61 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 ae 30"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b8 a8 90 e6 e7 00 c4 02 02 00 00 00 00 03 00 82 03 f4 02 04 12 00 01 11 00 09 06 00 00 80 07 11
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 15 ff 02 02 01
	// 02 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 16 ff 02 02 01 02 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 5c f7"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 ba ba b3 00 09 06 00 00 80 07 17 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 00 80 07 18 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00
	// 01 11
	// / 00 09 06 00 00 80 07 19 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09
	// 06 00
	// / 00 80 07 1a ff 02 02 01 02 02 03 0f 01 16 01 00 02 ad d6"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 bc 8c d6 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 1b ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 1f ff 02 02 01 02 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 20 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f
	// 02 16
	// / 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 db 54"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 be 9e f5 21 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 80 07 22 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00
	// 80 07
	// / 23 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 24 ff
	// 02 02
	// / 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 b4 b8"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b0 e0 1c 00 02 04 12 00 01 11 00 09 06 00 00 80 07 25 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 29 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 01
	// / 00 02 04 12 00 01 11 00 09 06 00 00 80 07 2a ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02
	// 04 12
	// / 00 01 11 00 09 06 00 00 80 07 2b ff 02 02 01 02 02 6e 67"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b2 f2 3f 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 2c
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 2d ff 02 02 01
	// 02 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 07 2e ff 02 02 01 02 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 46 f8"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b4 c4 5a 00 09 06 00 00 80 07 2f ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00
	// 02 04
	// / 12 00 03 11 00 09 06 00 00 80 08 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01
	// 00 01
	// / 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 08 01 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02
	// 16 01
	// / 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 65 a8"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b6 d6 79 04 12 00 03 11 00 09 06 00 00 80 08 02 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02
	// 16 01
	// / 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 08 03 ff 02 02 01 03 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80
	// 08 0a
	// / ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 12 1b"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 b8 b1 a9 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 c3 cc"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 da bc d0 e6 e7 00 c4 02 02 00 00 00 00 04 00 82 03 f4 12 00 03 11 00 09 06 00 00 80 08 0b ff 02
	// 02 01
	// / 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00
	// 09 06
	// / 00 00 80 08 0c ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01
	// 03 01
	// / 02 04 12 00 03 11 00 09 06 00 00 80 08 0d ff 02 02 7b c0"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 dc 8a b5 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01
	// 03 01
	// / 02 04 12 00 03 11 00 09 06 00 00 80 08 14 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03
	// 16 01
	// / 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 08 15 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 b1 d1"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 de 98 96 01 02 04 12 00 03 11 00 09 06 00 00 80 08 16 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 08 17 ff 02 02 01 03
	// 02 03
	// / 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00
	// 00 80
	// / 08 1e ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f bf 94"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d0 e6 7f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00
	// 00 80
	// / 08 1f ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02
	// 04 12
	// / 00 03 11 00 09 06 00 00 80 08 20 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00
	// 01 01
	// / 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 bf 2a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d2 f4 5c 80 08 21 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00
	// 01 01
	// / 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 00 00 80 0a 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 03 00
	// / 01 00 02 04 12 00 01 11 00 09 06 00 00 80 0a 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 00 80 0a 02 ff 02 02 01 02 0e 91"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d4 c2 39 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 0a
	// 03 ff
	// / 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 14 00 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 14 01 ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 2e c4"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d6 d0 1a 11 00 09 06 00 00 80 14 02 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01
	// 00 02
	// / 04 12 00 01 11 00 09 06 00 00 80 14 03 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 00 80 14 0a ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 80 14 0b ff 02 02 01 02 02 03 0f 01 16 01 00 a2 7d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d8 ae f3 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 14 0c ff 02 02 01 02 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 14 14 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 14 15 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 7c 4d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 da a5 e9 1e 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 0c 57"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 fc 88 94 e6 e7 00 c4 02 02 00 00 00 00 05 00 82 03 f4 03 00 01 00 02 04 12 00 01 11 00 09 06 00
	// 00 80
	// / 1e 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 1e 02
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 1e 03 ff 02 02 01
	// 02 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 fa ee"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 fe 9a b7 12 00 01 11 00 09 06 00 00 80 1e 0a ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 03 00
	// / 01 00 02 04 12 00 01 11 00 09 06 00 00 80 1e 0b ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 00 80 1e 0c ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00
	// 01 11
	// / 00 09 06 00 00 80 1e 0d ff 02 02 01 02 02 03 0f 01 0b ad"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f0 e4 5e 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 1e 14 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 1e 15 ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 1e 16 ff 02 02 01 02 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 5e 82"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f2 f6 7d 00 00 80 1e 17 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 00 80 1e 1e ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 80 1e 1f ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00
	// 80 28
	// / 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 18 25"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f4 c0 18 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 28 01 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 28 02 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 28 03 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01
	// 00 01
	// / 00 02 04 12 00 01 11 00 09 06 00 00 80 28 04 ff 02 bc 9d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f6 d2 3b 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00
	// 00 80
	// / 32 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 3c 00
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 03 11 00 09 06 00 00 80 3c 01 ff 02 02 01
	// 03 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 04 ee"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f8 ac d2 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c 02 ff 02 02 01
	// 03 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06
	// 00 00
	// / 80 3c 03 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 03 11 00 09 06 00 00 80 3c
	// 04 ff
	// / 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 1f 98"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 fa be f1 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c
	// 0a ff
	// / 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00
	// 03 11
	// / 00 09 06 00 00 80 3c 0b ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02
	// 02 0f
	// / 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c 0c b4 b5"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 fc 91 ad ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 fa 7d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 1e 94 50 e6 e7 00 c4 02 02 00 00 00 00 06 00 82 03 f4 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03
	// 01 02
	// / 04 12 00 03 11 00 09 06 00 00 80 3c 14 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16
	// 01 00
	// / 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c 15 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f
	// 02 16
	// / 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 00 7a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 10 ea b9 02 04 12 00 03 11 00 09 06 00 00 80 3c 16 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f
	// 02 16
	// / 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c 17 ff 02 02 01 03 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00
	// 80 3c
	// / 18 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 6b d1"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 12 f8 9a 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 00 00
	// 80 3c
	// / 1e ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 3c 1f ff
	// 02 02
	// / 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 3c 20 ff 02 02 01 02
	// 02 03
	// / 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 71 57"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 14 ce ff 00 03 11 00 09 06 00 00 80 3c 28 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 02
	// / 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c 29 ff 02 02 01 03 02 03 0f 01
	// 16 01
	// / 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c 2a
	// ff 02
	// / 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 c4 4e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 16 dc dc 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3c 2b
	// ff 02
	// / 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03
	// 11 00
	// / 09 06 00 00 80 3c 2c ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02
	// 0f 01
	// / 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3d 00 ff 31 70"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 18 a2 35 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02
	// 0f 01
	// / 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3d 01 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03
	// 0f 03
	// / 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3d 02 ff 02 02 01 03 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 7f ca"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 1a b0 16 01 03 01 02 04 12 00 03 11 00 09 06 00 00 80 3d 03 ff 02 02 01 03 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 00 00 80 3d 0a ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 3d 0b ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 39 5d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 1c 86 73 11 00 09 06 00 00 80 3d 0c ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01
	// 00 02
	// / 04 12 00 01 11 00 09 06 00 00 80 3d 0d ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 00 80 3d 0e ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 80 3d 0f ff 02 02 01 02 02 03 0f 01 16 01 00 3f c4"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 1e 8d 69 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 f3 f7"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 30 e8 98 e6 e7 00 c4 02 02 00 00 00 00 07 00 82 03 f4 00 80 3e 00 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 3e 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 03 00 01 00 02 04 12 00 03 11 00 09 06 00 00 80 3e 02 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 02
	// / 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 f2 df"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 32 fa bb 00 03 11 00 09 06 00 00 80 3e 03 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 02
	// / 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 00 00 80 3e 04 ff 02 02 01 02 02 03 0f 01
	// 16 01
	// / 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 03 11 00 09 06 00 00 80 3e 05 ff 02 02 01 03 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 05 73"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 34 cc de 03 01 02 04 12 00 01 11 00 09 06 00 00 80 3e 06 ff 02 02 01 02 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 01 00
	// / 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 00 80 65 02 ff 02 02 01 02 2d 96"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 36 de fd 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65
	// 03 ff
	// / 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 04 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 05 ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 44 e6"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 38 a0 14 11 00 09 06 00 00 80 65 06 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01
	// 00 02
	// / 04 12 00 01 11 00 09 06 00 00 80 65 07 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 00 80 65 08 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 80 65 0f ff 02 02 01 02 02 03 0f 01 16 01 00 e7 8d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 3a b2 37 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 10 ff 02 02 01 02 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 11 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 12 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 da 11"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 3c 84 52 65 19 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11
	// 00 09
	// / 06 00 00 80 65 1a ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00
	// 00 80
	// / 65 1b ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 65 1c
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 7e 2d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 3e 96 71 01 00 02 04 12 00 01 11 00 09 06 00 00 80 66 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 00 80 66 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 01 00
	// / 01 00 02 04 12 00 01 11 00 09 06 00 00 80 67 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 00 80 67 01 ff 02 02 01 02 ca 1b"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 30 f1 a1 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 33 a8"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 52 fc d8 e6 e7 00 c4 02 02 00 00 00 00 08 00 82 03 f4 00 01 11 00 09 06 00 00 80 67 02 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 c4 01 00 ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 00 c4 01 01 ff 02 02 01 02 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 76 92"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 54 ca bd 00 00 c4 03 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 00 c4 3c 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 00 c4 61 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 13 11 00 09 06 00 01
	// 14 00
	// / 00 ff 02 02 01 09 02 03 0f 01 16 01 00 02 03 0f 02 20 a5"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 56 d8 9e 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06 16
	// 03 00
	// / 02 03 0f 07 16 03 00 02 03 0f 08 16 03 00 02 03 0f 09 16 03 00 01 00 02 04 12 00 17 11 00 09 06 00 01 16 00 00
	// ff 02
	// / 02 01 09 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03
	// 00 02
	// / 03 0f 06 16 03 00 02 03 0f 07 16 03 00 02 03 0f 08 97 99"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 58 a6 77 16 03 00 02 03 0f 09 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 01 60 0a 80 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 04 11 00 09 06 00 01 80 32 00 ff 02 02 01 05 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01
	// 03 01
	// / 02 04 12 00 04 11 00 09 06 00 01 80 32 01 ff 02 02 ed 22"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5a b4 54 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01
	// 00 02
	// / 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 00 01 80 32 02 ff 02 02 01 05 02 03 0f 01
	// 16 01
	// / 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01
	// 02 04
	// / 12 00 04 11 00 09 06 00 01 80 32 03 ff 02 02 01 05 31 dc"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5c 82 31 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02
	// 03 0f
	// / 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 00 01 80 32 14 ff 02 02 01 02 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 01 80 32 15 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f
	// 02 16
	// / 01 00 01 00 02 04 12 00 01 11 00 09 06 00 01 80 32 d7 9f"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5e 90 12 16 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 01 80 32 17 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 01
	// 80 32
	// / 18 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 01 80 32 19 ff
	// 02 02
	// / 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 0a 21"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 50 ee fb 00 02 04 12 00 01 11 00 09 06 00 01 80 32 1a ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 01 80 32 1e ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 01
	// / 00 02 04 12 00 01 11 00 09 06 00 01 80 32 1f ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02
	// 04 12
	// / 00 01 11 00 09 06 00 01 80 32 20 ff 02 02 01 02 02 56 83"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 52 e5 e1 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 0f f8"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 74 c8 9c e6 e7 00 c4 02 02 00 00 00 00 09 00 82 03 f4 01 11 00 09 06 00 01 80 32 21 ff 02 02 01
	// 02 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 01 80 32 28 ff 02 02 01 02 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 02 60 0a 80 ff 02 02 01 02 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 01 00 01 00 02 04 12 00 04 11 00 09 06 00 fa 14"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 76 da bf 02 80 32 00 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01
	// 00 02
	// / 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 00 02 80 32 01 ff 02
	// 02 01
	// / 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01
	// 01 02
	// / 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 00 02 80 7d 92"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 78 a4 56 32 02 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02
	// 03 0f
	// / 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 00 02 80 32 03 ff 02 02 01
	// 05 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02
	// 02 0f
	// / 01 03 01 02 04 12 00 01 11 00 09 06 00 02 80 32 14 25 a1"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 7a b6 75 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09
	// 06 00
	// / 02 80 32 15 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 02 80
	// 32 16
	// / ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 02 80 32 17 ff 02
	// 02 01
	// / 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 2e 26"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 7c 80 10 02 04 12 00 01 11 00 09 06 00 02 80 32 18 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f
	// 02 16
	// / 01 00 01 00 02 04 12 00 01 11 00 09 06 00 02 80 32 19 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00
	// 01 00
	// / 02 04 12 00 01 11 00 09 06 00 02 80 32 1a ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04
	// 12 00
	// / 01 11 00 09 06 00 02 80 32 1e ff 02 02 01 02 02 03 e2 5a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 7e 92 33 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 02 80 32 1f ff
	// 02 02
	// / 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 02 80 32 20 ff 02 02 01 02
	// 02 03
	// / 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 02 80 32 21 ff 02 02 01 02 02 03 0f 01
	// 16 01
	// / 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 86 20"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 70 ec da 09 06 00 02 80 32 28 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02
	// 04 12
	// / 00 04 11 00 09 06 00 03 80 32 00 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00
	// 02 03
	// / 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 00 03 80 32 01 ff 02 02
	// 01 05
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f d8 86"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 72 fe f9 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12
	// 00 04
	// / 11 00 09 06 00 03 80 32 02 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03
	// 0f 04
	// / 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 00 03 80 32 03 ff 02 02 01 05
	// 02 03
	// / 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 ec 4c"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 74 d1 a5 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 42 23"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 96 d4 58 e6 e7 00 c4 02 02 00 00 00 00 0a 00 82 03 f4 02 0f 01 03 01 02 04 12 00 01 11 00 09 06
	// 00 03
	// / 80 32 14 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 03 80 32
	// 15 ff
	// / 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 03 80 32 16 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 9f 1e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 98 aa b1 04 12 00 01 11 00 09 06 00 03 80 32 17 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02
	// 16 01
	// / 00 01 00 02 04 12 00 01 11 00 09 06 00 03 80 32 18 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01
	// 00 02
	// / 04 12 00 01 11 00 09 06 00 03 80 32 19 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 03 80 32 1a ff 02 02 01 02 02 03 0f db 4c"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 9a b8 92 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 03 80 32 1e ff 02
	// 02 01
	// / 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 03 80 32 1f ff 02 02 01 02 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 03 80 32 20 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 54 58"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 9c 8e f7 06 00 03 80 32 21 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04
	// 12 00
	// / 01 11 00 09 06 00 03 80 32 28 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 04 11
	// 00 09
	// / 06 00 04 80 32 00 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16
	// 01 00
	// / 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 45 0e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 9e 9c d4 12 00 04 11 00 09 06 00 04 80 32 01 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 01 00
	// / 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09
	// 06 00
	// / 04 80 32 02 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00
	// 02 03
	// / 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 d6 16"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 90 e2 3d 04 11 00 09 06 00 04 80 32 03 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00
	// 02 03
	// / 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 00
	// 04 80
	// / 32 14 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 04 80 32 15
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 5f 17"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 92 f0 1e 01 00 02 04 12 00 01 11 00 09 06 00 04 80 32 16 ff 02 02 01 02 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 04 80 32 17 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 01 00
	// / 01 00 02 04 12 00 01 11 00 09 06 00 04 80 32 18 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 00 04 80 32 19 ff 02 02 01 02 67 b6"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 94 c6 7b 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 04 80 32
	// 1a ff
	// / 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 00 04 80 32 1e ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 00 04 80 32 1f ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 54 f9"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 96 cd 61 11 00 09 06 00 04 80 32 20 ff 02 02 01 02 02 03 0f 01 16 78 fe"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b8 a8 90 e6 e7 00 c4 02 02 00 00 00 00 0b 00 82 03 f4 01 00 02 03 0f 02 16 03 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 00 04 80 32 21 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00
	// 09 06
	// / 00 04 80 32 28 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00
	// 00 00
	// / 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 bd ae"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 ba ba b3 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 01 00 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 02 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 01 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 03 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 01
	// / 00 02 04 12 00 01 11 00 09 06 01 00 00 03 01 ff 02 cb b2"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 bc 8c d6 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01
	// 00 00
	// / 03 02 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 03 03
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 03 04 ff 02 02 01
	// 02 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 e5 03"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 be 9e f5 12 00 01 11 00 09 06 01 00 00 03 05 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 03 00
	// / 01 00 02 04 12 00 01 11 00 09 06 01 00 00 04 02 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 01 00 00 04 03 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00
	// 01 11
	// / 00 09 06 01 00 00 04 05 ff 02 02 01 02 02 03 0f 01 49 51"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b0 e0 1c 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 04 06 ff 02 02
	// 01 02
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 08 00 ff 02 02 01 02 02 03
	// 0f 01
	// / 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 00 08 02 ff 02 02 01 02 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 06 75"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b2 f2 3f 01 00 00 09 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12
	// 00 01
	// / 11 00 09 06 01 00 00 09 02 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 03 11 00
	// 09 06
	// / 01 00 01 04 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01
	// 03 01
	// / 02 04 12 00 03 11 00 09 06 01 00 01 05 00 ff 02 02 7b 1b"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b4 c4 5a 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01
	// 03 01
	// / 02 04 12 00 04 11 00 09 06 01 00 01 06 00 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03
	// 16 01
	// / 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 01 06 01
	// ff 02
	// / 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 86 9a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 b6 d6 79 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01
	// 02 04
	// / 12 00 04 11 00 09 06 01 00 01 06 02 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01
	// 00 02
	// / 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 01 06 03 ff 02
	// 02 01
	// / 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 9d 9e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 b8 b1 a9 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 be fe"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 da bc d0 e6 e7 00 c4 02 02 00 00 00 00 0c 00 82 03 f4 01 01 02 02 0f 01 03 01 02 04 12 00 04 11
	// 00 09
	// / 06 01 00 01 06 04 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16
	// 01 00
	// / 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 01 07 00 ff 02 02 01 03 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 dc 45"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 dc 8a b5 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 01 08 00 ff 02 02 01 03 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 01 08
	// 01 ff
	// / 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00
	// 03 11
	// / 00 09 06 01 00 01 08 02 ff 02 02 01 03 02 03 0f 01 e5 17"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 de 98 96 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00
	// 03 11
	// / 00 09 06 01 00 01 08 03 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02
	// 02 0f
	// / 01 03 01 02 04 12 00 03 11 00 09 06 01 00 01 08 04 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02
	// 03 0f
	// / 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 1e a1"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d0 e6 7f 11 00 09 06 01 00 02 04 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02
	// 03 0f
	// / 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 02 05 00 ff 02 02 01 03 02 03 0f 01 16 01
	// 00 02
	// / 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 02 06 00 ff 02
	// 02 01
	// / 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 de cc"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d2 f4 5c 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04
	// 12 00
	// / 04 11 00 09 06 01 00 02 06 01 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02
	// 03 0f
	// / 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 02 06 02 ff 02 02 01
	// 05 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 91 a4"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d4 c2 39 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00
	// 04 11
	// / 00 09 06 01 00 02 06 03 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f
	// 04 16
	// / 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 02 06 04 ff 02 02 01 05 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 e3 c5"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d6 d0 1a 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11
	// 00 09
	// / 06 01 00 02 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f
	// 01 03
	// / 01 02 04 12 00 03 11 00 09 06 01 00 02 08 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f
	// 03 16
	// / 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 e7 7d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 d8 ae f3 09 06 01 00 02 08 01 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f
	// 03 16
	// / 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 02 08 02 ff 02 02 01 03 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 02 08 03 ff 02 02 01
	// 03 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 4b 24"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 da a5 e9 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 07 0e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 fc 88 94 e6 e7 00 c4 02 02 00 00 00 00 0d 00 82 03 f4 06 01 00 02 08 04 ff 02 02 01 03 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 03 04
	// 00 ff
	// / 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00
	// 03 11
	// / 00 09 06 01 00 03 05 00 ff 02 02 01 03 02 03 0f 01 97 e7"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 fe 9a b7 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00
	// 04 11
	// / 00 09 06 01 00 03 06 00 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f
	// 04 16
	// / 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 03 06 01 ff 02 02 01 05 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 03 ce"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f0 e4 5e 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11
	// 00 09
	// / 06 01 00 03 06 02 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16
	// 01 00
	// / 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 03 06 03 ff 02 02 01 05 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 2e b4"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f2 f6 7d 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09
	// 06 01
	// / 00 03 06 04 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00
	// 02 03
	// / 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 03 07 00 ff 02 02 01 03 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 c2 90"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f4 c0 18 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 03 08 00 ff 02 02 01 03 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 03 08 01 ff
	// 02 02
	// / 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11
	// 00 09
	// / 06 01 00 03 08 02 ff 02 02 01 03 02 03 0f 01 16 01 87 33"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f6 d2 3b 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11
	// 00 09
	// / 06 01 00 03 08 03 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f
	// 01 03
	// / 01 02 04 12 00 03 11 00 09 06 01 00 03 08 04 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f
	// 03 16
	// / 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 32 a5"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 f8 ac d2 09 06 01 00 04 04 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f
	// 03 16
	// / 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 04 05 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02
	// 03 0f
	// / 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 04 06 00 ff 02 02 01
	// 05 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 6f 55"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 fa be f1 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00
	// 04 11
	// / 00 09 06 01 00 04 06 01 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f
	// 04 16
	// / 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 04 06 02 ff 02 02 01 05 02
	// 03 0f
	// / 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 17 e1"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 fc 91 ad 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 1c db"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 1e 94 50 e6 e7 00 c4 02 02 00 00 00 00 0e 00 82 03 f4 0f 01 03 01 02 04 12 00 04 11 00 09 06 01
	// 00 04
	// / 06 03 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03
	// 0f 05
	// / 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 04 06 04 ff 02 02 01 05 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 da 84"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 10 ea b9 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 04
	// 07 00
	// / ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12
	// 00 03
	// / 11 00 09 06 01 00 04 08 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01
	// 02 02
	// / 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 04 08 07 d5"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 12 f8 9a 01 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01
	// 02 02
	// / 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 04 08 02 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00
	// 02 03
	// / 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 04 08 03 ff 02 02 01 03 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 21 1d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 14 ce ff 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 04 08 04 ff 02 02 01 03 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 09 04 00 ff
	// 02 02
	// / 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11
	// 00 09
	// / 06 01 00 09 05 00 ff 02 02 01 03 02 03 0f 01 16 01 17 2d"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 16 dc dc 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11
	// 00 09
	// / 06 01 00 09 06 00 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16
	// 01 00
	// / 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 09 06 01 ff 02 02 01 05 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 8a b9"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 18 a2 35 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09
	// 06 01
	// / 00 09 06 02 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00
	// 02 03
	// / 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 09 06 03 ff 02 02 01 05 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 9b 36"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 1a b0 16 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01
	// 00 09
	// / 06 04 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03
	// 0f 05
	// / 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 09 08 00 ff 02 02 01 03 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f af 3e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 1c 86 73 01 03 01 02 04 12 00 03 11 00 09 06 01 00 09 08 01 ff 02 02 01 03 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 09 08 02 ff 02 02
	// 01 03
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09
	// 06 01
	// / 00 09 08 03 ff 02 02 01 03 02 03 0f 01 16 01 00 02 60 e4"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 1e 8d 69 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 ff 96"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 30 e8 98 e6 e7 00 c4 02 02 00 00 00 00 0f 00 82 03 f4 03 01 02 04 12 00 03 11 00 09 06 01 00 09
	// 08 04
	// / ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12
	// 00 03
	// / 11 00 09 06 01 00 0a 04 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01
	// 02 02
	// / 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 0a 05 51 50"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 32 fa bb 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01
	// 02 02
	// / 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 0a 06 00 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00
	// 02 03
	// / 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01
	// 00 0a
	// / 06 01 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f eb f6"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 34 cc de 02 16 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02
	// 0f 01
	// / 03 01 02 04 12 00 04 11 00 09 06 01 00 0a 06 02 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03
	// 0f 03
	// / 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 04 11 00 09 06 01 00 0a
	// 06 03
	// / ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 12 8c"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 36 de fd 01 00 02 03 0f 03 16 01 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01
	// 03 01
	// / 02 04 12 00 04 11 00 09 06 01 00 0a 06 04 ff 02 02 01 05 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03
	// 16 01
	// / 00 02 03 0f 04 16 01 00 02 03 0f 05 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 0a 08 00
	// ff 02
	// / 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 6c 6e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 38 a0 14 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 0a 08 01
	// ff 02
	// / 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03
	// 11 00
	// / 09 06 01 00 0a 08 02 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02
	// 0f 01
	// / 03 01 02 04 12 00 03 11 00 09 06 01 00 0a 08 03 ff 5d eb"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 3a b2 37 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03 0f 03 16 01 00 01 01 02 02
	// 0f 01
	// / 03 01 02 04 12 00 03 11 00 09 06 01 00 0a 08 04 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 02 03
	// 0f 03
	// / 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 15 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 2d 03"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 3c 84 52 01 03 01 02 04 12 00 03 11 00 09 06 01 00 1f 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00
	// 02 03
	// / 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 20 07 00 ff 02 02
	// 01 03
	// / 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09
	// 06 01
	// / 00 29 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 87 0e"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 3e 96 71 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09
	// 06 01
	// / 00 33 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 02 0f 01 03
	// 01 02
	// / 04 12 00 03 11 00 09 06 01 00 34 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 02 03 0f 03 16
	// 01 00
	// / 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 93 62"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 30 f1 a1 01 00 3d 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 e8 13"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 52 fc d8 e6 e7 00 c4 02 02 00 00 00 00 10 00 82 03 f4 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01
	// 02 02
	// / 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 47 07 00 ff 02 02 01 03 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00
	// 02 03
	// / 0f 03 16 01 00 01 01 02 02 0f 01 03 01 02 04 12 00 03 11 00 09 06 01 00 48 07 00 ff 02 02 01 03 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 01 00 02 03 0f 03 16 01 00 01 01 02 7b e3"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 54 ca bd 02 0f 01 03 01 02 04 12 00 01 11 00 09 06 01 00 60 ef 00 ff 02 02 01 02 02 03 0f 01 16
	// 01 00
	// / 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 60 ef 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03
	// 0f 02
	// / 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 60 ef 02 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03
	// 00 01
	// / 00 02 04 12 00 01 11 00 09 06 01 00 60 f0 00 ff 02 0f 1a"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 56 d8 9e 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 01
	// 00 60
	// / f1 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 01 00 01 00 02 04 12 00 01 11 00 09 06 01 00 60 f2 00
	// ff 02
	// / 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00 01 11 00 09 06 01 00 60 f2 01 ff 02 02 01
	// 02 02
	// / 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 cf fd"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 58 a6 77 12 00 01 11 00 09 06 01 00 60 f3 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16
	// 03 00
	// / 01 00 02 04 12 00 01 11 00 09 06 01 00 60 f3 01 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00
	// 02 04
	// / 12 00 01 11 00 09 06 01 00 60 f4 00 ff 02 02 01 02 02 03 0f 01 16 01 00 02 03 0f 02 16 03 00 01 00 02 04 12 00
	// 07 11
	// / 01 09 06 01 00 62 01 00 ff 02 02 01 08 02 03 0f 01 1d 40"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5a b4 54 16 01 00 02 03 0f 02 16 01 01 02 0f 01 0f 02 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00
	// 02 03
	// / 0f 05 16 03 00 02 03 0f 06 16 03 00 02 03 0f 07 16 01 00 02 03 0f 08 16 01 00 01 02 02 02 0f 01 03 01 02 02 0f
	// 02 03
	// / 01 02 04 12 00 07 11 01 09 06 01 00 62 02 00 ff 02 02 01 08 02 03 0f 01 16 01 00 02 03 0f 02 16 01 01 02 0f 01
	// 0f 02
	// / 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 15 e5"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5c 82 31 05 16 03 00 02 03 0f 06 16 03 00 02 03 0f 07 16 01 00 02 03 0f 08 16 01 00 01 02 02 02
	// 0f 01
	// / 03 01 02 02 0f 02 03 01 02 04 12 00 07 11 01 09 06 01 00 63 01 00 ff 02 02 01 08 02 03 0f 01 16 01 00 02 03 0f
	// 02 16
	// / 01 01 02 0f 01 0f 02 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03 00 02 03 0f 06 16 03 00 02 03
	// 0f 07
	// / 16 01 00 02 03 0f 08 16 01 00 01 02 02 02 0f 01 03 53 4c"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 5e 90 12 01 02 02 0f 02 03 01 02 04 12 00 07 11 01 09 06 01 00 63 02 00 ff 02 02 01 08 02 03 0f
	// 01 16
	// / 01 00 02 03 0f 02 16 01 01 02 0f 01 0f 02 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 03 00 02 03
	// 0f 06
	// / 16 03 00 02 03 0f 07 16 01 00 02 03 0f 08 16 01 00 01 02 02 02 0f 01 03 01 02 02 0f 02 03 01 02 04 12 00 07 11
	// 01 09
	// / 06 01 00 63 61 00 ff 02 02 01 08 02 03 0f 01 16 01 9b 62"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a8 88 21 02 23 50 ee fb 00 02 03 0f 02 16 01 01 02 0f 01 0f 02 02 03 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03
	// 0f 05
	// / 16 03 00 02 03 0f 06 16 03 00 02 03 0f 07 16 01 00 02 03 0f 08 16 01 00 01 02 02 02 0f 01 03 01 02 02 0f 02 03
	// 01 02
	// / 04 12 00 07 11 01 09 06 01 00 63 62 00 ff 02 02 01 08 02 03 0f 01 16 01 00 02 03 0f 02 16 01 01 02 0f 01 0f 02
	// 02 03
	// / 0f 03 16 03 00 02 03 0f 04 16 03 00 02 03 0f 05 16 d0 80"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 1d 21 02 23 52 e5 e1 03 00 02 03 0f 06 16 03 00 02 03 0f 07 16 01 00 02 03 0f 24 c2"));
	// loopback.onSentReceive(helper.getBytesFromString(
	// "a0 29 21 02 23 74 10 5c e6 e7 00 c4 02 02 01 00 00 00 11 00 12 08 16 01 00 01 02 02 02 0f 01 03 01 02 02 0f 02
	// 03 01
	// / 89 5e"));
	//
	// GetRequest getParam = new GetRequest(15, new ObisCode(0, 0, 40, 0, 0, 255), 2);
	//
	// testee.get(0, false, getParam);
	//
	// Assert.assertEquals(154, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// " 7e a0 1a 02 23 21 32 f6 72 e6 e6 00 c0 01 02 00 0f 00 00 28 00 00 ff 02 00 3c f2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 54 7e 15 e6 e6 00 c0 02 02 00 00 00 00 63 f9 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 76 6e 17 e6 e6 00 c0 02 02 00 00 00 01 ea e8 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 98 1e 19 e6 e6 00 c0 02 02 00 00 00 02 71 da 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 ba 0e 1b e6 e6 00 c0 02 02 00 00 00 03 f8 cb 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 dc 3e 1d e6 e6 00 c0 02 02 00 00 00 04 47 bf 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 fe 2e 1f e6 e6 00 c0 02 02 00 00 00 05 ce ae 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 10 5e 11 e6 e6 00 c0 02 02 00 00 00 06 55 9c 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 32 4e 13 e6 e6 00 c0 02 02 00 00 00 07 dc 8d 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 54 7e 15 e6 e6 00 c0 02 02 00 00 00 08 2b 75 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 76 6e 17 e6 e6 00 c0 02 02 00 00 00 09 a2 64 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 98 1e 19 e6 e6 00 c0 02 02 00 00 00 0a 39 56 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 ba 0e 1b e6 e6 00 c0 02 02 00 00 00 0b b0 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 dc 3e 1d e6 e6 00 c0 02 02 00 00 00 0c 0f 33 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 fe 2e 1f e6 e6 00 c0 02 02 00 00 00 0d 86 22 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 10 5e 11 e6 e6 00 c0 02 02 00 00 00 0e 1d 10 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 32 4e 13 e6 e6 00 c0 02 02 00 00 00 0f 94 01 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 51 a3 81 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 71 a1 a0 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 91 af 47 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 b1 ad 66 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 d1 ab 05 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 f1 a9 24 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 11 a7 c3 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(helper.getBytesFromString(" 7e a0 08 02 23 21 31 a5 e2 7e"),
	// loopback.nextSentMessage());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(" 7e a0 14 02 23 21 54 7e 15 e6 e6 00 c0 02 02 00 00 00 10 e2 e9 7e"),
	// loopback.nextSentMessage());
	// }
	//
	// @Test
	// public void testSet() throws IOException {
	// loopback.onSentReceive(helper.getBytesFromString("a0 11 21 02 23 52 d5 96 e6 e7 00 c5 01 02 00 92 69"));
	//
	// SetRequest setClock = new SetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 2);
	// setClock.data().setOctetString(helper.getBytesFromString("07 dc 0c 1f 01 01 23 03 00 00 3c 00"));
	//
	// testee.set(0, false, setClock);
	//
	// Assert.assertEquals(1, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// "7e a0 28 02 23 21 32 af b0 e6 e6 00 c1 01 02 00 08 00 00 01 00 00 ff 02 00 09 0c 07 dc 0c 1f 01 01 23 03 00 00
	// 3c 00
	// / 37 97 7e"),
	// loopback.nextSentMessage());
	// }
	//
	// @Test
	// public void testSetList() throws IOException {
	// loopback.onSentReceive(helper.getBytesFromString("a0 13 21 02 23 52 5d 80 e6 e7 00 c5 05 02 02 00 00 c5 43"));
	//
	// byte[] value = new byte[] { 0x07, (byte) 0xdc, 0x0c, 0x1f, 0x01, 0x01, 0x23, 0x03, 0x00, 0x00, 0x3c, 0x00 };
	// ObisCode clockAddress = new ObisCode(0, 0, 1, 0, 0, 255);
	// SetRequest setClock = new SetRequest(8, clockAddress, 2);
	// setClock.data().setOctetString(value);
	//
	// SetRequest setTimezone = new SetRequest(8, clockAddress, 3);
	// setTimezone.data().setInteger16(60);
	//
	// testee.set(0, false, setClock, setTimezone);
	//
	// Assert.assertEquals(1, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// "7e a0 37 02 23 21 32 13 6e e6 e6 00 c1 04 02 02 00 08 00 00 01 00 00 ff 02 00 00 08 00 00 01 00 00 ff 03 00 02
	// 09 0c
	// / 07 dc 0c 1f 01 01 23 03 00 00 3c 00 10 00 3c fd 41 7e"),
	// loopback.nextSentMessage());
	// }
	//
	// @Test
	// public void testAction() throws IOException {
	// loopback.onSentReceive(helper.getBytesFromString("a0 11 21 02 23 52 d5 96 e6 e7 00 c7 01 02 00 e4 50"));
	//
	// MethodRequest resetLog = new MethodRequest(7, new ObisCode(1, 0, 99, 98, 0, 255), 1);
	// resetLog.data().setInteger8(0);
	//
	// testee.action(0, false, resetLog);
	//
	// Assert.assertEquals(1, loopback.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// helper.getBytesFromString(
	// "7e a0 1c 02 23 21 32 6e 49 e6 e6 00 c3 01 02 00 07 01 00 63 62 00 ff 01 01 0f 00 51 21 7e"),
	// loopback.nextSentMessage());
	// }
}
