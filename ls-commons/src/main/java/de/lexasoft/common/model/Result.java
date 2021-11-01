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

import java.util.Optional;

/**
 * Defines a common result, signalizing whether the result came back with an
 * error or not.
 * <p>
 * The model allows a number of messages, as there might be several hints,
 * warnings or errors connected with a special operation.
 * <p>
 * The result can be build only by a special builder class, which can be
 * accessed via a static method.
 * 
 * @author nierax
 *
 * @param <T> The return type of the operation.
 */
public class Result<T> {

	private final T value;
	private final MessageList messages;

	/**
	 * 
	 */
	Result(T value, MessageList messages) {
		this.value = value;
		this.messages = MessageList.of();
		Optional//
		    .ofNullable(messages)//
		    .ifPresent(this.messages::addMessages);
	}

	/**
	 * @return The result value of the operation
	 */
	public T get() {
		return value;
	}

	/**
	 * @return The messages, connected to the operation
	 */
	public MessageList getMessages() {
		return messages;
	}

	/**
	 * 
	 * @return True, if there is at least one message in the result
	 *         with @MessageSeverity#ERROR, false otherwise
	 */
	public boolean isErroneous() {
		return messages.countMessagesWithSeverity(MessageSeverity.ERROR) > 0;
	}

}
