package com.applovin.impl.sdk;

class cz implements Runnable {
    final /* synthetic */ cv a;
    private final String b;
    private final ca c;
    private final cw d;

    cz(cv cvVar, ca caVar, cw cwVar) {
        this.a = cvVar;
        this.b = caVar.a();
        this.c = caVar;
        this.d = cwVar;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            n.a();
            if (this.a.a.e()) {
                this.a.b.i(this.b, "Task re-scheduled...");
                this.a.a(this.c, this.d, 2000);
            } else if (this.a.a.isEnabled()) {
                this.a.b.i(this.b, "Task started execution...");
                this.c.run();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (this.c instanceof dl) {
                    dl dlVar = (dl) this.c;
                    dg.a().a(dlVar.e(), currentTimeMillis2, q.a(this.a.a), dlVar.f());
                }
                this.a.b.i(this.b, "Task executed successfully in " + currentTimeMillis2 + "ms.");
                cg b = this.a.a.b();
                b.a(this.b + "_count");
                b.a(this.b + "_time", currentTimeMillis2);
            } else {
                if (this.a.a.f()) {
                    this.a.a.g();
                } else {
                    this.a.b.w(this.b, "Task not executed, SDK is disabled");
                }
                this.c.b();
            }
        } catch (Throwable th) {
            this.a.b.e(this.b, "Task failed execution in " + (System.currentTimeMillis() - currentTimeMillis) + "ms.", th);
        }
    }
}
