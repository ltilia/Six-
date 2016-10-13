package com.applovin.impl.sdk;

import java.util.Map;

class bt {
    final /* synthetic */ br a;
    private int b;
    private String c;
    private Map d;

    bt(br brVar, String str, Map map) {
        this(brVar, str, map, 0);
    }

    bt(br brVar, String str, Map map, int i) {
        this.a = brVar;
        this.b = i;
        this.c = str + "&postback_ts=" + System.currentTimeMillis();
        this.d = map;
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String b() {
        return this.c;
    }

    public Map c() {
        return this.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r5 = (com.applovin.impl.sdk.bt) r5;
        r2 = r4.b;
        r3 = r5.b;
        if (r2 != r3) goto L_0x0005;
    L_0x001a:
        r2 = r4.c;
        if (r2 == 0) goto L_0x0039;
    L_0x001e:
        r2 = r4.c;
        r3 = r5.c;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0028:
        r2 = r4.d;
        if (r2 == 0) goto L_0x003e;
    L_0x002c:
        r2 = r4.d;
        r3 = r5.d;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0037;
    L_0x0036:
        r0 = r1;
    L_0x0037:
        r1 = r0;
        goto L_0x0005;
    L_0x0039:
        r2 = r5.c;
        if (r2 == 0) goto L_0x0028;
    L_0x003d:
        goto L_0x0005;
    L_0x003e:
        r2 = r5.d;
        if (r2 != 0) goto L_0x0036;
    L_0x0042:
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bt.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.c != null ? this.c.hashCode() : 0) + (this.b * 31)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "PostbackRequest{attemptNumber=" + this.b + ", targetUrl='" + this.c + '\'' + ", requestBody=" + this.d + '}';
    }
}
