package com.vungle.publisher;

import android.content.Context;
import android.net.wifi.WifiManager;
import dagger.Module;
import dagger.Provides;

@Module
/* compiled from: vungle */
public class fk {
    @Provides
    public WifiManager a(Context context) {
        return (WifiManager) context.getSystemService("wifi");
    }
}
