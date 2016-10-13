package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.InterstitialAdActivity.Type;
import com.facebook.ads.internal.util.f;
import com.facebook.ads.internal.util.h;
import com.google.android.gms.drive.DriveFile;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class j extends InterstitialAdapter {
    private final String a;
    private Context b;
    private boolean c;
    private int d;
    private int e;
    private boolean f;
    private d g;
    private InterstitialAdapterListener h;
    private boolean i;
    private l j;
    private WebView k;

    class 1 extends WebChromeClient {
        final /* synthetic */ j a;

        1(j jVar) {
            this.a = jVar;
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i == 100 && this.a.h != null) {
                this.a.h.onInterstitialAdLoaded(this.a);
            }
        }
    }

    public j() {
        this.a = UUID.randomUUID().toString();
        this.i = false;
    }

    private void a(l lVar) {
        if (lVar != null) {
            this.k = new WebView(this.b.getApplicationContext());
            this.k.setWebChromeClient(new 1(this));
            this.k.loadDataWithBaseURL(h.a(), lVar.d(), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
        }
    }

    public void loadInterstitialAd(Context context, InterstitialAdapterListener interstitialAdapterListener, Map<String, Object> map) {
        this.b = context;
        this.h = interstitialAdapterListener;
        this.j = l.a((JSONObject) map.get(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY));
        if (f.a(context, this.j)) {
            interstitialAdapterListener.onInterstitialError(this, AdError.NO_FILL);
            return;
        }
        this.g = new d(context, this.a, this, this.h);
        this.g.a();
        this.i = true;
        Map h = this.j.h();
        if (h.containsKey("is_tablet")) {
            this.c = Boolean.parseBoolean((String) h.get("is_tablet"));
        }
        if (h.containsKey("ad_height")) {
            this.d = Integer.parseInt((String) h.get("ad_height"));
        }
        if (h.containsKey("ad_width")) {
            this.e = Integer.parseInt((String) h.get("ad_width"));
        }
        if (h.containsKey("native_close")) {
            this.f = Boolean.valueOf((String) h.get("native_close")).booleanValue();
        }
        if (h.containsKey("preloadMarkup") && Boolean.parseBoolean((String) h.get("preloadMarkup"))) {
            a(this.j);
        } else if (this.h != null) {
            this.h.onInterstitialAdLoaded(this);
        }
    }

    public void onDestroy() {
        if (this.g != null) {
            this.g.b();
        }
        if (this.k != null) {
            h.a(this.k);
            this.k.destroy();
            this.k = null;
        }
    }

    public boolean show() {
        if (this.i) {
            Intent intent = new Intent(this.b, InterstitialAdActivity.class);
            this.j.a(intent);
            Display defaultDisplay = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            intent.putExtra("displayRotation", defaultDisplay.getRotation());
            intent.putExtra("displayWidth", displayMetrics.widthPixels);
            intent.putExtra("displayHeight", displayMetrics.heightPixels);
            intent.putExtra("isTablet", this.c);
            intent.putExtra(GooglePlayServicesBanner.AD_HEIGHT_KEY, this.d);
            intent.putExtra(GooglePlayServicesBanner.AD_WIDTH_KEY, this.e);
            intent.putExtra("adInterstitialUniqueId", this.a);
            intent.putExtra("useNativeCloseButton", this.f);
            intent.putExtra(InterstitialAdActivity.VIEW_TYPE, Type.DISPLAY);
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            this.b.startActivity(intent);
            return true;
        }
        if (this.h != null) {
            this.h.onInterstitialError(this, AdError.INTERNAL_ERROR);
        }
        return false;
    }
}
