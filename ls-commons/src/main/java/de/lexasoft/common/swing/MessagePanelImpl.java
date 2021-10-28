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

import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.lexasoft.common.model.Message;

/**
 * @author nierax
 *
 */
@SuppressWarnings("serial")
public class MessagePanelImpl extends JPanel implements MessagePanel {
	private JTextArea errorArea;

	/**
	 * 
	 */
	public MessagePanelImpl() {

		errorArea = new JTextArea();
		add(errorArea);
	}

	JTextArea getErrorArea() {
		return errorArea;
	}

	@Override
	public void pushMessageToTheGUI(Message message) {
		String text = message.getErrorMessage().toString();
		Optional.ofNullable(errorArea.getText()) //
		    .ifPresentOrElse(//
		        e -> errorArea.append("\n" + text), //
		        () -> errorArea.setText(text));
	}

	@Override
	public void removeAllMessagesFromTheGUI() {
		errorArea.setText("");
	}
}
