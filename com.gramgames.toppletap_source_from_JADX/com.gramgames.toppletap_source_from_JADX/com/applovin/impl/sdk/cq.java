package com.applovin.impl.sdk;

import org.json.JSONObject;

class cq extends dc {
    final /* synthetic */ cp a;

    cq(cp cpVar, String str, int i, AppLovinSdkImpl appLovinSdkImpl) {
        this.a = cpVar;
        super(str, i, appLovinSdkImpl);
    }

    public void a(int i) {
        this.a.c.onPostbackFailure(this.a.a, i);
    }

    protected void a(o oVar, p pVar) {
        int intValue = this.a.j < 0 ? ((Integer) this.f.a(cb.ax)).intValue() : this.a.j;
        if (this.a.b == null) {
            oVar.a(this.a.a, intValue, false, pVar);
        } else {
            oVar.a(this.a.a, intValue, new JSONObject(this.a.b), false, pVar);
        }
    }

    public void a(JSONObject jSONObject, int i) {
        this.a.c.onPostbackSuccess(this.a.a);
    }
}
