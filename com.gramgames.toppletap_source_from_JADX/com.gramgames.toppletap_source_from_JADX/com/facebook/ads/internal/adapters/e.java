package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.u;

public class e {
    private int a;
    private int b;
    private final Context c;
    private final View d;
    private final int e;
    private final a f;
    private final Handler g;
    private final Runnable h;
    private final boolean i;
    private volatile boolean j;

    public static abstract class a {
        public abstract void a();

        public void b() {
        }
    }

    private static final class b extends u<e> {
        public b(e eVar) {
            super(eVar);
        }

        public void run() {
            e eVar = (e) a();
            if (eVar != null) {
                if (eVar.i || !eVar.j) {
                    View c = eVar.d;
                    a d = eVar.f;
                    if (c != null && d != null) {
                        if (g.a(eVar.c, c, eVar.e)) {
                            d.a();
                            eVar.j = true;
                            return;
                        }
                        d.b();
                        eVar.g.postDelayed(eVar.h, (long) eVar.b);
                    }
                }
            }
        }
    }

    public e(Context context, View view, int i, a aVar) {
        this(context, view, i, false, aVar);
    }

    public e(Context context, View view, int i, boolean z, a aVar) {
        this.a = 0;
        this.b = AdError.NETWORK_ERROR_CODE;
        this.g = new Handler();
        this.h = new b(this);
        this.c = context;
        this.d = view;
        this.e = i;
        this.f = aVar;
        this.i = z;
    }

    public void a() {
        if (!this.i && !this.j) {
            this.g.postDelayed(this.h, (long) this.a);
        }
    }

    public void a(int i) {
        this.a = i;
    }

    public void b() {
        this.g.removeCallbacks(this.h);
    }

    public void b(int i) {
        this.b = i;
    }
}
