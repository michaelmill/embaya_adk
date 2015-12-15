/*
 * Copyright 2012-15 Fraunhofer ISE
 *
 * This file is part of jDLMS.
 * For more information visit http://www.openmuc.org
 *
 * jDLMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jDLMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jDLMS.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jdlms.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openmuc.jdlms.AccessResultCode;
import org.openmuc.jdlms.GetResult;
import org.openmuc.jdlms.ObisCode;
import org.openmuc.jdlms.datatypes.BitString;
import org.openmuc.jdlms.datatypes.CosemDateFormat;
import org.openmuc.jdlms.datatypes.DataObject;

abstract class ConsoleApp {

	private static final String DATA_INPUT_FORMAT = "<Data_Type>:<Data>";

	private static final String POSSIBLE_DATA_TYPES = "(b)oolean / (f)loat / (d)ouble / (l)ong / (i)nteger / (o)ctet";

	private final BufferedReader inputReader;

	public ConsoleApp() {
		this.inputReader = new BufferedReader(new InputStreamReader(System.in));
	}

	public final void processRead() throws IOException {
		System.out.println("Enter: " + nameFormat());
		String requestParameter = inputReader.readLine();

		GetResult result;
		try {
			result = callGet(requestParameter);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Failed to process read.");

			return;
		}
		AccessResultCode resultCode = result.resultCode();

		if (resultCode == AccessResultCode.SUCCESS) {
			System.out.println("Result Code: " + result.resultCode());

			DataObject resultData = result.resultData();
			printType(resultData);
		}
		else {
			System.err.printf("Failed to read. AccessResultCode: %s\n", resultCode);
		}
	}

	public final void processScan() throws IOException {
		System.out.println("** Scan started...");

		GetResult scanResult;
		try {
			scanResult = callScan();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Failed to scan. Timed out. Try again.");
			return;
		}

		if (scanResult.resultCode() != AccessResultCode.SUCCESS) {
			System.err.println("Device sent error code " + scanResult.resultCode().name());
			return;
		}

		DataObject root = scanResult.resultData();
		List<DataObject> objectArray = root.value();
		System.out.println("Scanned addresses:");

		for (DataObject objectDef : objectArray) {
			List<DataObject> defItems = objectDef.value();
			Integer classId = defItems.get(0).value();
			byte[] logicalName = defItems.get(2).value();
			List<DataObject> complex1 = defItems.get(3).value();
			List<DataObject> attributes = complex1.get(0).value();

			for (DataObject attributeAccess : attributes) {
				List<DataObject> value = attributeAccess.value();
				Number attributeId = value.get(0).value();

				ObisCode obisCode = new ObisCode(logicalName);

				// TODO: fix this output
				System.out.printf("%s   \t", obisCode.toDecimal());
				System.out.printf("Address: %d/%s/%d\n ", classId, obisCode.toString(), attributeId);
			}
		}
	}

	public final void processWrite() throws IOException {
		System.out.println("Enter: " + nameFormat());
		String address = inputReader.readLine();

		System.out.println("Enter: " + DATA_INPUT_FORMAT);
		System.out.println("possible data types: " + POSSIBLE_DATA_TYPES);

		String inputData = inputReader.readLine();
		DataObject dataToWrite = buildDataObject(inputData);

		AccessResultCode resultCode = callSet(address, dataToWrite);
		if (resultCode == AccessResultCode.SUCCESS) {
			System.out.println("Result Code: " + resultCode);
		}
		else {
			System.err.printf("Failed to write. AccessResultCode: %s\n", resultCode);
		}
	}

	public abstract void close();

	protected abstract String nameFormat();

	protected abstract GetResult callGet(String requestParameter) throws IOException, TimeoutException;

	protected abstract AccessResultCode callSet(String requestParameter, DataObject dataToWrite) throws IOException;

	protected abstract GetResult callScan() throws IOException, TimeoutException;

	private void printType(DataObject resultData) {
		printType(resultData, 0);
	}

	private void printType(DataObject resultData, int shiftChars) {
		String shift;

		if (shiftChars > 0) {
			shift = String.format("%" + shiftChars + "s%s", " ", "|- ");

		}
		else {
			shift = "";
		}

		String message = String.format("%s Value: ", resultData.choiceIndex().name());
		if (resultData.isBoolean()) {
			Boolean boolVal = resultData.value();
			System.out.printf(shift + message + boolVal.toString());
		}
		else if (resultData.isNumber()) {
			Number number = resultData.value();
			System.out.println(shift + message + number.toString());
		}
		else if (resultData.isByteArray()) {
			byte[] value = resultData.value();
			System.out.println(shift + message + Arrays.toString(value));
		}
		else if (resultData.isBitString()) {
			BitString value = resultData.value();
			System.out.println(shift + message + Arrays.toString(value.bitString()));

		}
		else if (resultData.isCosemDateFormat()) {
			CosemDateFormat value = resultData.value();
			System.out.println(shift + message + value.toCalendar().getTime().toString());
		}
		else if (resultData.isComplex()) {
			System.out.println(shift + message);
			List<DataObject> complex = resultData.value();

			for (DataObject data : complex) {
				printType(data, shiftChars + 3);
			}
		}
		else {
			System.err.println(shift + "Value is undefined type");
		}
	}

	private DataObject buildDataObject(String line) {

		String[] arguments = line.split(":");

		if (arguments.length != 2) {
			throw new IllegalArgumentException(String.format("Wrong number of arguments. %s", DATA_INPUT_FORMAT));
		}

		String dataTypeString = arguments[0];
		String dataString = arguments[1];

		char datatype = dataTypeString.toUpperCase().charAt(0);

		DataObject dataObject;

		switch (datatype) {
		case 'S':
			short sData = Short.parseShort(dataString);
			dataObject = DataObject.newInteger16Data(sData);
			break;
		case 'I':
			int iData = Integer.parseInt(dataString);
			dataObject = DataObject.newInteger32Data(iData);
			break;
		case 'L':
			Long lData = Long.parseLong(dataString);
			dataObject = DataObject.newInteger64Data(lData);
			break;
		case 'F':
			float fData = Float.parseFloat(dataString);
			dataObject = DataObject.newFloat32Data(fData);
			break;
		case 'D':
			double dData = Double.parseDouble(dataString);
			dataObject = DataObject.newFloat64Data(dData);
			break;
		case 'B':
			boolean bData = Boolean.parseBoolean(dataString);
			dataObject = DataObject.newBoolData(bData);
			break;
		default:
			throw new IllegalArgumentException(String.format("Wrong data type. %s", POSSIBLE_DATA_TYPES));
		}

		return dataObject;
	}
}
