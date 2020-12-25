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
public class MinimumValidator<T extends Number> implements Validator<T> {

  private T minimumValue;

  /**
   * Creates the validator.
   */
  public MinimumValidator(T minimumVale) {
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

}
