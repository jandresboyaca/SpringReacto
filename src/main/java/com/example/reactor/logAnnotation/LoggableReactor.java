package com.example.reactor.logAnnotation;

import jdk.jfr.Experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Experimental
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggableReactor {
}
