package com.leanplum.rondo;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;

import io.realm.Realm;

public class RondoApplication extends Application {

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

        Realm.init(this);
    }
}
