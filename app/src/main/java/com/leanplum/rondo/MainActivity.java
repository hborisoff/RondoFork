package com.leanplum.rondo;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.leanplum.Leanplum;
import com.leanplum.annotations.Parser;

public class MainActivity extends AppCompatActivity {

    protected GeoDataClient mGeoDataClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        populateVersionURLInfo();

        initLeanplum();
        createTriggersButton();
        createAppInboxButton();
        createVariablesButton();
        createMessagesButton();
        createPushButton();
        createAppDetailsButton();
        createAdhocButton();
    }

    private void populateVersionURLInfo() {
        RondoApplication app = (RondoApplication) this.getApplication();

        TextView tv = findViewById(R.id.sdkVersion);
        tv.setText(app.getSdkVersion());

        TextView apiUrl = findViewById(R.id.apiHostName);
        apiUrl.setText(app.getApiHostName());
    }

    private void initLeanplum() {
        RondoApplication app = (RondoApplication) this.getApplication();

        Leanplum.setAppIdForDevelopmentMode(
            app.getAppId(),
            BuildConfig.DEBUG ? app.getDevKey() : app.getProdKey()
        );
        Leanplum.setSocketConnectionSettings(app.getSocketHostName(), app.getSocketPort());
        Leanplum.setApiConnectionSettings(app.getApiHostName(), "api", app.getApiSSL());
        Parser.parseVariablesForClasses(VariablesActivity.class);

        // Enable for GCM
//        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
        Leanplum.setUserId(app.getUsername());
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
