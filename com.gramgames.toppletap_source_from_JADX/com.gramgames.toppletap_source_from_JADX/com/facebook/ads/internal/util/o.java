package com.facebook.ads.internal.util;

import android.os.AsyncTask;
import android.util.Log;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

public class o extends AsyncTask<String, Void, Void> {
    private static final String a;
    private static final Set<String> b;
    private Map<String, String> c;
    private Map<String, String> d;

    static {
        a = o.class.getSimpleName();
        b = new HashSet();
        b.add("#");
        b.add("null");
    }

    public o() {
        this(null, null);
    }

    public o(Map<String, String> map) {
        this(map, null);
    }

    public o(Map<String, String> map, Map<String, String> map2) {
        this.c = map;
        this.d = map2;
    }

    private String a(String str, String str2, String str3) {
        if (s.a(str) || s.a(str2) || s.a(str3)) {
            return str;
        }
        return str + (str.contains("?") ? "&" : "?") + str2 + "=" + URLEncoder.encode(str3);
    }

    private boolean a(String str) {
        HttpClient b = g.b();
        try {
            if (this.d == null || this.d.size() == 0) {
                return b.execute(new HttpGet(str)).getStatusLine().getStatusCode() == 200;
            }
            HttpUriRequest httpPost = new HttpPost(str);
            List arrayList = new ArrayList(1);
            for (Entry entry : this.d.entrySet()) {
                arrayList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
            return b.execute(httpPost).getStatusLine().getStatusCode() == 200;
        } catch (Throwable e) {
            Log.e(a, "Error opening url: " + str, e);
            return false;
        }
    }

    private String b(String str) {
        try {
            str = a(str, "analog", g.a(a.a()));
        } catch (Exception e) {
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.Void a(java.lang.String... r6) {
        /*
        r5 = this;
        r4 = 0;
        r0 = 0;
        r0 = r6[r0];
        r1 = com.facebook.ads.internal.util.s.a(r0);
        if (r1 != 0) goto L_0x0012;
    L_0x000a:
        r1 = b;
        r1 = r1.contains(r0);
        if (r1 == 0) goto L_0x0013;
    L_0x0012:
        return r4;
    L_0x0013:
        r0 = r5.b(r0);
        r1 = r5.c;
        if (r1 == 0) goto L_0x004d;
    L_0x001b:
        r1 = r5.c;
        r1 = r1.isEmpty();
        if (r1 != 0) goto L_0x004d;
    L_0x0023:
        r1 = r5.c;
        r1 = r1.entrySet();
        r3 = r1.iterator();
        r2 = r0;
    L_0x002e:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x004c;
    L_0x0034:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = r5.a(r2, r1, r0);
        r2 = r0;
        goto L_0x002e;
    L_0x004c:
        r0 = r2;
    L_0x004d:
        r1 = 1;
    L_0x004e:
        r2 = r1 + 1;
        r3 = 2;
        if (r1 > r3) goto L_0x0012;
    L_0x0053:
        r1 = r5.a(r0);
        if (r1 != 0) goto L_0x0012;
    L_0x0059:
        r1 = r2;
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.util.o.a(java.lang.String[]):java.lang.Void");
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }
}
