package com.applovin.impl.sdk;

import java.util.Map;

public class bx {
    private final String a;
    private final Map b;
    private final long c;
    private final String d;

    public bx(String str, Map map, long j, String str2) {
        this.a = str;
        this.b = map;
        this.c = j;
        this.d = str2;
    }

    public String a() {
        return this.a;
    }

    public Map b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        if (r6 != r7) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r7 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r6.getClass();
        r3 = r7.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r7 = (com.applovin.impl.sdk.bx) r7;
        r2 = r6.c;
        r4 = r7.c;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x0005;
    L_0x001c:
        r2 = r6.a;
        if (r2 == 0) goto L_0x0049;
    L_0x0020:
        r2 = r6.a;
        r3 = r7.a;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x002a:
        r2 = r6.b;
        if (r2 == 0) goto L_0x004e;
    L_0x002e:
        r2 = r6.b;
        r3 = r7.b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0038:
        r2 = r6.d;
        if (r2 == 0) goto L_0x0053;
    L_0x003c:
        r2 = r6.d;
        r3 = r7.d;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0047;
    L_0x0046:
        r0 = r1;
    L_0x0047:
        r1 = r0;
        goto L_0x0005;
    L_0x0049:
        r2 = r7.a;
        if (r2 == 0) goto L_0x002a;
    L_0x004d:
        goto L_0x0005;
    L_0x004e:
        r2 = r7.b;
        if (r2 == 0) goto L_0x0038;
    L_0x0052:
        goto L_0x0005;
    L_0x0053:
        r2 = r7.d;
        if (r2 != 0) goto L_0x0046;
    L_0x0057:
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bx.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.b != null ? this.b.hashCode() : 0) + ((this.a != null ? this.a.hashCode() : 0) * 31)) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "SdkEvent{eventType='" + this.a + '\'' + ", parameters=" + this.b + ", creationTsMillis=" + this.c + ", uniqueIdentifier='" + this.d + '\'' + '}';
    }
}
