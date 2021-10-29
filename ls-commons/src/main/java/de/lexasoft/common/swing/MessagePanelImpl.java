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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Optional;
import java.util.function.Predicate;

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
		setLayout(new BorderLayout(0, 0));
		errorArea = new JTextArea();
		errorArea.setWrapStyleWord(true);
		errorArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		errorArea.setBackground(SystemColor.control);
		errorArea.setEditable(false);
		errorArea.setForeground(Color.RED);
		add(errorArea);
	}

	JTextArea getErrorArea() {
		return errorArea;
	}

	@Override
	public void pushMessageToTheGUI(Message message) {
		String text = message.getErrorMessage().toString();
		Optional.ofNullable(errorArea.getText()) //
		    .filter(Predicate.not(String::isBlank)) //
		    .ifPresentOrElse(//
		        e -> errorArea.append("\n" + text), //
		        () -> errorArea.setText(text));
		errorArea.setToolTipText(getCompleteText());
	}

	@Override
	public void removeAllMessagesFromTheGUI() {
		errorArea.setText("");
	}

	@Override
	public String getCompleteText() {
		return errorArea.getText();
	}
}
