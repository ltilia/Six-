package com.vungle.publisher;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: vungle */
public enum Orientation implements Parcelable {
    autoRotate,
    matchVideo;
    
    public static final Creator<Orientation> CREATOR;

    static class 1 implements Creator<Orientation> {
        1() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return Orientation.values()[parcel.readInt()];
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new Orientation[i];
        }
    }

    static {
        CREATOR = new 1();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
