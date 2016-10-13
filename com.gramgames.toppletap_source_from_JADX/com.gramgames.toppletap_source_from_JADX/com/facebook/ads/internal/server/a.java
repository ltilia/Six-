package com.facebook.ads.internal.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.AdSettings;
import com.facebook.ads.internal.AdErrorType;
import com.facebook.ads.internal.b;
import com.facebook.ads.internal.dto.e;
import com.facebook.ads.internal.dto.f;
import com.facebook.ads.internal.http.c;
import com.facebook.ads.internal.http.d;
import com.facebook.ads.internal.util.AdInternalSettings;
import com.facebook.ads.internal.util.p;
import com.facebook.ads.internal.util.s;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;
import org.json.simple.parser.Yytoken;

public class a {
    private static final p g;
    private static final ThreadPoolExecutor h;
    Map<String, String> a;
    private final b b;
    private a c;
    private e d;
    private com.facebook.ads.internal.http.a e;
    private final String f;

    public interface a {
        void a(b bVar);

        void a(d dVar);
    }

    class 1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ e b;
        final /* synthetic */ a c;

        1(a aVar, Context context, e eVar) {
            this.c = aVar;
            this.a = context;
            this.b = eVar;
        }

        public void run() {
            f.b(this.a);
            this.c.a = this.b.e();
            try {
                this.c.e = new com.facebook.ads.internal.http.a(this.a, this.b.e);
                this.c.e.a(this.c.f, new d(this.c.a), this.c.b());
            } catch (Exception e) {
                this.c.a(AdErrorType.AD_REQUEST_FAILED.getAdErrorWrapper(e.getMessage()));
            }
        }
    }

    class 2 extends c {
        final /* synthetic */ a a;

        2(a aVar) {
            this.a = aVar;
        }

        public void a() {
        }

        public void a(int i, String str) {
            com.facebook.ads.internal.util.d.b(this.a.d);
            this.a.e = null;
            this.a.a(str);
        }

        public void a(Throwable th, String str) {
            com.facebook.ads.internal.util.d.b(this.a.d);
            this.a.e = null;
            try {
                c a = this.a.b.a(str);
                if (a.a() == com.facebook.ads.internal.server.c.a.ERROR) {
                    String c = ((e) a).c();
                    a aVar = this.a;
                    AdErrorType adErrorType = AdErrorType.ERROR_MESSAGE;
                    if (c != null) {
                        str = c;
                    }
                    aVar.a(adErrorType.getAdErrorWrapper(str));
                    return;
                }
            } catch (JSONException e) {
            }
            this.a.a(new b(AdErrorType.NETWORK_ERROR, th.getMessage()));
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ d a;
        final /* synthetic */ a b;

        3(a aVar, d dVar) {
            this.b = aVar;
            this.a = dVar;
        }

        public void run() {
            this.b.c.a(this.a);
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ a b;

        4(a aVar, b bVar) {
            this.b = aVar;
            this.a = bVar;
        }

        public void run() {
            this.b.c.a(this.a);
        }
    }

    static /* synthetic */ class 5 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[com.facebook.ads.internal.server.c.a.values().length];
            try {
                a[com.facebook.ads.internal.server.c.a.ADS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[com.facebook.ads.internal.server.c.a.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        g = new p();
        h = (ThreadPoolExecutor) Executors.newCachedThreadPool(g);
    }

    public a() {
        this.b = b.a();
        String urlPrefix = AdSettings.getUrlPrefix();
        if (AdInternalSettings.shouldUseLiveRailEndpoint()) {
            if (s.a(urlPrefix)) {
                urlPrefix = "https://ad6.liverail.com/";
            } else {
                urlPrefix = String.format("https://ad6.%s.liverail.com/", new Object[]{urlPrefix});
            }
            this.f = urlPrefix;
            return;
        }
        if (s.a(urlPrefix)) {
            urlPrefix = "https://graph.facebook.com/network_ads_common/";
        } else {
            urlPrefix = String.format("https://graph.%s.facebook.com/network_ads_common/", new Object[]{urlPrefix});
        }
        this.f = urlPrefix;
    }

    private void a(b bVar) {
        if (this.c != null) {
            new Handler(Looper.getMainLooper()).post(new 4(this, bVar));
        }
        a();
    }

    private void a(d dVar) {
        if (this.c != null) {
            new Handler(Looper.getMainLooper()).post(new 3(this, dVar));
        }
        a();
    }

    private void a(String str) {
        try {
            c a = this.b.a(str);
            com.facebook.ads.internal.dto.c b = a.b();
            if (b != null) {
                com.facebook.ads.internal.util.d.a(b.a().c(), this.d);
            }
            switch (5.a[a.a().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    d dVar = (d) a;
                    if (b != null && b.a().d()) {
                        com.facebook.ads.internal.util.d.a(str, this.d);
                    }
                    a(dVar);
                    return;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    String c = ((e) a).c();
                    AdErrorType adErrorType = AdErrorType.ERROR_MESSAGE;
                    if (c != null) {
                        str = c;
                    }
                    a(adErrorType.getAdErrorWrapper(str));
                    return;
                default:
                    a(AdErrorType.UNKNOWN_RESPONSE.getAdErrorWrapper(str));
                    return;
            }
        } catch (Exception e) {
            a(AdErrorType.PARSER_FAILURE.getAdErrorWrapper(e.getMessage()));
        }
        a(AdErrorType.PARSER_FAILURE.getAdErrorWrapper(e.getMessage()));
    }

    private boolean a(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return z;
    }

    private c b() {
        return new 2(this);
    }

    public void a() {
        if (this.e != null) {
            this.e.a(true);
            this.e = null;
        }
    }

    public void a(Context context, e eVar) {
        a();
        if (a(context)) {
            this.d = eVar;
            if (com.facebook.ads.internal.util.d.a(eVar)) {
                String c = com.facebook.ads.internal.util.d.c(eVar);
                if (c != null) {
                    a(c);
                    return;
                } else {
                    a(AdErrorType.LOAD_TOO_FREQUENTLY.getAdErrorWrapper(null));
                    return;
                }
            }
            h.submit(new 1(this, context, eVar));
            return;
        }
        a(new b(AdErrorType.NETWORK_ERROR, "No network connection"));
    }

    public void a(a aVar) {
        this.c = aVar;
    }
}
