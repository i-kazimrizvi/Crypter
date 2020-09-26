package com.kdk.Crypter.model;

public class CrypterTextResponseModel {

    String encryptedText;

    public CrypterTextResponseModel() {
    }

    public CrypterTextResponseModel(String text) {
        this.encryptedText = text;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }
}
