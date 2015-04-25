package com.hackfmi.bushidoclient;

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
    private Connection conn;
    
    public void progressBar(){
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setProgress(0);
        progressStatus = 0;
        
        new Thread(new Runnable() {
            public void run() {
               while (progressStatus < 100) {
                  progressStatus += 1;
           // Update the progress bar and display the 
           //current value in the text view
           handler.post(new Runnable() {
           public void run() {
              progressBar.setProgress(progressStatus);
             // textView.setText(progressStatus+"/"+progressBar.getMax());
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
        conn = new Connection(currentActivity);
     
        
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressBar();
                //button.setVisibility(View.GONE);
                
                TextView textView1 = (TextView) findViewById(R.id.textView1);
                textView1.setVisibility(View.GONE);
                
                textView1 = (TextView) findViewById(R.id.textView2);                
                textView1.setText("Connecting to server...");
                
                conn.start();
                // conn.sent("ASDF");
                //conn.sent("ASDFASDASD");
               // conn.sent("TESTERO");
                conn.close();

                // Intent intent = new Intent(currentActivity, PinActivity.class);
                // startActivity(intent);

                // From PinActivity, if reply is true, continue to LiveRecognition

                Log.d("BUSHIDO", "botona beshe natisnat!");
                
                // Intent intent = new Intent(currentActivity, LiveRecognition.class);
                // startActivity(intent);                
                
            }
        });        
        
        // Wifi.connect(MainActivity.this,"tester","password12345678901234567890");
        
         // Connection conn = new Connection();
         // conn.start();
        
        //System.out.println(BushidoHelper.getPhoneID(MainActivity.this));

        // RSA rsa = new RSA();
        // String s = rsa.Encrypt("Test");
        // System.out.println("RSA Encrypt : " + s);
        // System.out.println("RSA Decrypt : " + rsa.Decrypt(s));
        
        //Intent intent = new Intent(this, LiveRecognition.class);
        //startActivity(intent);

        Log.d("BUSHIDO", "Line: " + new Throwable().getStackTrace()[0].getLineNumber() + " | App created!");
    }

    @Override
    public void onResume() {
        super.onResume();
        
        Log.d("BUSHIDO", "App resumed!");

    }
 

}
