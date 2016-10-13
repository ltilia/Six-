package com.vungle.publisher.net.http;

import javax.inject.Inject;

/* compiled from: vungle */
public class FireAndForgetHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    @Inject
    public FireAndForgetHttpResponseHandler() {
        this.i = 1;
        this.h = 10;
    }
}
