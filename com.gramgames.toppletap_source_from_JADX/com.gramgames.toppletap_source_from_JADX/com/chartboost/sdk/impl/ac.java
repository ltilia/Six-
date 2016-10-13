package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.n.a;
import com.chartboost.sdk.impl.n.b;
import java.io.UnsupportedEncodingException;

public class ac extends l<String> {
    private final b<String> a;

    protected /* synthetic */ void b(Object obj) {
        c((String) obj);
    }

    public ac(int i, String str, b<String> bVar, a aVar) {
        super(i, str, aVar);
        this.a = bVar;
    }

    protected void c(String str) {
        this.a.a(str);
    }

    protected n<String> a(i iVar) {
        Object str;
        try {
            str = new String(iVar.b, w.a(iVar.c));
        } catch (UnsupportedEncodingException e) {
            str = new String(iVar.b);
        }
        return n.a(str, w.a(iVar));
    }
}
