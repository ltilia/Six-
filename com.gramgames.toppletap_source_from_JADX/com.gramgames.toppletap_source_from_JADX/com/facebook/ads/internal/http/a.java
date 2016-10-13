package com.facebook.ads.internal.http;

import android.content.Context;
import com.facebook.ads.internal.dto.f;
import com.facebook.ads.internal.e;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.h;
import com.facebook.ads.internal.util.p;
import com.facebook.ads.internal.util.q;
import com.facebook.internal.Utility;
import com.google.android.exoplayer.C;
import com.mopub.common.Constants;
import com.mopub.mobileads.CustomEventBannerAdapter;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

public class a {
    private static final p a;
    private static final ThreadPoolExecutor b;
    private final DefaultHttpClient c;
    private final Map<Context, List<WeakReference<Future<?>>>> d;
    private final Map<String, String> e;
    private Context f;

    class 1 implements HttpRequestInterceptor {
        final /* synthetic */ a a;

        1(a aVar) {
            this.a = aVar;
        }

        public void process(HttpRequest httpRequest, HttpContext httpContext) {
            if (!httpRequest.containsHeader("Accept-Encoding")) {
                httpRequest.addHeader("Accept-Encoding", "gzip");
            }
            for (String str : this.a.e.keySet()) {
                httpRequest.addHeader(str, (String) this.a.e.get(str));
            }
        }
    }

    class 2 implements HttpResponseInterceptor {
        final /* synthetic */ a a;

        2(a aVar) {
            this.a = aVar;
        }

        public void process(HttpResponse httpResponse, HttpContext httpContext) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                Header contentEncoding = entity.getContentEncoding();
                if (contentEncoding != null) {
                    for (HeaderElement name : contentEncoding.getElements()) {
                        if (name.getName().equalsIgnoreCase("gzip")) {
                            httpResponse.setEntity(new a(httpResponse.getEntity()));
                            return;
                        }
                    }
                }
            }
        }
    }

    private static class a extends HttpEntityWrapper {
        public a(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public InputStream getContent() {
            return new GZIPInputStream(this.wrappedEntity.getContent());
        }

        public long getContentLength() {
            return -1;
        }
    }

    static {
        a = new p();
        b = (ThreadPoolExecutor) Executors.newCachedThreadPool(a);
    }

    public a(Context context, e eVar) {
        this.f = context;
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        basicHttpParams.setParameter("http.protocol.cookie-policy", "compatibility");
        ConnManagerParams.setTimeout(basicHttpParams, 10000);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(100));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
        HttpConnectionParams.setSoTimeout(basicHttpParams, CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, Utility.DEFAULT_STREAM_BUFFER_SIZE);
        HttpProtocolParams.setUserAgent(basicHttpParams, h.a(context, eVar) + " [" + "FBAN/AudienceNetworkForAndroid;" + "FBSN/" + "Android" + ";" + "FBSV/" + f.a + ";" + "FBAB/" + f.d + ";" + "FBAV/" + f.f + ";" + "FBBV/" + f.g + ";" + "FBLC/" + Locale.getDefault().toString() + "]");
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(Constants.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        if (g.a()) {
            a(basicHttpParams, schemeRegistry);
        } else {
            schemeRegistry.register(new Scheme(Constants.HTTPS, SSLSocketFactory.getSocketFactory(), 443));
        }
        this.c = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        this.c.addRequestInterceptor(new 1(this));
        this.c.addResponseInterceptor(new 2(this));
        this.d = new WeakHashMap();
        this.e = new HashMap();
    }

    private HttpEntity a(d dVar) {
        return dVar != null ? dVar.a() : null;
    }

    private HttpEntityEnclosingRequestBase a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }

    private void a(BasicHttpParams basicHttpParams, SchemeRegistry schemeRegistry) {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory qVar = new q(instance);
            qVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpProtocolParams.setContentCharset(basicHttpParams, C.UTF8_NAME);
            schemeRegistry.register(new Scheme(Constants.HTTPS, qVar, 443));
        } catch (Exception e) {
        }
    }

    public void a(String str, d dVar, c cVar) {
        a(str, a(dVar), null, cVar);
    }

    public void a(String str, HttpEntity httpEntity, String str2, c cVar) {
        a(this.c, a(new HttpPost(str), httpEntity), str2, cVar);
    }

    protected void a(DefaultHttpClient defaultHttpClient, HttpUriRequest httpUriRequest, String str, c cVar) {
        if (str != null) {
            httpUriRequest.addHeader("Content-Type", str);
        }
        Future submit = b.submit(new b(defaultHttpClient, new SyncBasicHttpContext(new BasicHttpContext()), httpUriRequest, cVar));
        List list = (List) this.d.get(this.f);
        if (list == null) {
            list = new LinkedList();
            this.d.put(this.f, list);
        }
        list.add(new WeakReference(submit));
    }

    public void a(boolean z) {
        List<WeakReference> list = (List) this.d.get(this.f);
        if (list != null) {
            for (WeakReference weakReference : list) {
                Future future = (Future) weakReference.get();
                if (future != null) {
                    future.cancel(z);
                }
            }
        }
        this.d.remove(this.f);
    }
}
