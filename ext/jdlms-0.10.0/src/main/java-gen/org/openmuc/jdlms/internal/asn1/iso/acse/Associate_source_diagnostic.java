/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.jdlms.internal.asn1.iso.acse;

import java.io.IOException;
import java.io.InputStream;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerIdentifier;
import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.types.BerInteger;

public final class Associate_source_diagnostic {

	public byte[] code = null;
	public BerInteger acse_service_user = null;

	public BerInteger acse_service_provider = null;

	public Associate_source_diagnostic() {
	}

	public Associate_source_diagnostic(byte[] code) {
		this.code = code;
	}

	public Associate_source_diagnostic(BerInteger acse_service_user, BerInteger acse_service_provider) {
		this.acse_service_user = acse_service_user;
		this.acse_service_provider = acse_service_provider;
	}

	public int encode(BerByteArrayOutputStream os, boolean explicit) throws IOException {
		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			return code.length;

		}
		int codeLength = 0;
		int sublength;

		if (acse_service_provider != null) {
			sublength = acse_service_provider.encode(os, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)).encode(os);
			return codeLength;

		}

		if (acse_service_user != null) {
			sublength = acse_service_user.encode(os, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 1)).encode(os);
			return codeLength;

		}

		throw new IOException("Error encoding BerChoice: No item in choice was selected.");
	}

	public int decode(InputStream is, BerIdentifier berIdentifier) throws IOException {
		int codeLength = 0;
		BerIdentifier passedIdentifier = berIdentifier;
		if (berIdentifier == null) {
			berIdentifier = new BerIdentifier();
			codeLength += berIdentifier.decode(is);
		}
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 1)) {
			codeLength += new BerLength().decode(is);
			acse_service_user = new BerInteger();
			codeLength += acse_service_user.decode(is, true);
			return codeLength;
		}

		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)) {
			codeLength += new BerLength().decode(is);
			acse_service_provider = new BerInteger();
			codeLength += acse_service_provider.decode(is, true);
			return codeLength;
		}

		if (passedIdentifier != null) {
			return 0;
		}
		throw new IOException("Error decoding BerChoice: Identifier matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	@Override
	public String toString() {
		if (acse_service_user != null) {
			return "choice: {acse_service_user: " + acse_service_user + "}";
		}

		if (acse_service_provider != null) {
			return "choice: {acse_service_provider: " + acse_service_provider + "}";
		}

		return "unknown";
	}

}
