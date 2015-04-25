package com.hackfmi.bushidoclient;

import java.io.*;
import java.net.*;


public class Connection extends Thread implements Serializable  {
    
    public Connection(){
    }
    
    public void run(){
        
        Socket echoSocket = null;

		String[] strings = String(3);
		strings[1] = "biometric&hkjshgkshgkjshkghsgjkshkghs";
		strings[2] = "pin&hkjshgkshgkjshkghssgsdggjkshkghs";
		strings[3] = "hwid&hkjshgkshgkjshkgdsgfsghsgjkshkghs";
        try {
			Socket sock = new Socket(InetAddress.getByName("192.168.43.1"),12345);
            PrintWrier out = new PrintWriter(new OutputStreamWriter(echoSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			for (string as strings){
				out.println(string);
				out.flush;
			}
			sock.close();
        } catch (UnknownHostException e) {
            System.err.println("SOCKET: Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("SOCKET: Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            System.exit(1);
        }
    }    
 }

