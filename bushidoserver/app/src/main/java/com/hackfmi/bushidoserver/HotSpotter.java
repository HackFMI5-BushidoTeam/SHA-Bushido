package com.hackfmi.bushidoserver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.util.Log;
import android.net.wifi.WifiManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Vlex on 4/25/2015.
 */
public class HotSpotter extends Thread {

    public HotSpotter(String ssid, String pass, Context context)  {
    WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled())
        {
            wifiManager.setWifiEnabled(false);
        }
    WifiConfiguration netConfig = new WifiConfiguration();

        netConfig.SSID = ssid;
        netConfig.preSharedKey = pass;
        netConfig.hiddenSSID = true;

        netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA); // For WPA
        netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN); // For WPA2
        netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
        netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);


    try{
        Method setWifiApMethod = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
        boolean apstatus=(Boolean) setWifiApMethod.invoke(wifiManager, netConfig,true);

        Method isWifiApEnabledmethod = wifiManager.getClass().getMethod("isWifiApEnabled");
        while(!(Boolean)isWifiApEnabledmethod.invoke(wifiManager)){};
        Method getWifiApStateMethod = wifiManager.getClass().getMethod("getWifiApState");
        int apstate=(Integer)getWifiApStateMethod.invoke(wifiManager);
        Method getWifiApConfigurationMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
        netConfig=(WifiConfiguration)getWifiApConfigurationMethod.invoke(wifiManager);
        Log.e("CLIENT", "\nSSID:"+netConfig.SSID+"\nPassword:"+netConfig.preSharedKey+"\n");

    } catch (Exception e) {
        Log.e(this.getClass().toString(), "", e);
    }
    }


    /**
     * Switching On data
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void setMobileDataEnabled(Context context, boolean enabled) {
        Log.i("NetworkUtil", "Mobile data enabling" + enabled);
        final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            Class conmanClass = Class.forName(conman.getClass().getName());
            final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
            connectivityManagerField.setAccessible(true);
            final Object connectivityManager = connectivityManagerField.get(conman);
            final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());

            final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);

            setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }









    }
}
