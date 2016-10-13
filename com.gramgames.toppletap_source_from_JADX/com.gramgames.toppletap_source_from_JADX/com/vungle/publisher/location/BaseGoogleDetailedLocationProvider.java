package com.vungle.publisher.location;

import android.location.Location;
import com.google.android.gms.common.ConnectionResult;
import com.vungle.log.Logger;
import com.vungle.publisher.fo;

/* compiled from: vungle */
abstract class BaseGoogleDetailedLocationProvider<T> implements fo {
    T a;
    private int b;
    private final Object c;
    private boolean d;

    protected abstract String a();

    protected abstract boolean a(T t);

    protected abstract void b(T t);

    protected abstract Location c(T t);

    protected abstract T c();

    protected abstract void d(T t);

    BaseGoogleDetailedLocationProvider() {
        this.c = new Object();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.location.Location b() {
        /*
        r6 = this;
        r0 = 0;
        r1 = r6.e();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        if (r1 == 0) goto L_0x0027;
    L_0x0007:
        r1 = r6.a;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r0 = r6.c(r1);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        if (r0 != 0) goto L_0x002b;
    L_0x000f:
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "no location returned from ";
        r2.<init>(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = r6.a();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.toString();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        com.vungle.log.Logger.d(r1, r2);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
    L_0x0027:
        r6.g();
    L_0x002a:
        return r0;
    L_0x002b:
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "provider: ";
        r2.<init>(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = r0.getProvider();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.toString();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        com.vungle.log.Logger.v(r1, r2);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "latitude: ";
        r2.<init>(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r4 = r0.getLatitude();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.append(r4);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "\u00b0";
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.toString();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        com.vungle.log.Logger.v(r1, r2);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "longitude: ";
        r2.<init>(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r4 = r0.getLongitude();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.append(r4);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "\u00b0";
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.toString();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        com.vungle.log.Logger.v(r1, r2);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "accuracy: ";
        r2.<init>(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = r0.getAccuracy();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = " m";
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.toString();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        com.vungle.log.Logger.v(r1, r2);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "speed: ";
        r2.<init>(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = r0.getSpeed();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = " m/s";
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.toString();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        com.vungle.log.Logger.v(r1, r2);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = "time: ";
        r2.<init>(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r4 = r0.getTime();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.append(r4);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r3 = " ms";
        r2 = r2.append(r3);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        r2 = r2.toString();	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        com.vungle.log.Logger.v(r1, r2);	 Catch:{ SecurityException -> 0x00db, Exception -> 0x00f9 }
        goto L_0x0027;
    L_0x00db:
        r1 = move-exception;
        r1 = "VungleLocation";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0117 }
        r3 = "no location permissions using ";
        r2.<init>(r3);	 Catch:{ all -> 0x0117 }
        r3 = r6.a();	 Catch:{ all -> 0x0117 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0117 }
        r2 = r2.toString();	 Catch:{ all -> 0x0117 }
        com.vungle.log.Logger.d(r1, r2);	 Catch:{ all -> 0x0117 }
        r6.g();
        goto L_0x002a;
    L_0x00f9:
        r1 = move-exception;
        r2 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0117 }
        r4 = "error obtaining detailed location using ";
        r3.<init>(r4);	 Catch:{ all -> 0x0117 }
        r4 = r6.a();	 Catch:{ all -> 0x0117 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0117 }
        r3 = r3.toString();	 Catch:{ all -> 0x0117 }
        com.vungle.log.Logger.w(r2, r3, r1);	 Catch:{ all -> 0x0117 }
        r6.g();
        goto L_0x002a;
    L_0x0117:
        r0 = move-exception;
        r6.g();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.location.BaseGoogleDetailedLocationProvider.b():android.location.Location");
    }

    private boolean e() {
        boolean z;
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean z2 = false;
        Object obj = null;
        Object obj2;
        try {
            synchronized (this.c) {
                try {
                    obj = this.a;
                    boolean e = e(obj);
                    if (e) {
                        Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " already connected to " + a() + " " + obj);
                        obj2 = obj;
                        z = e;
                    } else {
                        if (obj == null) {
                            obj2 = c();
                            this.a = obj2;
                            try {
                                this.d = false;
                                b(obj2);
                                obj = obj2;
                            } catch (Throwable th4) {
                                th = th4;
                                obj = obj2;
                                z2 = e;
                                try {
                                    throw th;
                                } catch (Throwable th5) {
                                    th2 = th5;
                                    z = z2;
                                    obj2 = obj;
                                    th3 = th2;
                                }
                            }
                        }
                        while (!this.d) {
                            try {
                                Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " waiting for " + a() + " to connect " + obj);
                                this.c.wait();
                            } catch (InterruptedException e2) {
                                Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " interrupted while waiting for " + a() + " to connect " + obj);
                            } catch (Throwable th6) {
                                th5 = th6;
                                z2 = e;
                            }
                        }
                        z = e(obj);
                        obj2 = obj;
                    }
                    if (z) {
                        try {
                            this.b++;
                        } catch (Throwable th32) {
                            th2 = th32;
                            obj = obj2;
                            z2 = z;
                            th5 = th2;
                            throw th5;
                        }
                    }
                    return z;
                } catch (Throwable th7) {
                    th5 = th7;
                    throw th5;
                }
            }
        } catch (Throwable th52) {
            th2 = th52;
            z = false;
            obj2 = null;
            th32 = th2;
            Logger.w(Logger.LOCATION_TAG, Thread.currentThread().getName() + " error connecting to " + a() + " " + obj2, th32);
            return z;
        }
    }

    private boolean e(T t) {
        return t != null && a(t);
    }

    protected final void d() {
        Logger.d(Logger.LOCATION_TAG, "connected to " + a() + " " + this.a);
        f();
    }

    protected void onConnectionFailed(ConnectionResult connectionResult) {
        Logger.i(Logger.LOCATION_TAG, "failed to connect " + a() + " " + this.a + "; connection result " + connectionResult);
        f();
    }

    private void f() {
        synchronized (this.c) {
            this.d = true;
            this.c.notifyAll();
        }
    }

    private void g() {
        synchronized (this.c) {
            int i = this.b - 1;
            this.b = i;
            if (i > 0) {
                Logger.v(Logger.LOCATION_TAG, Thread.currentThread().getName() + " not disconnecting from " + a() + " because " + i + " clients still connected " + this.a);
            } else {
                Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " disconnecting from " + a() + " " + this.a);
                Object obj = this.a;
                if (e(obj)) {
                    d(obj);
                    this.a = null;
                }
            }
        }
    }
}
