package com.redbee.goldsample.entity;

public class LoginData {

    private String sessionKey;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "sessionKey='" + sessionKey + '\'' +
                '}';
    }
}
