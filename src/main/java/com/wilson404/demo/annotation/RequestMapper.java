package com.wilson404.demo.annotation;

import com.wilson404.demo.base.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapper {
    String uri();
    HttpMethod httpMethod();
}
