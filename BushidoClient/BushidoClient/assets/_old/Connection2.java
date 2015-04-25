package com.hackfmi.bushidoclient;


import java.io.*;
import java.net.*;
import java.security.PublicKey;

import android.util.Log;


public class Connection2 extends Thread implements Serializable  {
    
    public Connection2(){
        
    }
    
    @Override
    public void run(){
        
        String serverHostname = new String ("192.168.43.1");
        Socket echoSocket = null;
        BufferedReader in = null;
        
        RSA server_cert = new RSA();
        PublicKey server_pub = null;
        
        try {
            echoSocket = new Socket(serverHostname, 12345);
            
            //in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            Log.e("CONN","Don't know about host: " + serverHostname);
            
            System.exit(1);
        } catch (IOException e) {
            Log.e("CONN","Couldn't get I/O for "
                    + "the connection to: " + serverHostname);
            System.exit(1);
        }
        
        if(echoSocket.isConnected()){
            Log.d("CONN","Connected!");
        }

        while(true){
            // Doo something.
            
            try {
                
                ObjectInputStream ois = new ObjectInputStream(echoSocket.getInputStream());
                PrintWriter out = new PrintWriter(new OutputStreamWriter(echoSocket.getOutputStream()));
                
                out.println("hi");
                out.flush();
                
                Object obj = ois.readObject();
               
                
                if(obj != null){
                    Log.d("CONN","SSL OBEKT 2 : " + obj.toString());
                    
                    if(obj instanceof PublicKey){
                        server_cert.publicKey = (PublicKey) obj;
                        Log.d("CONN","Key 4 :  " + server_cert.publicKey);
                        
                        //Log.d("CONN","Crypt (2s): " + server_cert.Encrypt("TEST 1234").toString());
                        //Log.d("CONN","Crypt: " + server_cert.Encrypt("TEST 1234"));
                        
                        
                       // out.println(server_cert.Encrypt("TEST 1234"));
                       // out.flush();
                    }else{
                        Log.d("CONN","obj 3 :  " + obj.toString());
                    }

                }
                
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }
        
        }
        
    }    
 }

