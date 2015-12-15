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
// package org.openmuc.jdlms;
//
// import java.io.IOException;
//
// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import org.openmuc.jdlms.internal.hdlc.common.HdlcAddressPair;
// import org.openmuc.jdlms.internal.hdlc.impl.Disconnected;
// import org.openmuc.jdlms.internal.hdlc.impl.HdlcClientLayer;
// import org.openmuc.jdlms.util.LowerLayerMock;
// import org.openmuc.jdlms.util.StringBytesHelper;
// import org.openmuc.jdlms.util.UpperLayerMock;
//
// public class HdlcClientLayerTest {
//
// private HdlcClientLayer connection;
// private final LowerLayerMock<HdlcAddressPair> lowerLayer = new LowerLayerMock<HdlcAddressPair>();
// private final UpperLayerMock upperLayer = new UpperLayerMock();
//
// private final HdlcAddress source = new HdlcAddress(16);
// private final HdlcAddress dest = new HdlcAddress(1, 17, 4);
//
// @Before
// public void setUp() throws Exception {
// connection = new HdlcClientLayer(lowerLayer, source, dest, Disconnected.instance, true);
// lowerLayer.reset();
// upperLayer.reset();
// }
//
// @Test
// public void connectTest() throws IOException {
// lowerLayer.onSentReceive(StringBytesHelper
// .getBytesFromString("A023210002002373F6C58180140502008006020080070400000001080400000001CE6A"));
//
// connection.connect(0);
//
// Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
// Assert.assertArrayEquals(StringBytesHelper.getBytesFromString("7EA00A00020023219318717E"),
// lowerLayer.nextSentMessage());
// }
// }
