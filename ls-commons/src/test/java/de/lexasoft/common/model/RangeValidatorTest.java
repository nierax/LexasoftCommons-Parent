package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RangeValidatorTest {

	private Validator<Integer> cut;

	@BeforeEach
	void prepareTestcase() {
		cut = RangeValidator.of(Range.of(0, 3));
	}

	private static Stream<Arguments> testValidate() {
		return Stream.of(Arguments.of(0, true), Arguments.of(1, true), Arguments.of(2, true), Arguments.of(3, true),
		    Arguments.of(4, false), Arguments.of(-1, false));
	}

	@ParameterizedTest
	@MethodSource
	void testValidate(Integer value, Boolean valid) {
		assertEquals(valid, cut.validate(value));
	}
}
