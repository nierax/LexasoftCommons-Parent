/**
 * 
 */
package de.lexasoft.common.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.lexasoft.common.model.Validator;

/**
 * @author Axel
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
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
  @SuppressWarnings("unchecked")
  @Test
  final void testFromConsole() {
    // Setup
    consoleReader = mock(ConsoleReader.class);
    when(consoleReader.nextInput()).thenReturn(3, 6);
    validator = mock(Validator.class);
    when(validator.validate(3)).thenReturn(false);
    when(validator.validate(6)).thenReturn(true);

    // Test
    Integer received = cut.fromConsole("Anything to enter", consoleReader, validator);

    // Check
    assertEquals(6, received, "Did not get proper value.");
    verify(consoleReader, times(2)).nextInput();
    verify(validator).validate(6);
  }

}
