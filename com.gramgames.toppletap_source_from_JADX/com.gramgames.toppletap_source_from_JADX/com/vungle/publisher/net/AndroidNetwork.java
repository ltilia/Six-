package com.vungle.publisher.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.vungle.log.Logger;
import com.vungle.publisher.fv;
import com.vungle.publisher.fw;
import com.vungle.publisher.inject.Injector;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

@Singleton
/* compiled from: vungle */
public class AndroidNetwork implements fw {
    @Inject
    public ConnectivityManager a;
    @Inject
    public Provider<NetworkBroadcastReceiver> b;
    @Inject
    public TelephonyManager c;

    @Inject
    AndroidNetwork() {
        Injector.b().a(this);
    }

    public final fv a() {
        try {
            NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            int type = activeNetworkInfo.getType();
            switch (type) {
                case Yylex.YYINITIAL /*0*/:
                    return fv.mobile;
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                case Yytoken.TYPE_COLON /*6*/:
                    return fv.wifi;
                default:
                    Logger.d(Logger.NETWORK_TAG, "unknown connectivity type: " + type);
                    return null;
            }
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting connectivity type", e);
            return null;
        }
    }

    public final String b() {
        String str = null;
        try {
            str = this.c.getNetworkOperatorName();
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting network operator", e);
        }
        return str;
    }
}
