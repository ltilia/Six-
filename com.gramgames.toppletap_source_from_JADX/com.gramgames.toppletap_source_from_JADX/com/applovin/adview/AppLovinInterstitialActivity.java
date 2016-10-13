package com.applovin.adview;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.impl.adview.AppLovinVideoView;
import com.applovin.impl.adview.ah;
import com.applovin.impl.adview.s;
import com.applovin.impl.adview.u;
import com.applovin.impl.adview.w;
import com.applovin.impl.sdk.AppLovinAdImpl;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.aw;
import com.applovin.impl.sdk.cf;
import com.applovin.impl.sdk.dm;
import com.applovin.impl.sdk.n;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppLovinInterstitialActivity extends Activity implements w {
    public static final String KEY_WRAPPER_ID = "com.applovin.interstitial.wrapper_id";
    public static volatile ah lastKnownWrapper;
    private u A;
    private View B;
    private s C;
    private volatile UUID D;
    private ImageView E;
    private WeakReference F;
    private aw G;
    private AppLovinAdView a;
    private ah b;
    private volatile boolean c;
    private AppLovinLogger d;
    private cf e;
    private AppLovinSdkImpl f;
    private volatile AppLovinAdImpl g;
    private volatile boolean h;
    private volatile boolean i;
    private volatile boolean j;
    private volatile boolean k;
    private volatile boolean l;
    private volatile boolean m;
    private volatile boolean n;
    private volatile boolean o;
    private volatile boolean p;
    private boolean q;
    private volatile boolean r;
    private boolean s;
    private int t;
    private AtomicBoolean u;
    private Handler v;
    private FrameLayout w;
    private AppLovinVideoView x;
    private u y;
    private View z;

    static {
        lastKnownWrapper = null;
    }

    public AppLovinInterstitialActivity() {
        this.c = false;
        this.g = dm.a();
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = 0;
        this.u = new AtomicBoolean(false);
        this.F = new WeakReference(null);
    }

    private void A() {
        SharedPreferences E = E();
        if (this.x != null) {
            int duration = this.x.getDuration();
            int i = E.getInt("com.applovin.interstitial.last_video_position", duration);
            duration -= i;
            r();
            this.x.seekTo(i);
            this.x.start();
            a(duration);
        }
    }

    private void B() {
        if (!this.l) {
            double C = C();
            String parametrizedCompletionUrl = this.g.getParametrizedCompletionUrl((int) C, this.b != null ? this.b.g() : null);
            if (AppLovinSdkUtils.isValidString(parametrizedCompletionUrl)) {
                this.f.getPostbackService().dispatchPostbackAsync(parametrizedCompletionUrl, null);
            } else {
                this.d.e("AppLovinInterstitialActivity", "Received invalid placement aware parameterized video completion url. No postback dispatched.");
            }
            a(this.g, C, D());
        }
    }

    private double C() {
        if (this.m) {
            return 100.0d;
        }
        if (this.x != null) {
            return 100.0d * (((double) this.x.getCurrentPosition()) / ((double) this.x.getDuration()));
        }
        this.d.e("AppLovinInterstitialActivity", "No video view detected on video end");
        return 0.0d;
    }

    private boolean D() {
        return C() >= 95.0d;
    }

    private SharedPreferences E() {
        return getSharedPreferences("com.applovin.interstitial.sharedpreferences", 0);
    }

    private static int a(Display display) {
        return display.getWidth() == display.getHeight() ? 3 : display.getWidth() < display.getHeight() ? 1 : 2;
    }

    private void a(int i) {
        b((int) (((float) i) - dm.a((float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)));
    }

    private void a(int i, UUID uuid) {
        if (this.C != null && uuid.equals(this.D)) {
            if (i <= 0) {
                this.C.setVisibility(8);
                this.s = true;
            } else if (!this.s) {
                int i2 = i - 1;
                this.C.a(i2);
                this.v.postDelayed(new g(this, i2, uuid), 1000);
            }
        }
    }

    private void a(long j, u uVar) {
        this.v.postDelayed(new f(this, uVar), j);
    }

    private void a(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener d = this.b.d();
        if (d != null) {
            d.adDisplayed(appLovinAd);
        }
        this.i = true;
    }

    private void a(AppLovinAd appLovinAd, double d, boolean z) {
        this.l = true;
        AppLovinAdVideoPlaybackListener c = this.b.c();
        if (c != null) {
            c.videoPlaybackEnded(appLovinAd, d, z);
        }
    }

    private void a(File file) {
        Uri fromFile = Uri.fromFile(file);
        this.x = new AppLovinVideoView(this);
        this.x.setOnPreparedListener(new j(this));
        this.x.setOnCompletionListener(new m(this));
        this.x.setOnErrorListener(new n(this));
        this.x.setVideoURI(fromFile);
        this.x.setLayoutParams(new LayoutParams(-1, -1, 17));
        this.x.setOnTouchListener(new AppLovinTouchToClickListener(this, new p(this)));
        this.w.addView(this.x);
        setContentView(this.w);
        o();
    }

    private void a(boolean z) {
        this.r = z;
        MediaPlayer mediaPlayer = (MediaPlayer) this.F.get();
        if (mediaPlayer != null) {
            int i = z ? 0 : 1;
            mediaPlayer.setVolume((float) i, (float) i);
        }
    }

    private boolean a() {
        return (this.b == null || this.e == null || this.e.a()) ? true : (this.e.c() && this.n) ? true : this.e.b() && this.p;
    }

    private void b() {
        if (!D()) {
            if (this.g.isVideoClickableDuringPlayback() && AppLovinSdkUtils.isValidString(this.g.getClickDestinationUrl())) {
                try {
                    this.f.getLogger().d("AppLovinInterstitialActivity", "Clicking through video...");
                    ((AppLovinAdServiceImpl) this.f.getAdService()).trackAndLaunchVideoClick(this.g, this.b.g(), this.a, Uri.parse(this.g.getClickDestinationUrl()));
                    AppLovinAdClickListener e = this.b.e();
                    if (e != null) {
                        e.adClicked(this.g);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    this.f.getLogger().e("AppLovinInterstitialActivity", "Encountered error while clicking through video.", th);
                    return;
                }
            }
            c();
        }
    }

    private void b(int i) {
        a(i, this.D);
    }

    private void b(AppLovinAd appLovinAd) {
        c(appLovinAd);
        dismiss();
    }

    private int c(int i) {
        return AppLovinSdkUtils.dpToPx(this, i);
    }

    private void c() {
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        if (this.e.z() && this.C != null && this.C.getVisibility() != 8) {
            boolean z = this.C.getVisibility() == 4;
            float f2 = z ? 0.0f : DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
            if (!z) {
                f = 0.0f;
            }
            Animation alphaAnimation = new AlphaAnimation(f2, f);
            alphaAnimation.setDuration(750);
            alphaAnimation.setAnimationListener(new b(this, z));
            this.C.startAnimation(alphaAnimation);
        }
    }

    private void c(AppLovinAd appLovinAd) {
        if (!this.j) {
            this.j = true;
            if (this.b != null) {
                AppLovinAdDisplayListener d = this.b.d();
                if (d != null) {
                    d.adHidden(appLovinAd);
                }
            }
        }
    }

    private void d() {
        Editor edit = E().edit();
        edit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        edit.putInt("com.applovin.interstitial.last_video_position", 0);
        edit.commit();
    }

    private void d(AppLovinAd appLovinAd) {
        if (!this.k) {
            this.k = true;
            AppLovinAdVideoPlaybackListener c = this.b.c();
            if (c != null) {
                c.videoPlaybackBegan(appLovinAd);
            }
        }
    }

    private boolean e() {
        return E().getInt("com.applovin.interstitial.last_video_position", 0) > 0 ? this.r : this.e.I() ? this.f.getSettings().isMuted() : this.e.G();
    }

    private void f() {
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.w = new FrameLayout(this);
        this.w.setLayoutParams(layoutParams);
        this.w.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
        this.v = new Handler();
    }

    private void g() {
        if (this.u.compareAndSet(false, true)) {
            if (this.e.k()) {
                this.d.e("AppLovinInterstitialActivity", "Handling media player error - Finishing activity...");
                finish();
            } else {
                this.d.e("AppLovinInterstitialActivity", "Handling media player error - Showing poststitial...");
                w();
            }
            this.d.e("AppLovinInterstitialActivity", "Finished handling media player error.");
            return;
        }
        this.d.e("AppLovinInterstitialActivity", "Already handled media player error. Doing nothing...");
    }

    private void h() {
        d(this.g);
        this.x.start();
        b(q());
    }

    private void i() {
        int i = 3;
        this.y = u.a(this.f, this, this.g.getCloseStyle());
        this.y.setVisibility(8);
        this.y.setOnClickListener(new q(this));
        int c = c(this.e.m());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(c, c, (this.e.x() ? 3 : 5) | 48);
        this.y.a(c);
        int c2 = c(this.e.o());
        int c3 = c(this.e.q());
        layoutParams.setMargins(c3, c2, c3, c2);
        this.w.addView(this.y, layoutParams);
        this.A = u.a(this.f, this, this.g.getCloseStyle());
        this.A.setVisibility(8);
        this.A.setOnClickListener(new r(this));
        layoutParams = new LayoutParams(c, c, (this.e.w() ? 3 : 5) | 48);
        layoutParams.setMargins(c3, c2, c3, c2);
        this.A.a(c);
        this.w.addView(this.A, layoutParams);
        this.A.bringToFront();
        if (m()) {
            int c4 = c(new cf(this.f).r());
            this.z = new View(this);
            this.z.setBackgroundColor(0);
            this.z.setVisibility(8);
            this.B = new View(this);
            this.B.setBackgroundColor(0);
            this.B.setVisibility(8);
            c += c4;
            int c5 = c2 - c(5);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(c, c, (this.e.x() ? 3 : 5) | 48);
            layoutParams2.setMargins(c5, c5, c5, c5);
            if (!this.e.w()) {
                i = 5;
            }
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(c, c, i | 48);
            layoutParams3.setMargins(c5, c5, c5, c5);
            this.z.setOnClickListener(new s(this));
            this.B.setOnClickListener(new t(this));
            this.w.addView(this.z, layoutParams2);
            this.z.bringToFront();
            this.w.addView(this.B, layoutParams3);
            this.B.bringToFront();
        }
    }

    private void j() {
        if (this.E == null) {
            try {
                this.E = new ImageView(this);
                if (k()) {
                    this.f.getLogger().d("AppLovinInterstitialActivity", "Mute button should be hidden");
                    return;
                }
                int c = c(this.e.C());
                ViewGroup.LayoutParams layoutParams = new LayoutParams(c, c, this.e.D());
                this.E.setScaleType(ScaleType.FIT_CENTER);
                int c2 = c(this.e.E());
                layoutParams.setMargins(c2, c2, c2, c2);
                this.r = e();
                String muteImageFilename = e() ? this.g.getMuteImageFilename() : this.g.getUnmuteImageFilename();
                File a = this.f.getFileManager().a(muteImageFilename, (Context) this, true);
                if (a != null) {
                    this.f.getLogger().d("AppLovinInterstitialActivity", "Added mute button with params: " + layoutParams);
                    AppLovinSdkUtils.safePopulateImageView(this.E, Uri.fromFile(a), c);
                    this.E.setClickable(true);
                    this.E.setOnClickListener(new c(this));
                    this.w.addView(this.E, layoutParams);
                    this.E.bringToFront();
                    return;
                }
                this.f.getLogger().e("AppLovinInterstitialActivity", "Attempting to add mute button but could not find cached file with intialFilename = " + muteImageFilename);
            } catch (Throwable e) {
                this.f.getLogger().w("AppLovinInterstitialActivity", "Failed to attach mute button", e);
            }
        }
    }

    private boolean k() {
        return !this.e.A() ? true : this.e.B() ? e() ? false : !this.e.H() : false;
    }

    private void l() {
        runOnUiThread(new d(this));
    }

    private boolean m() {
        return this.e.r() > 0;
    }

    private void n() {
        runOnUiThread(new e(this));
    }

    private void o() {
        if (this.g.getVideoCloseDelay() >= 0.0f) {
            u uVar = (!this.q || this.A == null) ? this.y : this.A;
            a(dm.c(this.g.getVideoCloseDelay()), uVar);
        }
    }

    private void p() {
        if (this.C == null) {
            this.C = new s(this);
            int s = s();
            this.C.c(s);
            this.C.b((float) this.e.h());
            this.C.d(s);
            this.C.a((float) this.e.g());
            this.C.b(q());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(c(this.e.f()), c(this.e.f()), this.e.v());
            int c = c(this.e.u());
            layoutParams.setMargins(c, c, c, c);
            this.w.addView(this.C, layoutParams);
            this.C.bringToFront();
            s sVar = this.C;
            s = (!this.e.i() || q() <= 0) ? 4 : 0;
            sVar.setVisibility(s);
        }
    }

    private int q() {
        int countdownLength = this.g.getCountdownLength();
        return (countdownLength <= 0 && this.e.t()) ? this.t + 1 : countdownLength;
    }

    private void r() {
        this.D = UUID.randomUUID();
    }

    private int s() {
        return Color.parseColor(this.e.d());
    }

    private void t() {
        if (u()) {
            z();
            this.G.b();
            return;
        }
        skipVideo();
    }

    private boolean u() {
        return !D() && AppLovinAdType.INCENTIVIZED.equals(this.g.getType()) && this.e.P() && this.G != null;
    }

    private void v() {
        if (!this.c) {
            if (this.a != null) {
                this.a.setAdDisplayListener(new h(this));
                this.a.setAdClickListener(new i(this));
                this.g = (AppLovinAdImpl) this.b.b();
                f();
                i();
                File a = this.f.getFileManager().a(this.g.getVideoFilename(), (Context) this, false);
                if (a != null) {
                    a(a);
                } else {
                    this.h = true;
                    this.f.getLogger().e("AppLovinInterstitialActivity", "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!");
                    w();
                }
                this.y.bringToFront();
                if (m() && this.z != null) {
                    this.z.bringToFront();
                }
                if (this.A != null) {
                    this.A.bringToFront();
                }
                this.a.renderAd(this.g, this.b.g());
                this.b.a(true);
                return;
            }
            exitWithError("AdView was null");
        }
    }

    private void w() {
        B();
        if (this.x != null) {
            this.x.stopPlayback();
        }
        View frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
        frameLayout.addView(this.a);
        if (!(this.w == null || this.A == null)) {
            this.w.removeView(this.A);
            if (this.B != null) {
                this.w.removeView(this.B);
            }
        }
        if (m() && this.z != null) {
            if (this.w != null) {
                this.w.removeView(this.z);
            }
            frameLayout.addView(this.z);
            this.z.bringToFront();
        }
        if (this.w != null) {
            this.w.removeView(this.y);
        }
        frameLayout.addView(this.y);
        setContentView(frameLayout);
        this.y.bringToFront();
        if (this.g.getCloseDelay() >= 0.0f) {
            a(dm.c(this.g.getCloseDelay()), this.y);
        } else if (this.g.getCloseDelay() == -2.0f) {
            this.y.setVisibility(0);
        } else {
            a(0, this.y);
        }
        this.p = true;
    }

    private void x() {
        boolean z = !y();
        a(z);
        String muteImageFilename = z ? this.g.getMuteImageFilename() : this.g.getUnmuteImageFilename();
        int c = c(this.e.C());
        File a = this.f.getFileManager().a(muteImageFilename, (Context) this, true);
        if (a != null) {
            AppLovinSdkUtils.safePopulateImageView(this.E, Uri.fromFile(a), c);
        } else {
            this.f.getLogger().w("AppLovinInterstitialActivity", "Attempt to toggle mute but no cached mute image file found.");
        }
    }

    private boolean y() {
        return this.r;
    }

    private void z() {
        Editor edit = E().edit();
        edit.putInt("com.applovin.interstitial.last_video_position", this.x.getCurrentPosition());
        edit.putBoolean("com.applovin.interstitial.should_resume_video", true);
        edit.commit();
        this.x.pause();
    }

    public void continueVideo() {
        A();
    }

    public void dismiss() {
        ((AdViewControllerImpl) this.a.getAdViewController()).setIsForegroundClickInvalidated(true);
        d();
        B();
        if (this.b != null) {
            if (this.g != null) {
                c(this.g);
            }
            this.b.a(false);
            this.b.h();
        }
        finish();
    }

    public void exitWithError(String str) {
        try {
            Log.e("AppLovinInterstitialActivity", "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + ah.a + "; CleanedUp = " + ah.b));
            c(dm.a());
        } catch (Throwable e) {
            Log.e("AppLovinInterstitialActivity", "Failed to show a video ad due to error:", e);
        }
        finish();
    }

    public void onBackPressed() {
        if (a()) {
            this.d.d("AppLovinInterstitialActivity", "Back button was pressed; forwarding to Android for handling...");
            super.onBackPressed();
            return;
        }
        try {
            if (this.q && this.A != null && this.A.getVisibility() == 0 && this.A.getAlpha() > 0.0f && !this.n) {
                this.d.d("AppLovinInterstitialActivity", "Back button was pressed; forwarding as a click to skip button.");
                this.A.performClick();
            } else if (this.y == null || this.y.getVisibility() != 0 || this.y.getAlpha() <= 0.0f) {
                this.d.d("AppLovinInterstitialActivity", "Back button was pressed, but was not eligible for dismissal.");
            } else {
                this.d.d("AppLovinInterstitialActivity", "Back button was pressed; forwarding as a click to close button.");
                this.y.performClick();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        String stringExtra = getIntent().getStringExtra(KEY_WRAPPER_ID);
        if (stringExtra == null || stringExtra.isEmpty()) {
            exitWithError("Wrapper ID is null");
        } else {
            this.b = ah.a(stringExtra);
            if (this.b == null && lastKnownWrapper != null) {
                this.b = lastKnownWrapper;
            }
            if (this.b != null) {
                AppLovinAd b = this.b.b();
                this.f = (AppLovinSdkImpl) this.b.a();
                this.d = this.b.a().getLogger();
                this.e = new cf(this.b.a());
                if (b != null) {
                    Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                    int a = a(defaultDisplay);
                    int rotation = defaultDisplay.getRotation();
                    boolean z = (a == 2 && rotation == 0) || ((a == 2 && rotation == 2) || ((a == 1 && rotation == 1) || (a == 1 && rotation == 3)));
                    if (this.b.f() == AdTarget.ACTIVITY_PORTRAIT) {
                        if (z) {
                            if (!(rotation == 1 || rotation == 3)) {
                                this.c = true;
                                setRequestedOrientation(1);
                            }
                        } else if (!(rotation == 0 || rotation == 2)) {
                            this.c = true;
                            setRequestedOrientation(1);
                        }
                    } else if (z) {
                        if (!(rotation == 0 || rotation == 2)) {
                            this.c = true;
                            setRequestedOrientation(0);
                        }
                    } else if (!(rotation == 1 || rotation == 3)) {
                        this.c = true;
                        setRequestedOrientation(0);
                    }
                    this.a = new AppLovinAdView(this.f, AppLovinAdSize.INTERSTITIAL, (Activity) this);
                    this.a.setAutoDestroy(false);
                    this.b.a((w) this);
                    this.q = this.e.s();
                    this.G = new aw(this.f, this);
                } else {
                    exitWithError("No current ad found.");
                }
            } else {
                exitWithError("Wrapper is null; initialized state: " + Boolean.toString(ah.a));
            }
        }
        Editor edit = E().edit();
        edit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        edit.commit();
        d();
        v();
    }

    protected void onDestroy() {
        try {
            if (this.a != null) {
                ViewParent parent = this.a.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.a);
                }
                this.a.destroy();
                this.a = null;
            }
            if (this.x != null) {
                this.x.pause();
                this.x.stopPlayback();
            }
        } catch (Throwable th) {
            this.d.w("AppLovinInterstitialActivity", "Unable to destroy video view", th);
        }
        super.onDestroy();
    }

    protected void onPause() {
        if (!(this.c || this.h)) {
            z();
        }
        this.b.a(false);
        this.G.a();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.b.a(true);
        if (E().getBoolean("com.applovin.interstitial.should_resume_video", false) && !this.G.c()) {
            A();
            if (this.y == null || !this.e.j()) {
                dismiss();
                return;
            }
            this.d.d("AppLovinInterstitialActivity", "Fading in close button due to app resume.");
            u uVar = (!this.q || this.A == null) ? this.y : this.A;
            a(0, uVar);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            try {
                if (n.c() && this.e.O()) {
                    getWindow().getDecorView().setSystemUiVisibility(5894);
                } else {
                    getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
                }
            } catch (Throwable th) {
                this.d.e("AppLovinInterstitialActivity", "Setting window flags failed.", th);
            }
        }
    }

    public void skipVideo() {
        if (this.g.getDismissOnSkip()) {
            dismiss();
        } else {
            w();
        }
    }
}
