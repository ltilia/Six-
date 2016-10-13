package com.vungle.publisher;

import com.facebook.ads.AdError;
import com.vungle.log.Logger;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.protocol.RequestConfigAsync;
import com.vungle.publisher.protocol.message.RequestConfigResponse;
import com.vungle.publisher.protocol.message.RequestConfigResponse.Factory;
import com.vungle.publisher.protocol.message.RequestConfigResponse.a;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Provider;
import org.json.JSONException;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public final class hr extends InfiniteRetryHttpResponseHandler {
    @Inject
    Factory a;
    @Inject
    SdkConfig b;
    @Inject
    EventBus c;
    @Inject
    Provider<RequestConfigAsync> d;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.all.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.wifi.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        RequestConfigResponse requestConfigResponse = (RequestConfigResponse) this.a.a(a(httpResponse.a));
        Integer num = requestConfigResponse.b;
        if (num != null && num.intValue() > 0) {
            ((RequestConfigAsync) this.d.get()).a((long) (num.intValue() * AdError.NETWORK_ERROR_CODE));
        }
        a aVar = requestConfigResponse.d;
        if (aVar != null) {
            switch (1.a[aVar.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    this.b.a(fv.values());
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    this.b.a(fv.wifi);
                    break;
                default:
                    Logger.w(Logger.NETWORK_TAG, "unhandled streaming connectivity type " + aVar);
                    break;
            }
        }
        SdkConfig sdkConfig = this.b;
        boolean equals = Boolean.TRUE.equals(requestConfigResponse.a);
        Logger.d(Logger.CONFIG_TAG, (equals ? "enabling" : "disabling") + " ad streaming");
        sdkConfig.b = equals;
        SdkConfig sdkConfig2 = this.b;
        boolean equals2 = Boolean.TRUE.equals(requestConfigResponse.e);
        Logger.d(Logger.CONFIG_TAG, "setting exception reporting enabled: " + equals2);
        sdkConfig2.j.edit().putBoolean(sdkConfig2.e, equals2).commit();
        Integer num2 = requestConfigResponse.c;
        if (num2 == null) {
            Logger.w(Logger.NETWORK_TAG, "null request streaming ad timeout millis");
        } else {
            sdkConfig = this.b;
            int intValue = num2.intValue();
            Logger.d(Logger.CONFIG_TAG, "setting streaming response timeout " + intValue + " ms");
            sdkConfig.d = intValue;
        }
        Long l = requestConfigResponse.f;
        Long l2 = requestConfigResponse.g;
        if (l2 != null) {
            sdkConfig = this.b;
            long longValue = l2.longValue();
            Logger.d(Logger.CONFIG_TAG, "setting app fingerprint frequency to " + longValue);
            sdkConfig.g = longValue;
        }
        if (l != null) {
            this.b.a(l.longValue());
        }
        this.c.a(new fy());
    }
}
