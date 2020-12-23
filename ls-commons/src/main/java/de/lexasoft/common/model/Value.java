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
    this((value) -> {
      return true;
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

  /**
   * Creates a value object with the parametrized validator and an initialValue.
   * <p>
   * The initial value is not checked against the validator, because it can be
   * used to signalize, that the object was not used until now.
   * 
   * 
   * @param validator
   * @param initialValue An inital value, can be outside the range, defined by the
   *                     validator.
   */
  public Value(Validator<T> validator, T initialValue) {
    this.validator = validator;
    this.value = initialValue;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    if (!validator.validate(value)) {
      String message = String.format("Value %s not valid regarding validator %s\n", value, validator.getClass());
      System.out.print(message);
      throw new IllegalArgumentException(message);
    }
    this.value = value;
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
}
