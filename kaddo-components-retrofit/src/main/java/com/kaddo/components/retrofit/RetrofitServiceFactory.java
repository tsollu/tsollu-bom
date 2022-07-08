package com.kaddo.components.retrofit;

import com.kaddo.components.retrofit.context.RetrofitContext;

import retrofit2.Retrofit;

public class RetrofitServiceFactory {

    private final RetrofitContext retrofitContext;

    public RetrofitServiceFactory(final RetrofitContext retrofitContext) {
        this.retrofitContext = retrofitContext;
    }

    public <T> T createRetrofitService(String identity, Class<T> beanClass) {
        return getConfiguredRetrofit(identity).create(beanClass);
    }

    private Retrofit getConfiguredRetrofit(String identity) {
        return retrofitContext.find(identity).orElseThrow(() -> new RuntimeException(
            "Cannot obtain [" + identity + "] Retrofit in your application configuration."));
    }

}
