package br.com.bssistem.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Anderson Fonseca
 * */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface NotNullable { }
