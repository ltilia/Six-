package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.google.android.gms.games.GamesStatusCodes;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.ct;
import com.vungle.publisher.ek;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.fa;
import com.vungle.publisher.fc;
import com.vungle.publisher.hb;
import com.vungle.publisher.net.http.DownloadHttpGateway;
import com.vungle.publisher.net.http.DownloadHttpGateway.1;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class LocalViewableDelegate {
    a a;
    public String b;
    Integer c;
    @Inject
    public DownloadHttpGateway d;
    @Inject
    public ek e;
    @Inject
    public SdkState f;

    interface a extends ct {
        boolean q();

        boolean u();
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<LocalViewableDelegate> a;
        @Inject
        public DownloadHttpGateway b;

        @Inject
        Factory() {
        }

        final LocalViewableDelegate a(a aVar) {
            LocalViewableDelegate localViewableDelegate = (LocalViewableDelegate) this.a.get();
            localViewableDelegate.a = aVar;
            localViewableDelegate.d = this.b;
            return localViewableDelegate;
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
    LocalViewableDelegate() {
    }

    private String i() {
        return this.a.d();
    }

    final String a() {
        return ((LocalAd) this.a.c()).t();
    }

    final File b() {
        String c = c();
        return c == null ? null : new File(c);
    }

    final String c() {
        return fc.a(a(), j() + "." + this.a.h());
    }

    private b j() {
        return this.a.f();
    }

    final int d() {
        this.a.z();
        return this.a.A();
    }

    final void a(hb hbVar) {
        Logger.d(Logger.PREPARE_TAG, "downloading " + this.a.f() + " for ad_id " + i());
        this.a.b(com.vungle.publisher.cg.a.downloading);
        DownloadHttpGateway downloadHttpGateway = this.d;
        downloadHttpGateway.e.a(new 1(downloadHttpGateway, this.a, hbVar), ScheduledPriorityExecutor.b.downloadLocalAd, (long) hbVar.a(GamesStatusCodes.STATUS_REQUEST_UPDATE_PARTIAL_SUCCESS));
    }

    final boolean e() {
        boolean q = this.a.q();
        if (q) {
            com.vungle.publisher.cg.a aVar = com.vungle.publisher.cg.a.ready;
            Logger.i(Logger.PREPARE_TAG, j() + " " + aVar + " for ad_id " + i());
            this.a.b(aVar);
        } else {
            if (SdkState.a()) {
                Logger.i(Logger.PREPARE_TAG, "debug mode: post-processing failed for " + this.a.x() + " - not deleting " + c());
            } else {
                Logger.d(Logger.PREPARE_TAG, "post-processing failed for " + this.a.x() + " - deleting " + c());
                this.a.z();
            }
            this.a.b(com.vungle.publisher.cg.a.aware);
        }
        return q;
    }

    final boolean f() throws fa {
        com.vungle.publisher.cg.a aVar;
        boolean u = this.a.u();
        String i = i();
        b j = j();
        if (u) {
            Logger.i(Logger.PREPARE_TAG, j + " verified for ad_id " + i);
            aVar = com.vungle.publisher.cg.a.ready;
        } else {
            Logger.w(Logger.PREPARE_TAG, j + " failed verification; reprocessing ad_id " + i);
            aVar = com.vungle.publisher.cg.a.aware;
        }
        this.a.b(aVar);
        return u;
    }

    final boolean g() throws fa {
        if (this.e.o()) {
            String i = i();
            b j = j();
            if (this.c == null) {
                Logger.d(Logger.PREPARE_TAG, j + " size " + this.c + " for ad_id: " + i);
                return true;
            }
            File b = b();
            int length = b == null ? 0 : (int) b.length();
            if (length == this.c.intValue()) {
                Logger.d(Logger.PREPARE_TAG, j + " disk size matched size " + this.c + " for ad_id: " + i);
                return true;
            }
            boolean z;
            Logger.d(Logger.PREPARE_TAG, j + " disk size " + length + " failed to match size " + this.c + " for ad_id: " + i);
            b = b();
            if (b == null) {
                Logger.w(Logger.PREPARE_TAG, "null " + this.a.f() + " file for ad " + i());
                z = false;
            } else if (b.exists()) {
                Logger.v(Logger.PREPARE_TAG, b.getAbsolutePath() + " exists, " + b.length() + " bytes");
                z = true;
            } else {
                Logger.w(Logger.PREPARE_TAG, b.getAbsolutePath() + " missing ");
                z = false;
            }
            if (!z) {
                return false;
            }
            Logger.d(Logger.PREPARE_TAG, "ignoring " + j + " size mismatch - file exists");
            return true;
        }
        throw new fa();
    }

    final boolean h() {
        File b = b();
        Logger.d(Logger.PREPARE_TAG, "deleting " + b);
        return b != null && b.delete();
    }

    final void a(ContentValues contentValues) {
        contentValues.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.b);
        contentValues.put("size", this.c);
    }

    final void a(Cursor cursor) {
        this.b = bs.f(cursor, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
        this.c = bs.d(cursor, "size");
    }

    final void a(StringBuilder stringBuilder) {
        cb.a(stringBuilder, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.b, false);
        cb.a(stringBuilder, "size", this.c, false);
    }
}
