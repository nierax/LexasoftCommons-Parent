/**
 * 
 */
package de.lexasoft.game;

import java.util.Random;

import de.lexasoft.common.model.Range;

/**
 * Dice with several numbers in positioning.
 * 
 * @author nierax
 *
 */
public class Dice {

	/**
	 * Keep the generator static to avoid the same seed in several objects.
	 */
	private static final Random generator = new Random();

	private final Range<Integer> range;

	/**
	 * The rolled dots
	 */
	private DiceDots dots;

	/**
	 * Create a dice, which can roll in several ranges from min to max.
	 * 
	 * @param min
	 * @param max
	 */
	private Dice(Range<Integer> range) {
		this.range = range;
		if ((range.min() < 0) || (range.max() < 0)) {
			throw new IllegalArgumentException("Maximum and minimum value must not be below 0.");
		}
		if ((range.min() == range.max())) {
			throw new IllegalArgumentException("Maximum and minimum value must differ.");
		}
		dots = null;
	}

	/**
	 * Rolls the dice between the minimum and maximum value, defined via the
	 * constructor.
	 * 
	 * @return Returns the rolled value.
	 */
	public DiceDots roll() {
		int value = generator.nextInt(max() + 1 - min());
		value += min();
		dots = DiceDots.of(value);
		return dots;
	}

	/**
	 * 
	 * @return True if rolled (at least once), false otherwise.
	 */
	public boolean isRolled() {
		return dots != null;
	}

	/**
	 * @return the min
	 */
	int min() {
		return range.min();
	}

	/**
	 * @return the max
	 */
	int max() {
		return range.max();
	}

	/**
	 * New dice which rolls from range.min to range.max.
	 * 
	 * @param range
	 * @return New Dice instance
	 */
	public static Dice of(Range<Integer> range) {
		return new Dice(range);
	}

	/**
	 * New dice which rolls from 1 to 6.
	 * 
	 * @param range
	 * @return New Dice instance
	 */
	public static Dice of() {
		return new Dice(Range.of(1, 6));
	}
}
