package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RangeTest {

	/**
	 * Max is greater than min. Valid.
	 */
	@Test
	void testConstructorValid() {
		Range<Integer> range = new Range<Integer>(0, 1);
		assertEquals(0, range.getMin());
		assertEquals(1, range.getMax());
	}

	/**
	 * Min is greater than max. Not valid. 
	 */
	@Test
	void testConstructorInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Range<Integer>(1,0);
		});
	}
	
	/**
	 * The value is within the range, so it is valid. 
	 */
	@Test 
	void testValidateInRange( ) {
		Range<Integer> range = new Range<Integer>(0, 2);
		assertTrue(range.validate(1), "The value within the range must be considered valid");
	}

	/**
	 * The value is min, so it is valid. 
	 */
	@Test 
	void testValidateMin( ) {
		Range<Integer> range = new Range<Integer>(0, 2);
		assertTrue(range.validate(0), "The value equal to min must be considered valid");
	}

	/**
	 * The value is max, so it is valid. 
	 */
	@Test 
	void testValidateMax( ) {
		Range<Integer> range = new Range<Integer>(0, 2);
		assertTrue(range.validate(2), "The value equal to max must be considered valid");
	}

	/**
	 * The value is lower than min, so it is invalid. 
	 */
	@Test 
	void testValidateUnderMin( ) {
		Range<Integer> range = new Range<Integer>(1, 3);
		assertFalse(range.validate(0), "The value below min must be considered invalid");
	}

	/**
	 * The value is greater than max, so it is invalid. 
	 */
	@Test 
	void testValidateAboveMax( ) {
		Range<Integer> range = new Range<Integer>(1, 3);
		assertFalse(range.validate(4), "The value above max must be considered invalid");
	}

}
