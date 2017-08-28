package com.example.nicenicenice.projectpam.interfaces;

/**
 * Created by capri on 5/13/2017.
 */


import java.util.List;
import retrofit2.Call;
import retrofit2.http.POST;
import com.example.nicenicenice.projectpam.controller.ResponseLeaderboard;

public interface APIHasilLeaderboard {

    /*URL Web service: /hasil */
    @POST("/adujagokuis/hasil")
    Call<ResponseLeaderboard> getHasilLeaderboard();
}
