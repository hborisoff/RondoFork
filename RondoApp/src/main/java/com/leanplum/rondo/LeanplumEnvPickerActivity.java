package com.leanplum.rondo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.leanplum.Leanplum;
import com.leanplum.rondo.adapters.LeanplumAppAdapter;
import com.leanplum.rondo.adapters.LeanplumEnvAdapter;
import com.leanplum.rondo.models.InternalState;
import com.leanplum.rondo.models.LeanplumApp;
import com.leanplum.rondo.models.LeanplumEnvironment;

import java.util.ArrayList;

public class LeanplumEnvPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_env_picker);
        final ListView listview = findViewById(R.id.listview);

        LeanplumEnvironment[] envs = new LeanplumEnvironment[] {
                LeanplumEnvironment.production(),
                LeanplumEnvironment.qa()
        };

        final ArrayList<LeanplumEnvironment> list = new ArrayList<LeanplumEnvironment>();
        for (int i = 0; i < envs.length; ++i) {
            list.add(envs[i]);
            finish();
        }
        final LeanplumEnvAdapter adapter = new LeanplumEnvAdapter(this,
                list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final LeanplumEnvironment env = (LeanplumEnvironment) parent.getItemAtPosition(position);
                InternalState.sharedState().setEnv(env);
            }
        });
    }
}
