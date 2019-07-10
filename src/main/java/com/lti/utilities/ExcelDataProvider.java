package com.lti.utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ExcelDataProvider {
    String fileName() default "";
    String sheetName() default "";
    boolean hasHeaderRow() default false;
}
