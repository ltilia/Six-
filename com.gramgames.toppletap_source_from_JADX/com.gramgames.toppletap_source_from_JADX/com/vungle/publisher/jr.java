package com.vungle.publisher;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* compiled from: vungle */
final class jr extends Handler {
    final ju a;
    boolean b;
    private final int c;
    private final jp d;

    jr(jp jpVar, Looper looper) {
        super(looper);
        this.d = jpVar;
        this.c = 10;
        this.a = new ju();
    }

    public final void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                jt a = this.a.a();
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.b = false;
                            this.b = false;
                            return;
                        }
                    }
                }
                this.d.a(a);
            } while (SystemClock.uptimeMillis() - uptimeMillis < ((long) this.c));
            if (sendMessage(obtainMessage())) {
                this.b = true;
                return;
            }
            throw new jq("Could not send handler message");
        } catch (Throwable th) {
            this.b = false;
        }
    }
}
