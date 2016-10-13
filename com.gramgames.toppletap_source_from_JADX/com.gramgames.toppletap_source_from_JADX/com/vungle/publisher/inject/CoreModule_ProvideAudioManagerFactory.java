package com.vungle.publisher.inject;

import android.content.Context;
import android.media.AudioManager;
import com.google.android.exoplayer.util.MimeTypes;
import com.vungle.log.Logger;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideAudioManagerFactory implements Factory<AudioManager> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<Context> c;

    static {
        a = !CoreModule_ProvideAudioManagerFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideAudioManagerFactory(fi module, Provider<Context> contextProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || contextProvider != null) {
                this.c = contextProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final AudioManager get() {
        AudioManager audioManager = (AudioManager) ((Context) this.c.get()).getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            Logger.d(Logger.DEVICE_TAG, "AudioManager not avaialble");
        }
        return (AudioManager) Preconditions.checkNotNull(audioManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AudioManager> create(fi module, Provider<Context> contextProvider) {
        return new CoreModule_ProvideAudioManagerFactory(module, contextProvider);
    }
}
