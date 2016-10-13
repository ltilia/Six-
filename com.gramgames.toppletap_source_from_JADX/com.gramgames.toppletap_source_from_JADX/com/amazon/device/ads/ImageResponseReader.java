package com.amazon.device.ads;

import android.graphics.Bitmap;

class ImageResponseReader extends ResponseReader {
    final GraphicsUtils graphicsUtils;

    ImageResponseReader(ResponseReader reader, GraphicsUtils graphicsUtils) {
        super(reader.getInputStream());
        this.graphicsUtils = graphicsUtils;
    }

    public Bitmap readAsBitmap() {
        return this.graphicsUtils.createBitmapImage(getInputStream());
    }
}
