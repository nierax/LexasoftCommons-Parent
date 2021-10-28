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

import de.lexasoft.common.model.Message;

/**
 * @author nierax
 *
 */
public interface MessagePanel {

	/**
	 * Displays the given error on the gui.
	 * 
	 * @param message
	 */
	void pushMessageToTheGUI(Message message);

	/**
	 * Removes all errors from the gui.
	 */
	void removeAllMessagesFromTheGUI();

	/**
	 * Get the complete text about all messages.
	 * 
	 * @return
	 */
	String getCompleteText();
}
