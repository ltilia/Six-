package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.Yytoken;

public class Strategy implements SafeParcelable {
    public static final Strategy BLE_ONLY;
    public static final Creator<Strategy> CREATOR;
    public static final Strategy DEFAULT;
    public static final int DISCOVERY_MODE_BROADCAST = 1;
    public static final int DISCOVERY_MODE_DEFAULT = 3;
    public static final int DISCOVERY_MODE_SCAN = 2;
    public static final int DISTANCE_TYPE_DEFAULT = 0;
    public static final int DISTANCE_TYPE_EARSHOT = 1;
    public static final int TTL_SECONDS_DEFAULT = 300;
    public static final int TTL_SECONDS_INFINITE = Integer.MAX_VALUE;
    public static final int TTL_SECONDS_MAX = 86400;
    @Deprecated
    public static final Strategy zzbbK;
    final int mVersionCode;
    @Deprecated
    final int zzbbL;
    final int zzbbM;
    final int zzbbN;
    @Deprecated
    final boolean zzbbO;
    final int zzbbP;
    final int zzbbQ;

    public static class Builder {
        private int zzbbR;
        private int zzbbS;
        private int zzbbT;
        private int zzbbU;

        public Builder() {
            this.zzbbR = Strategy.DISCOVERY_MODE_DEFAULT;
            this.zzbbS = Strategy.TTL_SECONDS_DEFAULT;
            this.zzbbT = Strategy.DISTANCE_TYPE_DEFAULT;
            this.zzbbU = -1;
        }

        public Strategy build() {
            if (this.zzbbU == Strategy.DISCOVERY_MODE_SCAN && this.zzbbT == Strategy.DISTANCE_TYPE_EARSHOT) {
                throw new IllegalStateException("Cannot set EARSHOT with BLE only mode.");
            }
            return new Strategy(Strategy.DISCOVERY_MODE_SCAN, Strategy.DISTANCE_TYPE_DEFAULT, this.zzbbS, this.zzbbT, false, this.zzbbU, this.zzbbR);
        }

        public Builder setDiscoveryMode(int discoveryMode) {
            this.zzbbR = discoveryMode;
            return this;
        }

        public Builder setDistanceType(int distanceType) {
            this.zzbbT = distanceType;
            return this;
        }

        public Builder setTtlSeconds(int ttlSeconds) {
            boolean z = ttlSeconds == Strategy.TTL_SECONDS_INFINITE || (ttlSeconds > 0 && ttlSeconds <= Strategy.TTL_SECONDS_MAX);
            Object[] objArr = new Object[Strategy.DISCOVERY_MODE_SCAN];
            objArr[Strategy.DISTANCE_TYPE_DEFAULT] = Integer.valueOf(ttlSeconds);
            objArr[Strategy.DISTANCE_TYPE_EARSHOT] = Integer.valueOf(Strategy.TTL_SECONDS_MAX);
            zzx.zzb(z, "mTtlSeconds(%d) must either be TTL_SECONDS_INFINITE, or it must be between 1 and TTL_SECONDS_MAX(%d) inclusive", objArr);
            this.zzbbS = ttlSeconds;
            return this;
        }

        public Builder zzjg(int i) {
            this.zzbbU = i;
            return this;
        }
    }

    static {
        CREATOR = new zzd();
        DEFAULT = new Builder().build();
        BLE_ONLY = new Builder().zzjg(DISCOVERY_MODE_SCAN).setTtlSeconds(TTL_SECONDS_INFINITE).build();
        zzbbK = BLE_ONLY;
    }

    Strategy(int versionCode, int broadcastScanStrategy, int ttlSeconds, int distanceType, boolean isBleBeaconStrategy, int discoveryMedium, int discoveryMode) {
        this.mVersionCode = versionCode;
        this.zzbbL = broadcastScanStrategy;
        if (broadcastScanStrategy != 0) {
            switch (broadcastScanStrategy) {
                case DISCOVERY_MODE_SCAN /*2*/:
                    this.zzbbQ = DISTANCE_TYPE_EARSHOT;
                    break;
                case DISCOVERY_MODE_DEFAULT /*3*/:
                    this.zzbbQ = DISCOVERY_MODE_SCAN;
                    break;
                default:
                    this.zzbbQ = DISCOVERY_MODE_DEFAULT;
                    break;
            }
        }
        this.zzbbQ = discoveryMode;
        this.zzbbN = distanceType;
        this.zzbbO = isBleBeaconStrategy;
        if (isBleBeaconStrategy) {
            this.zzbbP = DISCOVERY_MODE_SCAN;
            this.zzbbM = TTL_SECONDS_INFINITE;
            return;
        }
        this.zzbbM = ttlSeconds;
        switch (discoveryMedium) {
            case Yytoken.TYPE_EOF /*-1*/:
            case DISTANCE_TYPE_DEFAULT /*0*/:
            case DISTANCE_TYPE_EARSHOT /*1*/:
            case Yytoken.TYPE_COLON /*6*/:
                this.zzbbP = -1;
            default:
                this.zzbbP = discoveryMedium;
        }
    }

    private static String zzjd(int i) {
        switch (i) {
            case DISTANCE_TYPE_DEFAULT /*0*/:
                return "DEFAULT";
            case DISTANCE_TYPE_EARSHOT /*1*/:
                return "EARSHOT";
            default:
                return "UNKNOWN:" + i;
        }
    }

    private static String zzje(int i) {
        if (i == -1) {
            return "DEFAULT";
        }
        List arrayList = new ArrayList();
        if ((i & 4) > 0) {
            arrayList.add("AUDIO");
        }
        if ((i & DISCOVERY_MODE_SCAN) > 0) {
            arrayList.add("BLE");
        }
        return arrayList.isEmpty() ? "UNKNOWN:" + i : arrayList.toString();
    }

    private static String zzjf(int i) {
        if (i == DISCOVERY_MODE_DEFAULT) {
            return "DEFAULT";
        }
        List arrayList = new ArrayList();
        if ((i & DISTANCE_TYPE_EARSHOT) > 0) {
            arrayList.add("BROADCAST");
        }
        if ((i & DISCOVERY_MODE_SCAN) > 0) {
            arrayList.add("SCAN");
        }
        return arrayList.isEmpty() ? "UNKNOWN:" + i : arrayList.toString();
    }

    public int describeContents() {
        return DISTANCE_TYPE_DEFAULT;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Strategy)) {
            return false;
        }
        Strategy strategy = (Strategy) other;
        return this.mVersionCode == strategy.mVersionCode && this.zzbbQ == strategy.zzbbQ && this.zzbbM == strategy.zzbbM && this.zzbbN == strategy.zzbbN && this.zzbbP == strategy.zzbbP;
    }

    public int hashCode() {
        return (((((((this.mVersionCode * 31) + this.zzbbQ) * 31) + this.zzbbM) * 31) + this.zzbbN) * 31) + this.zzbbP;
    }

    public String toString() {
        return "Strategy{ttlSeconds=" + this.zzbbM + ", distanceType=" + zzjd(this.zzbbN) + ", discoveryMedium=" + zzje(this.zzbbP) + ", discoveryMode=" + zzjf(this.zzbbQ) + '}';
    }

    public void writeToParcel(Parcel out, int flags) {
        zzd.zza(this, out, flags);
    }

    public int zzEr() {
        return this.zzbbP;
    }

    public int zzEs() {
        return this.zzbbQ;
    }
}
