package com.vungle.publisher;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum Demographic_Factory implements Factory<Demographic> {
    ;

    private Demographic_Factory(String str) {
    }

    public final Demographic get() {
        return new Demographic();
    }

    public static Factory<Demographic> create() {
        return INSTANCE;
    }
}
