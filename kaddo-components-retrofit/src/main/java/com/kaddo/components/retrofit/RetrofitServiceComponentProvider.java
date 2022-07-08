package com.kaddo.components.retrofit;

import com.kaddo.components.retrofit.annotation.RetrofitClient;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class RetrofitServiceComponentProvider extends ClassPathScanningCandidateComponentProvider {

    private RetrofitServiceComponentProvider() {
        super(false);
        addIncludeFilter(new AnnotationTypeFilter(RetrofitClient.class, true, true));
    }

    public static RetrofitServiceComponentProvider getInstance() {
        return new RetrofitServiceComponentProvider();
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

}
