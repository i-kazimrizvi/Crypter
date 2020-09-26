package com.kdk.Crypter.model;

import org.springframework.web.multipart.MultipartFile;

public class CrypterFileRequestModel {
    String userKey;
    MultipartFile file;

    public CrypterFileRequestModel() {
    }

    public CrypterFileRequestModel(String userKey, MultipartFile file) {
        this.userKey = userKey;
        this.file = file;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
