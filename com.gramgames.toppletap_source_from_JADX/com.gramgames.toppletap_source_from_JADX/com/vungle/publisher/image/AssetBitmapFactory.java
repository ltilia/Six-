package com.vungle.publisher.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.vungle.publisher.inject.Injector;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AssetBitmapFactory extends BaseBitmapFactory {
    @Inject
    public Context a;

    @Inject
    AssetBitmapFactory() {
        Injector.b().a(this);
    }

    public Bitmap getBitmap(String path) throws IOException {
        return BitmapFactory.decodeStream(this.a.getAssets().open(path), null, getOptions(this.a));
    }
}
