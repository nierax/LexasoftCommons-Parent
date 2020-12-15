package de.lexasoft.exchange;

/**
 * Common interface, which describes a name value pair.
 * 
 * @author Axel Niering.
 */
public interface NameValuePair {
  
  /**
   * @return Gets the name of the attribute.
   */
  public abstract String getName();
  
  /**
   * @return Gets the value of the attribute.
   */
  public abstract String getValue();

}
