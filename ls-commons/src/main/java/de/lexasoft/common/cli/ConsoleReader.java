package de.lexasoft.common.cli;

/**
 * Interface to read something from the console (or actually from any source).
 * 
 * @author Axel
 *
 * @param <T>
 */
public interface ConsoleReader<T> {

  /**
   * 
   * @return Next value T.
   */
  public T nextInput();

}
