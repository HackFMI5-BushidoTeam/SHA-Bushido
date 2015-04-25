package com.hackfmi.bushidoclient;

import java.io.*;
import java.net.*;
import java.security.PublicKey;

import android.content.Context;
import android.util.Log;

public class Connection extends Thread implements Serializable {

    private Context context;
    private Socket echoSocket;
    private PrintWriter out;

    public Connection(Context context) {
        this.context = context;
    }

    public void sent(String s){
        this.out.println(s);
        out.flush();
    }
    
    @Override
    public void run() {

        String serverHostname = new String("192.168.43.67");

        String[] strings = new String[3];
        strings[0] = "biometric|hkjshgkshgkjshkghsgjkshkghs";
        strings[1] = "pin|1337";
        strings[2] = "hwid|" + BushidoHelper.getPhoneID(context);

        try {

            this.echoSocket = new Socket(serverHostname, 12346);
            Log.d("BUSHIDO", "Connection to " + serverHostname + " is " + (this.echoSocket.isConnected() ? "successfull" : "failed"));

            this.out = new PrintWriter(this.echoSocket.getOutputStream(), true);
            // BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            out.println(strings[0]);
            out.flush();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            out.println(strings[1]);
            out.flush();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            out.println(strings[2]);
            out.flush();

            // echoSocket.close();

        } catch (UnknownHostException e) {
            Log.e("BUSHIDO", "Don't know about host: " + serverHostname);

            System.exit(1);
        } catch (IOException e) {
            Log.e("BUSHIDO", "Couldn't get I/O for " + "the connection to: " + serverHostname);
            System.exit(1);
        }

    }

    public void close() {
        try {

            this.echoSocket.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
