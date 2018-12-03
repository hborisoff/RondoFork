package com.leanplum.rondo;

import com.leanplum.rondo.models.LeanplumApp;
import com.leanplum.rondo.models.LeanplumEnvironment;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RondoPreferences extends RealmObject {

    @PrimaryKey
    private int id;
    private LeanplumApp app;
    private LeanplumEnvironment env;

    static void updateRondoPreferencesWithApp(LeanplumApp app) {
        RondoPreferences prefs = RondoPreferences.getRondoPreferences();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        prefs.app = app;
        realm.commitTransaction();
    }

    static void updateRondoPreferencesWithEnv(LeanplumEnvironment env) {
        RondoPreferences prefs = RondoPreferences.getRondoPreferences();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        prefs.env = env;
        realm.commitTransaction();
    }

    static RondoPreferences getRondoPreferences() {
        Realm realm = Realm.getDefaultInstance();
        RondoPreferences rondoPreferences = realm.where(RondoPreferences.class).findFirst();
        if (rondoPreferences == null) {
            LeanplumApp app = LeanplumAppPersistence.rondoQAProduction();
            LeanplumEnvironment env = LeanplumEnvPersistence.production();

            realm.beginTransaction();
            rondoPreferences = new RondoPreferences();
            rondoPreferences.id = 1;

            rondoPreferences.app = app;
            rondoPreferences.env = env;
            realm.copyToRealm(rondoPreferences);
            realm.commitTransaction();
        }
        return rondoPreferences;
    }

    public LeanplumEnvironment getEnv() {
        return env;
    }

    public LeanplumApp getApp() {
        return app;
    }
}
