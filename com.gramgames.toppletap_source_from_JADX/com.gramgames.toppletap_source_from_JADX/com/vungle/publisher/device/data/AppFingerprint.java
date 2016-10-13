package com.vungle.publisher.device.data;

import android.content.Context;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.publisher.ek;
import com.vungle.publisher.protocol.message.BaseJsonObject;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class AppFingerprint extends BaseJsonObject {
    JSONObject a;
    long b;
    String c;
    String d;
    boolean e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public ek a;
        @Inject
        public Context b;
        @Inject
        public Provider<AppFingerprint> c;

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

    public final /* synthetic */ Object b() throws JSONException {
        return a();
    }

    @Inject
    AppFingerprint() {
    }

    public final JSONObject a() throws JSONException {
        JSONObject a = super.a();
        a.put("last_polled", this.b);
        a.put("ifa", this.c);
        a.put("isu", this.d);
        a.put("app_store_ids", this.a);
        a.put("is_tracking_enabled", this.e);
        a.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_PLATFORM_KEY, "android");
        return a;
    }
}
