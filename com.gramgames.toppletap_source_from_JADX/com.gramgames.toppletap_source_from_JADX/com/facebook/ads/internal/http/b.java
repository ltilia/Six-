package com.facebook.ads.internal.http;

import java.io.IOException;
import java.net.ConnectException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

class b implements Runnable {
    private final AbstractHttpClient a;
    private final HttpContext b;
    private final HttpUriRequest c;
    private final c d;
    private int e;

    public b(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, c cVar) {
        this.a = abstractHttpClient;
        this.b = httpContext;
        this.c = httpUriRequest;
        this.d = cVar;
    }

    private void a() {
        if (!Thread.currentThread().isInterrupted()) {
            HttpResponse execute = this.a.execute(this.c, this.b);
            if (!Thread.currentThread().isInterrupted() && this.d != null) {
                this.d.a(execute);
            }
        }
    }

    private void b() {
        int i;
        boolean z = true;
        Throwable th = null;
        HttpRequestRetryHandler httpRequestRetryHandler = this.a.getHttpRequestRetryHandler();
        while (z) {
            try {
                a();
                return;
            } catch (Throwable th2) {
                if (this.d != null) {
                    this.d.b(th2, "can't resolve host");
                    return;
                }
                return;
            } catch (Throwable th22) {
                if (this.d != null) {
                    this.d.b(th22, "can't resolve host");
                    return;
                }
                return;
            } catch (Throwable th222) {
                if (this.d != null) {
                    this.d.b(th222, "socket time out");
                    return;
                }
                return;
            } catch (IOException e) {
                th222 = e;
                i = this.e + 1;
                this.e = i;
                z = httpRequestRetryHandler.retryRequest(th222, i, this.b);
            } catch (NullPointerException e2) {
                th222 = new IOException("NPE in HttpClient" + e2.getMessage());
                i = this.e + 1;
                this.e = i;
                z = httpRequestRetryHandler.retryRequest(th222, i, this.b);
            }
        }
        ConnectException connectException = new ConnectException();
        connectException.initCause(th222);
        throw connectException;
    }

    public void run() {
        try {
            if (this.d != null) {
                this.d.c();
            }
            b();
            if (this.d != null) {
                this.d.d();
            }
        } catch (Throwable e) {
            Throwable th = e;
            if (this.d != null) {
                this.d.d();
                this.d.b(th, (String) null);
            }
        }
    }
}
