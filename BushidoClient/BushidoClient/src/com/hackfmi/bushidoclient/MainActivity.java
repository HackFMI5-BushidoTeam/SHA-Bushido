package com.hackfmi.bushidoclient;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hackfmi.bushidoclient.R;

public class MainActivity extends Activity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private Activity currentActivity;
    public static String persona = "";
    // private Connection conn;
    
    public void progressBar(){
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setProgress(0);
        progressStatus = 0;
        
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (progressStatus < 100) {
                  progressStatus += 1;
           // Update the progress bar and display the 
           //current value in the text view
           handler.post(new Runnable() {
           @Override
        public void run() {
              progressBar.setProgress(progressStatus);
           }
               });
               try {
                  // Sleep for 100 milliseconds. 
                  Thread.sleep(10);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
         }).start();    
    }
    

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        currentActivity = this;
        Log.d("BUSHIDO", "Application has being created!");

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUSHIDO", "Unlock button clicked!");
 
                progressBar();
                //button.setVisibility(View.GONE);
                
                
                /*
                TextView textView1 = (TextView) findViewById(R.id.textView1);
                textView1.setVisibility(View.GONE);
                */

                Connection conn = new Connection("192.168.43.67",8080,BushidoHelper.getPhoneID(MainActivity.this),"f6db3dc27640bd695c55ca540d1f7d90","1337");
                conn.start();

                TextView textView2 = (TextView) findViewById(R.id.textView2);                
                textView2.setText("Connecting to server...");
                
/*
 * 
 * 
                try{
                



                if(conn.connected()){
                    
                    textView2.setText("Connection successfull");
                                  
                    
               }else textView2.setText("Connection failed...");

                conn.close();
                Connection conn = new Connection();
                conn.connect("192.168.43.67",8080); 
                System.out.println(conn.send("hwid=f6db3dc27640bd695c55ca540d1f7d90"));
                System.out.println(conn.send("login=login"));
                System.out.println(conn.send("pin=1337"));
                System.out.println(conn.send("login=login"));
                System.out.println(conn.send("face=12345"));
                System.out.println(conn.send("login=login"));
                System.out.println(conn.send("face=f6db3dc27640bd695c55ca540d1f7d90"));
                System.out.println(conn.send("login=login"));
                conn.close();
                
                }catch(Exception e){
                    textView2.setText("Connection failed!");
                    e.printStackTrace();
                }                
              */  


                
                // Intent intent = new Intent(currentActivity, PinActivity.class);
                // startActivity(intent);
                // From PinActivity, if reply is true, continue to LiveRecognition
                 Intent intent = new Intent(currentActivity, LiveRecognition.class);
                 startActivity(intent);                
                
            }
        });        
        
  
        System.out.println(BushidoHelper.getPhoneID(MainActivity.this));
        Log.d("BUSHIDO", "Line: " + new Throwable().getStackTrace()[0].getLineNumber() + " | App created!");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("BUSHIDO", "Application has being resumed!");
    }
 

}
