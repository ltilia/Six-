package com.vungle.publisher.display.controller;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.ac;
import com.vungle.publisher.ad;
import com.vungle.publisher.ah;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.l;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class AdWebChromeClient extends WebChromeClient {
    @Inject
    public EventBus a;

    @Inject
    AdWebChromeClient() {
    }

    public boolean onJsPrompt(WebView webView, String str, String message, String str2, JsPromptResult result) {
        Logger.v(Logger.AD_TAG, "js prompt: " + message);
        boolean startsWith = message.startsWith("vungle:");
        if (startsWith) {
            String str3 = null;
            try {
                str3 = message.substring(7);
                JSONObject jSONObject = new JSONObject(str3);
                String string = jSONObject.getString("method");
                jSONObject.getString(NativeProtocol.WEB_DIALOG_PARAMS);
                if (UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE.equalsIgnoreCase(string)) {
                    this.a.a(new ac());
                } else if ("download".equalsIgnoreCase(string)) {
                    this.a.a(new l(a.postroll_click));
                } else if ("replay".equalsIgnoreCase(string)) {
                    this.a.a(new ad());
                } else if (ShareConstants.WEB_DIALOG_PARAM_PRIVACY.equalsIgnoreCase(string)) {
                    this.a.a(new ah());
                } else {
                    Logger.w(Logger.AD_TAG, "unknown javascript method: " + string);
                }
            } catch (IndexOutOfBoundsException e) {
                Logger.w(Logger.AD_TAG, "no javascript method call");
            } catch (Throwable e2) {
                Logger.w(Logger.AD_TAG, "invalid json " + str3, e2);
            } catch (Throwable e22) {
                Logger.e(Logger.AD_TAG, e22);
            }
            result.cancel();
        }
        return startsWith;
    }
}
