package com.vungle.publisher.display.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vungle.publisher.ah;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.util.ViewUtils;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class PrivacyButton extends LinearLayout implements OnClickListener {
    private EventBus a;
    private boolean b;
    private TextView c;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public ViewUtils a;
        @Inject
        public EventBus b;

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

    public PrivacyButton(Context context) {
        super(context);
        this.b = false;
        setOnClickListener(this);
    }

    public final void onClick(View view) {
        if (this.b) {
            this.a.a(new ah());
            return;
        }
        this.b = true;
        setBackgroundColor(Color.parseColor("#000000"));
        this.c.setVisibility(0);
    }
}
