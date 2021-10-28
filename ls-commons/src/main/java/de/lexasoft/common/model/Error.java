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
 * Represents an error in any application.
 * 
 * @author nierax
 *
 */
public class Error {

	private ErrorId errorId;
	private ErrorMessage errorMessage;

	/**
	 * Create an error message.
	 * 
	 * @param errorId
	 * @param errorMessage
	 */
	private Error(ErrorId errorId, ErrorMessage errorMessage) {
		super();
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorId
	 */
	public ErrorId getErrorId() {
		return errorId;
	}

	/**
	 * @return the errorMessage
	 */
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Create an error message.
	 * 
	 * @param errorId
	 * @param errorMessage
	 */
	public static final Error of(ErrorId errorId, ErrorMessage errorMessage) {
		return new Error(errorId, errorMessage);
	}

}
