package com.tsollu.aspectj;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import({CatchAndLogAspect.class})
public class AspectJAutoConfiguration {
}
