/**
 * Copyright (C) 2021 nierax
 * This program is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU General Public License as published by the Free Software Foundation; either version 3 
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, see <http://www.gnu.org/licenses/>. 
 */
package de.lexasoft.common.model;

/**
 * The id of an error.
 * 
 * @author nierax
 *
 */
public class ErrorId {

	private String idString;

	/**
	 * The ErrorId needs a String Representation to be created.
	 * 
	 * @param idString
	 */
	private ErrorId(String idString) {
		this.idString = idString;
	}

	private ErrorId() {

	}

	@Override
	public String toString() {
		return idString.toString();
	}

	/**
	 * The ErrorId will be created with a given Id String.
	 * <p>
	 * Can be used, if the value the error id is known.
	 * <p>
	 * The value can only be retrieved via the toString() method.
	 * 
	 * @param idString Value for the id.
	 * @return ErrorId object with the given value as id.
	 */
	public final static ErrorId of(String idString) {
		return new ErrorId(idString);
	}

	/**
	 * The ErrorId will be created with the hash code of the object as value.
	 * <p>
	 * This can be used, if there is no need for a special error id. In this case
	 * the ErrorId object must be kept to retrieve the error from lists.
	 * 
	 * @return ErrorId
	 */
	public final static ErrorId of() {
		ErrorId errorId = new ErrorId();
		errorId.idString = Integer.toString(errorId.hashCode());
		return errorId;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || !(obj instanceof ErrorId)) {
			return false;
		}
		ErrorId other = (ErrorId) obj;
		return idString.equals(other.idString);
	}

}
