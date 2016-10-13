package com.vungle.publisher.util;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum IntentFactory_Factory implements Factory<IntentFactory> {
    ;

    private IntentFactory_Factory(String str) {
    }

    public final IntentFactory get() {
        return new IntentFactory();
    }

    public static Factory<IntentFactory> create() {
        return INSTANCE;
    }
}
