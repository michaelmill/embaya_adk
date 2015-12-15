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
package org.openmuc.jdlms;

import org.openmuc.jdlms.datatypes.DataObject;

public class RequestParameterFactory {
	private final int classId;
	private final ObisCode obisCode;
	private final int attributeId;

	public RequestParameterFactory(int classId, ObisCode obisCode, int attributeId) {
		this.classId = classId;
		this.obisCode = obisCode;
		this.attributeId = attributeId;
	}

	public GetRequestParameter createGetRequestParameter() {
		return new GetRequestParameter(classId, obisCode, attributeId);
	}

	public SetRequestParameter createSetRequestParameter(DataObject data) {
		return new SetRequestParameter(classId, obisCode, attributeId, data);
	}
}
