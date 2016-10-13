package com.google.android.gms.drive;

import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.internal.zzu;

public class ExecutionOptions {
    public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
    public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
    public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
    private final String zzaoV;
    private final boolean zzaoW;
    private final int zzaoX;

    public static class Builder {
        protected String zzaoV;
        protected boolean zzaoW;
        protected int zzaoX;

        public Builder() {
            this.zzaoX = ExecutionOptions.CONFLICT_STRATEGY_OVERWRITE_REMOTE;
        }

        public ExecutionOptions build() {
            zzsE();
            return new ExecutionOptions(this.zzaoV, this.zzaoW, this.zzaoX);
        }

        public Builder setConflictStrategy(int strategy) {
            if (ExecutionOptions.zzcw(strategy)) {
                this.zzaoX = strategy;
                return this;
            }
            throw new IllegalArgumentException("Unrecognized value for conflict strategy: " + strategy);
        }

        public Builder setNotifyOnCompletion(boolean notify) {
            this.zzaoW = notify;
            return this;
        }

        public Builder setTrackingTag(String trackingTag) {
            if (ExecutionOptions.zzcX(trackingTag)) {
                this.zzaoV = trackingTag;
                return this;
            }
            Object[] objArr = new Object[ExecutionOptions.CONFLICT_STRATEGY_KEEP_REMOTE];
            objArr[ExecutionOptions.CONFLICT_STRATEGY_OVERWRITE_REMOTE] = Integer.valueOf(ExecutionOptions.MAX_TRACKING_TAG_STRING_LENGTH);
            throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", objArr));
        }

        protected void zzsE() {
            if (this.zzaoX == ExecutionOptions.CONFLICT_STRATEGY_KEEP_REMOTE && !this.zzaoW) {
                throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
            }
        }
    }

    public ExecutionOptions(String trackingTag, boolean notifyOnCompletion, int conflictStrategy) {
        this.zzaoV = trackingTag;
        this.zzaoW = notifyOnCompletion;
        this.zzaoX = conflictStrategy;
    }

    public static boolean zzcX(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= MAX_TRACKING_TAG_STRING_LENGTH;
    }

    public static boolean zzcv(int i) {
        switch (i) {
            case CONFLICT_STRATEGY_KEEP_REMOTE /*1*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean zzcw(int i) {
        switch (i) {
            case CONFLICT_STRATEGY_OVERWRITE_REMOTE /*0*/:
            case CONFLICT_STRATEGY_KEEP_REMOTE /*1*/:
                return true;
            default:
                return false;
        }
    }

    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        if (o == this) {
            return true;
        }
        ExecutionOptions executionOptions = (ExecutionOptions) o;
        return zzw.equal(this.zzaoV, executionOptions.zzaoV) && this.zzaoX == executionOptions.zzaoX && this.zzaoW == executionOptions.zzaoW;
    }

    public int hashCode() {
        return zzw.hashCode(this.zzaoV, Integer.valueOf(this.zzaoX), Boolean.valueOf(this.zzaoW));
    }

    public void zzg(GoogleApiClient googleApiClient) {
        zzu com_google_android_gms_drive_internal_zzu = (zzu) googleApiClient.zza(Drive.zzUI);
        if (zzsC() && !com_google_android_gms_drive_internal_zzu.zzti()) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
        }
    }

    public String zzsB() {
        return this.zzaoV;
    }

    public boolean zzsC() {
        return this.zzaoW;
    }

    public int zzsD() {
        return this.zzaoX;
    }
}
