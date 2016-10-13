package com.chartboost.sdk.impl;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.h;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.mopub.volley.DefaultRetryPolicy;
import gs.gram.mopub.BuildConfig;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class bb {
    private static volatile bb c;
    private h a;
    private Map<String, com.chartboost.sdk.Libraries.j.a> b;

    public interface b {
        void a(com.chartboost.sdk.Libraries.j.a aVar, Bundle bundle);
    }

    private class a implements Runnable {
        final /* synthetic */ bb a;
        private String b;
        private final WeakReference<ImageView> c;
        private b d;
        private String e;
        private Bundle f;

        class 1 implements com.chartboost.sdk.impl.n.a {
            final /* synthetic */ a a;

            1(a aVar) {
                this.a = aVar;
            }

            public void a(s sVar) {
                CBLogging.b("CBWebImageCache", "Error downloading the bitmap image from the server");
                if (!(sVar == null || TextUtils.isEmpty(sVar.getMessage()))) {
                    CBLogging.b("CBWebImageCache", sVar.getMessage());
                }
                if (sVar != null && sVar.a != null) {
                    CBLogging.b("CBWebImageCache", "Error status Code: " + sVar.a.a);
                }
            }
        }

        class 2 extends l<String> {
            final /* synthetic */ a a;

            2(a aVar, int i, String str, com.chartboost.sdk.impl.n.a aVar2) {
                this.a = aVar;
                super(i, str, aVar2);
            }

            protected /* synthetic */ void b(Object obj) {
                c((String) obj);
            }

            protected n<String> a(i iVar) {
                try {
                    byte[] bArr = iVar.b;
                    String b = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(bArr));
                    if (TextUtils.isEmpty(b)) {
                        b = BuildConfig.FLAVOR;
                    }
                    if (!b.equals(this.a.e)) {
                        this.a.e = b;
                        CBLogging.b("CBWebImageCache:ImageDownloader", "Error: checksum did not match while downloading from " + this.a.b);
                    }
                    this.a.a.a.a(this.a.a.a.i(), String.format("%s%s", new Object[]{this.a.e, ".png"}), bArr);
                    this.a.a.a(this.a.e);
                    return n.a(null, null);
                } catch (Exception e) {
                    return n.a(new s("Bitmap response data is empty, unable to download the bitmap"));
                }
            }

            protected void c(String str) {
                this.a.a();
            }

            public Map<String, String> i() throws a {
                Map<String, String> hashMap = new HashMap();
                for (Entry entry : ay.b().entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                }
                return hashMap;
            }
        }

        class 3 implements Runnable {
            final /* synthetic */ com.chartboost.sdk.Libraries.j.a a;
            final /* synthetic */ a b;

            3(a aVar, com.chartboost.sdk.Libraries.j.a aVar2) {
                this.b = aVar;
                this.a = aVar2;
            }

            public void run() {
                if (this.b.c != null) {
                    ImageView imageView = (ImageView) this.b.c.get();
                    a a = bb.b(imageView);
                    if (this.a != null && this.b == a) {
                        imageView.setImageBitmap(this.a.a());
                    }
                }
                if (this.b.d != null) {
                    this.b.d.a(this.a, this.b.f);
                }
            }
        }

        public a(bb bbVar, ImageView imageView, b bVar, String str, Bundle bundle, String str2) {
            this.a = bbVar;
            this.c = new WeakReference(imageView);
            Drawable cVar = new c(this);
            if (imageView != null) {
                imageView.setImageDrawable(cVar);
            }
            this.e = str;
            this.d = bVar;
            this.f = bundle;
            this.b = str2;
        }

        public void run() {
            if (this.a.b(this.e)) {
                a();
                return;
            }
            com.chartboost.sdk.impl.n.a 1 = new 1(this);
            CBLogging.a("CBWebImageCache", "downloading image to cache... " + this.b);
            az.a(com.chartboost.sdk.c.y()).a().a(new 2(this, 0, this.b, 1)).a(new d(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)).a(false);
        }

        public void a() {
            com.chartboost.sdk.Libraries.j.a b = b();
            if (!(b == null || this.c == null || this.c.get() == null || this != bb.b((ImageView) this.c.get()))) {
                b.b();
            }
            CBUtility.e().post(new 3(this, b));
        }

        private com.chartboost.sdk.Libraries.j.a b() {
            return (com.chartboost.sdk.Libraries.j.a) this.a.b.get(this.e);
        }
    }

    static class c extends BitmapDrawable {
        private final WeakReference<a> a;

        public c(a aVar) {
            this.a = new WeakReference(aVar);
        }

        public a a() {
            return (a) this.a.get();
        }
    }

    static {
        c = null;
    }

    public static bb a() {
        if (c == null) {
            synchronized (bb.class) {
                if (c == null) {
                    c = new bb();
                }
            }
        }
        return c;
    }

    private bb() {
        this.a = new h(true);
        this.b = new HashMap();
    }

    public void b() {
        this.a.d();
        this.b.clear();
    }

    public void a(String str, String str2, b bVar, ImageView imageView, Bundle bundle) {
        com.chartboost.sdk.Libraries.j.a a = a(str2);
        if (a != null) {
            if (imageView != null) {
                imageView.setImageBitmap(a.a());
            }
            if (bVar != null) {
                bVar.a(a, bundle);
                return;
            }
            return;
        }
        if (str == null && bVar != null) {
            bVar.a(null, bundle);
        }
        aw.a().execute(new a(this, imageView, bVar, str2, bundle, str));
    }

    private com.chartboost.sdk.Libraries.j.a a(String str) {
        if (!b(str)) {
            if (this.b.containsKey(str)) {
                this.b.remove(str);
            }
            return null;
        } else if (this.b.containsKey(str)) {
            return (com.chartboost.sdk.Libraries.j.a) this.b.get(str);
        } else {
            com.chartboost.sdk.Libraries.j.a aVar = new com.chartboost.sdk.Libraries.j.a(str, this.a.c(this.a.i(), String.format("%s%s", new Object[]{str, ".png"})), this.a);
            this.b.put(str, aVar);
            return aVar;
        }
    }

    private static a b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof c) {
                return ((c) drawable).a();
            }
        }
        return null;
    }

    private boolean b(String str) {
        return this.a.b(String.format("%s%s", new Object[]{str, ".png"}));
    }
}
