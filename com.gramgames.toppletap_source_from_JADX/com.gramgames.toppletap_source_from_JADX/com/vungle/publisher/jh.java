package com.vungle.publisher;

import android.text.TextUtils;

/* compiled from: vungle */
public final class jh {
    public static String a(Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(th.getClass().getName());
        Object message = th.getMessage();
        if (!TextUtils.isEmpty(message)) {
            stringBuilder.append(':').append(' ').append(message);
        }
        return stringBuilder.toString();
    }
}
