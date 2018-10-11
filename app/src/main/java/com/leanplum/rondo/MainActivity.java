package com.leanplum.rondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.leanplum.Leanplum;
import com.leanplum.callbacks.StartCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLeanplum();
        createEventTriggerButton();
        createStateTriggerButton();
        createUserAttributeChangeButton();
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
        Leanplum.start(this);
    }

    private void createEventTriggerButton() {
        Button button = (Button) findViewById(R.id.triggerEvent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leanplum.track("testEvent");
            }
        });
    }

    private void createStateTriggerButton() {
        Button button = (Button) findViewById(R.id.triggerState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leanplum.advanceTo("testState");
            }
        });
    }

    private void createUserAttributeChangeButton() {
        Button button = (Button) findViewById(R.id.userAttributeChange);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map attrib = new HashMap();
                attrib.put("age", Double.toString(Math.random()));
                Leanplum.setUserAttributes(attrib);
            }
        });
    }


}
