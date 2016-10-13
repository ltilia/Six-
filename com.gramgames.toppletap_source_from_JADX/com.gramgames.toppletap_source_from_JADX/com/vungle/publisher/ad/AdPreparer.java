package com.vungle.publisher.ad;

import com.vungle.log.Logger;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable.Factory;
import com.vungle.publisher.ad.prepare.PrepareViewableRunnable;
import com.vungle.publisher.ag;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.et;
import com.vungle.publisher.s;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdPreparer {
    @Inject
    public Factory a;
    @Inject
    public ViewablePreparationListener b;
    @Inject
    public ScheduledPriorityExecutor c;

    @Singleton
    /* compiled from: vungle */
    public static class ViewablePreparationListener extends et {
        @Inject
        public ScheduledPriorityExecutor a;
        @Inject
        public PrepareViewableRunnable.Factory b;
        @Inject
        public Lazy<AdPreparer> c;

        @Inject
        ViewablePreparationListener() {
        }

        public void onEvent(s downloadEvent) {
            this.a.a(this.b.a(downloadEvent.a, downloadEvent.b), b.prepareLocalViewable);
        }

        public void onEvent(ag prepareSuccessEvent) {
            ((AdPreparer) this.c.get()).a(prepareSuccessEvent.a);
        }
    }

    /* compiled from: vungle */
    public final class ViewablePreparationListener_Factory implements dagger.internal.Factory<ViewablePreparationListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<ViewablePreparationListener> b;

        static {
            a = !ViewablePreparationListener_Factory.class.desiredAssertionStatus();
        }

        public ViewablePreparationListener_Factory(MembersInjector<ViewablePreparationListener> viewablePreparationListenerMembersInjector) {
            if (a || viewablePreparationListenerMembersInjector != null) {
                this.b = viewablePreparationListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final ViewablePreparationListener get() {
            return (ViewablePreparationListener) MembersInjectors.injectMembers(this.b, new ViewablePreparationListener());
        }

        public static dagger.internal.Factory<ViewablePreparationListener> create(MembersInjector<ViewablePreparationListener> viewablePreparationListenerMembersInjector) {
            return new ViewablePreparationListener_Factory(viewablePreparationListenerMembersInjector);
        }
    }

    @Inject
    AdPreparer() {
    }

    public final void a(String str) {
        Logger.d(Logger.PREPARE_TAG, "prepare ad request: " + str);
        this.b.registerOnce();
        ScheduledPriorityExecutor scheduledPriorityExecutor = this.c;
        Runnable runnable = (PrepareAdRunnable) this.a.a.get();
        runnable.i = str;
        scheduledPriorityExecutor.a(runnable, b.prepareLocalAd);
    }
}
