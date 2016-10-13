package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class cv {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final ScheduledExecutorService c;
    private final ScheduledExecutorService d;
    private final ScheduledExecutorService e;

    cv(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.c = a("main");
        this.d = a("back");
        this.e = a("postbacks");
        this.b = appLovinSdkImpl.getLogger();
    }

    private static void a(Runnable runnable, long j, ScheduledExecutorService scheduledExecutorService) {
        if (j > 0) {
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    ScheduledExecutorService a(String str) {
        return Executors.newScheduledThreadPool(1, new cx(this, str));
    }

    void a(ca caVar, cw cwVar) {
        a(caVar, cwVar, 0);
    }

    void a(ca caVar, cw cwVar, long j) {
        if (caVar == null) {
            throw new IllegalArgumentException("No task specified");
        } else if (j < 0) {
            throw new IllegalArgumentException("Invalid delay specified: " + j);
        } else {
            this.b.d(caVar.e, "Scheduling " + caVar.e + " on " + cwVar + " queue in " + j + "ms.");
            Runnable czVar = new cz(this, caVar, cwVar);
            if (cwVar == cw.MAIN) {
                a(czVar, j, this.c);
            } else if (cwVar == cw.BACKGROUND) {
                a(czVar, j, this.d);
            } else if (cwVar == cw.POSTBACKS) {
                a(czVar, j, this.e);
            }
        }
    }

    void a(cu cuVar, long j) {
        if (cuVar == null) {
            throw new IllegalArgumentException("No task specified");
        }
        a((Runnable) cuVar, j, this.c);
    }
}
