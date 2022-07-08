package com.kaddo.components.retrofit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates an interface as Retrofit service.
 * <p>
 * Use this annotation to qualify a Retrofit annotated interface for auto-detection and automatic
 * instantiation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RetrofitClient {

    /**
     * Defines the name of retrofit should be used in building the service endpoint. Allows for more
     * * concise annotation declarations.
     *
     * @return the specified retrofit instance to build endpoint
     */
    String value();

}
