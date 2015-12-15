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
package org.openmuc.jdlms.internal.transportlayer.hdlc.physical;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.reflect.Whitebox.invokeMethod;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LocalDataExchangeConnection.class)
public class LocalDataExchangeTest {
	private static final String METHOD_NAME = "baudRateFor";

	private static LocalDataExchangeConnection dataExchangeClient;

	@BeforeClass
	public static void setupTest() throws Exception {
		dataExchangeClient = PowerMockito.mock(LocalDataExchangeConnection.class);
		PowerMockito.when(dataExchangeClient, method(LocalDataExchangeConnection.class, METHOD_NAME, char.class))
				.withArguments(Matchers.anyChar()).thenCallRealMethod();
	}

	/*
	 * Testing conditions of IEC 62056-21 6.3.14 13c
	 */
	@Test
	public void testBaudRateTransformation0() throws Exception {
		char option = 0x30;// '0';
		int baudRate = invokeMethodWith(option);

		assertEquals(300, baudRate);
	}

	@Test
	public void testBaudRateTransformation1() throws Exception {
		char option = '1';

		int baudRate = invokeMethodWith(option);

		assertEquals(600, baudRate);
	}

	@Test
	public void testBaudRateTransformation2() throws Exception {
		char option = '2';

		int baudRate = invokeMethodWith(option);

		assertEquals(1200, baudRate);
	}

	@Test
	public void testBaudRateTransformation3() throws Exception {
		char option = 0x33; // '3';

		int baudRate = invokeMethodWith(option);

		assertEquals(2400, baudRate);
	}

	private int invokeMethodWith(char option) throws Exception {
		return invokeMethod(dataExchangeClient, METHOD_NAME, option);
	}
}
