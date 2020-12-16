package de.lexasoft.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import de.lexasoft.util.LongTaskObserver;

/**
 * This class handels a textfile iterator like, which means, that every single
 * line is an element, until last line is reached.
 * <p>
 * This class reads the file the moment, the iterator method is called. It does
 * not keep a copy of it in memory.
 * <p>
 * Thus this class is unable to do any interpretion of the lines but does not
 * need many resources.
 * <p>
 * The file can be read just once. If You need the file to be read once more,
 * You must create another object. Or use <code>de.lexasoft.io.TextFileInterpreter</code>
 * instead.
 * <p>
 * Furthermore this class implements <code>LongTaskObserver</code> to easily
 * monitor the progress.
 * <p>
 * Please note, that not all <code>InputStream</code> implementations deliver
 * the total length of file correctly. If the file size is unknown, only the
 * number of bytes read so far can be monitored.
 * 
 * @author Axel Niering
 * @see de.lexasoft.io.TextFileInterpreter
 */
public class TextFileIterator implements Iterator, LongTaskObserver {

  private BufferedReader textFileReader;
  
  private boolean toBreak;

  private int lengthOfTask;
  
  private int current;
  
  private boolean done;

  /**
   * Creates a TextFileIterator, to read a text line based datasource from the 
   * given InputStream, which may represent any type of datasource. 
   * @param anInputStream The InputStream to read from.
   * @throws IOException
   */
  public TextFileIterator( InputStream anInputStream) throws IOException {
    super();
    textFileReader = new BufferedReader( new InputStreamReader( anInputStream));
    toBreak = false;
    lengthOfTask = determineLengthOfTask( anInputStream);
    current = 0;
  }
  
  private int determineLengthOfTask( InputStream aStream) throws IOException {
    int theSize = aStream.available();
    if( theSize <= 1) {
      theSize = -1;
    }
    return theSize;
  }

	/**
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
    throw new UnsupportedOperationException( "remove not supported");
	}

	/**
   * Returns true, if there is another line to read, false otherwise.
   * <p>
   * If an error occures, next call to this method will produce false.
   * <p>
   * Furthermore this method stops the action, if the current Thread has been
   * interrupted. 
   * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
    if( Thread.interrupted()) {
      toBreak = true;
    }
    if( toBreak) {
      return false;
    }
    try {
      done = !textFileReader.ready();
      return !done;
    } catch (IOException e) {
      e.printStackTrace();
      toBreak = true;
    }
    return false;
	}

	/**
   * Returns the next line in an instance of String. Due to the implementation
   * of the iterator interface, this method is declared to return 
   * <code>java.lang.Object</code>.
   * <p>
   * Anyway, You can cast this result to String or, even better, use toString().
   * 
   * @return Next line or null, if an error occured. 
	 * @see java.util.Iterator#next()
	 */
	public Object next() {
		try {
			String theLine = textFileReader.readLine();
      addProgress( theLine.length());
      return theLine;
		} 
    catch (IOException e) {
			e.printStackTrace();
      toBreak = true;
		}
    return null;
	}
  
  /**
   * @see de.lexasoft.util.LongTaskObserver#getLengthOfTask()
   */
  public int getLengthOfTask() {
    return lengthOfTask;
  }

  /**
   * @see de.lexasoft.util.LongTaskObserver#getCurrent()
   */
  public int getCurrent() {
    return current;
  }

  /**
   * Adds the given amount of bytes to the progress.
   * 
   * @param anyBytesRead Bytes, read since last call.
   */
  private void addProgress( int anyBytesRead) {
    current += anyBytesRead;
  }

  /**
   * @see de.lexasoft.util.LongTaskObserver#isDone()
   */
  public boolean isDone() {
    return done;
  }

  /**
   * Reading of the file always starts with zero (0).
   * 
   * @see de.lexasoft.util.LongTaskObserver#getStartOfTask()
   */
  public int getStartOfTask() {
    return 0;
  }

  /**
   * This static method can be used to create a TextFileIterator from any 
   * textfile, located on any resource in local file system or from a remote
   * datasource.
   * <p>
   * Supported protocols are file, http and ftp, where file is the default 
   * protocol.
   * <p>
   * Example:<br>
   * To access a file on Your local file system, write something like:<br>
   * <code>c:\mylogs\access.log</code> or <code>file:///c:/mylogs/access.log</code> 
   * on a windows system or something like <code>usr/opt/mylogs/access.log</code> 
   * <code>file:///usr/opt/mylogs/access.log</code> on a unix based system such 
   * as linux.
   * <p>
   * To access a remote file via ftp, write something like this:<br>
   * <code>ftp://username:password@ftp.yourdomain.com/mylogs/access.log</code>
   * <p>
   * Furthermore this method accepts compression via gzip or zip. It recognizes
   * the kind of compression from the end of the file name:
   * <ul>
   *  <li><code>".gz"</code> will be handled as gzip.</li>
   *  <li><code>".zip"</code> will be handled as zip.</li>
   *  <li>everything else will be handled as not compressed.</li>
   * </ul>
   * In case of zip, the first entry in the zip file is used. If there are more 
   * than one entry in the zip, please use <code>createFromZipFile()</code> 
   * instead.
   * <p>
   * Please note: In case of zip, only local file system will be accepted.
   *  
   * @param aFilename The adress to access the text file. May represent a
   *         url or a file on the local file system.
   * @return A new instance of TextFileIetarator.
   * @throws IOException If TextFileIterator can not be created.
   */
  public static TextFileIterator create( String aFilename) 
                                  throws IOException {
    return new TextFileIterator( ZIPStreamFactory.createInputStream( aFilename));
  }
  
  /**
   * This method creates a TextFileIterator from a zip file entry.
   * <p>
   * Please note: Reading zip files only will work from local file system.
   * 
   * @param aFilename The name of the zip file.
   * @param anEntry The name of the entry in the zip file.
   * @return A new instance of TextFileIterator.
   * @throws IOException
   */
  public static TextFileIterator createFromZipFile(String aFilename, String anEntry) throws IOException {
    InputStream theStream = 
              ZIPStreamFactory.createZIPInputStream( aFilename, anEntry);
    return new TextFileIterator(theStream);
  }

  /**
   * @see de.lexasoft.util.LongTaskObserver#getMessage()
   */
  public String getMessage() {
    return "Please wait, while reading the file."; 
  }

}
