package com.leanplum.rondo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.rondo.models.InternalState;
import com.leanplum.rondo.models.LeanplumApp;
import com.leanplum.rondo.models.LeanplumEnvironment;

public class AppDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        InternalState state = InternalState.sharedState();
        LeanplumApp app = state.app;
        LeanplumEnvironment env = state.env;

        ((TextView)findViewById(R.id.appId)).setText(app.getAppId());
        ((TextView)findViewById(R.id.devKey)).setText(app.getDevKey());
        ((TextView)findViewById(R.id.prodKey)).setText(app.getProdKey());

        ((TextView)findViewById(R.id.userId)).setText(Leanplum.getUserId());
        ((TextView)findViewById(R.id.deviceId)).setText(Leanplum.getDeviceId());

//        ((TextView)findViewById(R.id.sdkVersion)).setText(env.getSdkVersion());
        ((TextView)findViewById(R.id.apiHostName)).setText(env.getApiHostName());
        ((TextView)findViewById(R.id.apiSSL)).setText(env.getApiSSL().toString());
        ((TextView)findViewById(R.id.socketHostName)).setText(env.getSocketHostName());
        ((TextView)findViewById(R.id.socketPort)).setText(String.valueOf(env.getSocketPort()));
    }
}
