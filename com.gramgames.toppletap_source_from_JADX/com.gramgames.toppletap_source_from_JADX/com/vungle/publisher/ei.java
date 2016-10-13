package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ei implements MembersInjector<AndroidDevice> {
    static final /* synthetic */ boolean a;
    private final Provider<AudioHelper> b;
    private final Provider<WindowManager> c;
    private final Provider<Context> d;
    private final Provider<EventBus> e;
    private final Provider<SharedPreferences> f;
    private final Provider<DeviceIdStrategy> g;

    static {
        a = !ei.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AndroidDevice androidDevice = (AndroidDevice) obj;
        if (androidDevice == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        androidDevice.g = (AudioHelper) this.b.get();
        androidDevice.h = (WindowManager) this.c.get();
        androidDevice.i = (Context) this.d.get();
        androidDevice.j = (EventBus) this.e.get();
        androidDevice.k = (SharedPreferences) this.f.get();
        androidDevice.l = (DeviceIdStrategy) this.g.get();
    }

    private ei(Provider<AudioHelper> provider, Provider<WindowManager> provider2, Provider<Context> provider3, Provider<EventBus> provider4, Provider<SharedPreferences> provider5, Provider<DeviceIdStrategy> provider6) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            if (a || provider6 != null) {
                                this.g = provider6;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AndroidDevice> a(Provider<AudioHelper> provider, Provider<WindowManager> provider2, Provider<Context> provider3, Provider<EventBus> provider4, Provider<SharedPreferences> provider5, Provider<DeviceIdStrategy> provider6) {
        return new ei(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
