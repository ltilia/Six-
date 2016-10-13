package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.util.g;

public abstract class b {
    protected final c a;
    private final Context b;
    private boolean c;

    public b(Context context, c cVar) {
        this.b = context;
        this.a = cVar;
    }

    public final void a() {
        if (!this.c) {
            if (this.a != null) {
                this.a.d();
            }
            b();
            this.c = true;
            g.a(this.b, "Impression logged");
            if (this.a != null) {
                this.a.e();
            }
        }
    }

    protected abstract void b();
}
