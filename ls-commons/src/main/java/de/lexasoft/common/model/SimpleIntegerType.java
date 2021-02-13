/**
 * 
 */
package de.lexasoft.common.model;

/**
 * @author admin
 *
 */
public abstract class SimpleIntegerType extends SimpleType<Integer> implements Comparable<SimpleIntegerType> {

	protected SimpleIntegerType(Integer value) {
		super(value);
	}

	@Override
	public int compareTo(SimpleIntegerType other) {
		return value().compareTo(other.value());
	}

}
