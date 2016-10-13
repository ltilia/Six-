package com.vungle.publisher.protocol.message;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.fm;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestLocalAd extends RequestAd<RequestLocalAd> {
    public List<EventTrackingHttpLogEntry> g;
    HttpLogEntry[] h;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.message.RequestAd.Factory<RequestLocalAd> {
        @Inject
        public com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory e;
        @Inject
        public Factory f;
        @Inject
        public Lazy<SdkState> g;

        protected final /* synthetic */ Object a() {
            return new RequestLocalAd();
        }

        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new RequestLocalAd[i];
        }

        public final /* synthetic */ RequestAd b() {
            return c();
        }

        @Inject
        Factory() {
        }

        public final RequestLocalAd c() {
            HttpLogEntry[] httpLogEntryArr = null;
            RequestLocalAd requestLocalAd = (RequestLocalAd) super.b();
            List<EventTrackingHttpLogEntry> d = this.e.d();
            requestLocalAd.g = d;
            int size = d == null ? 0 : d.size();
            if (size > 0) {
                Logger.d(Logger.REPORT_TAG, "sending " + size + " event_tracking_http_log records");
                HttpLogEntry[] httpLogEntryArr2 = new HttpLogEntry[size];
                size = 0;
                for (EventTrackingHttpLogEntry eventTrackingHttpLogEntry : d) {
                    HttpLogEntry httpLogEntry;
                    Logger.v(Logger.REPORT_TAG, "sending " + eventTrackingHttpLogEntry.x());
                    int i = size + 1;
                    if (eventTrackingHttpLogEntry != null) {
                        HttpLogEntry httpLogEntry2 = new HttpLogEntry();
                        httpLogEntry2.a = eventTrackingHttpLogEntry.a;
                        httpLogEntry2.b = eventTrackingHttpLogEntry.b;
                        httpLogEntry2.c = Long.valueOf(eventTrackingHttpLogEntry.d);
                        httpLogEntry2.d = String.valueOf(eventTrackingHttpLogEntry.c);
                        httpLogEntry2.e = eventTrackingHttpLogEntry.e;
                        httpLogEntry2.f = eventTrackingHttpLogEntry.f;
                        httpLogEntry2.g = eventTrackingHttpLogEntry.g;
                        httpLogEntry = httpLogEntry2;
                    } else {
                        httpLogEntry = null;
                    }
                    httpLogEntryArr2[size] = httpLogEntry;
                    size = i;
                }
                httpLogEntryArr = httpLogEntryArr2;
            }
            requestLocalAd.h = httpLogEntryArr;
            return requestLocalAd;
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
    public static class HttpLogEntry extends BaseJsonObject {
        String a;
        String b;
        Long c;
        String d;
        Integer e;
        Long f;
        String g;

        @Singleton
        /* compiled from: vungle */
        public static class Factory extends MessageFactory<HttpLogEntry> {
            protected final /* synthetic */ Object a() {
                return new HttpLogEntry();
            }

            protected final /* bridge */ /* synthetic */ Object[] a(int i) {
                return new HttpLogEntry[i];
            }

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

        HttpLogEntry() {
        }

        public final /* synthetic */ Object b() throws JSONException {
            return a();
        }

        public final JSONObject a() throws JSONException {
            JSONObject a = super.a();
            a.putOpt(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY, this.a);
            a.putOpt("deliveryId", this.b);
            a.putOpt("deviceMillis", this.c);
            a.putOpt(NotificationCompatApi21.CATEGORY_EVENT, this.d);
            a.putOpt("responseCode", this.e);
            a.putOpt("responseMillis", this.f);
            a.putOpt(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.g);
            return a;
        }
    }

    public final /* synthetic */ Object b() throws JSONException {
        return a();
    }

    @Inject
    RequestLocalAd() {
    }

    public final JSONObject a() throws JSONException {
        JSONObject a = super.a();
        a.putOpt("httpLog", fm.a(this.h));
        return a;
    }
}
