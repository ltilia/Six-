package com.vungle.publisher.event;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum EventBus_Factory implements Factory<EventBus> {
    ;

    private EventBus_Factory(String str) {
    }

    public final EventBus get() {
        return new EventBus();
    }

    public static Factory<EventBus> create() {
        return INSTANCE;
    }
}
