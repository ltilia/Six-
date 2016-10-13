package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {
    private static int h;
    private static int i;
    private static int j;
    private static int k;
    private volatile Thread a;
    private volatile boolean b;
    private AudioTrack c;
    private boolean d;
    private ByteBuffer e;
    private byte[] f;
    private volatile a g;

    static {
        h = 0;
        i = 1;
        j = 2;
        k = 3;
    }

    public FMODAudioDevice() {
        this.a = null;
        this.b = false;
        this.c = null;
        this.d = false;
        this.e = null;
        this.f = null;
    }

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        if (this.c != null) {
            if (this.c.getState() == 1) {
                this.c.stop();
            }
            this.c.release();
            this.c = null;
        }
        this.e = null;
        this.f = null;
        this.d = false;
    }

    public synchronized void close() {
        stop();
    }

    native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.a != null && this.a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.b) {
            int i2;
            if (this.d || i <= 0) {
                i2 = i;
            } else {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(i);
                i2 = fmodGetInfo(j);
                if ((fmodGetInfo2 * i2) * 4 > round) {
                    round = (i2 * fmodGetInfo2) * 4;
                }
                this.c = new AudioTrack(3, fmodGetInfo, 3, 2, round, 1);
                this.d = this.c.getState() == 1;
                if (this.d) {
                    this.e = ByteBuffer.allocateDirect((fmodGetInfo2 * 2) * 2);
                    this.f = new byte[this.e.capacity()];
                    this.c.play();
                    i2 = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.c.getState() + ")");
                    releaseAudioTrack();
                    i2 = i - 1;
                }
            }
            if (!this.d) {
                i = i2;
            } else if (fmodGetInfo(k) == 1) {
                fmodProcess(this.e);
                this.e.get(this.f, 0, this.e.capacity());
                this.c.write(this.f, 0, this.e.capacity());
                this.e.position(0);
                i = i2;
            } else {
                releaseAudioTrack();
                i = i2;
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.a != null) {
            stop();
        }
        this.a = new Thread(this, "FMODAudioDevice");
        this.a.setPriority(10);
        this.b = true;
        this.a.start();
        if (this.g != null) {
            this.g.b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.g == null) {
            this.g = new a(this, i, i2);
            this.g.b();
        }
        return this.g.a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
        r1 = this;
        monitor-enter(r1);
    L_0x0001:
        r0 = r1.a;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x0013;
    L_0x0005:
        r0 = 0;
        r1.b = r0;	 Catch:{ all -> 0x001e }
        r0 = r1.a;	 Catch:{ InterruptedException -> 0x0011 }
        r0.join();	 Catch:{ InterruptedException -> 0x0011 }
        r0 = 0;
        r1.a = r0;	 Catch:{ InterruptedException -> 0x0011 }
        goto L_0x0001;
    L_0x0011:
        r0 = move-exception;
        goto L_0x0001;
    L_0x0013:
        r0 = r1.g;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x001c;
    L_0x0017:
        r0 = r1.g;	 Catch:{ all -> 0x001e }
        r0.c();	 Catch:{ all -> 0x001e }
    L_0x001c:
        monitor-exit(r1);
        return;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.FMODAudioDevice.stop():void");
    }

    public synchronized void stopAudioRecord() {
        if (this.g != null) {
            this.g.c();
            this.g = null;
        }
    }
}
