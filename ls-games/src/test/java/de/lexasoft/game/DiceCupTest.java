package de.lexasoft.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.lexasoft.common.model.Range;

class DiceCupTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstruct() {
		DiceCup cup = DiceCup.of(5, Range.of(1, 6));
		assertNotNull(cup, "Object not created");
		int nrOfDices = cup.numberOfDices();
		assertEquals(5, nrOfDices, "Number of dices not correct.");
		System.out.println(String.format("Cup constructed with %s dices", nrOfDices));
	}

	@Test
	void testRoll() {
		DiceCup cup = DiceCup.of(5, Range.of(1, 6));
		List<DiceDots> values = cup.roll();
		assertNotNull(values, "List of results mus not be null.");
		assertEquals(5, values.size(), "Size of values must be equal to number of dices in cup.");
		for (DiceDots dots : values) {
			System.out.println("Value: " + dots);
			assertTrue((dots.value() <= 6) && (dots.value() >= 1),
			    String.format("Value %s not in range between %s and %s", dots, 1, 6));
		}
	}

}
