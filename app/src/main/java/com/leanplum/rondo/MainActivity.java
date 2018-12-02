package com.leanplum.rondo;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        initLeanplum();
        createTriggersButton();
        createAppInboxButton();
        createVariablesButton();
        createMessagesButton();
        createPushButton();
        createAppDetailsButton();
        createAdhocButton();
    }

    private void setUpAppState() {
        InternalState state = InternalState.sharedState();
        state.app = LeanplumApp.rondoQAProduction();
        state.env = LeanplumEnvironment.production();
    }

    private void initLeanplum() {
        InternalState state = InternalState.sharedState();

        LeanplumApp app = state.app;

        Leanplum.setAppIdForDevelopmentMode(
            app.getAppId(),
            BuildConfig.DEBUG ? app.getDevKey() : app.getProdKey()
        );

        LeanplumEnvironment env = state.env;

        Leanplum.setSocketConnectionSettings(env.getSocketHostName(), env.getSocketPort());
        Leanplum.setApiConnectionSettings(env.getApiHostName(), "api", env.getApiSSL());
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
}
