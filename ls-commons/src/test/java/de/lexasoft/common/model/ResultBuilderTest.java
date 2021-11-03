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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author nierax
 *
 */
class ResultBuilderTest {

	ResultBuilder<String> cut;

	private Message message1;
	private Message message2;
	private Message message3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cut = ResultBuilder.of("test");
		message1 = Message.of(MessageId.of("test01"), MessageText.of("Error test"), MessageSeverity.ERROR);
		message2 = Message.of(MessageId.of("test02"), MessageText.of("Warning test"), MessageSeverity.WARNING);
		message3 = Message.of(MessageId.of("test03"), MessageText.of("Info test"), MessageSeverity.INFO);
	}

	@Test
	final void build_value_and_1_message() {
		// Run
		Result<String> result = cut//
		    .withMessage(message1) //
		    .build();
		// Check
		assertNotNull(result);
		assertEquals("test", result.get());
		assertEquals(1, result.getMessages().countMessagesWithSeverity(MessageSeverity.ERROR));
		assertEquals("Error test", result.getMessages().//
		    findMessageById(MessageId.of("test01"))//
		    .get().getText().toString());
	}

	@Test
	final void build_value_and_3_messages() {
		// Run
		Result<String> result = cut//
		    .withMessage(message1) //
		    .withMessage(message2) //
		    .withMessage(message3) //
		    .build();
		// Check
		assertNotNull(result);
		assertEquals("test", result.get());
		assertEquals(1, result.getMessages().countMessagesWithSeverity(MessageSeverity.ERROR));
		assertEquals(1, result.getMessages().countMessagesWithSeverity(MessageSeverity.WARNING));
		assertEquals(1, result.getMessages().countMessagesWithSeverity(MessageSeverity.INFO));
		assertEquals("Error test", result.getMessages()//
		    .findMessageById(MessageId.of("test01"))//
		    .get().getText().toString());
		assertEquals("Warning test", result.getMessages()//
		    .findMessageById(MessageId.of("test02"))//
		    .get().getText().toString());
		assertEquals("Info test", result.getMessages()//
		    .findMessageById(MessageId.of("test03"))//
		    .get().getText().toString());
	}

	@Test
	final void with_message_null() {
		// Run
		Result<String> result = cut.withMessage(null).build();
		// Check
		assertNotNull(result);
		assertEquals(0, result.getMessages().nrOfMessages());
	}

	@Test
	final void with_messages_null() {
		// Run
		Result<String> result = cut.withMessages(null).build();
		// Check
		assertNotNull(result);
		assertEquals(0, result.getMessages().nrOfMessages());
	}

	@Test
	final void build_with_messages() {
		// Prepare
		MessageList messages = MessageList.of()//
		    .addMessage(message1) //
		    .addMessage(message2) //
		    .addMessage(message3);
		// Run
		Result<String> result = cut.withMessages(messages).build();
		assertEquals(1, result.getMessages().countMessagesWithSeverity(MessageSeverity.ERROR));
		assertEquals(1, result.getMessages().countMessagesWithSeverity(MessageSeverity.WARNING));
		assertEquals(1, result.getMessages().countMessagesWithSeverity(MessageSeverity.INFO));
		assertEquals("Error test", result.getMessages()//
		    .findMessageById(MessageId.of("test01"))//
		    .get().getText().toString());
		assertEquals("Warning test", result.getMessages()//
		    .findMessageById(MessageId.of("test02"))//
		    .get().getText().toString());
		assertEquals("Info test", result.getMessages()//
		    .findMessageById(MessageId.of("test03"))//
		    .get().getText().toString());
	}

}
