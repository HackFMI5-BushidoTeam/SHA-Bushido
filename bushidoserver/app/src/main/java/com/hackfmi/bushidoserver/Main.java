package com.hackfmi.bushidoserver;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import java.io.*;
import java.net.*;

//import android.net.wifi.WifiManager;


import java.util.Random;


public class Main extends ActionBarActivity {
    public int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String ssid = "tester";
        String pass = "password";
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        try {
            new Beamer(ssid, pass);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void starter(View v) throws UnsupportedEncodingException {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setEnabled(true);
        Random rand = new Random();
//        String ssid = new BushidoHelper().md5(Integer.toString(rand.nextInt()));
//        String pass = new BushidoHelper().md5(Integer.toString(rand.nextInt()));
        String ssid = "tester";
        String pass = "password";
        HotSpotter hot = new HotSpotter(ssid, pass, Main.this);
        hot.start();
        Sock shit = new Sock();
        shit.start();


    }

    public void pairer(View v, String ssid, String pass, Context context){
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        final ProgressBar prog = (ProgressBar) findViewById(R.id.prog);
        prog.setProgress(0);
        prog.setMax(150);
        prog.setProgress(150 - value);

        synchronized ((Object)value){

            new CountDownTimer(15000, 100) {
                public void onTick(long millisUntilFinished) {
                    value += 1;
                    prog.setProgress(150-value);
                }
                public void onFinish() {
                    prog.setProgress(0);
                    toggleButton.setEnabled(true);
                    toggleButton.setChecked(false);
                }
            }.start();
            value = 0;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
