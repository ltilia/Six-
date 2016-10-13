package com.applovin.impl.sdk;

import org.json.JSONObject;

abstract class by extends ca {
    protected by(String str, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
    }

    protected void a(String str, JSONObject jSONObject, p pVar) {
        dc bzVar = new bz(this, "Repeat" + this.e, cb.g, this.f, str, jSONObject, pVar);
        bzVar.a(cb.j);
        bzVar.run();
    }
}
