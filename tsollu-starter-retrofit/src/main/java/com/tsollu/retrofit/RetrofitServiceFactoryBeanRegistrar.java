package com.tsollu.retrofit;

import com.tsollu.retrofit.annotation.RetrofitClient;
import com.tsollu.retrofit.annotation.RetrofitClientScan;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetrofitServiceFactoryBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
                                        BeanDefinitionRegistry registry) {
        if (!registry.containsBeanDefinition(RetrofitServiceBeanPostProcessor.class.getSimpleName())) {
            registry.registerBeanDefinition(RetrofitServiceBeanPostProcessor.class.getSimpleName(),
                new RootBeanDefinition(RetrofitServiceBeanPostProcessor.class));
        }
        doRegisterRetrofitServiceBeanDefinitions(annotationMetadata, registry);
    }

    private void doRegisterRetrofitServiceBeanDefinitions(AnnotationMetadata annotationMetadata,
                                                          BeanDefinitionRegistry registry) {
        RetrofitServiceComponentProvider provider = RetrofitServiceComponentProvider.getInstance();

        Set<String> packagesToScan = getPackagesToScan(annotationMetadata);

        for (String packageToScan : packagesToScan) {
            log.debug("Trying to find candidates from package {}", packageToScan);
            Set<BeanDefinition> candidates = provider.findCandidateComponents(packageToScan);

            if (!CollectionUtils.isEmpty(candidates)) {
                processCandidates(candidates, registry);
            }
        }
    }

    private void processCandidates(Set<BeanDefinition> candidates, BeanDefinitionRegistry registry) {
        log.debug("Found {} Retrofit Service candidate(s)", candidates.size());

        for (BeanDefinition beanDefinition : candidates) {
            String beanName = generateBeanName(beanDefinition);

            log.debug("Processing candidate class {} with bean name {}",
                beanDefinition.getBeanClassName(), beanName);

            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes
            .fromMap(metadata.getAnnotationAttributes(RetrofitClientScan.class.getName()));

        String[] basePackages = attributes.getStringArray("basePackages");
        Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
        Set<String> packagesToScan = new LinkedHashSet<String>();

        if (!ObjectUtils.isEmpty(basePackages)) {
            packagesToScan.addAll(Arrays.asList(basePackages));
        }

        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }

        if (CollectionUtils.isEmpty(packagesToScan)) {
            return Collections.singleton(ClassUtils.getPackageName(metadata.getClassName()));
        }

        return packagesToScan;
    }

    private String generateBeanName(BeanDefinition beanDefinition) {
        // Try obtaining the client specified bean name if available in the annotated interface
        try {
            Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
            RetrofitClient retrofitService = beanClass.getAnnotation(RetrofitClient.class);
            if (retrofitService != null && StringUtils.hasText(retrofitService.value())) {
                return retrofitService.value();
            }

            // Reduce the conflict of same endpoint class name, use full package class name instead
            // So we wouldn't prefer to use AnnotationBeanNameGenerator
            return beanClass.getName();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot obtain bean name for Retrofit service interface", e);
        }
    }

}
