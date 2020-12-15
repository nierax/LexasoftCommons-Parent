package de.lexasoft.exchange;

import java.util.Iterator;

/**
 * Describes a set of name value pairs.
 * 
 * @author Axel Niering
 */
public interface NameValuePairs {
  
  /**
   * Gets a name value pair on the position anIndex, 0-based.
   * @param anIndex The position to read from.
   * @return The Name value pair at the given position.
   * @throws ArrayIndexOutOfBoundsException If anIndex is out of range.
   */
  public NameValuePair getNameValuePair( int anIndex) throws ArrayIndexOutOfBoundsException;
  
  /**
   * Gets a name value pair by its name.
   * @param aName The name of the name value pair.
   * @return The name value pair, requested.
   */
  public NameValuePair getNameValuePair( String aName);
  
  /**
   * @return The number of name value pairs in this collection.
   */
  public int getNrOfNameValuePairs();
  
  /**
   * @return All name value pairs in this collection in an iterator instance.
   */
  public Iterator getNameValuePairs();

}
