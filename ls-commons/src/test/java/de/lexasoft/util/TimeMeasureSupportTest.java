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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author nierax
 *
 */
class TimeMeasureSupportTest {

	private TimeMeasureSupport<String> cut;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cut = TimeMeasureSupport.of();
	}

	/**
	 * Test method for
	 * {@link de.lexasoft.util.TimeMeasureSupport#runProcess(de.lexasoft.util.TimeMeasureSupport.TimeComsumingProcess)}.
	 */
	@Test
	final void testRunProcessOk() {
		String processRun = cut.runProcess(() -> {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "run";
		});
		assertEquals("run", processRun);
		assertTrue(cut.getTimeElapsed() >= 5);
	}

	/**
	 * If an exception occurs under the process run, the time must be measured,
	 * anyway.
	 */
	@Test
	final void testRunProcessExceptionInProcess() {
		try {
			cut.runProcess(() -> {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				throw new IllegalArgumentException("Just to check...");
			});
		} catch (IllegalArgumentException e) {
			// Keep the test running
		}
		assertTrue(cut.getTimeElapsed() >= 5);
	}

	/**
	 * Test method for {@link de.lexasoft.util.TimeMeasureSupport#getTimeElapsed()}.
	 */
	@Test
	final void testGetTimeElapsedNotRun() {
		assertThrows(TimeMeasureException.class, () -> {
			cut.getTimeElapsed();
		});
	}

}
