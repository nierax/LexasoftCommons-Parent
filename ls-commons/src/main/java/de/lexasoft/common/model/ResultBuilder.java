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
 * Builder for @Result.
 * 
 * @author nierax
 *
 * @param <T>
 */
public class ResultBuilder<T> {

	private T value;
	private MessageList messages;

	private ResultBuilder(T value) {
		messages = MessageList.of();
		this.value = value;
	}

	/**
	 * Create a result builder.
	 * 
	 * @param <T>   The type of the result
	 * @param value The value, connected with the result.
	 * @return A result builder for the given type.
	 */
	public static final <T> ResultBuilder<T> of(T value) {
		return new ResultBuilder<>(value);
	}

	/**
	 * A message, connected with the operation, performed.
	 * <p>
	 * This method can be called several times to add a number of messages to the
	 * list.
	 * 
	 * @param message A message, connected with the operation.
	 * @return The builder for fluent api notation
	 */
	public ResultBuilder<T> withMessage(Message message) {
		Optional.ofNullable(message).ifPresent(this.messages::addMessage);
		return this;
	}

	/**
	 * Use all messages in the given list as messages for this operation, connected
	 * with the result..
	 * 
	 * @param messages The messages to use.
	 * @return The builder for fluent api notation
	 */
	public ResultBuilder<T> withMessages(MessageList messages) {
		Optional.ofNullable(messages)//
		    .ifPresent((ms) -> this.messages.addMessages(ms));
		return this;
	}

	/**
	 * Final step: Build the result object form former calls of the "with" methods.
	 * 
	 * @return The result object, connected with the operation
	 */
	public Result<T> build() {
		return new Result<>(value, messages);
	}

}
