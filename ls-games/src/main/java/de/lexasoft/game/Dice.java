/**
 * 
 */
package de.lexasoft.game;

import java.util.Random;

import de.lexasoft.common.model.Range;
import de.lexasoft.common.model.Value;

/**
 * Dice with several numbers in positioning.
 * 
 * @author Axel Niering
 *
 */
public class Dice extends Value<Integer>{
	
	/**
	 * Keep the generator static to avoid the same seed in several objects.
	 */
	private static Random generator = new Random();
	
	/**
	 * Create a dice, which can roll in several ranges from min to max.
	 * <p>
	 * Initial value is -1, signalizing, that the role is not being rolled yet.
	 * @param min
	 * @param max
	 */
	public Dice(int min, int max) {
		super(new Range<Integer>(min, max), -1);
		if((min<=0) || (max<=0)) {
			throw new IllegalArgumentException("Maximum and minimum value must not be 0.");
		}
		if((min == max)) {
			throw new IllegalArgumentException("Maximum and minimum value must differ.");
		}
	}
	
	/**
	 * Create a dice, which can roll from 1 to 6.
	 */
	public Dice() {
		this(1,6);
	}
	
	/**
	 * Rolls the dice between the minimum and maximum value, defined via the constructor.
	 * @return Returns the rolled value.
	 */
	public int roll() {
		int value = generator.nextInt(getMax() + 1 - getMin());
		value += getMin();
		setValue(value);
		return value;
	}
	
	/**
	 * @return the min
	 */
	int getMin() {
		return ((Range<Integer>)getValidator()).getMin();
	}

	/**
	 * @return the max
	 */
	int getMax() {
		return ((Range<Integer>)getValidator()).getMax();
	}

}
