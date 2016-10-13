package com.google.android.gms.internal;

import com.google.android.gms.drive.FileUploadPreferences;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

public class zzsl {
    private final byte[] zzbtW;
    private int zzbtX;
    private int zzbtY;

    public zzsl(byte[] bArr) {
        int i;
        this.zzbtW = new byte[FileUploadPreferences.BATTERY_USAGE_UNRESTRICTED];
        for (i = 0; i < FileUploadPreferences.BATTERY_USAGE_UNRESTRICTED; i++) {
            this.zzbtW[i] = (byte) i;
        }
        i = 0;
        for (int i2 = 0; i2 < FileUploadPreferences.BATTERY_USAGE_UNRESTRICTED; i2++) {
            i = ((i + this.zzbtW[i2]) + bArr[i2 % bArr.length]) & RadialCountdown.PROGRESS_ALPHA;
            byte b = this.zzbtW[i2];
            this.zzbtW[i2] = this.zzbtW[i];
            this.zzbtW[i] = b;
        }
        this.zzbtX = 0;
        this.zzbtY = 0;
    }

    public void zzC(byte[] bArr) {
        int i = this.zzbtX;
        int i2 = this.zzbtY;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & RadialCountdown.PROGRESS_ALPHA;
            i2 = (i2 + this.zzbtW[i]) & RadialCountdown.PROGRESS_ALPHA;
            byte b = this.zzbtW[i];
            this.zzbtW[i] = this.zzbtW[i2];
            this.zzbtW[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.zzbtW[(this.zzbtW[i] + this.zzbtW[i2]) & RadialCountdown.PROGRESS_ALPHA]);
        }
        this.zzbtX = i;
        this.zzbtY = i2;
    }
}
