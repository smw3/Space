package de.schaf.space;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( java.lang.annotation.ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface KeyInput {
	enum State { PRESS, DOWN }
	
	int value();
	State state() default State.DOWN;
	
}
