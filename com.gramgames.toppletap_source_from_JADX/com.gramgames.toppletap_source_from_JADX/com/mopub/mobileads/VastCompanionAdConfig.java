package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.List;

public class VastCompanionAdConfig implements Serializable {
    private static final long serialVersionUID = 0;
    @Nullable
    private final String mClickThroughUrl;
    @NonNull
    private final List<VastTracker> mClickTrackers;
    @NonNull
    private final List<VastTracker> mCreativeViewTrackers;
    private final int mHeight;
    @NonNull
    private final VastResource mVastResource;
    private final int mWidth;

    class 1 implements ResultActions {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$dspCreativeId;
        final /* synthetic */ int val$requestCode;

        1(String str, Context context, int i) {
            this.val$dspCreativeId = str;
            this.val$context = context;
            this.val$requestCode = i;
        }

        public void urlHandlingSucceeded(@NonNull String url, @NonNull UrlAction urlAction) {
            if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                Bundle bundle = new Bundle();
                bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, url);
                if (!TextUtils.isEmpty(this.val$dspCreativeId)) {
                    bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, this.val$dspCreativeId);
                }
                Class clazz = MoPubBrowser.class;
                try {
                    ((Activity) this.val$context).startActivityForResult(Intents.getStartActivityIntent(this.val$context, clazz, bundle), this.val$requestCode);
                } catch (ActivityNotFoundException e) {
                    MoPubLog.d("Activity " + clazz.getName() + " not found. Did you " + "declare it in your AndroidManifest.xml?");
                }
            }
        }

        public void urlHandlingFailed(@NonNull String url, @NonNull UrlAction lastFailedUrlAction) {
        }
    }

    public VastCompanionAdConfig(int width, int height, @NonNull VastResource vastResource, @Nullable String clickThroughUrl, @NonNull List<VastTracker> clickTrackers, @NonNull List<VastTracker> creativeViewTrackers) {
        Preconditions.checkNotNull(vastResource);
        Preconditions.checkNotNull(clickTrackers, "clickTrackers cannot be null");
        Preconditions.checkNotNull(creativeViewTrackers, "creativeViewTrackers cannot be null");
        this.mWidth = width;
        this.mHeight = height;
        this.mVastResource = vastResource;
        this.mClickThroughUrl = clickThroughUrl;
        this.mClickTrackers = clickTrackers;
        this.mCreativeViewTrackers = creativeViewTrackers;
    }

    public void addClickTrackers(@NonNull List<VastTracker> clickTrackers) {
        Preconditions.checkNotNull(clickTrackers, "clickTrackers cannot be null");
        this.mClickTrackers.addAll(clickTrackers);
    }

    public void addCreativeViewTrackers(@NonNull List<VastTracker> creativeViewTrackers) {
        Preconditions.checkNotNull(creativeViewTrackers, "creativeViewTrackers cannot be null");
        this.mCreativeViewTrackers.addAll(creativeViewTrackers);
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @NonNull
    public VastResource getVastResource() {
        return this.mVastResource;
    }

    @Nullable
    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    @NonNull
    public List<VastTracker> getClickTrackers() {
        return this.mClickTrackers;
    }

    @NonNull
    public List<VastTracker> getCreativeViewTrackers() {
        return this.mCreativeViewTrackers;
    }

    void handleImpression(@NonNull Context context, int contentPlayHead) {
        Preconditions.checkNotNull(context);
        TrackingRequest.makeVastTrackingHttpRequest(this.mCreativeViewTrackers, null, Integer.valueOf(contentPlayHead), null, context);
    }

    void handleClick(@NonNull Context context, int requestCode, @Nullable String webViewClickThroughUrl, @Nullable String dspCreativeId) {
        Preconditions.checkNotNull(context);
        Preconditions.checkArgument(context instanceof Activity, "context must be an activity");
        String correctClickThroughUrl = this.mVastResource.getCorrectClickThroughUrl(this.mClickThroughUrl, webViewClickThroughUrl);
        if (!TextUtils.isEmpty(correctClickThroughUrl)) {
            new Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new 1(dspCreativeId, context, requestCode)).withDspCreativeId(dspCreativeId).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
        }
    }
}
