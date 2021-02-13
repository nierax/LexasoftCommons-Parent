package de.lexasoft.game;

import de.lexasoft.common.model.SimpleIntegerType;

/**
 * Represents dots of dice.
 * 
 * @author nierax
 *
 */
public class DiceDots extends SimpleIntegerType {

	private DiceDots(Integer dots) {
		super(dots);
	}

	static DiceDots of(Integer dots) {
		return new DiceDots(dots);
	}
}
