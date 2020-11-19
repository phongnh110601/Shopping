package com.nhpva.shopping.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductClient {
    private static Retrofit INSTANCE;
    private ProductClient(){

    }

    public static Retrofit getInstance(String url){
        if (INSTANCE == null){
            INSTANCE = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
