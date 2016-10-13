package com.vungle.publisher.util;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.widget.ImageView;
import com.vungle.log.Logger;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.image.BitmapFactory;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ViewUtils {
    @Inject
    public BitmapFactory a;
    @Inject
    public Factory b;

    static class 1 implements OnTouchListener {
        1() {
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    @Inject
    ViewUtils() {
    }

    public static void a(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public final Bitmap a(String str) {
        Bitmap bitmap = null;
        try {
            bitmap = this.a.getBitmap(str);
        } catch (Throwable e) {
            this.b.b(Logger.AD_TAG, "error loading " + str, e);
        }
        return bitmap;
    }

    public static String a(WebView webView) {
        return webView.getSettings().getUserAgentString();
    }

    public static void a(View view) {
        view.setOnTouchListener(new 1());
    }

    public static void b(View view) {
        view.setOnTouchListener(null);
    }
}
