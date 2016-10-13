package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.chartboost.sdk.g;
import com.chartboost.sdk.g.b;
import com.chartboost.sdk.impl.bb;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.io.File;

public final class j implements b {
    private a a;
    private g b;
    private String c;
    private float d;
    private bb.b e;

    class 1 implements bb.b {
        final /* synthetic */ j a;

        1(j jVar) {
            this.a = jVar;
        }

        public void a(a aVar, Bundle bundle) {
            this.a.a = aVar;
            this.a.b.a(this.a);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ com.chartboost.sdk.Libraries.e.a a;
        final /* synthetic */ String b;
        final /* synthetic */ Bundle c;
        final /* synthetic */ j d;

        2(j jVar, com.chartboost.sdk.Libraries.e.a aVar, String str, Bundle bundle) {
            this.d = jVar;
            this.a = aVar;
            this.b = str;
            this.c = bundle;
        }

        public void run() {
            String str = BuildConfig.FLAVOR;
            if (!(this.a.e("checksum") == null || this.a.e("checksum").isEmpty())) {
                str = this.a.e("checksum");
            }
            bb.a().a(this.b, str, this.d.e, null, this.c == null ? new Bundle() : this.c);
        }
    }

    public static class a {
        private int a;
        private String b;
        private File c;
        private Bitmap d;
        private h e;
        private int f;
        private int g;

        public a(String str, File file, h hVar) {
            this.f = -1;
            this.g = -1;
            this.c = file;
            this.b = str;
            this.d = null;
            this.a = 1;
            this.e = hVar;
        }

        public Bitmap a() {
            if (this.d == null) {
                b();
            }
            return this.d;
        }

        public void b() {
            if (this.d == null) {
                CBLogging.a("MemoryBitmap", "Loading image '" + this.b + "' from cache");
                byte[] b = this.e.b(this.c);
                if (b == null) {
                    CBLogging.b("MemoryBitmap", "decode() - bitmap not found");
                    return;
                }
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(b, 0, b.length, options);
                Options options2 = new Options();
                options2.inJustDecodeBounds = false;
                options2.inDither = false;
                options2.inPurgeable = true;
                options2.inInputShareable = true;
                options2.inTempStorage = new byte[AccessibilityNodeInfoCompat.ACTION_PASTE];
                options2.inSampleSize = 1;
                while (options2.inSampleSize < 32) {
                    try {
                        this.d = BitmapFactory.decodeByteArray(b, 0, b.length, options2);
                        break;
                    } catch (Throwable e) {
                        CBLogging.b("MemoryBitmap", "OutOfMemoryError suppressed - trying larger sample size", e);
                        options2.inSampleSize *= 2;
                    } catch (Throwable e2) {
                        CBLogging.b("MemoryBitmap", "Exception raised decoding bitmap", e2);
                    }
                }
                this.a = options2.inSampleSize;
            }
        }

        public void c() {
            try {
                if (!(this.d == null || this.d.isRecycled())) {
                    this.d.recycle();
                }
            } catch (Exception e) {
            }
            this.d = null;
        }

        public int d() {
            if (this.d != null) {
                return this.d.getWidth();
            }
            if (this.f >= 0) {
                return this.f;
            }
            f();
            return this.f;
        }

        public int e() {
            if (this.d != null) {
                return this.d.getHeight();
            }
            if (this.g >= 0) {
                return this.g;
            }
            f();
            return this.g;
        }

        private void f() {
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.c.getAbsolutePath(), options);
                this.f = options.outWidth;
                this.g = options.outHeight;
            } catch (Throwable e) {
                CBLogging.b("MemoryBitmap", "Error decoding file size", e);
            }
        }
    }

    public j(g gVar) {
        this.d = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.e = new 1(this);
        this.b = gVar;
    }

    public int b() {
        return this.a.d() * this.a.a;
    }

    public int c() {
        return this.a.e() * this.a.a;
    }

    public void a(String str) {
        a(this.b.g(), str, new Bundle());
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar, String str, Bundle bundle) {
        com.chartboost.sdk.Libraries.e.a a = aVar.a(str);
        this.c = str;
        if (!a.b()) {
            Object e = a.e(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
            this.d = a.a("scale").a((float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            if (!TextUtils.isEmpty(e)) {
                this.b.b((b) this);
                CBUtility.e().post(new 2(this, a, e, bundle));
            }
        }
    }

    public boolean a() {
        return e();
    }

    public void d() {
        if (this.a != null) {
            this.a.c();
        }
    }

    public boolean e() {
        return this.a != null;
    }

    public Bitmap f() {
        return this.a != null ? this.a.a() : null;
    }

    public float g() {
        return this.d;
    }

    public int h() {
        return Math.round(((float) b()) / this.d);
    }

    public int i() {
        return Math.round(((float) c()) / this.d);
    }
}
