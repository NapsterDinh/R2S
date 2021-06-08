package com.uniapp.r2scalendar.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit Client() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                        .baseUrl(ConfigEnum.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
        }

        return retrofit;
    }
}
