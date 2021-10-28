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
 * Represents an (error) message in any application.
 * 
 * @author nierax
 *
 */
public class Message {

	private MessageId messageId;
	private MessageText messageText;

	/**
	 * Create an error message.
	 * 
	 * @param messageId
	 * @param messageText
	 */
	private Message(MessageId messageId, MessageText messageText) {
		super();
		this.messageId = messageId;
		this.messageText = messageText;
	}

	/**
	 * @return the messageId
	 */
	public MessageId getErrorId() {
		return messageId;
	}

	/**
	 * @return the messageText
	 */
	public MessageText getErrorMessage() {
		return messageText;
	}

	/**
	 * Create an error message.
	 * 
	 * @param messageId
	 * @param messageText
	 */
	public static final Message of(MessageId messageId, MessageText messageText) {
		return new Message(messageId, messageText);
	}

}
