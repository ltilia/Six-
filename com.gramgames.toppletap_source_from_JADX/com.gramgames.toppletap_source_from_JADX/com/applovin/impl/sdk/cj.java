package com.applovin.impl.sdk;

import org.json.JSONObject;

class cj extends dc {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ ci b;

    cj(ci ciVar, String str, cd cdVar, AppLovinSdkImpl appLovinSdkImpl, JSONObject jSONObject) {
        this.b = ciVar;
        this.a = jSONObject;
        super(str, cdVar, appLovinSdkImpl);
    }

    public void a(int i) {
        q.a(i, this.f);
    }

    protected void a(o oVar, p pVar) {
        oVar.a(q.a("device", this.f), this.a, pVar);
    }

    public void a(JSONObject jSONObject, int i) {
        this.b.a(jSONObject);
    }
}
