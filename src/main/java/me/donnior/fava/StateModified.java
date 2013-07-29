package me.donnior.fava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 *One Annotation indicate that one method will modify some object's state.  
 *
 */
@Target({ElementType.METHOD})
public @interface StateModified {

}
