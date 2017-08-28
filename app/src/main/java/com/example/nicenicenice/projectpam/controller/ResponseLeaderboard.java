package com.example.nicenicenice.projectpam.controller;
import com.example.nicenicenice.projectpam.model.ModelHasilLeaderboard;

/**
 * Created by capri on 5/13/2017.
 */

import java.util.List;

public class ResponseLeaderboard {
    private String error;
    private List<ModelHasilLeaderboard> hasil;


    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public List<ModelHasilLeaderboard> getHasil() {
        return hasil;
    }
    public void setHasil(List<ModelHasilLeaderboard> hasil) {
        this.hasil = hasil;
    }
}

