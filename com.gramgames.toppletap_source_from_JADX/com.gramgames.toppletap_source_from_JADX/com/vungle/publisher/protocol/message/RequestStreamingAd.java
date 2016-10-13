package com.vungle.publisher.protocol.message;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.publisher.ad.SafeBundleAdConfig;
import com.vungle.publisher.fm;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestStreamingAd extends RequestAd<RequestStreamingAd> {
    ExtraInfo g;
    String h;
    String i;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.message.RequestAd.Factory<RequestStreamingAd> {
        @Inject
        public com.vungle.publisher.protocol.message.ExtraInfo.Factory e;

        protected final /* synthetic */ Object a() {
            return new RequestStreamingAd();
        }

        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new RequestStreamingAd[i];
        }

        @Inject
        Factory() {
        }

        public final RequestStreamingAd a(String str, SafeBundleAdConfig safeBundleAdConfig) {
            RequestStreamingAd requestStreamingAd = (RequestStreamingAd) super.b();
            requestStreamingAd.g = com.vungle.publisher.protocol.message.ExtraInfo.Factory.a(safeBundleAdConfig.getExtras());
            requestStreamingAd.h = str;
            requestStreamingAd.i = safeBundleAdConfig.getPlacement();
            return requestStreamingAd;
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

    public final /* synthetic */ Object b() throws JSONException {
        return a();
    }

    @Inject
    RequestStreamingAd() {
    }

    public final JSONObject a() throws JSONException {
        JSONObject a = super.a();
        a.putOpt(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY, this.h);
        a.putOpt("extraInfo", fm.a(this.g));
        a.putOpt("placement", this.i);
        return a;
    }
}
