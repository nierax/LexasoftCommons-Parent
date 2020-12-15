/**
 * 
 */
package de.lexasoft.game;

import java.util.Random;

/**
 * Dice with several numbers in positioning.
 * 
 * @author Axel Niering
 *
 */
public class Dice {
	
	/**
	 * Exception, thrown to signalize, that there was something wrong with the dice.
	 * @author Axel Niering
	 */
	@SuppressWarnings("serial")
	class DiceException extends RuntimeException {
		public DiceException(String message) {
			super(message);
		}
	}

	private int value;
	private int min;
	private int max;
	
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
		if(max <= min) {
			throw new DiceException("Maximum value must be greater than minimum.");
		}
		if((min<=0) || (max<=0)) {
			throw new DiceException("Maximum and minimum value must not be 0.");
		}
		this.min = min;
		this.max = max;
		this.value = -1;
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
		value = generator.nextInt(max + 1 - min);
		value += min;
		return value;
	}
	
	/**
	 * @return The current value of the dice, without rolling newly.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the min
	 */
	int getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	int getMax() {
		return max;
	}

}
