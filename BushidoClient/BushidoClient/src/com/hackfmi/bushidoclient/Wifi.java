package com.hackfmi.bushidoclient;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.AuthAlgorithm;
import android.net.wifi.WifiConfiguration.GroupCipher;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiConfiguration.PairwiseCipher;
import android.net.wifi.WifiConfiguration.Protocol;
import android.net.wifi.WifiManager;
import android.util.Log;

public class Wifi {

    /*
     * ssid: vlex pass: password type: WPA2-PSK
     * Source: http://bit.ly/1JmRqn6
     */

    public static void connect(Context context, String ssid, String key) {

        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        // Enable the wifi
        Log.d("WIFI", "Status before " + wifi.isWifiEnabled());
        wifi.setWifiEnabled(true);
        Log.d("WIFI", "Status after " + wifi.isWifiEnabled());

        // Set the configuration
        WifiConfiguration wifi_config = new WifiConfiguration();
        wifi_config.SSID = String.format("\"%s\"", ssid);
        wifi_config.preSharedKey = String.format("\"%s\"", key);

        // Remember and connect
        int network_id = wifi.addNetwork(wifi_config);
        wifi.disconnect();
        wifi.enableNetwork(network_id, true);
        wifi.reconnect();
    }

    
    public void readWepConfig(Context context)
    { 
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE); 
        List<WifiConfiguration> item = wifi.getConfiguredNetworks();
        int i = item.size();
        Log.d("WifiPreference", "NO OF CONFIG " + i );
        Iterator<WifiConfiguration> iter =  item.iterator();
        WifiConfiguration config = item.get(0);
        Log.d("WifiPreference", "SSID" + config.SSID);
        Log.d("WifiPreference", "PASSWORD" + config.preSharedKey);
        Log.d("WifiPreference", "ALLOWED ALGORITHMS");
        Log.d("WifiPreference", "LEAP" + config.allowedAuthAlgorithms.get(AuthAlgorithm.LEAP));
        Log.d("WifiPreference", "OPEN" + config.allowedAuthAlgorithms.get(AuthAlgorithm.OPEN));
        Log.d("WifiPreference", "SHARED" + config.allowedAuthAlgorithms.get(AuthAlgorithm.SHARED));
        Log.d("WifiPreference", "GROUP CIPHERS");
        Log.d("WifiPreference", "CCMP" + config.allowedGroupCiphers.get(GroupCipher.CCMP));
        Log.d("WifiPreference", "TKIP" + config.allowedGroupCiphers.get(GroupCipher.TKIP));
        Log.d("WifiPreference", "WEP104" + config.allowedGroupCiphers.get(GroupCipher.WEP104));
        Log.d("WifiPreference", "WEP40" + config.allowedGroupCiphers.get(GroupCipher.WEP40));
        Log.d("WifiPreference", "KEYMGMT");
        Log.d("WifiPreference", "IEEE8021X" + config.allowedKeyManagement.get(KeyMgmt.IEEE8021X));
        Log.d("WifiPreference", "NONE" + config.allowedKeyManagement.get(KeyMgmt.NONE));
        Log.d("WifiPreference", "WPA_EAP" + config.allowedKeyManagement.get(KeyMgmt.WPA_EAP));
        Log.d("WifiPreference", "WPA_PSK" + config.allowedKeyManagement.get(KeyMgmt.WPA_PSK));
        Log.d("WifiPreference", "PairWiseCipher");
        Log.d("WifiPreference", "CCMP" + config.allowedPairwiseCiphers.get(PairwiseCipher.CCMP));
        Log.d("WifiPreference", "NONE" + config.allowedPairwiseCiphers.get(PairwiseCipher.NONE));
        Log.d("WifiPreference", "TKIP" + config.allowedPairwiseCiphers.get(PairwiseCipher.TKIP));
        Log.d("WifiPreference", "Protocols");
        Log.d("WifiPreference", "RSN" + config.allowedProtocols.get(Protocol.RSN));
        Log.d("WifiPreference", "WPA" + config.allowedProtocols.get(Protocol.WPA));
        Log.d("WifiPreference", "WEP Key Strings");
        String[] wepKeys = config.wepKeys;
        Log.d("WifiPreference", "WEP KEY 0" + wepKeys[0]);
        Log.d("WifiPreference", "WEP KEY 1" + wepKeys[1]);
        Log.d("WifiPreference", "WEP KEY 2" + wepKeys[2]);
        Log.d("WifiPreference", "WEP KEY 3" + wepKeys[3]);
    }    
}
