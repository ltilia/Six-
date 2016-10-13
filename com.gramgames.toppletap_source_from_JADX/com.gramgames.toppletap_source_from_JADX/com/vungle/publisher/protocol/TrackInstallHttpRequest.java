package com.vungle.publisher.protocol;

import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.internal.ServerProtocol;
import com.vungle.publisher.ek;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class TrackInstallHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<TrackInstallHttpRequest> {
        @Inject
        public ek g;
        @Inject
        public com.vungle.publisher.protocol.message.RequestLocalAd.Factory h;

        public final /* synthetic */ ProtocolHttpRequest a() {
            return d();
        }

        protected final /* synthetic */ HttpRequest b() {
            return new TrackInstallHttpRequest();
        }

        public final /* synthetic */ HttpRequest c() {
            return d();
        }

        @Inject
        Factory() {
        }

        public final TrackInstallHttpRequest d() {
            TrackInstallHttpRequest trackInstallHttpRequest = (TrackInstallHttpRequest) super.a();
            Builder appendQueryParameter = Uri.parse(this.d + "new").buildUpon().appendQueryParameter(ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID, this.c.b());
            String a = this.g.a();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("ifa", a);
            }
            a = this.g.c();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("isu", a);
            }
            a = this.g.j();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("mac", a);
            }
            trackInstallHttpRequest.b = appendQueryParameter.toString();
            return trackInstallHttpRequest;
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

    @Inject
    TrackInstallHttpRequest() {
    }

    protected final b a() {
        return b.trackInstall;
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.POST;
    }
}
