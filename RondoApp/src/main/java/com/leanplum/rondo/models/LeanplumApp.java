package com.leanplum.rondo.models;

import io.realm.RealmObject;

public class LeanplumApp extends RealmObject {
    private String appId;
    private String devKey;
    private String prodKey;
    private String displayName;

    static public LeanplumApp rondoQAProduction() {
        LeanplumApp app = new LeanplumApp();
        app.appId = "app_ve9UCNlqI8dy6Omzfu1rEh6hkWonNHVZJIWtLLt6aLs";
        app.devKey = "dev_cKF5HMpLGqhbovlEGMKjgTuf8AHfr2Jar6rrnNhtzQ0";
        app.prodKey = "prod_D5ECYBLrRrrOYaFZvAFFHTg1JyZ2Llixe5s077Lw3rM";
        app.displayName = "Rondo QA Production";
        return app;
    }

    public String getAppId() {
        return appId;
    }

    public String getDevKey() {
        return devKey;
    }

    public String getProdKey() {
        return prodKey;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setDevKey(String devKey) {
        this.devKey = devKey;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setProdKey(String prodKey) {
        this.prodKey = prodKey;
    }
}
