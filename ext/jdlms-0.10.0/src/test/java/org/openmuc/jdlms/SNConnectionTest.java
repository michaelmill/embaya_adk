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
// import java.io.IOException;
// import java.util.Calendar;
// import java.util.List;
//
// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import org.openmuc.jdlms.internal.cosem.context.MechanismName;
// import org.openmuc.jdlms.internal.impl.ClientConnection;
// import org.openmuc.jdlms.internal.impl.ConnectModule;
// import org.openmuc.jdlms.util.LowerLayerMock;
// import org.openmuc.jdlms.util.StringBytesHelper;
//
public class SNConnectionTest {
	//
	// private final LowerLayerMock<Object> lowerLayer = new LowerLayerMock<Object>();
	// private ClientConnection connection;
	//
	// @Before
	// public void setUp() throws Exception {
	// if (connection != null) {
	// connection.disconnect(false);
	// }
	// lowerLayer.reset();
	// lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString(
	// "6129A109060760857405080102A203020100A305A103020100BE10040E0800065F1F04001802201900FA00"));
	// connection = new SNConnection(true, MechanismName.LOWEST, lowerLayer, new ConnectModule());
	// connection.connect(0);
	// lowerLayer.clearSentMessages();
	// }
	//
	// @Test
	// public void connectTest() throws IOException {
	// lowerLayer.reset();
	// lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString(
	// "6129A109060760857405080102A203020100A305A103020100BE10040E0800065F1F04001802201900FA00"));
	// SNConnection newConnection = new SNConnection(true, MechanismName.LOWEST, lowerLayer, new ConnectModule());
	//
	// newConnection.connect(0);
	//
	// Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
	// Assert.assertArrayEquals(
	// stringHelper.getBytesFromString("601da109060760857405080102be10040e01000000065f1f04001c0320ffff"),
	// lowerLayer.nextSentMessage());
	// }
	//
	// @Test
	// public void getSingleElementTest() throws IOException {
	// lowerLayer
	// .onSentReceive(StringBytesHelper.getBytesFromString("0C010001010204102A40120008110009060000010000FF"));
	// lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString("0C010009060000010000FF"));
	// GetRequest param = new GetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 1);
	//
	// List<GetResult> result = connection.get(0, false, param);
	//
	// Assert.assertEquals(2, lowerLayer.numberOfSentMessages());
	// Assert.assertArrayEquals(StringBytesHelper.getBytesFromString("050104fa0802020212000809060000010000ff"),
	// lowerLayer.nextSentMessage());
	// Assert.assertArrayEquals(StringBytesHelper.getBytesFromString("0501022a40"), lowerLayer.nextSentMessage());
	// Assert.assertEquals(1, result.size());
	// Assert.assertEquals(AccessResultCode.SUCCESS, result.get(0).getResultCode());
	// Assert.assertArrayEquals(StringBytesHelper.getBytesFromString("0000010000FF"),
	// result.get(0).getResultData().getByteArray());
	// }
	//
	// @Test
	// public void setSingleElementTest() throws IOException {
	// lowerLayer
	// .onSentReceive(StringBytesHelper.getBytesFromString("0C010001010204102A40120008110009060000010000FF"));
	// lowerLayer.onSentReceive(StringBytesHelper.getBytesFromString("0D0100"));
	//
	// Calendar cal = Calendar.getInstance();
	// cal.set(2002, Calendar.DECEMBER, 4, 10, 6, 11);
	// cal.set(Calendar.ZONE_OFFSET, 120 * 60000);
	// cal.set(Calendar.DST_OFFSET, 0);
	//
	// SetRequest param = new SetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 2);
	// param.data().setDateTime(cal, false);
	//
	// List<AccessResultCode> result = connection.set(0, false, param);
	//
	// Assert.assertEquals(2, lowerLayer.numberOfSentMessages());
	// Assert.assertArrayEquals(StringBytesHelper.getBytesFromString("050104fa0802020212000809060000010000ff"),
	// lowerLayer.nextSentMessage());
	// Assert.assertArrayEquals(StringBytesHelper.getBytesFromString("0601022a4801090c07d20c04030a060bff007800"),
	// lowerLayer.nextSentMessage());
	// Assert.assertEquals(1, result.size());
	// Assert.assertEquals(AccessResultCode.SUCCESS, result.get(0));
	// }
}
