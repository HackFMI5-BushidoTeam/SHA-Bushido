package com.hackfmi.bushidoclient;

import java.io.*;
import java.net.*;

import android.util.Log;

class Connection extends Thread implements Runnable,Serializable  {
    
    private String inet;
    private int port;
    private Socket echoSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String client_hwid;
    private String client_face;
    private String client_pin;
    
    
    public Connection(String inet,int port, String client_hwid, String client_face, String client_pin){
        this.port = port;
        this.inet = inet;
        this.client_hwid = client_hwid;
        this.client_face = client_face;
        this.client_pin = client_pin;

    }
    
    public void run(){

       try {
           echoSocket = new Socket(inet,port);
           out = new PrintWriter(echoSocket.getOutputStream(), true);
           in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
           
           
           out.println("hwid="+client_hwid);
           out.flush();
           out.println("pin="+client_pin);
           out.flush();           
           out.println("face="+client_face);
           out.flush();           
           out.println("login=login");
           out.flush();           
           close();       
            


            
        } catch (Exception e) {
            
            Log.e("BUSHIDO","Critical connection exceptin." + e.toString());
            e.printStackTrace();
            
        }      
             
    }
    
    public boolean connected(){    
        return echoSocket.isConnected();
    }
    
    public String send(String s){
        
        try {

        out.println(s);
        out.flush();
        return in.readLine();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "";
        
    }
    
    public void close(){

        try {
            
            this.send("close");
            out.close();
            in.close();
            echoSocket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
