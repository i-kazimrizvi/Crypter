package com.kdk.Crypter.model;

public class CrypterTextRequestModel {

    String data, userKey;

    public CrypterTextRequestModel() {
    }

    public CrypterTextRequestModel(String text, String key) {
        this.data = text;
        this.userKey = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
