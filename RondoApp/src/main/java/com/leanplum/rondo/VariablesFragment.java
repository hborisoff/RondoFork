package com.leanplum.rondo;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leanplum.Var;
import com.leanplum.annotations.File;

import androidx.fragment.app.Fragment;

public class VariablesFragment extends Fragment {
    Var<String> composerName = Var.define("composerName", "Composer name");
    Var<String> compositionTitle = Var.define("compositionTitle", "Composition Title");
    Var<File> photoFile = Var.define("photograph", null);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_variables, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateViewWithVariables();
    }

    private void updateViewWithVariables() {
        TextView name = getView().findViewById(R.id.name);
        name.setText(composerName.stringValue);

        TextView title = getView().findViewById(R.id.title);
        title.setText(compositionTitle.stringValue);

        if (photoFile.fileValue()!= null) {
            java.io.File imgFile = new java.io.File(photoFile.fileValue());
            if(imgFile.exists()) {
                ImageView myImage = getView().findViewById(R.id.image);
                myImage.setImageURI(Uri.fromFile(imgFile));
            }
        }
    }
}
