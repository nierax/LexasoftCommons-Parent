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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author nierax
 *
 */
class ErrorListTest {

	private ErrorList cut;

	private Error error1;
	private ErrorId errorId1;
	private Error error2;
	private ErrorId errorId2;
	private Error error3;
	private ErrorId errorId3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cut = ErrorList.of();
		errorId1 = ErrorId.of("0815");
		error1 = Error.of(errorId1, ErrorMessage.of("Error 1 occured"));
		errorId2 = ErrorId.of();
		error2 = Error.of(errorId2, ErrorMessage.of("Error 2 occured"));
		errorId3 = ErrorId.of("4711");
		error3 = Error.of(errorId3, ErrorMessage.of("Error 3 occured"));

		cut.addError(error1);
		cut.addError(error2);
		cut.addError(error3);
	}

	@Test
	final void find_Errors_By_Id_1() {
		Optional<Error> error = cut.findErrorById(ErrorId.of("0815"));
		assertTrue(error.isPresent());
		assertEquals(error1, error.get());
	}

	@Test
	final void find_Errors_By_Id_2() {
		Optional<Error> error = cut.findErrorById(errorId2);
		assertTrue(error.isPresent());
		assertEquals(error2, error.get());
	}

	@Test
	final void find_Errors_By_Id_3() {
		Optional<Error> error = cut.findErrorById(errorId3);
		assertTrue(error.isPresent());
		assertEquals(error3, error.get());
	}

	@Test
	final void find_Errors_By_Id_NotFound() {
		Optional<Error> error = cut.findErrorById(ErrorId.of());
		assertTrue(error.isEmpty());
	}

	@Test
	final void remove_error_() {
		cut.removeError(error1);
		assertFalse(cut.findErrorById(error1.getErrorId()).isPresent());
	}

	@Test
	final void remove_error_from_id() {
		cut.removeError(errorId1);
		assertFalse(cut.findErrorById(errorId1).isPresent());
	}

}
