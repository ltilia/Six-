package com.mopub.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageCache;
import com.mopub.volley.toolbox.ImageLoader.ImageContainer;
import com.mopub.volley.toolbox.ImageLoader.ImageListener;

public class MaxWidthImageLoader extends ImageLoader {
    private final int mMaxImageWidth;

    @TargetApi(13)
    public MaxWidthImageLoader(RequestQueue queue, Context context, ImageCache imageCache) {
        super(queue, imageCache);
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        if (VERSION.SDK_INT < 13) {
            size.set(display.getWidth(), display.getHeight());
        } else {
            display.getSize(size);
        }
        this.mMaxImageWidth = Math.min(size.x, size.y);
    }

    public ImageContainer get(String requestUrl, ImageListener listener) {
        return super.get(requestUrl, listener, this.mMaxImageWidth, 0);
    }
}
