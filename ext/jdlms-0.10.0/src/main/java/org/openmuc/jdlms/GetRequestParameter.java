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

/**
 * Collection of data needed for a single remote GET call
 */
public class GetRequestParameter implements AttributeRequest {
	private final int classId;
	private final ObisCode obisCode;
	private final int attributeId;

	/**
	 * Structure defining access to a subset of an attribute. Consort IEC 62056-62 to see which attribute has which
	 * access selections. May be null if not needed. (A value of null reads the full attribute)
	 */
	private SelectiveAccessDescription access_selection;

	/**
	 * Creates a get parameter for that particular attribute
	 * 
	 * @param classId
	 *            Class of the object to read
	 * @param obisCode
	 *            Identifier of the remote object to read
	 * @param attributeId
	 *            Attribute of the object that is to read
	 */
	public GetRequestParameter(int classId, ObisCode obisCode, int attributeId) {
		this.classId = classId;
		this.obisCode = obisCode;
		this.attributeId = attributeId;
	}

	@Override
	public int classId() {
		return classId;
	}

	@Override
	public ObisCode obisCode() {
		return obisCode;
	}

	@Override
	public int attributeId() {
		return attributeId;
	}

	@Override
	public SelectiveAccessDescription accessSelection() {
		return access_selection;
	}

	/**
	 * Adds an selection filter to this get request
	 * 
	 * @param access
	 *            The filter to apply
	 */
	public void setAccessSelection(SelectiveAccessDescription access) {
		access_selection = access;
	}
}