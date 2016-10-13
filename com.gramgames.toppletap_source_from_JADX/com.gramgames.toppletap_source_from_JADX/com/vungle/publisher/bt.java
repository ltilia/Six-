package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.db.DatabaseBroadcastReceiver;
import com.vungle.publisher.db.DatabaseHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bt implements MembersInjector<DatabaseBroadcastReceiver> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<DatabaseHelper> c;
    private final Provider<em> d;

    static {
        a = !bt.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        DatabaseBroadcastReceiver databaseBroadcastReceiver = (DatabaseBroadcastReceiver) obj;
        if (databaseBroadcastReceiver == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        databaseBroadcastReceiver.a = (Context) this.b.get();
        databaseBroadcastReceiver.b = (DatabaseHelper) this.c.get();
        databaseBroadcastReceiver.c = (em) this.d.get();
    }

    private bt(Provider<Context> provider, Provider<DatabaseHelper> provider2, Provider<em> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<DatabaseBroadcastReceiver> a(Provider<Context> provider, Provider<DatabaseHelper> provider2, Provider<em> provider3) {
        return new bt(provider, provider2, provider3);
    }
}
