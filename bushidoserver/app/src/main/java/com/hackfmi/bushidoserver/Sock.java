package com.hackfmi.bushidoserver;

import java.io.*;
import java.net.*;
import java.security.PublicKey;

/**
 * Created by Vlex on 4/25/2015.
 */
public class Sock extends Thread implements Serializable {
    public RSA keypair;
//    public final static PublicKey clientPubKey;
    public Sock(){
        keypair  = new RSA();
    };

    public void run() {
        try {
            InetAddress addr = InetAddress.getByName("192.168.43.1");
            ServerSocket servSock = new ServerSocket(12345, 50, addr);
            System.out.println("Local hsot ip is " + InetAddress.getLocalHost());
            System.out.println(keypair.publicKey);
            while (true) {
                Socket sock = servSock.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
                while (true) {
                    String str = "Shit this fuck123!";
                    pw.println(str);
                    System.out.println(str);
                    pw.flush();
                    if (br.readLine() == null) break;
                }
                sock.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
