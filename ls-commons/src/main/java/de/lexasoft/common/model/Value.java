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
   * Creates a value object without validating functionality and without a preset
   * value.
   */
  public Value() {
    /* "Always return true" validator */
    this((value) -> {
      return true;
    });
  }

  /**
   * Creates a value object without validating functionality and with the given
   * initial value.
   * 
   * @param initialValue
   */
  public Value(T initialValue) {
    this();
    validate(initialValue);
    this.value = initialValue;
  }

  /**
   * Creates a value object with the parametrized validator.
   * 
   * @param validator
   */
  public Value(Validator<T> validator) {
    this.validator = validator;
  }

  /**
   * Creates a value object with the given validator and an initial value.
   * <p>
   * Ensures, the object has a value and the {@link #getValue()} method doesn't
   * return null, provided the connected validator doesn't accept null values.
   * 
   * @param validator
   * @param initialValue An initial value
   */
  public Value(Validator<T> validator, T initialValue) {
    this.validator = validator;
    validate(initialValue);
    this.value = initialValue;
  }

  /**
   * Returns the actual value.
   * <p>
   * Can be null, if the value wasn't set before.
   * 
   * @return The current value or null, if the value was not set before.
   */
  public T getValue() {
    return value;
  }

  /**
   * @param value The value to set.
   * @return The value, set.
   */
  public T setValue(T value) {
    validate(value);
    this.value = value;
    return value;
  }

  /**
   * Performs the validation via a call to the validator.
   * 
   * @param value The value to check.
   * @throws IllegalArgumentException if the validation fails.
   */
  private void validate(T value) {
    if (!validator.validate(value)) {
      String message = String.format("Value %s not valid regarding validator %s\n", value, validator.getClass());
      System.out.print(message);
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Subclasses sometimes need access to the validator, as it is typically created
   * within the constructor.
   * 
   * @return The validator of this object.
   */
  protected Validator<T> getValidator() {
    return validator;
  }

  /**
   * Returns, whether the object has a value. Usually the value is not set after
   * creation, so this can be used to check, whether the value has been set
   * before.
   * 
   * @return True, if a value is set, false otherwise.
   */
  public boolean hasValue() {
    return value != null;
  }

  /**
   * Unsets the value.
   */
  public T unsetValue() {
    T lastValue = value;
    value = null;
    return lastValue;
  }
}
