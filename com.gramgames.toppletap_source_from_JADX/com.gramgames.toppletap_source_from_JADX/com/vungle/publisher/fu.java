package com.vungle.publisher;

import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import com.vungle.publisher.net.AndroidNetwork;
import com.vungle.publisher.net.NetworkBroadcastReceiver;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fu implements MembersInjector<AndroidNetwork> {
    static final /* synthetic */ boolean a;
    private final Provider<ConnectivityManager> b;
    private final Provider<NetworkBroadcastReceiver> c;
    private final Provider<TelephonyManager> d;

    static {
        a = !fu.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AndroidNetwork androidNetwork = (AndroidNetwork) obj;
        if (androidNetwork == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        androidNetwork.a = (ConnectivityManager) this.b.get();
        androidNetwork.b = this.c;
        androidNetwork.c = (TelephonyManager) this.d.get();
    }

    private fu(Provider<ConnectivityManager> provider, Provider<NetworkBroadcastReceiver> provider2, Provider<TelephonyManager> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AndroidNetwork> a(Provider<ConnectivityManager> provider, Provider<NetworkBroadcastReceiver> provider2, Provider<TelephonyManager> provider3) {
        return new fu(provider, provider2, provider3);
    }
}
