package org.openmuc.jdlms;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jdlms.internal.security.HlsProcessorGmac;
import org.openmuc.jdlms.internal.security.HlsSecretProcessor;

public class GmacTest {

	@Test
	public void doGmacTestFromStandardExample() {

		byte[] challenge = HexConverter.fromShortHexString("503677524A323146");
		byte[] encryptionKey = HexConverter.fromShortHexString("000102030405060708090A0B0C0D0E0F");
		byte[] authenticationKey = HexConverter.fromShortHexString("D0D1D2D3D4D5D6D7D8D9DADBDCDDDEDF");
		byte[] systemTitle = HexConverter.fromShortHexString("4d4d4d0000000001");
		int frameCounter = 1;

		String fStoCFromStandard = "10000000011A52FE7DD3E72748973C1E28";

		HlsSecretProcessor hlsSecretProcessor = new HlsProcessorGmac();
		byte[] resultStoC = null;
		try {
			resultStoC = hlsSecretProcessor.process(challenge, authenticationKey, encryptionKey, systemTitle,
					frameCounter);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String resultStoCString = HexConverter.toShortHexString(resultStoC).toUpperCase();

		System.out.println("f(StoC) from Standard: " + fStoCFromStandard);
		System.out.println("f(StoC) calculated   : " + resultStoCString);

		Assert.assertEquals(fStoCFromStandard, resultStoCString);
	}

}
