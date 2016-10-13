package com.vungle.publisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: vungle */
final class jx {
    private static final Map<String, List<jw>> a;
    private static final Map<Class<?>, Class<?>> b;

    jx() {
    }

    static {
        a = new HashMap();
        b = new ConcurrentHashMap();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<com.vungle.publisher.jw> a(java.lang.Class<?> r14, java.lang.String r15) {
        /*
        r3 = 0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r14.getName();
        r0 = r0.append(r1);
        r1 = 46;
        r0 = r0.append(r1);
        r0 = r0.append(r15);
        r5 = r0.toString();
        r1 = a;
        monitor-enter(r1);
        r0 = a;	 Catch:{ all -> 0x002b }
        r0 = r0.get(r5);	 Catch:{ all -> 0x002b }
        r0 = (java.util.List) r0;	 Catch:{ all -> 0x002b }
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x002e;
    L_0x002a:
        return r0;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        throw r0;
    L_0x002e:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r6 = new java.util.HashSet;
        r6.<init>();
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r4 = r14;
    L_0x003e:
        if (r4 == 0) goto L_0x012b;
    L_0x0040:
        r0 = r4.getName();
        r2 = "java.";
        r2 = r0.startsWith(r2);
        if (r2 != 0) goto L_0x012b;
    L_0x004c:
        r2 = "javax.";
        r2 = r0.startsWith(r2);
        if (r2 != 0) goto L_0x012b;
    L_0x0054:
        r2 = "android.";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x012b;
    L_0x005c:
        r8 = r4.getMethods();
        r9 = r8.length;
        r2 = r3;
    L_0x0062:
        if (r2 >= r9) goto L_0x0124;
    L_0x0064:
        r10 = r8[r2];
        r11 = r10.getName();
        r0 = r11.startsWith(r15);
        if (r0 == 0) goto L_0x00bb;
    L_0x0070:
        r0 = r10.getModifiers();
        r12 = r0 & 1;
        if (r12 == 0) goto L_0x00fd;
    L_0x0078:
        r0 = r0 & 1032;
        if (r0 != 0) goto L_0x00fd;
    L_0x007c:
        r12 = r10.getParameterTypes();
        r0 = r12.length;
        r13 = 1;
        if (r0 != r13) goto L_0x00bb;
    L_0x0084:
        r0 = r15.length();
        r0 = r11.substring(r0);
        r13 = r0.length();
        if (r13 != 0) goto L_0x00bf;
    L_0x0092:
        r0 = com.vungle.publisher.jz.PostThread;
    L_0x0094:
        r12 = r12[r3];
        r7.setLength(r3);
        r7.append(r11);
        r11 = 62;
        r11 = r7.append(r11);
        r13 = r12.getName();
        r11.append(r13);
        r11 = r7.toString();
        r11 = r6.add(r11);
        if (r11 == 0) goto L_0x00bb;
    L_0x00b3:
        r11 = new com.vungle.publisher.jw;
        r11.<init>(r10, r0, r12);
        r1.add(r11);
    L_0x00bb:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0062;
    L_0x00bf:
        r13 = "MainThread";
        r13 = r0.equals(r13);
        if (r13 == 0) goto L_0x00ca;
    L_0x00c7:
        r0 = com.vungle.publisher.jz.MainThread;
        goto L_0x0094;
    L_0x00ca:
        r13 = "BackgroundThread";
        r13 = r0.equals(r13);
        if (r13 == 0) goto L_0x00d5;
    L_0x00d2:
        r0 = com.vungle.publisher.jz.BackgroundThread;
        goto L_0x0094;
    L_0x00d5:
        r13 = "Async";
        r0 = r0.equals(r13);
        if (r0 == 0) goto L_0x00e0;
    L_0x00dd:
        r0 = com.vungle.publisher.jz.Async;
        goto L_0x0094;
    L_0x00e0:
        r0 = b;
        r0 = r0.containsKey(r4);
        if (r0 != 0) goto L_0x00bb;
    L_0x00e8:
        r0 = new com.vungle.publisher.jq;
        r1 = new java.lang.StringBuilder;
        r2 = "Illegal onEvent method, check for typos: ";
        r1.<init>(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00fd:
        r0 = b;
        r0 = r0.containsKey(r4);
        if (r0 != 0) goto L_0x00bb;
    L_0x0105:
        r0 = "VungleEvent";
        r10 = new java.lang.StringBuilder;
        r12 = "Skipping method (not public, static or abstract): ";
        r10.<init>(r12);
        r10 = r10.append(r4);
        r12 = ".";
        r10 = r10.append(r12);
        r10 = r10.append(r11);
        r10 = r10.toString();
        com.vungle.log.Logger.d(r0, r10);
        goto L_0x00bb;
    L_0x0124:
        r0 = r4.getSuperclass();
        r4 = r0;
        goto L_0x003e;
    L_0x012b:
        r0 = r1.isEmpty();
        if (r0 == 0) goto L_0x0150;
    L_0x0131:
        r0 = new com.vungle.publisher.jq;
        r1 = new java.lang.StringBuilder;
        r2 = "Subscriber ";
        r1.<init>(r2);
        r1 = r1.append(r14);
        r2 = " has no public methods called ";
        r1 = r1.append(r2);
        r1 = r1.append(r15);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0150:
        r2 = a;
        monitor-enter(r2);
        r0 = a;	 Catch:{ all -> 0x015c }
        r0.put(r5, r1);	 Catch:{ all -> 0x015c }
        monitor-exit(r2);	 Catch:{ all -> 0x015c }
        r0 = r1;
        goto L_0x002a;
    L_0x015c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x015c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.jx.a(java.lang.Class, java.lang.String):java.util.List<com.vungle.publisher.jw>");
    }
}
