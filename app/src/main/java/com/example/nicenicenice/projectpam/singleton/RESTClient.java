package com.example.nicenicenice.projectpam.singleton;

/**
 * Created by capri on 5/13/2017.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.nicenicenice.projectpam.interfaces.APIHasilLeaderboard;

public class RESTClient {
    private static APIHasilLeaderboard REST_CLIENT;

    static { //dieksekusi sebelum constructor, tapi hanya sekali untuk semua instans
        setupRestClient();
    }
    private RESTClient() {}
    public static APIHasilLeaderboard get() {
        return REST_CLIENT;
    }
    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.219.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(APIHasilLeaderboard.class);
    }
}
