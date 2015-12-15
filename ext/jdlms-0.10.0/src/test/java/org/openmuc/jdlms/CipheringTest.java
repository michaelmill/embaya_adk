package org.openmuc.jdlms;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jdlms.internal.asn1.axdr.types.AxdrBoolean;
import org.openmuc.jdlms.internal.asn1.axdr.types.AxdrOctetString;
import org.openmuc.jdlms.internal.asn1.cosem.COSEMpdu;
import org.openmuc.jdlms.internal.asn1.cosem.Conformance;
import org.openmuc.jdlms.internal.asn1.cosem.InitiateRequest;
import org.openmuc.jdlms.internal.asn1.cosem.Unsigned16;
import org.openmuc.jdlms.internal.asn1.cosem.Unsigned8;
import org.openmuc.jdlms.internal.security.CipheringGcm;

public class CipheringTest {

	private final byte[] dedicatedKey = HexConverter.fromShortHexString("00112233445566778899AABBCCDDEEFF");
	private final byte[] globalCipherKey = HexConverter.fromShortHexString("000102030405060708090A0B0C0D0E0F");
	private final byte[] authenticationKey = HexConverter.fromShortHexString("D0D1D2D3D4D5D6D7D8D9DADBDCDDDEDF");

	@Test
	public void testCipheringExampleFromStandard()
			throws IOException, IllegalStateException, InvalidCipherTextException {

		// create xDLMS plain text byte array

		byte[] initRequestXDlmsPduBytes = getInitRequestXDlmsPduBytes();

		byte[] ciphered = CipheringGcm.encrypt(initRequestXDlmsPduBytes, 0, initRequestXDlmsPduBytes.length,
				new byte[] { 0x4d, 0x4d, 0x4d, 0, 0, (byte) 0xbc, 0x61, 0x4e }, 0x1234567, globalCipherKey,
				authenticationKey, (byte) 33);

		Assert.assertArrayEquals(getCipherdFromStandard(), ciphered);

	}

	@Test
	public void testDecryptionExampleFromStandard() throws IOException {

		// String cipheredFromStandardString = "3001234567"
		// + "801302FF8A7874133D414CED25B42534D28DB0047720606B175BD52211BE68" + "41DB204D39EE6FDB8E356855";

		byte[] cipheredFromStandard = HexConverter.fromShortHexString("3001234567"
				+ "801302FF8A7874133D414CED25B42534D28DB0047720606B175BD52211BE68" + "41DB204D39EE6FDB8E356855");
		byte[] initRequestXDlmsPduBytes = getInitRequestXDlmsPduBytes();

		byte[] deciphered;

		deciphered = CipheringGcm.decrypt(cipheredFromStandard,
				new byte[] { 0x4d, 0x4d, 0x4d, 0, 0, (byte) 0xBC, 0x61, 0x4E }, globalCipherKey, authenticationKey);
		Assert.assertArrayEquals(initRequestXDlmsPduBytes, deciphered);

	}

	private byte[] getInitRequestXDlmsPduBytes() throws IOException {

		byte[] conformanceBlock = HexConverter.fromShortHexString("007E1F");
		int clientMaxReceivePduSize = 1200;

		COSEMpdu initRequestXDlmsPdu = new COSEMpdu();
		initRequestXDlmsPdu.setinitiateRequest(
				new InitiateRequest(new AxdrOctetString(dedicatedKey), new AxdrBoolean(true), null, new Unsigned8(6),
						new Conformance(conformanceBlock, 24), new Unsigned16(clientMaxReceivePduSize)));

		BerByteArrayOutputStream abaos = new BerByteArrayOutputStream(1000);
		initRequestXDlmsPdu.encode(abaos);

		byte[] initRequestXDlmsPduBytes = abaos.getArray();

		return initRequestXDlmsPduBytes;
	}

	private byte[] getCipherdFromStandard() {

		String cipheredFromStandardString = "21303001234567"
				+ "801302FF8A7874133D414CED25B42534D28DB0047720606B175BD52211BE68" + "41DB204D39EE6FDB8E356855";
		byte[] cipheredFromStandard = HexConverter.fromShortHexString(cipheredFromStandardString);
		return cipheredFromStandard;
	}

	@Test
	public void testKaifaMessages() throws IOException {

		ByteArrayInputStream bais = new ByteArrayInputStream(
				HexConverter.fromShortHexString("c301c1000f0000280000ff0101091110012345677d52391c407e3a45210f000e"));
		COSEMpdu actionRequestPdu = new COSEMpdu();
		actionRequestPdu.decode(bais);
		System.out.println("action request pdu from kaifa meter: " + actionRequestPdu);

		bais = new ByteArrayInputStream(
				HexConverter.fromShortHexString("c701c10001000911101a9190bc0a587217bcd2bcd4fddb7143"));
		COSEMpdu actionResponsePdu = new COSEMpdu();
		actionResponsePdu.decode(bais);
		System.out.println("action response pdu from kaifa meter: " + actionResponsePdu);

	}
}
