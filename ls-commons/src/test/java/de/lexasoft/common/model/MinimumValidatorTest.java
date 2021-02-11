/**
 * 
 */
package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests the minimum value validator.
 * 
 * @author Axel
 */
class MinimumValidatorTest {

	private Validator<Integer> cut;

	@BeforeEach
	void prepareTest() {
		// cut with minimum value 1
		cut = MinimumValidator.of(Integer.valueOf(1));
	}

	/**
	 * Test method for
	 * {@link de.lexasoft.common.model.MinimumValidator#validate(java.lang.Number)}.
	 * 
	 * Digits bigger than minimum value (1) must pass with true as result.
	 */
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 10, 10000 })
	void testValidateOk(int value) {
		assertTrue(cut.validate(Integer.valueOf(value)), "Must pass as being above the minimum.");
	}

	/**
	 * Test method for
	 * {@link de.lexasoft.common.model.MinimumValidator#validate(java.lang.Number)}.
	 * 
	 * Digits lower than minimum value (1) must be rejected with false as result.
	 */
	@ParameterizedTest
	@ValueSource(ints = { 0, -1, -3, -4, -10, -10000 })
	void testValidateTooSmall(int value) {
		assertFalse(cut.validate(Integer.valueOf(value)), "Must be rejected, as being smaller than minimum");
	}

}
