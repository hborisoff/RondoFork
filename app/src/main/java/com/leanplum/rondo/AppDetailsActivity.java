package com.leanplum.rondo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.leanplum.Leanplum;

public class AppDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        RondoApplication app = (RondoApplication) this.getApplication();

        ((TextView)findViewById(R.id.appId)).setText(app.getAppId());
        ((TextView)findViewById(R.id.devKey)).setText(app.getDevKey());
        ((TextView)findViewById(R.id.prodKey)).setText(app.getProdKey());

        ((TextView)findViewById(R.id.userId)).setText(Leanplum.getUserId());
        ((TextView)findViewById(R.id.deviceId)).setText(Leanplum.getDeviceId());

        ((TextView)findViewById(R.id.sdkVersion)).setText(app.getSdkVersion());
        ((TextView)findViewById(R.id.apiHostName)).setText(app.getApiHostName());
        ((TextView)findViewById(R.id.apiSSL)).setText(app.getApiSSL().toString());
        ((TextView)findViewById(R.id.socketHostName)).setText(app.getSocketHostName());
        ((TextView)findViewById(R.id.socketPort)).setText(app.getSocketPort().toString());
    }
}
