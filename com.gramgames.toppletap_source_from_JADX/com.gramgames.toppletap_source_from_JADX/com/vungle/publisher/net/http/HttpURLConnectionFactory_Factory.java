package com.vungle.publisher.net.http;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum HttpURLConnectionFactory_Factory implements Factory<HttpURLConnectionFactory> {
    ;

    private HttpURLConnectionFactory_Factory(String str) {
    }

    public final HttpURLConnectionFactory get() {
        return new HttpURLConnectionFactory();
    }

    public static Factory<HttpURLConnectionFactory> create() {
        return INSTANCE;
    }
}
