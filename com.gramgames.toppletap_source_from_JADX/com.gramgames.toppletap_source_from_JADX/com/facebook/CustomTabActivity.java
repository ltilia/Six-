package com.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public class CustomTabActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, FacebookActivity.class);
        intent.putExtra(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, getIntent().getDataString());
        intent.addFlags(603979776);
        startActivity(intent);
    }
}
