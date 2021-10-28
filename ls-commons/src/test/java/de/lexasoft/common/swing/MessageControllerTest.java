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
package de.lexasoft.common.swing;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.lexasoft.common.model.Message;
import de.lexasoft.common.model.MessageId;
import de.lexasoft.common.model.MessageList;
import de.lexasoft.common.model.MessageText;

/**
 * @author nierax
 *
 */
@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

	private MessageController cut;
	@Mock
	private MessagePanel view;
	private MessageList errors;
	private Message error1;
	private Message error2;
	private Message error3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cut = new MessageController(view);
		errors = MessageList.of();

		error1 = Message.of(MessageId.of(), MessageText.of("Message 1 occured"));
		error2 = Message.of(MessageId.of(), MessageText.of("Message 2 occured"));
		error3 = Message.of(MessageId.of(), MessageText.of("Message 3 occured"));
		errors.addMessage(error1);
		errors.addMessage(error2);
		errors.addMessage(error3);
	}

	/**
	 * Test method for
	 * {@link de.lexasoft.common.swing.MessageController#displayMessage(java.lang.Error)}.
	 */
	@Test
	final void display_message_1_message() {
		cut.displayMessage(error1);
		verify(view, times(1)).removeAllMessagesFromTheGUI();
		verify(view, times(1)).pushMessageToTheGUI(error1);
	}

	/**
	 * Test method for
	 * {@link de.lexasoft.common.swing.MessageController#displayMessage(java.lang.Error)}.
	 */
	@Test
	final void display_message_3_messages() {
		cut.displayMessage(error1);
		cut.displayMessage(error2);
		cut.displayMessage(error3);
		verify(view, times(3)).removeAllMessagesFromTheGUI();
		verify(view, times(3)).pushMessageToTheGUI(error1);
		verify(view, times(2)).pushMessageToTheGUI(error2);
		verify(view, times(1)).pushMessageToTheGUI(error3);
	}

	/**
	 * Test method for
	 * {@link de.lexasoft.common.swing.MessageController#unDisplayMessage(java.lang.Error)}.
	 */
	@Test
	final void testUnDisplayError() {
		cut.displayMessage(error1);
		cut.displayMessage(error2);
		reset(view);

		cut.unDisplayMessage(error2);
		verify(view, times(1)).removeAllMessagesFromTheGUI();
		verify(view, times(1)).pushMessageToTheGUI(error1);
	}

}
