/*
 * Copyright 2012-15 Fraunhofer ISE
 *
 * This file is part of jDLMS.
 * For more information visit http://www.openmuc.org
 *
 * jDLMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jDLMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jDLMS.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jdlms;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.internal.util.reflection.Whitebox;
import org.openmuc.jdlms.internal.asn1.cosem.Invoke_Id_And_Priority;
import org.powermock.api.mockito.PowerMockito;

public class ClientConnectionTest {
	@Test
	public void testInvoke_Id_And_Priority1() throws Exception {
		ClientConnection connection = PowerMockito.mock(ClientConnection.class);
		PowerMockito.when(connection.invokeIdAndPriorityFor(Matchers.anyBoolean())).thenCallRealMethod();
		PowerMockito.when(connection.confirmedModeEnabled()).thenReturn(false);
		Whitebox.setInternalState(connection, "invokeId", 1);

		Invoke_Id_And_Priority idAndPriority = connection.invokeIdAndPriorityFor(true);

		Assert.assertEquals((byte) 0x81, idAndPriority.getValue()[0]);
	}

	@Test
	public void testInvoke_Id_And_Priority2() throws Exception {
		ClientConnection connection = PowerMockito.mock(ClientConnection.class);
		PowerMockito.when(connection.invokeIdAndPriorityFor(Matchers.anyBoolean())).thenCallRealMethod();

		PowerMockito.when(connection.confirmedModeEnabled()).thenReturn(true);
		Whitebox.setInternalState(connection, "invokeId", 1);

		Invoke_Id_And_Priority idAndPriority = connection.invokeIdAndPriorityFor(true);

		Assert.assertEquals((byte) 0xC1, idAndPriority.getValue()[0]);
	}

	@Test
	public void testInvoke_Id_And_Priority3() throws Exception {
		ClientConnection connection = PowerMockito.mock(ClientConnection.class);
		PowerMockito.when(connection.invokeIdAndPriorityFor(Matchers.anyBoolean())).thenCallRealMethod();
		PowerMockito.when(connection.confirmedModeEnabled()).thenReturn(true);
		Whitebox.setInternalState(connection, "invokeId", 1);

		Invoke_Id_And_Priority idAndPriority = connection.invokeIdAndPriorityFor(false);

		Assert.assertEquals((byte) 0x41, idAndPriority.getValue()[0]);
	}

}
