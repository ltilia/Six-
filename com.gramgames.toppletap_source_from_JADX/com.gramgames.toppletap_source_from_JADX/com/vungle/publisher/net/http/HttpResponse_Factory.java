package com.vungle.publisher.net.http;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum HttpResponse_Factory implements Factory<HttpResponse> {
    ;

    private HttpResponse_Factory(String str) {
    }

    public final HttpResponse get() {
        return new HttpResponse();
    }

    public static Factory<HttpResponse> create() {
        return INSTANCE;
    }
}
