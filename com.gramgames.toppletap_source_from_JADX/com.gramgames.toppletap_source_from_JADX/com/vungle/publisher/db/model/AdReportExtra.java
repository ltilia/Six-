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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class AdReportExtra extends cb<Integer> {
    Integer a;
    public String b;
    public String c;
    @Inject
    public Factory d;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<AdReportExtra, Integer> {
        @Inject
        public Provider<AdReportExtra> a;

        protected final /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            AdReportExtra adReportExtra = (AdReportExtra) cbVar;
            adReportExtra.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            adReportExtra.a = bs.d(cursor, "ad_report_id");
            adReportExtra.b = bs.f(cursor, UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
            adReportExtra.c = bs.f(cursor, "value");
            return adReportExtra;
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new AdReportExtra[i];
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
            int delete = this.c.getWritableDatabase().delete("ad_report_extra", "ad_report_id = ?", new String[]{String.valueOf(num)});
            Logger.v(Logger.DATABASE_TAG, "deleted " + delete + " ad_report_extra records for adReportId: " + num);
            return delete;
        }

        final Map<String, AdReportExtra> b(Integer num) {
            Throwable th;
            Cursor cursor = null;
            if (num == null) {
                Logger.w(Logger.DATABASE_TAG, "failed to fetch ad_report_extra records by ad_report_id " + num);
                return null;
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching ad_report_extra records by ad_report_id " + num);
                Cursor query = this.c.getReadableDatabase().query("ad_report_extra", null, "ad_report_id = ?", new String[]{String.valueOf(num)}, null, null, null);
                try {
                    Map<String, AdReportExtra> hashMap;
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, count + " ad_report_extra for ad_report_id " + num);
                    if (count > 0) {
                        hashMap = new HashMap();
                        while (query.moveToNext()) {
                            cb b = b();
                            b(b, query);
                            hashMap.put(b.b, b);
                        }
                    } else {
                        hashMap = null;
                    }
                    if (query == null) {
                        return hashMap;
                    }
                    query.close();
                    return hashMap;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        protected final String e_() {
            return "ad_report_extra";
        }

        final AdReportExtra b() {
            return (AdReportExtra) this.a.get();
        }

        protected static AdReportExtra[] a(int i) {
            return new AdReportExtra[i];
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
    AdReportExtra() {
    }

    protected final String b() {
        return "ad_report_extra";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("ad_report_id", this.a);
        }
        contentValues.put(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, this.b);
        contentValues.put("value", this.c);
        return contentValues;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "ad_report_id", this.a, false);
        cb.a(p, UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, this.b, false);
        cb.a(p, "value", this.c, false);
        return p;
    }
}
