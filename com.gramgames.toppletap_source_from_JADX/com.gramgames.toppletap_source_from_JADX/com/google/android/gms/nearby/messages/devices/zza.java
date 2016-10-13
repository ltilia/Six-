package com.google.android.gms.nearby.messages.devices;

import java.util.Arrays;

abstract class zza {
    private static final char[] zzbbX;
    private final byte[] zzbbY;

    static {
        zzbbX = "0123456789abcdef".toCharArray();
    }

    protected zza(byte[] bArr) {
        this.zzbbY = bArr;
    }

    static String zzt(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            stringBuilder.append(zzbbX[(b >> 4) & 15]).append(zzbbX[b & 15]);
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!o.getClass().isAssignableFrom(getClass())) {
            return false;
        }
        return Arrays.equals(this.zzbbY, ((zza) o).zzbbY);
    }

    byte[] getBytes() {
        return this.zzbbY;
    }

    public int hashCode() {
        return Arrays.hashCode(this.zzbbY);
    }

    public String toString() {
        return zzt(this.zzbbY);
    }

    String zzEt() {
        return zzt(this.zzbbY);
    }
}
