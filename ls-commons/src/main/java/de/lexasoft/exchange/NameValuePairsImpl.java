package de.lexasoft.exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Common implementation of NameValuePairs interface.
 * 
 * @author axel
 */
public class NameValuePairsImpl implements NameValuePairs {

  private List allPairs;
  
  private Map allIndexes;
  
  /**
   * @return Returns the allIndexes.
   */
  private Map getAllIndexes() {
    if (allIndexes==null) {
      allIndexes = new HashMap();
    }
    return allIndexes;
  }
  /**
   * @return Returns the allPairs.
   */
  private List getAllPairs() {
    if( allPairs == null) {
      allPairs = new ArrayList();
    }
    return allPairs;
  }
  /**
   * @see de.lexasoft.exchange.NameValuePairs#getNameValuePair(int)
   */
  public NameValuePair getNameValuePair(int anIndex)
      throws ArrayIndexOutOfBoundsException {
    return (NameValuePair)getAllPairs().get( anIndex);
  }

  /**
   * @see de.lexasoft.exchange.NameValuePairs#getNrOfNameValuePairs()
   */
  public int getNrOfNameValuePairs() {
    return getAllPairs().size();
  }

  /**
   * @see de.lexasoft.exchange.NameValuePairs#getNameValuePairs()
   */
  public Iterator getNameValuePairs() {
    return getAllPairs().iterator();
  }
  
  /**
   * Adds a name value pair to the collection.
   * @param aNameValuePair The name value pair to add.
   */
  public void addNameValuePair( NameValuePair aNameValuePair) {
    int thePosition = getAllPairs().size();
    getAllPairs().add( thePosition, aNameValuePair);
    getAllIndexes().put( aNameValuePair.getName(), new Integer(thePosition));
  }
  
  /**
   * Adds a name value pair, represented by the parameters <code>name</code> 
   * and <code>value</code> to the current collection.
   * @param aName The name of the name value pair.
   * @param aValue The value of the name value pair.
   */
  public void addNameValuePair( String aName, String aValue) {
    NameValuePairImpl theNVP = new NameValuePairImpl();
    theNVP.setName( aName);
    theNVP.setValue( aValue);
    addNameValuePair( theNVP);
  }
  
  /**
   * Returns the String representation of all name value pairs by using the
   * toString() method of the List object.
   */
  public String toString() {
    return getAllPairs().toString();
  }
  
  /**
   * Looks up the position of the name value pair with the name as specified in
   * <code>aName</code>.
   * @param aName The name of the requested name value pair.
   * @return The position of the name value pair.
   */
  private int getPositionOfElement( String aName) {
    return ((Integer)getAllIndexes().get( aName)).intValue();
  }

  /**
   * @see de.lexasoft.exchange.NameValuePairs#getNameValuePair(java.lang.String)
   */
  public NameValuePair getNameValuePair(String aName) {
    return getNameValuePair( getPositionOfElement( aName));
  }
  
}
