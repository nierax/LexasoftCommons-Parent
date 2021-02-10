/**
 * 
 */
package de.lexasoft.game;

import java.util.Random;

import de.lexasoft.common.model.Range;
import de.lexasoft.common.model.RangeValidator;
import de.lexasoft.common.model.Value;

/**
 * Dice with several numbers in positioning.
 * 
 * @author Axel Niering
 *
 */
public class Dice extends Value<Integer> {

	/**
	 * Keep the generator static to avoid the same seed in several objects.
	 */
	private static final Random generator = new Random();

	private final Range<Integer> range;

	/**
	 * Create a dice, which can roll in several ranges from min to max.
	 * 
	 * @param min
	 * @param max
	 */
	private Dice(Range<Integer> range) {
		super(RangeValidator.of(range));
		this.range = range;
		if ((range.min() <= 0) || (range.max() <= 0)) {
			throw new IllegalArgumentException("Maximum and minimum value must not be 0.");
		}
		if ((range.min() == range.max())) {
			throw new IllegalArgumentException("Maximum and minimum value must differ.");
		}
	}

	/**
	 * Rolls the dice between the minimum and maximum value, defined via the
	 * constructor.
	 * 
	 * @return Returns the rolled value.
	 */
	public int roll() {
		int value = generator.nextInt(max() + 1 - min());
		value += min();
		setValue(value);
		return value;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRolled() {
		return hasValue();
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
