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

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author nierax
 *
 */
class ErrorIdTest {

	private ErrorId cut;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cut = ErrorId.of("0815");
	}

	private static final Stream<Arguments> equals_not_null() {
		return Stream.of( //
		    Arguments.of(ErrorId.of("0815"), true), //
		    Arguments.of(ErrorId.of(), false), //
		    Arguments.of(ErrorId.of("4711"), false), //
		    Arguments.of("other type", false));
	}

	/**
	 * Test method for
	 * {@link de.lexasoft.common.model.ErrorId#equals(java.lang.Object)}. In case,
	 * equals should return true.
	 */
	@ParameterizedTest
	@MethodSource
	final void equals_not_null(Object obj, boolean expected) {
		assertEquals(expected, cut.equals(obj));
	}

	/**
	 * The same object should be true
	 */
	@Test
	final void equals_same_object() {
		assertTrue(cut.equals(cut));
	}

	/**
	 * Null should lead to false without NPE.
	 */
	@Test
	final void equals_null() {
		assertFalse(cut.equals(null));
	}

}
