package com.tsollu.retrofit.annotation;

import com.tsollu.retrofit.RetrofitServiceFactoryBeanRegistrar;

import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
@Import(RetrofitServiceFactoryBeanRegistrar.class)
public @interface RetrofitClientScan {

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] basePackages() default {};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackageClasses")
    Class<?>[] basePackageClasses() default {};

}
