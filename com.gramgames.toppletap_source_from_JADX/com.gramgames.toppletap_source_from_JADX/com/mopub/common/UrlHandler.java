package com.mopub.common;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.util.EnumSet;
import java.util.Iterator;

public class UrlHandler {
    private static final ResultActions EMPTY_CLICK_LISTENER;
    private static final MoPubSchemeListener EMPTY_MOPUB_SCHEME_LISTENER;
    private boolean mAlreadySucceeded;
    @Nullable
    private String mDspCreativeId;
    @NonNull
    private MoPubSchemeListener mMoPubSchemeListener;
    @NonNull
    private ResultActions mResultActions;
    private boolean mSkipShowMoPubBrowser;
    @NonNull
    private EnumSet<UrlAction> mSupportedUrlActions;
    private boolean mTaskPending;

    public interface ResultActions {
        void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction);

        void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction);
    }

    static class 1 implements ResultActions {
        1() {
        }

        public void urlHandlingSucceeded(@NonNull String url, @NonNull UrlAction urlAction) {
        }

        public void urlHandlingFailed(@NonNull String url, @NonNull UrlAction lastFailedUrlAction) {
        }
    }

    public interface MoPubSchemeListener {
        void onClose();

        void onFailLoad();

        void onFinishLoad();
    }

    static class 2 implements MoPubSchemeListener {
        2() {
        }

        public void onFinishLoad() {
        }

        public void onClose() {
        }

        public void onFailLoad() {
        }
    }

    class 3 implements UrlResolutionListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$destinationUrl;
        final /* synthetic */ boolean val$fromUserInteraction;
        final /* synthetic */ Iterable val$trackingUrls;

        3(Context context, boolean z, Iterable iterable, String str) {
            this.val$context = context;
            this.val$fromUserInteraction = z;
            this.val$trackingUrls = iterable;
            this.val$destinationUrl = str;
        }

        public void onSuccess(@NonNull String resolvedUrl) {
            UrlHandler.this.mTaskPending = false;
            UrlHandler.this.handleResolvedUrl(this.val$context, resolvedUrl, this.val$fromUserInteraction, this.val$trackingUrls);
        }

        public void onFailure(@NonNull String message, @Nullable Throwable throwable) {
            UrlHandler.this.mTaskPending = false;
            UrlHandler.this.failUrlHandling(this.val$destinationUrl, null, message, throwable);
        }
    }

    public static class Builder {
        @Nullable
        private String creativeId;
        @NonNull
        private MoPubSchemeListener moPubSchemeListener;
        @NonNull
        private ResultActions resultActions;
        private boolean skipShowMoPubBrowser;
        @NonNull
        private EnumSet<UrlAction> supportedUrlActions;

        public Builder() {
            this.supportedUrlActions = EnumSet.of(UrlAction.NOOP);
            this.resultActions = UrlHandler.EMPTY_CLICK_LISTENER;
            this.moPubSchemeListener = UrlHandler.EMPTY_MOPUB_SCHEME_LISTENER;
            this.skipShowMoPubBrowser = false;
        }

        public Builder withSupportedUrlActions(@NonNull UrlAction first, @Nullable UrlAction... others) {
            this.supportedUrlActions = EnumSet.of(first, others);
            return this;
        }

        public Builder withSupportedUrlActions(@NonNull EnumSet<UrlAction> supportedUrlActions) {
            this.supportedUrlActions = EnumSet.copyOf(supportedUrlActions);
            return this;
        }

        public Builder withResultActions(@NonNull ResultActions resultActions) {
            this.resultActions = resultActions;
            return this;
        }

        public Builder withMoPubSchemeListener(@NonNull MoPubSchemeListener moPubSchemeListener) {
            this.moPubSchemeListener = moPubSchemeListener;
            return this;
        }

        public Builder withoutMoPubBrowser() {
            this.skipShowMoPubBrowser = true;
            return this;
        }

        public Builder withDspCreativeId(@Nullable String creativeId) {
            this.creativeId = creativeId;
            return this;
        }

        public UrlHandler build() {
            return new UrlHandler(this.resultActions, this.moPubSchemeListener, this.skipShowMoPubBrowser, this.creativeId, null);
        }
    }

    static {
        EMPTY_CLICK_LISTENER = new 1();
        EMPTY_MOPUB_SCHEME_LISTENER = new 2();
    }

    private UrlHandler(@NonNull EnumSet<UrlAction> supportedUrlActions, @NonNull ResultActions resultActions, @NonNull MoPubSchemeListener moPubSchemeListener, boolean skipShowMoPubBrowser, @Nullable String dspCreativeId) {
        this.mSupportedUrlActions = EnumSet.copyOf(supportedUrlActions);
        this.mResultActions = resultActions;
        this.mMoPubSchemeListener = moPubSchemeListener;
        this.mSkipShowMoPubBrowser = skipShowMoPubBrowser;
        this.mDspCreativeId = dspCreativeId;
        this.mAlreadySucceeded = false;
        this.mTaskPending = false;
    }

    @NonNull
    EnumSet<UrlAction> getSupportedUrlActions() {
        return EnumSet.copyOf(this.mSupportedUrlActions);
    }

    @NonNull
    ResultActions getResultActions() {
        return this.mResultActions;
    }

    @NonNull
    MoPubSchemeListener getMoPubSchemeListener() {
        return this.mMoPubSchemeListener;
    }

    boolean shouldSkipShowMoPubBrowser() {
        return this.mSkipShowMoPubBrowser;
    }

    public void handleUrl(@NonNull Context context, @NonNull String destinationUrl) {
        Preconditions.checkNotNull(context);
        handleUrl(context, destinationUrl, true);
    }

    public void handleUrl(@NonNull Context context, @NonNull String destinationUrl, boolean fromUserInteraction) {
        Preconditions.checkNotNull(context);
        handleUrl(context, destinationUrl, fromUserInteraction, null);
    }

    public void handleUrl(@NonNull Context context, @NonNull String destinationUrl, boolean fromUserInteraction, @Nullable Iterable<String> trackingUrls) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(destinationUrl)) {
            failUrlHandling(destinationUrl, null, "Attempted to handle empty url.", null);
            return;
        }
        UrlResolutionTask.getResolvedUrl(destinationUrl, new 3(context, fromUserInteraction, trackingUrls, destinationUrl));
        this.mTaskPending = true;
    }

    public boolean handleResolvedUrl(@NonNull Context context, @NonNull String url, boolean fromUserInteraction, @Nullable Iterable<String> trackingUrls) {
        if (TextUtils.isEmpty(url)) {
            failUrlHandling(url, null, "Attempted to handle empty url.", null);
            return false;
        }
        UrlAction lastFailedUrlAction = UrlAction.NOOP;
        Uri destinationUri = Uri.parse(url);
        Iterator it = this.mSupportedUrlActions.iterator();
        while (it.hasNext()) {
            UrlAction urlAction = (UrlAction) it.next();
            if (urlAction.shouldTryHandlingUrl(destinationUri)) {
                try {
                    urlAction.handleUrl(this, context, destinationUri, fromUserInteraction, this.mDspCreativeId);
                    if (!(this.mAlreadySucceeded || this.mTaskPending || UrlAction.IGNORE_ABOUT_SCHEME.equals(urlAction) || UrlAction.HANDLE_MOPUB_SCHEME.equals(urlAction))) {
                        TrackingRequest.makeTrackingHttpRequest((Iterable) trackingUrls, context, Name.CLICK_REQUEST);
                        this.mResultActions.urlHandlingSucceeded(destinationUri.toString(), urlAction);
                        this.mAlreadySucceeded = true;
                    }
                    return true;
                } catch (IntentNotResolvableException e) {
                    MoPubLog.d(e.getMessage(), e);
                    lastFailedUrlAction = urlAction;
                }
            }
        }
        failUrlHandling(url, lastFailedUrlAction, "Link ignored. Unable to handle url: " + url, null);
        return false;
    }

    private void failUrlHandling(@Nullable String url, @Nullable UrlAction urlAction, @NonNull String message, @Nullable Throwable throwable) {
        Preconditions.checkNotNull(message);
        if (urlAction == null) {
            urlAction = UrlAction.NOOP;
        }
        MoPubLog.d(message, throwable);
        this.mResultActions.urlHandlingFailed(url, urlAction);
    }
}
