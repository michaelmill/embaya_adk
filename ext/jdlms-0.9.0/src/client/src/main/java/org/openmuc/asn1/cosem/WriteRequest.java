/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.asn1.cosem;

import java.io.IOException;
import java.io.InputStream;

import org.openmuc.jasn1.axdr.AxdrByteArrayOutputStream;
import org.openmuc.jasn1.axdr.AxdrType;
import org.openmuc.jasn1.axdr.types.AxdrSequenceOf;

public class WriteRequest implements AxdrType {

	public static class SubSeqOf_variable_access_specification extends AxdrSequenceOf<Variable_Access_Specification> {

		@Override
		protected Variable_Access_Specification createListElement() {
			return new Variable_Access_Specification();
		}

		protected SubSeqOf_variable_access_specification(int length) {
			super(length);
		}

		public SubSeqOf_variable_access_specification() {
		} // Call empty base constructor

	}

	public static class SubSeqOf_list_of_data extends AxdrSequenceOf<Data> {

		@Override
		protected Data createListElement() {
			return new Data();
		}

		protected SubSeqOf_list_of_data(int length) {
			super(length);
		}

		public SubSeqOf_list_of_data() {
		} // Call empty base constructor

	}

	public byte[] code = null;
	public SubSeqOf_variable_access_specification variable_access_specification = null;

	public SubSeqOf_list_of_data list_of_data = null;

	public WriteRequest() {
	}

	public WriteRequest(byte[] code) {
		this.code = code;
	}

	public WriteRequest(SubSeqOf_variable_access_specification variable_access_specification,
			SubSeqOf_list_of_data list_of_data) {
		this.variable_access_specification = variable_access_specification;
		this.list_of_data = list_of_data;
	}

	@Override
	public int encode(AxdrByteArrayOutputStream axdrOStream) throws IOException {

		int codeLength;

		if (code != null) {
			codeLength = code.length;
			for (int i = code.length - 1; i >= 0; i--) {
				axdrOStream.write(code[i]);
			}
		}
		else {
			codeLength = 0;
			codeLength += list_of_data.encode(axdrOStream);

			codeLength += variable_access_specification.encode(axdrOStream);

		}

		return codeLength;

	}

	@Override
	public int decode(InputStream iStream) throws IOException {
		int codeLength = 0;

		variable_access_specification = new SubSeqOf_variable_access_specification();
		codeLength += variable_access_specification.decode(iStream);

		list_of_data = new SubSeqOf_list_of_data();
		codeLength += list_of_data.decode(iStream);

		return codeLength;
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		AxdrByteArrayOutputStream axdrOStream = new AxdrByteArrayOutputStream(encodingSizeGuess);
		encode(axdrOStream);
		code = axdrOStream.getArray();
	}
}
