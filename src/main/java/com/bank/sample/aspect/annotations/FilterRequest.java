package com.bank.sample.aspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE , ElementType.ANNOTATION_TYPE})
public @interface FilterRequest {

}
