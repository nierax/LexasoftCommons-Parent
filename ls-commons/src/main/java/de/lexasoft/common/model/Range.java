package de.lexasoft.common.model;

/**
 * Represents a value, being valid in a range between min and max, inclusive.
 * 
 * @author Axel
 */
public class Range<T extends Number> implements Validator<T> {

	private T min;
	private T max;
	
	public Range(T min, T max) {
		this.min = min;
		this.max = max;
		if(min.doubleValue() > max.doubleValue()) {
			throw new IllegalArgumentException("Min must not be greater than max");
		}
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}

	@Override
	public boolean validate(T value) {
		return ((value.doubleValue() >= min.doubleValue()) && (value.doubleValue() <= max.doubleValue()));
	}

}
