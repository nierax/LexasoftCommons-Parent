package de.lexasoft.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.lexasoft.exchange.NameValuePair;
import de.lexasoft.exchange.NameValuePairsImpl;

/**
 * Contains variables and replaces them in any String.
 * <p>
 * Variables are represented by <code>$Name</code> in the String. Example:
 * <code>"$a loves $b, but not $c, probably however his 100.000$ bank account."</code>
 * containes variables <code>$a, $b, $c</code>.
 * <p>
 * Variables can be of any length You want. But they must always start with
 * <code>"$"</code>.
 * <p>
 * Variables are added by one of the add methods provided with this class.
 * <p>
 * Variable values can be referenced directly or indirectly. This means value 
 * can be the real value or another variable, pointing to the real value.
 * <p>
 * Example:
 * <pre>
 *  VariableParser theParser = new VariableParser();
 *  theParser.addVariable( "a", "Anton");
 *  theParser.addVariable( "b", "Berta");
 *  theParser.addVariable( "c", "$d");
 *  theParser.addVariable( "d", "Charly");
 * </pre> 
 * will define: <code>$a="Anton", $b="Berta", $c="Charly", $d="Charly"</code>.
 * Our sentence (example in the first place) in this case would be parsed to:
 * <code>"Anton loves Berta, but not Charly, probably however his 100.000$ bank 
 * account."</code>
 * <p>
 * Be aware of circle references. Look at this example:
 * <pre>
 *  VariableParser theParser = new VariableParser();
 *  theParser.addVariable( "c", "$d");
 *  theParser.addVariable( "d", "$e");
 *  theParser.addVariable( "e", "$c");
 * </pre>
 * The value of <code>"$c", "$d" or "$e" </code> can not be determined because
 * of a circle reference between <code>$c</code> and <code>$e</code>. In this
 * case parser will return <code>"[circle referenced]"</code> as value for all 
 * these variables.
 *    
 * @author Axel Niering
 */
public class VariableParser implements Parser {
  
  /**
   * Stores all variables of this object.
   */
  private NameValuePairsImpl variables;
  
  /**
   * Used to determine circle references.
   */
  private List currentlyParsing;

  /**
   * @return Returns all variables, added to this object.
   */
  private NameValuePairsImpl getVariables() {
    if( variables==null) {
      variables=new NameValuePairsImpl();
    }
    return variables;
  }
  
  /**
   * Adds the variable to the collection in this object.
   * @param aName The name of the variable.
   * @param aValue The value of the variable.
   */
  public void addVariable( String aName, String aValue) {
    String theName = aName;
    if( !theName.startsWith( "$")) {
      theName = "$" + theName; 
    }
    getVariables().addNameValuePair( theName, aValue);
  }
  
  /**
   * Adds a variable to the collection in this object from a NameValuePair. 
   * <p>
   * <b>Please note:</b>The NameValuePair Object is not referenced directly.
   * Therefore changes, made to ist, will not reflect to the object in the
   * parser.
   * @param aNameValuePair The name value pair to add.
   */
  public void addVariable( NameValuePair aNameValuePair) {
    addVariable( aNameValuePair.getName(), aNameValuePair.getValue());
  }
  
  /**
   * Determines, which of the variables, currently stored in this object, are 
   * present in the given string.
   * <p>
   * The iterator contains all variable names determined in an instance of
   * java.lang.String.
   * @param aString The string to be parsed.
   * @return An iterator with all variable names, contained in the string.
   */
  private Iterator determineContainedVariables( String aString) {
    ArrayList theVariablesInString = new ArrayList();
    Iterator theVariables = getVariables().getNameValuePairs();
    while( theVariables.hasNext()) {
      NameValuePair theVariable = (NameValuePair)theVariables.next();
      if( aString.indexOf( theVariable.getName()) >= 0) {
        theVariablesInString.add( theVariable.getName());
      }
    }
    return theVariablesInString.iterator();
  }
  
  /**
   * Replaces all occurences of the variable <code>aName</code> in the string
   * <code>anOriginal</code> with the value in <code>aValue</code>.
   * @param anOriginal The string to be parsed.
   * @param aName The variable to be replaced.
   * @param aValue The value to be used instead of the variable name.
   * @return The string with all variables replaced by their values.
   */
  private String replace( String anOriginal, String aName, String aValue) {
    StringBuffer theParsed = new StringBuffer( anOriginal);
    
    int theStartIndex = theParsed.indexOf( aName);
    while( theStartIndex >= 0) {
      int theEndeIndex = theStartIndex + aName.length();
      theParsed.replace( theStartIndex, theEndeIndex, aValue);
      theStartIndex = theParsed.indexOf( aName);
    }
    return theParsed.toString();
  }
  
  /**
   * Parses the given String <code>anOriginal</code> and replaces all occurences
   * of variables in it with the appropiate values.
   * <p>
   * In case of a circle reference the variable name in the string will be 
   * replaced by <code>"[circle referenced]"</code>. 
   * 
   * @param anOriginal The original of the string.
   * @return The parsed string with all variables replaced.
   */
  public String parse( String anOriginal) {
    // catch null
    if( anOriginal==null) {
      return null;
    }
    String theReplaced = anOriginal;
    // nothing to do if no variable inside.
    if( theReplaced.indexOf( "$")<0) {
      return theReplaced;
    }
    Iterator theVariablesToReplace = determineContainedVariables( theReplaced);
    while( theVariablesToReplace.hasNext()) {
      String theVariableName = theVariablesToReplace.next().toString();
      theReplaced = replace( theReplaced, theVariableName, getVariableValue( theVariableName));
    }
    return theReplaced;
  }
  
  /**
   * Returns the parsed value of the variable <code>aName</code>.
   * <p>
   * All occurences of variables, defined in this object, will be replaced by
   * their value.
   * <p>
   * In case of a circle reference <code>"[circle referenced]"</code> is 
   * returned.
   * @param aName The name of the variable.
   * @return The parsed value of the variable. 
   */
  public String getVariableValue( String aName) {
    // Get unparsed value
    String theValue = getVariables().getNameValuePair( aName).getValue();
    // Check for circel reference
    List theCurrentlyParsing = getCurrentlyParsing();
    // Check, whether the value of this variable is currently looked up in a
    // prior level of this recursive call.
    // In this case there is a circle reference and therefore we must break the
    // recursive call chain.
    if( theCurrentlyParsing.contains( aName)) {
      // Return the unparsed value, as parsing is not possible due to a circle
      // reference.
      return "[circle referenced]";
    }
    // Mark this variable to currently being looked up 
    theCurrentlyParsing.add( aName);
    // Parse the value
    theValue = parse( theValue);
    // Variable is not lokked up any more.
    theCurrentlyParsing.remove( aName);
    return theValue;
  }

  /**
   * Returns the list with the variables, currently being parsed.
   * 
   * @return Returns the currentlyParsing.
   */
  private List getCurrentlyParsing() {
    if( currentlyParsing==null) {
      currentlyParsing = new ArrayList();
    }
    return currentlyParsing;
  }
}
