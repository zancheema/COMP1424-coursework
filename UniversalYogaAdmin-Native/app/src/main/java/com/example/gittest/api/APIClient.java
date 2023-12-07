package com.example.gittest.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;
//    private static final String BASE_URL = "https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw/";
    private static final String BASE_URL = "http://192.168.1.97:8080";

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
