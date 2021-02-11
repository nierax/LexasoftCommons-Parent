/**
 * 
 */
package de.lexasoft.common.model;

/**
 * This validator checks, whether a value is bigger than a minimum value.
 * 
 * @author Axel
 *
 */
public final class MinimumValidator<T extends Number> implements Validator<T> {

	private final T minimumValue;

	/**
	 * Creates the validator.
	 */
	private MinimumValidator(T minimumVale) {
		this.minimumValue = minimumVale;
	}

	/**
	 * @param value Value to check.
	 * @return True, if the value is bigger than the minimum (false otherwise)
	 */
	@Override
	public boolean validate(T value) {
		return value.doubleValue() >= minimumValue.doubleValue();
	}

	public static <T extends Number> Validator<T> of(T value) {
		return new MinimumValidator<T>(value);
	}

}
