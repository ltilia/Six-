package com.vungle.publisher;

/* compiled from: vungle */
public final class hb {
    public long a;
    public int b;
    public int c;

    public final int a(int i) {
        return jk.a(this.b, i, 300000);
    }

    public final String toString() {
        return "{firstAttemptMillis: " + this.a + ", hardRetryCount: " + this.b + ", softRetryCount: " + this.c + "}";
    }
}
