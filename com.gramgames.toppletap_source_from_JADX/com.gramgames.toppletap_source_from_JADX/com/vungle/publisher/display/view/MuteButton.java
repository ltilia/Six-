package com.vungle.publisher.display.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import com.vungle.log.Logger;
import com.vungle.publisher.at;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.dw;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.util.ViewUtils;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import gs.gram.mopub.BuildConfig;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class MuteButton extends dw {
    boolean a;
    AudioHelper b;
    EventBus c;
    private Bitmap d;
    private Bitmap e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Context a;
        @Inject
        public ViewUtils b;
        @Inject
        public AudioHelper c;
        @Inject
        public EventBus d;

        class 1 implements OnClickListener {
            final /* synthetic */ MuteButton a;
            final /* synthetic */ Factory b;

            1(Factory factory, MuteButton muteButton) {
                this.b = factory;
                this.a = muteButton;
            }

            public final void onClick(View view) {
                Logger.d(Logger.AD_TAG, (this.a.a() ? BuildConfig.FLAVOR : "un") + "mute clicked");
                MuteButton muteButton = this.a;
                boolean z = !muteButton.a();
                muteButton.setAndCacheSoundEnabled(z);
                if (z && muteButton.b.b() == 0) {
                    muteButton.setVolume((int) (0.4f * ((float) muteButton.b.a())));
                }
                muteButton.a(z);
            }
        }

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

    private MuteButton(Context context) {
        super(context);
    }

    final boolean a() {
        return this.a && this.b.b() > 0;
    }

    final void setAndCacheSoundEnabled(boolean isSoundEnabled) {
        this.a = isSoundEnabled;
        setSoundEnabled(isSoundEnabled);
    }

    final void setSoundEnabled(boolean isSoundEnabled) {
        this.b.a(isSoundEnabled);
        b();
    }

    final void b() {
        setImageBitmap(a() ? this.e : this.d);
    }

    final void setVolume(int volume) {
        this.b.a.setStreamVolume(3, volume, 0);
        b();
    }

    final void a(boolean z) {
        if (z) {
            this.c.a(new at(false));
        } else {
            this.c.a(new at(true));
        }
    }
}
