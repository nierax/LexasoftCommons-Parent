package de.lexasoft.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import de.lexasoft.common.model.Range;

class DiceConstructionTest {

	/**
	 * Creation correct, if the fields are correctly given?
	 */
	@Test
	void testConstructionOk() {
		Dice dice = Dice.of(Range.of(1, 6));
		assertNotNull(dice, "Dice not created correctly");
		assertFalse(dice.hasValue(), "Dice must be confirmed being not rolled after creation");
	}

	/**
	 * Creation must fail, when the maximum value is smaller than the minimum value.
	 */
	@Test
	void testConstructionMaxSmallerMin() {
		assertThrows(IllegalArgumentException.class, () -> {
			Dice.of(Range.of(6, 1));
		});
	}

	/**
	 * Creation must fail, when the maximum value equals the minimum value.
	 */
	@Test
	void testConstructionMaxEqualsMin() {
		assertThrows(IllegalArgumentException.class, () -> {
			Dice.of(Range.of(1, 1));
		});
	}

	/**
	 * Creation must fail, when the minimum and maximum value are smaller than zero.
	 */
	@Test
	void testConstructionMaxMinUnderZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			Dice.of(Range.of(-6, -1));
		}, "Both minimum and maximum value must be greater than zero.");
	}

	/**
	 * Creation must fail, when the minimum value is smaller than zero.
	 */
	@Test
	void testConstructionMinUnderZero() {
		// Smaller than zero
		assertThrows(IllegalArgumentException.class, () -> {
			Dice.of(Range.of(-1, 6));
		}, "Minimum value must not be smaller than zero.");

		// Equals zero
		assertThrows(IllegalArgumentException.class, () -> {
			Dice.of(Range.of(0, 6));
		}, "Minimum value must not be 0.");
	}
}
