package com.leanplum.rondo;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.annotations.Parser;
import com.leanplum.rondo.models.InternalState;
import com.leanplum.rondo.models.LeanplumApp;
import com.leanplum.rondo.models.LeanplumEnvironment;

public class SdkQaFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        setUpAppState();
        initLeanplum();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sdk_qa, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        createTriggersButton();
        createAppInboxButton();
        createVariablesButton();
        createMessagesButton();
        createPushButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        populateVersionURLInfo();
    }

    private void populateVersionURLInfo() {
        InternalState state = InternalState.sharedState();
        LeanplumApp app = state.getApp();
        LeanplumEnvironment env = state.getEnv();

        TextView tv = getView().findViewById(R.id.sdkVersion);
        tv.setText(BuildConfig.LEANPLUM_SDK_VERSION);

        TextView tv1 = getView().findViewById(R.id.appName);
        tv1.setText(app.getDisplayName());

        TextView apiUrl = getView().findViewById(R.id.apiHostName);
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

        Leanplum.start(getContext());
    }

    private void createTriggersButton() {
        Button button = getView().findViewById(R.id.triggers);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), TriggersActivity.class);
                SdkQaFragment.this.startActivity(myIntent);
            }
        });
    }

    private void createAppInboxButton() {
        Button button = getView().findViewById(R.id.appInbox);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AppInboxActivity.class);
                SdkQaFragment.this.startActivity(myIntent);
            }
        });
    }

    private void createVariablesButton() {
        Button button = getView().findViewById(R.id.variables);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), VariablesActivity.class);
                SdkQaFragment.this.startActivity(myIntent);
            }
        });
    }

    private void createMessagesButton() {
        Button button = getView().findViewById(R.id.messages);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MessagesActivity.class);
                SdkQaFragment.this.startActivity(myIntent);
            }
        });
    }

    private void createPushButton() {
        Button button = getView().findViewById(R.id.push);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), PushActivity.class);
                SdkQaFragment.this.startActivity(myIntent);
            }
        });
    }
}
