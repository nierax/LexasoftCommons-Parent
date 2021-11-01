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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This list collects messages and offers methods to acces them.
 * 
 * @author nierax
 *
 */
public class MessageList {

	private List<Message> messages;

	private MessageList() {
		messages = new ArrayList<>();
	}

	/**
	 * Add the given error to the list.
	 * 
	 * @param message The error to add
	 * @return Reference to this list for fluent API writing
	 */
	public MessageList addMessage(Message message) {
		this.messages.add(message);
		return this;
	}

	/**
	 * Remove the given error from the list.
	 * <p>
	 * The list remains unchanged, if there is not such error in the list.
	 * 
	 * @param message The error to remove.
	 * @return
	 */
	public MessageList removeMessage(Message message) {
		messages.remove(message);
		return this;
	}

	/**
	 * Remove the error with the given id from the list.
	 * <p>
	 * Does nothing, if there is no error with the given id in the list.
	 * 
	 * @param messageId The error id to remove
	 * @return The error list
	 */
	public MessageList removeMessage(MessageId messageId) {
		findMessageById(messageId)//
		    .ifPresent(messages::remove);
		return this;
	}

	/**
	 * Find the first error, connected to the given id.
	 * 
	 * @param messageId The error id to find
	 * @return An @Optional with the error, being empty, if there was no error with
	 *         that error id.
	 */
	public Optional<Message> findMessageById(MessageId messageId) {
		return stream()//
		    .filter(e -> e.getId().equals(messageId))//
		    .findFirst();
	}

	private Stream<Message> filterMessagesWithSeverity(MessageSeverity severity) {
		return stream() //
		    .filter((m) -> m.getSeverity() == severity);
	}

	/**
	 * Get all messages with the given severity.
	 * 
	 * @param severity The severity asked for.
	 * @return A new list, only containing the messages with the severity given.
	 */
	public List<Message> findAllMessagesWithSeverity(MessageSeverity severity) {
		return filterMessagesWithSeverity(severity)//
		    .collect(Collectors.toList());
	}

	/**
	 * Counts the number of messages with the given severity.
	 * 
	 * @param severity The severity to do count.
	 * @return The number of messages with the given severity.
	 */
	public long countMessagesWithSeverity(MessageSeverity severity) {
		return filterMessagesWithSeverity(severity)//
		    .count();
	}

	/**
	 * 
	 * @return The number of messages in the list.
	 */
	public int nrOfMessages() {
		return messages.size();
	}

	/**
	 * Removes all messages from the list.
	 * 
	 * @return The list, now being empty.
	 */
	public MessageList removeAllMessages() {
		messages.clear();
		return this;
	}

	/**
	 * 
	 * @return The stream of messages
	 */
	public Stream<Message> stream() {
		return messages.stream();
	}

	/**
	 * Create an empty error list
	 * 
	 * @return Empty error list
	 */
	public final static MessageList of() {
		return new MessageList();
	}

}
