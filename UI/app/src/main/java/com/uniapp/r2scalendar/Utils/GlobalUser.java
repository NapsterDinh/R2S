package com.uniapp.r2scalendar.Utils;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalUser {
    private static HashMap<String,String> hashMap;

    public static  HashMap<String,String> getInstance() {
        if (hashMap  == null) {
            hashMap = new HashMap<String,String>();
        }
        return hashMap;
    }
}
