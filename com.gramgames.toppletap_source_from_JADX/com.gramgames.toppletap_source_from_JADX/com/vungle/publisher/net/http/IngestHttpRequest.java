package com.vungle.publisher.net.http;

import com.vungle.publisher.ek;
import com.vungle.publisher.inject.annotations.IngestBaseUrl;
import com.vungle.publisher.net.http.HttpRequest.a;
import dagger.MembersInjector;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: vungle */
public abstract class IngestHttpRequest extends HttpRequest {

    /* compiled from: vungle */
    public static abstract class Factory<T extends IngestHttpRequest> extends com.vungle.publisher.net.http.HttpRequest.Factory<T> {
        @Inject
        @IngestBaseUrl
        public String c;

        protected abstract String a();

        protected final /* synthetic */ HttpRequest c() {
            return d();
        }

        protected Factory() {
        }

        protected final T d() {
            IngestHttpRequest ingestHttpRequest = (IngestHttpRequest) super.c();
            ingestHttpRequest.a("Content-Encoding", "gzip");
            ingestHttpRequest.a("Content-Type", WebRequest.CONTENT_TYPE_JSON);
            ingestHttpRequest.b = this.c + a();
            return ingestHttpRequest;
        }
    }

    /* compiled from: vungle */
    public final class Factory_MembersInjector<T extends IngestHttpRequest> implements MembersInjector<Factory<T>> {
        static final /* synthetic */ boolean a;
        private final Provider<ek> b;
        private final Provider<String> c;

        static {
            a = !Factory_MembersInjector.class.desiredAssertionStatus();
        }

        public final /* synthetic */ void injectMembers(Object obj) {
            Factory factory = (Factory) obj;
            if (factory == null) {
                throw new NullPointerException("Cannot inject members into a null reference");
            }
            factory.b = (ek) this.b.get();
            factory.c = (String) this.c.get();
        }
    }

    protected final a b() {
        return a.POST;
    }
}
