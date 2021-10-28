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
public class MessageId {

	private String idString;

	/**
	 * The MessageId needs a String Representation to be created.
	 * 
	 * @param idString
	 */
	private MessageId(String idString) {
		this.idString = idString;
	}

	private MessageId() {

	}

	@Override
	public String toString() {
		return idString.toString();
	}

	/**
	 * The MessageId will be created with a given Id String.
	 * <p>
	 * Can be used, if the value the error id is known.
	 * <p>
	 * The value can only be retrieved via the toString() method.
	 * 
	 * @param idString Value for the id.
	 * @return MessageId object with the given value as id.
	 */
	public final static MessageId of(String idString) {
		return new MessageId(idString);
	}

	/**
	 * The MessageId will be created with the hash code of the object as value.
	 * <p>
	 * This can be used, if there is no need for a special error id. In this case
	 * the MessageId object must be kept to retrieve the error from lists.
	 * 
	 * @return MessageId
	 */
	public final static MessageId of() {
		MessageId messageId = new MessageId();
		messageId.idString = Integer.toString(messageId.hashCode());
		return messageId;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || !(obj instanceof MessageId)) {
			return false;
		}
		MessageId other = (MessageId) obj;
		return idString.equals(other.idString);
	}

}
