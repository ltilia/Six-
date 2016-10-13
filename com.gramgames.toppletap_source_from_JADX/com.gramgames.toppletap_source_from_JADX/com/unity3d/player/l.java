package com.unity3d.player;

import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class l implements h {
    private Choreographer a;
    private long b;
    private FrameCallback c;
    private boolean d;
    private boolean e;
    private Lock f;

    class 1 implements FrameCallback {
        final /* synthetic */ UnityPlayer a;
        final /* synthetic */ l b;

        1(l lVar, UnityPlayer unityPlayer) {
            this.b = lVar;
            this.a = unityPlayer;
        }

        public final void doFrame(long j) {
            UnityPlayer.lockNativeAccess();
            if (v.c()) {
                this.a.nativeAddVSyncTime(j);
            }
            UnityPlayer.unlockNativeAccess();
            this.b.f.lock();
            if (this.b.a == null || this.b.d) {
                this.b.e = false;
            } else {
                this.b.a.postFrameCallback(this.b.c);
                this.b.e = true;
            }
            this.b.f.unlock();
        }
    }

    public l() {
        this.a = null;
        this.b = 0;
        this.d = false;
        this.e = false;
        this.f = new ReentrantLock();
    }

    public final void a() {
        this.f.lock();
        if (this.a != null) {
            this.a.removeFrameCallback(this.c);
        }
        this.e = false;
        this.a = null;
        this.f.unlock();
    }

    public final void a(UnityPlayer unityPlayer) {
        this.f.lock();
        if (this.a == null) {
            this.a = Choreographer.getInstance();
            if (this.a != null) {
                m.Log(4, "Choreographer available: Enabling VSYNC timing");
                this.c = new 1(this, unityPlayer);
                this.a.postFrameCallback(this.c);
                this.e = true;
            }
        }
        this.f.unlock();
    }

    public final void b() {
        this.f.lock();
        this.d = true;
        this.f.unlock();
    }

    public final void c() {
        this.f.lock();
        if (this.d) {
            if (!(this.a == null || this.c == null || this.e)) {
                this.a.postFrameCallback(this.c);
                this.e = true;
            }
            this.d = false;
        }
        this.f.unlock();
    }
}
