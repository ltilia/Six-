package com.adjust.sdk.plugin;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import gs.gram.mopub.BuildConfig;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class MacAddressUtil {
    public static String getMacAddress(Context context) {
        String rawAddress = getRawMacAddress(context);
        if (rawAddress == null) {
            return null;
        }
        return removeSpaceString(rawAddress.toUpperCase(Locale.US));
    }

    private static String getRawMacAddress(Context context) {
        String wlanAddress = loadAddress("wlan0");
        if (wlanAddress != null) {
            return wlanAddress;
        }
        String ethAddress = loadAddress("eth0");
        if (ethAddress != null) {
            return ethAddress;
        }
        try {
            String wifiAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (wifiAddress != null) {
                return wifiAddress;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static String loadAddress(String interfaceName) {
        try {
            String filePath = "/sys/class/net/" + interfaceName + "/address";
            StringBuilder fileData = new StringBuilder(AdError.NETWORK_ERROR_CODE);
            BufferedReader reader = new BufferedReader(new FileReader(filePath), AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
            char[] buf = new char[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
            while (true) {
                int numRead = reader.read(buf);
                if (numRead != -1) {
                    fileData.append(String.valueOf(buf, 0, numRead));
                } else {
                    reader.close();
                    return fileData.toString();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static String removeSpaceString(String inputString) {
        if (inputString == null) {
            return null;
        }
        String outputString = inputString.replaceAll("\\s", BuildConfig.FLAVOR);
        if (TextUtils.isEmpty(outputString)) {
            return null;
        }
        return outputString;
    }
}
