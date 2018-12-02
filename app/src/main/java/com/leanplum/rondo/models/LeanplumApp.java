package com.leanplum.rondo.models;

public class LeanplumApp {
    private String appId;
    private String devKey;
    private String prodKey;

    static public LeanplumApp rondoQAProduction() {
        LeanplumApp app = new LeanplumApp();
        app.appId = "app_ve9UCNlqI8dy6Omzfu1rEh6hkWonNHVZJIWtLLt6aLs";
        app.devKey = "dev_cKF5HMpLGqhbovlEGMKjgTuf8AHfr2Jar6rrnNhtzQ0";
        app.prodKey = "prod_D5ECYBLrRrrOYaFZvAFFHTg1JyZ2Llixe5s077Lw3rM";
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
}
