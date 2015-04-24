package com.hackfmi.bushidoclient;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

import com.hackfmi.bushidoclient.R;

public class MainActivity extends Activity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Wifi.connect(MainActivity.this,"vlex","password");
        
        
        RSA rsa = new RSA();
        try {
            
            String s = rsa.Encrypt("Test");

            System.out.println("RSA Encrypt : " + s);
            System.out.println("RSA Decrypt : " + rsa.Decrypt(s));
            
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        Log.d("BUSHIDO", "Line: " + new Throwable().getStackTrace()[0].getLineNumber() + " | App created!");
    }
    
    @Override
    public void onResume() { 
        super.onResume();
        Log.d("BUSHIDO","App resumed!");
        
    }    
    
}
