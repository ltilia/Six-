package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangesAvailableEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.QueryResultEventParcelable;
import com.google.android.gms.drive.events.TransferProgressEvent;
import com.google.android.gms.drive.events.TransferStateEvent;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class OnEventResponse implements SafeParcelable {
    public static final Creator<OnEventResponse> CREATOR;
    final int mVersionCode;
    final int zzanf;
    final ChangeEvent zzasl;
    final CompletionEvent zzasm;
    final QueryResultEventParcelable zzasn;
    final ChangesAvailableEvent zzaso;
    final TransferStateEvent zzasp;
    final TransferProgressEvent zzasq;

    static {
        CREATOR = new zzba();
    }

    OnEventResponse(int versionCode, int eventType, ChangeEvent changeEvent, CompletionEvent completionEvent, QueryResultEventParcelable queryResultEvent, ChangesAvailableEvent changesAvailableEvent, TransferStateEvent transferStateEvent, TransferProgressEvent transferProgressEvent) {
        this.mVersionCode = versionCode;
        this.zzanf = eventType;
        this.zzasl = changeEvent;
        this.zzasm = completionEvent;
        this.zzasn = queryResultEvent;
        this.zzaso = changesAvailableEvent;
        this.zzasp = transferStateEvent;
        this.zzasq = transferProgressEvent;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzba.zza(this, dest, flags);
    }

    public DriveEvent zzts() {
        switch (this.zzanf) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return this.zzasl;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return this.zzasm;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return this.zzasn;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return this.zzaso;
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                return this.zzasp;
            case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                return this.zzasq;
            default:
                throw new IllegalStateException("Unexpected event type " + this.zzanf);
        }
    }
}
