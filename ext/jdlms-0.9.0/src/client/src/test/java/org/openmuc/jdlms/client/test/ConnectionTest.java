package org.openmuc.jdlms.client.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmuc.asn1.cosem.COSEMpdu;
import org.openmuc.asn1.cosem.Conformance;
import org.openmuc.jdlms.client.AccessResultCode;
import org.openmuc.jdlms.client.GetRequest;
import org.openmuc.jdlms.client.GetResult;
import org.openmuc.jdlms.client.HlsSecretProcessor;
import org.openmuc.jdlms.client.IClientConnection;
import org.openmuc.jdlms.client.IEventListener;
import org.openmuc.jdlms.client.MethodRequest;
import org.openmuc.jdlms.client.MethodResult;
import org.openmuc.jdlms.client.SetRequest;
import org.openmuc.jdlms.client.communication.ILowerLayer;
import org.openmuc.jdlms.client.cosem.context.ApplicationContext;
import org.openmuc.jdlms.client.cosem.context.MechanismName;
import org.openmuc.jdlms.client.impl.ConnectModule;
import org.openmuc.jdlms.client.impl.Connection;
import org.openmuc.jdlms.client.test.util.LowerLayerMock;
import org.openmuc.jdlms.client.test.util.StringBytesHelper;

public class ConnectionTest {

	/**
	 * Stup class to test abstract class Connection
	 * 
	 * @author Karsten Mueller-Bier
	 */
	private class NoConnection extends Connection {

		protected NoConnection(boolean confirmedMode, MechanismName authName, ApplicationContext appContext,
				ILowerLayer<Object> lowerLayer, ConnectModule connectModule) {
			super(confirmedMode, authName, appContext, lowerLayer, connectModule);
		}

		@Override
		public List<GetResult> get(long timeout, GetRequest... params) throws IOException {
			return null;
		}

		@Override
		public List<GetResult> get(long timeout, boolean highPriority, GetRequest... params) throws IOException {
			return null;
		}

		@Override
		public List<AccessResultCode> set(long timeout, boolean highPriority, SetRequest... params) throws IOException {
			return null;
		}

		@Override
		public List<MethodResult> action(long timeout, boolean highPriority, MethodRequest... params)
				throws IOException {
			return null;
		}

		@Override
		public void registerEventListener(IEventListener listener) {
		}

		@Override
		public void removeEventListener(IEventListener listener) {

		}

		@Override
		protected Conformance getProposedConformance() {
			return new Conformance(new byte[] { (byte) 0x00, (byte) 0xA0, (byte) 0x1F }, 24);
		}

		@Override
		protected void processPdu(COSEMpdu pdu) {
		}

		@Override
		public void connect(long timeout) throws IOException {
			connect(timeout, null, null);
		}

		@Override
		public void connect(long timeout, byte[] secret) throws IOException {
			connect(timeout, secret, null);
		}

		@Override
		public void connect(long timeout, byte[] secret, HlsSecretProcessor processor) throws IOException {
			establishConnection(timeout, secret, processor);
		}

		@Override
		public byte[] hlsAuthentication(byte[] processedChallenge, long timeout) throws IOException {
			return new byte[0];
		}

	}

	private final StringBytesHelper stringHelper = new StringBytesHelper();

	LowerLayerMock<Object> lowerLayer = new LowerLayerMock<Object>();
	IClientConnection connection;

	@Before
	public void setUp() throws Exception {
		if (connection != null) {
			connection.disconnect(false);
		}
		lowerLayer.reset();
		connection = new NoConnection(true, MechanismName.LOWEST, ApplicationContext.LOGICAL_NAME_NO_CIPHERING,
				lowerLayer, new ConnectModule());
	}

	@Test
	public void connectTest() throws IOException {
		lowerLayer
				.onSentReceive(stringHelper
						.getBytesFromString("6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
		connection.connect(0);

		Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
		Assert.assertArrayEquals(
				stringHelper.getBytesFromString("601DA109060760857405080101BE10040E01000000065F1F040000A01FFFFF"),
				lowerLayer.nextSentMessage());
	}

	@Test
	public void connectTwiceTest() throws IOException {
		lowerLayer
				.onSentReceive(stringHelper
						.getBytesFromString("6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
		connection.connect(0);
		connection.connect(0);

		Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
	}

	@Test
	public void disconnectTest() throws IOException {
		lowerLayer
				.onSentReceive(stringHelper
						.getBytesFromString("6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
		lowerLayer.onSentReceive(stringHelper.getBytesFromString("6303800100"));
		connection.connect(0);
		connection.disconnect(true);

		Assert.assertEquals(2, lowerLayer.numberOfSentMessages());
		lowerLayer.nextSentMessage();
		Assert.assertArrayEquals(stringHelper.getBytesFromString("6203800100"), lowerLayer.nextSentMessage());
		Assert.assertEquals(false, connection.isConnected());
		Assert.assertEquals(false, lowerLayer.isConnected());
	}

	@Test
	public void disconnectLowerLayerTest() throws IOException {
		lowerLayer
				.onSentReceive(stringHelper
						.getBytesFromString("6129A109060760857405080101A203020100A305A103020100BE10040E0800065F1F040000301D19000007"));
		connection.connect(0);
		connection.disconnect(false);

		Assert.assertEquals(1, lowerLayer.numberOfSentMessages());
		Assert.assertEquals(false, connection.isConnected());
		Assert.assertEquals(false, lowerLayer.isConnected());
	}

	@Test
	public void disconnectFirstTest() {
		connection.disconnect(true);

		Assert.assertEquals(0, lowerLayer.numberOfSentMessages());
		Assert.assertEquals(false, connection.isConnected());
		Assert.assertEquals(false, lowerLayer.isConnected());

	}
}
