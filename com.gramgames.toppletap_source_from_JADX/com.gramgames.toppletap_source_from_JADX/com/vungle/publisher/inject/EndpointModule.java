package com.vungle.publisher.inject;

import dagger.Module;

@Module
/* compiled from: vungle */
public class EndpointModule {
    String a;
    String b;

    public EndpointModule() {
        this.a = "http://api.vungle.com/api/v4/";
        this.b = "https://ingest.vungle.com/";
    }

    public EndpointModule setVungleBaseUrl(String vungleBaseUrl) {
        this.a = vungleBaseUrl;
        return this;
    }

    public EndpointModule setIngestBaseUrl(String ingestBaseUrl) {
        this.b = ingestBaseUrl;
        return this;
    }
}
