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

import java.text.ParseException;

import javax.swing.JFormattedTextField;

/**
 * Extends the @JFormattedTextField by a type safe get method for the real
 * value.
 * <p>
 * To avoid differences between the versions of converting, the method uses the
 * given format used in the text field.
 * 
 * @author nierax
 *
 */
@SuppressWarnings("serial")
public class LSJFormattedTextField<T> extends JFormattedTextField {

	/**
	 * @param formatter The formatter to be used
	 */
	public LSJFormattedTextField(AbstractFormatter formatter) {
		super(formatter);
	}

	/**
	 * Get the original value type safe.
	 * 
	 * @return The original value.
	 * @throws ParseException If the value can not be parsed.
	 */
	@SuppressWarnings("unchecked")
	public T getBasicValue() throws ParseException {
		return (T) getFormatter().stringToValue(getText());
	}

	/**
	 * Sets the text in the input field type safe from the original value.
	 * 
	 * @param value The value to set.
	 * @throws ParseException If the value can not be transferred to String.
	 */
	public void setBasicValue(T value) throws ParseException {
		setText(getFormatter().valueToString(value));
	}

}
