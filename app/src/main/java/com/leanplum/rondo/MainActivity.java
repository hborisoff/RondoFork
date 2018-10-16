package com.leanplum.rondo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLeanplum();
        createTriggersButton();
        createAppInboxButton();
        createVariablesButton();

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    private static final String PERMISSION = "android.permission.ACCESS_FINE_LOCATION";
    private static final String METADATA = "com.google.android.gms.version";

    private boolean isPermissionGranted() {
        Context context = Leanplum.getContext();
        try {
            return context.checkCallingOrSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    private boolean isMetaDataSet() {
        Context context = Leanplum.getContext();
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                if (appInfo.metaData != null) {
                    Object value = appInfo.metaData.get(METADATA);
                    if (value != null) {
                        return true;
                    }
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void initLeanplum() {
        // Insert your API keys here.
        String appId = "app_ve9UCNlqI8dy6Omzfu1rEh6hkWonNHVZJIWtLLt6aLs";
        String devKey = "dev_cKF5HMpLGqhbovlEGMKjgTuf8AHfr2Jar6rrnNhtzQ0";
        String prodKey = "prod_D5ECYBLrRrrOYaFZvAFFHTg1JyZ2Llixe5s077Lw3rM";

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode(appId, devKey);
        } else {
            Leanplum.setAppIdForProductionMode(appId, prodKey);
        }
        Parser.parseVariablesForClasses(VariablesActivity.class);

        // Enable for GCM
//        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }


    private void createTriggersButton() {
        Button button = findViewById(R.id.triggers);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TriggersActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void createAppInboxButton() {
        Button button = findViewById(R.id.appInbox);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AppInboxActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void createVariablesButton() {
        Button button = findViewById(R.id.variables);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, VariablesActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

}
