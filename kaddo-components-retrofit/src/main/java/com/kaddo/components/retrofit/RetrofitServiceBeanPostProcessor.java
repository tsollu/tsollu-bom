package com.kaddo.components.retrofit;

import com.kaddo.components.retrofit.annotation.RetrofitClient;
import com.kaddo.components.retrofit.context.RetrofitContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.Assert;

public class RetrofitServiceBeanPostProcessor
    implements
    InstantiationAwareBeanPostProcessor,
    BeanFactoryAware,
    PriorityOrdered {

    private BeanFactory beanFactory;
    private RetrofitServiceFactory retrofitServiceFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE - 1;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
        throws BeansException {
        if (beanClass.isAnnotationPresent(RetrofitClient.class)) {
            String identity = beanClass.getAnnotation(RetrofitClient.class).value();
            return getRetrofitServiceFactory().createRetrofitService(identity, beanClass);
        }
        return null;
    }

    private RetrofitServiceFactory getRetrofitServiceFactory() {
        Assert.notNull(beanFactory, "BeanFactory can not be null");
        if (retrofitServiceFactory == null) {
            RetrofitContext context = beanFactory.getBean(RetrofitContext.class);
            retrofitServiceFactory = new RetrofitServiceFactory(context);
        }
        return retrofitServiceFactory;
    }
}
