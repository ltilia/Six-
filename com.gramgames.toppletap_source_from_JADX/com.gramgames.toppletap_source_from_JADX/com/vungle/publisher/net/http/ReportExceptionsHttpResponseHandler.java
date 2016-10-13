package com.vungle.publisher.net.http;

import com.vungle.publisher.db.model.LoggedException;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public class ReportExceptionsHttpResponseHandler extends FireAndForgetHttpResponseHandler {
    List<LoggedException> a;
    @Inject
    public com.vungle.publisher.db.model.LoggedException.Factory b;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<ReportExceptionsHttpResponseHandler> a;

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

    @Inject
    ReportExceptionsHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        super.a(httpTransaction, httpResponse);
        this.b.a(this.a);
    }
}
