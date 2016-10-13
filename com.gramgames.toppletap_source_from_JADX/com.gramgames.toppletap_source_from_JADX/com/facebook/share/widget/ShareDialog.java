package com.facebook.share.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.FacebookCallback;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.DialogFeature;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.DialogPresenter.ParameterProvider;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.internal.FragmentWrapper;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.OpenGraphActionDialogFeature;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.games.Games;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.Yytoken;

public final class ShareDialog extends FacebookDialogBase<ShareContent, Result> implements Sharer {
    private static final int DEFAULT_REQUEST_CODE;
    private static final String FEED_DIALOG = "feed";
    private static final String WEB_OG_SHARE_DIALOG = "share_open_graph";
    private static final String WEB_SHARE_DIALOG = "share";
    private boolean isAutomaticMode;
    private boolean shouldFailOnDataError;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$share$widget$ShareDialog$Mode;

        static {
            $SwitchMap$com$facebook$share$widget$ShareDialog$Mode = new int[Mode.values().length];
            try {
                $SwitchMap$com$facebook$share$widget$ShareDialog$Mode[Mode.AUTOMATIC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$share$widget$ShareDialog$Mode[Mode.WEB.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$share$widget$ShareDialog$Mode[Mode.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private class FeedHandler extends ModeHandler {
        private FeedHandler() {
            super();
        }

        public Object getMode() {
            return Mode.FEED;
        }

        public boolean canShow(ShareContent content, boolean isBestEffort) {
            return (content instanceof ShareLinkContent) || (content instanceof ShareFeedContent);
        }

        public AppCall createAppCall(ShareContent content) {
            Bundle params;
            ShareDialog.this.logDialogShare(ShareDialog.this.getActivityContext(), content, Mode.FEED);
            AppCall appCall = ShareDialog.this.createBaseAppCall();
            if (content instanceof ShareLinkContent) {
                ShareLinkContent linkContent = (ShareLinkContent) content;
                ShareContentValidation.validateForWebShare(linkContent);
                params = WebDialogParameters.createForFeed(linkContent);
            } else {
                params = WebDialogParameters.createForFeed((ShareFeedContent) content);
            }
            DialogPresenter.setupAppCallForWebDialog(appCall, ShareDialog.FEED_DIALOG, params);
            return appCall;
        }
    }

    public enum Mode {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    private class NativeHandler extends ModeHandler {

        class 1 implements ParameterProvider {
            final /* synthetic */ AppCall val$appCall;
            final /* synthetic */ ShareContent val$content;
            final /* synthetic */ boolean val$shouldFailOnDataError;

            1(AppCall appCall, ShareContent shareContent, boolean z) {
                this.val$appCall = appCall;
                this.val$content = shareContent;
                this.val$shouldFailOnDataError = z;
            }

            public Bundle getParameters() {
                return NativeDialogParameters.create(this.val$appCall.getCallId(), this.val$content, this.val$shouldFailOnDataError);
            }

            public Bundle getLegacyParameters() {
                return LegacyNativeDialogParameters.create(this.val$appCall.getCallId(), this.val$content, this.val$shouldFailOnDataError);
            }
        }

        private NativeHandler() {
            super();
        }

        public Object getMode() {
            return Mode.NATIVE;
        }

        public boolean canShow(ShareContent content, boolean isBestEffort) {
            if (content == null) {
                return false;
            }
            boolean canShowResult = true;
            if (!isBestEffort) {
                if (content.getShareHashtag() != null) {
                    canShowResult = DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.HASHTAG);
                }
                if ((content instanceof ShareLinkContent) && !Utility.isNullOrEmpty(((ShareLinkContent) content).getQuote())) {
                    canShowResult &= DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.LINK_SHARE_QUOTES);
                }
            }
            boolean z = canShowResult && ShareDialog.canShowNative(content.getClass());
            return z;
        }

        public AppCall createAppCall(ShareContent content) {
            ShareDialog.this.logDialogShare(ShareDialog.this.getActivityContext(), content, Mode.NATIVE);
            ShareContentValidation.validateForNativeShare(content);
            AppCall appCall = ShareDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(appCall, new 1(appCall, content, ShareDialog.this.getShouldFailOnDataError()), ShareDialog.getFeature(content.getClass()));
            return appCall;
        }
    }

    private class WebShareHandler extends ModeHandler {
        private WebShareHandler() {
            super();
        }

        public Object getMode() {
            return Mode.WEB;
        }

        public boolean canShow(ShareContent content, boolean isBestEffort) {
            return content != null && ShareDialog.canShowWebTypeCheck(content.getClass());
        }

        public AppCall createAppCall(ShareContent content) {
            Bundle params;
            ShareDialog.this.logDialogShare(ShareDialog.this.getActivityContext(), content, Mode.WEB);
            AppCall appCall = ShareDialog.this.createBaseAppCall();
            ShareContentValidation.validateForWebShare(content);
            if (content instanceof ShareLinkContent) {
                params = WebDialogParameters.create((ShareLinkContent) content);
            } else {
                params = WebDialogParameters.create((ShareOpenGraphContent) content);
            }
            DialogPresenter.setupAppCallForWebDialog(appCall, getActionName(content), params);
            return appCall;
        }

        private String getActionName(ShareContent shareContent) {
            if (shareContent instanceof ShareLinkContent) {
                return ShareDialog.WEB_SHARE_DIALOG;
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return ShareDialog.WEB_OG_SHARE_DIALOG;
            }
            return null;
        }
    }

    static {
        DEFAULT_REQUEST_CODE = RequestCodeOffset.Share.toRequestCode();
    }

    public static void show(Activity activity, ShareContent shareContent) {
        new ShareDialog(activity).show(shareContent);
    }

    public static void show(Fragment fragment, ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }

    public static void show(android.app.Fragment fragment, ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }

    private static void show(FragmentWrapper fragmentWrapper, ShareContent shareContent) {
        new ShareDialog(fragmentWrapper).show(shareContent);
    }

    public static boolean canShow(Class<? extends ShareContent> contentType) {
        return canShowWebTypeCheck(contentType) || canShowNative(contentType);
    }

    private static boolean canShowNative(Class<? extends ShareContent> contentType) {
        DialogFeature feature = getFeature(contentType);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }

    private static boolean canShowWebTypeCheck(Class<? extends ShareContent> contentType) {
        return ShareLinkContent.class.isAssignableFrom(contentType) || ShareOpenGraphContent.class.isAssignableFrom(contentType);
    }

    public ShareDialog(Activity activity) {
        super(activity, DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(DEFAULT_REQUEST_CODE);
    }

    public ShareDialog(Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    public ShareDialog(android.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    private ShareDialog(FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(DEFAULT_REQUEST_CODE);
    }

    ShareDialog(Activity activity, int requestCode) {
        super(activity, requestCode);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(requestCode);
    }

    ShareDialog(Fragment fragment, int requestCode) {
        this(new FragmentWrapper(fragment), requestCode);
    }

    ShareDialog(android.app.Fragment fragment, int requestCode) {
        this(new FragmentWrapper(fragment), requestCode);
    }

    private ShareDialog(FragmentWrapper fragmentWrapper, int requestCode) {
        super(fragmentWrapper, requestCode);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(requestCode);
    }

    protected void registerCallbackImpl(CallbackManagerImpl callbackManager, FacebookCallback<Result> callback) {
        ShareInternalUtility.registerSharerCallback(getRequestCode(), callbackManager, callback);
    }

    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }

    public void setShouldFailOnDataError(boolean shouldFailOnDataError) {
        this.shouldFailOnDataError = shouldFailOnDataError;
    }

    public boolean canShow(ShareContent content, Mode mode) {
        if (mode == Mode.AUTOMATIC) {
            mode = BASE_AUTOMATIC_MODE;
        }
        return canShowImpl(content, mode);
    }

    public void show(ShareContent content, Mode mode) {
        this.isAutomaticMode = mode == Mode.AUTOMATIC;
        if (this.isAutomaticMode) {
            mode = BASE_AUTOMATIC_MODE;
        }
        showImpl(content, mode);
    }

    protected AppCall createBaseAppCall() {
        return new AppCall(getRequestCode());
    }

    protected List<ModeHandler> getOrderedModeHandlers() {
        ArrayList<ModeHandler> handlers = new ArrayList();
        handlers.add(new NativeHandler());
        handlers.add(new FeedHandler());
        handlers.add(new WebShareHandler());
        return handlers;
    }

    private static DialogFeature getFeature(Class<? extends ShareContent> contentType) {
        if (ShareLinkContent.class.isAssignableFrom(contentType)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(contentType)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(contentType)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(contentType)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        if (ShareMediaContent.class.isAssignableFrom(contentType)) {
            return ShareDialogFeature.MULTIMEDIA;
        }
        return null;
    }

    private void logDialogShare(Context context, ShareContent content, Mode mode) {
        String displayType;
        String contentType;
        if (this.isAutomaticMode) {
            mode = Mode.AUTOMATIC;
        }
        switch (1.$SwitchMap$com$facebook$share$widget$ShareDialog$Mode[mode.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                displayType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC;
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                displayType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB;
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                displayType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                break;
            default:
                displayType = UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN;
                break;
        }
        DialogFeature dialogFeature = getFeature(content.getClass());
        if (dialogFeature == ShareDialogFeature.SHARE_DIALOG) {
            contentType = Games.EXTRA_STATUS;
        } else if (dialogFeature == ShareDialogFeature.PHOTOS) {
            contentType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO;
        } else if (dialogFeature == ShareDialogFeature.VIDEO) {
            contentType = MimeTypes.BASE_TYPE_VIDEO;
        } else if (dialogFeature == OpenGraphActionDialogFeature.OG_ACTION_DIALOG) {
            contentType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_OPENGRAPH;
        } else {
            contentType = UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN;
        }
        AppEventsLogger logger = AppEventsLogger.newLogger(context);
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW, displayType);
        parameters.putString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_TYPE, contentType);
        logger.logSdkEvent(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW, null, parameters);
    }
}
