package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SimpleTypeTest {

	private SimpleType<Integer> cut;

	/**
	 * Being able to instantiate abstract class in test.
	 */
	class SimpleCUTType<T> extends SimpleType<T> {

		protected SimpleCUTType(T value) {
			super(value);
		}

	}

	@BeforeEach
	void prepareTestCase() {
		cut = new SimpleCUTType<Integer>(3);
	}

	/**
	 * Should be false, if other type.
	 */
	@Test
	void testEqualsOtherType() {
		assertFalse(cut.equals(new SimpleCUTType<String>("me")), "Equals must be false, if another type is used.");
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 4, 5, 6, 7777, -100 })
	void testEqualsFalseIfDifferentIntegerValue(int value) {
		assertFalse(cut.equals(new SimpleCUTType<Integer>(value)),
		    "Equals mus be false, if a different Integer value is used.");
	}

	@Test
	void testEqualsTrueWithSameIntegerValue() {
		assertTrue(cut.equals(new SimpleCUTType<Integer>(3)),
		    "Equals mus be true, if used with an identical integer value.");
	}
}
