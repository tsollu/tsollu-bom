package com.kaddo.components.retrofit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "kaddo.retrofit")
public class RetrofitProperties {

    private Map<String, String> endpoints = new HashMap<>();

}
