package com.facebook.ads.internal.action;

import android.content.Context;
import android.net.Uri;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.o;
import com.facebook.ads.internal.util.s;

public abstract class a {
    public abstract com.facebook.ads.internal.util.b.a a();

    protected void a(Context context, Uri uri) {
        if (!s.a(uri.getQueryParameter("native_click_report_url"))) {
            new o().execute(new String[]{r0});
            g.a(context, "Click logged");
        }
    }

    public abstract void b();
}
