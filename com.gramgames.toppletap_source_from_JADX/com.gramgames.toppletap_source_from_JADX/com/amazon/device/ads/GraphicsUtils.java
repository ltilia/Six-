package com.amazon.device.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

class GraphicsUtils {
    private static final String LOGTAG;
    private final MobileAdsLogger logger;

    GraphicsUtils() {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    }

    static {
        LOGTAG = GraphicsUtils.class.getSimpleName();
    }

    public Bitmap createBitmapImage(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        InputStream inputStream2 = new BufferedInputStream(inputStream, AccessibilityNodeInfoCompat.ACTION_PASTE);
        inputStream2.mark(AccessibilityNodeInfoCompat.ACTION_PASTE);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream2);
        try {
            inputStream2.close();
        } catch (IOException e) {
            this.logger.e("IOException while trying to close the input stream.");
        }
        inputStream = inputStream2;
        return bitmap;
    }

    public String insertImageInMediaStore(Context context, Bitmap bitmap, String name, String description) {
        return Media.insertImage(context.getContentResolver(), bitmap, name, description);
    }
}
