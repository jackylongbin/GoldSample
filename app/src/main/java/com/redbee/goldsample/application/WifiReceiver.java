package com.redbee.goldsample.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;

import com.redbee.goldsample.logger.LoggerUtil;

public class WifiReceiver extends BroadcastReceiver {

    private WifiManager mWifiManager;

    public WifiReceiver(WifiManager mWifiManager) {
        this.mWifiManager = mWifiManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //LoggerUtil.d("Wifi receiver action: "+ intent.getAction());
        String action = intent.getAction();
        if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) {

            int linkWifiResult = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 0);
            if (linkWifiResult == WifiManager.ERROR_AUTHENTICATING) {
                LoggerUtil.d("Wifi password error.");
            }
        } else if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {

            LoggerUtil.d("Wifi state changed action: " + action);
            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                if (state == NetworkInfo.State.CONNECTED) {
                    String ssid = mWifiManager.getConnectionInfo().getSSID().replace("\"","");
                    LoggerUtil.d("Wifi connected:" + ssid);
/*
                    if(ssidToConnect != null && ssidToConnect.equals(ssid))
                    {
                        Toast.makeText(MainActivity.this, "连接到指定wifi成功", Toast.LENGTH_LONG).show();
                    }
                    wifiConnector.startScan();*/
                }
            }
        }
    }
}
