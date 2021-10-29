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
import de.lexasoft.common.model.MessagePanel;

/**
 * This Panel is an implementation of @MessagePanel, based on a @JTextArea.
 * <p>
 * It consists of a @JPanel, holding a @JTextArea in Center (@BorderLayout).
 * 
 * @author nierax
 *
 */
@SuppressWarnings("serial")
public class MessagePanelTextAreaImpl extends JPanel implements MessagePanel {
	private JTextArea messageArea;

	/**
	 * Create an object of {@link MessagePanelTextAreaImpl}
	 */
	public MessagePanelTextAreaImpl() {
		setLayout(new BorderLayout(0, 0));
		messageArea = new JTextArea();
		messageArea.setWrapStyleWord(true);
		messageArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		messageArea.setBackground(SystemColor.control);
		messageArea.setEditable(false);
		messageArea.setForeground(Color.RED);
		add(messageArea);
	}

	@Override
	public void pushMessageToTheGUI(Message message) {
		String text = message.getErrorMessage().toString();
		Optional.ofNullable(messageArea.getText()) //
		    .filter(Predicate.not(String::isBlank)) //
		    .ifPresentOrElse(//
		        e -> messageArea.append("\n" + text), //
		        () -> messageArea.setText(text));
		messageArea.setToolTipText(getCompleteText());
	}

	@Override
	public void removeAllMessagesFromTheGUI() {
		messageArea.setText("");
	}

	@Override
	public String getCompleteText() {
		return messageArea.getText();
	}
}
