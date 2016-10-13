package com.vungle.publisher;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum AdConfig_Factory implements Factory<AdConfig> {
    ;

    private AdConfig_Factory(String str) {
    }

    public final AdConfig get() {
        return new AdConfig();
    }

    public static Factory<AdConfig> create() {
        return INSTANCE;
    }
}
