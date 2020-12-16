/*
 * Created on 16.12.2004
 * 
 * (c) www.lexasoft.de 2004
 */
package de.lexasoft.util;

/**
 * This interface represents a task which might take a longer period to run and
 * therefore some observation is needed.
 * <p>
 * This could be, for example, using a progress bar to see, how far it has run.
 * 
 * @author Axel Niering
 */
public interface LongTaskObserver {
  
  /**
   * This returns the maximum amount, the task can reach.
   * <p>
   * This can be any digit, being in range of int. The amount itself is not
   * important.
   * 
   * @return Maximum amount, the task can reach.
   */
  public int getLengthOfTask();
  
  /**
   * This returns the minimum amount, the task has. Normally this is the value,
   * the task starts with.
   * 
   * @return The value, the task starts with.
   */
  public int getStartOfTask();
  
  /**
   * Tells You, how far this task is gone. Should always be between <code>"0"</code>
   * and <code>getLengthOfTask()</code>.
   *  
   * @return The progress made so far.
   */
  public int getCurrent(); 
  
  /**
   * Returns true if the task has run completely and successfully, false 
   * otherwise.
   * <p>
   * If the task was canceled, the method returns false as well. 
   * @return true, if the task is finished.
   */
  public boolean isDone();
  
  /**
   * Returns a message, which might be displayed to the user to explain, what
   * is going on.
   * <p>
   * This information will not change throughout the process.
   * @return A message to explain th user, what is going on.
   */
  public String getMessage();

}
