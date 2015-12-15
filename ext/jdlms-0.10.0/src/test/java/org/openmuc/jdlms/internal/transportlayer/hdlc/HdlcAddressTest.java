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
package org.openmuc.jdlms.internal.transportlayer.hdlc;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class HdlcAddressTest {

	@Test
	public void testSymmetry1() throws Exception {
		HdlcAddress address = new HdlcAddress(10);
		InputStream inputStream = new ByteArrayInputStream(address.encode());

		HdlcAddress decodedAddress = HdlcAddress.decode(inputStream);

		assertEquals(address.logicalDeviceAddress(), decodedAddress.logicalDeviceAddress());
		assertEquals(address, decodedAddress);
	}

	@Test
	public void testSymmetry2() throws Exception {
		HdlcAddress address = new HdlcAddress(10, 12);
		InputStream inputStream = new ByteArrayInputStream(address.encode());

		HdlcAddress decodedAddress = HdlcAddress.decode(inputStream);

		assertEquals(address.logicalDeviceAddress(), decodedAddress.logicalDeviceAddress());
		assertEquals(address, decodedAddress);
	}

	@Test
	public void testDecode() throws Exception {

		byte logicalDeviceAddress = 30;
		byte[] bytes = { (byte) (logicalDeviceAddress << 1 | 1) };

		InputStream inputStream = new ByteArrayInputStream(bytes);

		HdlcAddress decodedAddress = HdlcAddress.decode(inputStream);

		assertEquals(logicalDeviceAddress, decodedAddress.logicalDeviceAddress());
	}

}
