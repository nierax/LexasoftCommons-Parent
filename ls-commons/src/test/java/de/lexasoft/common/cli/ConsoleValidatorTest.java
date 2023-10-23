/**
 * 
 */
package de.lexasoft.common.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.InputMismatchException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.lexasoft.common.model.Validator;

/**
 * @author nierax
 *
 */
@ExtendWith(MockitoExtension.class)
class ConsoleValidatorTest {

	ConsoleValidator cut;

	@Mock
	ConsoleReader<Integer> consoleReader;

	@Mock
	Validator<Integer> validator;

	@BeforeEach
	void init() {
		cut = new ConsoleValidator();
	}

	/**
	 * Scenario:
	 * <p>
	 * 1) call with 3 => validate false => next try must follow
	 * <p>
	 * 2) call with 6 => validate ok => value must be returned.
	 * 
	 * <p>
	 * Test method for
	 * {@link de.lexasoft.common.cli.ConsoleValidator#fromConsole(java.lang.String, de.lexasoft.common.cli.ConsoleReader, de.lexasoft.common.model.Validator)}.
	 */
	@Test
	final void testFromConsole() {
		// Setup
		when(consoleReader.nextInput()).thenReturn(3, 6);
		when(validator.validate(3)).thenReturn(false);
		when(validator.validate(6)).thenReturn(true);

		// Test
		Integer received = cut.fromConsole("Anything to enter", consoleReader, validator);

		// Check
		assertEquals(6, received, "Did not get proper value.");
		verify(consoleReader, times(2)).nextInput();
		verify(validator).validate(6);
	}

	/**
	 * Scenario:
	 * <p>
	 * 1) call to consoleReader => {@link InputMismatchException} is thrown. Input
	 * must be repeated.
	 * <p>
	 * 2) call with 6 => validate ok => value must be returned.
	 * 
	 * <p>
	 * Test method for
	 * {@link de.lexasoft.common.cli.ConsoleValidator#fromConsole(java.lang.String, de.lexasoft.common.cli.ConsoleReader, de.lexasoft.common.model.Validator)}.
	 */
	@Test
	final void test_from_console_gets_InputMismatchException() {
		// Setup
		when(consoleReader.nextInput())//
		    .thenThrow(InputMismatchException.class)//
		    .thenReturn(6);//
		when(validator.validate(6)).thenReturn(true);

		// Test
		Integer received = cut.fromConsole("Anything to enter", consoleReader, validator);

		// Check
		assertEquals(6, received, "Did not get proper value.");
		verify(consoleReader, times(2)).nextInput();
		verify(validator).validate(6);

	}

}
