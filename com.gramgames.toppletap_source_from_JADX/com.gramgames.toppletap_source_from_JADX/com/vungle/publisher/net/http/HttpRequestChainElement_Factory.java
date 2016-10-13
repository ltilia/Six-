package com.vungle.publisher.net.http;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum HttpRequestChainElement_Factory implements Factory<HttpRequestChainElement> {
    ;

    private HttpRequestChainElement_Factory(String str) {
    }

    public final HttpRequestChainElement get() {
        return new HttpRequestChainElement();
    }

    public static Factory<HttpRequestChainElement> create() {
        return INSTANCE;
    }
}
