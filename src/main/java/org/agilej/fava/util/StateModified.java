package org.agilej.fava.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation to indicate the method will change self's state.
 *
 */
@Target({ElementType.METHOD})
public @interface StateModified {

}
