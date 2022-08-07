package com.aman.contacttask.data.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String BASE_URL = "https://reqres.in/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    public static <S> S createService(
            Class<S> serviceClass) {

        return retrofit.create(serviceClass);
    }

}