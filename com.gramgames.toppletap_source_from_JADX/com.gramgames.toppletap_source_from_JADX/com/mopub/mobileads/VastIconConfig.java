package com.mopub.mobileads;

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
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.List;

class VastIconConfig implements Serializable {
    private static final long serialVersionUID = 0;
    @Nullable
    private final String mClickThroughUri;
    @NonNull
    private final List<VastTracker> mClickTrackingUris;
    @Nullable
    private final Integer mDurationMS;
    private final int mHeight;
    private final int mOffsetMS;
    @NonNull
    private final VastResource mVastResource;
    @NonNull
    private final List<VastTracker> mViewTrackingUris;
    private final int mWidth;

    class 1 implements ResultActions {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$dspCreativeId;

        1(String str, Context context) {
            this.val$dspCreativeId = str;
            this.val$context = context;
        }

        public void urlHandlingSucceeded(@NonNull String url, @NonNull UrlAction urlAction) {
            if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                Bundle bundle = new Bundle();
                bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, url);
                if (!TextUtils.isEmpty(this.val$dspCreativeId)) {
                    bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, this.val$dspCreativeId);
                }
                try {
                    Intents.startActivity(this.val$context, Intents.getStartActivityIntent(this.val$context, MoPubBrowser.class, bundle));
                } catch (IntentNotResolvableException e) {
                    MoPubLog.d(e.getMessage());
                }
            }
        }

        public void urlHandlingFailed(@NonNull String url, @NonNull UrlAction lastFailedUrlAction) {
        }
    }

    VastIconConfig(int width, int height, @Nullable Integer offsetMS, @Nullable Integer durationMS, @NonNull VastResource vastResource, @NonNull List<VastTracker> clickTrackingUris, @Nullable String clickThroughUri, @NonNull List<VastTracker> viewTrackingUris) {
        Preconditions.checkNotNull(vastResource);
        Preconditions.checkNotNull(clickTrackingUris);
        Preconditions.checkNotNull(viewTrackingUris);
        this.mWidth = width;
        this.mHeight = height;
        this.mOffsetMS = offsetMS == null ? 0 : offsetMS.intValue();
        this.mDurationMS = durationMS;
        this.mVastResource = vastResource;
        this.mClickTrackingUris = clickTrackingUris;
        this.mClickThroughUri = clickThroughUri;
        this.mViewTrackingUris = viewTrackingUris;
    }

    int getWidth() {
        return this.mWidth;
    }

    int getHeight() {
        return this.mHeight;
    }

    int getOffsetMS() {
        return this.mOffsetMS;
    }

    @Nullable
    Integer getDurationMS() {
        return this.mDurationMS;
    }

    @NonNull
    VastResource getVastResource() {
        return this.mVastResource;
    }

    @NonNull
    List<VastTracker> getClickTrackingUris() {
        return this.mClickTrackingUris;
    }

    @Nullable
    String getClickThroughUri() {
        return this.mClickThroughUri;
    }

    @NonNull
    List<VastTracker> getViewTrackingUris() {
        return this.mViewTrackingUris;
    }

    void handleImpression(@NonNull Context context, int contentPlayHead, @NonNull String assetUri) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(assetUri);
        TrackingRequest.makeVastTrackingHttpRequest(this.mViewTrackingUris, null, Integer.valueOf(contentPlayHead), assetUri, context);
    }

    void handleClick(@NonNull Context context, @Nullable String webViewClickThroughUri, @Nullable String dspCreativeId) {
        Preconditions.checkNotNull(context);
        String correctClickThroughUri = this.mVastResource.getCorrectClickThroughUrl(this.mClickThroughUri, webViewClickThroughUri);
        if (!TextUtils.isEmpty(correctClickThroughUri)) {
            new Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER).withResultActions(new 1(dspCreativeId, context)).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUri);
        }
    }
}
