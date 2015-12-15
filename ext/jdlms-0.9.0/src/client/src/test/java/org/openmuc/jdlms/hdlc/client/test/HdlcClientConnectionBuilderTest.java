package org.openmuc.jdlms.hdlc.client.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmuc.jdlms.client.ClientConnectionSettings.Authentication;
import org.openmuc.jdlms.client.ClientConnectionSettings.ConfirmedMode;
import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.hdlc.HdlcAddress;
import org.openmuc.jdlms.client.hdlc.HdlcClientConnectionSettings;
import org.openmuc.jdlms.client.hdlc.impl.HdlcClientLayer;
import org.openmuc.jdlms.client.hdlc.impl.HdlcClientLayerFactory;

public class HdlcClientConnectionBuilderTest {

	HdlcClientConnectionSettings settings = new HdlcClientConnectionSettings(null, null, null, null);

	@Before
	public void setUp() throws Exception {
		settings.setAuthentication(Authentication.LOWEST).setConfirmedMode(ConfirmedMode.CONFIRMED)
				.setReferencingMethod(ReferencingMethod.LN);
		settings.setClientAddress(null).setServerAddress(null).setPortName(null);
	}

	@Test
	public void buildHdlcConnectionTest() throws IOException {
		settings.setClientAddress(new HdlcAddress(0x10));
		settings.setServerAddress(new HdlcAddress(0x01));
		settings.setPortName("/dev/ttyS0");
		HdlcClientLayerFactory builder = new HdlcClientLayerFactory();

		Assert.assertEquals(true, settings.isFullyParametrized());
		HdlcClientLayer connection = (HdlcClientLayer) builder.build(settings);

		Assert.assertNotNull(connection);
		Assert.assertNotNull(connection.getLowerLayer());
		Assert.assertEquals(new HdlcAddress(0x10), connection.getClientAddress());
		Assert.assertEquals(new HdlcAddress(0x01), connection.getServerAddress());
	}

	@Test
	public void buildDuplicateHdlcConnectionTest() throws IOException {
		settings.setClientAddress(new HdlcAddress(0x10));
		settings.setServerAddress(new HdlcAddress(0x01));
		settings.setPortName("/dev/ttyS0");
		HdlcClientLayerFactory builder = new HdlcClientLayerFactory();

		HdlcClientConnectionSettings settings2 = new HdlcClientConnectionSettings("/dev/ttyS0", new HdlcAddress(0x10),
				new HdlcAddress(0x01), ReferencingMethod.LN);

		Assert.assertTrue(settings.isFullyParametrized());
		Assert.assertTrue(settings2.isFullyParametrized());
		Assert.assertTrue(settings.equals(settings2));
		HdlcClientLayer connection1 = (HdlcClientLayer) builder.build(settings);
		HdlcClientLayer connection2 = (HdlcClientLayer) builder.build(settings2);

		Assert.assertTrue(connection1.equals(connection2));
		Assert.assertTrue(connection1.getLowerLayer().equals(connection2.getLowerLayer()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void uninitializedBuildTest() throws IOException {
		Assert.assertEquals(false, settings.isFullyParametrized());
		new HdlcClientLayerFactory().build(settings);
	}

	@Test
	public void duplicateAddressDifferentPortTest() throws IOException {
		settings.setClientAddress(new HdlcAddress(0x10));
		settings.setServerAddress(new HdlcAddress(0x01));
		settings.setPortName("/dev/ttyS0");

		HdlcClientConnectionSettings settings2 = new HdlcClientConnectionSettings("/dev/ttyS1", new HdlcAddress(0x10),
				new HdlcAddress(0x01), ReferencingMethod.LN);

		HdlcClientLayerFactory builder = new HdlcClientLayerFactory();

		Assert.assertEquals(true, settings.isFullyParametrized());
		Assert.assertEquals(true, settings2.isFullyParametrized());
		Assert.assertFalse(settings.equals(settings2));
		HdlcClientLayer connection1 = (HdlcClientLayer) builder.build(settings);
		HdlcClientLayer connection2 = (HdlcClientLayer) builder.build(settings2);

		Assert.assertFalse(connection1.equals(connection2));
		Assert.assertFalse(connection1.getLowerLayer().equals(connection2.getLowerLayer()));
	}
}
