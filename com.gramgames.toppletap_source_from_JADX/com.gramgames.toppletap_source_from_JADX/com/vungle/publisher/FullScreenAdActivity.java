package com.vungle.publisher;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import com.google.android.gms.drive.DriveFile;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.display.view.AdFragment;
import com.vungle.publisher.display.view.PostRollFragment;
import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.reporting.AdReportEventListener;
import com.vungle.publisher.reporting.AdReportEventListener.Factory;
import com.vungle.publisher.util.IntentFactory;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public class FullScreenAdActivity extends Activity {
    public static final String AD_CONFIG_EXTRA_KEY = "adConfig";
    public static final String AD_ID_EXTRA_KEY = "adId";
    Ad a;
    AdReportEventListener b;
    @Inject
    Factory c;
    @Inject
    AdManager d;
    @Inject
    Factory e;
    @Inject
    ek f;
    @Inject
    EventBus g;
    @Inject
    VideoFragment.Factory h;
    @Inject
    PostRollFragment.Factory i;
    @Inject
    SdkState j;
    @Inject
    IntentFactory k;
    @Inject
    LoggedException.Factory l;
    @Inject
    protected WrapperFramework m;
    private AdFragment n;
    private PostRollFragment o;
    private View p;
    private VideoFragment q;
    private AdEventListener r;

    class 1 implements OnSystemUiVisibilityChangeListener {
        final /* synthetic */ a a;
        final /* synthetic */ FullScreenAdActivity b;

        1(FullScreenAdActivity fullScreenAdActivity, a aVar) {
            this.b = fullScreenAdActivity;
            this.a = aVar;
        }

        public final void onSystemUiVisibilityChange(int visibility) {
            if ((visibility & 4) == 0) {
                this.b.a(this.a);
            }
        }
    }

    static /* synthetic */ class 2 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Orientation.values().length];
            try {
                a[Orientation.autoRotate.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Orientation.matchVideo.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class AdEventListener extends et {
        private FullScreenAdActivity a;

        @Singleton
        /* compiled from: vungle */
        public static class Factory {
            @Inject
            AdEventListener a;

            @Inject
            Factory() {
            }

            public AdEventListener getInstance(FullScreenAdActivity activity) {
                this.a.a = activity;
                return this.a;
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

        /* compiled from: vungle */
        public final class Factory_MembersInjector implements MembersInjector<Factory> {
            static final /* synthetic */ boolean a;
            private final Provider<AdEventListener> b;

            static {
                a = !Factory_MembersInjector.class.desiredAssertionStatus();
            }

            public Factory_MembersInjector(Provider<AdEventListener> listenerProvider) {
                if (a || listenerProvider != null) {
                    this.b = listenerProvider;
                    return;
                }
                throw new AssertionError();
            }

            public static MembersInjector<Factory> create(Provider<AdEventListener> listenerProvider) {
                return new Factory_MembersInjector(listenerProvider);
            }

            public final void injectMembers(Factory instance) {
                if (instance == null) {
                    throw new NullPointerException("Cannot inject members into a null reference");
                }
                instance.a = (AdEventListener) this.b.get();
            }

            public static void injectListener(Factory instance, Provider<AdEventListener> listenerProvider) {
                instance.a = (AdEventListener) listenerProvider.get();
            }
        }

        @Inject
        AdEventListener() {
        }

        public void onEvent(ac acVar) {
            Logger.v(Logger.EVENT_TAG, "postRoll.onCancel()");
            this.a.a(true, false);
        }

        public void onEvent(l event) {
            a aVar = event.a;
            Logger.v(Logger.EVENT_TAG, "cta click event: " + aVar);
            FullScreenAdActivity fullScreenAdActivity = this.a;
            boolean z = false;
            try {
                String f = fullScreenAdActivity.a.f();
                Logger.v(Logger.AD_TAG, "call to action destination " + f);
                if (f != null) {
                    Intent a = IntentFactory.a("android.intent.action.VIEW", Uri.parse(f));
                    a.addFlags(DriveFile.MODE_READ_ONLY);
                    fullScreenAdActivity.startActivity(a);
                    fullScreenAdActivity.g.a(new m(fullScreenAdActivity.a, aVar));
                }
                z = true;
            } catch (Throwable e) {
                fullScreenAdActivity.l.a(Logger.AD_TAG, "error loading call-to-action URL " + null, e);
            }
            fullScreenAdActivity.a(z, true);
        }

        public void onEvent(ad adVar) {
            Logger.v(Logger.EVENT_TAG, "postRoll.onRepeat()");
            this.a.b();
        }

        public void onEvent(aw awVar) {
            this.a.a(false, false);
        }

        public void onEvent(aq aqVar) {
            Logger.v(Logger.EVENT_TAG, "video.onCancel()");
            this.a.a();
        }

        public void onEvent(ar arVar) {
            Logger.v(Logger.EVENT_TAG, "video.onNext()");
            this.a.a();
        }

        public void onEvent(ah ahVar) {
            FullScreenAdActivity fullScreenAdActivity = this.a;
            try {
                Intent a = IntentFactory.a("android.intent.action.VIEW", Uri.parse("https://www.vungle.com/privacy/"));
                a.addFlags(DriveFile.MODE_READ_ONLY);
                fullScreenAdActivity.startActivity(a);
            } catch (Throwable e) {
                fullScreenAdActivity.l.a(Logger.AD_TAG, "error loading privacy URL", e);
            }
        }
    }

    /* compiled from: vungle */
    public final class AdEventListener_Factory implements dagger.internal.Factory<AdEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<AdEventListener> b;

        static {
            a = !AdEventListener_Factory.class.desiredAssertionStatus();
        }

        public AdEventListener_Factory(MembersInjector<AdEventListener> adEventListenerMembersInjector) {
            if (a || adEventListenerMembersInjector != null) {
                this.b = adEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final AdEventListener get() {
            return (AdEventListener) MembersInjectors.injectMembers(this.b, new AdEventListener());
        }

        public static dagger.internal.Factory<AdEventListener> create(MembersInjector<AdEventListener> adEventListenerMembersInjector) {
            return new AdEventListener_Factory(adEventListenerMembersInjector);
        }
    }

    /* compiled from: vungle */
    public final class AdEventListener_MembersInjector implements MembersInjector<AdEventListener> {
        static final /* synthetic */ boolean a;
        private final Provider<EventBus> b;

        static {
            a = !AdEventListener_MembersInjector.class.desiredAssertionStatus();
        }

        public AdEventListener_MembersInjector(Provider<EventBus> eventBusProvider) {
            if (a || eventBusProvider != null) {
                this.b = eventBusProvider;
                return;
            }
            throw new AssertionError();
        }

        public static MembersInjector<AdEventListener> create(Provider<EventBus> eventBusProvider) {
            return new AdEventListener_MembersInjector(eventBusProvider);
        }

        public final void injectMembers(AdEventListener instance) {
            if (instance == null) {
                throw new NullPointerException("Cannot inject members into a null reference");
            }
            eu.a(instance, this.b);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        try {
            Logger.d(Logger.AD_TAG, "interstital ad");
            super.onCreate(savedInstanceState);
            Injector.b().a(this);
            Intent intent = getIntent();
            a aVar = (a) intent.getParcelableExtra(AD_CONFIG_EXTRA_KEY);
            String stringExtra = intent.getStringExtra(AD_ID_EXTRA_KEY);
            AdManager adManager = this.d;
            Ad a = adManager.g.a(stringExtra);
            if (a == null) {
                a = adManager.n.a(stringExtra);
            }
            this.a = a;
            if (a == null) {
                Logger.w(Logger.AD_TAG, "no ad in activity");
                this.g.a(new be());
                finish();
                return;
            }
            int i;
            this.p = getWindow().getDecorView();
            Video k = a.k();
            this.r = this.c.getInstance(this);
            Factory factory = this.e;
            factory.a.a(a);
            this.b = factory.a;
            this.r.registerOnce();
            this.b.registerOnce();
            boolean z = savedInstanceState != null;
            if (!z) {
                this.g.a(new al(a, aVar));
            }
            VideoFragment.Factory factory2 = this.h;
            VideoFragment a2 = VideoFragment.Factory.a(this);
            if (a2 == null) {
                a2 = (VideoFragment) factory2.a.get();
            }
            this.q = VideoFragment.Factory.a(a2, a, aVar);
            if ((a instanceof LocalAd) && ((LocalAd) a).u() != null) {
                String uri = new File(fc.a(((LocalAd) a).u().j(), "index.html")).toURI().toString();
                PostRollFragment.Factory factory3 = this.i;
                PostRollFragment a3 = PostRollFragment.Factory.a(this);
                if (a3 == null) {
                    a3 = (PostRollFragment) factory3.a.get();
                }
                this.o = PostRollFragment.Factory.a(a3, uri);
            }
            a(aVar);
            Orientation orientation = aVar.getOrientation();
            switch (2.a[orientation.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation);
                    i = 10;
                    break;
                default:
                    boolean z2 = (k.g == null || k.n == null || k.n.intValue() <= k.g.intValue()) ? false : true;
                    if (!z2) {
                        z2 = (k.g == null || k.n == null || k.g.intValue() <= k.n.intValue()) ? false : true;
                        if (!z2) {
                            Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (unknown) --> auto-rotate");
                            i = 10;
                            break;
                        }
                        Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (portrait)");
                        i = 7;
                        break;
                    }
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (landscape)");
                    i = 6;
                    break;
                    break;
            }
            setRequestedOrientation(i);
            if ("postRollFragment".equals(z ? savedInstanceState.getString("currentFragment") : null)) {
                a();
            } else {
                b();
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing ad", e);
            a(false, false);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
            outState.putString("currentFragment", this.n.b());
        } catch (Throwable e) {
            this.l.a(Logger.AD_TAG, "error in onSaveInstanceState", e);
        }
    }

    final void a(a aVar) {
        if (this.f.a(eh.KITKAT) && aVar.isImmersiveMode()) {
            this.p.setSystemUiVisibility(5894);
            this.p.setOnSystemUiVisibilityChangeListener(new 1(this, aVar));
        }
    }

    protected void onResume() {
        try {
            super.onResume();
            SdkState sdkState = this.j;
            Logger.d(Logger.AD_TAG, "onAdActivityResume()");
            sdkState.a(false);
            sdkState.m = 0;
        } catch (Throwable e) {
            this.l.a(Logger.AD_TAG, "error in onResume()", e);
        }
    }

    protected void onPause() {
        try {
            super.onPause();
            SdkState sdkState = this.j;
            Logger.d(Logger.AD_TAG, "onAdActivityPause()");
            sdkState.m = sdkState.f();
        } catch (Throwable e) {
            this.l.a(Logger.AD_TAG, "error in onPause()", e);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!isFinishing()) {
            Logger.i(Logger.AD_TAG, "finishing ad that is being destroyed");
            finish();
        }
    }

    public void onBackPressed() {
        try {
            Logger.v(Logger.AD_TAG, "back button pressed");
            this.g.a(new i());
            this.n.a();
        } catch (Throwable e) {
            this.l.a(Logger.AD_TAG, "error in onBackPressed", e);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.n.a(keyCode);
        return super.onKeyDown(keyCode, event);
    }

    final void a() {
        if (this.o == null) {
            a(true, false);
            return;
        }
        this.g.a(new ae());
        a(this.o);
    }

    final void b() {
        if (this.q == null) {
            a();
        } else {
            a(this.q);
        }
    }

    final void a(boolean z, boolean z2) {
        try {
            this.g.a(z ? new bj(this.a, z2) : new bi(this.a, z2));
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error exiting ad", e);
        } finally {
            finish();
        }
    }

    private void a(AdFragment adFragment) {
        if (adFragment != this.n) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(16908290, adFragment, adFragment.b());
            beginTransaction.commit();
            this.n = adFragment;
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        try {
            super.onWindowFocusChanged(hasFocus);
            this.n.a(hasFocus);
        } catch (Throwable e) {
            this.l.a(Logger.AD_TAG, "error in onWindowFocusChanged", e);
        }
    }
}
