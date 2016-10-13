package com.vungle.publisher;

/* compiled from: vungle */
final class jy {
    final Object a;
    final jw b;
    final int c;
    volatile boolean d;

    jy(Object obj, jw jwVar) {
        this.a = obj;
        this.b = jwVar;
        this.c = 0;
        this.d = true;
    }

    public final boolean equals(Object other) {
        if (!(other instanceof jy)) {
            return false;
        }
        jy jyVar = (jy) other;
        if (this.a == jyVar.a && this.b.equals(jyVar.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() + this.b.d.hashCode();
    }
}
