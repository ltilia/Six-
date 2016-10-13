package com.facebook.ads.internal.server;

import com.facebook.ads.internal.dto.c;
import com.facebook.ads.internal.server.c.a;

public class e extends c {
    private final String a;

    public e(String str, c cVar) {
        super(a.ERROR, cVar);
        this.a = str;
    }

    public String c() {
        return this.a;
    }
}
