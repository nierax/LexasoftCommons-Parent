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
 * Controller for use with @MessagePanel
 * 
 * @author nierax
 *
 */
public class MessageController {

	private MessagePanel view;
	private MessageList errors;

	/**
	 * 
	 */
	public MessageController(MessagePanel view) {
		this.view = view;
		this.errors = MessageList.of();
	}

	public void displayMessage(Message message) {
		errors.addMessage(message);
		outMessages();
	}

	public void unDisplayMessage(Message message) {
		errors.removeMessage(message);
		outMessages();
	}

	public void unDisplayMessage(MessageId messageId) {
		errors.removeMessage(messageId);
		outMessages();
	}

	/**
	 * 
	 */
	private void outMessages() {
		view.removeAllMessagesFromTheGUI();
		errors.stream().forEach(view::pushMessageToTheGUI);
	}

}
