package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.g;
import org.json.simple.parser.Yytoken;

public abstract class al extends RelativeLayout {
    private static final String b;
    protected ah a;
    private am c;
    private a d;

    class 1 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ al b;

        1(al alVar, boolean z) {
            this.b = alVar;
            this.a = z;
        }

        public void run() {
            if (!this.a) {
                this.b.setVisibility(8);
                this.b.clearAnimation();
            }
            this.b.a.i.remove(Integer.valueOf(hashCode()));
        }
    }

    static /* synthetic */ class 2 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum a {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    protected abstract View a();

    protected abstract int b();

    static {
        b = al.class.getSimpleName();
    }

    public al(Context context, ah ahVar) {
        super(context);
        this.a = ahVar;
        this.d = a.BOTTOM;
        a(context);
    }

    public void a(a aVar) {
        if (aVar == null) {
            CBLogging.b(b, "Side object cannot be null");
            return;
        }
        this.d = aVar;
        LayoutParams layoutParams = null;
        setClickable(false);
        int b = b();
        switch (2.a[this.d.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(b, getContext()));
                layoutParams.addRule(10);
                this.c.b(1);
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(b, getContext()));
                layoutParams.addRule(12);
                this.c.b(4);
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(b, getContext()), -1);
                layoutParams.addRule(9);
                this.c.b(8);
                break;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(b, getContext()), -1);
                layoutParams.addRule(11);
                this.c.b(2);
                break;
        }
        setLayoutParams(layoutParams);
    }

    private void a(Context context) {
        Context context2 = getContext();
        setGravity(17);
        this.c = new am(context2);
        this.c.a(-1);
        this.c.setBackgroundColor(-855638017);
        addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
        addView(a(), new RelativeLayout.LayoutParams(-1, -1));
    }

    public void a(boolean z) {
        a(z, 500);
    }

    private void a(boolean z, long j) {
        this.a.A = z;
        if (!z || getVisibility() != 0) {
            if (z || getVisibility() != 8) {
                Animation translateAnimation;
                Runnable 1 = new 1(this, z);
                if (z) {
                    setVisibility(0);
                }
                float a = CBUtility.a((float) b(), getContext());
                float f;
                switch (2.a[this.d.ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, z ? 0.0f : -a);
                        break;
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                        if (z) {
                            f = a;
                        } else {
                            f = 0.0f;
                        }
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, a);
                        break;
                    case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, z ? 0.0f : -a, 0.0f, 0.0f);
                        break;
                    case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        f = z ? a : 0.0f;
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, a, 0.0f, 0.0f);
                        break;
                    default:
                        translateAnimation = null;
                        break;
                }
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(!z);
                startAnimation(translateAnimation);
                this.a.i.put(Integer.valueOf(hashCode()), 1);
                g.a.postDelayed(1, j);
            }
        }
    }
}
