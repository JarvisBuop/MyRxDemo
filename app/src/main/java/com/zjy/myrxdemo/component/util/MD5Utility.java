package com.zjy.myrxdemo.component.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utility
{
    public static String md5(String input) {
        try {
        	MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input.getBytes());
            return toHexString(algorithm.digest());
        } catch (NoSuchAlgorithmException e) {
        	throw new RuntimeException(e);
        }
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
        	String hex = Integer.toHexString(0xFF & b);
        	if (hex.length() == 1) {
        		hex = "0" + hex;
        	}
        	hexString.append(hex).append("");
        }
        
        return hexString.toString();
    }
    
    public static boolean check(String input, String digest) {
    	return digest == md5(input);
    }
}