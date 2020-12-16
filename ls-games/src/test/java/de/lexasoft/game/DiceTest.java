package de.lexasoft.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {
	
	private Dice cut;
	
	private static final int minimum = 5;
	private static final int maximum = 50;

	@BeforeEach
	void setUp() throws Exception {
		cut = new Dice(minimum,maximum);
	}

	@AfterEach
	void tearDown() throws Exception {
		cut = null;
	}

	@Test
	void testRoll() {
		int value;
		value = cut.roll();
		System.out.println("Value: " + value);
		assertTrue((value>=minimum) && (value<=maximum), String.format("The value \"%s\" of the dice is note between minimum and maximum", value));
	}

	@Test
	void testDispersion() {
		int nrOfRolls=20*(maximum-minimum);
		int[] values = new int[nrOfRolls];
		for(int i=0; i<nrOfRolls; i++) {
		  values[i]	= cut.roll();
		}
		Arrays.sort(values);
		int min = values[0];
		int max = values[nrOfRolls-1];
		System.out.println("minimalvalue = " + min);
		System.out.println("maximalvalue = " + max);
		assertEquals(minimum, min, "Minimalwert stimmt nicht");
		assertEquals(maximum, max, "Maximalwert stimmt nicht");
	}
}
