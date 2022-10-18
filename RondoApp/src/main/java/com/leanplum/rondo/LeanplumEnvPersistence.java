package com.leanplum.rondo;

import com.leanplum.rondo.models.LeanplumEnv;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class LeanplumEnvPersistence {
    private static final String ENV_PRODUCTION = "api.leanplum.com";
    private static final String ENV_PRODUCTION_SOCKET = "dev.leanplum.com";

    private static final String ENV_STAGING = "api-staging.leanplum.com";
    private static final String ENV_STAGING_SOCKET = "dev-staging.leanplum.com";

    private static final String ENV_QA = "ingester.qa.leanplum.com";
    private static final String ENV_QA_SOCKET = "dev-qa.leanplum.com";

    private static final String ENV_CT = "ct-dot-ingester.prod.leanplum.com";
    private static final String ENV_CT_SOCKET = "dev.leanplum.com";

    private static final String ENV_RC = "20221017-664f1d3-dot-ingester.prod.leanplum.com";
    private static final String ENV_RC_SOCKET = "dev.leanplum.com";

    private static final int SOCKET_PORT = 443;
    private static final boolean API_USE_SSL = true;

    static void saveLeanplumEnv(LeanplumEnv env) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(env);
        realm.commitTransaction();
    }

    static ArrayList<LeanplumEnv> loadLeanplumEnvs() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<LeanplumEnv> envs = realm.where(LeanplumEnv.class)
                .findAll();
        envs.load();
        realm.commitTransaction();
        final ArrayList<LeanplumEnv> list = new ArrayList<LeanplumEnv>();
        for (LeanplumEnv env:envs) {
            list.add(env);
        }
        return list;
    }

    static public LeanplumEnv production() {
        return find(ENV_PRODUCTION);
    }

    static public LeanplumEnv find(String environment) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        LeanplumEnv env = realm.where(LeanplumEnv.class).
            equalTo("apiHostName", environment).findFirst();
        realm.commitTransaction();
        return env;
    }

    public static void seedDatabase() {
        if (find(ENV_PRODUCTION) == null) {
            seedToDB(ENV_PRODUCTION, ENV_PRODUCTION_SOCKET);
        }
        if (find(ENV_QA) == null) {
            seedToDB(ENV_QA, ENV_QA_SOCKET);
        }
        if (find(ENV_STAGING) == null) {
            seedToDB(ENV_STAGING, ENV_STAGING_SOCKET);
        }
        if (find(ENV_CT) == null) {
            seedToDB(ENV_CT, ENV_CT_SOCKET);
        }
        if (find(ENV_RC) == null) {
            seedToDB(ENV_RC, ENV_RC_SOCKET);
        }
    }

    static private void seedToDB(String environment, String socket) {
        LeanplumEnv env = new LeanplumEnv();
        env.setApiHostName (environment);
        env.setApiSSL(API_USE_SSL);
        env.setSocketHostName(socket);
        env.setSocketPort(SOCKET_PORT);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(env);
        realm.commitTransaction();
    }
}
