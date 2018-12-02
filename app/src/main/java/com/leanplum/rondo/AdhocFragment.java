package com.leanplum.rondo;

import android.support.v4.app.Fragment;;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.leanplum.Leanplum;

import java.util.HashMap;
import java.util.Map;

public class AdhocFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_adhoc, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initButtons();
    }

    private void sendTrackEvent() {
        String eventName = ((EditText)getView().findViewById(R.id.trackName))
            .getText().toString();
        Leanplum.track(eventName.trim());
        // TODO: figure out how to alert event response/status

        ((EditText)getView().findViewById(R.id.trackName)).setText("");
    }

    private void sendState() {
        String stateName = ((EditText)getView().findViewById(R.id.stateName))
                .getText().toString();
        Leanplum.track(stateName.trim());
        // TODO: figure out how to alert state response/status

        ((EditText)getView().findViewById(R.id.stateName)).setText("");
    }

    private void sendUserAttr() {
        String attrKey = ((EditText)getView().findViewById(R.id.attrKey))
                .getText().toString();
        String attrValue = ((EditText)getView().findViewById(R.id.attrValue))
                .getText().toString();
        Map attrib = new HashMap();
        attrib.put(attrKey.trim(), attrValue.trim());
        Leanplum.setUserAttributes(attrib);
        // TODO: figure out how to alert state response/status

        ((EditText)getView().findViewById(R.id.attrKey)).setText("");
        ((EditText)getView().findViewById(R.id.attrValue)).setText("");
    }

    private void setDeviceLocation() {
        Float latitude = Float.parseFloat(
                ((EditText)getView().findViewById(R.id.locLatitude)).getText().toString());
        Float longitude = Float.parseFloat(
                ((EditText)getView().findViewById(R.id.locLongitude)).getText().toString());

        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        Leanplum.setDeviceLocation(location);
        // TODO: figure out how to alert state response/status

        ((EditText)getView().findViewById(R.id.locLatitude)).setText("");
        ((EditText)getView().findViewById(R.id.locLongitude)).setText("");
    }

    private void setDeviceLocation(Intent data) {
//        Place place = PlacePicker.getPlace(this, data);
//        Location location = new Location(LocationManager.GPS_PROVIDER);
//        location.setLatitude(place.getLatLng().latitude);
//        location.setLongitude(place.getLatLng().longitude);
//        Leanplum.setDeviceLocation(location);
    }

    private void initButtons() {
        getView().findViewById(R.id.buttonTrack)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendTrackEvent();
                }
            });

        getView().findViewById(R.id.buttonState)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendState();
                }
            });

        getView().findViewById(R.id.buttonUserAttr)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendUserAttr();
                    }
                });

        getView().findViewById(R.id.buttonDeviceLocation)
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
