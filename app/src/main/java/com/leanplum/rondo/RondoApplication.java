package com.leanplum.rondo;

import android.app.Application;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;

public class RondoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
//        LeanplumActivityHelper.enableLifecycleCallbacks(this);
    }
}
