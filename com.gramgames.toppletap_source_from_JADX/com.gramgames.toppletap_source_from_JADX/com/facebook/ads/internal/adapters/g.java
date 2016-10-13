package com.facebook.ads.internal.adapters;

import java.util.Locale;

public enum g {
    UNKNOWN,
    AN;

    public static g a(String str) {
        if (str == null) {
            return UNKNOWN;
        }
        try {
            return (g) Enum.valueOf(g.class, str.toUpperCase(Locale.getDefault()));
        } catch (Exception e) {
            return UNKNOWN;
        }
    }
}
