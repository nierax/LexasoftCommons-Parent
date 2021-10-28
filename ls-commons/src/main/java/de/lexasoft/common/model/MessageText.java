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
 * The message of an error.
 * 
 * @author nierax
 *
 */
public class MessageText {

	private String msgString;

	/**
	 * The MessageText needs a String Representation to be created.
	 * 
	 * @param msgString
	 */
	private MessageText(String msgString) {
		this.msgString = msgString;
	}

	@Override
	public String toString() {
		return msgString.toString();
	}

	/**
	 * Create an error message with the given error text.
	 * 
	 * @param errorText
	 * @return
	 */
	public static final MessageText of(String errorText) {
		return new MessageText(errorText);
	}

}
