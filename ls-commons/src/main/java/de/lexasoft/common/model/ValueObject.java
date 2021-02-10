package de.lexasoft.common.model;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * Signalizes any object being a value object.
 * 
 * @author nierax
 *
 */
@Documented
@Target(TYPE)
public @interface ValueObject {

}
