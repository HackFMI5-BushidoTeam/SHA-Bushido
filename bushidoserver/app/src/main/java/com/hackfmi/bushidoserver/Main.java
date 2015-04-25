package com.hackfmi.bushidoserver;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.google.common.base.Preconditions;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Random;


public class Main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toggleButton.setEnabled(false);
        createWifiAccessPoint();

    }

    public void starter(View v){
        final ProgressBar prog = (ProgressBar) findViewById(R.id.prog);
        prog.setProgress(0);
        prog.setMax(15);
        new ApManager();
        Random rand = new Random();
        String ssid = "tester";
        String pass = "password";
//        final TextView label = (TextView) findViewById(R.id.title);
//        new CountDownTimer(15000, 100) {
//            public void onTick(long millisUntilFinished) {
//                label.setText(Long.toString(millisUntilFinished / 100));
//                prog.setProgress((int)millisUntilFinished / 100);
//            }
//
//        public void onFinish() {
////            stopNFC();
//        }
//        }.start();
    }

    private void createWifiAccessPoint() {
        WifiManager wifiManager = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled())
        {
            wifiManager.setWifiEnabled(false);
        }
        Method[] wmMethods = wifiManager.getClass().getDeclaredMethods();
        boolean methodFound=false;
        for(Method method: wmMethods){
            if(method.getName().equals("setWifiApEnabled")){
                methodFound=true;
                WifiConfiguration netConfig = new WifiConfiguration();
                netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
//                netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                try {
                    boolean apstatus=(Boolean) method.invoke(wifiManager, netConfig,true);
                    //statusView.setText("Creating a Wi-Fi Network \""+netConfig.SSID+"\"");
                    for (Method isWifiApEnabledmethod: wmMethods)
                    {
                        if(isWifiApEnabledmethod.getName().equals("isWifiApEnabled")){
                            while(!(Boolean)isWifiApEnabledmethod.invoke(wifiManager)){
                            };
                            for(Method method1: wmMethods){
                                if(method1.getName().equals("getWifiApState")){
                                    int apstate;
                                    apstate=(Integer)method1.invoke(wifiManager);
                                    //                    netConfig=(WifiConfiguration)method1.invoke(wifi);
                                    //statusView.append("\nSSID:"+netConfig.SSID+"\nPassword:"+netConfig.preSharedKey+"\n");
                                }
                            }
                        }
                    }
                    if(apstatus)
                    {
                        System.out.println("SUCCESSdddd");
                        //statusView.append("\nAccess Point Created!");
                        //finish();
                        //Intent searchSensorsIntent = new Intent(this,SearchSensors.class);
                        //startActivity(searchSensorsIntent);
                    }else
                    {
                        System.out.println("FAILED");
                        //statusView.append("\nAccess Point Creation failed!");
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!methodFound){
            //statusView.setText("Your phone's API does not contain setWifiApEnabled method to configure an access point");
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
