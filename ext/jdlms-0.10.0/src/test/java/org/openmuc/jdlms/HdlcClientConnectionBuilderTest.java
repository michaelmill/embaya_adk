///**Copyright 2012-15 Fraunhofer ISE**This file is part of jDLMS.*For more information visit http://www.openmuc.org
// **jDLMS is free software:you can redistribute it and/or modify*it under the terms of the GNU General Public License as published by*the Free Software Foundation,either version 3 of the License,or*(at your option)any later version.**jDLMS is distributed in the hope that it will be useful,*but WITHOUT ANY WARRANTY;without even the implied warranty of*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the*GNU General Public License for more details.**You should have received a copy of the GNU General Public License*along with jDLMS.If not,see<http://www.gnu.org/licenses/>.
// **/
package org.openmuc.jdlms;

//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.powermock.api.mockito.PowerMockito.mock;
//import static org.powermock.api.mockito.PowerMockito.when;
//import static org.powermock.reflect.Whitebox.setInternalState;
//
//import org.junit.Test;
//import org.openmuc.jdlms.ClientSap.AuthenticationMechanism;
//import org.openmuc.jdlms.ClientSap.ConfirmedMode;
//import org.openmuc.jdlms.ClientSap.ReferencingMethod;
//import org.openmuc.jdlms.internal.hdlc.impl.HdlcClientLayer;
//import org.powermock.reflect.Whitebox;
//
public class HdlcClientConnectionBuilderTest {
	//
	// private HdlcClientSap constructHdlcClientSap(HdlcAddress clientAddress, HdlcAddress serverAddress, String
	// portName)
	// throws Exception {
	// HdlcClientSap clientSap = mock(HdlcClientSap.class);
	//
	// when(clientSap.newLowerLayer()).thenCallRealMethod();
	// when(clientSap.portName()).thenCallRealMethod();
	// when(clientSap.baudrate()).thenCallRealMethod();
	// when(clientSap.doesUseHandshake()).thenCallRealMethod();
	// when(clientSap.confirmedMode()).thenCallRealMethod();
	// when(clientSap.serverAddress()).thenCallRealMethod();
	// when(clientSap.clientAddress()).thenCallRealMethod();
	// when(clientSap.isFullyParametrized()).thenCallRealMethod();
	//
	// setInternalState(clientSap, "portName", portName);
	// setInternalState(clientSap, "clientAddress", clientAddress);
	// setInternalState(clientSap, "serverAddress", serverAddress);
	// setInternalState(clientSap, "baudrate", 0);
	// setInternalState(clientSap, "referencingMethod", ReferencingMethod.LN);
	// setInternalState(clientSap, "authentication", AuthenticationMechanism.NONE);
	// setInternalState(clientSap, "confirmedMode", ConfirmedMode.CONFIRMED);
	// setInternalState(clientSap, "useHandshake", false);
	//
	// return clientSap;
	// }
	//
	// @Test
	// public void buildHdlcConnectionTest() throws Exception {
	// ClientSap sap = constructHdlcClientSap(new HdlcAddress(0x10), new HdlcAddress(0x01), "/dev/ttyS0");
	//
	// // HdlcClientLayerFactory builder = new HdlcClientLayerFactory();
	//
	// assertTrue(sap.isFullyParametrized());
	// HdlcClientLayer connection = Whitebox.invokeMethod(sap, "newLowerLayer");
	//
	// assertNotNull(connection);
	// assertNotNull(connection.lowerLayer());
	// assertEquals(new HdlcAddress(0x10), connection.clientAddress());
	// assertEquals(new HdlcAddress(0x01), connection.serverAddress());
	//
	// // Assert.fail();
	// }
	//
	// @Test
	// public void buildDuplicateHdlcConnectionTest() throws Exception {
	// ClientSap sap1 = constructHdlcClientSap(new HdlcAddress(0x10), new HdlcAddress(0x01), "/dev/ttyS0");
	//
	// ClientSap sap2 = constructHdlcClientSap(new HdlcAddress(0x10), new HdlcAddress(0x01), "/dev/ttyS0");
	//
	// assertTrue(sap1.isFullyParametrized());
	// assertTrue(sap2.isFullyParametrized());
	//
	// HdlcClientLayer connection1 = Whitebox.invokeMethod(sap1, "newLowerLayer");
	// HdlcClientLayer connection2 = Whitebox.invokeMethod(sap2, "newLowerLayer");
	//
	// // assertEquals(connection2, connection1);
	// assertEquals(connection2.lowerLayer(), connection1.lowerLayer());
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void uninitializedBuildTest() throws Exception {
	// ClientSap sap = constructHdlcClientSap(null, null, null);
	//
	// assertTrue(!sap.isFullyParametrized());
	// Whitebox.invokeMethod(sap, "newLowerLayer");
	// }
	//
	// @Test
	// public void duplicateAddressDifferentPortTest() throws Exception {
	// ClientSap sap1 = constructHdlcClientSap(new HdlcAddress(0x10), new HdlcAddress(0x01), "/dev/ttyS0");
	// ClientSap sap2 = constructHdlcClientSap(new HdlcAddress(0x10), new HdlcAddress(0x01), "/dev/ttyS1");
	//
	// assertTrue(sap1.isFullyParametrized());
	// assertTrue(sap2.isFullyParametrized());
	//
	// assertFalse(sap1.equals(sap2));
	//
	// HdlcClientLayer connection1 = Whitebox.invokeMethod(sap1, "newLowerLayer");
	// HdlcClientLayer connection2 = Whitebox.invokeMethod(sap2, "newLowerLayer");
	//
	// // assertFalse(connection1.equals(connection2));
	// assertFalse(connection1.lowerLayer().equals(connection2.lowerLayer()));
	// }
}
