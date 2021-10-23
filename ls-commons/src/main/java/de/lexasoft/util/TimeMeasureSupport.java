/**
 * Copyright (C) 2021 nierax
 * This program is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU General Public License as published by the Free Software Foundation; either version 3 
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, see <http://www.gnu.org/licenses/>. 
 */
package de.lexasoft.util;

import java.time.Duration;
import java.time.Instant;

/**
 * Measures the time, a process needs to run.
 * <p>
 * Please note, that this class is not thread safe. Use an instance for each
 * measuring.
 * 
 * @author nierax
 * @param <T> the return type of the process run.
 */
public class TimeMeasureSupport<T> {

	interface TimeComsumingProcess<T> {
		T run();
	}

	private Instant start;
	private Instant finish;

	/**
	 * Don't instantiate via constructor. Use {@link #of()} instead.
	 */
	private TimeMeasureSupport() {
	}

	/**
	 * Create new instance of @TimeMeasureSupport
	 * 
	 * @return New instance of @TimeMeasureSupport
	 */
	public static <T> TimeMeasureSupport<T> of() {
		return new TimeMeasureSupport<>();
	}

	/**
	 * Run process and measure time it runs.
	 * 
	 * @param process
	 */
	public T runProcess(TimeComsumingProcess<T> process) {
		start = Instant.now();
		T result;
		try {
			result = process.run();
		} finally {
			finish = Instant.now();
		}
		return result;
	}

	private void checkState() {
		if (start == null) {
			throw new TimeMeasureException("Process has not been started yet.");
		} else if (finish == null) {
			throw new TimeMeasureException("Process is still running.");
		}
	}

	/**
	 * Gets the elapsed time of the last process running.
	 * <p>
	 * Throws an @TimeMeasureException, if the process has not been started yet or
	 * is still running
	 * 
	 * @return
	 */
	public double getTimeElapsed() {
		checkState();
		return Duration.between(start, finish).toMillis();
	}

}

/**
 * Exception, signalizing a problem measuring the elapsed time.
 * 
 * @author nierax
 */
@SuppressWarnings("serial")
class TimeMeasureException extends RuntimeException {

	/**
	 * 
	 * @param message
	 */
	public TimeMeasureException(String message) {
		super(message);
	}

}
