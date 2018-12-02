package com.leanplum.rondo;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;

public class RondoApplication extends Application {

    /**
     * Add your SDK version and API/Socket settings here
     */
    private String sdkVersion = "4.2";
    private String apiHostName = "api.leanplum.com";
    private Boolean apiSSL = true;
    private String socketHostName = "dev.leanplum.com";
    private int socketPort = 443;

    /**
     * Insert your API keys here.
     */
    private String appId = "app_ve9UCNlqI8dy6Omzfu1rEh6hkWonNHVZJIWtLLt6aLs";
    private String devKey = "dev_cKF5HMpLGqhbovlEGMKjgTuf8AHfr2Jar6rrnNhtzQ0";
    private String prodKey = "prod_D5ECYBLrRrrOYaFZvAFFHTg1JyZ2Llixe5s077Lw3rM";

    /**
     * Update user info here
     */
    private String username = "some-qa-user@leanplum.com";

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);

        Leanplum.setApplicationContext(this);

        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getApiHostName() {
        return this.apiHostName;
    }

    public Boolean getApiSSL() {
        return this.apiSSL;
    }

    public String getSocketHostName() {
        return this.socketHostName;
    }

    public Integer getSocketPort() {
        return this.socketPort;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getDevKey() {
        return this.devKey;
    }

    public String getProdKey() {
        return this.prodKey;
    }

    public String getUsername() {
        return this.username;
    }
}
