package com.unity3d.player;

import android.os.Build.VERSION;

public final class q {
    static final boolean a;
    static final boolean b;
    static final boolean c;
    static final boolean d;
    static final boolean e;
    static final boolean f;
    static final boolean g;
    static final boolean h;
    static final f i;
    static final e j;
    static final h k;
    static final g l;
    static final i m;

    static {
        i iVar = null;
        boolean z = true;
        a = VERSION.SDK_INT >= 11;
        b = VERSION.SDK_INT >= 12;
        c = VERSION.SDK_INT >= 14;
        d = VERSION.SDK_INT >= 16;
        e = VERSION.SDK_INT >= 17;
        f = VERSION.SDK_INT >= 19;
        g = VERSION.SDK_INT >= 21;
        if (VERSION.SDK_INT < 23) {
            z = false;
        }
        h = z;
        i = a ? new d() : null;
        j = b ? new c() : null;
        k = d ? new l() : null;
        l = e ? new k() : null;
        if (h) {
            iVar = new n();
        }
        m = iVar;
    }
}
