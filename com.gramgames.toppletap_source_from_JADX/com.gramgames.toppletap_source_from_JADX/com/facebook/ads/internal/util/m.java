package com.facebook.ads.internal.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class m {

    static class 1 implements l {
        final /* synthetic */ int[] a;
        final /* synthetic */ l b;

        1(int[] iArr, l lVar) {
            this.a = iArr;
            this.b = lVar;
        }

        public void a() {
            int[] iArr = this.a;
            iArr[0] = iArr[0] - 1;
            if (this.a[0] == 0 && this.b != null) {
                this.b.a();
            }
        }
    }

    static Bitmap a(Context context, String str) {
        return BitmapFactory.decodeFile(new File(context.getCacheDir(), String.format("%d.png", new Object[]{Integer.valueOf(str.hashCode())})).getAbsolutePath());
    }

    static void a(Context context, String str, Bitmap bitmap) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(context.getCacheDir(), String.format("%d.png", new Object[]{Integer.valueOf(str.hashCode())})));
            bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
        }
    }

    public static void a(Context context, List<String> list, l lVar) {
        int[] iArr = new int[]{list.size()};
        if (iArr[0] != 0) {
            for (String str : list) {
                new k(context).a(new 1(iArr, lVar)).execute(new String[]{str});
            }
        } else if (lVar != null) {
            lVar.a();
        }
    }
}
