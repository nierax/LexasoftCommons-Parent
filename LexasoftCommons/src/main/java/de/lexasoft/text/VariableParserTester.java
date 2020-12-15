package de.lexasoft.text;

/**
 * Testing parser...
 * 
 * @author axel
 */
public class VariableParserTester {

  /**
   * Don't instantiate.
   */
  private VariableParserTester() {
    super();
  }

  public static void main(String[] args) {
    VariableParser theParser = new VariableParser();
    theParser.addVariable( "a", "Anton");
    theParser.addVariable( "b", "Berta");
    theParser.addVariable( "c", "$d");
    theParser.addVariable( "d", "$e");
    theParser.addVariable( "e", "Emilia");
    
    System.out.println( theParser.parse( "$a liebt $b, aber nicht $d, wohl aber seine 100.000$ auf dem Konto."));
  }
}
