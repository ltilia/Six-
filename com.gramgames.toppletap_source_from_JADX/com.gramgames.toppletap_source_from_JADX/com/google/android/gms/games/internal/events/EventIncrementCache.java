package com.google.android.gms.games.internal.events;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EventIncrementCache {
    final Object zzaId;
    private Handler zzaIe;
    private boolean zzaIf;
    private HashMap<String, AtomicInteger> zzaIg;
    private int zzaIh;

    class 1 implements Runnable {
        final /* synthetic */ EventIncrementCache zzaIi;

        1(EventIncrementCache eventIncrementCache) {
            this.zzaIi = eventIncrementCache;
        }

        public void run() {
            this.zzaIi.zzxl();
        }
    }

    public EventIncrementCache(Looper looper, int flushIntervalMillis) {
        this.zzaId = new Object();
        this.zzaIe = new Handler(looper);
        this.zzaIg = new HashMap();
        this.zzaIh = flushIntervalMillis;
    }

    private void zzxl() {
        synchronized (this.zzaId) {
            this.zzaIf = false;
            flush();
        }
    }

    public void flush() {
        synchronized (this.zzaId) {
            for (Entry entry : this.zzaIg.entrySet()) {
                zzs((String) entry.getKey(), ((AtomicInteger) entry.getValue()).get());
            }
            this.zzaIg.clear();
        }
    }

    protected abstract void zzs(String str, int i);

    public void zzw(String str, int i) {
        synchronized (this.zzaId) {
            if (!this.zzaIf) {
                this.zzaIf = true;
                this.zzaIe.postDelayed(new 1(this), (long) this.zzaIh);
            }
            AtomicInteger atomicInteger = (AtomicInteger) this.zzaIg.get(str);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                this.zzaIg.put(str, atomicInteger);
            }
            atomicInteger.addAndGet(i);
        }
    }
}
