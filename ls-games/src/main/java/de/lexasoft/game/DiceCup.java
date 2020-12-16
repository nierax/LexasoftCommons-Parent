/**
 * 
 */
package de.lexasoft.game;

import java.util.ArrayList;
import java.util.List;

/**
 * A cup of several dices, all with the same range to roll.
 * @author Axel Niering
 *
 */
public class DiceCup {

	private List<Dice> dices;

	/**
	 * Creates a DiceCup with the number of dices given, all in range from min to max.
	 * 
	 * @param nrOfDices Number of dices to add
	 * @param min 
	 * @param max
	 */
	public DiceCup(int nrOfDices, int min, int max) {
		dices = new ArrayList<Dice>();
		for (int i = 0; i < nrOfDices; i++) {
			dices.add(new Dice(min, max));
		}
	}
	
	/**
	 * Creates a DiceCup with the number of dices given, all in range from 1 to 6.
	 * 
	 * @param nrOfDices Number of Dices to add
	 */
	public DiceCup(int nrOfDices) {
		this(nrOfDices, 1, 6);
	}
	
	/**
	 * Rolls all dices in the cup.
	 * @return List of rolled values.
	 */
	public List<Integer> roll() {
		List<Integer> values = new ArrayList<Integer>();
		for (Dice dice : dices) {
			values.add(dice.roll());
		}
		return values;
	}
	
	/**
	 * Export a copy of the list with all dices. The list is a new object, the dices are the identical objects. 
	 * @return New list of dices
	 */
	public List<Dice> getDices() {
		return dices.subList(0, dices.size());
	}
	
	/**
	 * @return Gets the number of dices in the cup.
	 */
	public int numberOfDices() {
		return dices.size();
	}
}