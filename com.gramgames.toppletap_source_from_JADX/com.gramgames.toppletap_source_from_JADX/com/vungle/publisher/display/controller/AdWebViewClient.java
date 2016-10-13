package com.vungle.publisher.display.controller;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.vungle.log.Logger;
import com.vungle.publisher.aw;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.util.ViewUtils;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdWebViewClient extends WebViewClient {
    @Inject
    public EventBus a;
    @Inject
    public WebViewConfig b;

    @Inject
    AdWebViewClient() {
    }

    public void onReceivedError(WebView webView, int code, String desc, String str) {
        Logger.e(Logger.AD_TAG, "failed to load web view: code " + code + ", " + desc);
        this.a.a(new aw());
    }

    public void onPageFinished(WebView webView, String url) {
        Logger.v(Logger.AD_TAG, "webview finished loading. appending config string");
        if (!url.toLowerCase().startsWith("javascript:")) {
            StringBuilder stringBuilder = new StringBuilder("javascript:function actionClicked(m,p){ var q = prompt('vungle:'+JSON.stringify({method:m,params:(p?p:null)}));if(q&&typeof q === 'string'){return JSON.parse(q).result;}};function noTapHighlight(){var l=document.getElementsByTagName('*');for(var i=0; i<l.length; i++){l[i].style.webkitTapHighlightColor='rgba(0,0,0,0)';}};noTapHighlight();");
            try {
                stringBuilder.append("if (typeof vungleInit == 'function') {vungleInit($webviewConfig$);};".replace("$webviewConfig$", this.b.c()));
            } catch (Throwable e) {
                Logger.e(Logger.AD_TAG, "webview failed to load config object", e);
            }
            String stringBuilder2 = stringBuilder.toString();
            Logger.v(Logger.AD_TAG, "webview client injecting javascript: " + stringBuilder2);
            webView.loadUrl(stringBuilder2);
            ViewUtils.b(webView);
        }
    }
}
