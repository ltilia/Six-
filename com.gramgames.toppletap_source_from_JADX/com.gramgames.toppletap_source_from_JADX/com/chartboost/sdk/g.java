package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.bg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class g {
    public static Handler a;
    public boolean b;
    protected com.chartboost.sdk.Libraries.e.a c;
    protected List<b> d;
    protected List<b> e;
    protected com.chartboost.sdk.Libraries.e.a f;
    protected com.chartboost.sdk.Model.a g;
    protected f h;
    public Map<Integer, Runnable> i;
    protected boolean j;
    protected boolean k;
    private boolean l;
    private a m;

    public interface b {
        boolean a();
    }

    class 1 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;
        final /* synthetic */ g c;

        1(g gVar, boolean z, View view) {
            this.c = gVar;
            this.a = z;
            this.b = view;
        }

        public void run() {
            if (!this.a) {
                this.b.setVisibility(8);
                this.b.setClickable(false);
            }
            this.c.i.remove(Integer.valueOf(this.b.hashCode()));
        }
    }

    public abstract class a extends RelativeLayout {
        final /* synthetic */ g a;
        private boolean b;
        private int c;
        private int d;
        private int e;
        private int f;
        private f g;

        class 1 implements Runnable {
            final /* synthetic */ a a;

            1(a aVar) {
                this.a = aVar;
            }

            public void run() {
                this.a.requestLayout();
            }
        }

        protected abstract void a(int i, int i2);

        public a(g gVar, Context context) {
            this.a = gVar;
            super(context);
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = null;
            gVar.m = this;
            gVar.l = false;
            setFocusableInTouchMode(true);
            requestFocus();
        }

        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            this.e = w;
            this.f = h;
            if (this.c != -1 && this.d != -1 && this.a.g != null && this.a.g.a == com.chartboost.sdk.Model.a.b.NATIVE) {
                a();
            }
        }

        private boolean b(int i, int i2) {
            boolean z = true;
            if (this.a.g != null && this.a.g.a == com.chartboost.sdk.Model.a.b.WEB) {
                return true;
            }
            if (this.b) {
                return false;
            }
            f c = CBUtility.c();
            if (this.c == i && this.d == i2 && this.g == c) {
                return true;
            }
            this.b = true;
            try {
                if (this.a.j && c.a()) {
                    this.a.h = c;
                } else if (this.a.k && c.b()) {
                    this.a.h = c;
                }
                a(i, i2);
                post(new 1(this));
                this.c = i;
                this.d = i2;
                this.g = c;
            } catch (Throwable e) {
                CBLogging.b("CBViewProtocol", "Exception raised while layouting Subviews", e);
                z = false;
            }
            this.b = false;
            return z;
        }

        public final void a() {
            a(false);
        }

        public final void a(boolean z) {
            if (z) {
                this.g = null;
            }
            a((Activity) getContext());
        }

        public void b() {
        }

        public boolean a(Activity activity) {
            if (this.e == -1 || this.f == -1) {
                int width;
                int height;
                try {
                    width = getWidth();
                    height = getHeight();
                    if (width == 0 || height == 0) {
                        View findViewById = activity.getWindow().findViewById(16908290);
                        if (findViewById == null) {
                            findViewById = activity.getWindow().getDecorView();
                        }
                        width = findViewById.getWidth();
                        height = findViewById.getHeight();
                    }
                } catch (Exception e) {
                    height = 0;
                    width = 0;
                }
                if (width == 0 || r0 == 0) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    width = displayMetrics.widthPixels;
                    height = displayMetrics.heightPixels;
                }
                this.e = width;
                this.f = height;
            }
            return b(this.e, this.f);
        }

        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            for (int i = 0; i < this.a.i.size(); i++) {
                g.a.removeCallbacks((Runnable) this.a.i.get(Integer.valueOf(i)));
            }
            this.a.i.clear();
        }

        public final void a(View view) {
            int i = 200;
            if (200 == getId()) {
                i = 201;
            }
            int i2 = i;
            View findViewById = findViewById(i);
            while (findViewById != null) {
                i2++;
                findViewById = findViewById(i2);
            }
            view.setId(i2);
            view.setSaveEnabled(false);
        }

        protected boolean c() {
            return g.a(getContext());
        }
    }

    protected abstract a b(Context context);

    static {
        a = CBUtility.e();
    }

    public static boolean a(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 4;
    }

    public g(com.chartboost.sdk.Model.a aVar) {
        this.b = false;
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.i = Collections.synchronizedMap(new HashMap());
        this.j = true;
        this.k = true;
        this.g = aVar;
        this.m = null;
        this.h = CBUtility.c();
        this.l = false;
    }

    public f a() {
        return this.h;
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        this.f = aVar.a("assets");
        if (!this.f.b()) {
            return true;
        }
        CBLogging.b("CBViewProtocol", "Media got from the response is null or empty");
        a(CBImpressionError.INVALID_RESPONSE);
        return false;
    }

    public void a(b bVar) {
        if (bVar.a()) {
            this.e.remove(bVar);
        }
        this.d.remove(bVar);
        if (this.d.isEmpty() && !b()) {
            CBLogging.b("CBViewProtocol", "Error while downloading the assets");
            a(CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        }
    }

    public boolean b() {
        if (this.e.isEmpty()) {
            i();
            return true;
        }
        CBLogging.d("CBViewProtocol", "not completed loading assets for impression");
        return false;
    }

    public CBImpressionError c() {
        Activity f = Chartboost.f();
        if (f == null) {
            this.m = null;
            return CBImpressionError.NO_HOST_ACTIVITY;
        } else if (!this.k && !this.j) {
            return CBImpressionError.WRONG_ORIENTATION;
        } else {
            if (this.m == null) {
                this.m = b((Context) f);
            }
            if (this.g.a != com.chartboost.sdk.Model.a.b.NATIVE || this.m.a(f)) {
                return null;
            }
            this.m = null;
            return CBImpressionError.ERROR_CREATING_VIEW;
        }
    }

    public void d() {
        f();
        for (int i = 0; i < this.i.size(); i++) {
            a.removeCallbacks((Runnable) this.i.get(Integer.valueOf(i)));
        }
        this.i.clear();
    }

    public a e() {
        return this.m;
    }

    public void f() {
        if (this.m != null) {
            this.m.b();
        }
        this.m = null;
    }

    public com.chartboost.sdk.Libraries.e.a g() {
        return this.f;
    }

    public void b(b bVar) {
        this.d.add(bVar);
        this.e.add(bVar);
    }

    protected void a(CBImpressionError cBImpressionError) {
        this.g.a(cBImpressionError);
    }

    protected void h() {
        if (!this.l) {
            this.l = true;
            this.g.c();
        }
    }

    protected void i() {
        this.g.d();
    }

    public boolean a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        return this.g.a(str, aVar);
    }

    public void a(boolean z, View view) {
        a(z, view, true);
    }

    public void a(boolean z, View view, boolean z2) {
        int i = 8;
        if (((z && view.getVisibility() == 0) || (!z && view.getVisibility() == 8)) && this.i.get(Integer.valueOf(view.hashCode())) == null) {
            return;
        }
        if (z2) {
            Runnable 1 = new 1(this, z, view);
            if (this.g.a == com.chartboost.sdk.Model.a.b.WEB) {
                bg.a(z, view, 500);
                a(view, 1, 500);
            } else {
                bg.a(z, view, 500);
                a(view, 1, 500);
            }
            return;
        }
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        view.setClickable(z);
    }

    protected void a(View view, Runnable runnable, long j) {
        Runnable runnable2 = (Runnable) this.i.get(Integer.valueOf(view.hashCode()));
        if (runnable2 != null) {
            a.removeCallbacks(runnable2);
        }
        this.i.put(Integer.valueOf(view.hashCode()), runnable);
        a.postDelayed(runnable, j);
    }

    public static int a(String str) {
        int i = 0;
        if (str != null) {
            if (!str.startsWith("#")) {
                try {
                    i = Color.parseColor(str);
                } catch (IllegalArgumentException e) {
                    str = "#" + str;
                }
            }
            if (str.length() == 4 || str.length() == 5) {
                StringBuilder stringBuilder = new StringBuilder((str.length() * 2) + 1);
                stringBuilder.append("#");
                for (int i2 = i; i2 < str.length() - 1; i2++) {
                    stringBuilder.append(str.charAt(i2 + 1));
                    stringBuilder.append(str.charAt(i2 + 1));
                }
                str = stringBuilder.toString();
            }
            try {
                i = Color.parseColor(str);
            } catch (Throwable e2) {
                CBLogging.d("CBViewProtocol", "error parsing color " + str, e2);
            }
        }
        return i;
    }

    public float j() {
        return 0.0f;
    }

    public float k() {
        return 0.0f;
    }

    public boolean l() {
        return false;
    }

    public void m() {
        if (this.b) {
            this.b = false;
        }
        if (e() != null && CBUtility.c() != e().g) {
            e().a(false);
        }
    }

    public void n() {
        this.b = true;
    }
}
