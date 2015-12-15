package org.openmuc.jdlms.client.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmuc.jdlms.client.AccessResultCode;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.ObisCode;
import org.openmuc.jdlms.client.SetRequest;
import org.openmuc.jdlms.client.cosem.context.MechanismName;
import org.openmuc.jdlms.client.impl.ConnectModule;
import org.openmuc.jdlms.client.impl.SNConnection;
import org.openmuc.jdlms.client.test.util.LowerLayerMock;
import org.openmuc.jdlms.client.test.util.StringBytesHelper;

public class SNConnectionTest {

	private final StringBytesHelper stringHelper = new StringBytesHelper();

	private final LowerLayerMock<Object> lowerLayer = new LowerLayerMock<Object>();
	private IClientConnection connection;

	@Before
	public void setUp() throws Exception {
		if (connection != null) {
			connection.disconnect(false);
		}
		lowerLayer.reset();
		lowerLayer
				.onSentReceive(stringHelper
						.getBytesFromString("6129A109060760857405080102A203020100A305A103020100BE10040E0800065F1F04001802201900FA00"));
		connection = new SNConnection(true, MechanismName.LOWEST, lowerLayer, new ConnectModule());
		connection.connect(0);
		lowerLayer.clearSentMessages();
	}

	@Test
	public void connectTest() throws IOException {
		lowerLayer.reset();
		lowerLayer
				.onSentReceive(stringHelper
						.getBytesFromString("6129A109060760857405080102A203020100A305A103020100BE10040E0800065F1F04001802201900FA00"));
		SNConnection newConnection = new SNConnection(true, MechanismName.LOWEST, lowerLayer, new ConnectModule());

		newConnection.connect(0);

		Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
		Assert.assertArrayEquals(
				stringHelper.getBytesFromString("601da109060760857405080102be10040e01000000065f1f04001c0320ffff"),
				lowerLayer.nextSentMessage());
	}

	@Test
	public void getSingleElementTest() throws IOException {
		lowerLayer.onSentReceive(stringHelper.getBytesFromString("0C010001010204102A40120008110009060000010000FF"));
		lowerLayer.onSentReceive(stringHelper.getBytesFromString("0C010009060000010000FF"));
		GetRequest param = new GetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 1);

		List<GetResult> result = connection.get(0, false, param);

		Assert.assertEquals(2, lowerLayer.numberOfSentMessages());
		Assert.assertArrayEquals(stringHelper.getBytesFromString("050104fa0802020212000809060000010000ff"),
				lowerLayer.nextSentMessage());
		Assert.assertArrayEquals(stringHelper.getBytesFromString("0501022a40"), lowerLayer.nextSentMessage());
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(AccessResultCode.SUCCESS, result.get(0).getResultCode());
		Assert.assertArrayEquals(stringHelper.getBytesFromString("0000010000FF"), result.get(0).getResultData()
				.getByteArray());
	}

	@Test
	public void setSingleElementTest() throws IOException {
		lowerLayer.onSentReceive(stringHelper.getBytesFromString("0C010001010204102A40120008110009060000010000FF"));
		lowerLayer.onSentReceive(stringHelper.getBytesFromString("0D0100"));

		Calendar cal = Calendar.getInstance();
		cal.set(2002, Calendar.DECEMBER, 4, 10, 6, 11);
		cal.set(Calendar.ZONE_OFFSET, 120 * 60000);
		cal.set(Calendar.DST_OFFSET, 0);

		SetRequest param = new SetRequest(8, new ObisCode(0, 0, 1, 0, 0, 255), 2);
		param.data().setDateTime(cal, false);

		List<AccessResultCode> result = connection.set(0, false, param);

		Assert.assertEquals(2, lowerLayer.numberOfSentMessages());
		Assert.assertArrayEquals(stringHelper.getBytesFromString("050104fa0802020212000809060000010000ff"),
				lowerLayer.nextSentMessage());
		Assert.assertArrayEquals(stringHelper.getBytesFromString("0601022a4801090c07d20c04030a060bff007800"),
				lowerLayer.nextSentMessage());
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(AccessResultCode.SUCCESS, result.get(0));
	}
}
