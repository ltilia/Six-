package com.unity3d.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.drive.ExecutionOptions;

public class UnityPlayerProxyActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        intent.addFlags(ExecutionOptions.MAX_TRACKING_TAG_STRING_LENGTH);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        startActivity(intent);
    }
}
