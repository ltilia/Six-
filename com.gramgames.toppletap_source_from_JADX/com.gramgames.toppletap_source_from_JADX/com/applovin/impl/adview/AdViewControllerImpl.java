package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.widget.RelativeLayout.LayoutParams;
import com.applovin.adview.AdViewController;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.ClickTrackingOverlayView;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.cf;
import com.applovin.impl.sdk.ch;
import com.applovin.impl.sdk.dm;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

public class AdViewControllerImpl implements AdViewController {
    private Activity a;
    private AppLovinSdk b;
    private AppLovinAdService c;
    private AppLovinLogger d;
    private AppLovinAdSize e;
    private String f;
    private r g;
    private l h;
    private o i;
    private AppLovinAd j;
    private Runnable k;
    private Runnable l;
    private Runnable m;
    private volatile AppLovinAd n;
    private ClickTrackingOverlayView o;
    private WeakReference p;
    private final AtomicReference q;
    private volatile boolean r;
    private volatile boolean s;
    private volatile boolean t;
    private volatile boolean u;
    private volatile AppLovinAdLoadListener v;
    private volatile AppLovinAdDisplayListener w;
    private volatile AppLovinAdVideoPlaybackListener x;
    private volatile AppLovinAdClickListener y;
    private volatile boolean z;

    public AdViewControllerImpl() {
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = new AtomicReference();
        this.r = false;
        this.s = true;
        this.t = false;
        this.u = false;
    }

    private void a(ViewGroup viewGroup, AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Context context) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (context instanceof Activity) {
            this.b = appLovinSdk;
            this.c = appLovinSdk.getAdService();
            this.d = appLovinSdk.getLogger();
            this.e = appLovinAdSize;
            this.a = (Activity) context;
            this.j = dm.a();
            this.g = new r(this, appLovinSdk);
            this.m = new f();
            this.k = new k();
            this.l = new i();
            this.h = new l(this, appLovinSdk);
            if (a(context)) {
                this.i = b();
                viewGroup.setBackgroundColor(0);
                viewGroup.addView(this.i);
                b(this.i, appLovinAdSize);
                a(this.m);
                a(new j());
                this.r = true;
                return;
            }
            this.d.userError("AppLovinAdView", "Web view database is corrupt, AdView not loaded");
        } else {
            throw new IllegalArgumentException("Specified context is not an activity");
        }
    }

    private void a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        if (this.o == null) {
            this.d.d("AppLovinAdView", "Creating and rendering click overlay");
            this.o = new ClickTrackingOverlayView(appLovinAdView.getContext(), this.b);
            this.o.setLayoutParams(new LayoutParams(-1, -1));
            appLovinAdView.addView(this.o);
            appLovinAdView.bringChildToFront(this.o);
            ((AppLovinAdServiceImpl) this.c).trackAndLaunchForegroundClick(appLovinAd, this.f, appLovinAdView, this, uri);
            return;
        }
        this.d.d("AppLovinAdView", "Skipping click overlay rendering because it already exists");
    }

    private void a(Runnable runnable) {
        this.a.runOnUiThread(runnable);
    }

    private static boolean a(Context context) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return true;
            }
            WebViewDatabase instance = WebViewDatabase.getInstance(context);
            Method declaredMethod = WebViewDatabase.class.getDeclaredMethod("getCacheTotalSize", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(instance, new Object[0]);
            return true;
        } catch (Throwable e) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e);
            return true;
        } catch (Throwable e2) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e2);
            return true;
        } catch (Throwable e22) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e22);
            return true;
        } catch (Throwable e3) {
            Log.e("AppLovinAdView", "getCacheTotalSize() reported exception", e3);
            return false;
        } catch (Throwable e32) {
            Log.e("AppLovinAdView", "Unexpected error while checking DB state", e32);
            return false;
        }
    }

    private o b() {
        o oVar = new o(this.g, this.b, this.a);
        oVar.setBackgroundColor(0);
        oVar.setWillNotCacheDrawing(false);
        if (new cf(this.b).F() && VERSION.SDK_INT >= 19) {
            oVar.setLayerType(2, null);
        }
        return oVar;
    }

    private static void b(View view, AppLovinAdSize appLovinAdSize) {
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        int applyDimension = appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel()) ? -1 : appLovinAdSize.getWidth() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getWidth(), displayMetrics);
        int applyDimension2 = appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel()) ? -1 : appLovinAdSize.getHeight() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getHeight(), displayMetrics);
        ViewGroup.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-2, -2);
        }
        layoutParams.width = applyDimension;
        layoutParams.height = applyDimension2;
        if (layoutParams instanceof LayoutParams) {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        }
        view.setLayoutParams(layoutParams);
    }

    void a() {
        this.d.d("AppLovinAdView", "Ad: " + this.n + " with placement = \"" + this.f + "\" closed.");
        a(this.m);
        a(new h(this, this.n));
        this.n = null;
        this.f = null;
    }

    void a(int i) {
        if (!this.t) {
            this.c.addAdUpdateListener(this.h, this.e);
            a(this.m);
        }
        a(new b(this, i));
    }

    void a(AppLovinAd appLovinAd) {
        if (appLovinAd != null) {
            this.u = true;
            if (this.t) {
                this.q.set(appLovinAd);
                this.d.d("AppLovinAdView", "Ad view has paused when an ad was recieved, ad saved for later");
            } else {
                this.c.addAdUpdateListener(this.h, this.e);
                renderAd(appLovinAd);
            }
            a(new a(this, appLovinAd));
            return;
        }
        this.d.e("AppLovinAdView", "No provided when to the view controller");
        a(-1);
    }

    void a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        AppLovinAdServiceImpl appLovinAdServiceImpl = (AppLovinAdServiceImpl) this.c;
        if (!new cf(this.b).J() || uri == null) {
            appLovinAdServiceImpl.trackAndLaunchClick(appLovinAd, this.f, appLovinAdView, this, uri);
        } else {
            a(appLovinAd, appLovinAdView, uri);
        }
        a(new g(this, appLovinAd));
    }

    public void destroy() {
        if (this.c != null) {
            this.c.removeAdUpdateListener(this.h, getSize());
        }
        if (this.i != null) {
            try {
                ViewParent parent = this.i.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.i);
                }
                this.i.removeAllViews();
                this.i.destroy();
                this.i = null;
            } catch (Throwable th) {
                this.d.w("AppLovinAdView", "Unable to destroy ad view", th);
            }
        }
        this.t = true;
    }

    public void dismissInterstitialIfRequired() {
        if (!new cf(this.b).N()) {
            return;
        }
        if (this.a != null && (this.a instanceof AppLovinInterstitialActivity)) {
            ((AppLovinInterstitialActivity) this.a).dismiss();
        } else if (this.p != null) {
            x xVar = (x) this.p.get();
            if (xVar != null) {
                xVar.dismiss();
            }
        }
    }

    public AppLovinAdSize getSize() {
        return this.e;
    }

    public void initializeAdView(ViewGroup viewGroup, Context context, AppLovinAdSize appLovinAdSize, AppLovinSdk appLovinSdk, AttributeSet attributeSet) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (context == null) {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to build AppLovinAdView: no context provided. Please use a different constructor for this view.");
        } else {
            if (appLovinAdSize == null) {
                appLovinAdSize = m.a(attributeSet);
                if (appLovinAdSize == null) {
                    appLovinAdSize = AppLovinAdSize.BANNER;
                }
            }
            if (appLovinSdk == null) {
                appLovinSdk = AppLovinSdk.getInstance(context);
            }
            if (appLovinSdk != null && !appLovinSdk.hasCriticalErrors()) {
                a(viewGroup, appLovinSdk, appLovinAdSize, context);
                if (m.b(attributeSet)) {
                    loadNextAd();
                }
            }
        }
    }

    public boolean isAdReadyToDisplay() {
        return this.b.getAdService().hasPreloadedAd(this.e);
    }

    public boolean isAutoDestroy() {
        return this.s;
    }

    public boolean isForegroundClickInvalidated() {
        return this.z;
    }

    public void loadNextAd() {
        if (this.b == null || this.h == null || this.a == null || !this.r) {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to load next ad: AppLovinAdView is not initialized.");
        } else {
            this.c.loadNextAd(this.e, this.h);
        }
    }

    public void onAdHtmlLoaded(WebView webView) {
        if (this.n != null) {
            webView.setVisibility(0);
            try {
                if (this.w != null) {
                    this.w.adDisplayed(this.n);
                }
            } catch (Throwable th) {
                this.d.userError("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    public void onDetachedFromWindow() {
        if (this.r) {
            a(new h(this, this.n));
            if (this.s) {
                destroy();
            }
        }
    }

    public void onVisibilityChanged(int i) {
        if (!this.r || !this.s) {
            return;
        }
        if (i == 8 || i == 4) {
            pause();
        } else if (i == 0) {
            resume();
        }
    }

    public void pause() {
        if (this.r) {
            this.c.removeAdUpdateListener(this.h, getSize());
            AppLovinAd appLovinAd = this.n;
            renderAd(this.j);
            if (appLovinAd != null) {
                this.q.set(appLovinAd);
            }
            this.t = true;
        }
    }

    public void removeClickTrackingOverlay() {
        if (this.o != null) {
            ViewParent parent = this.o.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.o);
                this.o = null;
                return;
            }
            return;
        }
        this.d.d("AppLovinAdView", "Asked to remove an overlay when none existed. Skipping...");
    }

    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, null);
    }

    public void renderAd(AppLovinAd appLovinAd, String str) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (!this.r) {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to render ad: AppLovinAdView is not initialized.");
        } else if (appLovinAd != this.n) {
            this.d.d("AppLovinAdView", "Rendering ad #" + appLovinAd.getAdIdNumber() + " (" + appLovinAd.getSize() + ") over placement: " + str);
            a(new h(this, this.n));
            this.q.set(null);
            this.n = appLovinAd;
            this.f = str;
            if (appLovinAd.getSize() == this.e) {
                a(this.k);
            } else if (appLovinAd.getSize() == AppLovinAdSize.INTERSTITIAL) {
                a(this.m);
                a(this.l);
            }
            new ch(this.b).a();
        } else {
            this.d.w("AppLovinAdView", "Ad #" + appLovinAd.getAdIdNumber() + " is already showing, ignoring");
        }
    }

    public void resume() {
        if (this.r) {
            if (this.u) {
                this.c.addAdUpdateListener(this.h, this.e);
            }
            AppLovinAd appLovinAd = (AppLovinAd) this.q.getAndSet(null);
            if (appLovinAd != null) {
                renderAd(appLovinAd);
            }
            this.t = false;
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.y = appLovinAdClickListener;
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.w = appLovinAdDisplayListener;
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.v = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.x = appLovinAdVideoPlaybackListener;
    }

    public void setAutoDestroy(boolean z) {
        this.s = z;
    }

    public void setIsForegroundClickInvalidated(boolean z) {
        this.z = z;
    }

    public void setParentDialog(WeakReference weakReference) {
        this.p = weakReference;
    }
}
