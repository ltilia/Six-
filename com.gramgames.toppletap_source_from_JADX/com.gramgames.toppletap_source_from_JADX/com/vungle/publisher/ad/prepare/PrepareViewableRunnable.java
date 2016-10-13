package com.vungle.publisher.ad.prepare;

import com.vungle.log.Logger;
import com.vungle.publisher.ab;
import com.vungle.publisher.ag;
import com.vungle.publisher.cg.a;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.ct;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.hb;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public final class PrepareViewableRunnable implements Runnable {
    ct a;
    String b;
    hb c;
    @Inject
    public EventBus d;
    @Inject
    public com.vungle.publisher.db.model.LoggedException.Factory e;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.aware.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.queued.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.downloading.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.downloaded.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.ready.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<PrepareViewableRunnable> a;

        @Inject
        Factory() {
        }

        public final PrepareViewableRunnable a(ct ctVar, hb hbVar) {
            PrepareViewableRunnable prepareViewableRunnable = (PrepareViewableRunnable) this.a.get();
            prepareViewableRunnable.a = ctVar;
            prepareViewableRunnable.b = ctVar.d();
            prepareViewableRunnable.c = hbVar;
            return prepareViewableRunnable;
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

    @Inject
    PrepareViewableRunnable() {
    }

    public final void run() {
        Logger.d(Logger.PREPARE_TAG, "run PrepareViewableRunnable. adId = " + this.b + ". type = " + this.a.f());
        try {
            if (a()) {
                this.d.a(new ag(this.b));
            }
        } catch (Throwable e) {
            this.e.a(Logger.PREPARE_TAG, "error processing " + this.a.f() + " for ad " + this.b + ". retryCount = " + this.c.b, e);
            this.a.b(a.failed);
            this.d.a(new ab(this.c));
        }
    }

    private boolean a() {
        ct ctVar = this.a;
        boolean z = false;
        b f = ctVar.f();
        a e = ctVar.e();
        switch (1.a[e.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                Logger.d(Logger.PREPARE_TAG, f + " will begin downloading for ad_id " + this.b);
                try {
                    ctVar.a(this.c);
                    break;
                } catch (Throwable e2) {
                    throw new a("external storage not available, could not download ad", e2);
                }
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                Logger.d(Logger.PREPARE_TAG, f + " still downloading for ad_id " + this.b);
                break;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                Logger.d(Logger.PREPARE_TAG, f + " downloaded, continuing to postprocessing for ad_id " + this.b);
                try {
                    ct ctVar2 = this.a;
                    if (ctVar2.l()) {
                        z = true;
                        break;
                    }
                    throw new b(ctVar2.f() + " post processing failed for ad_id " + ctVar2.d());
                } catch (Throwable e22) {
                    throw new a("external storage not available, could not post process ad", e22);
                }
            case Yytoken.TYPE_COMMA /*5*/:
                Logger.v(Logger.PREPARE_TAG, f + " already " + e + " for ad_id " + this.b);
                z = true;
                break;
            default:
                throw new IllegalStateException("unexpected " + f + " status: " + e);
        }
        ctVar.m();
        return z;
    }
}
