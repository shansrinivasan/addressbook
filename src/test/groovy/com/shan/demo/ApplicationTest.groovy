package com.shan.demo

import java.lang.annotation.*

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApplicationTest {

}