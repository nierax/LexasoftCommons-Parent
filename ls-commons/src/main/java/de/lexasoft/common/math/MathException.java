/**
 * 
 */
package de.lexasoft.common.math;

/**
 * Represents an exception in a mathematical calculation.
 * 
 * @author nierax
 *
 */
@SuppressWarnings("serial")
public class MathException extends Exception {

	/**
	 * @param message
	 */
	public MathException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MathException(String message, Throwable cause) {
		super(message, cause);
	}

}
