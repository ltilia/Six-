package com.adjust.sdk;

import android.net.Uri;

public class AdjustInstance {
    private ActivityHandler activityHandler;
    private String referrer;
    private long referrerClickTime;

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    public void onCreate(AdjustConfig adjustConfig) {
        if (this.activityHandler != null) {
            getLogger().error("Adjust already initialized", new Object[0]);
            return;
        }
        adjustConfig.referrer = this.referrer;
        adjustConfig.referrerClickTime = this.referrerClickTime;
        this.activityHandler = ActivityHandler.getInstance(adjustConfig);
    }

    public void trackEvent(AdjustEvent event) {
        if (checkActivityHandler()) {
            this.activityHandler.trackEvent(event);
        }
    }

    public void onResume() {
        if (checkActivityHandler()) {
            this.activityHandler.trackSubsessionStart();
        }
    }

    public void onPause() {
        if (checkActivityHandler()) {
            this.activityHandler.trackSubsessionEnd();
        }
    }

    public void setEnabled(boolean enabled) {
        if (checkActivityHandler()) {
            this.activityHandler.setEnabled(enabled);
        }
    }

    public boolean isEnabled() {
        if (checkActivityHandler()) {
            return this.activityHandler.isEnabled();
        }
        return false;
    }

    public void appWillOpenUrl(Uri url) {
        if (checkActivityHandler()) {
            this.activityHandler.readOpenUrl(url, System.currentTimeMillis());
        }
    }

    public void sendReferrer(String referrer) {
        long clickTime = System.currentTimeMillis();
        if (this.activityHandler == null) {
            this.referrer = referrer;
            this.referrerClickTime = clickTime;
            return;
        }
        this.activityHandler.sendReferrer(referrer, clickTime);
    }

    public void setOfflineMode(boolean enabled) {
        if (checkActivityHandler()) {
            this.activityHandler.setOfflineMode(enabled);
        }
    }

    private boolean checkActivityHandler() {
        if (this.activityHandler != null) {
            return true;
        }
        getLogger().error("Adjust not initialized correctly", new Object[0]);
        return false;
    }
}
