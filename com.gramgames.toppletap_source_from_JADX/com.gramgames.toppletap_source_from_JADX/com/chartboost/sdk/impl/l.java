package com.chartboost.sdk.impl;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.exoplayer.C;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class l<T> implements Comparable<l<T>> {
    private final a a;
    private final int b;
    private final String c;
    private final int d;
    private final com.chartboost.sdk.impl.n.a e;
    private Integer f;
    private m g;
    private boolean h;
    private boolean i;
    private boolean j;
    private p k;
    private com.chartboost.sdk.impl.b.a l;
    private Object m;

    class 1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ long b;
        final /* synthetic */ l c;

        1(l lVar, String str, long j) {
            this.c = lVar;
            this.a = str;
            this.b = j;
        }

        public void run() {
            this.c.a.a(this.a, this.b);
            this.c.a.a(toString());
        }
    }

    public enum a {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected abstract n<T> a(i iVar);

    protected abstract void b(T t);

    public /* synthetic */ int compareTo(Object x0) {
        return a((l) x0);
    }

    public l(int i, String str, com.chartboost.sdk.impl.n.a aVar) {
        a aVar2;
        if (a.a) {
            aVar2 = new a();
        } else {
            aVar2 = null;
        }
        this.a = aVar2;
        this.h = false;
        this.i = false;
        this.j = false;
        this.l = null;
        this.b = i;
        this.c = str;
        this.e = aVar;
        a(new d());
        this.d = c(str);
    }

    public int a() {
        return this.b;
    }

    public l<?> a(Object obj) {
        this.m = obj;
        return this;
    }

    public Object b() {
        return this.m;
    }

    public int c() {
        return this.d;
    }

    private static int c(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public l<?> a(p pVar) {
        this.k = pVar;
        return this;
    }

    public void a(String str) {
        if (a.a) {
            this.a.a(str, Thread.currentThread().getId());
        }
    }

    void b(String str) {
        if (this.g != null) {
            this.g.b(this);
        }
        if (a.a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new 1(this, str, id));
                return;
            }
            this.a.a(str, id);
            this.a.a(toString());
        }
    }

    public l<?> a(m mVar) {
        this.g = mVar;
        return this;
    }

    public final l<?> a(int i) {
        this.f = Integer.valueOf(i);
        return this;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return d();
    }

    public l<?> a(com.chartboost.sdk.impl.b.a aVar) {
        this.l = aVar;
        return this;
    }

    public com.chartboost.sdk.impl.b.a f() {
        return this.l;
    }

    public void g() {
        this.i = true;
    }

    public boolean h() {
        return this.i;
    }

    public Map<String, String> i() throws a {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> j() throws a {
        return n();
    }

    @Deprecated
    protected String k() {
        return o();
    }

    @Deprecated
    public String l() {
        return p();
    }

    @Deprecated
    public byte[] m() throws a {
        Map j = j();
        if (j == null || j.size() <= 0) {
            return null;
        }
        return a(j, k());
    }

    protected Map<String, String> n() throws a {
        return null;
    }

    protected String o() {
        return C.UTF8_NAME;
    }

    public String p() {
        return "application/x-www-form-urlencoded; charset=" + o();
    }

    public byte[] q() throws a {
        Map n = n();
        if (n == null || n.size() <= 0) {
            return null;
        }
        return a(n, o());
    }

    private byte[] a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final l<?> a(boolean z) {
        this.h = z;
        return this;
    }

    public final boolean r() {
        return this.h;
    }

    public a s() {
        return a.NORMAL;
    }

    public final int t() {
        return this.k.a();
    }

    public p u() {
        return this.k;
    }

    public void v() {
        this.j = true;
    }

    public boolean w() {
        return this.j;
    }

    protected s a(s sVar) {
        return sVar;
    }

    public void b(s sVar) {
        if (this.e != null) {
            this.e.a(sVar);
        }
    }

    public int a(l<T> lVar) {
        a s = s();
        a s2 = lVar.s();
        return s == s2 ? this.f.intValue() - lVar.f.intValue() : s2.ordinal() - s.ordinal();
    }

    public String toString() {
        return (this.i ? "[X] " : "[ ] ") + d() + " " + ("0x" + Integer.toHexString(c())) + " " + s() + " " + this.f;
    }
}
