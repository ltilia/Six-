package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.util.o;
import com.facebook.ads.internal.util.s;
import com.facebook.ads.internal.view.d;
import java.util.Collections;
import java.util.Map;

public class m extends b {
    private static final String b;
    private final d c;
    private l d;
    private boolean e;

    class 1 implements Runnable {
        final /* synthetic */ m a;

        1(m mVar) {
            this.a = mVar;
        }

        public void run() {
            if (this.a.c.b()) {
                Log.w(m.b, "Webview already destroyed, cannot activate");
            } else {
                this.a.c.loadUrl("javascript:" + this.a.d.e());
            }
        }
    }

    static {
        b = m.class.getSimpleName();
    }

    public m(Context context, d dVar, c cVar) {
        super(context, cVar);
        this.c = dVar;
    }

    private void a(Map<String, String> map) {
        if (this.d != null) {
            if (!s.a(this.d.f())) {
                new o(map).execute(new String[]{r0});
            }
        }
    }

    public void a(l lVar) {
        this.d = lVar;
    }

    protected void b() {
        if (this.d != null) {
            if (!(this.c == null || s.a(this.d.g()))) {
                if (this.c.b()) {
                    Log.w(b, "Webview already destroyed, cannot send impression");
                } else {
                    this.c.loadUrl("javascript:" + this.d.g());
                }
            }
            a(Collections.singletonMap("evt", "native_imp"));
        }
    }

    public synchronized void c() {
        if (!(this.e || this.d == null)) {
            this.e = true;
            if (!(this.c == null || s.a(this.d.e()))) {
                this.c.post(new 1(this));
            }
        }
    }

    public void d() {
        a(Collections.singletonMap("evt", "interstitial_displayed"));
    }
}
