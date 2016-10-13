package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.exoplayer.chunk.FormatEvaluator.AdaptiveEvaluator;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking;
import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class EventTracking extends cb<Integer> {
    String a;
    a b;
    String c;
    @Inject
    public Factory d;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.cb.a<EventTracking, Integer> {
        @Inject
        public Provider<EventTracking> a;

        protected final /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            EventTracking eventTracking = (EventTracking) cbVar;
            eventTracking.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            eventTracking.a = bs.f(cursor, "ad_id");
            eventTracking.b = (a) bs.a(cursor, NotificationCompatApi21.CATEGORY_EVENT, a.class);
            eventTracking.c = bs.f(cursor, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
            return eventTracking;
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new EventTracking[i];
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

        final Map<a, List<EventTracking>> a(String str, ThirdPartyAdTracking thirdPartyAdTracking) {
            if (thirdPartyAdTracking == null) {
                return null;
            }
            Map<a, List<EventTracking>> hashMap = new HashMap();
            a(str, hashMap, a.error, thirdPartyAdTracking.g());
            a(str, hashMap, a.mute, thirdPartyAdTracking.h());
            PlayCheckpoint[] j = thirdPartyAdTracking.j();
            if (j != null && j.length > 0) {
                for (PlayCheckpoint playCheckpoint : j) {
                    Float f = playCheckpoint.a;
                    if (f != null) {
                        a aVar;
                        float floatValue = f.floatValue();
                        if (floatValue == 0.0f) {
                            aVar = a.play_percentage_0;
                        } else if (((double) floatValue) == 0.25d) {
                            aVar = a.play_percentage_25;
                        } else if (((double) floatValue) == 0.5d) {
                            aVar = a.play_percentage_50;
                        } else if (((double) floatValue) == 0.75d) {
                            aVar = a.play_percentage_75;
                        } else if (floatValue == DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                            aVar = a.play_percentage_100;
                        } else {
                            Logger.w(Logger.DATABASE_TAG, "invalid play percent: " + floatValue);
                            aVar = null;
                        }
                        if (aVar != null) {
                            a(str, hashMap, aVar, playCheckpoint.b);
                        }
                    }
                }
            }
            a(str, hashMap, a.postroll_click, thirdPartyAdTracking.d());
            a(str, hashMap, a.postroll_view, thirdPartyAdTracking.k());
            a(str, hashMap, a.video_click, thirdPartyAdTracking.e());
            a(str, hashMap, a.video_close, thirdPartyAdTracking.f());
            a(str, hashMap, a.video_pause, thirdPartyAdTracking.i());
            a(str, hashMap, a.video_resume, thirdPartyAdTracking.l());
            a(str, hashMap, a.unmute, thirdPartyAdTracking.m());
            return hashMap;
        }

        private void a(String str, Map<a, List<EventTracking>> map, a aVar, String[] strArr) {
            if (strArr != null && strArr.length > 0) {
                List list;
                if (strArr == null || strArr.length <= 0) {
                    list = null;
                } else {
                    List arrayList = new ArrayList();
                    for (String str2 : strArr) {
                        Object obj;
                        if (aVar == null || str2 == null) {
                            obj = null;
                        } else {
                            obj = (EventTracking) this.a.get();
                            obj.a = str;
                            obj.b = aVar;
                            obj.c = str2;
                        }
                        if (obj != null) {
                            arrayList.add(obj);
                        }
                    }
                    list = arrayList;
                }
                if (list == null || list.isEmpty()) {
                    list = null;
                }
                if (list != null && !list.isEmpty()) {
                    map.put(aVar, list);
                }
            }
        }

        final void a(String str) {
            Logger.v(Logger.DATABASE_TAG, "deleted " + this.c.getWritableDatabase().delete("event_tracking", "ad_id = ?", new String[]{str}) + " expired event_tracking records for adId: " + str);
        }

        static void a(Map<a, List<EventTracking>> map) {
            if (map != null) {
                for (List<EventTracking> list : map.values()) {
                    if (list != null) {
                        for (EventTracking r : list) {
                            r.r();
                        }
                    }
                }
            }
        }

        final Map<a, List<EventTracking>> b(String str) {
            Throwable th;
            Cursor cursor = null;
            if (str == null) {
                Logger.w(Logger.DATABASE_TAG, "failed to fetch event_tracking records by ad_id: " + str);
                return null;
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching event_tracking records by ad_id: " + str);
                Cursor query = this.c.getReadableDatabase().query("event_tracking", null, "ad_id = ?", new String[]{str}, null, null, null);
                try {
                    Map<a, List<EventTracking>> map;
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, count + " event_tracking for ad_id: " + str);
                    if (count > 0) {
                        Map<a, List<EventTracking>> hashMap = new HashMap();
                        while (query.moveToNext()) {
                            cb b = b();
                            b(b, query);
                            if (b != null) {
                                a aVar = b.b;
                                List list = (List) hashMap.get(aVar);
                                if (list == null) {
                                    list = new ArrayList();
                                    hashMap.put(aVar, list);
                                }
                                list.add(b);
                            }
                        }
                        map = hashMap;
                    } else {
                        map = null;
                    }
                    if (query == null) {
                        return map;
                    }
                    query.close();
                    return map;
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
            return "event_tracking";
        }

        private EventTracking b() {
            return (EventTracking) this.a.get();
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

    public enum a {
        error,
        mute,
        play_percentage_0(0.0f),
        play_percentage_25(0.25f),
        play_percentage_50(0.5f),
        play_percentage_75(AdaptiveEvaluator.DEFAULT_BANDWIDTH_FRACTION),
        play_percentage_80(DefaultLoadControl.DEFAULT_HIGH_BUFFER_LOAD),
        play_percentage_100(0.99f),
        postroll_click,
        postroll_view,
        unmute,
        video_click,
        video_close,
        video_pause,
        video_resume;
        
        public final float p;

        private a(float f) {
            this.p = f;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cb.a a_() {
        return this.d;
    }

    @Inject
    EventTracking() {
    }

    protected final String b() {
        return "event_tracking";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, (Integer) this.s);
        contentValues.put("ad_id", this.a);
        contentValues.put(NotificationCompatApi21.CATEGORY_EVENT, this.b.toString());
        contentValues.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.c);
        return contentValues;
    }

    public final StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "ad_id", this.a, false);
        cb.a(p, NotificationCompatApi21.CATEGORY_EVENT, this.b, false);
        cb.a(p, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.c, false);
        return p;
    }
}
