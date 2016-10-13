package com.vungle.publisher;

/* compiled from: vungle */
final class jn implements Runnable {
    final ju a;
    private final jp b;

    jn(jp jpVar) {
        this.b = jpVar;
        this.a = new ju();
    }

    public final void run() {
        jt a = this.a.a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.a(a);
    }
}
