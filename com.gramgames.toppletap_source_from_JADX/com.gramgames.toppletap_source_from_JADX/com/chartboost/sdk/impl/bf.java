package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;

public class bf extends FrameLayout {
    private View a;
    private boolean b;

    public interface a {
        void a();

        void a(int i);

        void a(int i, int i2);

        void a(OnCompletionListener onCompletionListener);

        void a(OnErrorListener onErrorListener);

        void a(OnPreparedListener onPreparedListener);

        void a(Uri uri);

        void b();

        int c();

        int d();

        boolean e();
    }

    public bf(Context context) {
        super(context);
        c();
    }

    private void c() {
        this.b = b();
        if (!Chartboost.getImpressionsUseActivities() && (getContext() instanceof Activity)) {
            this.b = a((Activity) getContext());
        }
        CBLogging.e("VideoInit", "Choosing " + (this.b ? "texture" : "surface") + " solution for video playback");
        if (this.b) {
            this.a = new be(getContext());
        } else {
            this.a = new bd(getContext());
        }
        addView(this.a, new LayoutParams(-1, -1));
        if (!this.b) {
            ((SurfaceView) this.a).setZOrderMediaOverlay(true);
        }
    }

    public a a() {
        return (a) this.a;
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        a().a(w, h);
    }

    public static boolean a(Activity activity) {
        if (b()) {
            if (Chartboost.getImpressionsUseActivities()) {
                return true;
            }
            try {
                return activity.getWindow().getDecorView().isHardwareAccelerated();
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static boolean b() {
        return VERSION.SDK_INT >= 14;
    }
}
