package com.leanplum.rondo;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.SecuredVars;
import java.lang.ref.WeakReference;


public class VariablesFragment extends Fragment {
    Var<String> varString = Var.define("var_text", "Default value in code");
    Var<Number> varNumber = Var.define("var_number", null);
    Var<Boolean> varBoolean = Var.define("var_bool", false);
    Var<String> varFile = Var.defineFile("var_file", null);

    private Thread signatureVerificationThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_variables, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateViewWithVariables();
        verifySignature();
    }

    private void updateViewWithVariables() {
        TextView string = getView().findViewById(R.id.varString);
        string.setText(varString.stringValue);

        TextView number = getView().findViewById(R.id.varNumber);
        number.setText(varNumber.stringValue);

        TextView bool = getView().findViewById(R.id.varBool);
        bool.setText(varBoolean.stringValue);

        TextView file = getView().findViewById(R.id.varFile);
        file.setText(varFile.stringValue);

        if (varFile.fileValue()!= null) {
            java.io.File imgFile = new java.io.File(varFile.fileValue());
            if(imgFile.exists()) {
                ImageView myImage = getView().findViewById(R.id.varFileImage);
                myImage.setImageURI(Uri.fromFile(imgFile));
            }
        }
    }

    private void verifySignature() {
        TextView view = getView().findViewById(R.id.verificationResult);

        SecuredVars securedVars = Leanplum.securedVars();
        if (securedVars != null) {
            view.setText("verifying...");
            signatureVerificationThread = new SignatureVerificationThread(securedVars, view);
            signatureVerificationThread.start();
        } else {
            view.setText("missing");
        }
    }

    public void onStop() {
        super.onStop();
        if (signatureVerificationThread != null) {
            signatureVerificationThread.interrupt();
            signatureVerificationThread = null;
        }
    }

    private static class SignatureVerificationThread extends Thread {
        SecuredVars securedVars;
        WeakReference<TextView> viewRef;

        SignatureVerificationThread(SecuredVars securedVars, TextView view) {
            this.securedVars = securedVars;
            viewRef = new WeakReference<>(view);
        }

        @Override
        public void run() {
            String publicKey = SecuredVarsHelper.downloadPublicKey();
            if (publicKey == null) {
                showText("public key error");
            } else {
                if (SecuredVarsHelper.verify(securedVars, publicKey)) {
                    showText("verified");
                } else {
                    showText("verification error");
                }
            }
        }

        void showText(String text) {
            TextView view = viewRef.get();
            if (view != null) {
                view.post(() -> view.setText(text));
            }
        }
    }
}
