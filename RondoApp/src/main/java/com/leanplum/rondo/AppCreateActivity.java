package com.leanplum.rondo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.annotations.Parser;
import com.leanplum.rondo.models.InternalState;
import com.leanplum.rondo.models.LeanplumApp;
import com.leanplum.rondo.models.LeanplumEnvironment;

public class AppCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_create);

        createButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void createButton() {
        Button button = findViewById(R.id.create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeanplumApp app = createApp();
                LeanplumAppPersistence.saveLeanplumApp(app);
                finish();
            }
        });
    }

    private void validateInput() {
        EditText displayNameET = findViewById(R.id.displayName);
        String displayName = displayNameET.getText().toString();

        EditText appIdET= findViewById(R.id.appId);
        String appId = appIdET.getText().toString();

        EditText devKeyET = findViewById(R.id.devKey);
        String devKey = devKeyET.getText().toString();

        EditText prodKeyET = findViewById(R.id.prodKey);
        String prodKey = prodKeyET.getText().toString();
    }

    private LeanplumApp createApp() {
        EditText displayNameET = findViewById(R.id.displayName);
        String displayName = displayNameET.getText().toString();

        EditText appIdET= findViewById(R.id.appId);
        String appId = appIdET.getText().toString();

        EditText devKeyET = findViewById(R.id.devKey);
        String devKey = devKeyET.getText().toString();

        EditText prodKeyET = findViewById(R.id.prodKey);
        String prodKey = prodKeyET.getText().toString();

        LeanplumApp app = new LeanplumApp();
        app.setDisplayName(displayName);
        app.setAppId(appId);
        app.setDevKey(devKey);
        app.setProdKey(prodKey);
        return app;
    }
}
