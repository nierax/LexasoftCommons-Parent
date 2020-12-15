package de.lexasoft.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Diese Klasse wendet das Iterator Interface auf eine Enumeration an. Die 
 * zusaetzliche Methode remove() wird dabei nicht unterstuetzt.
 * 
 * @author axel
 */
public class IteratorWrapper implements Iterator {
  
  /**
   * Speichert die Enumeration, auf die Iterator angewendet werden soll.
   */
  private Enumeration enumeration;
  
  public IteratorWrapper( Enumeration anEnumeration) {
    enumeration = anEnumeration;
  }

  /**
   * Nicht implementiert. Wirft eine UnsupportedOperationException.
   * @see java.util.Iterator#remove()
   */
  public void remove() {
    throw new UnsupportedOperationException( "remove not supported");
  }

  /**
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext() {
    return getEnumeration().hasMoreElements();
  }

  /** 
   * @see java.util.Iterator#next()
   */
  public Object next() {
    return getEnumeration().nextElement();
  }

  /**
   * @return Returns the enumeration.
   */
  private Enumeration getEnumeration() {
    return enumeration;
  }
}
