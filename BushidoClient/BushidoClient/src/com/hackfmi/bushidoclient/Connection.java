package com.hackfmi.bushidoclient;

import java.io.*;
import java.net.*;


public class Connection {
    
    
    public Connection(){
        
        Socket socket = null;
        String response = "";
        
        try {
         socket = new Socket("192.168.143.1", 8888);
         
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
         byte[] buffer = new byte[1024];
         
         int bytesRead;
         InputStream inputStream = socket.getInputStream();
         System.out.println("SOCKET : INIT " + socket.isConnected());
         /*
          * notice:
          * inputStream.read() will block if no data return
          */
                  while ((bytesRead = inputStream.read(buffer)) != -1){
                      byteArrayOutputStream.write(buffer, 0, bytesRead);
                      response += byteArrayOutputStream.toString("UTF-8");
                  }
                  
              System.out.println(responce);
                  
               
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
