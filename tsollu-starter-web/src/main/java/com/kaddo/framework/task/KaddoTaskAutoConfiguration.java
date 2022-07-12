package com.kaddo.framework.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
@Import(KaddoExecutorService.class)
public class KaddoTaskAutoConfiguration {

}
