/**
 * 
 */
package de.lexasoft.common.model;

/**
 * Represents a range with a minimum and maximum value
 * 
 * @author nierax
 */
public class Range<T extends Number> {

	private final T min;
	private final T max;

	private Range(T min, T max) {
		this.min = min;
		this.max = max;
		if (min.doubleValue() > max.doubleValue()) {
			throw new IllegalArgumentException("Min must not be greater than max");
		}
	}

	public T min() {
		return min;
	}

	public T max() {
		return max;
	}

	public static <T extends Number> Range<T> of(T min, T max) {
		return new Range<T>(min, max);
	}

}
