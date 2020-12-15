package de.lexasoft.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * This class helps creating SimpleDateFormat instance with given Strings.
 * <p>
 * It has to be used as follows:
 * <ol>
 *  <li>Create an instance of the class.</li>
 *  <li>Set the values as needed by using the provided set methods.</li>
 *  <li>Create the format class by calling <code>createDocFormat()</code></li>
 * </ol>
 * Here is an example:
 * <code><br>
 *  ...<br>
 *  DateFormatCreator theCreator = new DateFormatCreator();<br>
 *  theCreator.setFormatString( "[dd/MMM/yyyy:HH:mm:ss Z]");<br>
 *  theCreator.setLocaleString( "en,UK");<br>
 *  theCreator.setTimeZoneString( "GMT");<br>
 *  SimpleDateFormat theFormat = theCreator.createDateFormat();<br>
 *  ...<br>
 * </code>
 * <p>
 * To get familiar with the formats of the input strings, please check the 
 * methods details for the set methods (see below). 
 * 
 * @author Axel Niering
 * @see DateFormatCreator#setFormatString(String)
 * @see DateFormatCreator#setLocaleString(String)
 * @see DateFormatCreator#setTimeZoneString(String)
 */
public class DateFormatCreator {
  
  /**
   * Stores the format string.
   */
  private String formatString;
  
  /**
   * Stores the String representation of the time zone.
   */
  private String timeZoneString;
  
  /**
   * Stores the String representation of the locale.
   */
  private String localeString;

  /**
   * This string describes the format of the time string to read or write. 
   * <p>
   * To get familiar with this string please check documentation for 
   * java.util.SimpleDateFormat, which, for instance, is available here:
   * <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/text/SimpleDateFormat.html" target="_blank"> 
   * http://java.sun.com/j2se/1.4.2/docs/api/java/text/SimpleDateFormat.html</a>
   * <p>
   * An example. Suppose, You have to read (or write) the following time stamp:
   * <code>[06/Sep/2004:06:39:42 +0200]</code>. You can now define a format 
   * string, describing the structure of the time stamp:<br>
   * <ul>
   *  <li>The <code>"["</code> is a not changing character, so simply write it.</li>
   *  <li>The <code>"06"</code> is the day of month, written as <code>"d"</code>. 
   *      As ist has two digits all the time, You must write it double: 
   *      <code>"dd"</code>. So our format string up to now is <code>"[dd"</code></li>
   *  <li>Next follows a slash, which has to be taken up: <code>"[dd/"</code></li>
   *  <li>Next part is the month in 3 letter code. This is coded with <code>"MMM"</code>.
   *      So add this to the format string: <code>"[dd/MMM"</code></li>
   *  <li>A slash again: <code>"[dd/MMM/"</code></li>
   *  <li>The year with four digits is coded with <code>"yyyy": "[dd/MMM/yyyy"</code></li>
   *  <li>Add the <code>":"</code> to the string: <code>"[dd/MMM/yyyy:"</code></li>
   *  <li>The next three parts are the time of day in hour (24 hours!), minute 
   *      and second, always with two digits. This is coded as follows: 
   *      <code>"HH:mm:ss"</code>. The hour part is written uppercase, because
   *      they are given as 24 hours a day. The format string up to here is:
   *      <code>"[dd/MMM/yyyy:HH:mm:ss"</code></li>
   *  <li>Add the space character: <code>"[dd/MMM/yyyy:HH:mm:ss "</code></li>
   *  <li>The last part describes the time zone. In this example it is two hours
   *      ahead to Greenwich Mean Time (GMT). This is coded simply as <code>"Z"</code>
   *      The format string up to now: <code>"[dd/MMM/yyyy:HH:mm:ss Z"</code></li>
   *  <li>End with the closing <code>"]" : "[dd/MMM/yyyy:HH:mm:ss Z]"</code></li>
   * </ul>
   *
   * @param aFormatString The format string to set.
   */
  public void setFormatString(String aFormatString) {
    this.formatString = aFormatString;
  }
  
  /**
   * The locale string consists of 3 parts, separated by ",". The first part is 
   * the ISO-639 lower case, two letter <b>language code</b>. Check this 
   * link for details:
   * <a href="http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt" target="_blank">
   * http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt</a>
   * <p>
   * The second part is the ISO-3166 upper case, two letter <b>country code</b>. 
   * You can find a description here:
   * <a href="http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt" target="_blank">
   * http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html</a>
   * <p>
   * The third part is a vendor or browser-specific code, such as "WIN" for
   * Windows, MAC for Macintosh or POSIX for POSIX. This part is mainly unused.
   * <p>
   * The first part is mandatory, the last ones are optional.
   * <p>
   * Examples:
   * <ul>
   *  <li>"en,GB," or "en,GB"for UK</li>
   *  <li>"en" for english without a specific country.</li>
   *  <li>"en,US" for USA</li>
   *  <li>"en,CA" for Canada</li>
   *  <li>"de" for german</li>
   *  <li>"de,DE" for Germany</li>
   * </ul> 
   * @param aLocaleString The localeString to set.
   */
  public void setLocaleString(String aLocaleString) {
    this.localeString = aLocaleString;
  }

  /**
   * This string describes the time zone to use. There are different ways to
   * do this. As this string is passed to the java.util.TimeZone class, please
   * check its documentation for details:
   * <a href"http://java.sun.com/j2se/1.4.2/docs/api/java/util/TimeZone.html" target="_blank">
   * http://java.sun.com/j2se/1.4.2/docs/api/java/util/TimeZone.html</a>
   * <p>
   * We recommend to use the three-letter time zone IDs, as they do describe
   * the daylight saving time correctly.
   *  
   * @param aTimeZoneString The timeZoneString to set.
   */
  public void setTimeZoneString(String aTimeZoneString) {
    this.timeZoneString = aTimeZoneString;
  }
  /**
   * @return Returns the formatString.
   */
  private String getFormatString() {
    return formatString;
  }
  /**
   * @return Returns the localeString.
   */
  private String getLocaleString() {
    return localeString;
  }
  /**
   * @return Returns the timeZoneString.
   */
  private String getTimeZoneString() {
    return timeZoneString;
  }
  
  /**
   * Creates a locale, corresponding to the String representation, set via 
   * setLocalString().
   * <p>
   * If no locale was set, the locale could not be recognized or any other error
   * occures, the default locale will be returned.
   * @return The locale, corresponding to the set String.
   */
  private Locale createLocale() {
    String theLocalString = getLocaleString();
    if( (theLocalString != null) && !"".equals( theLocalString)) {
      StringTokenizer theParts = new StringTokenizer( theLocalString, ",");
      if( theParts.countTokens()== 1) {
        return new Locale( theParts.nextToken().trim());
      }
      else if( theParts.countTokens()== 2) {
        return new Locale( theParts.nextToken().trim(), 
                           theParts.nextToken().trim());
      }
      else if( theParts.countTokens()== 3) {
        return new Locale( theParts.nextToken().trim(), 
                           theParts.nextToken().trim(), 
                           theParts.nextToken().trim());
      }
      else {
        System.err.println("malformed locale string: \"" + theLocalString + "\". Using default local instead.");
      }
    }
    return Locale.getDefault();
  }
  
  /**
   * Public method do create resulting DateFormat object.
   * @return A SimpleDateFormat object, corresponding to the values, set via the
   *         set methods.
   */
  public SimpleDateFormat createDateFormat() {
    SimpleDateFormat theDateFormat = 
                      new SimpleDateFormat( getFormatString(), createLocale());
    theDateFormat.setTimeZone( TimeZone.getTimeZone( getTimeZoneString()));
    return theDateFormat;
  }
}
