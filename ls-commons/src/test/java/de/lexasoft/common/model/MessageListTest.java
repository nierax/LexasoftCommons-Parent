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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		error1 = Message.of(errorId1, MessageText.of("Message 1 occured"));
		errorId2 = MessageId.of();
		error2 = Message.of(errorId2, MessageText.of("Message 2 occured"));
		errorId3 = MessageId.of("4711");
		error3 = Message.of(errorId3, MessageText.of("Message 3 occured"));

		cut.addMessage(error1);
		cut.addMessage(error2);
		cut.addMessage(error3);
	}

	@Test
	final void find_Errors_By_Id_1() {
		Optional<Message> message = cut.findErrorById(MessageId.of("0815"));
		assertTrue(message.isPresent());
		assertEquals(error1, message.get());
	}

	@Test
	final void find_Errors_By_Id_2() {
		Optional<Message> message = cut.findErrorById(errorId2);
		assertTrue(message.isPresent());
		assertEquals(error2, message.get());
	}

	@Test
	final void find_Errors_By_Id_3() {
		Optional<Message> message = cut.findErrorById(errorId3);
		assertTrue(message.isPresent());
		assertEquals(error3, message.get());
	}

	@Test
	final void find_Errors_By_Id_NotFound() {
		Optional<Message> message = cut.findErrorById(MessageId.of());
		assertTrue(message.isEmpty());
	}

	@Test
	final void remove_error_() {
		cut.removeMessage(error1);
		assertFalse(cut.findErrorById(error1.getErrorId()).isPresent());
	}

	@Test
	final void remove_error_from_id() {
		cut.removeMessage(errorId1);
		assertFalse(cut.findErrorById(errorId1).isPresent());
	}

	@Test
	final void remove_all_errors() {
		// Just make sure, there are some entries.
		assertEquals(3, cut.nrOfErrors());
		// Now remove all of them.
		cut.removeAllErrors();
		// Now there should be nor more errors
		assertEquals(0, cut.nrOfErrors());
		assertFalse(cut.findErrorById(errorId1).isPresent());
	}

}
