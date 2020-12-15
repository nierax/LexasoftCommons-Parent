package de.lexasoft.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Creates a stream (input or output) to a file, located in a zip file (gzip or
 * zip) or not.
 * <p>
 * TODO Implement for ouptut. 
 * @author Axel Niering
 */
public class ZIPStreamFactory {

  /**
   * Don't create
   */
  private ZIPStreamFactory() {
    super();
  }
  
  /**
   * Creates an input stream from gzip file. Reads the file inside the gzip.
   * <p>
   * Gzips only can contain one file. Therefore this method easily reads the
   * entry, found in the gzip file.
   * 
   * @param aFilename The name of the gzip file.
   * @return An input stream to read the gzip file.
   * @throws IOException If the stream can not be created.
   */
  public static InputStream createGZIPInputStream( String aFilename) throws IOException {
    if( (aFilename==null) || "".equals( aFilename) ) {
      throw new MalformedURLException( "URL to read from must be given.");
    }
    InputStream theURL = URLStreamFactory.getFactory().createInputStream( aFilename);
    return new GZIPInputStream( theURL);
  }
  
  /**
   * Creates an input stream from zip file. Reads the file specified by 
   * <code>anEntry</code> from the given zip file.
   * <p>
   * If <code>anEntry</code> is null or "", the first entry, found in the zip
   * file, is used.
   * 
   * @param aFilename The name of the zip file.
   * @param anEntry The name of the file in the zip.
   * @return An input stream to read the entry from the zip file.
   * @throws IOException If the stream can not be created.
   */
  public static InputStream createZIPInputStream(String aFilename, String anEntry) throws IOException {
    if( (aFilename==null) || "".equals( aFilename) ) {
      throw new MalformedURLException( "URL to read from must be given.");
    }
    ZipFile theZipFile = new ZipFile( aFilename);
    ZipEntry theEntry;
    if( (anEntry != null) && !"".equals( anEntry)) {
      theEntry = theZipFile.getEntry( anEntry);
    }
    else {
      theEntry = (ZipEntry)theZipFile.entries().nextElement();
    }
    return theZipFile.getInputStream(theEntry);
  }
  
  /**
   * Creates an input stream from zip file. Reads the first entry, found in the 
   * zip file.
   * 
   * @param aFilename The name of the zip file.
   * @return An input stream to read the entry from the zip file.
   * @throws IOException If the stream can not be created.
   */
  public static InputStream createZIPInputStream(String aFilename) throws IOException {
    return createZIPInputStream( aFilename, null);
  }
    
  /**
   * Tries to create a stream to the given file. It recognizes the right 
   * compression format from the ending of the file name:
   * <ul>
   *  <li><code>".gz"</code>: GZIP stream is created.</code></li>
   *  <li><code>".zip"</code>: ZIP stream is created.</code></li>
   *  <li>everything else: stream without compression is created.</li>
   * </ul>
   * @param aFilename Name of the file to read, may be compressed by gzip or zip. 
   * @return InputStream to read from the file.
   * @throws IOException If the stream can not be created.
   */
  public static InputStream createInputStream( String aFilename) throws IOException {
    if( (aFilename==null) || "".equals( aFilename) ) {
      throw new MalformedURLException( "URL to read from must be given.");
    }
    if( aFilename.endsWith( ".gz")) {
      return createGZIPInputStream( aFilename);
    }
    if( aFilename.endsWith( ".zip")) {
      return createZIPInputStream( aFilename);
    }
    return URLStreamFactory.getFactory().createInputStream( aFilename);
  }

}
