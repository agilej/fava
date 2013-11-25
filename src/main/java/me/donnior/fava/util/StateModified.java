package me.donnior.fava.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 *One Annotation indicate that  method will modify self's state.  
 *
 */
@Target({ElementType.METHOD})
public @interface StateModified {

}
