package com.kdk.Crypter.utils;

import org.apache.commons.logging.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CrypterFile {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encrypt(String key, File inputFile, File outputFile, FileInputStream inputStream) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile, inputStream);
    }

    public static void decrypt(String key, File inputFile, File outputFile, FileInputStream inputStream) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile, inputStream);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile, FileInputStream inputStream) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            byte[] inputBytes = new byte[8192];
            int nread;
            while ((nread = inputStream.read(inputBytes)) >= 0) {
                byte[] enc = cipher.update(inputBytes, 0, nread);
                outputStream.write(enc);
            }
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal();
            outputStream.write(outputBytes);
            inputStream.close();
            outputStream.close();


    }

}