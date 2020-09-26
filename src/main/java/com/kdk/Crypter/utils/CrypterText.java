package com.kdk.Crypter.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CrypterText {

//    private static final String AES_ENCRYPTION_KEY = "uHgYu7P0ptwtuaho";
    private static final String INT_VEC = "sdsopiuioyyttyhj";

    public static String encrypt(String plainText, String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INT_VEC.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipherInstance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipherInstance.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipherInstance.doFinal(plainText.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String decrypt(String encryptedText, String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INT_VEC.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encryptedText));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
