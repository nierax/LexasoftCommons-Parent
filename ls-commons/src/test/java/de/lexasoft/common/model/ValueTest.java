package de.lexasoft.common.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ValueTest {

  /**
   * Validator says ok, so the value must be set.
   */
  @Test
  void testSetValueOk() {
    Value<Integer> cut = new Value<>((value) -> {
      return true;
    });
    cut.setValue(2);
    assertEquals(cut.getValue(), 2);
    assertTrue("Value must be set", cut.hasValue());
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
    assertFalse("Value must be unset", cut.hasValue());
  }

  /**
   * Value must be confirmed being set, if a value is given with the constructor.
   */
  @Test
  void testConstructor_WithInitialValue() {
    Value<Integer> cut = new Value<>(2);
    assertTrue("Value must be set", cut.hasValue());
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
    assertFalse("Value must be unset", cut.hasValue());
    cut.setValue(2);
    assertTrue("Value must be set", cut.hasValue());
    cut.unsetValue();
    assertFalse("Value must be unset", cut.hasValue());
  }
}
