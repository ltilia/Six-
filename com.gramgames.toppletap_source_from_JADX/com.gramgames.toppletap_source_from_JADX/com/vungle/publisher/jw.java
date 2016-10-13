package com.vungle.publisher;

import java.lang.reflect.Method;

/* compiled from: vungle */
final class jw {
    final Method a;
    final jz b;
    final Class<?> c;
    String d;

    jw(Method method, jz jzVar, Class<?> cls) {
        this.a = method;
        this.b = jzVar;
        this.c = cls;
    }

    public final boolean equals(Object other) {
        if (!(other instanceof jw)) {
            return false;
        }
        a();
        jw jwVar = (jw) other;
        jwVar.a();
        return this.d.equals(jwVar.d);
    }

    private synchronized void a() {
        if (this.d == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.a.getDeclaringClass().getName());
            stringBuilder.append('#').append(this.a.getName());
            stringBuilder.append('(').append(this.c.getName());
            this.d = stringBuilder.toString();
        }
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
