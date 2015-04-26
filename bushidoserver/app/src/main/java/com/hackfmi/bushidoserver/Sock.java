package com.hackfmi.bushidoserver;

import android.util.Base64;

import java.io.*;
import java.net.*;
import java.lang.*;
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
    private int pinId = Integer.MIN_VALUE;
    private int hw_id = Integer.MIN_VALUE;
    private String[] pin = {(Integer.toString(1337)),(Integer.toString(12340)),(Integer.toString(1234567890))};
    private String[] hwid = {"89dc4f4b13fc5150e9898fd5daecd34", "89dc4f4b13fc51femuchimfg", "2i3crng28tmthm93jm4t3m94jv"};


//    public final static PublicKey clientPubKey;
    public Sock(){
        keypair  = new RSA();
        keyClient = new RSA();
    };

    public void run() {
        try {
            ServerSocket servSock = new ServerSocket(12345, 50, InetAddress.getByName("192.168.43.1"));
            Socket sock = servSock.accept();
            while (true) {

                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
                String line = null;

                while ((line = br.readLine()) !=null){
                    System.out.println(line);
                    String[] str = line.split("|");
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
//                    if (this.hwid_check && this.pin_check && this.bio_check) {
                    if (line == "Bye mofo") {
                        System.out.println("Bravo na mom4etooo");
                        break;
                    }
                    if (this.hwid_check && this.pin_check && this.bio_check) break;
                }


                if (this.hwid_check && this.pin_check && this.bio_check) break;
            }sock.close();
            System.out.println("Eventualno bihme startirali ssh tuk... Eventualno... mnogo eventualno....");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
