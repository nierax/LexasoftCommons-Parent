/**
 * 
 */
package de.lexasoft.common.model;

/**
 * Describes a method to validate a value against a rule, that depends on the implementation
 * 
 * @author Axel
 */
public interface Validator<T> {
	
	/**
	 * Validates the value.
	 * @param value
	 * @return True, if valid, otherwise false.
	 */
	public boolean validate(T value);

}
