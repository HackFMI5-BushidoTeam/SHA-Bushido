import java.net.*; 
import java.io.*; 


public class Server extends Thread
{ 
 protected Socket clientSocket;

 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 

    try { 
        
         serverSocket = new ServerSocket(8080); 
         System.out.println("Server started: " + serverSocket);

              while (true)
                 {
                  System.out.println ("Waiting for Connection");
                  new Server (serverSocket.accept()); 
                 }

        } 
    catch (Exception e) 
        { 
        e.printStackTrace();
        serverSocket.close(); 
        System.err.println("Could not listen on port: 8080."); 
        // System.exit(1); 
        } 

   }

 private Server (Socket clientSoc)
   {
    clientSocket = clientSoc;
    start();
   }

 
 private boolean check_client_item(String type,String value){
     
     // This method should connect to internal db and check the applied data..
     // But for Hackaton testing purposes we will hardcode the
  
     if((type.equals("face") && value.equals("f6db3dc27640bd695c55ca540d1f7d90")) ||
       (type.equals("hwid") && value.equals("f6db3dc27640bd695c55ca540d1f7d90")) ||
       (type.equals("pin") && value.equals("1337")))
         return true;
     else return false;

 }
 

 
 public boolean check_login(String client_hwid, String client_face, String client_pin){
             
             // This method should connect to internal db and check the applied data..
             // But for Hackaton testing purposes we will hardcode them

             if(check_client_item("hwid",client_hwid) && check_client_item("face",client_face) && check_client_item("pin",client_pin))
             return true;   
             return false;
 }
 
 
 public void run()
   {
     
     // Client settings
     String client_hwid = "";
     String client_face = "";
     String client_pin = ""; 
     int session_id = 1 + (int)(Math.random()*1000); 
     
    System.out.println("Session ["+ session_id +"] started");
    

    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String request;
         out.println("welcome");
         out.flush();
         
         while ((request = in.readLine()) != null) 
             { 
             
             if (request.equals("close")){ 
                 System.out.println ("Session ["+ session_id +"] closed!");
                 break; 
             }
             
             // Requests
             
             if(!request.contains("=")){
                 System.out.println ("Request " + request + " is unknown to server!");
                 out.println("fail");
             }else{
                 String[] request_split = request.split("=");
                 
                 
                 if(request_split[0].equals("login")){
                     // Login try
                     System.out.println("Trying to login with data: " + client_hwid +" - " + client_face +" - " + client_pin);
                     if(check_login(client_hwid, client_face, client_pin)){
                         
                         System.out.println("Login successfull");
                         out.println("success=login");
                         
                     }else{
                         
                         System.out.println("Login failed");
                         out.println("fail=login");
                         
                     }
                 // All other cases    
                 }else{

                     switch(request_split[0]){
                         case "hwid":
                             System.out.println("Hardware ID: " + request_split[1]);
                             client_hwid = request_split[1];
                             if(check_client_item("hwid",request_split[1])){
                                 out.println("success=hwid");
                             }else out.println("fail=hwid");  
                             
                         break;
                         case "face":
                             
                             System.out.println("Face ID: " + request_split[1]);
                             client_face = new String(request_split[1]);
                             if(check_client_item("face",request_split[1])){
                                 out.println("success=face");
                             }else out.println("fail=face");     
                             
                         break;
                         case "pin":
                             
                             System.out.println("PIN: " + request_split[1]);
                             client_pin = request_split[1];
                             if(check_client_item("pin",request_split[1])){
                                 out.println("success=pin");
                             }else out.println("fail=pin");    
                             
                         break;                         
                                            
                         default:
                             System.out.println ("Request in loop " + request + " - " +request_split[0] + " is unknown to server!");
                             out.println("fail");                          
                         break;
                         
                     }                     
                     
                 }
                 

             }

             } 

         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         // System.exit(1); 
        } 
    }
} 

