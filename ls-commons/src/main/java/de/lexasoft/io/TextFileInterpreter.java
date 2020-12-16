package de.lexasoft.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import de.lexasoft.text.Parser;

/**
 * Reads and interprets a text file.
 * <p>
 * Following funtions are implemented at the moment:
 * <ul>
 *  <li>Ignoring lines with comment (must start with "#")</li>
 *  <li>Parsing all lines with a <code>de.lexasoft.text.Parser</code></li>
 * </ul>
 * You can use the parser feature for example to replace variables in the file
 * by their value with <code>de.lexasoft.text.VariableParser</code>.
 * <p>
 * Due to the interpreting algorithm this class reads the file while running its
 * constructor and keeps all lines of it in memory. Thus it needs more resources,
 * but might be much faster, when the content of the file is read several times.
 * <p>
 * To be able to use the Iterator feature several times, there is an additional 
 * method <code>reset()</code>.
 * <p>
 * Remember, that all actions performed by this class will always refer to the
 * file's copy in memory. To really read the file once more, You must create
 * another object.
 * <p>
 * If You have to read large files, please check, if You can use 
 * <code>de.lexasoft.io.TextFileIterator</code> instead.   
 * 
 * @author Axel Niering
 * @see de.lexasoft.text.Parser
 * @see de.lexasoft.text.VariableParser
 * @see de.lexasoft.io.TextFileIterator
 */
public class TextFileInterpreter implements Iterator {

  /**
   * The lines of the text file.
   */
  private ArrayList lines;
  
  /**
   * Keeps an instace of the line iterator.
   */
  private Iterator lineIterator;
  
  /**
   * An optional parser.
   */
  private Parser parser;
  
  /**
   * Reads file from the given location and stores all none comment lines.
   * <p>
   * The file may be located on any resource in the local file system or via
   * ftp or http protocol.
   * @param aFileName The location of the text file.
   * @throws IOException
   */
  public TextFileInterpreter(String aFileName) throws IOException {
    readFile( aFileName);
  }

  /**
   * Reads file from the given location and stores all none comment lines.
   * @param aFileName The location of the text file.
   * @throws IOException
   */
  private void readFile( String aFileName) throws IOException {
    TextFileIterator theFile = TextFileIterator.create( aFileName);
    ArrayList theLines = getLines();
    while( theFile.hasNext()) {
      String theLine = theFile.next().toString();
      if( theLine == null) {
        break;
      }
      theLine = theLine.trim();
      if( !theLine.startsWith( "#")){
        theLines.add( theLine);
      }
    }
  }
  
	/**
	 * @return Returns all read lines.
	 */
	private ArrayList getLines() {
    if( lines==null) {
      lines = new ArrayList();
    }
		return lines;
	}

	/**
   * Remove method implementation is not supported.
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
    throw new UnsupportedOperationException( "remove not supported");
	}

	/**
   * Checks, whether there is still a line to read or not.
   * @return True, while there still is a next line to read, false otherwise.
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return getLineIterator().hasNext();
	}

	/**
   * Steps through the lines of the files and returns next one with every call.
   * Comment lines will be skipped.
   * <p>
   * If there is a parser set, every line will be processed through it, before 
   * returning.
   * @return Returns next line of text file.
	 * @see java.util.Iterator#next()
	 */
	public Object next() {
    String theLine = getLineIterator().next().toString();
    if( parser==null) {
      return theLine;
    }
		return parser.parse( theLine);
	}

	/**
	 * @return Returns instance of iterator.
	 */
	private Iterator getLineIterator() {
    if( lineIterator==null) {
      lineIterator = getLines().iterator();
    }
		return lineIterator;
	}
  
  /**
   * Prepares the object to be used once more. Thus, once You read the file,
   * You can use its contents several times.
   */
  public void reset() {
    lineIterator = null;
  }

  /**
   * Sets an optional parser for this object.
   * <p>
   * If a parser is set, every line in the file will be parsed by it.
   * <p>
   * Parser can be set at any time between creating this object and using it
   * to read the file.
   * @param parser The parser to set.
   */
  public void setParser(Parser parser) {
    this.parser = parser;
  }
}
