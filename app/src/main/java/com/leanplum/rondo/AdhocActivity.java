package com.leanplum.rondo;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.leanplum.Leanplum;

import java.util.HashMap;
import java.util.Map;

public class AdhocActivity extends AppCompatActivity {

    private final static int PLACE_PICKER_REQUEST = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhoc);

        initButtons();
    }

    private void sendTrackEvent() {
        String eventName = ((EditText)findViewById(R.id.trackName))
            .getText().toString();
        Leanplum.track(eventName.trim());
        // TODO: figure out how to alert event response/status

        ((EditText)findViewById(R.id.trackName)).setText("");
    }

    private void sendState() {
        String stateName = ((EditText)findViewById(R.id.stateName))
                .getText().toString();
        Leanplum.track(stateName.trim());
        // TODO: figure out how to alert state response/status

        ((EditText)findViewById(R.id.stateName)).setText("");
    }

    private void sendUserAttr() {
        String attrKey = ((EditText)findViewById(R.id.attrKey))
                .getText().toString();
        String attrValue = ((EditText)findViewById(R.id.attrValue))
                .getText().toString();
        Map attrib = new HashMap();
        attrib.put(attrKey.trim(), attrValue.trim());
        Leanplum.setUserAttributes(attrib);
        // TODO: figure out how to alert state response/status

        ((EditText)findViewById(R.id.attrKey)).setText("");
        ((EditText)findViewById(R.id.attrValue)).setText("");
    }

    private void openPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            // for activty
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            // for fragment
            //startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case PLACE_PICKER_REQUEST:
                    setDeviceLocation(data);
            }
        }
    }

    private void setDeviceLocation() {
        Float latitude = Float.parseFloat(
                ((EditText)findViewById(R.id.locLatitude)).getText().toString());
        Float longitude = Float.parseFloat(
                ((EditText)findViewById(R.id.locLongitude)).getText().toString());

        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        Leanplum.setDeviceLocation(location);
        // TODO: figure out how to alert state response/status

        ((EditText)findViewById(R.id.locLatitude)).setText("");
        ((EditText)findViewById(R.id.locLongitude)).setText("");
    }

    private void setDeviceLocation(Intent data) {
        Place place = PlacePicker.getPlace(this, data);
        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(place.getLatLng().latitude);
        location.setLongitude(place.getLatLng().longitude);
        Leanplum.setDeviceLocation(location);
    }

    private void initButtons() {
        findViewById(R.id.buttonTrack)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendTrackEvent();
                }
            });

        findViewById(R.id.buttonState)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendState();
                }
            });

        findViewById(R.id.buttonUserAttr)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendUserAttr();
                    }
                });

        findViewById(R.id.buttonDeviceLocation)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setDeviceLocation();
                    }
                });
/*
        findViewById(R.id.buttonPlacePicker)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPlacePicker();
                    }
                });
*/
    }
}
