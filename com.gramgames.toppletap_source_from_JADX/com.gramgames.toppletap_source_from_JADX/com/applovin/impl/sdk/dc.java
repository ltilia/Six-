package com.applovin.impl.sdk;

import org.json.JSONObject;

abstract class dc extends ca implements p {
    private int a;
    private long b;
    private final p c;
    private cd d;

    dc(String str, int i, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
        this.b = -1;
        this.d = null;
        this.a = i;
        this.c = new dd(this, appLovinSdkImpl, str);
    }

    dc(String str, cd cdVar, AppLovinSdkImpl appLovinSdkImpl) {
        this(str, ((Integer) appLovinSdkImpl.a(cdVar)).intValue(), appLovinSdkImpl);
    }

    private void c() {
        if (this.d != null) {
            ce settingsManager = this.f.getSettingsManager();
            settingsManager.a(this.d, this.d.c());
            settingsManager.b();
        }
    }

    public void a(int i) {
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(cd cdVar) {
        this.d = cdVar;
    }

    protected abstract void a(o oVar, p pVar);

    public void a(JSONObject jSONObject, int i) {
    }

    public void run() {
        a(this.f.getConnectionManager(), this.c);
    }
}
