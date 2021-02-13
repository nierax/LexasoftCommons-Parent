/**
 * 
 */
package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author nierax
 *
 */
class RangeTest {

	private static Stream<Arguments> testConstructorValidArguments() {
		return Stream.of(Arguments.of(0, 4), Arguments.of(4, 5), Arguments.of(-5, 5), Arguments.of(5, 5));
	}

	/**
	 * Constructor should accept values, if min is lower than max or min is equal to
	 * max.
	 * 
	 * @param min
	 * @param max
	 */
	@ParameterizedTest
	@MethodSource
	void testConstructorValidArguments(Integer min, Integer max) {
		Range<Integer> cut = Range.of(min, max);
		assertEquals(min, cut.min(), "Minimal value was not set correctly.");
		assertEquals(max, cut.max(), "Maximal value was not set correctly.");
	}

	private static Stream<Arguments> testConstructorInvalidArguments() {
		return Stream.of(Arguments.of(4, 0), Arguments.of(5, 4), Arguments.of(5, -5));
	}

	/**
	 * Constructor must not accept values, if min is greater than max.
	 * 
	 * @param min
	 * @param max
	 */
	@ParameterizedTest
	@MethodSource
	void testConstructorInvalidArguments(Integer min, Integer max) {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(min, max);
		});
	}

}
