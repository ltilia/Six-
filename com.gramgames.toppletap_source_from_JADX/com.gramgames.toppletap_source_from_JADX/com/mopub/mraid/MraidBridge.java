package com.mopub.mraid;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.C;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.AdReport;
import com.mopub.common.AdType;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.common.Constants;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BaseWebView;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.ViewGestureDetector;
import com.mopub.mobileads.ViewGestureDetector.UserClickListener;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.mobileads.resource.MraidJavascript;
import com.mopub.network.Networking;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public class MraidBridge {
    private final String FILTERED_JAVASCRIPT_SOURCE;
    private final AdReport mAdReport;
    private boolean mHasLoaded;
    private boolean mIsClicked;
    @Nullable
    private MraidBridgeListener mMraidBridgeListener;
    @NonNull
    private final MraidNativeCommandHandler mMraidNativeCommandHandler;
    @Nullable
    private MraidWebView mMraidWebView;
    private final WebViewClient mMraidWebViewClient;
    @NonNull
    private final PlacementType mPlacementType;

    class 1 extends WebChromeClient {
        1() {
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            if (MraidBridge.this.mMraidBridgeListener != null) {
                return MraidBridge.this.mMraidBridgeListener.onJsAlert(message, result);
            }
            return super.onJsAlert(view, url, message, result);
        }

        public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
            if (MraidBridge.this.mMraidBridgeListener != null) {
                return MraidBridge.this.mMraidBridgeListener.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }

        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }
    }

    class 2 implements UserClickListener {
        2() {
        }

        public void onUserClick() {
            MraidBridge.this.mIsClicked = true;
        }

        public void onResetUserClick() {
            MraidBridge.this.mIsClicked = false;
        }

        public boolean wasClicked() {
            return MraidBridge.this.mIsClicked;
        }
    }

    class 3 implements OnTouchListener {
        final /* synthetic */ ViewGestureDetector val$gestureDetector;

        3(ViewGestureDetector viewGestureDetector) {
            this.val$gestureDetector = viewGestureDetector;
        }

        public boolean onTouch(View v, MotionEvent event) {
            this.val$gestureDetector.sendTouchEvent(event);
            switch (event.getAction()) {
                case Yylex.YYINITIAL /*0*/:
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    if (!v.hasFocus()) {
                        v.requestFocus();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    class 4 implements OnVisibilityChangedListener {
        4() {
        }

        public void onVisibilityChanged(boolean isVisible) {
            if (MraidBridge.this.mMraidBridgeListener != null) {
                MraidBridge.this.mMraidBridgeListener.onVisibilityChanged(isVisible);
            }
        }
    }

    class 5 extends WebViewClient {
        5() {
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            MoPubLog.d("Error: " + description);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public boolean shouldOverrideUrlLoading(@NonNull WebView view, @NonNull String url) {
            return MraidBridge.this.handleShouldOverrideUrl(url);
        }

        public void onPageFinished(@NonNull WebView view, @NonNull String url) {
            MraidBridge.this.handlePageFinished();
        }
    }

    class 6 implements MraidCommandFailureListener {
        final /* synthetic */ MraidJavascriptCommand val$command;

        6(MraidJavascriptCommand mraidJavascriptCommand) {
            this.val$command = mraidJavascriptCommand;
        }

        public void onFailure(MraidCommandException exception) {
            MraidBridge.this.fireErrorEvent(this.val$command, exception.getMessage());
        }
    }

    static /* synthetic */ class 7 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mraid$MraidJavascriptCommand;

        static {
            $SwitchMap$com$mopub$mraid$MraidJavascriptCommand = new int[MraidJavascriptCommand.values().length];
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.RESIZE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.EXPAND.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.USE_CUSTOM_CLOSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.OPEN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.SET_ORIENTATION_PROPERTIES.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.PLAY_VIDEO.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.STORE_PICTURE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.CREATE_CALENDAR_EVENT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$mopub$mraid$MraidJavascriptCommand[MraidJavascriptCommand.UNSPECIFIED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public interface MraidBridgeListener {
        void onClose();

        boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage);

        void onExpand(URI uri, boolean z) throws MraidCommandException;

        boolean onJsAlert(@NonNull String str, @NonNull JsResult jsResult);

        void onOpen(URI uri);

        void onPageFailedToLoad();

        void onPageLoaded();

        void onPlayVideo(URI uri);

        void onResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) throws MraidCommandException;

        void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException;

        void onUseCustomClose(boolean z);

        void onVisibilityChanged(boolean z);
    }

    public static class MraidWebView extends BaseWebView {
        private boolean mIsVisible;
        @Nullable
        private OnVisibilityChangedListener mOnVisibilityChangedListener;

        public interface OnVisibilityChangedListener {
            void onVisibilityChanged(boolean z);
        }

        public MraidWebView(Context context) {
            super(context);
            this.mIsVisible = getVisibility() == 0;
        }

        void setVisibilityChangedListener(@Nullable OnVisibilityChangedListener listener) {
            this.mOnVisibilityChangedListener = listener;
        }

        protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
            super.onVisibilityChanged(changedView, visibility);
            boolean newIsVisible = visibility == 0;
            if (newIsVisible != this.mIsVisible) {
                this.mIsVisible = newIsVisible;
                if (this.mOnVisibilityChangedListener != null) {
                    this.mOnVisibilityChangedListener.onVisibilityChanged(this.mIsVisible);
                }
            }
        }

        public boolean isVisible() {
            return this.mIsVisible;
        }
    }

    MraidBridge(@Nullable AdReport adReport, @NonNull PlacementType placementType) {
        this(adReport, placementType, new MraidNativeCommandHandler());
    }

    @VisibleForTesting
    MraidBridge(@Nullable AdReport adReport, @NonNull PlacementType placementType, @NonNull MraidNativeCommandHandler mraidNativeCommandHandler) {
        this.FILTERED_JAVASCRIPT_SOURCE = MraidJavascript.JAVASCRIPT_SOURCE.replaceAll("(?m)^\\s+", BuildConfig.FLAVOR).replaceAll("(?m)^//.*(?=\\n)", BuildConfig.FLAVOR);
        this.mMraidWebViewClient = new 5();
        this.mAdReport = adReport;
        this.mPlacementType = placementType;
        this.mMraidNativeCommandHandler = mraidNativeCommandHandler;
    }

    void setMraidBridgeListener(@Nullable MraidBridgeListener listener) {
        this.mMraidBridgeListener = listener;
    }

    void attachView(@NonNull MraidWebView mraidWebView) {
        this.mMraidWebView = mraidWebView;
        this.mMraidWebView.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 17 && this.mPlacementType == PlacementType.INTERSTITIAL) {
            mraidWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.mMraidWebView.loadUrl("javascript:" + this.FILTERED_JAVASCRIPT_SOURCE);
        this.mMraidWebView.setScrollContainer(false);
        this.mMraidWebView.setVerticalScrollBarEnabled(false);
        this.mMraidWebView.setHorizontalScrollBarEnabled(false);
        this.mMraidWebView.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
        this.mMraidWebView.setWebViewClient(this.mMraidWebViewClient);
        this.mMraidWebView.setWebChromeClient(new 1());
        ViewGestureDetector gestureDetector = new ViewGestureDetector(this.mMraidWebView.getContext(), this.mMraidWebView, this.mAdReport);
        gestureDetector.setUserClickListener(new 2());
        this.mMraidWebView.setOnTouchListener(new 3(gestureDetector));
        this.mMraidWebView.setVisibilityChangedListener(new 4());
    }

    void detach() {
        this.mMraidWebView = null;
    }

    public void setContentHtml(@NonNull String htmlData) {
        if (this.mMraidWebView == null) {
            MoPubLog.d("MRAID bridge called setContentHtml before WebView was attached");
            return;
        }
        this.mHasLoaded = false;
        this.mMraidWebView.loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", htmlData, WebRequest.CONTENT_TYPE_HTML, C.UTF8_NAME, null);
    }

    public void setContentUrl(String url) {
        if (this.mMraidWebView == null) {
            MoPubLog.d("MRAID bridge called setContentHtml while WebView was not attached");
            return;
        }
        this.mHasLoaded = false;
        this.mMraidWebView.loadUrl(url);
    }

    void injectJavaScript(@NonNull String javascript) {
        if (this.mMraidWebView == null) {
            MoPubLog.d("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t" + javascript);
            return;
        }
        MoPubLog.v("Injecting Javascript into MRAID WebView:\n\t" + javascript);
        this.mMraidWebView.loadUrl("javascript:" + javascript);
    }

    private void fireErrorEvent(@NonNull MraidJavascriptCommand command, @NonNull String message) {
        injectJavaScript("window.mraidbridge.notifyErrorEvent(" + JSONObject.quote(command.toJavascriptString()) + ", " + JSONObject.quote(message) + ")");
    }

    private void fireNativeCommandCompleteEvent(@NonNull MraidJavascriptCommand command) {
        injectJavaScript("window.mraidbridge.nativeCallComplete(" + JSONObject.quote(command.toJavascriptString()) + ")");
    }

    @VisibleForTesting
    boolean handleShouldOverrideUrl(@NonNull String url) {
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if ("mopub".equals(scheme)) {
                if (!"failLoad".equals(host) || this.mPlacementType != PlacementType.INLINE || this.mMraidBridgeListener == null) {
                    return true;
                }
                this.mMraidBridgeListener.onPageFailedToLoad();
                return true;
            } else if (AdType.MRAID.equals(scheme)) {
                Map<String, String> params = new HashMap();
                for (NameValuePair pair : URLEncodedUtils.parse(uri, C.UTF8_NAME)) {
                    params.put(pair.getName(), pair.getValue());
                }
                MraidJavascriptCommand command = MraidJavascriptCommand.fromJavascriptString(host);
                try {
                    runCommand(command, params);
                } catch (MraidCommandException exception) {
                    fireErrorEvent(command, exception.getMessage());
                }
                fireNativeCommandCompleteEvent(command);
                return true;
            } else if (!this.mIsClicked) {
                return false;
            } else {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                intent.addFlags(DriveFile.MODE_READ_ONLY);
                try {
                    if (this.mMraidWebView == null) {
                        MoPubLog.d("WebView was detached. Unable to load a URL");
                        return true;
                    }
                    this.mMraidWebView.getContext().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    MoPubLog.d("No activity found to handle this URL " + url);
                    return false;
                }
            }
        } catch (URISyntaxException e2) {
            MoPubLog.w("Invalid MRAID URL: " + url);
            fireErrorEvent(MraidJavascriptCommand.UNSPECIFIED, "Mraid command sent an invalid URL");
            return true;
        }
    }

    @VisibleForTesting
    private void handlePageFinished() {
        if (!this.mHasLoaded) {
            this.mHasLoaded = true;
            if (this.mMraidBridgeListener != null) {
                this.mMraidBridgeListener.onPageLoaded();
            }
        }
    }

    @VisibleForTesting
    void runCommand(@NonNull MraidJavascriptCommand command, @NonNull Map<String, String> params) throws MraidCommandException {
        if (command.requiresClick(this.mPlacementType) && !this.mIsClicked) {
            throw new MraidCommandException("Cannot execute this command unless the user clicks");
        } else if (this.mMraidBridgeListener == null) {
            throw new MraidCommandException("Invalid state to execute this command");
        } else if (this.mMraidWebView == null) {
            throw new MraidCommandException("The current WebView is being destroyed");
        } else {
            switch (7.$SwitchMap$com$mopub$mraid$MraidJavascriptCommand[command.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    this.mMraidBridgeListener.onClose();
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    this.mMraidBridgeListener.onResize(checkRange(parseSize((String) params.get(VastIconXmlManager.WIDTH)), 0, 100000), checkRange(parseSize((String) params.get(VastIconXmlManager.HEIGHT)), 0, 100000), checkRange(parseSize((String) params.get("offsetX")), -100000, 100000), checkRange(parseSize((String) params.get("offsetY")), -100000, 100000), parseClosePosition((String) params.get("customClosePosition"), ClosePosition.TOP_RIGHT), parseBoolean((String) params.get("allowOffscreen"), true));
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    this.mMraidBridgeListener.onExpand(parseURI((String) params.get(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY), null), parseBoolean((String) params.get("shouldUseCustomClose"), false));
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    this.mMraidBridgeListener.onUseCustomClose(parseBoolean((String) params.get("shouldUseCustomClose"), false));
                case Yytoken.TYPE_COMMA /*5*/:
                    this.mMraidBridgeListener.onOpen(parseURI((String) params.get(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY)));
                case Yytoken.TYPE_COLON /*6*/:
                    this.mMraidBridgeListener.onSetOrientationProperties(parseBoolean((String) params.get("allowOrientationChange")), parseOrientation((String) params.get("forceOrientation")));
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    this.mMraidBridgeListener.onPlayVideo(parseURI((String) params.get(ShareConstants.MEDIA_URI)));
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    this.mMraidNativeCommandHandler.storePicture(this.mMraidWebView.getContext(), parseURI((String) params.get(ShareConstants.MEDIA_URI)).toString(), new 6(command));
                case R.styleable.Toolbar_popupTheme /*9*/:
                    this.mMraidNativeCommandHandler.createCalendarEvent(this.mMraidWebView.getContext(), params);
                case R.styleable.Toolbar_titleTextAppearance /*10*/:
                    throw new MraidCommandException("Unspecified MRAID Javascript command");
                default:
            }
        }
    }

    private ClosePosition parseClosePosition(@NonNull String text, @NonNull ClosePosition defaultValue) throws MraidCommandException {
        if (TextUtils.isEmpty(text)) {
            return defaultValue;
        }
        if (text.equals("top-left")) {
            return ClosePosition.TOP_LEFT;
        }
        if (text.equals("top-right")) {
            return ClosePosition.TOP_RIGHT;
        }
        if (text.equals(TtmlNode.CENTER)) {
            return ClosePosition.CENTER;
        }
        if (text.equals("bottom-left")) {
            return ClosePosition.BOTTOM_LEFT;
        }
        if (text.equals("bottom-right")) {
            return ClosePosition.BOTTOM_RIGHT;
        }
        if (text.equals("top-center")) {
            return ClosePosition.TOP_CENTER;
        }
        if (text.equals("bottom-center")) {
            return ClosePosition.BOTTOM_CENTER;
        }
        throw new MraidCommandException("Invalid close position: " + text);
    }

    private int parseSize(@NonNull String text) throws MraidCommandException {
        try {
            return Integer.parseInt(text, 10);
        } catch (NumberFormatException e) {
            throw new MraidCommandException("Invalid numeric parameter: " + text);
        }
    }

    private MraidOrientation parseOrientation(String text) throws MraidCommandException {
        if (DeviceInfo.ORIENTATION_PORTRAIT.equals(text)) {
            return MraidOrientation.PORTRAIT;
        }
        if (DeviceInfo.ORIENTATION_LANDSCAPE.equals(text)) {
            return MraidOrientation.LANDSCAPE;
        }
        if (UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_NONE.equals(text)) {
            return MraidOrientation.NONE;
        }
        throw new MraidCommandException("Invalid orientation: " + text);
    }

    private int checkRange(int value, int min, int max) throws MraidCommandException {
        if (value >= min && value <= max) {
            return value;
        }
        throw new MraidCommandException("Integer parameter out of range: " + value);
    }

    private boolean parseBoolean(@Nullable String text, boolean defaultValue) throws MraidCommandException {
        return text == null ? defaultValue : parseBoolean(text);
    }

    private boolean parseBoolean(String text) throws MraidCommandException {
        if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(text)) {
            return true;
        }
        if ("false".equals(text)) {
            return false;
        }
        throw new MraidCommandException("Invalid boolean parameter: " + text);
    }

    @NonNull
    private URI parseURI(@Nullable String encodedText, URI defaultValue) throws MraidCommandException {
        return encodedText == null ? defaultValue : parseURI(encodedText);
    }

    @NonNull
    private URI parseURI(@Nullable String encodedText) throws MraidCommandException {
        if (encodedText == null) {
            throw new MraidCommandException("Parameter cannot be null");
        }
        try {
            return new URI(encodedText);
        } catch (URISyntaxException e) {
            throw new MraidCommandException("Invalid URL parameter: " + encodedText);
        }
    }

    void notifyViewability(boolean isViewable) {
        injectJavaScript("mraidbridge.setIsViewable(" + isViewable + ")");
    }

    void notifyPlacementType(PlacementType placementType) {
        injectJavaScript("mraidbridge.setPlacementType(" + JSONObject.quote(placementType.toJavascriptString()) + ")");
    }

    void notifyViewState(ViewState state) {
        injectJavaScript("mraidbridge.setState(" + JSONObject.quote(state.toJavascriptString()) + ")");
    }

    void notifySupports(boolean sms, boolean telephone, boolean calendar, boolean storePicture, boolean inlineVideo) {
        injectJavaScript("mraidbridge.setSupports(" + sms + "," + telephone + "," + calendar + "," + storePicture + "," + inlineVideo + ")");
    }

    @NonNull
    private String stringifyRect(Rect rect) {
        return rect.left + "," + rect.top + "," + rect.width() + "," + rect.height();
    }

    @NonNull
    private String stringifySize(Rect rect) {
        return rect.width() + "," + rect.height();
    }

    public void notifyScreenMetrics(@NonNull MraidScreenMetrics screenMetrics) {
        injectJavaScript("mraidbridge.setScreenSize(" + stringifySize(screenMetrics.getScreenRectDips()) + ");mraidbridge.setMaxSize(" + stringifySize(screenMetrics.getRootViewRectDips()) + ");mraidbridge.setCurrentPosition(" + stringifyRect(screenMetrics.getCurrentAdRectDips()) + ");mraidbridge.setDefaultPosition(" + stringifyRect(screenMetrics.getDefaultAdRectDips()) + ")");
        injectJavaScript("mraidbridge.notifySizeChangeEvent(" + stringifySize(screenMetrics.getCurrentAdRect()) + ")");
    }

    void notifyReady() {
        injectJavaScript("mraidbridge.notifyReadyEvent();");
    }

    boolean isClicked() {
        return this.mIsClicked;
    }

    boolean isVisible() {
        return this.mMraidWebView != null && this.mMraidWebView.isVisible();
    }

    boolean isAttached() {
        return this.mMraidWebView != null;
    }

    boolean isLoaded() {
        return this.mHasLoaded;
    }

    @VisibleForTesting
    MraidWebView getMraidWebView() {
        return this.mMraidWebView;
    }

    @VisibleForTesting
    void setClicked(boolean clicked) {
        this.mIsClicked = clicked;
    }
}
