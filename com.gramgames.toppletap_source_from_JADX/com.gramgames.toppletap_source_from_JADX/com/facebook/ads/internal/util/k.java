package com.facebook.ads.internal.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import com.facebook.ads.internal.view.e;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class k extends AsyncTask<String, Void, Bitmap[]> {
    private static final String a;
    private final Context b;
    private final ImageView c;
    private final e d;
    private l e;

    static {
        a = k.class.getSimpleName();
    }

    public k(Context context) {
        this.b = context;
        this.d = null;
        this.c = null;
    }

    public k(ImageView imageView) {
        this.b = imageView.getContext();
        this.d = null;
        this.c = imageView;
    }

    public k(e eVar) {
        this.b = eVar.getContext();
        this.d = eVar;
        this.c = null;
    }

    public k a(l lVar) {
        this.e = lVar;
        return this;
    }

    protected void a(Bitmap[] bitmapArr) {
        if (this.c != null) {
            this.c.setImageBitmap(bitmapArr[0]);
        }
        if (this.d != null) {
            this.d.a(bitmapArr[0], bitmapArr[1]);
        }
        if (this.e != null) {
            this.e.a();
        }
    }

    protected Bitmap[] a(String... strArr) {
        Throwable th;
        Object obj;
        Object obj2;
        Bitmap bitmap;
        String str = null;
        String str2 = strArr[0];
        Bitmap a;
        try {
            a = m.a(this.b, str2);
            if (a == null) {
                try {
                    HttpEntity entity = g.b().execute(new HttpGet(str2)).getEntity();
                    byte[] toByteArray = EntityUtils.toByteArray(entity);
                    a = BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length);
                    entity.consumeContent();
                    m.a(this.b, str2, a);
                } catch (Throwable th2) {
                    th = th2;
                    obj = str;
                    Log.e(a, "Error downloading image: " + str2, th);
                    c.a(b.a(th, str));
                    obj2 = bitmap;
                    bitmap = a;
                    return new Bitmap[]{bitmap, str};
                }
            }
            bitmap = a;
            try {
                if (!(this.d == null || bitmap == null)) {
                    try {
                        r rVar = new r(bitmap);
                        rVar.a(Math.round(((float) bitmap.getWidth()) / 40.0f));
                        str = rVar.a();
                    } catch (Throwable th3) {
                        th = th3;
                        a = bitmap;
                        Log.e(a, "Error downloading image: " + str2, th);
                        c.a(b.a(th, str));
                        obj2 = bitmap;
                        bitmap = a;
                        return new Bitmap[]{bitmap, str};
                    }
                }
            } catch (Throwable th32) {
                th = th32;
                a = bitmap;
                obj = str;
                Log.e(a, "Error downloading image: " + str2, th);
                c.a(b.a(th, str));
                obj2 = bitmap;
                bitmap = a;
                return new Bitmap[]{bitmap, str};
            }
        } catch (Throwable th22) {
            th = th22;
            a = str;
            bitmap = str;
            Log.e(a, "Error downloading image: " + str2, th);
            c.a(b.a(th, str));
            obj2 = bitmap;
            bitmap = a;
            return new Bitmap[]{bitmap, str};
        }
        return new Bitmap[]{bitmap, str};
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Bitmap[]) obj);
    }
}
