package com.leanplum.rondo;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.annotations.Parser;
import com.leanplum.rondo.models.InternalState;
import com.leanplum.rondo.models.LeanplumApp;
import com.leanplum.rondo.models.LeanplumEnvironment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        setUpAppState();
        populateVersionURLInfo();

        initLeanplum();
        createStartButton();
        createTriggersButton();
        createAppInboxButton();
        createVariablesButton();
        createMessagesButton();
        createPushButton();
        createAppDetailsButton();
        createAdhocButton();
        createAppPickerButton();
        createEnvPickerButton();
    }

    private void populateVersionURLInfo() {
        InternalState state = InternalState.sharedState();
        LeanplumApp app = state.getApp();
        LeanplumEnvironment env = state.getEnv();

        TextView tv = findViewById(R.id.sdkVersion);
        tv.setText(BuildConfig.LEANPLUM_SDK_VERSION);

        TextView tv1 = findViewById(R.id.appName);
        tv1.setText(app.getDisplayName());

        TextView apiUrl = findViewById(R.id.apiHostName);
        apiUrl.setText(env.getApiHostName());
    }

    private void setUpAppState() {
        InternalState state = InternalState.sharedState();
        state.setApp(LeanplumApp.rondoQAProduction());
        state.setEnv(LeanplumEnvironment.production());
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

    private void createStartButton() {
        Button button = findViewById(R.id.call_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLeanplum();
            }
        });
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

    private void createMessagesButton() {
        Button button = findViewById(R.id.messages);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MessagesActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void createPushButton() {
        Button button = findViewById(R.id.push);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, PushActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void createAppDetailsButton() {
        Button button = findViewById(R.id.appDetails);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AppDetailsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void createAdhocButton() {
        Button button = findViewById(R.id.adhoc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AdhocActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void createAppPickerButton() {
        Button button = findViewById(R.id.app_picker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, LeanplumAppPickerActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
    private void createEnvPickerButton() {
        Button button = findViewById(R.id.env_picker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, LeanplumEnvPickerActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
