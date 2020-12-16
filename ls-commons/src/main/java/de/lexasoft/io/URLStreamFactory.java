package de.lexasoft.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates streams (for input or output) depending on the protocol to the file.
 * <p>
 * This class will be instantiated in a singleton, holding information about
 * the base directory, from which relative paths will be read.
 * <p>
 * It handels the given file address as follows:
 * <ul>
 *  <li>File adress contains protocol information: Stream to URL will be created.</li>
 *  <li>File address is absolute, but without protocol: Stream to local file 
 *      system will be created.</li>
 *  <li>File address is relative: Base directory will be added. In this case:
 *    <ul>
 *      <li>If resulting address contains protocol: Stream to URL will be created.</li>
 *      <li>Otherwise: Stream to local file system will be created.</li>
 *    </ul>
 *  </li>
 * </ul>
 * @author Axel Niering
 */
public class URLStreamFactory {

  /**
   * Patterns tested in addition to "/" at the beginning to find absolute path.
   */
  public static final String[] ABSOLUTE_PATTERNS = {
      ":\\", ":/"
  };
  
  /**
   * Keeps base directory for use in case of relative path.
   */
  private String baseDir;
  
  /**
   * Holds the factory's singleton. 
   */
  private static URLStreamFactory factory;
  
  /**
   * Creates a URLStreamFactory for the given base directory. This directory
   * will be used to complete relative file adresses.
   * <p>
   * Base directory must be given absolutely. Otherwise, MalformedURLException
   * is thrown.
   * <p>
   * Class should only be used as singleton, therefore it can not be instantiated
   * outside this class or its derived classes.
   * @param aBaseDir The base directoy of the application.
   * @throws MalformedURLException if the directory is not given absolutely.
   */
  protected URLStreamFactory( String aBaseDir) throws MalformedURLException {
    baseDir = aBaseDir;
    if( !isAbsolute( aBaseDir)) {
      throw new MalformedURLException( "Base direectory must be given absolutely");
    }
  }
  
  /**
   * Returns, whether the file address is absolute or not.
   * <p>
   * It is assumed to be absolute, if:
   * <ul>
   *  <li>it starts with protocol information</li>
   *  <li>it starts with system file separator (for example "/")</li>
   *  <li>it contains any additional pattern, signaling it is absolute</li>
   * </ul>
   * @param aFileAddress The file address to check being absolute.
   * @see URLStreamFactory#ABSOLUTE_PATTERNS
   * @return True, if the file address given is absolute, false otherwise.
   */
  private boolean isAbsolute( String aFileAddress) {
    if( containsProtocol( aFileAddress)) {
      return true;
    }
    if( aFileAddress.startsWith( System.getProperty( "file.separator"))) {
      return true;
    }
    for( int i=0; i<ABSOLUTE_PATTERNS.length; i++) {
      if( aFileAddress.indexOf( ABSOLUTE_PATTERNS[i]) > 0) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Returns, whether the given file address starts with a known protocol 
   * information.
   * <p>
   * Supported protocols are:
   * <ul>
   *  <li>file</li>
   *  <li>http</li>
   *  <li>ftp</li>
   *  <li>gopher</li>
   *  <li>mailto</li>
   *  <li>doc</li>
   * </ul>
   * @param aFileAddress The file address to check.
   * @return True, if the file adress starts with a known protocol.
   */
  private boolean containsProtocol( String aFileAddress) {
    return ( aFileAddress.startsWith( "file://") ||
             aFileAddress.startsWith( "http://") ||
             aFileAddress.startsWith( "ftp://") ||
             aFileAddress.startsWith( "gopher://") ||
             aFileAddress.startsWith( "mailto://") ||
             aFileAddress.startsWith( "doc://"));
  }
  
  /**
   * Creates an Inputstream to the file, represented in the given file name.
   * <p>
   * The stream is created as described in class comment above.
   * 
   * @param aFileAddress The file address, an input stream is requested to.
   * @return The input stream to read the given file.
   * @throws IOException If the stream could not be created due to several 
   *          problems.
   */
  public InputStream createInputStream( String aFileAddress) throws IOException {
    // Adress must be given.
    if( aFileAddress==null || "".equals(aFileAddress)) {
      throw new MalformedURLException( "Malformed URL '" + aFileAddress + "'");
    }
    String theSource = aFileAddress;
    // If file is absolute, but no protocol is set, file must be on local file 
    // system.
    if( isAbsolute( theSource) && !containsProtocol( theSource)) {
      return new FileInputStream( theSource);
    }
    if( !isAbsolute( theSource)) {
      String theCanonifiedName = getBaseDir() + theSource;
      if( !containsProtocol( theCanonifiedName)) {
        return new FileInputStream( theCanonifiedName);
      }
      return new URL( theCanonifiedName).openConnection().getInputStream();
    }
    return new URL(theSource).openConnection().getInputStream();
  }

  /**
   * Creates an output stream to the file, represented in the given file name.
   * <p>
   * The stream is created as described in class comment above.
   * 
   * @param aFileAddress The file address, an input stream is requested to.
   * @return The output stream to read the given file.
   * @throws IOException If the stream could not be created due to several 
   *          problems.
   */
  public OutputStream createOutputStream( String aFileAddress) throws IOException {
    // Adress must be given.
    if( aFileAddress==null || "".equals(aFileAddress)) {
      throw new MalformedURLException( "Malformed URL '" + aFileAddress + "'");
    }
    String theSource = aFileAddress;
    // If file is absolute, but no protocol is set, file must be on local file 
    // system.
    if( isAbsolute( theSource) && !containsProtocol( theSource)) {
      return new FileOutputStream( theSource);
    }
    if( !isAbsolute( theSource)) {
      String theCanonifiedName = getBaseDir() + theSource;
      if( !containsProtocol( theCanonifiedName)) {
        return new FileOutputStream( theCanonifiedName);
      }
      return new URL( theCanonifiedName).openConnection().getOutputStream();
    }
    return new URL(theSource).openConnection().getOutputStream();
  }

  /**
   * This method must be used to get the factory's singleton.
   * <p>
   * If it is called first time without <code>setBaseDir()</code> or
   * <code>setFactory()</code> being called before, a factory will be created
   * with base directory read from system property <code>"user.dir"</code>.
   * <p>
   * Please make sure, that access to this property is allowed before using
   * this methode. If this is not possible (for example in applets in internet),
   * make sure, <code>setBaseDir()</code> or <code>setFactory()</code> are called
   * prior to this method.
   * 
   * @return Returns the factory's singleton.
   * @throws IOException If the factory could not be created.
   */
  public static URLStreamFactory getFactory() throws IOException {
    if( factory == null) {
      factory = new URLStreamFactory( System.getProperty("user.dir", ""));
    }
    return factory;
  }

  /**
   * Allows You to set a specific URLStreamFactory, adapted to Your needs.
   * This factory from now on will be used to create URL streams. 
   * @param aFactory The factory to set.
   */
  public static void setFactory(URLStreamFactory aFactory) {
    factory = aFactory;
  }
  
  /**
   * An easier way to create an adapted <code>URLStreamFactory</code>.
   * <p>
   * Creates an <code>URLStreamFactory</code> with given base directory, which
   * will be used from now on to create URL streams.
   * @param aBaseDir
   * @throws MalformedURLException
   */
  public static void setBaseDir( String aBaseDir) throws MalformedURLException {
    factory = new URLStreamFactory( aBaseDir);
  }

  /**
   * Returns the base directory, used by the factory.
   * @return Returns the baseDir.
   */
  private String getBaseDir() {
    return baseDir;
  }
}
