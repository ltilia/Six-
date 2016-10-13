package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Libraries.g.e;
import com.google.android.gms.nearby.messages.Strategy;

public interface a {
    public static final com.chartboost.sdk.Libraries.g.a a;

    static class 1 extends e {
        1() {
        }

        public boolean a(Object obj) {
            int intValue = ((Number) obj).intValue();
            return intValue >= 200 && intValue < Strategy.TTL_SECONDS_DEFAULT;
        }

        public String a() {
            return "Must be a valid status code (>=200 && <300)";
        }
    }

    static {
        a = g.b(g.b(), new 1());
    }
}
