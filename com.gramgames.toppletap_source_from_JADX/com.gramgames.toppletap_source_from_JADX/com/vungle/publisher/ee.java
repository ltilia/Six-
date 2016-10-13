package com.vungle.publisher;

import com.vungle.publisher.audio.VolumeChangeContentObserver;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.display.view.AlertDialogFactory;
import com.vungle.publisher.display.view.DisplayUtils;
import com.vungle.publisher.display.view.MuteButton;
import com.vungle.publisher.display.view.PrivacyButton;
import com.vungle.publisher.display.view.ProgressBar;
import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.display.view.VideoFragment.Factory;
import com.vungle.publisher.display.view.VideoFragment.VideoEventListener;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.image.BitmapFactory;
import com.vungle.publisher.util.ViewUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ee implements MembersInjector<VideoFragment> {
    static final /* synthetic */ boolean a;
    private final Provider<AlertDialogFactory> b;
    private final Provider<BitmapFactory> c;
    private final Provider<DisplayUtils> d;
    private final Provider<EventBus> e;
    private final Provider<Factory> f;
    private final Provider<PrivacyButton.Factory> g;
    private final Provider<ProgressBar.Factory> h;
    private final Provider<MuteButton.Factory> i;
    private final Provider<LoggedException.Factory> j;
    private final Provider<ek> k;
    private final Provider<ViewUtils> l;
    private final Provider<VolumeChangeContentObserver> m;
    private final Provider<VideoEventListener.Factory> n;
    private final Provider<AudioHelper> o;

    static {
        a = !ee.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        VideoFragment videoFragment = (VideoFragment) obj;
        if (videoFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        videoFragment.f = (AlertDialogFactory) this.b.get();
        videoFragment.g = (BitmapFactory) this.c.get();
        videoFragment.h = (DisplayUtils) this.d.get();
        videoFragment.i = (EventBus) this.e.get();
        videoFragment.j = (Factory) this.f.get();
        videoFragment.k = (PrivacyButton.Factory) this.g.get();
        videoFragment.l = (ProgressBar.Factory) this.h.get();
        videoFragment.m = (MuteButton.Factory) this.i.get();
        videoFragment.n = (LoggedException.Factory) this.j.get();
        videoFragment.o = (ek) this.k.get();
        videoFragment.p = (ViewUtils) this.l.get();
        videoFragment.q = (VolumeChangeContentObserver) this.m.get();
        videoFragment.r = (VideoEventListener.Factory) this.n.get();
        videoFragment.s = (AudioHelper) this.o.get();
    }

    private ee(Provider<AlertDialogFactory> provider, Provider<BitmapFactory> provider2, Provider<DisplayUtils> provider3, Provider<EventBus> provider4, Provider<Factory> provider5, Provider<PrivacyButton.Factory> provider6, Provider<ProgressBar.Factory> provider7, Provider<MuteButton.Factory> provider8, Provider<LoggedException.Factory> provider9, Provider<ek> provider10, Provider<ViewUtils> provider11, Provider<VolumeChangeContentObserver> provider12, Provider<VideoEventListener.Factory> provider13, Provider<AudioHelper> provider14) {
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
                                if (a || provider7 != null) {
                                    this.h = provider7;
                                    if (a || provider8 != null) {
                                        this.i = provider8;
                                        if (a || provider9 != null) {
                                            this.j = provider9;
                                            if (a || provider10 != null) {
                                                this.k = provider10;
                                                if (a || provider11 != null) {
                                                    this.l = provider11;
                                                    if (a || provider12 != null) {
                                                        this.m = provider12;
                                                        if (a || provider13 != null) {
                                                            this.n = provider13;
                                                            if (a || provider14 != null) {
                                                                this.o = provider14;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<VideoFragment> a(Provider<AlertDialogFactory> provider, Provider<BitmapFactory> provider2, Provider<DisplayUtils> provider3, Provider<EventBus> provider4, Provider<Factory> provider5, Provider<PrivacyButton.Factory> provider6, Provider<ProgressBar.Factory> provider7, Provider<MuteButton.Factory> provider8, Provider<LoggedException.Factory> provider9, Provider<ek> provider10, Provider<ViewUtils> provider11, Provider<VolumeChangeContentObserver> provider12, Provider<VideoEventListener.Factory> provider13, Provider<AudioHelper> provider14) {
        return new ee(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }
}
