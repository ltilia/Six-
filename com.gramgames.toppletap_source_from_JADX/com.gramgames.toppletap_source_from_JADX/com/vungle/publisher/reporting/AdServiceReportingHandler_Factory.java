package com.vungle.publisher.reporting;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum AdServiceReportingHandler_Factory implements Factory<AdServiceReportingHandler> {
    ;

    private AdServiceReportingHandler_Factory(String str) {
    }

    public final AdServiceReportingHandler get() {
        return new AdServiceReportingHandler();
    }

    public static Factory<AdServiceReportingHandler> create() {
        return INSTANCE;
    }
}
