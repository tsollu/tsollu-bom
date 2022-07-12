package com.tsollu.retrofit.context;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;

public class DefaultRetrofitContext extends ConcurrentHashMap<String, Retrofit>
    implements RetrofitContext {

    private static final long serialVersionUID = 1L;

    @Override
    public Retrofit register(String identity, Retrofit retrofit) {
        return put(identity, retrofit);
    }

    @Override
    public Optional<Retrofit> find(String identity) {
        return Optional.ofNullable(get(identity));
    }

}
