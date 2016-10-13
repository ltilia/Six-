package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import com.vungle.publisher.cb;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.db.model.Viewable.BaseFactory;
import com.vungle.publisher.fc;
import com.vungle.publisher.ff;
import com.vungle.publisher.ff.a;
import com.vungle.publisher.hb;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public class LocalArchive extends Viewable<LocalAd, LocalVideo, RequestLocalAdResponse> implements a {
    ArchiveEntry[] a;
    boolean b;
    boolean c;
    boolean d;
    @Inject
    public Factory e;
    @Inject
    public com.vungle.publisher.db.model.LoggedException.Factory f;
    @Inject
    public com.vungle.publisher.db.model.ArchiveEntry.Factory g;
    @Inject
    public com.vungle.publisher.db.model.LocalAd.Factory h;
    @Inject
    public LocalViewableDelegate i;

    class 1 implements a {
        final /* synthetic */ List a;
        final /* synthetic */ LocalArchive b;

        1(LocalArchive localArchive, List list) {
            this.b = localArchive;
            this.a = list;
        }

        public final void a(File file, long j) {
            Logger.v(Logger.PREPARE_TAG, "extracted " + file + ": " + j + " bytes");
            List list = this.a;
            com.vungle.publisher.db.model.ArchiveEntry.Factory factory = this.b.g;
            LocalArchive localArchive = this.b;
            int i = (int) j;
            ArchiveEntry b = factory.b();
            b.a = localArchive;
            b.b = file.getName();
            b.c = Integer.valueOf(i);
            list.add(b);
        }
    }

    static /* synthetic */ class 2 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.values().length];
            try {
                a[b.postRoll.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.preRoll.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends BaseFactory<LocalAd, LocalArchive, LocalVideo, RequestLocalAdResponse> {
        @Inject
        public Provider<LocalArchive> a;
        @Inject
        public com.vungle.publisher.db.model.LocalViewableDelegate.Factory b;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* bridge */ /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            return a((LocalArchive) cbVar, cursor, false);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new LocalArchive[i];
        }

        protected final /* synthetic */ cb c_() {
            LocalArchive localArchive = (LocalArchive) this.a.get();
            localArchive.i = this.b.a(localArchive);
            return localArchive;
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        public final /* bridge */ /* synthetic */ List d(int i) {
            return super.d(i);
        }

        @Inject
        Factory() {
        }

        final LocalArchive a(LocalAd localAd, RequestLocalAdResponse requestLocalAdResponse, b bVar) {
            if (requestLocalAdResponse == null) {
                return null;
            }
            String str;
            LocalArchive localArchive;
            switch (2.a[bVar.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    str = requestLocalAdResponse.t;
                    if (str == null) {
                        return null;
                    }
                    localArchive = (LocalArchive) super.b(localAd, requestLocalAdResponse);
                    localArchive.q = bVar;
                    localArchive.a(str);
                    return localArchive;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str = requestLocalAdResponse.u;
                    if (str == null) {
                        return null;
                    }
                    localArchive = (LocalArchive) super.b(localAd, requestLocalAdResponse);
                    localArchive.q = bVar;
                    localArchive.a(str);
                    return localArchive;
                default:
                    throw new IllegalArgumentException("cannot create archive of type: " + bVar);
            }
        }

        private LocalArchive a(LocalArchive localArchive, Cursor cursor, boolean z) {
            super.a((Viewable) localArchive, cursor, z);
            localArchive.i.a(cursor);
            if (z) {
                localArchive.i();
            }
            return localArchive;
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
        return this.h;
    }

    protected final /* bridge */ /* synthetic */ cb.a a_() {
        return this.e;
    }

    @Inject
    LocalArchive() {
    }

    public final String g() {
        return this.i.b;
    }

    public final void a(String str) {
        this.i.b = str;
    }

    public final void a(Integer num) {
        this.i.c = num;
    }

    public final String h() {
        return "zip";
    }

    public final ArchiveEntry[] i() {
        if (!this.b) {
            a(this.g.a(this), false);
        }
        return this.a;
    }

    private void a(ArchiveEntry[] archiveEntryArr, boolean z) {
        this.a = archiveEntryArr;
        this.c = z;
        this.b = true;
    }

    public final String j() {
        return fc.a(this.i.a(), this.q);
    }

    public final String k() {
        return this.i.c();
    }

    public final void a(hb hbVar) {
        this.i.a(hbVar);
    }

    public final boolean l() {
        return this.i.e();
    }

    public final boolean q() {
        if (this.i.g() && D()) {
            return u();
        }
        return false;
    }

    private boolean D() {
        File b = this.i.b();
        try {
            List arrayList = new ArrayList();
            ff.a(b, new File(j()), new 1(this, arrayList));
            a((ArchiveEntry[]) arrayList.toArray(new ArchiveEntry[arrayList.size()]), true);
            return true;
        } catch (Throwable e) {
            this.f.b(Logger.PREPARE_TAG, "error extracting " + b, e);
            return false;
        }
    }

    public final boolean t() {
        return this.i.f();
    }

    public final boolean u() {
        ArchiveEntry[] i = i();
        int length = i.length;
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            ArchiveEntry archiveEntry = i[i2];
            String a = fc.a(archiveEntry.a.j(), archiveEntry.b);
            File file = a == null ? null : new File(a);
            if (archiveEntry.c == null) {
                Logger.w(Logger.PREPARE_TAG, file + " size is null");
                z = false;
            } else {
                int length2 = (int) file.length();
                int intValue = archiveEntry.c.intValue();
                boolean z2 = length2 == intValue;
                if (z2) {
                    Logger.v(Logger.PREPARE_TAG, file + " size verified " + length2);
                    z = z2;
                } else {
                    Logger.d(Logger.PREPARE_TAG, file + " size " + length2 + " doesn't match expected " + intValue);
                    z = file.exists();
                }
            }
            if (!z) {
                return false;
            }
            i2++;
            z = true;
        }
        return z;
    }

    public final int n() {
        E();
        return this.i.d();
    }

    public final boolean z() {
        return this.i.h() & E();
    }

    private boolean E() {
        String j = j();
        Logger.d(Logger.DATABASE_TAG, "deleting " + this.q + " directory " + j);
        boolean a = fc.a(j());
        if (a) {
            Logger.v(Logger.DATABASE_TAG, "deleting " + this.q + " directory " + j);
            this.a = null;
            this.d = true;
        } else {
            Logger.w(Logger.DATABASE_TAG, "failed to delete " + this.q + " directory " + j);
        }
        return a;
    }

    public final int A() {
        return super.n();
    }

    public final int m() {
        int m = super.m();
        if (m == 1) {
            if (this.d) {
                this.g.a((Integer) this.s);
                E();
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesDeleted = false");
                this.d = false;
            } else if (this.c) {
                cb.a.a(this.a);
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesNew = false");
                this.c = false;
            }
        }
        return m;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.i.a(a);
        return a;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        this.i.a(p);
        return p;
    }
}
