package com.leanplum.rondo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.annotations.Parser;
import com.leanplum.rondo.models.InternalState;
import com.leanplum.rondo.models.LeanplumApp;
import com.leanplum.rondo.models.LeanplumEnvironment;

public class AppSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setup);

        createStartButton();
        createAppPickerButton();
        createEnvPickerButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateAppDetails();
    }

    private void createAppPickerButton() {
        Button button = findViewById(R.id.app_picker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AppSetupActivity.this, LeanplumAppPickerActivity.class);
                AppSetupActivity.this.startActivity(myIntent);
            }
        });
    }
    private void createEnvPickerButton() {
        Button button = findViewById(R.id.env_picker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AppSetupActivity.this, LeanplumEnvPickerActivity.class);
                AppSetupActivity.this.startActivity(myIntent);
            }
        });
    }

    private void createStartButton() {
        Button button = findViewById(R.id.call_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLeanplum();
            }
        });
    }


    private void populateAppDetails() {
        InternalState state = InternalState.sharedState();
        LeanplumApp app = state.getApp();
        LeanplumEnvironment env = state.getEnv();

        ((TextView)findViewById(R.id.appId)).setText(app.getAppId());
        ((TextView)findViewById(R.id.devKey)).setText(app.getDevKey());
        ((TextView)findViewById(R.id.prodKey)).setText(app.getProdKey());

        ((TextView)findViewById(R.id.userId)).setText(Leanplum.getUserId());
        ((TextView)findViewById(R.id.deviceId)).setText(Leanplum.getDeviceId());

        ((TextView)findViewById(R.id.sdkVersion)).setText(BuildConfig.LEANPLUM_SDK_VERSION);
        ((TextView)findViewById(R.id.apiHostName)).setText(env.getApiHostName());
        ((TextView)findViewById(R.id.apiSSL)).setText(env.getApiSSL().toString());
        ((TextView)findViewById(R.id.socketHostName)).setText(env.getSocketHostName());
        ((TextView)findViewById(R.id.socketPort)).setText(String.valueOf(env.getSocketPort()));
    }

    private void initLeanplum() {
        InternalState state = InternalState.sharedState();

        LeanplumApp app = state.getApp();

        Leanplum.setAppIdForDevelopmentMode(
                app.getAppId(),
                BuildConfig.DEBUG ? app.getDevKey() : app.getProdKey()
        );

        LeanplumEnvironment env = state.getEnv();

        Leanplum.setSocketConnectionSettings(env.getSocketHostName(), env.getSocketPort());
        Leanplum.setApiConnectionSettings(env.getApiHostName(), "api", env.getApiSSL());
        Parser.parseVariablesForClasses(VariablesActivity.class);

        // Enable for GCM
//        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }

}
