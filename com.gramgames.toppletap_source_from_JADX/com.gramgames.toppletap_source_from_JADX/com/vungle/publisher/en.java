package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.event.ClientEventListenerAdapter.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class en implements MembersInjector<SdkConfig> {
    static final /* synthetic */ boolean a;
    private final Provider<Factory> b;
    private final Provider<Context> c;
    private final Provider<SharedPreferences> d;

    static {
        a = !en.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        SdkConfig sdkConfig = (SdkConfig) obj;
        if (sdkConfig == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        sdkConfig.h = (Factory) this.b.get();
        sdkConfig.i = (Context) this.c.get();
        sdkConfig.j = (SharedPreferences) this.d.get();
    }

    private en(Provider<Factory> provider, Provider<Context> provider2, Provider<SharedPreferences> provider3) {
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

    public static MembersInjector<SdkConfig> a(Provider<Factory> provider, Provider<Context> provider2, Provider<SharedPreferences> provider3) {
        return new en(provider, provider2, provider3);
    }
}
