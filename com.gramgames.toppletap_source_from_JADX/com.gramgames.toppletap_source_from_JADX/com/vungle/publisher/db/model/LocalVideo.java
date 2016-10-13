package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.hb;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalVideo extends Video<LocalAd, LocalVideo, RequestLocalAdResponse> implements a {
    String a;
    public LocalViewableDelegate b;
    @Inject
    public com.vungle.publisher.db.model.LocalAd.Factory c;
    @Inject
    public Factory d;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.Video.Factory<LocalAd, LocalVideo, RequestLocalAdResponse> {
        private static final b d;
        @Inject
        public Provider<LocalVideo> a;
        @Inject
        public com.vungle.publisher.db.model.LocalViewableDelegate.Factory b;

        protected final /* synthetic */ Viewable b(Ad ad, RequestAdResponse requestAdResponse) {
            return a((LocalAd) ad, (RequestLocalAdResponse) requestAdResponse);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new LocalVideo[i];
        }

        protected final /* synthetic */ cb c_() {
            LocalVideo localVideo = (LocalVideo) this.a.get();
            localVideo.b = this.b.a(localVideo);
            return localVideo;
        }

        static {
            d = b.localVideo;
        }

        @Inject
        Factory() {
        }

        protected final b b() {
            return d;
        }

        private LocalVideo a(LocalAd localAd, RequestLocalAdResponse requestLocalAdResponse) {
            LocalVideo localVideo = (LocalVideo) super.a((Ad) localAd, (RequestAdResponse) requestLocalAdResponse);
            if (localVideo != null) {
                localVideo.a = requestLocalAdResponse.w;
                localVideo.a(requestLocalAdResponse.v);
                localVideo.b.b = requestLocalAdResponse.m();
                localVideo.q = d;
            }
            return localVideo;
        }

        private LocalVideo a(LocalVideo localVideo, Cursor cursor, boolean z) {
            super.a((Video) localVideo, cursor, z);
            localVideo.b.a(cursor);
            localVideo.a = bs.f(cursor, "checksum");
            return localVideo;
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

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory B() {
        return this.c;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.d;
    }

    @Inject
    LocalVideo() {
    }

    public final String g() {
        return this.b.b;
    }

    public final void a(Integer num) {
        this.b.c = num;
    }

    public final String h() {
        return "mp4";
    }

    public final String k() {
        return this.b.c();
    }

    public final Uri i() {
        return Uri.fromFile(new File(this.b.c()));
    }

    public final void a(hb hbVar) {
        this.b.a(hbVar);
    }

    public final boolean l() {
        return this.b.e();
    }

    public final boolean q() {
        return this.b.f();
    }

    public final boolean t() {
        return this.b.f();
    }

    public final boolean u() {
        return this.b.g();
    }

    public final int n() {
        return this.b.d();
    }

    public final boolean z() {
        return this.b.h();
    }

    public final int A() {
        return super.n();
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.b.a(a);
        a.put("checksum", this.a);
        return a;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        this.b.a(p);
        cb.a(p, "checksum", this.a, false);
        return p;
    }
}
