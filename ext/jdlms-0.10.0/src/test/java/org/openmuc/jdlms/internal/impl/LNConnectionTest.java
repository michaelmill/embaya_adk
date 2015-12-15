///*
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
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with jDLMS.  If not, see <http://www.gnu.org/licenses/>.
// *
// */
package org.openmuc.jdlms.internal.impl;

//

//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;

//
//import org.junit.Before;
//import org.junit.Test;
//import org.openmuc.jdlms.AccessResultCode;
//import org.openmuc.jdlms.Apdu;
//import org.openmuc.jdlms.ClientConnection;
//import org.openmuc.jdlms.GetRequestParameter;
//import org.openmuc.jdlms.GetResult;
//import org.openmuc.jdlms.LNConnection;
//import org.openmuc.jdlms.ObisCode;
//import org.openmuc.jdlms.RequestParameterFactory;
//import org.openmuc.jdlms.SetRequestParameter;
//import org.openmuc.jdlms.ClientSap.ConfirmedMode;
//import org.openmuc.jdlms.internal.cosem.context.MechanismName;
//import org.openmuc.jdlms.util.LowerLayerMock;
//import org.openmuc.jdlms.util.StringBytesHelper;
//
public class LNConnectionTest {
	//
	// private final LowerLayerMock<Void> lowerLayer = new LowerLayerMock<Void>();
	// private ClientConnection connection;
	//
	// @Before
	// public void setUp() throws Exception {
	// if (connection != null) {
	// connection.disconnect(false);
	// }
	// lowerLayer.reset();
	// lowerLayer
	// .onSentReceive(StringBytesHelper
	// .getBytesFromString("6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
	//
	// connection = new LNConnection(ConfirmedMode.CONFIRMED, MechanismName.LOWEST, lowerLayer, 0, null, null,
	// new ConnectModule());
	// // connection.connect(0);
	// lowerLayer.clearSentMessages();
	// }
	//
	// @Test
	// public void getSingleElementTest() throws Exception {
	// lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString("C401000009060000010000FF"));
	// GetRequestParameter param = new RequestParameterFactory(8, new ObisCode(0, 0, 1, 0, 0, 255), 1)
	// .createGetRequestParameter();
	//
	// List<GetResult> result = connection.get(0, false, param);
	//
	// assertEquals(1, lowerLayer.numberOfSentMessages());
	// assertArrayEquals(StringBytesHelper.getBytesFromString("C0014000080000010000FF0100"),
	// lowerLayer.nextSentMessage());
	// assertEquals(1, result.size());
	// assertEquals(AccessResultCode.SUCCESS, result.get(0).resultCode());
	//
	// byte[] resByteArray = result.get(0).resultData().value();
	// assertArrayEquals(StringBytesHelper.getBytesFromString("0000010000FF"), resByteArray);
	// }
	//
	// public static void main(String[] args) {
	// CosemDate date = new CosemDate(2002, 12, 4);
	// CosemTime time = new CosemTime(10, 6, 11);
	// int deviation = 120;
	// CosemDateTime cal = new CosemDateTime(date, time, deviation);
	//
	// for (byte b : cal.ocletString()) {
	// System.out.printf("%02x", b);
	// }
	// System.out.println();
	// }
	//
	// @Test
	// public void setSingleElementTest() throws Exception {
	// lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString("C5010000"));
	//
	// CosemDate date = new CosemDate(2002, 12, 4);
	// CosemTime time = new CosemTime(10, 6, 11);
	// int deviation = 120;
	// CosemDateTime cal = new CosemDateTime(date, time, deviation);
	// SetRequestParameter param = new RequestParameterFactory(8, new ObisCode(0, 0, 1, 0, 0, 255), 2)
	// .createSetRequestParameter(Apdu.newDateTimeData(cal));
	//
	// List<AccessResultCode> result = connection.set(0, false, param);
	//
	// assertEquals(1, lowerLayer.numberOfSentMessages());
	//
	// byte[] bytes = lowerLayer.nextSentMessage();
	// for (byte b : bytes) {
	// System.out.printf("%02x", b);
	// }
	// System.out.println();
	// assertArrayEquals(
	// StringBytesHelper.getBytesFromString("c1014000080000010000ff0200090c07d20c04ff0a060bff007800"), bytes);
	//
	// assertEquals(1, result.size());
	// assertEquals(AccessResultCode.SUCCESS, result.get(0));
	// }
}
