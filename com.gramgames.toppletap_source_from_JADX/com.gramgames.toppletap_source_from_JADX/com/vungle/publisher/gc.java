package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.net.http.DownloadHttpGateway;
import com.vungle.publisher.net.http.DownloadHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gc implements MembersInjector<DownloadHttpGateway> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<Factory> c;
    private final Provider<ScheduledPriorityExecutor> d;
    private final Provider<DownloadHttpTransactionFactory> e;

    static {
        a = !gc.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        DownloadHttpGateway downloadHttpGateway = (DownloadHttpGateway) obj;
        if (downloadHttpGateway == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        downloadHttpGateway.c = (Context) this.b.get();
        downloadHttpGateway.d = (Factory) this.c.get();
        downloadHttpGateway.e = (ScheduledPriorityExecutor) this.d.get();
        downloadHttpGateway.a = (DownloadHttpTransactionFactory) this.e.get();
    }

    private gc(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<DownloadHttpTransactionFactory> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<DownloadHttpGateway> a(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<DownloadHttpTransactionFactory> provider4) {
        return new gc(provider, provider2, provider3, provider4);
    }
}
