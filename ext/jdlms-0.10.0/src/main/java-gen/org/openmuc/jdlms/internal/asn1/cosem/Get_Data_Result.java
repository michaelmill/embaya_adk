/**
 * This class file was automatically generated by the AXDR compiler that is part of jDLMS (http://www.openmuc.org)
 */

package org.openmuc.jdlms.internal.asn1.cosem;

import java.io.IOException;
import java.io.InputStream;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jdlms.internal.asn1.axdr.AxdrType;
import org.openmuc.jdlms.internal.asn1.axdr.types.AxdrEnum;

public class Get_Data_Result implements AxdrType {

	public byte[] code = null;

	public static enum Choices {
		_ERR_NONE_SELECTED(-1),
		DATA(0),
		DATA_ACCESS_RESULT(1),;

		private int value;

		private Choices(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static Choices valueOf(long tagValue) {
			Choices[] values = Choices.values();

			for (Choices c : values) {
				if (c.value == tagValue) {
					return c;
				}
			}
			return _ERR_NONE_SELECTED;
		}
	}

	private Choices choice;

	public Data data = null;

	public AxdrEnum data_access_result = null;

	public Get_Data_Result() {
	}

	public Get_Data_Result(byte[] code) {
		this.code = code;
	}

	@Override
	public int encode(BerByteArrayOutputStream axdrOStream) throws IOException {
		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				axdrOStream.write(code[i]);
			}
			return code.length;

		}
		if (choice == Choices._ERR_NONE_SELECTED) {
			throw new IOException("Error encoding AxdrChoice: No item in choice was selected.");
		}

		int codeLength = 0;

		if (choice == Choices.DATA_ACCESS_RESULT) {
			codeLength += data_access_result.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(1);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.DATA) {
			codeLength += data.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(0);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		// This block should be unreachable
		throw new IOException("Error encoding AxdrChoice: No item in choice was encoded.");
	}

	@Override
	public int decode(InputStream iStream) throws IOException {
		int codeLength = 0;
		AxdrEnum choosen = new AxdrEnum();

		codeLength += choosen.decode(iStream);
		resetChoices();
		this.choice = Choices.valueOf(choosen.getValue());

		if (choice == Choices.DATA) {
			data = new Data();
			codeLength += data.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.DATA_ACCESS_RESULT) {
			data_access_result = new AxdrEnum();
			codeLength += data_access_result.decode(iStream);
			return codeLength;
		}

		throw new IOException("Error decoding AxdrChoice: Identifier matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream axdrOStream = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(axdrOStream);
		code = axdrOStream.getArray();
	}

	public Choices getChoiceIndex() {
		return this.choice;
	}

	public void setdata(Data newVal) {
		resetChoices();
		choice = Choices.DATA;
		data = newVal;
	}

	public void setdata_access_result(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.DATA_ACCESS_RESULT;
		data_access_result = newVal;
	}

	private void resetChoices() {
		choice = Choices._ERR_NONE_SELECTED;
		data = null;
		data_access_result = null;
	}

	@Override
	public String toString() {
		if (choice == Choices.DATA) {
			return "choice: {data: " + data + "}";
		}

		if (choice == Choices.DATA_ACCESS_RESULT) {
			return "choice: {data_access_result: " + data_access_result + "}";
		}

		return "unknown";
	}

}
