package com.vungle.publisher.net.http;

/* compiled from: vungle */
public abstract class InfiniteRetryHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    public InfiniteRetryHttpResponseHandler() {
        this.j = 0;
        this.h = 0;
        this.i = 0;
    }
}
