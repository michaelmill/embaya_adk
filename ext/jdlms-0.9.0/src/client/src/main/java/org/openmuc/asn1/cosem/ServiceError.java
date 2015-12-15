/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.asn1.cosem;

import java.io.IOException;
import java.io.InputStream;

import org.openmuc.jasn1.axdr.AxdrByteArrayOutputStream;
import org.openmuc.jasn1.axdr.AxdrType;
import org.openmuc.jasn1.axdr.types.AxdrEnum;

public class ServiceError implements AxdrType {

	public byte[] code = null;

	public static enum Choices {
		_ERR_NONE_SELECTED(-1), APPLICATION_REFERENCE(0), HARDWARE_RESOURCE(1), VDE_STATE_ERROR(2), SERVICE(3), DEFINITION(
				4), ACCESS(5), INITIATE(6), LOAD_DATA_SET(7), TASK(9), ;

		private int value;

		private Choices(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
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

	public AxdrEnum application_reference = null;

	public AxdrEnum hardware_resource = null;

	public AxdrEnum vde_state_error = null;

	public AxdrEnum service = null;

	public AxdrEnum definition = null;

	public AxdrEnum access = null;

	public AxdrEnum initiate = null;

	public AxdrEnum load_data_set = null;

	public AxdrEnum task = null;

	public ServiceError() {
	}

	public ServiceError(byte[] code) {
		this.code = code;
	}

	@Override
	public int encode(AxdrByteArrayOutputStream axdrOStream) throws IOException {
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

		if (choice == Choices.TASK) {
			codeLength += task.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(9);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.LOAD_DATA_SET) {
			codeLength += load_data_set.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(7);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.INITIATE) {
			codeLength += initiate.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(6);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.ACCESS) {
			codeLength += access.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(5);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.DEFINITION) {
			codeLength += definition.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(4);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.SERVICE) {
			codeLength += service.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(3);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.VDE_STATE_ERROR) {
			codeLength += vde_state_error.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(2);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.HARDWARE_RESOURCE) {
			codeLength += hardware_resource.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(1);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.APPLICATION_REFERENCE) {
			codeLength += application_reference.encode(axdrOStream);
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
		choice = Choices.valueOf(choosen.getValue());

		if (choice == Choices.APPLICATION_REFERENCE) {
			application_reference = new AxdrEnum();
			codeLength += application_reference.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.HARDWARE_RESOURCE) {
			hardware_resource = new AxdrEnum();
			codeLength += hardware_resource.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.VDE_STATE_ERROR) {
			vde_state_error = new AxdrEnum();
			codeLength += vde_state_error.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.SERVICE) {
			service = new AxdrEnum();
			codeLength += service.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.DEFINITION) {
			definition = new AxdrEnum();
			codeLength += definition.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.ACCESS) {
			access = new AxdrEnum();
			codeLength += access.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.INITIATE) {
			initiate = new AxdrEnum();
			codeLength += initiate.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.LOAD_DATA_SET) {
			load_data_set = new AxdrEnum();
			codeLength += load_data_set.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.TASK) {
			task = new AxdrEnum();
			codeLength += task.decode(iStream);
			return codeLength;
		}

		throw new IOException("Error decoding AxdrChoice: Identifier matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		AxdrByteArrayOutputStream axdrOStream = new AxdrByteArrayOutputStream(encodingSizeGuess);
		encode(axdrOStream);
		code = axdrOStream.getArray();
	}

	public Choices getChoiceIndex() {
		return choice;
	}

	public void setapplication_reference(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.APPLICATION_REFERENCE;
		application_reference = newVal;
	}

	public void sethardware_resource(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.HARDWARE_RESOURCE;
		hardware_resource = newVal;
	}

	public void setvde_state_error(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.VDE_STATE_ERROR;
		vde_state_error = newVal;
	}

	public void setservice(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.SERVICE;
		service = newVal;
	}

	public void setdefinition(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.DEFINITION;
		definition = newVal;
	}

	public void setaccess(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.ACCESS;
		access = newVal;
	}

	public void setinitiate(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.INITIATE;
		initiate = newVal;
	}

	public void setload_data_set(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.LOAD_DATA_SET;
		load_data_set = newVal;
	}

	public void settask(AxdrEnum newVal) {
		resetChoices();
		choice = Choices.TASK;
		task = newVal;
	}

	private void resetChoices() {
		choice = Choices._ERR_NONE_SELECTED;
		application_reference = null;
		hardware_resource = null;
		vde_state_error = null;
		service = null;
		definition = null;
		access = null;
		initiate = null;
		load_data_set = null;
		task = null;
	}

}