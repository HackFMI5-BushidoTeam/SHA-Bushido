package com.hackfmi.bushidoserver;

/**
 * Created by Vlex on 4/25/2015.
 */
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class RSA {
    /*
     * Source: http://bit.ly/1JDpwQy
     */

    KeyPairGenerator kpg;
    KeyPair kp;
    PublicKey publicKey;
    PrivateKey privateKey;
    byte[] encryptedBytes, decryptedBytes;
    Cipher cipher, cipher1;
    String encrypted, decrypted;

    public RSA(){

        try {

            kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            kp = kpg.genKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();

            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String Encrypt(String plain){


        try {

            encryptedBytes = cipher.doFinal(plain.getBytes());

        } catch (IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        encrypted = bytesToString(encryptedBytes);
        return encrypted;


    }

    public String Decrypt(String result){
        try {

            cipher1 = Cipher.getInstance("RSA");
            cipher1.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedBytes = cipher1.doFinal(stringToBytes(result));
            decrypted = new String(decryptedBytes);
            return decrypted;

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public String bytesToString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }

    public static byte[] stringToBytes(String s) {
        byte[] b2 = new BigInteger(s, 36).toByteArray();
        return Arrays.copyOfRange(b2, 1, b2.length);
    }



}