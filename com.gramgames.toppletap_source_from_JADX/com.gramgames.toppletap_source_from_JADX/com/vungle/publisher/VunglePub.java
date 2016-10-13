package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.fh.1;
import com.vungle.publisher.inject.Injector;

/* compiled from: vungle */
public class VunglePub extends VunglePubBase {
    public static final String VERSION = "VungleDroid/3.3.5";
    private static final VunglePub m;

    static {
        m = new VunglePub();
    }

    public static VunglePub getInstance() {
        return m;
    }

    VunglePub() {
    }

    protected final void a(Context context, String str) {
        Injector instance = Injector.getInstance();
        instance.c = new 1();
        instance.a = false;
        super.a(context, str);
    }

    public boolean init(Context context, String vungleAppId) {
        return super.init(context, vungleAppId);
    }

    public Demographic getDemographic() {
        return super.getDemographic();
    }

    public void addEventListeners(EventListener... eventListeners) {
        super.addEventListeners(eventListeners);
    }

    public void clearEventListeners() {
        super.clearEventListeners();
    }

    public void setEventListeners(EventListener... eventListeners) {
        super.setEventListeners(eventListeners);
    }

    public void removeEventListeners(EventListener... eventListeners) {
        super.removeEventListeners(eventListeners);
    }

    public AdConfig getGlobalAdConfig() {
        return super.getGlobalAdConfig();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public boolean isAdPlayable() {
        return super.isAdPlayable();
    }

    public void playAd() {
        super.playAd();
    }

    public void playAd(AdConfig adConfig) {
        super.playAd(adConfig);
    }
}
