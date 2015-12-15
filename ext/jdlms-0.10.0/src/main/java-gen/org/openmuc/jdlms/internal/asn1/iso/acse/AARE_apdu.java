/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.jdlms.internal.asn1.iso.acse;

import java.io.IOException;
import java.io.InputStream;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerIdentifier;
import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.types.BerBitString;
import org.openmuc.jasn1.ber.types.BerInteger;
import org.openmuc.jasn1.ber.types.BerObjectIdentifier;
import org.openmuc.jasn1.ber.types.string.BerGraphicString;

public final class AARE_apdu {

	public final static BerIdentifier identifier = new BerIdentifier(BerIdentifier.APPLICATION_CLASS,
			BerIdentifier.CONSTRUCTED, 1);
	protected BerIdentifier id;

	public byte[] code = null;
	public BerBitString protocol_version = null;

	public BerObjectIdentifier application_context_name = null;

	public BerInteger result = null;

	public Associate_source_diagnostic result_source_diagnostic = null;

	public AP_title responding_AP_title = null;

	public AE_qualifier responding_AE_qualifier = null;

	public BerInteger responding_AP_invocation_identifier = null;

	public BerInteger responding_AE_invocation_identifier = null;

	public BerBitString responder_acse_requirements = null;

	public BerObjectIdentifier mechanism_name = null;

	public Authentication_value responding_authentication_value = null;

	public Application_context_name_list application_context_name_list = null;

	public BerGraphicString implementation_information = null;

	public Association_information user_information = null;

	public AARE_apdu() {
		id = identifier;
	}

	public AARE_apdu(byte[] code) {
		id = identifier;
		this.code = code;
	}

	public AARE_apdu(BerBitString protocol_version, BerObjectIdentifier application_context_name, BerInteger result,
			Associate_source_diagnostic result_source_diagnostic, AP_title responding_AP_title,
			AE_qualifier responding_AE_qualifier, BerInteger responding_AP_invocation_identifier,
			BerInteger responding_AE_invocation_identifier, BerBitString responder_acse_requirements,
			BerObjectIdentifier mechanism_name, Authentication_value responding_authentication_value,
			Application_context_name_list application_context_name_list, BerGraphicString implementation_information,
			Association_information user_information) {
		id = identifier;
		this.protocol_version = protocol_version;
		this.application_context_name = application_context_name;
		this.result = result;
		this.result_source_diagnostic = result_source_diagnostic;
		this.responding_AP_title = responding_AP_title;
		this.responding_AE_qualifier = responding_AE_qualifier;
		this.responding_AP_invocation_identifier = responding_AP_invocation_identifier;
		this.responding_AE_invocation_identifier = responding_AE_invocation_identifier;
		this.responder_acse_requirements = responder_acse_requirements;
		this.mechanism_name = mechanism_name;
		this.responding_authentication_value = responding_authentication_value;
		this.application_context_name_list = application_context_name_list;
		this.implementation_information = implementation_information;
		this.user_information = user_information;
	}

	public int encode(BerByteArrayOutputStream os, boolean explicit) throws IOException {

		int codeLength;

		if (code != null) {
			codeLength = code.length;
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
		}
		else {
			codeLength = 0;
			int sublength;

			if (user_information != null) {
				codeLength += user_information.encode(os, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 30))
						.encode(os);
			}

			if (implementation_information != null) {
				codeLength += implementation_information.encode(os, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 29)).encode(os);
			}

			if (application_context_name_list != null) {
				codeLength += application_context_name_list.encode(os, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 11))
						.encode(os);
			}

			if (responding_authentication_value != null) {
				sublength = responding_authentication_value.encode(os, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(os, sublength);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 10))
						.encode(os);
			}

			if (mechanism_name != null) {
				codeLength += mechanism_name.encode(os, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 9)).encode(os);
			}

			if (responder_acse_requirements != null) {
				codeLength += responder_acse_requirements.encode(os, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 8)).encode(os);
			}

			if (responding_AE_invocation_identifier != null) {
				sublength = responding_AE_invocation_identifier.encode(os, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(os, sublength);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 7)).encode(os);
			}

			if (responding_AP_invocation_identifier != null) {
				sublength = responding_AP_invocation_identifier.encode(os, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(os, sublength);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 6)).encode(os);
			}

			if (responding_AE_qualifier != null) {
				sublength = responding_AE_qualifier.encode(os, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(os, sublength);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 5)).encode(os);
			}

			if (responding_AP_title != null) {
				sublength = responding_AP_title.encode(os, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(os, sublength);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 4)).encode(os);
			}

			sublength = result_source_diagnostic.encode(os, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 3)).encode(os);

			sublength = result.encode(os, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)).encode(os);

			sublength = application_context_name.encode(os, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 1)).encode(os);

			if (protocol_version != null) {
				codeLength += protocol_version.encode(os, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)).encode(os);
			}

			codeLength += BerLength.encodeLength(os, codeLength);
		}

		if (explicit) {
			codeLength += id.encode(os);
		}

		return codeLength;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("sequence: {");
		boolean firstSelectedElement = true;
		if (protocol_version != null) {
			sb.append("protocol_version: ").append(protocol_version);
			firstSelectedElement = false;
		}

		if (!firstSelectedElement) {
			sb.append(", ");
		}
		sb.append("application_context_name: ").append(application_context_name);

		sb.append(", ");
		sb.append("result: ").append(result);

		sb.append(", ");
		sb.append("result_source_diagnostic: ").append(result_source_diagnostic);

		if (responding_AP_title != null) {
			sb.append(", ");
			sb.append("responding_AP_title: ").append(responding_AP_title);
		}

		if (responding_AE_qualifier != null) {
			sb.append(", ");
			sb.append("responding_AE_qualifier: ").append(responding_AE_qualifier);
		}

		if (responding_AP_invocation_identifier != null) {
			sb.append(", ");
			sb.append("responding_AP_invocation_identifier: ").append(responding_AP_invocation_identifier);
		}

		if (responding_AE_invocation_identifier != null) {
			sb.append(", ");
			sb.append("responding_AE_invocation_identifier: ").append(responding_AE_invocation_identifier);
		}

		if (responder_acse_requirements != null) {
			sb.append(", ");
			sb.append("responder_acse_requirements: ").append(responder_acse_requirements);
		}

		if (mechanism_name != null) {
			sb.append(", ");
			sb.append("mechanism_name: ").append(mechanism_name);
		}

		if (responding_authentication_value != null) {
			sb.append(", ");
			sb.append("responding_authentication_value: ").append(responding_authentication_value);
		}

		if (application_context_name_list != null) {
			sb.append(", ");
			sb.append("application_context_name_list: ").append(application_context_name_list);
		}

		if (implementation_information != null) {
			sb.append(", ");
			sb.append("implementation_information: ").append(implementation_information);
		}

		if (user_information != null) {
			sb.append(", ");
			sb.append("user_information: ").append(user_information);
		}

		sb.append("}");
		return sb.toString();
	}

	public int decode(InputStream is, boolean explicit) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerIdentifier berIdentifier = new BerIdentifier();

		if (explicit) {
			codeLength += id.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		while (subCodeLength < length.val) {
			subCodeLength += berIdentifier.decode(is);
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)) {
				protocol_version = new BerBitString();
				subCodeLength += protocol_version.decode(is, false);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 1)) {
				subCodeLength += new BerLength().decode(is);
				application_context_name = new BerObjectIdentifier();
				subCodeLength += application_context_name.decode(is, true);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)) {
				subCodeLength += new BerLength().decode(is);
				result = new BerInteger();
				subCodeLength += result.decode(is, true);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 3)) {
				subCodeLength += new BerLength().decode(is);
				result_source_diagnostic = new Associate_source_diagnostic();
				subCodeLength += result_source_diagnostic.decode(is, null);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 4)) {
				subCodeLength += new BerLength().decode(is);
				responding_AP_title = new AP_title();
				subCodeLength += responding_AP_title.decode(is, null);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 5)) {
				subCodeLength += new BerLength().decode(is);
				responding_AE_qualifier = new AE_qualifier();
				subCodeLength += responding_AE_qualifier.decode(is, null);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 6)) {
				subCodeLength += new BerLength().decode(is);
				responding_AP_invocation_identifier = new BerInteger();
				subCodeLength += responding_AP_invocation_identifier.decode(is, true);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 7)) {
				subCodeLength += new BerLength().decode(is);
				responding_AE_invocation_identifier = new BerInteger();
				subCodeLength += responding_AE_invocation_identifier.decode(is, true);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 8)) {
				responder_acse_requirements = new BerBitString();
				subCodeLength += responder_acse_requirements.decode(is, false);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 9)) {
				mechanism_name = new BerObjectIdentifier();
				subCodeLength += mechanism_name.decode(is, false);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 10)) {
				subCodeLength += new BerLength().decode(is);
				responding_authentication_value = new Authentication_value();
				subCodeLength += responding_authentication_value.decode(is, null);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 11)) {
				application_context_name_list = new Application_context_name_list();
				subCodeLength += application_context_name_list.decode(is, false);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 29)) {
				implementation_information = new BerGraphicString();
				subCodeLength += implementation_information.decode(is, false);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 30)) {
				user_information = new Association_information();
				subCodeLength += user_information.decode(is, false);
			}
		}
		if (subCodeLength != length.val) {
			throw new IOException("Length of set does not match length tag, length tag: " + length.val
					+ ", actual set length: " + subCodeLength);

		}
		codeLength += subCodeLength;

		return codeLength;
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}
}