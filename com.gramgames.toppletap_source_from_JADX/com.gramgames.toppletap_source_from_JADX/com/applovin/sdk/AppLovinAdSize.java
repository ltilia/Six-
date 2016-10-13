package com.applovin.sdk;

import com.google.android.gms.nearby.messages.Strategy;
import com.mopub.common.AdType;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AppLovinAdSize {
    public static final AppLovinAdSize BANNER;
    public static final AppLovinAdSize INTERSTITIAL;
    public static final AppLovinAdSize LEADER;
    public static final AppLovinAdSize MREC;
    public static final int SPAN = -1;
    private final int a;
    private final int b;
    private final String c;

    static {
        BANNER = new AppLovinAdSize(SPAN, 50, "BANNER");
        LEADER = new AppLovinAdSize(SPAN, 75, "LEADER");
        INTERSTITIAL = new AppLovinAdSize(SPAN, SPAN, "INTER");
        MREC = new AppLovinAdSize(Strategy.TTL_SECONDS_DEFAULT, 250, "MREC");
    }

    AppLovinAdSize(int i, int i2, String str) {
        if (i < 0 && i != SPAN) {
            throw new IllegalArgumentException("Ad width must be a positive number. Number provided: " + i);
        } else if (i > 9999) {
            throw new IllegalArgumentException("Ad width must be less then 9999. Number provided: " + i);
        } else if (i2 < 0 && i2 != SPAN) {
            throw new IllegalArgumentException("Ad height must be a positive number. Number provided: " + i2);
        } else if (i2 > 9999) {
            throw new IllegalArgumentException("Ad height must be less then 9999. Number provided: " + i2);
        } else if (str == null) {
            throw new IllegalArgumentException("No label specified");
        } else if (str.length() > 9) {
            throw new IllegalArgumentException("Provided label is too long. Label provided: " + str);
        } else {
            this.a = i;
            this.b = i2;
            this.c = str;
        }
    }

    public AppLovinAdSize(String str) {
        this(0, 0, str);
    }

    private static int a(String str) {
        if (TtmlNode.TAG_SPAN.equalsIgnoreCase(str)) {
            return SPAN;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Set allSizes() {
        Set hashSet = new HashSet(4);
        hashSet.add(BANNER);
        hashSet.add(MREC);
        hashSet.add(INTERSTITIAL);
        hashSet.add(LEADER);
        return hashSet;
    }

    public static AppLovinAdSize fromString(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        String toLowerCase = str.toLowerCase(Locale.ENGLISH);
        if (toLowerCase.equals("banner")) {
            return BANNER;
        }
        if (toLowerCase.equals(AdType.INTERSTITIAL) || toLowerCase.equals("inter")) {
            return INTERSTITIAL;
        }
        if (toLowerCase.equals("mrec")) {
            return MREC;
        }
        if (toLowerCase.equals("leader")) {
            return LEADER;
        }
        String[] split = str.split("x");
        return split.length == 2 ? new AppLovinAdSize(a(split[0]), a(split[1]), str) : new AppLovinAdSize(0, 0, str);
    }

    public int getHeight() {
        return this.b;
    }

    public String getLabel() {
        return this.c.toUpperCase(Locale.ENGLISH);
    }

    public int getWidth() {
        return this.a;
    }

    public String toString() {
        return getLabel();
    }
}
