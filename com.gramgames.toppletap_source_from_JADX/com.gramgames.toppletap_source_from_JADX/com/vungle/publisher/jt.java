package com.vungle.publisher;

import com.mopub.mobileads.CustomEventBannerAdapter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: vungle */
final class jt {
    private static final List<jt> d;
    Object a;
    jy b;
    jt c;

    static {
        d = new ArrayList();
    }

    private jt(Object obj, jy jyVar) {
        this.a = obj;
        this.b = jyVar;
    }

    static jt a(jy jyVar, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size > 0) {
                jt jtVar = (jt) d.remove(size - 1);
                jtVar.a = obj;
                jtVar.b = jyVar;
                jtVar.c = null;
                return jtVar;
            }
            return new jt(obj, jyVar);
        }
    }

    static void a(jt jtVar) {
        jtVar.a = null;
        jtVar.b = null;
        jtVar.c = null;
        synchronized (d) {
            if (d.size() < CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY) {
                d.add(jtVar);
            }
        }
    }
}
