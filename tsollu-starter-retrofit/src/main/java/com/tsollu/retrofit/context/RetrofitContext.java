package com.tsollu.retrofit.context;

import java.util.Optional;

import retrofit2.Retrofit;

/**
 * The K,V store for retrofit instance, because the retrofit instance is immutable, and we couldn't
 * get some useful identify from it's public method.
 * <p>
 * In order to support multiply base url endpoint instance, we must create and store them
 * separately.
 */
public interface RetrofitContext {

    Retrofit register(String identity, Retrofit retrofit);

    Optional<Retrofit> find(String identity);

}
