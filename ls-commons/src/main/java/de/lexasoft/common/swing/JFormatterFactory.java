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

import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.text.NumberFormatter;

/**
 * Helps to create formatter, f.ex. for @JFormattedTextField
 * 
 * @see LSJFormattedTextField
 * 
 * @author nierax
 *
 */
public class JFormatterFactory {

	/**
	 * Should not be instantiated.
	 */
	private JFormatterFactory() {
	}

	/**
	 * Creates a formatter for use f.ex. with @JFormattedTextField, defining an
	 * integer range.
	 * <p>
	 * Only integer values are accepted:
	 * <ul>
	 * <li>above zero</li>
	 * <li>below the maximum integer value</li>
	 * <li>grouping (f.ex. 5.000) is not used
	 * <li>invalid characters will not be accepted</li>
	 * <li>changes will be committed at focus lost</li>
	 * </ul>
	 * 
	 * @return
	 */
	public static NumberFormatter createIntegerFormatter() {
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setGroupingUsed(false);
		NumberFormatter formatter = new NumberFormatter(nf);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(false);
		return formatter;
	}

	/**
	 * Creates a formatter for use f.ex. with @JFormattedTextField, defining a
	 * double range.
	 * <p>
	 * Only double values are accepted:
	 * <ul>
	 * <li>rounding will be up with the most possible number of fractions</li>
	 * <li>grouping (f.ex. 5.000) is not used
	 * <li>invalid characters will not be accepted</li>
	 * <li>changes will be committed at focus lost</li>
	 * </ul>
	 * 
	 * @return
	 */
	public static NumberFormatter createDoubleFormatter() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setRoundingMode(RoundingMode.UP);
		nf.setMaximumFractionDigits(Integer.MAX_VALUE);
		nf.setGroupingUsed(false);
		NumberFormatter formatter = new NumberFormatter(nf);
		formatter.setValueClass(Double.class);
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(false);
		return formatter;
	}

}
