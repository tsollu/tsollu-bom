package com.tsollu.retrofit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "tsollu.retrofit")
public class RetrofitProperties {

    private Map<String, String> endpoints = new HashMap<>();

}
