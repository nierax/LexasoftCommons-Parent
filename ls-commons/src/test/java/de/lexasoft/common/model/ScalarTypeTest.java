package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScalarTypeTest {

	private ScalarType<Integer> cut;

	@BeforeEach
	void prepareTestCase() {
		cut = new ScalarType<Integer>(3);
	}

	/**
	 * Should be false, if other type.
	 */
	@Test
	void testEqualsOtherType() {
		assertFalse(cut.equals(new ScalarType<String>("me")), "Equals must be false, if another type is used.");
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 4, 5, 6, 7777, -100 })
	void testEqualsFalseIfDifferentIntegerValue(int value) {
		assertFalse(cut.equals(new ScalarType<Integer>(value)),
		    "Equals mus be false, if a different Integer value is used.");
	}

	@Test
	void testEqualsTrueWithSameIntegerValue() {
		assertTrue(cut.equals(new ScalarType<Integer>(3)), "Equals mus be true, if used with an identical integer value.");
	}
}
