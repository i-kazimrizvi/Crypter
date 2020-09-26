package com.kdk.Crypter.utils;

public class KeyGenerator {

    //Key length must be from 7 to 16 chars
    public static String generateKeyForAes128(String initKey){
        StringBuilder initialKey = new StringBuilder(initKey);
        StringBuilder finalKey = new StringBuilder();
        while(finalKey.length()<16){
            finalKey.append(initialKey.toString());
            finalKey.append(initialKey.length());
            initialKey.reverse();
        }
        return finalKey.substring(0,16);
    }


    public static String generateKeyForAes256(String initKey){
        StringBuilder initialKey = new StringBuilder(initKey);
        StringBuilder finalKey = new StringBuilder();
        while(finalKey.length()<32){
            finalKey.append(initialKey.toString());
            finalKey.append(initialKey.length());
            initialKey.reverse();
        }
        return finalKey.substring(0,32);
    }

}
