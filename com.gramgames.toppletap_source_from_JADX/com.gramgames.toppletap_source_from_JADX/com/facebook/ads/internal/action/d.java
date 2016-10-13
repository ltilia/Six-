package com.facebook.ads.internal.action;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.util.b.a;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.drive.DriveFile;

public class d extends a {
    private static final String a;
    private final Context b;
    private final Uri c;

    static {
        a = d.class.getSimpleName();
    }

    public d(Context context, Uri uri) {
        this.b = context;
        this.c = uri;
    }

    public a a() {
        return a.OPEN_LINK;
    }

    public void b() {
        a(this.b, this.c);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.c.getQueryParameter(ShareConstants.WEB_DIALOG_PARAM_LINK)));
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        try {
            this.b.startActivity(intent);
        } catch (Throwable e) {
            Log.d(a, "Failed to open market url: " + this.c.toString(), e);
        }
    }
}
