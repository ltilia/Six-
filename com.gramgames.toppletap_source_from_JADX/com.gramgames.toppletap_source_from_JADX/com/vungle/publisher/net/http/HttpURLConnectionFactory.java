package com.vungle.publisher.net.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class HttpURLConnectionFactory {
    @Inject
    HttpURLConnectionFactory() {
    }

    public static HttpURLConnection a(String str) throws MalformedURLException, IOException {
        return (HttpURLConnection) new URL(str).openConnection();
    }
}
