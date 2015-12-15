package org.openmuc.jdlms.client.test;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openmuc.jdlms.client.ClientConnectionSettings;
import org.openmuc.jdlms.client.ClientConnectionSettings.Authentication;
import org.openmuc.jdlms.client.ClientConnectionSettings.ConfirmedMode;
import org.openmuc.jdlms.client.ClientConnectionSettings.ReferencingMethod;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.communication.ILowerLayer;
import org.openmuc.jdlms.client.impl.ClientConnectionFactory;
import org.openmuc.jdlms.client.impl.ILowerLayerFactory;
import org.openmuc.jdlms.client.test.util.LowerLayerMock;

public class ClientConnectionFactoryTest {

	public static class MockTransportLayerBuilder implements ILowerLayerFactory {
		private final LowerLayerMock<Object> lowerLayer = new LowerLayerMock<Object>();

		@Override
		public ILowerLayer<Object> build(ClientConnectionSettings<?> settings) {
			return lowerLayer;
		}

		@Override
		public boolean accepts(Class<?> clazz) {
			return MockClientConnectionSettings.class.isAssignableFrom(clazz);
		}
	}

	private static class MockClientConnectionSettings extends ClientConnectionSettings<MockClientConnectionSettings> {
		public MockClientConnectionSettings() {
			super(ReferencingMethod.LN);
		}
	}

	private final MockClientConnectionSettings settings = new MockClientConnectionSettings();

	@Before
	public void setUp() throws Exception {
		settings.setAuthentication(null);
		settings.setConfirmedMode(null);
		settings.setReferencingMethod(null);
	}

	@Test
	public void createClientConnectionTest() throws IOException {
		settings.setAuthentication(Authentication.LOWEST).setConfirmedMode(ConfirmedMode.CONFIRMED)
				.setReferencingMethod(ReferencingMethod.LN);
		ClientConnectionFactory factory = new ClientConnectionFactory();

		Assert.assertEquals(true, settings.isFullyParametrized());
		factory.createClientConnection(settings);
	}

	@Test
	public void createDuplicateConnectionTest() throws IOException {
		settings.setAuthentication(Authentication.LOWEST).setConfirmedMode(ConfirmedMode.CONFIRMED)
				.setReferencingMethod(ReferencingMethod.LN);
		ClientConnectionFactory factory = new ClientConnectionFactory();

		MockClientConnectionSettings settings2 = new MockClientConnectionSettings();
		settings2.setAuthentication(Authentication.LOWEST).setConfirmedMode(ConfirmedMode.CONFIRMED)
				.setReferencingMethod(ReferencingMethod.LN);

		Assert.assertEquals(true, settings.isFullyParametrized());
		Assert.assertEquals(true, settings2.isFullyParametrized());
		Assert.assertEquals(true, settings.equals(settings2));
		IClientConnection connection1 = factory.createClientConnection(settings);
		IClientConnection connection2 = factory.createClientConnection(settings2);

		Assert.assertEquals(true, connection1.equals(connection2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void uninitializedCreateTest() throws IOException {
		Assert.assertEquals(false, settings.isFullyParametrized());
		new ClientConnectionFactory().createClientConnection(settings);
	}
}
