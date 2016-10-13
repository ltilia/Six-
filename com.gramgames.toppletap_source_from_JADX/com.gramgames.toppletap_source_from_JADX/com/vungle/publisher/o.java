package com.vungle.publisher;

import com.vungle.publisher.db.model.Ad;

public abstract class o extends es implements n {
    protected final Ad<?, ?, ?> b;

    public o(Ad<?, ?, ?> ad) {
        this.b = ad;
    }

    public final Ad<?, ?, ?> a() {
        return this.b;
    }
}
