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
 * Declares methods that both GetRequest and SetRequest use.
 * <p>
 * This interface is used internally and has no further purpose for the user of this library
 * </p>
 */
public interface AttributeRequest {
	/**
	 * @return The class id this request refers to
	 */
	int classId();

	/**
	 * @return The logical name of the remote object this request refers to
	 */
	ObisCode obisCode();

	/**
	 * @return The attribute id this request refers to
	 */
	int attributeId();

	/**
	 * @return The selection mask this request uses, if any
	 */
	SelectiveAccessDescription accessSelection();
}
