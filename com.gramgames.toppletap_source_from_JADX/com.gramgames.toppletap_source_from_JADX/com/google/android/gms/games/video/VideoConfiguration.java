package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import org.json.simple.parser.Yytoken;

public final class VideoConfiguration implements SafeParcelable {
    public static final Creator<VideoConfiguration> CREATOR;
    private final int mVersionCode;
    private final int zzaLr;
    private final int zzaLs;
    private final String zzaLt;
    private final String zzaLu;

    public static final class Builder {
    }

    static {
        CREATOR = new VideoConfigurationCreator();
    }

    public VideoConfiguration(int versionCode, int qualityLevel, int captureMode, String streamUrl, String streamKey) {
        boolean z = true;
        this.mVersionCode = versionCode;
        zzx.zzac(zzgZ(qualityLevel));
        zzx.zzac(zzha(captureMode));
        this.zzaLr = qualityLevel;
        this.zzaLs = captureMode;
        if (captureMode == 1) {
            this.zzaLu = streamKey;
            this.zzaLt = streamUrl;
            return;
        }
        zzx.zzb(streamKey == null, (Object) "Stream key should be null when not streaming");
        if (streamUrl != null) {
            z = false;
        }
        zzx.zzb(z, (Object) "Stream url should be null when not streaming");
        this.zzaLu = null;
        this.zzaLt = null;
    }

    public static boolean zzgZ(int i) {
        switch (i) {
            case Yylex.YYINITIAL /*0*/:
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean zzha(int i) {
        switch (i) {
            case Yylex.YYINITIAL /*0*/:
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return true;
            default:
                return false;
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getStreamUrl() {
        return this.zzaLt;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        VideoConfigurationCreator.zza(this, out, flags);
    }

    public int zzyd() {
        return this.zzaLr;
    }

    public int zzye() {
        return this.zzaLs;
    }

    public String zzyf() {
        return this.zzaLu;
    }
}
