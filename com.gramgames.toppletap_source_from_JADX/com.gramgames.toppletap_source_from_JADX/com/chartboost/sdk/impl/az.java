package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError;
import com.facebook.GraphResponse;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.games.Games;
import com.google.android.gms.nearby.messages.Strategy;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.mopub.volley.DefaultRetryPolicy;
import gs.gram.mopub.BuildConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class az implements Observer {
    private static az b;
    private static ConcurrentHashMap<ay, File> e;
    private static List<Runnable> g;
    private ax a;
    private m c;
    private h d;
    private ConcurrentHashMap<String, b> f;
    private CountDownTimer h;

    class 1 extends CountDownTimer {
        final /* synthetic */ az a;

        1(az azVar, long j, long j2) {
            this.a = azVar;
            super(j, j2);
        }

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            this.a.c();
        }
    }

    public enum a {
        ARRAY_OF_DICTIONARY
    }

    private class b {
        final /* synthetic */ az a;
        private String b;
        private int c;
        private String d;
        private a e;
        private boolean f;
        private JSONArray g;
        private ay h;

        public b(az azVar) {
            this.a = azVar;
            this.b = null;
            this.c = 50;
            this.f = false;
            this.g = null;
            this.d = Long.toString(System.nanoTime());
            this.e = a.ARRAY_OF_DICTIONARY;
            this.g = new JSONArray();
        }

        public String a() {
            return this.d;
        }

        public void a(String str) {
            this.b = str;
        }

        public String b() {
            return this.b;
        }

        public boolean c() {
            return this.f;
        }

        public void a(boolean z) {
            this.f = z;
        }

        public void d() {
            this.g = new JSONArray();
        }

        public synchronized ay a(ay ayVar) {
            com.chartboost.sdk.Libraries.e.a j = ayVar.j();
            if (j.c()) {
                j = j.a(this.b);
                if (!j.b()) {
                    if (this.e == a.ARRAY_OF_DICTIONARY) {
                        if (this.a.a.c() || (this.h != null && this.h.r())) {
                            this.d = Long.toString(System.nanoTime());
                            ayVar.a(this.b, new JSONArray().put(j.e()));
                        } else {
                            if (this.g.length() == this.c) {
                                this.d = Long.toString(System.nanoTime());
                                this.g = new JSONArray();
                            }
                            this.g.put(j.e());
                            if (this.h != null) {
                                az.e.remove(this.h);
                            }
                            ayVar.a(this.b, this.g);
                            this.h = ayVar;
                            ayVar = this.h;
                        }
                    }
                }
            }
            return ayVar;
        }

        public void b(ay ayVar) {
            this.h = ayVar;
        }
    }

    public static class c extends s {
        private CBError b;

        public c(CBError cBError) {
            this.b = cBError;
        }
    }

    public static class d {
        private com.chartboost.sdk.Libraries.e.a a;
        private i b;

        public d(com.chartboost.sdk.Libraries.e.a aVar, i iVar) {
            this.a = aVar;
            this.b = iVar;
        }
    }

    private class e implements Runnable {
        final /* synthetic */ az a;
        private ay b;

        private class a extends l<d> {
            final /* synthetic */ e a;
            private ay b;

            protected /* synthetic */ void b(Object obj) {
                a((d) obj);
            }

            public a(e eVar, int i, String str, ay ayVar) {
                this.a = eVar;
                super(i, str, null);
                this.b = ayVar;
            }

            public String p() {
                String c = this.b.c();
                if (c == null) {
                    return "application/json; charset=utf-8";
                }
                return c;
            }

            public byte[] q() {
                return (this.b.j() == null ? BuildConfig.FLAVOR : this.b.j().toString()).getBytes();
            }

            public com.chartboost.sdk.impl.l.a s() {
                return this.b.o();
            }

            public Map<String, String> i() throws a {
                Map<String, String> hashMap = new HashMap();
                for (Entry entry : this.b.k().entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                }
                return hashMap;
            }

            protected n<d> a(i iVar) {
                CBError cBError;
                Exception exception;
                Object obj = com.chartboost.sdk.Libraries.e.a.a;
                int i = iVar.a;
                if (i <= Strategy.TTL_SECONDS_DEFAULT || i >= 200) {
                    try {
                        String str;
                        byte[] bArr = iVar.b;
                        if (bArr != null) {
                            str = new String(bArr);
                        } else {
                            str = null;
                        }
                        if (str != null) {
                            Object jSONObject = new JSONObject(new JSONTokener(str));
                            com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a(jSONObject);
                            try {
                                CBError cBError2;
                                com.chartboost.sdk.Libraries.g.a m = this.b.m();
                                CBLogging.c("CBRequestManager", "Request " + this.b.h() + " succeeded. Response code: " + i + ", body: " + jSONObject.toString(4));
                                if (a.f(Games.EXTRA_STATUS) == 404) {
                                    cBError2 = new CBError(com.chartboost.sdk.Model.CBError.a.HTTP_NOT_FOUND, "404 error from server");
                                } else {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    if (m == null || m.a(a, stringBuilder)) {
                                        cBError2 = null;
                                    } else {
                                        cBError2 = new CBError(com.chartboost.sdk.Model.CBError.a.UNEXPECTED_RESPONSE, "Json response failed validation");
                                        CBLogging.b("CBRequestManager", "Json response failed validation: " + stringBuilder.toString());
                                    }
                                }
                                com.chartboost.sdk.Libraries.e.a aVar = a;
                                cBError = cBError2;
                                com.chartboost.sdk.Libraries.e.a aVar2 = aVar;
                            } catch (Exception e) {
                                exception = e;
                                obj = a;
                                cBError = new CBError(com.chartboost.sdk.Model.CBError.a.MISCELLANEOUS, exception.getLocalizedMessage());
                                if (obj.c()) {
                                }
                                return n.a(new c(cBError));
                            }
                        }
                        cBError = new CBError(com.chartboost.sdk.Model.CBError.a.INVALID_RESPONSE, "Response is not a valid json object");
                    } catch (Exception e2) {
                        exception = e2;
                        cBError = new CBError(com.chartboost.sdk.Model.CBError.a.MISCELLANEOUS, exception.getLocalizedMessage());
                        if (obj.c()) {
                        }
                        return n.a(new c(cBError));
                    }
                }
                CBLogging.b("CBRequestManager", "Request " + this.b.h() + " failed. Response code: " + i);
                cBError = new CBError(com.chartboost.sdk.Model.CBError.a.NETWORK_FAILURE, "Request failed. Response code: " + i + " is not valid ");
                if (obj.c() || cBError != null) {
                    return n.a(new c(cBError));
                }
                return n.a(new d(com.chartboost.sdk.Libraries.e.a.a(obj), iVar), null);
            }

            protected void a(d dVar) {
                if (!(this.a.b.s() == null || dVar == null)) {
                    this.a.b.s().a(dVar.a, this.a.b);
                }
                if (!this.a.b.i()) {
                    this.a.a.d.e((File) az.e.get(this.a.b));
                    az.e.remove(this.a.b);
                    b bVar = (b) this.a.a.f.get(this.a.b.h());
                    if (bVar != null && !TextUtils.isEmpty(bVar.b()) && bVar.c() && bVar.h == this.a.b) {
                        bVar.d();
                        bVar.b(null);
                    }
                    this.a.b.d(false);
                    this.a.a.a(this.a.b, dVar.b, null, true);
                }
            }

            public void b(s sVar) {
                if (!(this.a.b == null || com.chartboost.sdk.c.n() || this.a.b.i() || !az.e.containsKey(this.a.b))) {
                    this.a.a.d.e((File) az.e.get(this.a.b));
                    az.e.remove(this.a.b);
                }
                if (sVar != null) {
                    CBError a;
                    if (sVar instanceof c) {
                        a = ((c) sVar).b;
                    } else {
                        a = new CBError(com.chartboost.sdk.Model.CBError.a.NETWORK_FAILURE, sVar.getMessage());
                    }
                    com.chartboost.sdk.Libraries.e.a aVar = com.chartboost.sdk.Libraries.e.a.a;
                    if (sVar != null) {
                        try {
                            if (!(sVar.a == null || sVar.a.b == null || sVar.a.b.length <= 0)) {
                                aVar = com.chartboost.sdk.Libraries.e.a.k(new String(sVar.a.b));
                            }
                        } catch (Throwable e) {
                            CBLogging.d("CBRequestManager", "unable to read error json", e);
                        }
                    }
                    if (sVar.a == null || sVar.a.a != 200) {
                        if (this.a.b.s() != null) {
                            this.a.b.s().a(aVar, this.a.b, a);
                        }
                        if (!this.a.b.i()) {
                            this.a.b.d(false);
                            this.a.a.a(this.a.b, sVar.a, a, false);
                            return;
                        }
                        return;
                    }
                    a(new d(aVar, sVar.a));
                }
            }
        }

        public e(az azVar, ay ayVar) {
            this.a = azVar;
            this.b = ayVar;
        }

        public void run() {
            this.b.d();
            this.b.e();
            String format = String.format("%s%s", new Object[]{"https://live.chartboost.com", this.b.f()});
            this.b.a();
            l aVar = new a(this, 1, format, this.b);
            aVar.a(new d(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            aVar.a(false);
            this.a.c.a(aVar);
        }
    }

    static {
        g = new ArrayList();
    }

    public static az a(Context context) {
        if (b == null) {
            synchronized (az.class) {
                if (b == null) {
                    b = new az(context);
                }
            }
        }
        return b;
    }

    private az(Context context) {
        this.a = null;
        this.c = new m(new aa(), new u(new y()));
        this.a = ax.a();
        this.d = new h(false);
        e = new ConcurrentHashMap();
        this.f = new ConcurrentHashMap();
        j();
        this.a.addObserver(this);
    }

    public m a() {
        return this.c;
    }

    private void a(ay ayVar, i iVar, CBError cBError, boolean z) {
        if (ayVar != null) {
            String str;
            com.chartboost.sdk.Libraries.e.b[] bVarArr = new com.chartboost.sdk.Libraries.e.b[5];
            bVarArr[0] = com.chartboost.sdk.Libraries.e.a("endpoint", ayVar.h());
            bVarArr[1] = com.chartboost.sdk.Libraries.e.a("statuscode", iVar == null ? "None" : Integer.valueOf(iVar.a));
            bVarArr[2] = com.chartboost.sdk.Libraries.e.a(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, cBError == null ? "None" : cBError.a());
            bVarArr[3] = com.chartboost.sdk.Libraries.e.a("errorDescription", cBError == null ? "None" : cBError.b());
            bVarArr[4] = com.chartboost.sdk.Libraries.e.a("retryCount", Integer.valueOf(ayVar.p()));
            com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a(bVarArr);
            String str2 = "request_manager";
            String str3 = ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID;
            if (z) {
                str = GraphResponse.SUCCESS_KEY;
            } else {
                str = "failure";
            }
            com.chartboost.sdk.Tracking.a.a(str2, str3, str, null, null, null, a.e());
        }
    }

    protected void a(ay ayVar, com.chartboost.sdk.impl.ay.c cVar) {
        if (ayVar != null) {
            if (this.a.c()) {
                if (!ayVar.i() && ayVar.q()) {
                    ayVar.c(false);
                    a(ayVar);
                }
                a(new e(this, ayVar));
                return;
            }
            CBError cBError = new CBError(com.chartboost.sdk.Model.CBError.a.INTERNET_UNAVAILABLE, "Internet Unavailable");
            ayVar.d(false);
            if (!ayVar.i()) {
                if (ayVar.q()) {
                    ayVar.c(false);
                    a(ayVar);
                }
                a(ayVar, null, cBError, false);
                if (cVar != null) {
                    CBLogging.b("Network failure", String.format("request %s failed with error : %s", new Object[]{ayVar.h(), cBError.b()}));
                    cVar.a(com.chartboost.sdk.Libraries.e.a.a, ayVar, cBError);
                }
            }
        }
    }

    public void a(Runnable runnable) {
        Object obj = null;
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
            if (c == com.chartboost.sdk.Libraries.c.a.PRELOAD || c == com.chartboost.sdk.Libraries.c.a.LOADING) {
                g.add(runnable);
            } else {
                obj = 1;
            }
        }
        if (obj != null) {
            runnable.run();
        }
    }

    public static void b() {
        List<Runnable> arrayList = new ArrayList();
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            arrayList.addAll(g);
            g.clear();
        }
        for (Runnable run : arrayList) {
            run.run();
        }
    }

    public synchronized void c() {
        synchronized (this) {
            if (e != null && !e.isEmpty()) {
                for (ay ayVar : e.keySet()) {
                    if (!(ayVar == null || ayVar.r())) {
                        ayVar.a(ayVar.p() + 1);
                        ayVar.a(ayVar.s());
                    }
                }
                e();
            } else if (this.d.g() != null) {
                String[] list = this.d.g().list();
                if (list != null) {
                    for (String str : list) {
                        ay a = a(str);
                        if (a != null) {
                            e.put(a, this.d.c(this.d.g(), str));
                            a.c(false);
                            a.a(a.p() + 1);
                            a.a(a.s());
                        }
                    }
                }
                e();
            }
        }
    }

    public synchronized void d() {
        try {
            String[] c;
            if (this.d != null) {
                c = this.d.c(this.d.g());
            } else {
                c = null;
            }
            if (c != null && c.length > 0) {
                for (String str : c) {
                    com.chartboost.sdk.Libraries.e.a a = this.d.a(this.d.g(), str);
                    if (a.c()) {
                        this.d.b(this.d.g(), str);
                        ay a2 = ay.a(a);
                        if (a2 != null) {
                            a2.a(true);
                            a2.t();
                        } else {
                            CBLogging.b("CBRequestManager", "Error processing video completion event");
                        }
                    }
                }
            }
        } catch (Throwable e) {
            CBLogging.b("CBRequestManager", "Error executing saved requests", e);
        }
    }

    private void a(ay ayVar) {
        if (ayVar != null) {
            Object a;
            if (ayVar.l()) {
                b bVar = (b) this.f.get(ayVar.h());
                if (bVar == null || TextUtils.isEmpty(bVar.b()) || !bVar.c()) {
                    a = this.d.a(this.d.g(), null, ayVar.u());
                } else {
                    ayVar = bVar.a(ayVar);
                    a = this.d.a(this.d.g(), bVar.a(), ayVar.u());
                }
            } else {
                a = null;
            }
            if ((ayVar.l() || ayVar.n()) && a != null) {
                e.put(ayVar, a);
            }
        }
    }

    private ay a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a a = this.d.a(this.d.g(), str);
        if (a.c()) {
            return ay.a(a);
        }
        return null;
    }

    public void e() {
        if (this.h == null) {
            this.h = new 1(this, 240000, 1000).start();
        }
    }

    public void f() {
        CBLogging.a("CBRequestManager", "Timer stopped:");
        if (this.h != null) {
            this.h.cancel();
            this.h = null;
        }
    }

    public void update(Observable observable, Object data) {
        if (this.h != null) {
            f();
        }
        c();
    }

    public ConcurrentHashMap<ay, File> g() {
        return e;
    }

    public h h() {
        return this.d;
    }

    private void j() {
        b bVar = new b(this);
        bVar.a("track_info");
        bVar.a(true);
        this.f.put("/post-install-event/".concat("tracking"), bVar);
    }
}
