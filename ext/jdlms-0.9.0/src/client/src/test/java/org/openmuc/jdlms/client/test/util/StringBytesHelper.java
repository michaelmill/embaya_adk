package org.openmuc.jdlms.client.test.util;

public class StringBytesHelper {
	public byte[] getBytesFromString(String byteString) {
		byteString = byteString.replaceAll("\\s", "");

		byte[] result = new byte[byteString.length() / 2];
		int index = 0;

		while (index < result.length) {
			result[index] = (byte) Short.parseShort(byteString.substring(index * 2, index * 2 + 2), 16);
			index++;
		}

		return result;
	}

	public void printBytes(byte[] bytes) {
		StringBuilder sb = new StringBuilder();

		for (byte b : bytes) {
			String byteString = Integer.toHexString(b & 0x000000FF);
			byteString = byteString.length() == 1 ? "0" + byteString : byteString;
			sb.append(byteString);
		}

		System.out.println(sb.toString());
	}
}
