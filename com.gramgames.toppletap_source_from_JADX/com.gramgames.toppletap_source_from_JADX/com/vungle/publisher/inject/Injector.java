package com.vungle.publisher.inject;

import com.vungle.log.Logger;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.fi;
import com.vungle.publisher.fk;
import com.vungle.publisher.image.BitmapFactory;

/* compiled from: vungle */
public class Injector {
    private static final Injector e;
    public boolean a;
    public EndpointModule b;
    public fk c;
    public VungleMainComponent d;
    private fi f;

    static {
        e = new Injector();
    }

    public static Injector getInstance() {
        return e;
    }

    private Injector() {
    }

    public void setBitmapFactory(BitmapFactory bitmapFactory) {
        try {
            if (this.a) {
                Logger.d(Logger.INJECT_TAG, "bitmap factory in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting bitmap factory in injector " + bitmapFactory);
            fi a = a();
            if (a.g) {
                Logger.d(Logger.INJECT_TAG, "BitmapFactory in publisher module NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting BitmapFactory in publisher module: " + bitmapFactory);
            a.c = bitmapFactory;
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public void setWrapperFramework(WrapperFramework wrapperFramework) {
        try {
            if (this.a) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework in injector: " + wrapperFramework);
            fi a = a();
            if (a.g) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework in publisher module NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + wrapperFramework);
            a.e = wrapperFramework;
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public void setWrapperFrameworkVersion(String wrapperFrameworkVersion) {
        try {
            if (this.a) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework version in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework version in injector: " + wrapperFrameworkVersion);
            fi a = a();
            if (a.g) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework version in publisher module NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + wrapperFrameworkVersion);
            a.f = wrapperFrameworkVersion;
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public final fi a() {
        if (this.f == null) {
            this.f = new fi();
        }
        return this.f;
    }

    public Injector setEndpointModule(EndpointModule endpointModule) {
        this.b = endpointModule;
        this.a = false;
        return this;
    }

    public static VungleMainComponent b() {
        return getInstance().d;
    }
}
