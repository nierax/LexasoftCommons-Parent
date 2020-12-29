package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ValueTest {

  /**
   * Validator says ok, so the value must be set.
   */
  @Test
  void testSetValueOk() {
    Value<Integer> cut = new Value<>((value) -> {
      return true;
    });
    Integer value = cut.setValue(2);
    assertEquals(cut.getValue(), 2);
    assertSame(value, cut.getValue(), "The value returned must be the one, inserted.");
    assertTrue(cut.hasValue(), "Value must be set");
  }

  /**
   * Validator says not ok, an exception is expected.
   */
  @Test
  void testSetValueNok() {
    Value<Integer> cut = new Value<>((value) -> {
      return false;
    });
    assertThrows(IllegalArgumentException.class, () -> {
      cut.setValue(2);
    });
  }

  /**
   * Value must be confirmed being unset, if no value is given with the
   * constructor.
   */
  @Test
  void testConstructor_WithoutInitialValue() {
    Value<Integer> cut = new Value<>();
    assertFalse(cut.hasValue(), "Value must be unset");
  }

  /**
   * Value must be confirmed being set, if a value is given with the constructor.
   */
  @Test
  void testConstructor_WithInitialValue() {
    Value<Integer> cut = new Value<>(2);
    assertTrue(cut.hasValue(), "Value must be set");
  }

  /**
   * Validator says not ok, an exception is expected, when given with the
   * constructor.
   */
  @Test
  void testConstructor_ValidatorNok() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Value<>((value) -> {
        return false;
      }, 2);
    });
  }

  /**
   * The value must be confirmed not set after initializing, set after setting
   * value and false again after unsetting.
   */
  void testUnsetValue() {
    Value<Integer> cut = new Value<>((value) -> {
      return true;
    });
    assertFalse(cut.hasValue(), "Value must be unset");
    cut.setValue(2);
    assertTrue(cut.hasValue(), "Value must be set");
    cut.unsetValue();
    assertFalse(cut.hasValue(), "Value must be unset");
  }

  private static Stream<Arguments> testEquals() {
    return Stream.of(Arguments.of(1, 1, true), Arguments.of(0, 1, false), Arguments.of(null, null, true),
        Arguments.of(null, 1, false), Arguments.of(1, null, false));
  }

  @ParameterizedTest
  @MethodSource
  void testEquals(Integer val1, Integer val2, boolean result) {
    Value<Integer> cut1 = new Value<Integer>(val1);
    Value<Integer> cut2 = new Value<Integer>(val2);

    assertEquals(result, cut1.equals(cut2), "equals() did not respond correctly");
  }
}
