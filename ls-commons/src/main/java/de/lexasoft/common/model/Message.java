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

	private MessageId id;
	private MessageText text;
	private MessageSeverity severity;

	/**
	 * Create an error message.
	 * 
	 * @param id
	 * @param text
	 */
	private Message(MessageId id, MessageText text, MessageSeverity severity) {
		super();
		this.id = id;
		this.text = text;
		this.severity = severity;
	}

	/**
	 * @return the id
	 */
	public MessageId getId() {
		return id;
	}

	/**
	 * @return the text
	 */
	public MessageText getText() {
		return text;
	}

	/**
	 * Create an error message.
	 * 
	 * @param id
	 * @param text
	 */
	public static final Message of(MessageId id, MessageText text, MessageSeverity severity) {
		return new Message(id, text, severity);
	}

	/**
	 * @return the severity
	 */
	public MessageSeverity getSeverity() {
		return severity;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", severity=" + severity + "]";
	}

}
