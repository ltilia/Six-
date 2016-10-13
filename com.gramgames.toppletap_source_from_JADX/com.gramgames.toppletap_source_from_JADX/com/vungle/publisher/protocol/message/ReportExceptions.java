package com.vungle.publisher.protocol.message;

import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.ek;
import com.vungle.publisher.em;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ReportExceptions extends BaseJsonArray {
    public List<LoggedException> a;
    @Inject
    public ek b;
    @Inject
    public em c;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<ReportExceptions> a;

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
    ReportExceptions() {
    }

    public final JSONArray a() throws JSONException {
        JSONArray a = super.a();
        for (LoggedException loggedException : this.a) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            String[] strArr = loggedException.b;
            Object obj = null;
            if (strArr != null) {
                obj = new JSONArray(Arrays.asList(strArr));
            }
            jSONObject.putOpt("code", Integer.valueOf(loggedException.c));
            jSONObject.putOpt("timestamp", Long.valueOf(loggedException.a));
            jSONObject.putOpt("stack_trace", obj);
            jSONObject.putOpt("tag", loggedException.d);
            jSONObject.putOpt("log_message", loggedException.e);
            jSONObject.putOpt("exception_class", loggedException.f);
            jSONObject.putOpt(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_PLATFORM_KEY, "android");
            jSONObject.putOpt("model", this.b.m());
            jSONObject.putOpt("os_version", loggedException.g);
            jSONObject.putOpt("sdk_version", loggedException.h);
            jSONObject.putOpt(ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID, this.c.b());
            jSONObject2.putOpt("play_services_version", loggedException.i);
            jSONObject.putOpt("platform_specific", jSONObject2);
            a.put(jSONObject);
        }
        return a;
    }
}
