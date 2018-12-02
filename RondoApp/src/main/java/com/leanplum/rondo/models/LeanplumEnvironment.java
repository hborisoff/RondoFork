package com.leanplum.rondo.models;

public class LeanplumEnvironment {
    private String apiHostName;
    private Boolean apiSSL;
    private String socketHostName;
    private int socketPort;

    static public LeanplumEnvironment production() {
        LeanplumEnvironment env = new LeanplumEnvironment();
        env.apiHostName = "api.leanplum.com";
        env.apiSSL = true;
        env.socketHostName = "dev.leanplum.com";
        env.socketPort = 443;
        return env;
    }

    static public LeanplumEnvironment qa() {
        LeanplumEnvironment env = new LeanplumEnvironment();
        env.apiHostName = "leanplum-qa-1372.appspot.com";
        env.apiSSL = true;
        env.socketHostName = "dev-qa.leanplum.com";
        env.socketPort = 80;
        return env;
    }

    public String getApiHostName() {
        return apiHostName;
    }

    public Boolean getApiSSL() {
        return apiSSL;
    }

    public String getSocketHostName() {
        return socketHostName;
    }

    public int getSocketPort() {
        return socketPort;
    }
}
