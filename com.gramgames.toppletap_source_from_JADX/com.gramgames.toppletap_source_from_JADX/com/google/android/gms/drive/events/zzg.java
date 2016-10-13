package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class zzg {
    public static boolean zza(int i, DriveId driveId) {
        switch (i) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                return driveId != null;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                return driveId == null;
            default:
                return false;
        }
    }
}
