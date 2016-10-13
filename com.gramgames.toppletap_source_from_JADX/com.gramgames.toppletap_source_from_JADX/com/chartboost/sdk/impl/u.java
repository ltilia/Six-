package com.chartboost.sdk.impl;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.chartboost.sdk.impl.b.a;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.nearby.connection.Connections;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class u implements f {
    protected static final boolean a;
    private static int d;
    private static int e;
    protected final x b;
    protected final v c;

    static {
        a = t.b;
        d = GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCK_FAILURE;
        e = Connections.MAX_RELIABLE_MESSAGE_LEN;
    }

    public u(x xVar) {
        this(xVar, new v(e));
    }

    public u(x xVar, v vVar) {
        this.b = xVar;
        this.c = vVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.chartboost.sdk.impl.i a(com.chartboost.sdk.impl.l<?> r19) throws com.chartboost.sdk.impl.s {
        /*
        r18 = this;
        r16 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r14 = 0;
        r6 = java.util.Collections.emptyMap();
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x00ee }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x00ee }
        r4 = r19.f();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x00ee }
        r0 = r18;
        r0.a(r2, r4);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x00ee }
        r0 = r18;
        r4 = r0.b;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x00ee }
        r0 = r19;
        r15 = r4.a(r0, r2);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x00ee }
        r12 = r15.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r4 = r12.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r2 = r15.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r6 = a(r2);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r4 != r2) goto L_0x0065;
    L_0x0036:
        r2 = r19.f();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        if (r2 != 0) goto L_0x004c;
    L_0x003c:
        r3 = new com.chartboost.sdk.impl.i;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r4 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r5 = 0;
        r7 = 1;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
    L_0x004b:
        return r3;
    L_0x004c:
        r3 = r2.g;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r3.putAll(r6);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r7 = new com.chartboost.sdk.impl.i;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r9 = r2.a;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r10 = r2.g;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r11 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r12 = r2 - r16;
        r7.<init>(r8, r9, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r3 = r7;
        goto L_0x004b;
    L_0x0065:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        if (r2 == 0) goto L_0x00af;
    L_0x006b:
        r2 = r15.getEntity();	 Catch:{ OutOfMemoryError -> 0x009f, IOException -> 0x00a7, SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0 }
        r0 = r18;
        r11 = r0.a(r2);	 Catch:{ OutOfMemoryError -> 0x009f, IOException -> 0x00a7, SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0 }
    L_0x0075:
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
        r8 = r2 - r16;
        r7 = r18;
        r10 = r19;
        r7.a(r8, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 < r2) goto L_0x008a;
    L_0x0086:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r4 <= r2) goto L_0x00b3;
    L_0x008a:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
    L_0x0090:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.chartboost.sdk.impl.r;
        r3.<init>();
        r0 = r19;
        a(r2, r0, r3);
        goto L_0x0004;
    L_0x009f:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r2 = 0;
        r11 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        goto L_0x0075;
    L_0x00a7:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        r2 = 0;
        r11 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        goto L_0x0075;
    L_0x00af:
        r2 = 0;
        r11 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x0147 }
        goto L_0x0075;
    L_0x00b3:
        r3 = new com.chartboost.sdk.impl.i;	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
        r8 = r8 - r16;
        r5 = r11;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x0090, ConnectTimeoutException -> 0x00c1, MalformedURLException -> 0x00d0, IOException -> 0x014b }
        goto L_0x004b;
    L_0x00c1:
        r2 = move-exception;
        r2 = "connection";
        r3 = new com.chartboost.sdk.impl.r;
        r3.<init>();
        r0 = r19;
        a(r2, r0, r3);
        goto L_0x0004;
    L_0x00d0:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Bad URL ";
        r4 = r4.append(r5);
        r5 = r19.d();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00ee:
        r2 = move-exception;
        r5 = r14;
    L_0x00f0:
        r7 = 0;
        if (r3 == 0) goto L_0x0135;
    L_0x00f3:
        r2 = r3.getStatusLine();
        r4 = r2.getStatusCode();
        r2 = "Unexpected response code %d for %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r8 = 0;
        r9 = java.lang.Integer.valueOf(r4);
        r3[r8] = r9;
        r8 = 1;
        r9 = r19.d();
        r3[r8] = r9;
        com.chartboost.sdk.impl.t.c(r2, r3);
        if (r5 == 0) goto L_0x0141;
    L_0x0113:
        r3 = new com.chartboost.sdk.impl.i;
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r4 == r2) goto L_0x0127;
    L_0x0123:
        r2 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r4 != r2) goto L_0x013b;
    L_0x0127:
        r2 = "auth";
        r4 = new com.chartboost.sdk.impl.a;
        r4.<init>(r3);
        r0 = r19;
        a(r2, r0, r4);
        goto L_0x0004;
    L_0x0135:
        r3 = new com.chartboost.sdk.impl.j;
        r3.<init>(r2);
        throw r3;
    L_0x013b:
        r2 = new com.chartboost.sdk.impl.q;
        r2.<init>(r3);
        throw r2;
    L_0x0141:
        r2 = new com.chartboost.sdk.impl.h;
        r2.<init>(r7);
        throw r2;
    L_0x0147:
        r2 = move-exception;
        r5 = r14;
        r3 = r15;
        goto L_0x00f0;
    L_0x014b:
        r2 = move-exception;
        r5 = r11;
        r3 = r15;
        goto L_0x00f0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.u.a(com.chartboost.sdk.impl.l):com.chartboost.sdk.impl.i");
    }

    private void a(long j, l<?> lVar, byte[] bArr, StatusLine statusLine) {
        if (a || j > ((long) d)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = lVar;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(lVar.u().b());
            t.b(str, objArr);
        }
    }

    private static void a(String str, l<?> lVar, s sVar) throws s {
        p u = lVar.u();
        int t = lVar.t();
        try {
            u.a(sVar);
            lVar.a(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(t)}));
        } catch (s e) {
            lVar.a(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(t)}));
            throw e;
        }
    }

    private void a(Map<String, String> map, a aVar) {
        if (aVar != null) {
            if (aVar.b != null) {
                map.put("If-None-Match", aVar.b);
            }
            if (aVar.d > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(aVar.d)));
            }
        }
    }

    private byte[] a(HttpEntity httpEntity) throws IOException, q {
        ab abVar = new ab(this.c, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new q();
            }
            bArr = this.c.a((int) AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                abVar.write(bArr, 0, read);
            }
            byte[] toByteArray = abVar.toByteArray();
            return toByteArray;
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
                t.a("IOException error occured when calling consumingContent", new Object[0]);
            } catch (OutOfMemoryError e2) {
                System.gc();
                t.a("Volley Out of Memory error occured when calling consumingContent", new Object[0]);
            }
            this.c.a(bArr);
            abVar.close();
        }
    }

    protected static Map<String, String> a(Header[] headerArr) {
        Map<String, String> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
