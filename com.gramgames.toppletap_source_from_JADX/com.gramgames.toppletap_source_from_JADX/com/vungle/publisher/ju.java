package com.vungle.publisher;

/* compiled from: vungle */
final class ju {
    private jt a;
    private jt b;

    ju() {
    }

    final synchronized void a(jt jtVar) {
        if (jtVar == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        if (this.b != null) {
            this.b.c = jtVar;
            this.b = jtVar;
        } else if (this.a == null) {
            this.b = jtVar;
            this.a = jtVar;
        } else {
            throw new IllegalStateException("Head present, but no tail");
        }
        notifyAll();
    }

    final synchronized jt a() {
        jt jtVar;
        jtVar = this.a;
        if (this.a != null) {
            this.a = this.a.c;
            if (this.a == null) {
                this.b = null;
            }
        }
        return jtVar;
    }

    final synchronized jt b() throws InterruptedException {
        if (this.a == null) {
            wait(1000);
        }
        return a();
    }
}
