package com.vungle.publisher.ad;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.h;

/* compiled from: vungle */
public class SafeBundleAdConfig extends h implements Parcelable {
    public static final Creator<SafeBundleAdConfig> CREATOR;
    static final Orientation c;

    static class 1 implements Creator<SafeBundleAdConfig> {
        1() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new SafeBundleAdConfig(new h[0]).a(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new SafeBundleAdConfig[i];
        }
    }

    static {
        c = Orientation.matchVideo;
        CREATOR = new 1();
    }

    public SafeBundleAdConfig(h... bundleAdConfigs) {
        if (bundleAdConfigs != null) {
            for (h hVar : bundleAdConfigs) {
                if (hVar != null) {
                    this.a.putAll(hVar.a);
                    this.b.putAll(hVar.b);
                }
            }
        }
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.a.getBoolean("isBackButtonEnabled", false);
    }

    public boolean isImmersiveMode() {
        return this.a.getBoolean("isImmersiveMode", false);
    }

    public boolean isIncentivized() {
        return this.a.getBoolean("isIncentivized", false);
    }

    public String getIncentivizedCancelDialogBodyText() {
        String string = this.a.getString("incentivizedCancelDialogBodyText");
        if (string == null) {
            return "Closing this video early will prevent you from earning your reward. Are you sure?";
        }
        return string;
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        String string = this.a.getString("incentivizedCancelDialogNegativeButtonText");
        if (string == null) {
            return "Close video";
        }
        return string;
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        String string = this.a.getString("incentivizedCancelDialogPositiveButtonText");
        if (string == null) {
            return "Keep watching";
        }
        return string;
    }

    public String getIncentivizedCancelDialogTitle() {
        String string = this.a.getString("incentivizedCancelDialogTitle");
        if (string == null) {
            return "Close video?";
        }
        return string;
    }

    public Orientation getOrientation() {
        Orientation orientation = (Orientation) this.a.getParcelable("orientation");
        return orientation == null ? c : orientation;
    }

    public boolean isSoundEnabled() {
        return this.a.getBoolean("isSoundEnabled", true);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
        parcel.writeBundle(this.b);
    }

    protected final SafeBundleAdConfig a(Parcel parcel) {
        ClassLoader classLoader = SafeBundleAdConfig.class.getClassLoader();
        this.a = parcel.readBundle(classLoader);
        this.b = parcel.readBundle(classLoader);
        return this;
    }
}
