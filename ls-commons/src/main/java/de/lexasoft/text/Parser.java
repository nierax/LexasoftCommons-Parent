package de.lexasoft.text;

/**
 * This interface defines a common string parser. Any string, passed through it,
 * will be parsed in a way, which depends on the implementation.
 * 
 * @author Axel Niering
 */
public interface Parser {

  /**
   * Parses the given String <code>anOriginal</code> in a way, which depends
   * on the implementation of the interface.
   * <p>
   * Please refer to the documentation of the implementation to learn how to
   * use this method. 
   * 
   * @param anOriginal The original of the string.
   * @return The parsed string with all variables replaced.
   */
  public abstract String parse(String anOriginal);
}