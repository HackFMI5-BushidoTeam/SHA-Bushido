package com.hackfmi.bushidoserver;

import android.util.Base64;

import java.io.*;
import java.net.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Vlex on 4/25/2015.
 */
public class Sock extends Thread implements Serializable {
    public RSA keypair;
    public RSA keyClient;
    private boolean hwid_check = false;
    private boolean pin_check = false;
    private boolean bio_check = false;
//    public final static PublicKey clientPubKey;
    public Sock(){
        keypair  = new RSA();
        keyClient = new RSA();
    };

    public void run() {
        try {
            ServerSocket servSock = new ServerSocket(12345, 50, InetAddress.getByName("192.168.43.1"));
            while (true) {
                Socket sock = servSock.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
                while (br.readLine() !=null) {
                    String[] str = br.readLine().split("&");
                    switch (str[0]) {
                        case "hwid":
                            if (!hwid_check) this.hwid_check = true;
                            break;
                        case "pin":
                            if (!pin_check) this.pin_check = true;
                            break;
                        case "biometric":
                            if (!bio_check) this.bio_check = true;
                            break;
                    }
                    if (this.hwid_check && this.pin_check && this.bio_check) {
                        System.out.println("Bravo na mom4etooo");
                        break;
                    }
                    if (this.hwid_check && this.pin_check && this.bio_check) break;
                }
                sock.close();
                if (this.hwid_check && this.pin_check && this.bio_check) break;
            }
            System.out.println("Eventualno bihme startirali ssh tuk... Eventualno... mnogo eventualno....");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
