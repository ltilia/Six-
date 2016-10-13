package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.db.model.EventTracking.a;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class EventTrackingHttpLogEntry extends cb<Integer> {
    public String a;
    public String b;
    public a c;
    public long d;
    public Integer e;
    public Long f;
    public String g;
    @Inject
    public Factory h;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends cb.a<EventTrackingHttpLogEntry, Integer> {
        @Inject
        public Provider<EventTrackingHttpLogEntry> a;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            EventTrackingHttpLogEntry eventTrackingHttpLogEntry = (EventTrackingHttpLogEntry) cbVar;
            eventTrackingHttpLogEntry.a = bs.f(cursor, "ad_id");
            eventTrackingHttpLogEntry.b = bs.f(cursor, "delivery_id");
            eventTrackingHttpLogEntry.c = (a) bs.a(cursor, NotificationCompatApi21.CATEGORY_EVENT, a.class);
            eventTrackingHttpLogEntry.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            eventTrackingHttpLogEntry.d = bs.e(cursor, "insert_timestamp_millis").longValue();
            eventTrackingHttpLogEntry.e = bs.d(cursor, "response_code");
            eventTrackingHttpLogEntry.f = bs.e(cursor, "response_timestamp_millis");
            eventTrackingHttpLogEntry.g = bs.f(cursor, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
            return eventTrackingHttpLogEntry;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new EventTrackingHttpLogEntry[i];
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

        protected final String e_() {
            return "event_tracking_http_log";
        }

        public final EventTrackingHttpLogEntry b() {
            return (EventTrackingHttpLogEntry) this.a.get();
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

    protected final /* bridge */ /* synthetic */ cb.a a_() {
        return this.h;
    }

    @Inject
    EventTrackingHttpLogEntry() {
    }

    protected final String b() {
        return "event_tracking_http_log";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, (Integer) s());
            long currentTimeMillis = System.currentTimeMillis();
            this.d = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
        }
        contentValues.put("ad_id", this.a);
        contentValues.put("delivery_id", this.b);
        contentValues.put(NotificationCompatApi21.CATEGORY_EVENT, this.c.toString());
        contentValues.put("response_code", this.e);
        contentValues.put("response_timestamp_millis", this.f);
        contentValues.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.g);
        return contentValues;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "ad_id", this.a, false);
        cb.a(p, "delivery_id", this.b, false);
        cb.a(p, NotificationCompatApi21.CATEGORY_EVENT, this.c, false);
        cb.a(p, "response_code", this.e, false);
        cb.a(p, "response_timestamp_millis", this.f, false);
        cb.a(p, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.g, false);
        cb.a(p, "insert_timestamp_millis", Long.valueOf(this.d), false);
        return p;
    }
}
