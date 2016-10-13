package com.vungle.publisher.event;

import com.vungle.publisher.jp;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class EventBus {
    public final jp a;

    @Inject
    EventBus() {
        this.a = new jp();
    }

    public final void a(Object obj) {
        this.a.b(obj);
    }

    public final void b(Object obj) {
        this.a.a(obj, "onEvent", false);
    }
}
