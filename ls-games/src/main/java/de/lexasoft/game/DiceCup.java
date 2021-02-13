/**
 * 
 */
package de.lexasoft.game;

import java.util.ArrayList;
import java.util.List;

import de.lexasoft.common.model.Range;

/**
 * A cup of several dices, all with the same range to roll.
 * 
 * @author nierax
 *
 */
public class DiceCup {

	private final List<Dice> dices;

	/**
	 * Creates a DiceCup with the number of dices given, all in range from min to
	 * max.
	 * 
	 * @param nrOfDices Number of dices to add
	 * @param min
	 * @param max
	 */
	private DiceCup(int nrOfDices, Range<Integer> range) {
		dices = new ArrayList<Dice>();
		for (int i = 0; i < nrOfDices; i++) {
			dices.add(Dice.of(range));
		}
	}

	/**
	 * Rolls all dices in the cup.
	 * 
	 * @return List of rolled values.
	 */
	public List<DiceDots> roll() {
		List<DiceDots> values = new ArrayList<>();
		for (Dice dice : dices) {
			values.add(dice.roll());
		}
		return values;
	}

	/**
	 * Export a copy of the list with all dices. The list is a new object, the dices
	 * are the identical objects.
	 * 
	 * @return New list of dices
	 */
	public List<Dice> dices() {
		return dices.subList(0, dices.size());
	}

	/**
	 * @return Gets the number of dices in the cup.
	 */
	public int numberOfDices() {
		return dices.size();
	}

	/**
	 * Create new DiceCup
	 * 
	 * @param nrOfDices
	 * @param range     Rolls from min to max
	 * @return
	 */
	public static DiceCup of(int nrOfDices, Range<Integer> range) {
		return new DiceCup(nrOfDices, range);
	}

	/**
	 * Create new DiceCup with dices, which roll from 1 to 6.
	 * 
	 * @param nrOfDices
	 * @return
	 */
	public static DiceCup of(int nrOfDices) {
		return new DiceCup(nrOfDices, Range.of(1, 6));
	}
}