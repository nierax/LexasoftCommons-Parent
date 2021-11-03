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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author nierax
 *
 */
class MessageListTest {

	private MessageList cut;

	private Message error1;
	private MessageId errorId1;
	private Message error2;
	private MessageId errorId2;
	private Message error3;
	private MessageId errorId3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cut = MessageList.of();
		errorId1 = MessageId.of("0815");
		error1 = Message.of(errorId1, MessageText.of("Message 1 occured"), MessageSeverity.ERROR);
		errorId2 = MessageId.of();
		error2 = Message.of(errorId2, MessageText.of("Message 2 occured"), MessageSeverity.ERROR);
		errorId3 = MessageId.of("4711");
		error3 = Message.of(errorId3, MessageText.of("Message 3 occured"), MessageSeverity.ERROR);

		cut.addMessage(error1);
		cut.addMessage(error2);
		cut.addMessage(error3);
	}

	@Test
	final void find_message_By_Id_1() {
		Optional<Message> message = cut.findMessageById(MessageId.of("0815"));
		assertTrue(message.isPresent());
		assertEquals(error1, message.get());
	}

	@Test
	final void find_message_By_Id_2() {
		Optional<Message> message = cut.findMessageById(errorId2);
		assertTrue(message.isPresent());
		assertEquals(error2, message.get());
	}

	@Test
	final void find_message_By_Id_3() {
		Optional<Message> message = cut.findMessageById(errorId3);
		assertTrue(message.isPresent());
		assertEquals(error3, message.get());
	}

	@Test
	final void find_message_By_Id_NotFound() {
		Optional<Message> message = cut.findMessageById(MessageId.of());
		assertTrue(message.isEmpty());
	}

	@Test
	final void remove_error_() {
		cut.removeMessage(error1);
		assertFalse(cut.findMessageById(error1.getId()).isPresent());
	}

	@Test
	final void remove_error_from_id() {
		cut.removeMessage(errorId1);
		assertFalse(cut.findMessageById(errorId1).isPresent());
	}

	@Test
	final void remove_all_errors() {
		// Just make sure, there are some entries.
		assertEquals(3, cut.nrOfMessages());
		// Now remove all of them.
		cut.removeAllMessages();
		// Now there should be nor more errors
		assertEquals(0, cut.nrOfMessages());
		assertFalse(cut.findMessageById(errorId1).isPresent());
	}

	@Test
	final void find_all_messages_with_severity_error_3() {
		// Run
		List<Message> result = cut.findAllMessagesWithSeverity(MessageSeverity.ERROR);
		// Check
		assertEquals(3, result.size());
		assertEquals(error1, result.get(0));
		assertEquals(error2, result.get(1));
		assertEquals(error3, result.get(2));
	}

	@Test
	final void find_all_messages_with_severity_warning_none() {
		// Run
		List<Message> result = cut.findAllMessagesWithSeverity(MessageSeverity.WARNING);
		// Check
		assertEquals(0, result.size());
	}

	@Test
	final void find_all_messages_with_severity_info_none() {
		// Run
		List<Message> result = cut.findAllMessagesWithSeverity(MessageSeverity.INFO);
		// Check
		assertEquals(0, result.size());
	}

	@Test
	final void find_all_messages_with_severity_warning_1() {
		// Prepare
		Message warning = Message.of(MessageId.of(), MessageText.of("Message warning"), MessageSeverity.WARNING);
		cut.addMessage(warning);
		// Run
		List<Message> result = cut.findAllMessagesWithSeverity(MessageSeverity.WARNING);
		// Check
		assertEquals(1, result.size());
		assertEquals(warning, result.get(0));
	}

	@Test
	final void count_messages_with_severity_error_3() {
		// Run
		long count = cut.countMessagesWithSeverity(MessageSeverity.ERROR);
		// Check
		assertEquals(3, count);
	}

	@Test
	final void count_messages_with_severity_warning_0() {
		// Run
		long count = cut.countMessagesWithSeverity(MessageSeverity.WARNING);
		// Check
		assertEquals(0, count);
	}

	/**
	 * Make sure, it is possible to run the method several times. Avoid side effects
	 * from stream handling.
	 */
	@Test
	final void count_messages_with_severity_info_1_warning_0_error_3() {
		// Prepare
		cut.addMessage(Message.of(MessageId.of(), MessageText.of("Info - 1  test"), MessageSeverity.INFO));
		// Run
		long countInfo = cut.countMessagesWithSeverity(MessageSeverity.INFO);
		long countWarning = cut.countMessagesWithSeverity(MessageSeverity.WARNING);
		long countError = cut.countMessagesWithSeverity(MessageSeverity.ERROR);
		// Check
		assertEquals(1, countInfo);
		assertEquals(0, countWarning);
		assertEquals(3, countError);
	}

	@Test
	final void add_messages_3_messages() {
		// Prepare
		MessageList other = MessageList.of() //
		    .addMessage(Message.of(MessageId.of(), MessageText.of("Info - 2 - test"), MessageSeverity.INFO)) //
		    .addMessage(Message.of(MessageId.of(), MessageText.of("Info - 3 - test"), MessageSeverity.INFO)) //
		    .addMessage(Message.of(MessageId.of(), MessageText.of("Warning - 2 - test"), MessageSeverity.WARNING)); //

		// Run
		MessageList result = cut.addMessages(other);
		// Check
		assertSame(cut, result);
		assertEquals(3, cut.countMessagesWithSeverity(MessageSeverity.ERROR));
		assertEquals(2, cut.countMessagesWithSeverity(MessageSeverity.INFO));
		assertEquals(1, cut.countMessagesWithSeverity(MessageSeverity.WARNING));
	}
}
