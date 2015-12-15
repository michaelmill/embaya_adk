package org.openmuc.jdlms.hdlc.client.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.common.HdlcAddressPair;
import org.openmuc.jdlms.client.hdlc.impl.HdlcClientLayer;
import org.openmuc.jdlms.client.hdlc.states.Disconnected;
import org.openmuc.jdlms.client.test.util.LowerLayerMock;
import org.openmuc.jdlms.client.test.util.StringBytesHelper;
import org.openmuc.jdlms.client.test.util.UpperLayerMock;

public class HdlcClientLayerTest {

	private final StringBytesHelper stringHelper = new StringBytesHelper();

	private HdlcClientLayer connection;
	private final LowerLayerMock<HdlcAddressPair> lowerLayer = new LowerLayerMock<HdlcAddressPair>();
	private final UpperLayerMock upperLayer = new UpperLayerMock();

	private final HdlcAddress source = new HdlcAddress(16);
	private final HdlcAddress dest = new HdlcAddress(1, 17, 4);

	@Before
	public void setUp() throws Exception {
		connection = new HdlcClientLayer(lowerLayer, source, dest, Disconnected.instance, true);
		lowerLayer.reset();
		upperLayer.reset();
	}

	@Test
	public void connectTest() throws IOException {
		lowerLayer.onSentReceive(stringHelper
				.getBytesFromString("A023210002002373F6C58180140502008006020080070400000001080400000001CE6A"));

		connection.connect(0);

		Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
		Assert.assertArrayEquals(stringHelper.getBytesFromString("7EA00A00020023219318717E"),
				lowerLayer.nextSentMessage());
	}
}
