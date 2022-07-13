package com.tsollu.retrofit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsollu.retrofit.context.DefaultRetrofitContext;
import com.tsollu.retrofit.context.RetrofitContext;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.util.List;
import java.util.Map;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static java.util.concurrent.TimeUnit.MINUTES;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnClass(Retrofit.class)
@EnableConfigurationProperties({RetrofitProperties.class})
public class TsolluRetrofitAutoConfiguration {

    private final List<Converter.Factory> converterFactories;
    private final OkHttpClient okHttpClient;
    private final RetrofitProperties retrofitProperties;

    public TsolluRetrofitAutoConfiguration(List<Converter.Factory> converterFactories,
                                           OkHttpClient okHttpClient, RetrofitProperties retrofitProperties) {
        this.converterFactories = converterFactories;
        this.okHttpClient = okHttpClient;
        this.retrofitProperties = retrofitProperties;
        this.checkConfiguredUrl(retrofitProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public RetrofitContext retrofitContext() {
        Retrofit.Builder builder = new Retrofit.Builder().validateEagerly(true);
        converterFactories.forEach(builder::addConverterFactory);

        if (okHttpClient != null) {
            builder.client(okHttpClient);
        }

        RetrofitContext context = new DefaultRetrofitContext();
        Map<String, String> endpoints = retrofitProperties.getEndpoints();

        endpoints.keySet().forEach(identity -> {
            context.register(identity, builder.baseUrl(endpoints.get(identity)).build());
        });

        return context;
    }

    private void checkConfiguredUrl(RetrofitProperties properties) {
        Map<String, String> endpoints = properties.getEndpoints();
        endpoints.keySet().forEach(identity -> {
            String url = endpoints.get(identity);
            Assert.isTrue(ResourceUtils.isUrl(url), url + " is not a valid url");
        });
    }

    @Configuration
    @ConditionalOnClass(OkHttpClient.class)
    public static class OkHttpClientConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ConnectionPool connectionPool() {
            return new ConnectionPool(10, 60, MINUTES);
        }

        @Bean
        @ConditionalOnMissingBean
        public OkHttpClient okHttpClient(ConnectionPool connectionPool) {
            OkHttpClient.Builder builder =
                new OkHttpClient.Builder().readTimeout(60, MINUTES)
                    .writeTimeout(60, MINUTES)
                    .connectTimeout(10, MINUTES)
                    .connectionPool(connectionPool);
            return builder.build();
        }
    }

    @Configuration
    @ConditionalOnClass(JacksonConverterFactory.class)
    public static class JacksonConverterFactoryConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ObjectMapper objectMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
            return objectMapper;
        }

        @Bean
        @ConditionalOnMissingBean
        public JacksonConverterFactory jacksonConverterFactory(ObjectMapper objectMapper) {
            return JacksonConverterFactory.create(objectMapper);
        }
    }

}
