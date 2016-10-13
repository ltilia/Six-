package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class ay implements Runnable {
    final /* synthetic */ aw a;

    ay(aw awVar) {
        this.a = awVar;
    }

    public void run() {
        Builder builder = new Builder(this.a.b);
        builder.setTitle((CharSequence) this.a.a.a(cb.V));
        builder.setMessage((CharSequence) this.a.a.a(cb.W));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.a.a.a(cb.Y), new az(this));
        builder.setNegativeButton((CharSequence) this.a.a.a(cb.X), new ba(this));
        this.a.c = builder.show();
    }
}
