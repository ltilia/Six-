package com.vungle.publisher.display.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.view.View.MeasureSpec;
import com.vungle.publisher.dw;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ProgressBar extends dw {
    private ShapeDrawable a;
    private int b;
    private int c;
    private int d;
    private int e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Context a;
        @Inject
        public DisplayUtils b;

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

    private ProgressBar(Context context) {
        super(context);
        this.a = new ShapeDrawable();
        this.b = -1;
        this.a.getPaint().setColor(-13659954);
    }

    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.draw(canvas);
    }

    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.b = MeasureSpec.getSize(widthMeasureSpec);
    }

    private void setProgressBarWidth(float percent) {
        this.a.setBounds(0, 0, (int) (((float) this.b) * percent), this.d);
    }

    public final void setMaxTimeMillis(int maxTimeMillis) {
        this.c = maxTimeMillis;
    }

    public final void setCurrentTimeMillis(int currentTimeMillis) {
        setProgressBarWidth(((float) currentTimeMillis) / ((float) this.c));
        invalidate();
    }

    public final int getProgressBarHeight() {
        return this.d;
    }

    public final int getId() {
        return this.e;
    }
}
