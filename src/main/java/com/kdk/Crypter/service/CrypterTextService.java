package com.kdk.Crypter.service;


import com.kdk.Crypter.model.CrypterTextRequestModel;
import com.kdk.Crypter.model.CrypterTextResponseModel;
import com.kdk.Crypter.utils.CrypterText;
import com.kdk.Crypter.utils.KeyGenerator;
import org.springframework.stereotype.Service;

@Service
public class CrypterTextService {

    public CrypterTextResponseModel encryptText(CrypterTextRequestModel crypterTextRequestModel){
        return new CrypterTextResponseModel(CrypterText.encrypt(crypterTextRequestModel.getData(),
                KeyGenerator.generateKeyForAes128(crypterTextRequestModel.getUserKey())));
    }

    public CrypterTextResponseModel decryptText(CrypterTextRequestModel crypterTextRequestModel){
        return new CrypterTextResponseModel(CrypterText.decrypt(crypterTextRequestModel.getData(),
                KeyGenerator.generateKeyForAes128(crypterTextRequestModel.getUserKey())));
    }

}
