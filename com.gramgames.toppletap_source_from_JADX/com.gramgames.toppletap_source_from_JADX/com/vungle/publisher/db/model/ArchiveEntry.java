package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ArchiveEntry extends cb<Integer> {
    LocalArchive a;
    String b;
    Integer c;
    @Inject
    public Factory d;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<ArchiveEntry, Integer> {
        @Inject
        public Provider<ArchiveEntry> a;

        protected final /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            ArchiveEntry archiveEntry = (ArchiveEntry) cbVar;
            archiveEntry.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            archiveEntry.b = bs.f(cursor, "relative_path");
            archiveEntry.c = bs.d(cursor, "size");
            return archiveEntry;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new ArchiveEntry[i];
        }

        protected final /* synthetic */ cb c_() {
            return b();
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

        final int a(Integer num) {
            if (num == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            int delete = this.c.getWritableDatabase().delete("archive_entry", "viewable_id = ?", new String[]{String.valueOf(num)});
            Logger.d(Logger.DATABASE_TAG, "deleted " + delete + " archive_entry rows for viewable_id " + num);
            return delete;
        }

        final ArchiveEntry[] a(LocalArchive localArchive) {
            Cursor query;
            Throwable th;
            if (localArchive == null) {
                throw new IllegalArgumentException("null archive");
            }
            Integer C = localArchive.C();
            if (C == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching archive_entry records by viewable_id " + C);
                query = this.c.getReadableDatabase().query("archive_entry", null, "viewable_id = ?", new String[]{String.valueOf(C)}, null, null, null);
                try {
                    ArchiveEntry[] archiveEntryArr = new ArchiveEntry[query.getCount()];
                    int i = 0;
                    while (query.moveToNext()) {
                        cb b = b();
                        b(b, query);
                        b.a = localArchive;
                        archiveEntryArr[i] = b;
                        Logger.v(Logger.DATABASE_TAG, "fetched " + b);
                        i++;
                    }
                    if (query != null) {
                        query.close();
                    }
                    return archiveEntryArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        protected final String e_() {
            return "archive_entry";
        }

        final ArchiveEntry b() {
            return (ArchiveEntry) this.a.get();
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

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.d;
    }

    @Inject
    ArchiveEntry() {
    }

    protected final String b() {
        return "archive_entry";
    }

    private Integer d() {
        return this.a == null ? null : this.a.C();
    }

    public final int m() {
        if (this.s != null) {
            return super.m();
        }
        Integer d = d();
        Logger.d(Logger.DATABASE_TAG, "updating archive_entry by viewable_id " + d + ", relative_path " + this.b);
        int updateWithOnConflict = this.t.getWritableDatabase().updateWithOnConflict("archive_entry", a(false), "viewable_id = ? AND relative_path = ?", new String[]{String.valueOf(d), r6}, 3);
        v();
        return updateWithOnConflict;
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (this.s != null) {
            contentValues.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, (Integer) this.s);
        }
        contentValues.put("viewable_id", d());
        contentValues.put("relative_path", this.b);
        contentValues.put("size", this.c);
        return contentValues;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "viewable_id", d(), false);
        cb.a(p, "relative_path", this.b, false);
        cb.a(p, "size", this.c, false);
        return p;
    }
}
