package com.chartboost.sdk.impl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;
import com.chartboost.sdk.impl.n.a;
import com.chartboost.sdk.impl.n.b;
import com.facebook.ads.AdError;
import com.mopub.volley.DefaultRetryPolicy;

public class z extends l<Bitmap> {
    private static final Object f;
    private final b<Bitmap> a;
    private final Config b;
    private final int c;
    private final int d;
    private ScaleType e;

    protected /* synthetic */ void b(Object obj) {
        a((Bitmap) obj);
    }

    static {
        f = new Object();
    }

    public z(String str, b<Bitmap> bVar, int i, int i2, ScaleType scaleType, Config config, a aVar) {
        super(0, str, aVar);
        a(new d(AdError.NETWORK_ERROR_CODE, 2, 2.0f));
        this.a = bVar;
        this.b = config;
        this.c = i;
        this.d = i2;
        this.e = scaleType;
    }

    @Deprecated
    public z(String str, b<Bitmap> bVar, int i, int i2, Config config, a aVar) {
        this(str, bVar, i, i2, ScaleType.CENTER_INSIDE, config, aVar);
    }

    public l.a s() {
        return l.a.LOW;
    }

    private static int a(int i, int i2, int i3, int i4, ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ScaleType.FIT_XY) {
            if (i == 0) {
                return i3;
            }
            return i;
        } else if (i == 0) {
            return (int) ((((double) i2) / ((double) i4)) * ((double) i3));
        } else {
            if (i2 == 0) {
                return i;
            }
            double d = ((double) i4) / ((double) i3);
            if (scaleType == ScaleType.CENTER_CROP) {
                if (((double) i) * d < ((double) i2)) {
                    return (int) (((double) i2) / d);
                }
                return i;
            } else if (((double) i) * d > ((double) i2)) {
                return (int) (((double) i2) / d);
            } else {
                return i;
            }
        }
    }

    protected n<Bitmap> a(i iVar) {
        n<Bitmap> b;
        synchronized (f) {
            try {
                b = b(iVar);
            } catch (Throwable e) {
                t.c("Caught OOM for %d byte image, url=%s", Integer.valueOf(iVar.b.length), d());
                b = n.a(new k(e));
            }
        }
        return b;
    }

    private n<Bitmap> b(i iVar) {
        Object decodeByteArray;
        byte[] bArr = iVar.b;
        Options options = new Options();
        if (this.c == 0 && this.d == 0) {
            options.inPreferredConfig = this.b;
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int a = a(this.c, this.d, i, i2, this.e);
            int a2 = a(this.d, this.c, i2, i, this.e);
            options.inJustDecodeBounds = false;
            options.inSampleSize = a(i, i2, a, a2);
            Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray2 == null || (decodeByteArray2.getWidth() <= a && decodeByteArray2.getHeight() <= a2)) {
                Bitmap bitmap = decodeByteArray2;
            } else {
                decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray2, a, a2, true);
                decodeByteArray2.recycle();
            }
        }
        if (decodeByteArray == null) {
            return n.a(new k(iVar));
        }
        return n.a(decodeByteArray, w.a(iVar));
    }

    protected void a(Bitmap bitmap) {
        this.a.a(bitmap);
    }

    static int a(int i, int i2, int i3, int i4) {
        double min = Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4));
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        while (((double) (f * 2.0f)) <= min) {
            f *= 2.0f;
        }
        return (int) f;
    }
}
