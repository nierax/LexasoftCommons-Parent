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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This list collects errors and offers methods to acces them.
 * 
 * @author nierax
 *
 */
public class ErrorList {

	private List<Error> errors;

	private ErrorList() {
		errors = new ArrayList<>();
	}

	/**
	 * Add the given error to the list.
	 * 
	 * @param error The error to add
	 * @return Reference to this list for fluent API writing
	 */
	public ErrorList addError(Error error) {
		this.errors.add(error);
		return this;
	}

	/**
	 * Remove the given error from the list.
	 * <p>
	 * The list remains unchanged, if there is not such error in the list.
	 * 
	 * @param error The error to remove.
	 * @return
	 */
	public ErrorList removeError(Error error) {
		errors.remove(error);
		return this;
	}

	/**
	 * Remove the error with the given id from the list.
	 * <p>
	 * Does nothing, if there is no error with the given id in the list.
	 * 
	 * @param errorId The error id to remove
	 * @return The error list
	 */
	public ErrorList removeError(ErrorId errorId) {
		findErrorById(errorId).ifPresent(errors::remove);
		return this;
	}

	/**
	 * Find the first error, connected to the given id.
	 * 
	 * @param errorId The error id to find
	 * @return An @Optional with the error, being empty, if there was no error with
	 *         that error id.
	 */
	public Optional<Error> findErrorById(ErrorId errorId) {
		return stream().filter(e -> e.getErrorId().equals(errorId)).findFirst();
	}

	/**
	 * 
	 * @return The number of errors in the list.
	 */
	public int nrOfErrors() {
		return errors.size();
	}

	/**
	 * Removes all errors from the list.
	 * 
	 * @return The list, now being empty.
	 */
	public ErrorList removeAllErrors() {
		errors.clear();
		return this;
	}

	/**
	 * 
	 * @return The stream of errors
	 */
	public Stream<Error> stream() {
		return errors.stream();
	}

	/**
	 * Create an empty error list
	 * 
	 * @return Empty error list
	 */
	public final static ErrorList of() {
		return new ErrorList();
	}

}
