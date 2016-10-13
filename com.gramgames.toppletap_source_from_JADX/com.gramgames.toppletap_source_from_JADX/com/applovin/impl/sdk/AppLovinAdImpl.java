package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.impl.adview.v;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import gs.gram.mopub.BuildConfig;

public class AppLovinAdImpl implements bd, AppLovinAd {
    private final AppLovinAdSize a;
    private final AppLovinAdType b;
    private final long c;
    private final String d;
    private final AdTarget e;
    private final String f;
    private final String g;
    private final float h;
    private final float i;
    private final int j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final boolean o;
    private final boolean p;
    private final String q;
    private final v r;

    public enum AdTarget {
        DEFAULT,
        ACTIVITY_PORTRAIT,
        ACTIVITY_LANDSCAPE
    }

    public class Builder {
        private String a;
        private AppLovinAdSize b;
        private AppLovinAdType c;
        private String d;
        private AdTarget e;
        private v f;
        private float g;
        private float h;
        private int i;
        private long j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;
        private boolean p;
        private boolean q;
        private String r;

        public AppLovinAdImpl build() {
            return new AppLovinAdImpl(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, null);
        }

        public Builder setClCode(String str) {
            this.k = str;
            return this;
        }

        public Builder setClickDestinationUrl(String str) {
            this.r = str;
            return this;
        }

        public Builder setCloseDelay(float f) {
            this.h = f;
            return this;
        }

        public Builder setCloseStyle(v vVar) {
            this.f = vVar;
            return this;
        }

        public Builder setCompletionUrl(String str) {
            this.l = str;
            return this;
        }

        public Builder setCountdownLength(int i) {
            this.i = i;
            return this;
        }

        public Builder setCurrentAdIdNumber(long j) {
            this.j = j;
            return this;
        }

        public Builder setDismissOnSkip(boolean z) {
            this.p = z;
            return this;
        }

        public Builder setHtml(String str) {
            this.a = str;
            return this;
        }

        public Builder setMuteImageFilename(String str) {
            this.n = str;
            return this;
        }

        public Builder setSize(AppLovinAdSize appLovinAdSize) {
            this.b = appLovinAdSize;
            return this;
        }

        public Builder setSupplementalClickTrackingUrl(String str) {
            this.m = str;
            return this;
        }

        public Builder setTarget(AdTarget adTarget) {
            this.e = adTarget;
            return this;
        }

        public Builder setType(AppLovinAdType appLovinAdType) {
            this.c = appLovinAdType;
            return this;
        }

        public Builder setUnmuteImageFilename(String str) {
            this.o = str;
            return this;
        }

        public Builder setVideoClickableDuringPlayback(boolean z) {
            this.q = z;
            return this;
        }

        public Builder setVideoCloseDelay(float f) {
            this.g = f;
            return this;
        }

        public Builder setVideoFilename(String str) {
            this.d = str;
            return this;
        }
    }

    private AppLovinAdImpl(String str, AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str2, AdTarget adTarget, v vVar, float f, float f2, int i, long j, String str3, String str4, String str5, String str6, String str7, boolean z, boolean z2, String str8) {
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No size specified");
        } else if (appLovinAdType == null) {
            throw new IllegalArgumentException("No type specified");
        } else {
            this.a = appLovinAdSize;
            this.b = appLovinAdType;
            this.d = str2;
            this.c = j;
            this.g = str;
            this.e = adTarget;
            this.h = f;
            this.j = i;
            this.f = str3;
            this.r = vVar;
            this.i = f2;
            this.k = str4;
            this.l = str5;
            this.m = str6;
            this.n = str7;
            this.o = z;
            this.p = z2;
            this.q = str8;
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) obj;
        if (this.c != appLovinAdImpl.c || Float.compare(appLovinAdImpl.h, this.h) != 0 || Float.compare(appLovinAdImpl.i, this.i) != 0 || this.j != appLovinAdImpl.j) {
            return false;
        }
        if (this.a != null) {
            if (!this.a.equals(appLovinAdImpl.a)) {
                return false;
            }
        } else if (appLovinAdImpl.a != null) {
            return false;
        }
        if (this.b != null) {
            if (!this.b.equals(appLovinAdImpl.b)) {
                return false;
            }
        } else if (appLovinAdImpl.b != null) {
            return false;
        }
        if (this.o != appLovinAdImpl.o) {
            return false;
        }
        if (this.d != null) {
            if (!this.d.equals(appLovinAdImpl.d)) {
                return false;
            }
        } else if (appLovinAdImpl.d != null) {
            return false;
        }
        if (this.e != appLovinAdImpl.e) {
            return false;
        }
        if (this.f != null) {
            if (!this.f.equals(appLovinAdImpl.f)) {
                return false;
            }
        } else if (appLovinAdImpl.f != null) {
            return false;
        }
        if (this.g != null) {
            if (!this.g.equals(appLovinAdImpl.g)) {
                return false;
            }
        } else if (appLovinAdImpl.g != null) {
            return false;
        }
        if (this.k != null) {
            if (!this.k.equals(appLovinAdImpl.k)) {
                return false;
            }
        } else if (appLovinAdImpl.k != null) {
            return false;
        }
        if (this.l != null) {
            if (!this.l.equals(appLovinAdImpl.l)) {
                return false;
            }
        } else if (appLovinAdImpl.l != null) {
            return false;
        }
        if (this.m != null) {
            if (!this.m.equals(appLovinAdImpl.m)) {
                return false;
            }
        } else if (appLovinAdImpl.m != null) {
            return false;
        }
        if (this.n != null) {
            if (!this.n.equals(appLovinAdImpl.n)) {
                return false;
            }
        } else if (appLovinAdImpl.n != null) {
            return false;
        }
        if (this.q != null) {
            if (!this.q.equals(appLovinAdImpl.q)) {
                return false;
            }
        } else if (appLovinAdImpl.q != null) {
            return false;
        }
        if (this.r != appLovinAdImpl.r) {
            z = false;
        }
        return z;
    }

    public long getAdIdNumber() {
        return this.c;
    }

    public String getClCode() {
        return this.f;
    }

    public String getClickDestinationUrl() {
        return this.q;
    }

    public float getCloseDelay() {
        return this.i;
    }

    public v getCloseStyle() {
        return this.r;
    }

    public String getCompletionUrl() {
        return this.k;
    }

    public int getCountdownLength() {
        return this.j;
    }

    public boolean getDismissOnSkip() {
        return this.o;
    }

    public String getHtmlSource() {
        return this.g;
    }

    public String getMuteImageFilename() {
        return this.m;
    }

    public String getParametrizedCompletionUrl(int i, String str) {
        String completionUrl = getCompletionUrl();
        return AppLovinSdkUtils.isValidString(completionUrl) ? dm.a(str, Uri.parse(completionUrl.replace("{CLCODE}", getClCode())).buildUpon().appendQueryParameter(NativeAdImpl.QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).build().toString()) : BuildConfig.FLAVOR;
    }

    public AppLovinAdSize getSize() {
        return this.a;
    }

    public String getSupplementalClickTrackingUrl() {
        return getSupplementalClickTrackingUrl(null);
    }

    public String getSupplementalClickTrackingUrl(String str) {
        String str2 = this.l;
        return AppLovinSdkUtils.isValidString(str2) ? dm.a(str, str2.replace("{CLCODE}", getClCode())) : BuildConfig.FLAVOR;
    }

    public AdTarget getTarget() {
        return this.e;
    }

    public AppLovinAdType getType() {
        return this.b;
    }

    public String getUnmuteImageFilename() {
        return this.n;
    }

    public float getVideoCloseDelay() {
        return this.h;
    }

    public String getVideoFilename() {
        return this.d;
    }

    public int hashCode() {
        int i = 1;
        int hashCode = ((this.r != null ? this.r.hashCode() : 0) + (((this.q != null ? this.q.hashCode() : 0) + (((this.p ? 1 : 0) + (((this.n != null ? this.n.hashCode() : 0) + (((this.m != null ? this.m.hashCode() : 0) + (((this.l != null ? this.l.hashCode() : 0) + (((this.k != null ? this.k.hashCode() : 0) + (((((this.i != 0.0f ? Float.floatToIntBits(this.i) : 0) + (((this.h != 0.0f ? Float.floatToIntBits(this.h) : 0) + (((this.g != null ? this.g.hashCode() : 0) + (((this.f != null ? this.f.hashCode() : 0) + (((this.e != null ? this.e.hashCode() : 0) + (((this.d != null ? this.d.hashCode() : 0) + (((((this.b != null ? this.b.hashCode() : 0) + ((this.a != null ? this.a.hashCode() : 0) * 31)) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.j) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!this.o) {
            i = 0;
        }
        return hashCode + i;
    }

    public boolean isVideoAd() {
        return AppLovinSdkUtils.isValidString(this.d);
    }

    public boolean isVideoClickableDuringPlayback() {
        return this.p;
    }

    public String toString() {
        return "AppLovinAdImpl{size=" + this.a + ", type=" + this.b + ", adIdNumber=" + this.c + ", videoFilename='" + this.d + '\'' + ", target=" + this.e + ", clCode='" + this.f + '\'' + ", htmlSource='" + this.g + '\'' + ", videoCloseDelay=" + this.h + ", closeDelay=" + this.i + ", countdownLength=" + this.j + ", completionUrl='" + this.k + '\'' + ", supplementalClickTrackingUrl='" + this.l + '\'' + ", muteImageFilename='" + this.m + '\'' + ", unmuteImageFilename='" + this.n + '\'' + ", closeStyle=" + this.r + ", dismissOnSkip=" + this.o + ", videoClickableDuringPlayback=" + this.p + '\'' + ", clickDestinationUrl=" + this.q + '\'' + '}';
    }
}
