/**
 * 
 */
package de.lexasoft.common.model;

/**
 * Represents a value, which can be verified against a validator.
 * 
 * @author Axel
 */
public class Value<T> {

	private T value;
	
	private Validator<T> validator;
	
	/**
	 * Creates a value object without validating functionality.
	 */
	public Value() {
		/* Validator is optional */
		this(new Validator<T>() {

			/**
			 * Always valid.
			 */
			@Override
			public boolean validate(T value) {
				return true;
			}
		});
	}
	
	/**
	 * Creates a value object with the parametrized validator.
	 * 
	 * @param validator
	 */
	public Value(Validator<T> validator) {
		this.validator = validator;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		if(!validator.validate(value)) {
			String message = String.format("Value %s not valid regarding validator %s\n", value, validator.getClass());
			System.out.print(message);
			throw new IllegalArgumentException(message);
		}
		this.value = value;
	}
}
