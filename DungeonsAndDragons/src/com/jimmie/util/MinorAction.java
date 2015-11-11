package com.jimmie.util;

import java.lang.annotation.ElementType;
import com.jimmie.domain.PowerId;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinorAction {
	PowerId powerId();
}
