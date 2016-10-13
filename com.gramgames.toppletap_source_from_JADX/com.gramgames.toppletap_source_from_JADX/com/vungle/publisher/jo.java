package com.vungle.publisher;

/* compiled from: vungle */
final class jo implements Runnable {
    final ju a;
    volatile boolean b;
    private final jp c;

    jo(jp jpVar) {
        this.c = jpVar;
        this.a = new ju();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r5 = this;
        r4 = 0;
    L_0x0001:
        r0 = r5.a;	 Catch:{ InterruptedException -> 0x0020 }
        r0 = r0.b();	 Catch:{ InterruptedException -> 0x0020 }
        if (r0 != 0) goto L_0x001a;
    L_0x0009:
        monitor-enter(r5);	 Catch:{ InterruptedException -> 0x0020 }
        r0 = r5.a;	 Catch:{ all -> 0x0044 }
        r0 = r0.a();	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0019;
    L_0x0012:
        r0 = 0;
        r5.b = r0;	 Catch:{ all -> 0x0044 }
        monitor-exit(r5);	 Catch:{ all -> 0x0044 }
        r5.b = r4;
    L_0x0018:
        return;
    L_0x0019:
        monitor-exit(r5);	 Catch:{ all -> 0x0044 }
    L_0x001a:
        r1 = r5.c;	 Catch:{ InterruptedException -> 0x0020 }
        r1.a(r0);	 Catch:{ InterruptedException -> 0x0020 }
        goto L_0x0001;
    L_0x0020:
        r0 = move-exception;
        r1 = "Event";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0047 }
        r2.<init>();	 Catch:{ all -> 0x0047 }
        r3 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0047 }
        r3 = r3.getName();	 Catch:{ all -> 0x0047 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0047 }
        r3 = " was interruppted";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0047 }
        r2 = r2.toString();	 Catch:{ all -> 0x0047 }
        com.vungle.log.Logger.w(r1, r2, r0);	 Catch:{ all -> 0x0047 }
        r5.b = r4;
        goto L_0x0018;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0044 }
        throw r0;	 Catch:{ InterruptedException -> 0x0020 }
    L_0x0047:
        r0 = move-exception;
        r5.b = r4;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.jo.run():void");
    }
}
