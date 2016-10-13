package com.chartboost.sdk.impl;

import android.os.Process;
import com.chartboost.sdk.impl.b.a;
import java.util.concurrent.BlockingQueue;

public class c extends Thread {
    private static final boolean a;
    private final BlockingQueue<l<?>> b;
    private final BlockingQueue<l<?>> c;
    private final b d;
    private final o e;
    private volatile boolean f;

    class 1 implements Runnable {
        final /* synthetic */ l a;
        final /* synthetic */ c b;

        1(c cVar, l lVar) {
            this.b = cVar;
            this.a = lVar;
        }

        public void run() {
            try {
                this.b.c.put(this.a);
            } catch (InterruptedException e) {
            }
        }
    }

    static {
        a = t.b;
    }

    public c(BlockingQueue<l<?>> blockingQueue, BlockingQueue<l<?>> blockingQueue2, b bVar, o oVar) {
        this.f = false;
        this.b = blockingQueue;
        this.c = blockingQueue2;
        this.d = bVar;
        this.e = oVar;
    }

    public void a() {
        this.f = true;
        interrupt();
    }

    public void run() {
        if (a) {
            t.a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.d.a();
        while (true) {
            try {
                l lVar = (l) this.b.take();
                lVar.a("cache-queue-take");
                if (lVar.h()) {
                    lVar.b("cache-discard-canceled");
                } else {
                    a a = this.d.a(lVar.e());
                    if (a == null) {
                        lVar.a("cache-miss");
                        this.c.put(lVar);
                    } else if (a.a()) {
                        lVar.a("cache-hit-expired");
                        lVar.a(a);
                        this.c.put(lVar);
                    } else {
                        lVar.a("cache-hit");
                        n a2 = lVar.a(new i(a.a, a.g));
                        lVar.a("cache-hit-parsed");
                        if (a.b()) {
                            lVar.a("cache-hit-refresh-needed");
                            lVar.a(a);
                            a2.d = true;
                            this.e.a(lVar, a2, new 1(this, lVar));
                        } else {
                            this.e.a(lVar, a2);
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.f) {
                    return;
                }
            }
        }
    }
}
