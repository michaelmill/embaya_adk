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

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openmuc.jdlms.AccessResultCode;
import org.openmuc.jdlms.GetRequestParameter;
import org.openmuc.jdlms.GetResult;
import org.openmuc.jdlms.LnClientConnection;
import org.openmuc.jdlms.ObisCode;
import org.openmuc.jdlms.RequestParameterFactory;
import org.openmuc.jdlms.datatypes.DataObject;

class LnConsoleApp extends ConsoleApp {
	private static final String LOGICAL_NAME_FORMAT = "<Interface_Class_ID>/<OBIS_Code>/<Object_Attribute_ID>";

	private final LnClientConnection connection;

	public LnConsoleApp(LnClientConnection connection) {
		this.connection = connection;
	}

	@Override
	public void close() {
		this.connection.close();
	}

	@Override
	protected String nameFormat() {
		return LOGICAL_NAME_FORMAT;
	}

	@Override
	protected GetResult callGet(String requestParameter) throws IOException, TimeoutException {
		RequestParameterFactory request;

		try {
			request = buidlRequest(requestParameter);
		} catch (IllegalArgumentException e) {
			// TODO: handle this better
			// System.err.println(e.getMessage());
			// return;
			throw e;
		}

		return connection.get(request.createGetRequestParameter()).get(0);
	}

	private RequestParameterFactory buidlRequest(String requestParameter)
			throws IllegalArgumentException, NumberFormatException {
		String[] arguments = requestParameter.split("/");

		if (arguments.length != 3) {
			throw new IllegalArgumentException(String.format("Wrong number of arguments. %s", LOGICAL_NAME_FORMAT));
		}

		int classId = Integer.parseInt(arguments[0]);

		ObisCode obisCode = new ObisCode(arguments[1]);

		int attributeId = Integer.valueOf(arguments[2]);

		return new RequestParameterFactory(classId, obisCode, attributeId);
	}

	@Override
	protected GetResult callScan() throws IOException, TimeoutException {
		GetRequestParameter scanChannels = new GetRequestParameter(15, new ObisCode("0.0.40.0.0.255"), 2);

		return connection.get(scanChannels).get(0);
	}

	@Override
	protected AccessResultCode callSet(String requestParameter, DataObject dataToWrite) throws IOException {
		RequestParameterFactory request = buidlRequest(requestParameter);

		return connection.set(request.createSetRequestParameter(dataToWrite)).get(0);
	}

}
