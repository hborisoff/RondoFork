package com.leanplum.rondo;

import android.util.Log;

import com.leanplum.rondo.models.LeanplumApp;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class LeanplumAppPersistence {

    static void saveLeanplumApp(LeanplumApp app) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(app);
        realm.commitTransaction();
    }

    static ArrayList<LeanplumApp> loadLeanplumApps() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<LeanplumApp> apps = realm.where(LeanplumApp.class)
                .findAll();
        apps.load();
        final ArrayList<LeanplumApp> list = new ArrayList<LeanplumApp>();
        for (LeanplumApp app:apps) {
            list.add(app);
        }
        return list;
    }
}
