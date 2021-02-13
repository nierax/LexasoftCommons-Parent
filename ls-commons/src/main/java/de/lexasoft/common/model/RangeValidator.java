package de.lexasoft.common.model;

/**
 * Represents a value, being valid in a range between min and max, inclusive.
 * 
 * @author Axel
 */
public class RangeValidator<T extends Number> implements Validator<T> {

	private final Range<T> range;

	private RangeValidator(Range<T> range) {
		this.range = range;
	}

	@Override
	public boolean validate(T value) {
		return ((value.doubleValue() >= range.min().doubleValue()) && (value.doubleValue() <= range.max().doubleValue()));
	}

	public static <T extends Number> Validator<T> of(Range<T> range) {
		return new RangeValidator<>(range);
	}

}
