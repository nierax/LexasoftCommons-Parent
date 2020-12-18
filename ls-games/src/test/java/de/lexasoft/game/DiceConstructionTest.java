package de.lexasoft.game;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DiceConstructionTest {
	
	/**
	 * Creation correct, if the fields are correctly given?
	 */
	@Test
	void testConstructionOk() {
		Dice dice = new Dice(1,6);
		assertNotNull(dice, "Dice not created correctly");
		assertEquals(-1, dice.getValue(), "Value must be -1 initially");
	}

	/**
	 * Creation must fail, when the maximum value is smaller than the minimum value.
	 */
	@Test
	void testConstructionMaxSmallerMin() {
			assertThrows(IllegalArgumentException.class, () -> {
			new Dice(6,1);
		});
	}

	/**
	 * Creation must fail, when the maximum value equals the minimum value.
	 */
	@Test
	void testConstructionMaxEqualsMin() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Dice(1,1);
		});
	}

	/**
	 * Creation must fail, when the minimum and maximum value are smaller than zero.
	 */
	@Test
	void testConstructionMaxMinUnderZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Dice(-6,-1);
		}, "Both minimum and maximum value must be greater than zero.");
	}

	/**
	 * Creation must fail, when the minimum value is smaller than zero.
	 */
	@Test
	void testConstructionMinUnderZero() {
		// Smaller than zero
		assertThrows(IllegalArgumentException.class, () -> {
			new Dice(-1,6);
		}, "Minimum value must not be smaller than zero.");

		// Equals zero
		assertThrows(IllegalArgumentException.class, () -> {
			new Dice(0,6);
		}, "Minimum value must not be 0.");
	}
}
