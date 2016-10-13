package com.vungle.publisher;

import android.content.Context;
import android.net.wifi.WifiManager;
import dagger.Provides;

/* compiled from: vungle */
public final class fh {

    public static class 1 extends fk {
        @Provides
        final WifiManager a(Context context) {
            return (WifiManager) context.getSystemService("wifi");
        }
    }
}
