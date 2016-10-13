package com.chartboost.sdk.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.c;
import java.util.Observable;

public class ax extends Observable {
    private static ax c;
    private static b d;
    private boolean a;
    private boolean b;
    private a e;

    private class a extends BroadcastReceiver {
        final /* synthetic */ ax a;

        public a(ax axVar) {
            this.a = axVar;
        }

        public void onReceive(Context context, Intent intent) {
            ax a = ax.a();
            a.a(context);
            a.notifyObservers();
        }
    }

    public enum b {
        CONNECTION_UNKNOWN(-1),
        CONNECTION_ERROR(0),
        CONNECTION_WIFI(1),
        CONNECTION_MOBILE(2);
        
        private int e;

        private b(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    static {
        c = null;
        d = b.CONNECTION_UNKNOWN;
    }

    private ax() {
        this.a = true;
        this.b = false;
        this.e = null;
        this.e = new a(this);
    }

    public static ax a() {
        if (c == null) {
            c = new ax();
        }
        return c;
    }

    public int b() {
        return d.a();
    }

    public void a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                a(false);
                d = b.CONNECTION_ERROR;
                CBLogging.a("CBReachability", "NETWORK TYPE: NO Network");
                return;
            }
            a(true);
            if (activeNetworkInfo.getType() == 1) {
                d = b.CONNECTION_WIFI;
                CBLogging.a("CBReachability", "NETWORK TYPE: TYPE_WIFI");
            } else if (activeNetworkInfo.getType() == 0) {
                d = b.CONNECTION_MOBILE;
                CBLogging.a("CBReachability", "NETWORK TYPE: TYPE_MOBILE");
            }
        } catch (SecurityException e) {
            d = b.CONNECTION_UNKNOWN;
            CBLogging.b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
    }

    public void notifyObservers() {
        if (this.a) {
            setChanged();
            super.notifyObservers(this);
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    public boolean c() {
        return this.a;
    }

    public Intent b(Context context) {
        if (context == null || this.b) {
            return null;
        }
        b(true);
        CBLogging.a("CBReachability", "Network broadcast successfully registered");
        return context.registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void c(Context context) {
        if (context != null && this.b) {
            context.unregisterReceiver(this.e);
            b(false);
            CBLogging.a("CBReachability", "Network broadcast successfully unregistered");
        }
    }

    public void b(boolean z) {
        this.b = z;
    }

    public static Integer d() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) c.y().getSystemService("connectivity")).getActiveNetworkInfo();
            Object obj = (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) ? null : 1;
            if (obj != null) {
                TelephonyManager telephonyManager = (TelephonyManager) c.y().getSystemService("phone");
                if (telephonyManager != null) {
                    return Integer.valueOf(telephonyManager.getNetworkType());
                }
            }
        } catch (SecurityException e) {
            CBLogging.b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
        return null;
    }
}
