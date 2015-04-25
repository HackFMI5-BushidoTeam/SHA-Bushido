package com.hackfmi.bushidoclient;

import java.io.*;
import java.net.*;


public class Connection extends Thread implements Serializable  {
    
    public Connection(){
    }
    
    public void run(){
        
        String serverHostname = new String ("192.168.43.1");
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {

            echoSocket = new Socket(InetAddress.getByName(serverHostname),12345);
            
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("SOCKET: Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("SOCKET: Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            System.exit(1);
        }

    BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
    String userInput;

        System.out.println ("SOCKET: Type Message (\"Bye.\" to quit)");
    try {
        
        out.println("SOCKET: hello");
        out.flush();
        
        int i = 0;
        while(in.readLine() != null){
            i++;
            System.out.println("SOCKET REPLY:  " + in.readLine());
            
            if(i>100) break;
        }

        /*
        while ((userInput = stdIn.readLine()) != null) 
               {
            out.println(userInput);

                // end loop
                if (userInput.equals("Bye."))
                    break;

            System.out.println("echo: " + in.readLine());
           }
        */
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    out.close();
    try {
        in.close();
        stdIn.close();
        echoSocket.close();        
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }        
 }

