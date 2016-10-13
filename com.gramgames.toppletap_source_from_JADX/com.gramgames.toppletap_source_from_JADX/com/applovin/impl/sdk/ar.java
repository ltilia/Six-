package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class ar implements Runnable {
    final /* synthetic */ aq a;

    ar(aq aqVar) {
        this.a = aqVar;
    }

    public void run() {
        Builder builder = new Builder(this.a.c);
        builder.setTitle((CharSequence) this.a.a.a(cb.K));
        builder.setMessage((CharSequence) this.a.a.a(cb.L));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.a.a.a(cb.M), new as(this));
        builder.setNegativeButton((CharSequence) this.a.a.a(cb.N), new av(this));
        builder.show();
    }
}
