package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fq implements MembersInjector<fp> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;

    static {
        a = !fq.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        fp fpVar = (fp) obj;
        if (fpVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fpVar.c = (Context) this.b.get();
    }

    private fq(Provider<Context> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<fp> a(Provider<Context> provider) {
        return new fq(provider);
    }
}
