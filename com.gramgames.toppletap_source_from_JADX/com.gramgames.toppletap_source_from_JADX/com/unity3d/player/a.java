package com.unity3d.player;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import com.facebook.ads.AdError;
import com.google.android.gms.nearby.connection.Connections;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.List;

final class a {
    Camera a;
    Parameters b;
    Size c;
    int d;
    int[] e;
    b f;
    private final Object[] g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;

    interface a {
        void onCameraFrame(a aVar, byte[] bArr);
    }

    class 1 implements PreviewCallback {
        long a;
        final /* synthetic */ a b;
        final /* synthetic */ a c;

        1(a aVar, a aVar2) {
            this.c = aVar;
            this.b = aVar2;
            this.a = 0;
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            if (this.c.a == camera) {
                this.b.onCameraFrame(this.c, bArr);
            }
        }
    }

    class 2 extends b {
        Camera a;
        final /* synthetic */ a b;

        2(a aVar) {
            this.b = aVar;
            super(3);
            this.a = this.b.a;
        }

        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
            synchronized (this.b.g) {
                if (this.b.a != this.a) {
                    return;
                }
                try {
                    this.b.a.setPreviewDisplay(surfaceHolder);
                    this.b.a.startPreview();
                } catch (Exception e) {
                    m.Log(6, "Unable to initialize webcam data stream: " + e.getMessage());
                }
            }
        }

        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            synchronized (this.b.g) {
                if (this.b.a != this.a) {
                    return;
                }
                this.b.a.stopPreview();
            }
        }
    }

    public a(int i, int i2, int i3, int i4) {
        this.g = new Object[0];
        this.h = i;
        this.i = a(i2, 640);
        this.j = a(i3, 480);
        this.k = a(i4, 24);
    }

    private static final int a(int i, int i2) {
        return i != 0 ? i : i2;
    }

    private static void a(Parameters parameters) {
        if (parameters.getSupportedColorEffects() != null) {
            parameters.setColorEffect(UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_NONE);
        }
        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
    }

    private void b(a aVar) {
        synchronized (this.g) {
            this.a = Camera.open(this.h);
            this.b = this.a.getParameters();
            this.c = f();
            this.e = e();
            this.d = d();
            a(this.b);
            this.b.setPreviewSize(this.c.width, this.c.height);
            this.b.setPreviewFpsRange(this.e[0], this.e[1]);
            this.a.setParameters(this.b);
            PreviewCallback 1 = new 1(this, aVar);
            int i = (((this.c.width * this.c.height) * this.d) / 8) + Connections.MAX_RELIABLE_MESSAGE_LEN;
            this.a.addCallbackBuffer(new byte[i]);
            this.a.addCallbackBuffer(new byte[i]);
            this.a.setPreviewCallbackWithBuffer(1);
        }
    }

    private final int d() {
        this.b.setPreviewFormat(17);
        return ImageFormat.getBitsPerPixel(17);
    }

    private final int[] e() {
        double d = (double) (this.k * AdError.NETWORK_ERROR_CODE);
        List supportedPreviewFpsRange = this.b.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null) {
            supportedPreviewFpsRange = new ArrayList();
        }
        int[] iArr = new int[]{this.k * AdError.NETWORK_ERROR_CODE, this.k * AdError.NETWORK_ERROR_CODE};
        double d2 = Double.MAX_VALUE;
        for (int[] iArr2 : r0) {
            int[] iArr3;
            double d3;
            double abs = Math.abs(Math.log(d / ((double) iArr2[0]))) + Math.abs(Math.log(d / ((double) iArr2[1])));
            if (abs < d2) {
                iArr3 = iArr2;
                d3 = abs;
            } else {
                double d4 = d2;
                iArr3 = iArr;
                d3 = d4;
            }
            iArr = iArr3;
            d2 = d3;
        }
        return iArr;
    }

    private final Size f() {
        double d = (double) this.i;
        double d2 = (double) this.j;
        Size size = null;
        double d3 = Double.MAX_VALUE;
        for (Size size2 : this.b.getSupportedPreviewSizes()) {
            Size size3;
            double d4;
            double abs = Math.abs(Math.log(d / ((double) size2.width))) + Math.abs(Math.log(d2 / ((double) size2.height)));
            if (abs < d3) {
                double d5 = abs;
                size3 = size2;
                d4 = d5;
            } else {
                size3 = size;
                d4 = d3;
            }
            d3 = d4;
            size = size3;
        }
        return size;
    }

    public final int a() {
        return this.h;
    }

    public final void a(a aVar) {
        synchronized (this.g) {
            if (this.a == null) {
                b(aVar);
            }
            if (q.a && q.i.a(this.a)) {
                this.a.startPreview();
                return;
            }
            if (this.f == null) {
                this.f = new 2(this);
                this.f.a();
            }
        }
    }

    public final void a(byte[] bArr) {
        synchronized (this.g) {
            if (this.a != null) {
                this.a.addCallbackBuffer(bArr);
            }
        }
    }

    public final Size b() {
        return this.c;
    }

    public final void c() {
        synchronized (this.g) {
            if (this.a != null) {
                this.a.setPreviewCallbackWithBuffer(null);
                this.a.stopPreview();
                this.a.release();
                this.a = null;
            }
            if (this.f != null) {
                this.f.b();
                this.f = null;
            }
        }
    }
}
