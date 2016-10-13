package com.vungle.publisher;

import android.media.AudioManager;
import com.vungle.publisher.device.AudioHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dk implements MembersInjector<AudioHelper> {
    static final /* synthetic */ boolean a;
    private final Provider<AudioManager> b;

    static {
        a = !dk.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AudioHelper audioHelper = (AudioHelper) obj;
        if (audioHelper == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        audioHelper.a = (AudioManager) this.b.get();
    }

    private dk(Provider<AudioManager> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AudioHelper> a(Provider<AudioManager> provider) {
        return new dk(provider);
    }
}
