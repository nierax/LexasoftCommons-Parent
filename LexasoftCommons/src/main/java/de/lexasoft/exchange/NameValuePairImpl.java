package de.lexasoft.exchange;

import java.util.StringTokenizer;

/**
 * A common implementation of a name value pair.
 * 
 * @author axel
 */
public class NameValuePairImpl implements NameValuePair {

  /**
   * Stores the name.
   */
  private String name;
  
  /**
   * Stores the value.
   */
  private String value;
  
  /**
   * @see de.lexasoft.exchange.NameValuePair#getName()
   */
  public String getName() {
    return name;
  }

  /**
   * @see de.lexasoft.exchange.NameValuePair#getValue()
   */
  public String getValue() {
    return value;
  }
  
  /**
   * Sets the name of the name value pair.
   * @param aName The name to set.
   */
  public void setName(String aName) {
    this.name = aName;
  }

  /**
   * Sets the value of the name value pair.
   * @param aValue The value to set.
   */
  public void setValue(String aValue) {
    this.value = aValue;
  }
  
  /**
   * Sets name and value from a string in which both parts are separated by a
   * delimiter.
   * <p>
   * This could be a string like this: <code>"name=value"</code>, where the
   * name is <code>"name"</code>, the value is <code>"value"</code> and the 
   * delimiter is <code>"="</code>.
   * 
   * @param aNameAndValue
   * @param aDelimiter
   * @throws IllegalArgumentException If the given string can not be interpreted
   *              as a name value pair, for instance because the delimiter is not
   *              found in int.
   */
  public void setNameAndValue( String aNameAndValue, String aDelimiter) 
                                              throws IllegalArgumentException {
    StringTokenizer theNameAndValue = new StringTokenizer( aNameAndValue, aDelimiter);
    String theName = theNameAndValue.nextToken().trim();
    if( !theNameAndValue.hasMoreTokens()) {
      throw new IllegalArgumentException( "Could not interpret \"" + aNameAndValue + "\" to name and value with delimiter \"" + aDelimiter + "\"");
    }
    String theValue = theNameAndValue.nextToken().trim();
    setName( theName);
    setValue( theValue);
  }
  
  /**
   * Returns the string representation of the object. This will be build as 
   * follows:
   * <ul>
   *  <li>name followed by</li>
   *  <li>"=" followed by</li>
   *  <li>"value"</li>
   * </ul>
   */
  public String toString() {
    return getName() + "=\"" + getValue() + "\"";
  }
}
