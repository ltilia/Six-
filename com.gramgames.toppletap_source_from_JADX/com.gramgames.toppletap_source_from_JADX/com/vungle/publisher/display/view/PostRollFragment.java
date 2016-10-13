package com.vungle.publisher.display.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vungle.log.Logger;
import com.vungle.publisher.ac;
import com.vungle.publisher.display.controller.AdWebChromeClient;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class PostRollFragment extends WebViewFragment {
    @Inject
    public EventBus a;
    @Inject
    public AdWebChromeClient b;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<PostRollFragment> a;

        @Inject
        Factory() {
        }

        public static PostRollFragment a(PostRollFragment postRollFragment, String str) {
            postRollFragment.c = str;
            return postRollFragment;
        }

        public static PostRollFragment a(Activity activity) {
            return (PostRollFragment) activity.getFragmentManager().findFragmentByTag("postRollFragment");
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

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onCreate", e);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View onCreateView;
        Throwable e;
        try {
            onCreateView = super.onCreateView(inflater, container, savedInstanceState);
            try {
                this.d.setWebChromeClient(this.b);
            } catch (Exception e2) {
                e = e2;
                Logger.w(Logger.AD_TAG, "exception in onCreateView", e);
                return onCreateView;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            onCreateView = null;
            e = th;
            Logger.w(Logger.AD_TAG, "exception in onCreateView", e);
            return onCreateView;
        }
        return onCreateView;
    }

    public final void a() {
        try {
            this.a.a(new ac());
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onBackPressed", e);
        }
    }

    public final String b() {
        return "postRollFragment";
    }
}
