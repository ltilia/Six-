package com.vungle.publisher.env;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdaptiveDeviceIdStrategy extends AdvertisingDeviceIdStrategy {
    @Inject
    Context a;
    @Inject
    WifiManager b;

    protected final boolean a(AndroidDevice androidDevice) {
        return androidDevice.f();
    }

    protected final boolean b(AndroidDevice androidDevice) {
        try {
            boolean b = super.b(androidDevice);
            if (b) {
                Logger.v(Logger.DEVICE_TAG, "have advertising ID - not fetching fallback device IDs");
                return b;
            }
            String string;
            Logger.d(Logger.DEVICE_TAG, "ensuring fallback device IDs");
            if (AndroidDevice.a(androidDevice.e)) {
                Logger.v(Logger.DEVICE_TAG, "existing android ID " + androidDevice.c());
            } else {
                string = Secure.getString(this.a.getContentResolver(), "android_id");
                Logger.d(Logger.DEVICE_TAG, "fetched android ID " + string);
                if (androidDevice.b()) {
                    Logger.w(Logger.DEVICE_TAG, "have advertising id - not setting androidId");
                } else {
                    Logger.d(Logger.DEVICE_TAG, "setting android ID " + string);
                    androidDevice.e = string;
                    androidDevice.e();
                }
            }
            try {
                string = androidDevice.j();
                if (string == null) {
                    WifiInfo connectionInfo = this.b.getConnectionInfo();
                    if (connectionInfo == null) {
                        Logger.d(Logger.DEVICE_TAG, "unable to get MAC address - not connected");
                    } else {
                        string = connectionInfo.getMacAddress();
                        Logger.d(Logger.DEVICE_TAG, "fetched MAC address " + string);
                        if (androidDevice.b()) {
                            Logger.w(Logger.DEVICE_TAG, "have advertising id - not setting mac address");
                        } else {
                            Logger.d(Logger.DEVICE_TAG, "setting MAC address " + string);
                            androidDevice.f = string;
                        }
                    }
                } else {
                    Logger.v(Logger.DEVICE_TAG, "existing MAC address " + string);
                }
            } catch (SecurityException e) {
                Logger.d(Logger.DEVICE_TAG, "unable to get MAC address - no ACCESS_WIFI_STATE permission");
            }
            return true;
        } catch (Throwable e2) {
            Logger.w(Logger.DEVICE_TAG, e2);
            return androidDevice.f();
        }
    }
}
