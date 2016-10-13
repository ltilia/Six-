package com.prime31;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import com.applovin.sdk.AppLovinEventParameters;

public class GoogleIABProxyActivity extends Activity {
    private static final int RC_REQUEST = 10001;
    private static final String TAG = "Prime31-Proxy";
    private Boolean _created;
    private Boolean _didCompletePurcaseFlow;

    public GoogleIABProxyActivity() {
        this._created = Boolean.valueOf(false);
        this._didCompletePurcaseFlow = Boolean.valueOf(false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (this._created.booleanValue()) {
            Log.i("Prime31", "activity created twice. stopping one instance");
            return;
        }
        this._created = Boolean.valueOf(true);
        try {
            String sku = getIntent().getExtras().getString(AppLovinEventParameters.PRODUCT_IDENTIFIER);
            String itemType = getIntent().getExtras().getString("itemType");
            String developerPayload = getIntent().getExtras().getString("developerPayload");
            Log.i(TAG, "proxy received action. sku: " + sku);
            if (!GoogleIABPluginBase.instance().helper.launchPurchaseFlow(this, sku, itemType, RC_REQUEST, GoogleIABPluginBase.instance(), developerPayload)) {
                finish();
            }
        } catch (Exception e) {
            Log.i(TAG, "unhandled exception while attempting to purchase item: " + e.getMessage());
            Log.i(TAG, "going to end the async operation with null data to clear out the queue");
            this._didCompletePurcaseFlow = Boolean.valueOf(true);
            if (GoogleIABPluginBase.instance().helper == null) {
                Log.e(TAG, "FATAL ERROR: Plugin singleton helper is null. Aborting operation.");
            } else {
                GoogleIABPluginBase.instance().helper.handleActivityResult(RC_REQUEST, 0, null);
            }
            finish();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this._didCompletePurcaseFlow = Boolean.valueOf(true);
        IABConstants.logEntering(getClass().getSimpleName(), "onActivityResult", new Object[]{Integer.valueOf(requestCode), Integer.valueOf(resultCode), data});
        if (GoogleIABPluginBase.instance().helper == null) {
            Log.e(TAG, "FATAL ERROR: Plugin singleton helper is null in onActivityResult. Attempting to abort operation to avoid a crash.");
            super.onActivityResult(requestCode, resultCode, data);
            finish();
            return;
        }
        if (GoogleIABPluginBase.instance().helper.handleActivityResult(requestCode, resultCode, data)) {
            Log.d(TAG, "onActivityResult handled by IABUtil. All done here.");
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        finish();
    }

    public void onStop() {
        super.onStop();
        if (!this._didCompletePurcaseFlow.booleanValue()) {
            Log.d(TAG, "in onStop but we didnt complete the purchase flow. Canceling it now.");
            GoogleIABPluginBase.instance().helper.handleActivityResult(RC_REQUEST, 0, null);
        }
        Log.d(TAG, "GoogleIABProxyActivity onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "GoogleIABProxyActivity onDestroy");
    }
}
