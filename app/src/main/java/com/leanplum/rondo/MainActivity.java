package com.leanplum.rondo;

import android.content.Intent;
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
