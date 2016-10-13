package com.chartboost.sdk.impl;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.f;
import com.google.android.exoplayer.DefaultLoadControl;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public final class bg {

    public interface a {
        void a(com.chartboost.sdk.Model.a aVar);
    }

    static class 1 implements OnGlobalLayoutListener {
        final /* synthetic */ View a;
        final /* synthetic */ b b;
        final /* synthetic */ com.chartboost.sdk.Model.a c;
        final /* synthetic */ a d;
        final /* synthetic */ boolean e;

        1(View view, b bVar, com.chartboost.sdk.Model.a aVar, a aVar2, boolean z) {
            this.a = view;
            this.b = bVar;
            this.c = aVar;
            this.d = aVar2;
            this.e = z;
        }

        public void onGlobalLayout() {
            this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            bg.c(this.b, this.c, this.d, this.e);
        }
    }

    static class 2 implements Runnable {
        final /* synthetic */ a a;
        final /* synthetic */ com.chartboost.sdk.Model.a b;

        2(a aVar, com.chartboost.sdk.Model.a aVar2) {
            this.a = aVar;
            this.b = aVar2;
        }

        public void run() {
            this.a.a(this.b);
        }
    }

    static /* synthetic */ class 3 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.values().length];
            try {
                a[b.CBAnimationTypeFade.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.CBAnimationTypePerspectiveZoom.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.CBAnimationTypePerspectiveRotate.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[b.CBAnimationTypeSlideFromBottom.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[b.CBAnimationTypeSlideFromTop.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[b.CBAnimationTypeSlideFromLeft.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[b.CBAnimationTypeSlideFromRight.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[b.CBAnimationTypeBounce.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public enum b {
        CBAnimationTypePerspectiveRotate,
        CBAnimationTypeBounce,
        CBAnimationTypePerspectiveZoom,
        CBAnimationTypeSlideFromTop,
        CBAnimationTypeSlideFromBottom,
        CBAnimationTypeFade,
        CBAnimationTypeNone,
        CBAnimationTypeSlideFromLeft,
        CBAnimationTypeSlideFromRight;

        public static b a(int i) {
            if (i != 0 && i > 0 && i <= values().length) {
                return values()[i - 1];
            }
            return null;
        }
    }

    public static void a(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2) {
        b(bVar, aVar, aVar2, true);
    }

    public static void b(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2) {
        c(bVar, aVar, aVar2, false);
    }

    private static void b(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2, boolean z) {
        if (bVar == b.CBAnimationTypeNone) {
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
        } else if (aVar == null || aVar.l == null) {
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
        } else {
            View f = aVar.l.f();
            if (f == null) {
                f.a().d(aVar);
                CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
                return;
            }
            ViewTreeObserver viewTreeObserver = f.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new 1(f, bVar, aVar, aVar2, z));
            }
        }
    }

    private static void c(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2, boolean z) {
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        if (aVar == null || aVar.l == null) {
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
            if (aVar2 != null) {
                aVar2.a(aVar);
                return;
            }
            return;
        }
        View f = aVar.l.f();
        if (f == null) {
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
            return;
        }
        View view;
        long j;
        Animation alphaAnimation;
        if (aVar.f == c.INTERSTITIAL_REWARD_VIDEO || aVar.f == c.INTERSTITIAL_VIDEO) {
            view = aVar.l;
        } else {
            view = f;
        }
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        float f2 = (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - 0.4f) / 2.0f;
        if (aVar.a == com.chartboost.sdk.Model.a.b.WEB) {
            j = 500;
        } else {
            j = 500;
        }
        float f3;
        float f4;
        Animation translateAnimation;
        switch (3.a[bVar.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                if (z) {
                    alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                Animation animationSet2 = new AnimationSet(true);
                animationSet2.addAnimation(alphaAnimation);
                alphaAnimation = animationSet2;
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                if (z) {
                    alphaAnimation = new bl(-1114636288, 0.0f, width / 2.0f, height / 2.0f, false);
                } else {
                    alphaAnimation = new bl(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new TranslateAnimation(width * f2, 0.0f, (-height) * 0.4f, 0.0f);
                } else {
                    alphaAnimation = new TranslateAnimation(0.0f, width * f2, 0.0f, height);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                if (z) {
                    alphaAnimation = new bl(-1114636288, 0.0f, width / 2.0f, height / 2.0f, true);
                } else {
                    alphaAnimation = new bl(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f2, 0.0f);
                } else {
                    alphaAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f2);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                f3 = z ? height : 0.0f;
                if (z) {
                    f4 = 0.0f;
                } else {
                    f4 = height;
                }
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, f3, f4);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case Yytoken.TYPE_COMMA /*5*/:
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, z ? -height : 0.0f, z ? 0.0f : -height);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case Yytoken.TYPE_COLON /*6*/:
                f3 = z ? width : 0.0f;
                if (z) {
                    f4 = 0.0f;
                } else {
                    f4 = width;
                }
                translateAnimation = new TranslateAnimation(f3, f4, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                translateAnimation = new TranslateAnimation(z ? -width : 0.0f, z ? 0.0f : -width, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                if (!z) {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, 1, 0.5f, 1, 0.5f);
                    alphaAnimation.setDuration(j);
                    alphaAnimation.setStartOffset(0);
                    alphaAnimation.setFillAfter(true);
                    animationSet.addAnimation(alphaAnimation);
                    alphaAnimation = animationSet;
                    break;
                }
                alphaAnimation = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.6f));
                alphaAnimation.setStartOffset(0);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.81818175f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.81818175f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.19999999f));
                alphaAnimation.setStartOffset((long) Math.round(((float) j) * 0.6f));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1111112f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1111112f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.099999964f));
                alphaAnimation.setStartOffset((long) Math.round(((float) j) * DefaultLoadControl.DEFAULT_HIGH_BUFFER_LOAD));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            default:
                alphaAnimation = animationSet;
                break;
        }
        if (bVar != b.CBAnimationTypeNone) {
            if (aVar2 != null) {
                CBUtility.e().postDelayed(new 2(aVar2, aVar), j);
            }
            view.startAnimation(alphaAnimation);
        } else if (aVar2 != null) {
            aVar2.a(aVar);
        }
    }

    public static void a(boolean z, View view) {
        if (com.chartboost.sdk.c.H().booleanValue()) {
            a(z, view, 500);
        } else {
            a(z, view, 500);
        }
    }

    public static void a(boolean z, View view, long j) {
        float f;
        float f2 = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        view.clearAnimation();
        if (z) {
            view.setVisibility(0);
        }
        if (z) {
            f = 0.0f;
        } else {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        if (!z) {
            f2 = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(j);
        alphaAnimation.setFillBefore(true);
        view.startAnimation(alphaAnimation);
    }
}
