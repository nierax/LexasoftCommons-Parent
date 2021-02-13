package de.lexasoft.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.lexasoft.common.model.Range;

class DiceTest {

	private Dice cut;

	private static final DiceDots minimum = DiceDots.of(5);
	private static final DiceDots maximum = DiceDots.of(50);

	@BeforeEach
	void setUp() throws Exception {
		cut = Dice.of(Range.of(minimum.value(), maximum.value()));
	}

	@AfterEach
	void tearDown() throws Exception {
		cut = null;
	}

	@Test
	void testRoll() {
		DiceDots dots;
		dots = cut.roll();
		System.out.println("Value: " + dots);
		assertTrue((dots.value() >= minimum.value()) && (dots.value() <= maximum.value()),
		    String.format("The value \"%s\" of the dice is note between minimum and maximum", dots));
	}

	@Test
	void testDispersion() {
		int nrOfRolls = 20 * (maximum.value() - minimum.value());
		DiceDots[] values = new DiceDots[nrOfRolls];
		for (int i = 0; i < nrOfRolls; i++) {
			values[i] = cut.roll();
		}
		Arrays.sort(values);
		DiceDots min = values[0];
		DiceDots max = values[nrOfRolls - 1];
		System.out.println("minimalvalue = " + min);
		System.out.println("maximalvalue = " + max);
		assertEquals(minimum, min, "Minimalwert stimmt nicht");
		assertEquals(maximum, max, "Maximalwert stimmt nicht");
	}
}
