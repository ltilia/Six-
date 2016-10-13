package com.amazon.device.ads;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CompositeAdListenerExecutor extends AdListenerExecutor {
    private final List<AdListenerExecutor> adListenerExecutors;

    public CompositeAdListenerExecutor(MobileAdsLoggerFactory loggerFactory) {
        super(null, loggerFactory);
        this.adListenerExecutors = new ArrayList();
    }

    public void addAdListenerExecutor(AdListenerExecutor adListenerExecutor) {
        this.adListenerExecutors.add(adListenerExecutor);
    }

    private List<AdListenerExecutor> getAdListenerExecutors() {
        return this.adListenerExecutors;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdLoaded(ad, adProperties);
        }
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdFailedToLoad(ad, adError);
        }
    }

    public void onAdExpanded(Ad ad) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdExpanded(ad);
        }
    }

    public void onAdCollapsed(Ad ad) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdCollapsed(ad);
        }
    }

    public void onAdDismissed(Ad ad) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdDismissed(ad);
        }
    }

    public void onAdResized(Ad ad, Rect positionOnScreen) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdResized(ad, positionOnScreen);
        }
    }

    public void onAdExpired(Ad ad) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdExpired(ad);
        }
    }

    public void onSpecialUrlClicked(Ad ad, String url) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onSpecialUrlClicked(ad, url);
        }
    }

    public ActionCode onAdReceived(Ad ad, AdData adData) {
        Iterator i$ = getAdListenerExecutors().iterator();
        if (i$.hasNext()) {
            return ((AdListenerExecutor) i$.next()).onAdReceived(ad, adData);
        }
        return null;
    }

    public void onAdEvent(AdEvent adEvent) {
        for (AdListenerExecutor adListenerExecutor : getAdListenerExecutors()) {
            adListenerExecutor.onAdEvent(adEvent);
        }
    }
}
