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
package org.openmuc.jdlms.util;

public class StringBytesHelper {
	public static byte[] getBytesFromString(String byteString) {
		byteString = byteString.replaceAll("\\s", "");

		byte[] result = new byte[byteString.length() / 2];
		int index = 0;

		while (index < result.length) {
			result[index] = (byte) Short.parseShort(byteString.substring(index * 2, index * 2 + 2), 16);
			index++;
		}

		return result;
	}

	public static void printBytes(byte[] bytes) {
		StringBuilder sb = new StringBuilder();

		for (byte b : bytes) {
			String byteString = Integer.toHexString(b & 0x000000FF);
			byteString = byteString.length() == 1 ? "0" + byteString : byteString;
			sb.append(byteString);
		}

		System.out.println(sb.toString());
	}

	private StringBytesHelper() {
	}
}
