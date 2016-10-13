package com.vungle.publisher.display.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings.System;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;
import com.facebook.ads.AdError;
import com.facebook.share.internal.ShareConstants;
import com.mopub.volley.DefaultRetryPolicy;
import com.vungle.log.Logger;
import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.ad.event.VolumeChangeEvent;
import com.vungle.publisher.ai;
import com.vungle.publisher.am;
import com.vungle.publisher.aq;
import com.vungle.publisher.ar;
import com.vungle.publisher.as;
import com.vungle.publisher.au;
import com.vungle.publisher.audio.VolumeChangeContentObserver;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.dw;
import com.vungle.publisher.ek;
import com.vungle.publisher.et;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.image.BitmapFactory;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.l;
import com.vungle.publisher.p;
import com.vungle.publisher.t;
import com.vungle.publisher.u;
import com.vungle.publisher.util.ViewUtils;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import gs.gram.mopub.BuildConfig;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class VideoFragment extends AdFragment implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private Bitmap A;
    private final Handler B;
    private final Runnable C;
    private AlertDialog D;
    private VideoEventListener E;
    private String F;
    private int G;
    private boolean H;
    private int I;
    private boolean J;
    private AtomicBoolean K;
    private int L;
    com.vungle.publisher.a a;
    Video<?, ?, ?> b;
    ImageView c;
    ObjectAnimator d;
    TouchDelegate e;
    @Inject
    public AlertDialogFactory f;
    @Inject
    public BitmapFactory g;
    @Inject
    public DisplayUtils h;
    @Inject
    public EventBus i;
    @Inject
    public Factory j;
    @Inject
    public com.vungle.publisher.display.view.PrivacyButton.Factory k;
    @Inject
    public com.vungle.publisher.display.view.ProgressBar.Factory l;
    @Inject
    public com.vungle.publisher.display.view.MuteButton.Factory m;
    @Inject
    public com.vungle.publisher.db.model.LoggedException.Factory n;
    @Inject
    public ek o;
    @Inject
    public ViewUtils p;
    @Inject
    public VolumeChangeContentObserver q;
    @Inject
    public Factory r;
    @Inject
    public AudioHelper s;
    private ImageView t;
    private ProgressBar u;
    private MuteButton v;
    private RelativeLayout w;
    private VideoView x;
    private ViewGroup y;
    private Bitmap z;

    class 1 implements OnTouchListener {
        final /* synthetic */ VideoFragment a;

        1(VideoFragment videoFragment) {
            this.a = videoFragment;
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            Logger.v(Logger.AD_TAG, "video onTouch");
            if (this.a.e != null) {
                this.a.e.onTouchEvent(motionEvent);
            }
            VideoFragment videoFragment = this.a;
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!(videoFragment.c == null || videoFragment.c.getAlpha() != 0.0f || videoFragment.d == null || videoFragment.d.isRunning())) {
                videoFragment.d.start();
            }
            return true;
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ Float a;
        final /* synthetic */ ImageView b;
        final /* synthetic */ VideoFragment c;

        2(VideoFragment videoFragment, Float f, ImageView imageView) {
            this.c = videoFragment;
            this.a = f;
            this.b = imageView;
        }

        public final void run() {
            float sqrt = (float) Math.sqrt((double) this.a.floatValue());
            int height = this.b.getHeight();
            int width = this.b.getWidth();
            int round = Math.round(((float) height) * sqrt);
            int round2 = Math.round(sqrt * ((float) width));
            Logger.v(Logger.AD_TAG, "scaling cta clickable area " + this.a + "x - width: " + width + " --> " + round2 + ", height: " + height + " --> " + round);
            Rect rect = new Rect();
            this.b.getHitRect(rect);
            rect.bottom = rect.top + round;
            rect.left = rect.right - round2;
            this.c.e = new TouchDelegate(rect, this.b);
        }
    }

    class 3 implements OnClickListener {
        final /* synthetic */ ImageView a;
        final /* synthetic */ VideoFragment b;

        3(VideoFragment videoFragment, ImageView imageView) {
            this.b = videoFragment;
            this.a = imageView;
        }

        public final void onClick(View view) {
            if (this.b.H) {
                Logger.d(Logger.AD_TAG, "cta overlay onClick");
                this.a.setOnClickListener(null);
                this.b.b(false);
                this.b.i.a(new l(com.vungle.publisher.db.model.EventTracking.a.video_click));
                return;
            }
            Logger.v(Logger.AD_TAG, "cta overlay onClick, but not enabled");
        }
    }

    class 4 implements com.vungle.publisher.display.view.AlertDialogFactory.a {
        final /* synthetic */ VideoFragment a;

        4(VideoFragment videoFragment) {
            this.a = videoFragment;
        }

        public final void a() {
            d();
        }

        public final void b() {
            Logger.d(Logger.AD_TAG, "cancel video");
            this.a.g();
        }

        public final void c() {
            d();
        }

        private void d() {
            this.a.x.start();
            this.a.K.set(false);
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<VideoFragment> a;

        @Inject
        Factory() {
        }

        public static VideoFragment a(VideoFragment videoFragment, Ad<?, ?, ?> ad, com.vungle.publisher.a aVar) {
            String f = ad.f();
            Video k = ad.k();
            if (k == null) {
                return null;
            }
            videoFragment.a = aVar;
            videoFragment.b = k;
            videoFragment.F = f;
            return videoFragment;
        }

        public static void a(VideoFragment videoFragment, Bundle bundle) {
            if (bundle != null) {
                videoFragment.a = (com.vungle.publisher.a) bundle.getParcelable(FullScreenAdActivity.AD_CONFIG_EXTRA_KEY);
                videoFragment.J = bundle.getBoolean("adStarted");
            }
        }

        public static VideoFragment a(Activity activity) {
            return (VideoFragment) activity.getFragmentManager().findFragmentByTag("videoFragment");
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class VideoEventListener extends et {
        private VideoFragment a;

        @Singleton
        /* compiled from: vungle */
        public static class Factory {
            @Inject
            public VideoEventListener a;

            @Inject
            Factory() {
            }
        }

        /* compiled from: vungle */
        public final class Factory_Factory implements dagger.internal.Factory<Factory> {
            static final /* synthetic */ boolean a;
            private final MembersInjector<Factory> b;

            static {
                a = !Factory_Factory.class.desiredAssertionStatus();
            }

            public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
                if (a || factoryMembersInjector != null) {
                    this.b = factoryMembersInjector;
                    return;
                }
                throw new AssertionError();
            }

            public final Factory get() {
                return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
            }

            public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
                return new Factory_Factory(factoryMembersInjector);
            }
        }

        public void onEvent(VolumeChangeEvent event) {
            boolean z;
            Object obj = 1;
            MuteButton h = this.a.v;
            if (event.b != 0) {
                z = true;
            } else {
                z = false;
            }
            if (event.a == 0) {
                if (event.b == 0) {
                    obj = null;
                }
            } else if (event.b != 0) {
                obj = null;
            }
            if (obj != null) {
                Logger.d(Logger.AD_TAG, "volume change " + (z ? "un" : BuildConfig.FLAVOR) + "mute");
                h.b();
                h.a(z);
            }
        }
    }

    /* compiled from: vungle */
    public final class VideoEventListener_Factory implements dagger.internal.Factory<VideoEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<VideoEventListener> b;

        static {
            a = !VideoEventListener_Factory.class.desiredAssertionStatus();
        }

        public VideoEventListener_Factory(MembersInjector<VideoEventListener> videoEventListenerMembersInjector) {
            if (a || videoEventListenerMembersInjector != null) {
                this.b = videoEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final VideoEventListener get() {
            return (VideoEventListener) MembersInjectors.injectMembers(this.b, new VideoEventListener());
        }

        public static dagger.internal.Factory<VideoEventListener> create(MembersInjector<VideoEventListener> videoEventListenerMembersInjector) {
            return new VideoEventListener_Factory(videoEventListenerMembersInjector);
        }
    }

    class a implements OnClickListener {
        final /* synthetic */ VideoFragment a;

        a(VideoFragment videoFragment) {
            this.a = videoFragment;
        }

        public final void onClick(View view) {
            Logger.v(Logger.AD_TAG, "close clicked");
            this.a.f(false);
        }
    }

    class b implements Runnable {
        final /* synthetic */ VideoFragment a;

        b(VideoFragment videoFragment) {
            this.a = videoFragment;
        }

        public final void run() {
            try {
                int c = this.a.c(false);
                this.a.b(c);
                this.a.u.setCurrentTimeMillis(c);
                this.a.i.a(new ai(c));
            } catch (Throwable e) {
                Logger.w(Logger.AD_TAG, e);
            } finally {
                this.a.B.postDelayed(this, 50);
            }
        }
    }

    @Inject
    public VideoFragment() {
        this.B = new Handler();
        this.C = new b(this);
        this.K = new AtomicBoolean();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Injector.b().a(this);
        View relativeLayout = new RelativeLayout(getActivity());
        this.y = relativeLayout;
        return relativeLayout;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        try {
            super.onActivityCreated(savedInstanceState);
            Factory.a(this, savedInstanceState);
            if (this.J) {
                this.i.a(new as());
            }
            Context activity = getActivity();
            Factory factory = this.r;
            factory.a.a = this;
            this.E = factory.a;
            View videoView = new VideoView(activity);
            this.x = videoView;
            View dwVar = new dw(activity);
            this.t = dwVar;
            com.vungle.publisher.display.view.MuteButton.Factory factory2 = this.m;
            boolean isSoundEnabled = this.a.isSoundEnabled();
            MuteButton muteButton = new MuteButton((byte) 0);
            muteButton.d = factory2.b.a("vg_mute_on.png");
            muteButton.e = factory2.b.a("vg_mute_off.png");
            muteButton.b = factory2.c;
            muteButton.a = isSoundEnabled;
            muteButton.c = factory2.d;
            muteButton.setOnClickListener(new 1(factory2, muteButton));
            this.v = muteButton;
            com.vungle.publisher.display.view.PrivacyButton.Factory factory3 = this.k;
            PrivacyButton privacyButton = new PrivacyButton(activity);
            privacyButton.setGravity(16);
            View dwVar2 = new dw(activity);
            ViewUtils.a(dwVar2, factory3.a.a("vg_privacy.png"));
            TextView textView = new TextView(activity);
            textView.setText(ShareConstants.WEB_DIALOG_PARAM_PRIVACY);
            textView.setTextSize(16.0f);
            textView.setTextColor(-1);
            textView.setVisibility(8);
            textView.setPadding(10, 0, 10, 0);
            privacyButton.addView(textView);
            privacyButton.addView(dwVar2);
            privacyButton.a = factory3.b;
            privacyButton.c = textView;
            com.vungle.publisher.display.view.ProgressBar.Factory factory4 = this.l;
            dwVar2 = new ProgressBar((byte) 0);
            dwVar2.e = 1;
            dwVar2.d = (int) factory4.b.a(2);
            this.u = dwVar2;
            this.y.addView(videoView);
            LayoutParams layoutParams = (LayoutParams) videoView.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = -1;
            layoutParams.addRule(13);
            View relativeLayout = new RelativeLayout(activity);
            this.y.addView(relativeLayout);
            layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.addRule(10);
            relativeLayout.addView(dwVar);
            Bitmap a = a("vg_close.png");
            if (a != null) {
                dwVar.setImageBitmap(a);
            }
            layoutParams = (LayoutParams) dwVar.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            dwVar.setAlpha(0.0f);
            relativeLayout.addView(privacyButton);
            layoutParams = (LayoutParams) privacyButton.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, dwVar2.getProgressBarHeight());
            this.y.addView(dwVar2, layoutParams2);
            layoutParams2.addRule(12);
            View relativeLayout2 = new RelativeLayout(activity);
            this.w = relativeLayout2;
            this.y.addView(relativeLayout2);
            layoutParams = (LayoutParams) relativeLayout2.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.addRule(2, 1);
            relativeLayout2.addView(muteButton);
            layoutParams = (LayoutParams) muteButton.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            int round = Math.round(this.h.a(2));
            int round2 = Math.round(this.h.a(1));
            relativeLayout.setPadding(round, round2, round, round2);
            relativeLayout2.setPadding(round, round2, round, round2);
            this.t.setScaleType(ScaleType.FIT_CENTER);
            this.v.setScaleType(ScaleType.FIT_CENTER);
            Logger.i(Logger.AD_TAG, "video play URI = " + this.b.i());
            videoView.setVideoURI(this.b.i());
            if (Boolean.TRUE.equals(this.b.h)) {
                this.z = a("vg_cta.png");
                this.A = a("vg_cta_disabled.png");
                Integer num = this.b.f;
                Integer num2 = this.b.j;
                Integer num3;
                if (num == null) {
                    if (num2 != null) {
                        Logger.v(Logger.AD_TAG, "overriding cta enabled from null to " + num2);
                        num = num2;
                    }
                    num3 = num2;
                    num2 = num;
                    num = num3;
                } else if (num2 == null) {
                    Logger.v(Logger.AD_TAG, "overriding cta shown from null to " + num);
                    num2 = num;
                } else {
                    if (num2.intValue() > num.intValue()) {
                        Logger.v(Logger.AD_TAG, "overriding cta shown from " + num2 + " to " + num);
                        num2 = num;
                    }
                    num3 = num2;
                    num2 = num;
                    num = num3;
                }
                Logger.d(Logger.AD_TAG, "cta shown at " + num + " seconds; enabled at " + num2 + " seconds");
                if (num2 == null) {
                    round = 0;
                } else {
                    round = num2.intValue();
                }
                this.G = round;
                if (num == null) {
                    round = 0;
                } else {
                    round = num.intValue();
                }
                this.I = round;
                View dwVar3 = new dw(getActivity());
                this.c = dwVar3;
                this.w.addView(dwVar3);
                layoutParams = (LayoutParams) dwVar3.getLayoutParams();
                layoutParams.addRule(9);
                layoutParams.addRule(15);
                dwVar3.setScaleType(ScaleType.FIT_CENTER);
                Float f = this.b.e;
                if (f == null || f.floatValue() <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                    Logger.v(Logger.AD_TAG, "cta clickable area not scaled");
                } else {
                    dwVar3.post(new 2(this, f, dwVar3));
                }
                if (Boolean.TRUE.equals(this.b.i)) {
                    dwVar3.setAlpha(0.0f);
                    dwVar3.setImageBitmap(this.z);
                    this.d = ObjectAnimator.ofFloat(dwVar3, "alpha", new float[]{0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
                    this.d.setDuration(750);
                } else {
                    d(this.I >= this.G);
                }
                dwVar3.setOnClickListener(new 3(this, dwVar3));
            }
            dwVar.setOnClickListener(new a(this));
            videoView.setOnTouchListener(new 1(this));
            videoView.setOnCompletionListener(this);
            videoView.setOnErrorListener(this);
            videoView.setOnPreparedListener(this);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "exception in onActivityCreated", e);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
            outState.putString(FullScreenAdActivity.AD_ID_EXTRA_KEY, this.b.d());
            outState.putParcelable(FullScreenAdActivity.AD_CONFIG_EXTRA_KEY, (Parcelable) this.a);
            outState.putBoolean("adStarted", this.J);
        } catch (Throwable e) {
            this.n.a(Logger.AD_TAG, "exception in onSaveInstanceState", e);
        }
    }

    private Bitmap a(String str) {
        Bitmap bitmap = null;
        try {
            bitmap = this.g.getBitmap(str);
        } catch (Throwable e) {
            this.n.b(Logger.AD_TAG, "error loading " + str, e);
        }
        return bitmap;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        int duration = mediaPlayer.getDuration();
        Logger.d(Logger.AD_TAG, "video ready: duration " + duration + " ms");
        this.u.setMaxTimeMillis(duration);
        this.i.a(new t(duration));
        if (!this.o.a(getActivity())) {
            d();
        }
    }

    public void onResume() {
        boolean z = true;
        try {
            super.onResume();
            Logger.d(Logger.AD_TAG, "video onResume");
            this.L = 0;
            MuteButton muteButton = this.v;
            String str = Logger.AD_TAG;
            StringBuilder stringBuilder = new StringBuilder("refresh mute state. is muted? ");
            if (muteButton.a) {
                z = false;
            }
            Logger.d(str, stringBuilder.append(z).toString());
            muteButton.setSoundEnabled(muteButton.a);
            muteButton.c.a(new au(muteButton.b.c()));
            ContentObserver contentObserver = this.q;
            if (!contentObserver.b) {
                contentObserver.a = contentObserver.c.b();
                contentObserver.b = true;
            }
            contentObserver.f.getContentResolver().registerContentObserver(System.CONTENT_URI, true, contentObserver);
            this.E.register();
            d();
        } catch (Throwable e) {
            this.n.a(Logger.AD_TAG, "error resuming VideoFragment", e);
            c();
        }
    }

    public void onPause() {
        Logger.d(Logger.AD_TAG, "video onPause");
        try {
            super.onPause();
            e();
            ContentObserver contentObserver = this.q;
            contentObserver.f.getContentResolver().unregisterContentObserver(contentObserver);
            this.E.unregister();
            this.s.a(true);
            if (this.J) {
                this.i.a(new ai(this.x.getCurrentPosition()));
            }
        } catch (Throwable e) {
            this.n.a(Logger.AD_TAG, "error in VideoFragment.onPause()", e);
            c();
        }
    }

    public final void a(boolean z) {
        try {
            super.a(z);
            if (z) {
                d();
            } else {
                e();
            }
        } catch (Throwable e) {
            this.n.a(Logger.AD_TAG, "exception in onWindowFocusChanged", e);
        }
    }

    private void c() {
        this.B.removeCallbacks(this.C);
    }

    private void d() {
        boolean z = true;
        boolean z2 = !this.J;
        this.J = true;
        if (this.D == null || !this.D.isShowing()) {
            z = false;
        }
        if (!z) {
            b(this.x.getCurrentPosition());
            this.x.requestFocus();
            this.x.start();
            this.B.post(this.C);
            if (z2) {
                this.i.a(new am());
            }
        }
    }

    private void e() {
        this.x.pause();
        c();
    }

    final void b(boolean z) {
        c();
        int c = c(z);
        this.i.a(z ? new p(c) : new u(c));
        this.J = false;
        this.L = 0;
        this.K.set(false);
    }

    final int c(boolean z) {
        int duration = z ? this.x.getDuration() : this.x.getCurrentPosition();
        int i = this.L;
        if (duration > i) {
            this.L = duration;
            return duration;
        }
        if (duration < i) {
            Logger.w(Logger.AD_TAG, "watched millis decreased from " + i + " --> " + duration);
        }
        return i;
    }

    public final void a() {
        Logger.v(Logger.AD_TAG, "back button pressed");
        f(true);
    }

    private boolean f() {
        return this.t.getAlpha() == DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    final void b(int i) {
        boolean z = true;
        if (Boolean.TRUE.equals(this.b.h)) {
            if (Boolean.TRUE.equals(this.b.i)) {
                if (this.c.getAlpha() < DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                    z = false;
                }
                e(z);
            } else {
                a(this.c, this.I, i);
                if (i < this.G * AdError.NETWORK_ERROR_CODE) {
                    z = false;
                }
                e(z);
            }
        }
        Integer num = this.a.isIncentivized() ? this.b.k : this.b.l;
        if (num != null) {
            a(this.t, num.intValue(), i);
        }
    }

    private void d(boolean z) {
        boolean z2 = z && this.F != null;
        Logger.v(Logger.AD_TAG, "cta button " + (z2 ? "enabled" : "disabled"));
        this.H = z2;
        this.c.setImageBitmap(z2 ? this.z : this.A);
    }

    private void e(boolean z) {
        if (z != this.H) {
            d(z);
        }
    }

    private static void a(View view, int i, int i2) {
        float alpha = view.getAlpha();
        int i3 = i * AdError.NETWORK_ERROR_CODE;
        float f = 0.0f;
        int i4 = i3 - 750;
        if (i2 > i4) {
            f = i2 >= i3 ? DefaultRetryPolicy.DEFAULT_BACKOFF_MULT : ((float) (i2 - i4)) / ((float) (i3 - i4));
        }
        if (f != alpha) {
            view.setAlpha(f);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Logger.d(Logger.AD_TAG, "video.onCompletion");
        b(true);
        this.i.a(new ar());
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        Logger.e(Logger.AD_TAG, "video.onError: " + what + ", " + extra);
        b(false);
        this.i.a(new ar());
        return true;
    }

    private void g() {
        b(false);
        this.x.stopPlayback();
        this.i.a(new aq());
    }

    private void f(boolean z) {
        if (z) {
            boolean z2 = f() || this.a.isBackButtonImmediatelyEnabled();
            if (!z2) {
                return;
            }
        } else if (!f()) {
            return;
        }
        if (this.K.compareAndSet(false, true)) {
            Logger.d(Logger.AD_TAG, "exiting video");
            if (this.a.isIncentivized()) {
                AlertDialog alertDialog;
                this.x.pause();
                if (this.D != null) {
                    alertDialog = this.D;
                } else {
                    AlertDialogFactory alertDialogFactory = this.f;
                    Context activity = getActivity();
                    com.vungle.publisher.a aVar = this.a;
                    com.vungle.publisher.display.view.AlertDialogFactory.a 4 = new 4(this);
                    Builder builder = new Builder(new ContextThemeWrapper(activity, activity.getApplicationInfo().theme));
                    builder.setTitle(aVar.getIncentivizedCancelDialogTitle());
                    builder.setMessage(aVar.getIncentivizedCancelDialogBodyText());
                    builder.setPositiveButton(aVar.getIncentivizedCancelDialogKeepWatchingButtonText(), new 1(alertDialogFactory, 4));
                    builder.setNegativeButton(aVar.getIncentivizedCancelDialogCloseButtonText(), new 2(alertDialogFactory, 4));
                    builder.setOnCancelListener(new 3(alertDialogFactory, 4));
                    alertDialog = builder.create();
                }
                this.D = alertDialog;
                alertDialog.show();
                return;
            }
            this.t.setOnClickListener(null);
            g();
        }
    }

    public final boolean a(int i) {
        if (i == 24 && this.s.b() == 0) {
            Logger.d(Logger.AD_TAG, "volume up - unmuting");
            this.s.a(true);
        }
        return false;
    }

    public final String b() {
        return "videoFragment";
    }
}
