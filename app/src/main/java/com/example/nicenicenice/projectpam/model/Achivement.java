package com.example.nicenicenice.projectpam.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by capri on 5/13/2017.
 */

public class Achivement extends RealmObject{

    @PrimaryKey
    private int idAvhivement;

    private String titleAchivement;
    private String description;
    private String xp;

    public Achivement()
    {

    }

    public Achivement(int idAvhivement, String titleAchivement, String description, String xp) {
        this.idAvhivement = idAvhivement;
        this.titleAchivement = titleAchivement;
        this.description = description;
        this.xp = xp;
    }

    public int getIdAvhivement() {
        return idAvhivement;
    }

    public void setIdAvhivement(int idAvhivement) {
        this.idAvhivement = idAvhivement;
    }

    public String getTitleAchivement() {
        return titleAchivement;
    }

    public void setTitleAchivement(String titleAchivement) {
        this.titleAchivement = titleAchivement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }
}
