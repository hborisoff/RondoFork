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
    private String apiHostName = "leanplum-qa-1372.appspot.com";
    private Boolean apiSSL = true;
    private String socketHostName = "dev-qa.leanplum.com";
    private int socketPort = 80;

    /**
     * Insert your API keys here.
     */
    private String appId = "app_7AQjDdQHfMaPAohBQP2QoCj8tLfxRMmt6VPNht4DUsU";
    private String devKey = "dev_JxVMcJSqyWAyLZw9cB9m90NqTmgxmWnLWQH3X1qXySg";
    private String prodKey = "prod_aYkcw8AA9AYbQCQWiNgUV1EiDvseN7ZAoB1SNxKnphw";

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
