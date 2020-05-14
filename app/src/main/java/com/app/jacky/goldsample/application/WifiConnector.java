package com.app.jacky.goldsample.application;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;

import static android.text.TextUtils.isEmpty;

/**
 * Created by jacky on 2018/8/29 0029.
 *
 *
 // Receive network broadcast for incorrect password and wifi really connected
 private class NetworkReceiver extends BroadcastReceiver
 {
     @Override
     public void onReceive(Context context, Intent intent) {
     if(intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION))
     {
         int linkWifiResult = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR,0);
         if(linkWifiResult == WifiManager.ERROR_AUTHENTICATING)
         {
         if(netId != -1) {
         if(wifiConnector.removeNetworkById(netId))
         {
         netId = -1;
         //Toast.makeText(MainActivity.this,"Remove network success!",Toast.LENGTH_LONG).show();
         }
         }
         Toast.makeText(MainActivity.this,"Wifi password incorrect!",Toast.LENGTH_LONG).show();
         }
     }
     else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION))
     {
         Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
         if (null != parcelableExtra) {
         NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
         NetworkInfo.State state = networkInfo.getState();
         if(state == NetworkInfo.State.CONNECTED){
         //Wifi connected
         Toast.makeText(MainActivity.this,"Wifi connected!",Toast.LENGTH_LONG).show();
         if(isConnecting) {
             isConnecting = false;
             wifiConnector.startScan();
        }
     }
     else {
        //Wifi not connected
        //Toast.makeText(MainActivity.this,"Wifi not connected!",Toast.LENGTH_LONG).show();
     }
}
 //Register receiver in activity onCreate
 intentFilter = new IntentFilter();
 intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
 intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
 networkReceiver = new NetworkReceiver();
 registerReceiver(networkReceiver,intentFilter);

 //then must unregister it in onDestory
 unregisterReceiver(networkReceiver);
 */

public class WifiConnector {
    private Handler mHandler;
    private WifiManager wifiManager;

    public static final int MSG_ON_SCAN_WIFI_SUCCESS = 0X1001;
    public static final int MSG_ON_SCAN_WIFI_FAILED = 0X1002;
    public static final int MSG_ON_WIFI_ENABLED_SUCCESS = 0X1003;
    public static final int MSG_ON_WIFI_ENABLED_FAILED = 0X1004;
    public enum WifiCipherType {
        WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
    }

    //Constructor init wifi manager and message handler
    public WifiConnector(Handler handler,WifiManager wifiManager) {
        this.wifiManager = wifiManager;
        this.mHandler = handler;

    }

    //Get current connected wifi ssid
    public String getConnectedWifi()
    {
        return wifiManager.getConnectionInfo().getSSID().replaceAll("\"","").trim();
    }

    // Connect wifi by ssid ,password and wifi cipher type
    public void connect(String ssid, String password, WifiCipherType type) {
        Thread thread = new Thread(new ConnectRunnable(ssid, password, type));
        thread.start();
    }

    public WifiCipherType getType(ScanResult scanResult){
        WifiCipherType type;
        if (scanResult.capabilities.contains("WPA") || scanResult.capabilities.contains("wpa")) {
            type = WifiConnector.WifiCipherType.WIFICIPHER_WPA;
        } else if (scanResult.capabilities.contains("WEP") || scanResult.capabilities.contains("wep")) {
            type = WifiConnector.WifiCipherType.WIFICIPHER_WEP;
        } else {
            type = WifiConnector.WifiCipherType.WIFICIPHER_NOPASS;
        }
        return type;
    }

    //Start scan wifi
    public void startScan()
    {
        Thread thread = new Thread(new ScanRunnable());
        thread.start();
    }
    // If wifi already config
    public WifiConfiguration isExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = wifiManager
                .getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            Log.e("WifiConnector","Exist wifi:" + existingConfig.SSID);
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }

    public WifiConfiguration createWifiInfo(String SSID, String Password,
                                             WifiCipherType Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        // no password
        if (Type == WifiCipherType.WIFICIPHER_NOPASS) {
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        }
        // wep
        if (Type == WifiCipherType.WIFICIPHER_WEP) {
            if (!isEmpty(Password)) {
                if (isHexWepKey(Password)) {
                    config.wepKeys[0] = Password;
                } else {
                    config.wepKeys[0] = "\"" + Password + "\"";
                }
            }
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        // wpa
        if (Type == WifiCipherType.WIFICIPHER_WPA) {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

    //Open wifi if not open
    public boolean openWifi() {
        boolean bRet = true;
        if (!wifiManager.isWifiEnabled()) {
            bRet = wifiManager.setWifiEnabled(true);
        }
        return bRet;
    }

	private class ScanRunnable implements Runnable
	{
		@Override
        public void run() {
            Message msg = new Message();
            try {
				List<ScanResult> result;
				wifiManager.startScan();
				result = wifiManager.getScanResults();
				msg.what = MSG_ON_SCAN_WIFI_SUCCESS;
                mHandler.sendMessage(msg);
				msg.obj = result;
            } catch (Exception e) {
                // TODO: handle exception
                msg.what = MSG_ON_SCAN_WIFI_FAILED;
                msg.obj = e.getMessage();
                e.printStackTrace();
                mHandler.sendMessage(msg);
            }
        }
	}
	
    private class ConnectRunnable implements Runnable {
        private String ssid;
        private String password;
        private WifiCipherType type;
        public ConnectRunnable(String ssid, String password, WifiCipherType type) {
            this.ssid = ssid;
            this.password = password;
            this.type = type;
        }
        @Override
        public void run() {
            try {
                //open wifi
                openWifi();
                Thread.sleep(200);
                // wait for wifi enable
                while (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
                    try {
                        // Sleep for release cpu
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                    }
                }
                WifiConfiguration wifiConfig = null;
                if (StringUtil.isStringValid(password)) {
                     wifiConfig = createWifiInfo(ssid, password, type);
                    if (wifiConfig == null) {
                        mHandler.sendEmptyMessage(MSG_ON_WIFI_ENABLED_FAILED);
                        return;
                    }
                }
                WifiConfiguration tempConfig = isExsits(ssid);
                int netID = -1;
                if (tempConfig != null) {
                    netID = tempConfig.networkId;
                }
                else
                {
                    netID = wifiManager.addNetwork(wifiConfig);
                }
                boolean enabled = wifiManager.enableNetwork(netID, true);
                if(!enabled)
				{
                    mHandler.sendEmptyMessage(MSG_ON_WIFI_ENABLED_FAILED);
					return;
				}
                Message msg = new Message();
                msg.what = MSG_ON_WIFI_ENABLED_SUCCESS;
                msg.obj = netID;
                mHandler.sendMessage(msg);
            } catch (Exception e) {
                // TODO: handle exception
                mHandler.sendEmptyMessage(MSG_ON_WIFI_ENABLED_FAILED);
                e.printStackTrace();
            }
        }
    }

    public boolean removeNetworkById(int netId)
    {
        return wifiManager.removeNetwork(netId);
    }

    private static boolean isHexWepKey(String wepKey) {
        final int len = wepKey.length();
        // WEP-40, WEP-104, and some vendors using 256-bit WEP (WEP-232?)
        if (len != 10 && len != 26 && len != 58) {
            return false;
        }
        return isHex(wepKey);
    }
    private static boolean isHex(String key) {
        for (int i = key.length() - 1; i >= 0; i--) {
            final char c = key.charAt(i);
            if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'F' || c >= 'a'
                    && c <= 'f')) {
                return false;
            }
        }
        return true;
    }

}