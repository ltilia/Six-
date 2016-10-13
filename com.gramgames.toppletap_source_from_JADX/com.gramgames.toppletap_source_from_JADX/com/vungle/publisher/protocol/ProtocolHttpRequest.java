package com.vungle.publisher.protocol;

import android.os.Bundle;
import com.vungle.publisher.VunglePub;
import com.vungle.publisher.em;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.ft;
import com.vungle.publisher.inject.annotations.VungleBaseUrl;
import com.vungle.publisher.inject.annotations.WrapperFrameworkVersion;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.Factory;
import gs.gram.mopub.BuildConfig;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class ProtocolHttpRequest extends HttpRequest {

    public static abstract class a<T extends ProtocolHttpRequest> extends Factory<T> {
        @Inject
        public ft a;
        @Inject
        public em c;
        @VungleBaseUrl
        @Inject
        public String d;
        @Inject
        public WrapperFramework e;
        @WrapperFrameworkVersion
        @Inject
        public String f;
        private String g;

        protected a() {
        }

        protected /* synthetic */ HttpRequest c() {
            return a();
        }

        protected T a() {
            ProtocolHttpRequest protocolHttpRequest = (ProtocolHttpRequest) super.c();
            Bundle bundle = protocolHttpRequest.c;
            bundle.putString("X-VUNGLE-BUNDLE-ID", this.c.a());
            bundle.putString("X-VUNGLE-LANGUAGE", this.a.a());
            bundle.putString("X-VUNGLE-TIMEZONE", this.a.c());
            String str = "User-Agent";
            String str2 = this.g;
            if (str2 == null) {
                Object obj;
                StringBuilder stringBuilder = new StringBuilder(VunglePub.VERSION);
                WrapperFramework wrapperFramework = this.e;
                String str3 = this.f;
                if (wrapperFramework == null || wrapperFramework.equals(WrapperFramework.none)) {
                    obj = null;
                } else {
                    obj = 1;
                }
                Object obj2 = (str3 == null || BuildConfig.FLAVOR.equals(str3)) ? null : 1;
                if (!(obj == null && obj2 == null)) {
                    stringBuilder.append(';');
                    if (obj != null) {
                        stringBuilder.append(wrapperFramework);
                    }
                    if (obj2 != null) {
                        stringBuilder.append('/');
                        stringBuilder.append(str3);
                    }
                }
                str2 = stringBuilder.toString();
                this.g = str2;
            }
            bundle.putString(str, str2);
            if (ProtocolHttpRequest.a(protocolHttpRequest)) {
                bundle.putLong("X-VUNG-DATE", System.currentTimeMillis());
            }
            return protocolHttpRequest;
        }
    }

    static /* synthetic */ boolean a(ProtocolHttpRequest protocolHttpRequest) {
        return protocolHttpRequest.b != null && HttpRequest.a.matcher(protocolHttpRequest.b).find();
    }

    protected ProtocolHttpRequest() {
    }
}
