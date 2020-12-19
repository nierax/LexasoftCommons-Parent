package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValueTest {

	/**
	 * Validator says ok, so the value must be set.
	 */
	@Test
	void testSetValueOk() {
		Value<Integer> cut = new Value<Integer>((value) -> {return true;});
		cut.setValue(2);
		assertEquals(cut.getValue(), 2);
	}

	/**
	 * Validator says not ok, an exception is expected.
	 */
	@Test
	void testSetValueNok() {
		Value<Integer> cut = new Value<Integer>((value) -> {return false;});
		assertThrows(IllegalArgumentException.class, () -> {
			cut.setValue(2);
		});
	}

}
