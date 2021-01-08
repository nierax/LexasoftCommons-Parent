package de.lexasoft.common.cli;

import de.lexasoft.common.model.Validator;

/**
 * 
 * @author Axel
 *
 * @param <T>
 */
public class ConsoleValidator {

  public ConsoleValidator() {
  }

  /**
   * Reads an input from the ConsoleReader.
   * <p>
   * Repeats the read, until the Validator is true.
   * 
   * @param message   The message to tell the user what to input. Will be
   *                  repeated, if the validation fails.
   * @param input     The reader to read from the console.
   * @param validator The validator to check the input.
   * @return The value, read from the console.
   */
  public <T> T fromConsole(String message, ConsoleReader<T> input, Validator<T> validator) {
    while (true) {
      System.out.print(message);
      T value = input.nextInput();
      if (validator.validate(value)) {
        return value;
      }
    }
  }

}
