package com.unity3d.player;

import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

abstract class b implements Callback {
    private final Activity a;
    private final int b;
    private SurfaceView c;

    class 1 implements Runnable {
        final /* synthetic */ b a;

        1(b bVar) {
            this.a = bVar;
        }

        public final void run() {
            if (this.a.c == null) {
                this.a.c = new SurfaceView(t.a.a());
                this.a.c.getHolder().setType(this.a.b);
                this.a.c.getHolder().addCallback(this.a);
                t.a.a(this.a.c);
                this.a.c.setVisibility(0);
            }
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ b a;

        2(b bVar) {
            this.a = bVar;
        }

        public final void run() {
            if (this.a.c != null) {
                t.a.b(this.a.c);
            }
            this.a.c = null;
        }
    }

    b(int i) {
        this.a = (Activity) t.a.a();
        this.b = 3;
    }

    final void a() {
        this.a.runOnUiThread(new 1(this));
    }

    final void b() {
        this.a.runOnUiThread(new 2(this));
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
