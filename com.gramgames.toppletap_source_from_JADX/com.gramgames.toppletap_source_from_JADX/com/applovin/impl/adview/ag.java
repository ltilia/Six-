package com.applovin.impl.adview;

class ag implements Runnable {
    final /* synthetic */ x a;

    private ag(x xVar) {
        this.a = xVar;
    }

    public void run() {
        try {
            this.a.dismiss();
        } catch (Throwable th) {
            if (this.a.c != null) {
                this.a.c.e("InterstitialAdDialog", "dismiss() threw exception", th);
            }
        }
    }
}
