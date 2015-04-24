package com.hackfmi.bushidoclient;

import java.util.List;

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

        Wifi.connect(MainActivity.this,"vlex","password");
        
        
        Log.d("BUSHIDO", new Throwable().getStackTrace()[0].getLineNumber() + "App created!");
    }
    
    @Override
    public void onResume() { 
        super.onResume();
        Log.d("BUSHIDO","App resumed!");
        
    }    
    
}
