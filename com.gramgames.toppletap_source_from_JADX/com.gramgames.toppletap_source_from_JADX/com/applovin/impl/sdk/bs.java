package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class bs implements AppLovinPostbackListener {
    final /* synthetic */ bt a;
    final /* synthetic */ br b;

    bs(br brVar, bt btVar) {
        this.b = brVar;
        this.a = btVar;
    }

    public void onPostbackFailure(String str, int i) {
        this.b.b.i("PersistentPostbackManager", "Failed to submit postback with errorCode " + i + ". Will retry later...  Postback: " + this.a);
        this.b.e(this.a);
    }

    public void onPostbackSuccess(String str) {
        this.b.d(this.a);
        this.b.b.d("PersistentPostbackManager", "Successfully submitted postback: " + this.a);
        this.b.b();
    }
}
