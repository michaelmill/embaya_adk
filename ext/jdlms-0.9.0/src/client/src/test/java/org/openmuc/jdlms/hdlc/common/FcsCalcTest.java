package org.openmuc.jdlms.hdlc.common;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jdlms.client.hdlc.common.FcsCalc;

public class FcsCalcTest {

	private final FcsCalc calc = new FcsCalc();

	private byte[] readByteString(String byteString) {
		byte[] result = new byte[byteString.length() / 2];
		int index = 0;

		while (index < result.length) {
			result[index] = (byte) Short.parseShort(byteString.substring(index * 2, index * 2 + 2), 16);
			index++;
		}

		return result;
	}

	private void testBytes(String bytes, byte[] result) {
		calc.reset();
		calc.update(readByteString(bytes));
		Assert.assertArrayEquals("FCS creation failed, should be " + getByteString(result) + ", was "
				+ getByteString(calc.getFcsBytes()), result, calc.getFcsBytes());
		calc.update(calc.getFcsBytes());
		Assert.assertEquals(true, calc.checkData());
	}

	private String getByteString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();

		for (byte b : bytes) {
			String byteString = Integer.toHexString(b & 0x000000FF);
			byteString = byteString.length() == 1 ? "0" + byteString : byteString;
			sb.append(byteString);
		}

		return sb.toString();
	}

	@Test
	public void testFcsCalc() {
		testBytes("A00A000200232193", readByteString("1871"));
		testBytes("A023210002002373F6C5818014050200800602008007040000000108" + "0400000001", readByteString("CE6A"));
		testBytes("A02E0002002321107ECBE6E600601DA109060760857405080101BE10" + "040E01000000065F1F040000301DFFFF",
				readByteString("D4C5"));
		testBytes("A03A2100020023309941E6E7006129A109060760857405080101A203"
				+ "020100A305A103020100BE10040E0800065F1F040000301D19000007", readByteString("0C52"));
		testBytes("A0199575546835E6E600C0018100080000010000FF0100", readByteString("0DFD"));
		testBytes("A018759574E9E8E6E700C401810009060000010000FF", readByteString("FD49"));
		testBytes("A0199575767837E6E600C0018100080000010000FF0200", readByteString("65D7"));
		testBytes("A01E7595966F67E6E700C4018100090C07D20C04030A060BFF007800", readByteString("F330"));
		testBytes("A027957598B8DBE6E600C1018100080000010000FF0200090C07D20C" + "04030A060BFF007800",
				readByteString("62FB"));
		testBytes("A0107595B85101E6E700C5018100", readByteString("36CF"));
		testBytes("A0199575BA183BE6E600C0018100080000010000FF0300", readByteString("BDCE"));
		testBytes("A0137595DA8864E6E700C4018100100078", readByteString("563A"));
		testBytes("A01C9575DC7F53E6E600C1018100080000010000FF0300100078", readByteString("0117"));
		testBytes("A0107595FC7105E6E700C5018100", readByteString("36CF"));
		testBytes("A0199575FE383FE6E600C0018100080000010000FF0400", readByteString("B583"));
		testBytes("A01275951E1BF8E6E700C40181001100", readByteString("AC5B"));
		testBytes("A0199575104831E6E600C0018100080000010000FF0500", readByteString("6D9A"));
		testBytes("A01E75953053A7E6E700C4018100090CFF4F19F3007000000000000F", readByteString("090A"));
		testBytes("A0199575325833E6E600C0018100080000010000FF0600", readByteString("05B0"));
		testBytes("A01E75955247E7E6E700C4018100090CFF4F19F3007000000000000F", readByteString("090A"));
		testBytes("A0199575546835E6E600C0018100080000010000FF0700", readByteString("DDA9"));
		testBytes("A0127595744734E6E700C40181000F00", readByteString("2D54"));
		testBytes("A0199575767837E6E600C0018100080000010000FF0800", readByteString("152A"));
		testBytes("A0127595965BF0E6E700C40181000300", readByteString("8DFD"));
		testBytes("A0199575980839E6E600C0018100080000010000FF0900", readByteString("CD33"));
		testBytes("A0127595B82738E6E700C40181001601", readByteString("2D07"));
	}
}
