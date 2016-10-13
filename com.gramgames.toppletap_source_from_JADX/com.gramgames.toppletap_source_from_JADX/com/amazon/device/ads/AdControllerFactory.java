package com.amazon.device.ads;

import android.content.Context;

class AdControllerFactory {
    private static AdControlAccessor cachedAdControlAccessor;
    private static AdController cachedAdController;

    AdControllerFactory() {
    }

    static {
        cachedAdController = null;
        cachedAdControlAccessor = null;
    }

    public static void cacheAdController(AdController adController) {
        cachedAdController = adController;
    }

    public static void cacheAdControlAccessor(AdControlAccessor adControlAccessor) {
        cachedAdControlAccessor = adControlAccessor;
    }

    public static AdController getCachedAdController() {
        return cachedAdController;
    }

    public static AdControlAccessor getCachedAdControlAccessor() {
        return cachedAdControlAccessor;
    }

    public static AdController removeCachedAdController() {
        AdController currentAdController = cachedAdController;
        cachedAdController = null;
        return currentAdController;
    }

    public static AdControlAccessor removeCachedAdControlAccessor() {
        AdControlAccessor currenAdControlAccessor = cachedAdControlAccessor;
        cachedAdControlAccessor = null;
        return currenAdControlAccessor;
    }

    public AdController buildAdController(Context context, AdSize adSize) {
        try {
            AdController adController = new AdController(context, adSize);
            return adController;
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
