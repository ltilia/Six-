package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class DownloadHttpResponseHandler_Factory implements Factory<DownloadHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<DownloadHttpResponseHandler> b;

    static {
        a = !DownloadHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public DownloadHttpResponseHandler_Factory(MembersInjector<DownloadHttpResponseHandler> downloadHttpResponseHandlerMembersInjector) {
        if (a || downloadHttpResponseHandlerMembersInjector != null) {
            this.b = downloadHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final DownloadHttpResponseHandler get() {
        return (DownloadHttpResponseHandler) MembersInjectors.injectMembers(this.b, new DownloadHttpResponseHandler());
    }

    public static Factory<DownloadHttpResponseHandler> create(MembersInjector<DownloadHttpResponseHandler> downloadHttpResponseHandlerMembersInjector) {
        return new DownloadHttpResponseHandler_Factory(downloadHttpResponseHandlerMembersInjector);
    }
}
