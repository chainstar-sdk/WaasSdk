package com.tule.coin.util;


import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class EccUtils {
    /**
     * @see org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi
     * 192, 224, 239, 256, 384, 521
     * */
    private final static int KEY_SIZE = 256;//bit
    private final static String SIGNATURE = "SHA256withECDSA";

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    private static void printProvider() {
        Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        for (Provider.Service service : provider.getServices()) {
            System.out.println(service.getType() + ": "
                    + service.getAlgorithm());
        }
    }

    public static String toHexString(byte[] input, int offset, int length, boolean withPrefix) {
        StringBuilder stringBuilder = new StringBuilder();
        if (withPrefix) {
            stringBuilder.append("0x");
        }
        for (int i = offset; i < offset + length; i++) {
            stringBuilder.append(String.format("%02x", input[i] & 0xFF));
        }

        return stringBuilder.toString();
    }

    public static String toHexString(byte[] input) {
        return toHexString(input, 0, input.length, true);
    }

    public static void generateKeyPair(){
        try {
            KeyPair keyPair1 = getKeyPair();

            System.out.println(getPublicKey(keyPair1));
            System.out.println(getPrivateKey(keyPair1));

            KeyPair keyPair2 = getKeyPair();
            System.out.println(getPublicKey(keyPair2));
            System.out.println(getPrivateKey(keyPair2));
        }catch (Exception e){

        }
    }

    //生成秘钥对
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");//BouncyCastle
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    //获取公钥(Base64编码)
    public static String getPublicKey(KeyPair keyPair) {
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(bytes);
    }

    //获取私钥(Base64编码)
    public static String getPrivateKey(KeyPair keyPair) {
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(bytes);
    }

    //公钥加密
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            Cipher cipher = Cipher.getInstance("ECIES","BC");
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(x509KeySpec));
            return cipher.doFinal(data);
        }catch (Exception e){
            return null;
        }
    }


    public static byte[] decryptByPrivateKey(byte[] data, String privateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("ECIES","BC");
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(pkcs8KeySpec));
            return cipher.doFinal(data);
        }catch (Exception e){
            return null;
        }
    }

    public static String encrypt(byte[] data, String publicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            Cipher cipher = Cipher.getInstance("ECIES","BC");
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(x509KeySpec));
            return Base64.getEncoder().encodeToString(cipher.doFinal(data));
        }catch (Exception e){
            return null;
        }
    }

    public static boolean checkPublicKey( String publicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            Cipher cipher = Cipher.getInstance("ECIES","BC");
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(x509KeySpec));
            cipher.doFinal("test".getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public static boolean checkPriviateKey( String privateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            Cipher cipher = Cipher.getInstance("ECIES","BC");
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(x509KeySpec));
            cipher.doFinal("test".getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static byte[] decrypt(byte[] data, String privateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("ECIES","BC");
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(pkcs8KeySpec));
            return cipher.doFinal(Base64.getDecoder().decode(data));
        }catch (Exception e){
            return null;
        }
    }

}