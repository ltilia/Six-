package com.vungle.publisher.net.http;

import android.os.Bundle;
import android.text.TextUtils;
import com.vungle.publisher.ek;
import java.util.regex.Pattern;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class HttpRequest {
    public static final Pattern a;
    public String b;
    public Bundle c;
    public String d;

    /* compiled from: vungle */
    public static abstract class Factory<T extends HttpRequest> {
        @Inject
        public ek b;

        public abstract T b();

        public T c() {
            T b = b();
            Bundle bundle = new Bundle();
            Object r = this.b.r();
            if (!TextUtils.isEmpty(r)) {
                bundle.putString("User-Agent", r);
            }
            b.c = bundle;
            return b;
        }
    }

    public enum a {
        GET,
        HEAD,
        POST
    }

    public enum b {
        download,
        reportAd,
        requestConfig,
        requestLocalAd,
        requestStreamingAd,
        sessionEnd,
        sessionStart,
        trackEvent,
        trackInstall,
        unfilledAd,
        reportExceptions,
        appFingerprint
    }

    public abstract b a();

    public abstract a b();

    static {
        a = Pattern.compile("^https://");
    }

    public final void a(String str, String str2) {
        this.c.putString(str, str2);
    }

    public String toString() {
        return "{" + a() + "}";
    }
}
