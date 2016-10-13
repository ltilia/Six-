package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.api.Releasable;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.parser.Yytoken;

@zzhb
public abstract class zzdr implements Releasable {
    protected Context mContext;
    protected String zzzN;
    protected WeakReference<zzjp> zzzO;

    class 1 implements Runnable {
        final /* synthetic */ String zzzP;
        final /* synthetic */ String zzzQ;
        final /* synthetic */ int zzzR;
        final /* synthetic */ int zzzS;
        final /* synthetic */ boolean zzzT;
        final /* synthetic */ zzdr zzzU;

        1(zzdr com_google_android_gms_internal_zzdr, String str, String str2, int i, int i2, boolean z) {
            this.zzzU = com_google_android_gms_internal_zzdr;
            this.zzzP = str;
            this.zzzQ = str2;
            this.zzzR = i;
            this.zzzS = i2;
            this.zzzT = z;
        }

        public void run() {
            Map hashMap = new HashMap();
            hashMap.put(NotificationCompatApi21.CATEGORY_EVENT, "precacheProgress");
            hashMap.put("src", this.zzzP);
            hashMap.put("cachedSrc", this.zzzQ);
            hashMap.put("bytesLoaded", Integer.toString(this.zzzR));
            hashMap.put("totalBytes", Integer.toString(this.zzzS));
            hashMap.put("cacheReady", this.zzzT ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            this.zzzU.zza("onPrecacheEvent", hashMap);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ String zzzP;
        final /* synthetic */ String zzzQ;
        final /* synthetic */ int zzzS;
        final /* synthetic */ zzdr zzzU;

        2(zzdr com_google_android_gms_internal_zzdr, String str, String str2, int i) {
            this.zzzU = com_google_android_gms_internal_zzdr;
            this.zzzP = str;
            this.zzzQ = str2;
            this.zzzS = i;
        }

        public void run() {
            Map hashMap = new HashMap();
            hashMap.put(NotificationCompatApi21.CATEGORY_EVENT, "precacheComplete");
            hashMap.put("src", this.zzzP);
            hashMap.put("cachedSrc", this.zzzQ);
            hashMap.put("totalBytes", Integer.toString(this.zzzS));
            this.zzzU.zza("onPrecacheEvent", hashMap);
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ String zzzP;
        final /* synthetic */ String zzzQ;
        final /* synthetic */ zzdr zzzU;
        final /* synthetic */ String zzzV;
        final /* synthetic */ String zzzW;

        3(zzdr com_google_android_gms_internal_zzdr, String str, String str2, String str3, String str4) {
            this.zzzU = com_google_android_gms_internal_zzdr;
            this.zzzP = str;
            this.zzzQ = str2;
            this.zzzV = str3;
            this.zzzW = str4;
        }

        public void run() {
            Map hashMap = new HashMap();
            hashMap.put(NotificationCompatApi21.CATEGORY_EVENT, "precacheCanceled");
            hashMap.put("src", this.zzzP);
            if (!TextUtils.isEmpty(this.zzzQ)) {
                hashMap.put("cachedSrc", this.zzzQ);
            }
            hashMap.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, this.zzzU.zzW(this.zzzV));
            hashMap.put("reason", this.zzzV);
            if (!TextUtils.isEmpty(this.zzzW)) {
                hashMap.put(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, this.zzzW);
            }
            this.zzzU.zza("onPrecacheEvent", hashMap);
        }
    }

    public zzdr(zzjp com_google_android_gms_internal_zzjp) {
        this.mContext = com_google_android_gms_internal_zzjp.getContext();
        this.zzzN = zzr.zzbC().zze(this.mContext, com_google_android_gms_internal_zzjp.zzhX().afmaVersion);
        this.zzzO = new WeakReference(com_google_android_gms_internal_zzjp);
    }

    private String zzW(String str) {
        String str2 = "internal";
        Object obj = -1;
        switch (str.hashCode()) {
            case -1396664534:
                if (str.equals("badUrl")) {
                    obj = 6;
                    break;
                }
                break;
            case -1347010958:
                if (str.equals("inProgress")) {
                    obj = 2;
                    break;
                }
                break;
            case -918817863:
                if (str.equals("downloadTimeout")) {
                    obj = 7;
                    break;
                }
                break;
            case -659376217:
                if (str.equals("contentLengthMissing")) {
                    obj = 3;
                    break;
                }
                break;
            case -642208130:
                if (str.equals("playerFailed")) {
                    obj = 1;
                    break;
                }
                break;
            case -354048396:
                if (str.equals("sizeExceeded")) {
                    obj = 8;
                    break;
                }
                break;
            case -32082395:
                if (str.equals("externalAbort")) {
                    obj = 9;
                    break;
                }
                break;
            case 96784904:
                if (str.equals(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE)) {
                    obj = null;
                    break;
                }
                break;
            case 580119100:
                if (str.equals("expireFailed")) {
                    obj = 5;
                    break;
                }
                break;
            case 725497484:
                if (str.equals("noCacheDir")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case Yylex.YYINITIAL /*0*/:
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return "internal";
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
            case Yytoken.TYPE_COMMA /*5*/:
                return "io";
            case Yytoken.TYPE_COLON /*6*/:
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                return "network";
            case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
            case R.styleable.Toolbar_popupTheme /*9*/:
                return "policy";
            default:
                return str2;
        }
    }

    private void zza(String str, Map<String, String> map) {
        zzjp com_google_android_gms_internal_zzjp = (zzjp) this.zzzO.get();
        if (com_google_android_gms_internal_zzjp != null) {
            com_google_android_gms_internal_zzjp.zza(str, (Map) map);
        }
    }

    public abstract void abort();

    public void release() {
    }

    public abstract boolean zzU(String str);

    protected String zzV(String str) {
        return zzn.zzcS().zzaH(str);
    }

    protected void zza(String str, String str2, int i) {
        zza.zzMS.post(new 2(this, str, str2, i));
    }

    protected void zza(String str, String str2, int i, int i2, boolean z) {
        zza.zzMS.post(new 1(this, str, str2, i, i2, z));
    }

    protected void zza(String str, String str2, String str3, String str4) {
        zza.zzMS.post(new 3(this, str, str2, str3, str4));
    }
}
