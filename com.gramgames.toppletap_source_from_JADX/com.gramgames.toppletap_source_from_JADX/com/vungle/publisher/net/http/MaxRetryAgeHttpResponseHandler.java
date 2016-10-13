package com.vungle.publisher.net.http;

import android.os.SystemClock;
import com.facebook.ads.AdError;
import com.google.android.gms.games.GamesStatusCodes;
import com.vungle.log.Logger;
import com.vungle.publisher.gp;
import com.vungle.publisher.ha;
import com.vungle.publisher.hb;
import com.vungle.publisher.jk;

/* compiled from: vungle */
public abstract class MaxRetryAgeHttpResponseHandler extends gp {
    private int a;
    private int b;
    public int h;
    public int i;
    int j;

    public MaxRetryAgeHttpResponseHandler() {
        this.h = 100;
        this.a = GamesStatusCodes.STATUS_REQUEST_UPDATE_PARTIAL_SUCCESS;
        this.b = 300000;
    }

    protected final void d(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        hb hbVar = httpTransaction.b;
        int i = hbVar.b;
        int i2 = (this.h <= 0 || i < this.h) ? 0 : 1;
        if (i2 == 0) {
            long j = hbVar.a;
            if (this.j <= 0 || SystemClock.elapsedRealtime() - j < ((long) this.j)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i2 == 0) {
                int i3 = httpResponse.b;
                if (gp.a(i3) || i3 == 601) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                if (i2 != 0) {
                    i2 = hbVar.c;
                    if (i3 == 408 || i3 == 603) {
                        i3 = 0;
                    } else {
                        i3 = 1;
                    }
                    if (i3 == 0) {
                        i2 = hbVar.c - 1;
                        hbVar.c = i2;
                        if (i2 < 0) {
                            Logger.d(Logger.NETWORK_TAG, "Attempted to decrement softRetryCount < 0");
                            hbVar.c = 0;
                        }
                        i2 = hbVar.c;
                    }
                    if (this.i <= 0 || r0 < this.i) {
                        i2 = 0;
                    } else {
                        i2 = 1;
                    }
                    if (i2 == 0) {
                        i2 = jk.a(i, this.a, this.b);
                        Logger.d(Logger.NETWORK_TAG, "Retrying " + httpTransaction + " in " + (i2 / AdError.NETWORK_ERROR_CODE) + " seconds");
                        this.f.a(new ha(httpTransaction), httpTransaction.c, (long) i2);
                        return;
                    }
                }
            }
        }
        super.d(httpTransaction, httpResponse);
    }
}
