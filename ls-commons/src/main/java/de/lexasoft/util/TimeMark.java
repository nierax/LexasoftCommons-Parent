package de.lexasoft.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Marks a timestamp and gives information as difference to the timemark.
 * 
 * @author Axel Niering
 */
public class TimeMark {
  
  /**
   * Default format description, which is used, when method formatTimeRun() is
   * called.
   * @see TimeMark#formatTimeRun()
   */
  public static final String DEFAULT_TIME_DIFFERENCE_FORMAT ="mm:ss,SSS";
  
  /**
   * Marks the time, the object was created.
   */
  private Calendar timeMark;

  /**
   * Creates theTimeMark with current time.
   */
  public TimeMark() {
    super();
    timeMark = Calendar.getInstance();
  }

  /**
   * Creates theTimeMark with time in aTime. 
   * <p>
   * The parameter aTime will not be referenced in this object. New Calendar
   * will be used instead.
   * @param aTime Reference time to use.
   */
  public TimeMark( Calendar aTime) {
    this();
    timeMark.setTime( aTime.getTime());
  }

  /**
   * Supplies new Calendar object with difference time from now to time mark.
   * @return Calendar object with time, run from mark.
   */
  public Calendar getTimeRun() {
    Calendar theNow = Calendar.getInstance();
    long theDiffInMillis = theNow.getTimeInMillis() - timeMark.getTimeInMillis();
    Calendar theDiff = Calendar.getInstance();
    theDiff.setTimeInMillis( theDiffInMillis);
    return theDiff;
  }
  
  /**
   * Formats the time difference from now to the timemark with the given format
   * String.
   * <p>
   * The format string must correspond to the definitions in java.text package.
   * @param aFormatString The format description.
   * @return The time run from mark, formatted as described in format.
   * @see java.text.SimpleDateFormat
   */
  public String formatTimeRun( String aFormatString) {
    SimpleDateFormat theFormatter = new SimpleDateFormat( aFormatString);
    return theFormatter.format( getTimeRun().getTime());
  }
  
  /**
   * Formats the time difference from now to the timemark with the default format
   * String.
   * @return The time run from mark, formatted as described in default format.
   */
  public String formatTimeRun() {
    return formatTimeRun( DEFAULT_TIME_DIFFERENCE_FORMAT);
  }
}
