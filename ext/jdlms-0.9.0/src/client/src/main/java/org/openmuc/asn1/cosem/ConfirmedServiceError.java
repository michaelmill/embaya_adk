/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.asn1.cosem;

import java.io.IOException;
import java.io.InputStream;

import org.openmuc.jasn1.axdr.AxdrByteArrayOutputStream;
import org.openmuc.jasn1.axdr.AxdrType;
import org.openmuc.jasn1.axdr.types.AxdrEnum;

public class ConfirmedServiceError implements AxdrType {

	public byte[] code = null;

	public static enum Choices {
		_ERR_NONE_SELECTED(-1), INITIATEERROR(1), GETSTATUS(2), GETNAMELIST(3), GETVARIABLEATTRIBUTE(4), READ(5), WRITE(
				6), GETDATASETATTRIBUTE(7), GETTIATTRIBUTE(8), CHANGESCOPE(9), START(10), STOP(11), RESUME(12), MAKEUSABLE(
				13), INITIATELOAD(14), LOADSEGMENT(15), TERMINATELOAD(16), INITIATEUPLOAD(17), UPLOADSEGMENT(18), TERMINATEUPLOAD(
				19), ;

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

	public ServiceError initiateError = null;

	public ServiceError getStatus = null;

	public ServiceError getNameList = null;

	public ServiceError getVariableAttribute = null;

	public ServiceError read = null;

	public ServiceError write = null;

	public ServiceError getDataSetAttribute = null;

	public ServiceError getTIAttribute = null;

	public ServiceError changeScope = null;

	public ServiceError start = null;

	public ServiceError stop = null;

	public ServiceError resume = null;

	public ServiceError makeUsable = null;

	public ServiceError initiateLoad = null;

	public ServiceError loadSegment = null;

	public ServiceError terminateLoad = null;

	public ServiceError initiateUpLoad = null;

	public ServiceError upLoadSegment = null;

	public ServiceError terminateUpLoad = null;

	public ConfirmedServiceError() {
	}

	public ConfirmedServiceError(byte[] code) {
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

		if (choice == Choices.TERMINATEUPLOAD) {
			codeLength += terminateUpLoad.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(19);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.UPLOADSEGMENT) {
			codeLength += upLoadSegment.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(18);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.INITIATEUPLOAD) {
			codeLength += initiateUpLoad.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(17);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.TERMINATELOAD) {
			codeLength += terminateLoad.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(16);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.LOADSEGMENT) {
			codeLength += loadSegment.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(15);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.INITIATELOAD) {
			codeLength += initiateLoad.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(14);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.MAKEUSABLE) {
			codeLength += makeUsable.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(13);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.RESUME) {
			codeLength += resume.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(12);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.STOP) {
			codeLength += stop.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(11);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.START) {
			codeLength += start.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(10);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.CHANGESCOPE) {
			codeLength += changeScope.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(9);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.GETTIATTRIBUTE) {
			codeLength += getTIAttribute.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(8);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.GETDATASETATTRIBUTE) {
			codeLength += getDataSetAttribute.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(7);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.WRITE) {
			codeLength += write.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(6);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.READ) {
			codeLength += read.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(5);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.GETVARIABLEATTRIBUTE) {
			codeLength += getVariableAttribute.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(4);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.GETNAMELIST) {
			codeLength += getNameList.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(3);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.GETSTATUS) {
			codeLength += getStatus.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(2);
			codeLength += c.encode(axdrOStream);
			return codeLength;
		}

		if (choice == Choices.INITIATEERROR) {
			codeLength += initiateError.encode(axdrOStream);
			AxdrEnum c = new AxdrEnum(1);
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

		if (choice == Choices.INITIATEERROR) {
			initiateError = new ServiceError();
			codeLength += initiateError.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.GETSTATUS) {
			getStatus = new ServiceError();
			codeLength += getStatus.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.GETNAMELIST) {
			getNameList = new ServiceError();
			codeLength += getNameList.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.GETVARIABLEATTRIBUTE) {
			getVariableAttribute = new ServiceError();
			codeLength += getVariableAttribute.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.READ) {
			read = new ServiceError();
			codeLength += read.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.WRITE) {
			write = new ServiceError();
			codeLength += write.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.GETDATASETATTRIBUTE) {
			getDataSetAttribute = new ServiceError();
			codeLength += getDataSetAttribute.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.GETTIATTRIBUTE) {
			getTIAttribute = new ServiceError();
			codeLength += getTIAttribute.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.CHANGESCOPE) {
			changeScope = new ServiceError();
			codeLength += changeScope.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.START) {
			start = new ServiceError();
			codeLength += start.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.STOP) {
			stop = new ServiceError();
			codeLength += stop.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.RESUME) {
			resume = new ServiceError();
			codeLength += resume.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.MAKEUSABLE) {
			makeUsable = new ServiceError();
			codeLength += makeUsable.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.INITIATELOAD) {
			initiateLoad = new ServiceError();
			codeLength += initiateLoad.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.LOADSEGMENT) {
			loadSegment = new ServiceError();
			codeLength += loadSegment.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.TERMINATELOAD) {
			terminateLoad = new ServiceError();
			codeLength += terminateLoad.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.INITIATEUPLOAD) {
			initiateUpLoad = new ServiceError();
			codeLength += initiateUpLoad.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.UPLOADSEGMENT) {
			upLoadSegment = new ServiceError();
			codeLength += upLoadSegment.decode(iStream);
			return codeLength;
		}

		if (choice == Choices.TERMINATEUPLOAD) {
			terminateUpLoad = new ServiceError();
			codeLength += terminateUpLoad.decode(iStream);
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

	public void setinitiateError(ServiceError newVal) {
		resetChoices();
		choice = Choices.INITIATEERROR;
		initiateError = newVal;
	}

	public void setgetStatus(ServiceError newVal) {
		resetChoices();
		choice = Choices.GETSTATUS;
		getStatus = newVal;
	}

	public void setgetNameList(ServiceError newVal) {
		resetChoices();
		choice = Choices.GETNAMELIST;
		getNameList = newVal;
	}

	public void setgetVariableAttribute(ServiceError newVal) {
		resetChoices();
		choice = Choices.GETVARIABLEATTRIBUTE;
		getVariableAttribute = newVal;
	}

	public void setread(ServiceError newVal) {
		resetChoices();
		choice = Choices.READ;
		read = newVal;
	}

	public void setwrite(ServiceError newVal) {
		resetChoices();
		choice = Choices.WRITE;
		write = newVal;
	}

	public void setgetDataSetAttribute(ServiceError newVal) {
		resetChoices();
		choice = Choices.GETDATASETATTRIBUTE;
		getDataSetAttribute = newVal;
	}

	public void setgetTIAttribute(ServiceError newVal) {
		resetChoices();
		choice = Choices.GETTIATTRIBUTE;
		getTIAttribute = newVal;
	}

	public void setchangeScope(ServiceError newVal) {
		resetChoices();
		choice = Choices.CHANGESCOPE;
		changeScope = newVal;
	}

	public void setstart(ServiceError newVal) {
		resetChoices();
		choice = Choices.START;
		start = newVal;
	}

	public void setstop(ServiceError newVal) {
		resetChoices();
		choice = Choices.STOP;
		stop = newVal;
	}

	public void setresume(ServiceError newVal) {
		resetChoices();
		choice = Choices.RESUME;
		resume = newVal;
	}

	public void setmakeUsable(ServiceError newVal) {
		resetChoices();
		choice = Choices.MAKEUSABLE;
		makeUsable = newVal;
	}

	public void setinitiateLoad(ServiceError newVal) {
		resetChoices();
		choice = Choices.INITIATELOAD;
		initiateLoad = newVal;
	}

	public void setloadSegment(ServiceError newVal) {
		resetChoices();
		choice = Choices.LOADSEGMENT;
		loadSegment = newVal;
	}

	public void setterminateLoad(ServiceError newVal) {
		resetChoices();
		choice = Choices.TERMINATELOAD;
		terminateLoad = newVal;
	}

	public void setinitiateUpLoad(ServiceError newVal) {
		resetChoices();
		choice = Choices.INITIATEUPLOAD;
		initiateUpLoad = newVal;
	}

	public void setupLoadSegment(ServiceError newVal) {
		resetChoices();
		choice = Choices.UPLOADSEGMENT;
		upLoadSegment = newVal;
	}

	public void setterminateUpLoad(ServiceError newVal) {
		resetChoices();
		choice = Choices.TERMINATEUPLOAD;
		terminateUpLoad = newVal;
	}

	private void resetChoices() {
		choice = Choices._ERR_NONE_SELECTED;
		initiateError = null;
		getStatus = null;
		getNameList = null;
		getVariableAttribute = null;
		read = null;
		write = null;
		getDataSetAttribute = null;
		getTIAttribute = null;
		changeScope = null;
		start = null;
		stop = null;
		resume = null;
		makeUsable = null;
		initiateLoad = null;
		loadSegment = null;
		terminateLoad = null;
		initiateUpLoad = null;
		upLoadSegment = null;
		terminateUpLoad = null;
	}

}
