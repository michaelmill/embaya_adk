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
 * Class holding all data received from an event that the remote station sent
 */
public final class EventNotification {
	private final int classId;
	private final String obisCode;
	private final int attributeId;
	private final DataObject data;
	private final Long timestamp;

	/**
	 * Creates a new event notification without timestamp
	 * 
	 * @param classId
	 *            Class of the remote object that raised the event
	 * @param obisCode
	 *            Logical address of the remote object
	 * @param attributeId
	 *            Attribute that has been changed
	 * @param newValue
	 *            The new value of the attribute
	 */
	public EventNotification(int classId, String obisCode, int attributeId, DataObject newValue) {
		this(classId, obisCode, attributeId, newValue, null);
	}

	/**
	 * Creates a new event notification without timestamp
	 * 
	 * @param classId
	 *            Class of the remote object that raised the event
	 * @param obisCode
	 *            Logical address of the remote object
	 * @param attributeId
	 *            Attribute that has been changed
	 * @param newValue
	 *            The new value of the attribute
	 * @param timestamp
	 *            The timestamp of the remote station when the event has been raised
	 */
	public EventNotification(int classId, String obisCode, int attributeId, DataObject newValue, Long timestamp) {
		this.classId = classId;
		this.obisCode = obisCode;
		this.attributeId = attributeId;
		this.data = newValue;
		this.timestamp = timestamp;
	}

	public int classId() {
		return classId;
	}

	public String obisCode() {
		return obisCode;
	}

	public int attributeId() {
		return attributeId;
	}

	public DataObject data() {
		return data;
	}

	/**
	 * @return The timestamp when this event has been generated at the remote station. Null if there is no timestamp
	 */
	public Long timestamp() {
		return timestamp;
	}

	/**
	 * @return True if this event has a timestamp
	 */
	public boolean hasTimestamp() {
		return timestamp != null;
	}
}
