package com.leanplum.rondo;

import com.leanplum.rondo.models.LeanplumEnvironment;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class LeanplumEnvPersistence {

    static void saveLeanplumEnv(LeanplumEnvironment env) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(env);
        realm.commitTransaction();
    }

    static ArrayList<LeanplumEnvironment> loadLeanplumEnvs() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<LeanplumEnvironment> envs = realm.where(LeanplumEnvironment.class)
                .findAll();
        envs.load();
        realm.commitTransaction();
        final ArrayList<LeanplumEnvironment> list = new ArrayList<LeanplumEnvironment>();
        for (LeanplumEnvironment env:envs) {
            list.add(env);
        }
        return list;
    }

    static public LeanplumEnvironment production() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        LeanplumEnvironment env = realm.where(LeanplumEnvironment.class).
                equalTo("apiHostName", "api.leanplum.com").findFirst();
        realm.commitTransaction();
        return env;
    }

    static public LeanplumEnvironment qa() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        LeanplumEnvironment env = realm.where(LeanplumEnvironment.class).
                equalTo("apiHostName", "leanplum-qa-1372.appspot.com").findFirst();
        realm.commitTransaction();
        return env;
    }

    public static void seedDatabase() {
        if (production() == null) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealm(productionSeed());
            realm.copyToRealm(qaSeed());
            realm.commitTransaction();
        }
    }

    static private LeanplumEnvironment productionSeed() {
        LeanplumEnvironment env = new LeanplumEnvironment();
        env.setApiHostName ("api.leanplum.com");
        env.setApiSSL(true);
        env.setSocketHostName("dev.leanplum.com");
        env.setSocketPort(443);
        return env;
    }

    static private LeanplumEnvironment qaSeed() {
        LeanplumEnvironment env = new LeanplumEnvironment();
        env.setApiHostName("leanplum-qa-1372.appspot.com");
        env.setApiSSL(true);
        env.setSocketHostName("dev-qa.leanplum.com");
        env.setSocketPort(80);
        return env;
    }
}
