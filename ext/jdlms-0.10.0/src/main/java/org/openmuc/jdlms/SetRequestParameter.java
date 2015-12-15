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

/**
 * Collection of data needed for a single remote SET call
 */
public class SetRequestParameter implements AttributeRequest {
	private final int classId;
	private final ObisCode obisCode;
	private final int attributeId;
	private final DataObject data;

	private SelectiveAccessDescription access_selection;

	/**
	 * Creates a set parameter for that particular attribute with a copy of the given data container
	 * 
	 * @param classId
	 *            Class of the object to change
	 * @param obisCode
	 *            Identifier of the remote object to change
	 * @param attributeId
	 *            Attribute of the object that is to change
	 * @param data
	 *            Container of this parameter
	 */
	public SetRequestParameter(int classId, ObisCode obisCode, int attributeId, DataObject data) {
		this.classId = classId;
		this.obisCode = obisCode;
		this.attributeId = attributeId;
		this.data = data;
	}

	@Override
	public int classId() {
		return classId;
	}

	@Override
	public ObisCode obisCode() {
		return this.obisCode;
	}

	@Override
	public int attributeId() {
		return this.attributeId;
	}

	@Override
	public SelectiveAccessDescription accessSelection() {
		return this.access_selection;
	}

	/**
	 * Adds an selection filter to this get request
	 * 
	 * @param access
	 *            The filter to apply
	 */
	public void setAccessSelection(SelectiveAccessDescription access) {
		this.access_selection = access;
	}

	/**
	 * @return The Data container of this parameter object
	 */
	public DataObject data() {
		return this.data;
	}
}
