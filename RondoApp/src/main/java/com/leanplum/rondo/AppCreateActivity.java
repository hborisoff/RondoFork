package com.leanplum.rondo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.leanplum.rondo.models.LeanplumApp;
import org.json.JSONException;
import org.json.JSONObject;

public class AppCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_create);

        createButton();
    }

    private void createButton() {
        findViewById(R.id.scan_qr).setOnClickListener(v -> scanQR());

        findViewById(R.id.create).setOnClickListener(v -> {
            if (validateInput()) {
                LeanplumApp app = createApp();
                LeanplumAppPersistence.saveLeanplumApp(app);
                finish();
            } else {
                String errorMessage = "Empty fields are not allowed!";
                Toast.makeText(AppCreateActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void scanQR() {
        new IntentIntegrator(this)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setPrompt("Scan")
            .setCameraId(0)
            .setBeepEnabled(false)
            .setBarcodeImageEnabled(false)
            .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan cancelled", Toast.LENGTH_SHORT).show();
            } else {
                fill(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void fill(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            appId().setText(jsonObject.optString("app_id"));
            devKey().setText(jsonObject.optString("development"));
            prodKey().setText(jsonObject.optString("production"));
            displayName().requestFocus();

        } catch (JSONException e) {
            Toast.makeText(this, "QR text error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateInput() {
        String displayName = displayName().getText().toString();
        String appId = appId().getText().toString();
        String devKey = devKey().getText().toString();
        String prodKey = prodKey().getText().toString();

        return !(displayName.isEmpty() || appId.isEmpty() || devKey.isEmpty() || prodKey.isEmpty());
    }

    private LeanplumApp createApp() {
        LeanplumApp app = new LeanplumApp();
        app.setDisplayName(displayName().getText().toString());
        app.setAppId(appId().getText().toString());
        app.setDevKey(devKey().getText().toString());
        app.setProdKey(prodKey().getText().toString());
        return app;
    }

    private EditText displayName() {
        return findViewById(R.id.displayName);
    }

    private EditText appId() {
        return findViewById(R.id.appId);
    }

    private EditText devKey() {
        return findViewById(R.id.devKey);
    }

    private EditText prodKey() {
        return findViewById(R.id.prodKey);
    }
}
