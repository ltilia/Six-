package com.gramgames.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class UnityPlayerActivity extends com.unity3d.player.UnityPlayerActivity {
    private static final String TAG = "[GG-UnityPlayerActivity]";
    private ActivityProxyManager m_activityProxyManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Activity Proxy Manager initializing.");
        this.m_activityProxyManager = new ActivityProxyManager(this);
        this.m_activityProxyManager.init();
        this.m_activityProxyManager.invokeMethodWithObject("onCreate", savedInstanceState);
        Log.d(TAG, "Activity Proxy Manager initializing successfully done.");
    }

    protected void onDestroy() {
        super.onDestroy();
        this.m_activityProxyManager.invokeMethod("onDestroy");
    }

    protected void onPause() {
        super.onPause();
        this.m_activityProxyManager.invokeMethod("onPause");
    }

    protected void onResume() {
        super.onResume();
        this.m_activityProxyManager.invokeMethod("onResume");
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.m_activityProxyManager.invokeMethodWithObject("onConfigurationChanged", newConfig);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.m_activityProxyManager.invokeMethodWithBoolean("onWindowFocusChanged", hasFocus);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean result = super.onKeyUp(keyCode, event);
        this.m_activityProxyManager.invokeMethodWithKeyEvent("onKeyUp", keyCode, event);
        return result;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean result = super.onKeyDown(keyCode, event);
        this.m_activityProxyManager.invokeMethodWithKeyEvent("onKeyDown", keyCode, event);
        return result;
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.m_activityProxyManager.invokeMethodWithObject("onNewIntent", intent);
    }

    protected void onStop() {
        super.onStop();
        this.m_activityProxyManager.invokeMethod("onStop");
    }

    protected void onRestart() {
        super.onRestart();
        this.m_activityProxyManager.invokeMethod("onRestart");
    }

    protected void onStart() {
        super.onStart();
        this.m_activityProxyManager.invokeMethod("onStart");
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.m_activityProxyManager.invokeMethod("onLowMemory");
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        this.m_activityProxyManager.invokeMethodWithInteger("onTrimMemory", level);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.m_activityProxyManager.invokeMethod("onBackPressed");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.m_activityProxyManager.invokeMethodForActivityResult(requestCode, resultCode, data);
    }
}
