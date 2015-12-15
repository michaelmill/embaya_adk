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

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the address of a remote object according to IEC 62056-61. An instance of ObisCode is immutable.
 */
public class ObisCode {

	private static final int NUM_OF_BYTES = 6;

	private static final String HEX_REGEX = "[0-9A-Fa-f]";

	private final static Pattern OBIS_PATTERN;

	static {
		String a = "((" + HEX_REGEX + ")-)?";
		String b = "((" + HEX_REGEX + "{1,2}):)?";
		String c = "(" + HEX_REGEX + "{1,2}).";
		String d = "(" + HEX_REGEX + "{1,2})";
		String e = "(\\.(" + HEX_REGEX + "{1,2}))?";
		String f = "(\\*(" + HEX_REGEX + "{1,2}))?";

		OBIS_PATTERN = Pattern.compile("^" + a + b + c + d + e + f + "$");
	}

	private byte[] bytes;

	/**
	 * Constructor
	 * 
	 * @param byteA
	 *            First byte of the address
	 * @param byteB
	 *            Second byte of the address
	 * @param byteC
	 *            Third byte of the address
	 * @param byteD
	 *            Fourth byte of the address
	 * @param byteE
	 *            Fifth byte of the address
	 * @param byteF
	 *            Sixth byte of the address
	 * @throws IllegalArgumentException
	 *             If one of the bytes is out of range [0, 255]
	 */
	public ObisCode(int byteA, int byteB, int byteC, int byteD, int byteE, int byteF) {
		this.bytes = verifyLengthAndConvertToByteArray(byteA, byteB, byteC, byteD, byteE, byteF);
	}

	/**
	 * The reference-id can be written as OBIS number (e.g. 1-b:8.29.0*2) or as a series of six decimal numbers
	 * separated by periods (1.1.1.8.0.255).
	 * 
	 * @param address
	 *            Reference-ID
	 */
	public ObisCode(String address) {
		String[] addressArray = address.split("\\.");

		if (addressArray.length == NUM_OF_BYTES) {
			int[] bytesInt = { parseInt(addressArray[0]), parseInt(addressArray[1]), parseInt(addressArray[2]),
					parseInt(addressArray[3]), parseInt(addressArray[4]), parseInt(addressArray[5]) };

			this.bytes = verifyLengthAndConvertToByteArray(bytesInt);

		}
		else {
			Matcher obisMatcher = OBIS_PATTERN.matcher(address);

			if (obisMatcher.matches()) {
				this.bytes = new byte[NUM_OF_BYTES];

				this.bytes[0] = convertToByte(obisMatcher, 2);
				this.bytes[1] = convertToByte(obisMatcher, 4);
				this.bytes[2] = convertToByte(obisMatcher, 5);
				this.bytes[3] = convertToByte(obisMatcher, 6);
				this.bytes[4] = convertToByte(obisMatcher, 8);
				this.bytes[5] = convertToByte(obisMatcher, 10);
			}
			else {
				throw new IllegalArgumentException("ObisCode is not reduced obis format.");
			}
		}

	}

	private byte convertToByte(Matcher obisMatcher, int group) {
		String byteStr = obisMatcher.group(group);

		if (byteStr == null) {
			return 0;
		}

		int b = Integer.parseInt(byteStr, 16);
		return (byte) b;
	}

	public ObisCode(byte[] bytes) {
		if (bytes.length != NUM_OF_BYTES) {
			throw new IllegalArgumentException("ObisCode has the wrong length, not equal.");
		}
		this.bytes = bytes;
	}

	public String toObisCode() {
		StringBuilder sb = new StringBuilder();

		byte b = bytes[0];
		if (b != 0) {
			sb.append(format("%x-", b));
		}

		b = bytes[1];
		if (b != 0) {
			sb.append(format("%x:", b));
		}

		sb.append(format("%x.", bytes[2]));
		sb.append(format("%x", bytes[3]));

		b = bytes[4];
		if (b != 0) {
			sb.append(format(".%x", b));
		}

		b = bytes[5];
		if (b != 0) {
			sb.append(format("*%x", b));
		}

		return sb.toString();
	}

	public String toHexCode() {
		StringBuilder sb = new StringBuilder(12);
		for (int i = 0; i < 6; ++i) {
			sb.append(format("%02x", bytes[i]));
		}
		return sb.toString();
	}

	public byte[] getBytes() {
		return bytes;
	}

	public String toDecimal() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 5; ++i) {
			sb.append((bytes[i] & 0XFF));
			sb.append('.');
		}
		sb.append((bytes[5] & 0XFF));

		return sb.toString();
	}

	@Override
	public String toString() {
		return toObisCode();
	}

	private byte[] verifyLengthAndConvertToByteArray(int... bytesInt) throws IllegalArgumentException {
		for (int b : bytesInt) {
			checkLength(b);
		}

		byte[] bytes = new byte[NUM_OF_BYTES];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) bytesInt[i];
		}

		return bytes;
	}

	private void checkLength(int number) {
		if (number < 0 || number > 255) {
			throw new IllegalArgumentException(number + " is out of range [0, 255]");
		}
	}
}
