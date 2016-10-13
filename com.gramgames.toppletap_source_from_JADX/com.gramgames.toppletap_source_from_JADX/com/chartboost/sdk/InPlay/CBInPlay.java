package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;

public final class CBInPlay {
    private static final String a;
    private static a f;
    private String b;
    private Bitmap c;
    private String d;
    private a e;

    static {
        a = CBInPlay.class.getSimpleName();
        f = null;
    }

    CBInPlay() {
    }

    public void show() {
        a.a().a(this);
    }

    public void click() {
        a.a().b(this);
    }

    public String getLocation() {
        return this.b;
    }

    protected void a(String str) {
        this.b = str;
    }

    public Bitmap getAppIcon() {
        return this.c;
    }

    protected void a(Bitmap bitmap) {
        this.c = bitmap;
    }

    public String getAppName() {
        return this.d;
    }

    protected void b(String str) {
        this.d = str;
    }

    protected a a() {
        return this.e;
    }

    protected void a(a aVar) {
        this.e = aVar;
    }

    public static void cacheInPlay(String location) {
        if (((!c.H().booleanValue() || !c.L()) && (!c.N() || !c.R())) || !c.r()) {
            return;
        }
        if (TextUtils.isEmpty(location)) {
            CBLogging.b(a, "Inplay location cannot be empty");
            if (c.h() != null) {
                c.h().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        if (f == null) {
            f = a.a();
        }
        f.a(location);
    }

    public static boolean hasInPlay(String location) {
        if (!c.r()) {
            return false;
        }
        if (TextUtils.isEmpty(location)) {
            CBLogging.b(a, "Inplay location cannot be empty");
            if (c.h() == null) {
                return false;
            }
            c.h().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
            return false;
        }
        if (f == null) {
            f = a.a();
        }
        return f.b(location);
    }

    public static CBInPlay getInPlay(String location) {
        if (((!c.H().booleanValue() || !c.L()) && (!c.N() || !c.R())) || !c.r()) {
            return null;
        }
        if (TextUtils.isEmpty(location)) {
            CBLogging.b(a, "Inplay location cannot be empty");
            if (c.h() == null) {
                return null;
            }
            c.h().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
            return null;
        }
        if (f == null) {
            f = a.a();
        }
        return f.c(location);
    }
}
