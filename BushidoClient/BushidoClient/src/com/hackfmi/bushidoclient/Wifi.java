
package com.hackfmi.bushidoclient;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

public class Wifi {

    private WifiManager wifi;
    private Context context;
    
    /*
     * ssid: vlex
     * pass: password
     * type: WPA2-PSK
     * 
     */

    public boolean turnOn(){
        this.wifi = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        Log.d("WIFI","Status before " + this.wifi.isWifiEnabled());
        this.wifi.setWifiEnabled(true);
        Log.d("WIFI","Status after " + this.wifi.isWifiEnabled());
        
        return this.wifi.isWifiEnabled();
    }
    
    public void connect(Context context,String ssid, String key){
        
        this.context = context;
        this.turnOn();
        
        WifiConfiguration wifi_config = new WifiConfiguration();
        wifi_config.SSID = String.format("\"%s\"", ssid);
        wifi_config.preSharedKey = String.format("\"%s\"", key);

        //remember id
        int network_id = wifi.addNetwork(wifi_config);
        wifi.disconnect();
        wifi.enableNetwork(network_id, true);
        wifi.reconnect();
    }
    
}
