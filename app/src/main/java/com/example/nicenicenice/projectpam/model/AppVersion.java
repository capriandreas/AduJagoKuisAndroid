package com.example.nicenicenice.projectpam.model;

import java.util.List;

/**
 * Created by capri on 5/13/2017.
 */

public class AppVersion {

    private String version;
    private String appName;
    private String lastUpdate;

    public AppVersion()
    {

    }

    public AppVersion(String version, String appName, String lastUpdate) {
        this.version = version;
        this.appName = appName;
        this.lastUpdate = lastUpdate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
