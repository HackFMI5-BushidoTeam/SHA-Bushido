package com.hackfmi.bushidoclient;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

public class Wifi {

    /*
     * ssid: vlex pass: password type: WPA2-PSK
     * Source: http://bit.ly/1JmRqn6
     */

    public static void connect(Context context, String ssid, String key) {

        WifiManager wifi = (WifiManager) context.getSystemService(context.WIFI_SERVICE);

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

}
