package de.lexasoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Liefert die in diesem Prozess gueltigen Properties. Das Verzeichnis wird
 * ueber den VM - Parameter "de.lexasoft.properties" gesetzt.
 * 
 * @author axel
 */
public class PropProvider {
  
  /**
   * Nimmt das Singleton des PropProviders auf.
   */
  private static PropProvider singleton;
  
  private Hashtable propertiesContainer;
  
  private String createFileName( String aModul) {
    String theFileName = System.getProperty("de.lexasoft.properties", "./props");
    if( !theFileName.endsWith( "/")) {
      theFileName += "/";
    }
    String theModul = aModul.replace( '.', '_');
    theFileName += theModul + ".properties";
    return theFileName;
  }

	/**
	 * @return Die Hashtable, die die Propertyobjekte speichert.
	 */
	private Hashtable getPropertiesContainer() {
    if( propertiesContainer == null) {
      propertiesContainer = new Hashtable();
    }
		return propertiesContainer;
	}
  
  private Properties getProperties( String aModul) throws IOException {
    Hashtable thePropContainer = getPropertiesContainer();
    Properties theProperties = (Properties)thePropContainer.get( aModul);
    if( theProperties == null) {
      theProperties = new Properties();
      String theFileName = createFileName( aModul);
      File thePropertyFile = new File( theFileName);
      FileInputStream theStream = new FileInputStream(thePropertyFile);
      theProperties.load( theStream);
      thePropContainer.put( aModul, theProperties);
    }
    return theProperties; 
  }
  
  /**
   * @return Liefert die einzige Instanz des Property Providers im System.
   */
  private static PropProvider getSingleton() {
    if( singleton == null) {
      singleton = new PropProvider();
    }
    return singleton;
  }
  
  public static Properties provideProperties( String aModul) throws IOException {
    return getSingleton().getProperties( aModul);
  }

}
