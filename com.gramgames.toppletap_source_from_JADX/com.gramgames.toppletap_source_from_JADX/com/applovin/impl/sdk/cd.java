package com.applovin.impl.sdk;

public class cd implements Comparable {
    private static int a;
    private final int b;
    private final String c;
    private final Object d;

    static {
        a = 0;
    }

    private cd(String str, Object obj) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        } else {
            this.c = str;
            this.d = obj;
            this.b = a;
            a++;
        }
    }

    public int a() {
        return this.b;
    }

    Object a(Object obj) {
        return this.d.getClass().cast(obj);
    }

    public String b() {
        return this.c;
    }

    public Object c() {
        return this.d;
    }

    public int compareTo(Object obj) {
        return (obj == null || !(obj instanceof cd)) ? 0 : this.c.compareTo(((cd) obj).b());
    }
}
