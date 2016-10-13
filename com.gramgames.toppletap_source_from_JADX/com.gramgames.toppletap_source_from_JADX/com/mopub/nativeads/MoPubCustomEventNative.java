package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Numbers;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.NativeImageHelper.ImageListener;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public class MoPubCustomEventNative extends CustomEventNative {

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter;

        static {
            $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter = new int[Parameter.values().length];
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.MAIN_IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.ICON_IMAGE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.IMPRESSION_TRACKER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.CLICK_DESTINATION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.CLICK_TRACKER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.CALL_TO_ACTION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.TITLE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.TEXT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[Parameter.STAR_RATING.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    static class MoPubStaticNativeAd extends StaticNativeAd {
        @VisibleForTesting
        static final String PRIVACY_INFORMATION_CLICKTHROUGH_URL = "https://www.mopub.com/optout";
        @NonNull
        private final Context mContext;
        @NonNull
        private final CustomEventNativeListener mCustomEventNativeListener;
        @NonNull
        private final ImpressionTracker mImpressionTracker;
        @NonNull
        private final JSONObject mJsonObject;
        @NonNull
        private final NativeClickHandler mNativeClickHandler;

        class 1 implements ImageListener {
            1() {
            }

            public void onImagesCached() {
                MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(MoPubStaticNativeAd.this);
            }

            public void onImagesFailedToCache(NativeErrorCode errorCode) {
                MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(errorCode);
            }
        }

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE(ShareConstants.WEB_DIALOG_PARAM_TITLE, false),
            TEXT(MimeTypes.BASE_TYPE_TEXT, false),
            MAIN_IMAGE("mainimage", false),
            ICON_IMAGE("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            STAR_RATING("starrating", false);
            
            @NonNull
            @VisibleForTesting
            static final Set<String> requiredKeys;
            @NonNull
            final String name;
            final boolean required;

            static {
                requiredKeys = new HashSet();
                Parameter[] values = values();
                int length = values.length;
                int i;
                while (i < length) {
                    Parameter parameter = values[i];
                    if (parameter.required) {
                        requiredKeys.add(parameter.name);
                    }
                    i++;
                }
            }

            private Parameter(@NonNull String name, boolean required) {
                this.name = name;
                this.required = required;
            }

            @Nullable
            static Parameter from(@NonNull String name) {
                for (Parameter parameter : values()) {
                    if (parameter.name.equals(name)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        MoPubStaticNativeAd(@NonNull Context context, @NonNull JSONObject jsonBody, @NonNull ImpressionTracker impressionTracker, @NonNull NativeClickHandler nativeClickHandler, @NonNull CustomEventNativeListener customEventNativeListener) {
            this.mJsonObject = jsonBody;
            this.mContext = context.getApplicationContext();
            this.mImpressionTracker = impressionTracker;
            this.mNativeClickHandler = nativeClickHandler;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        void loadAd() throws IllegalArgumentException {
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator<String> keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Parameter parameter = Parameter.from(key);
                    if (parameter != null) {
                        try {
                            addInstanceVariable(parameter, this.mJsonObject.opt(key));
                        } catch (ClassCastException e) {
                            throw new IllegalArgumentException("JSONObject key (" + key + ") contained unexpected value.");
                        }
                    }
                    addExtra(key, this.mJsonObject.opt(key));
                }
                setPrivacyInformationIconClickThroughUrl(PRIVACY_INFORMATION_CLICKTHROUGH_URL);
                NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new 1());
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        private boolean containsRequiredKeys(@NonNull JSONObject jsonObject) {
            Set<String> keys = new HashSet();
            Iterator<String> jsonKeys = jsonObject.keys();
            while (jsonKeys.hasNext()) {
                keys.add(jsonKeys.next());
            }
            return keys.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(@NonNull Parameter key, @Nullable Object value) throws ClassCastException {
            try {
                switch (1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[key.ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        setMainImageUrl((String) value);
                        return;
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                        setIconImageUrl((String) value);
                        return;
                    case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                        addImpressionTrackers(value);
                        return;
                    case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        setClickDestinationUrl((String) value);
                        return;
                    case Yytoken.TYPE_COMMA /*5*/:
                        parseClickTrackers(value);
                        return;
                    case Yytoken.TYPE_COLON /*6*/:
                        setCallToAction((String) value);
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        setTitle((String) value);
                        return;
                    case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                        setText((String) value);
                        return;
                    case R.styleable.Toolbar_popupTheme /*9*/:
                        setStarRating(Numbers.parseDouble(value));
                        return;
                    default:
                        MoPubLog.d("Unable to add JSON key to internal mapping: " + key.name);
                        return;
                }
            } catch (ClassCastException e) {
                if (key.required) {
                    throw e;
                }
                MoPubLog.d("Ignoring class cast exception for optional key: " + key.name);
                return;
            }
            if (key.required) {
                MoPubLog.d("Ignoring class cast exception for optional key: " + key.name);
                return;
            }
            throw e;
        }

        private void parseClickTrackers(@NonNull Object clickTrackers) {
            if (clickTrackers instanceof JSONArray) {
                addClickTrackers(clickTrackers);
            } else {
                addClickTracker((String) clickTrackers);
            }
        }

        private boolean isImageKey(@Nullable String name) {
            return name != null && name.toLowerCase(Locale.US).endsWith("image");
        }

        @NonNull
        List<String> getExtrasImageUrls() {
            List<String> extrasBitmapUrls = new ArrayList(getExtras().size());
            for (Entry<String, Object> entry : getExtras().entrySet()) {
                if (isImageKey((String) entry.getKey()) && (entry.getValue() instanceof String)) {
                    extrasBitmapUrls.add((String) entry.getValue());
                }
            }
            return extrasBitmapUrls;
        }

        @NonNull
        List<String> getAllImageUrls() {
            List<String> imageUrls = new ArrayList();
            if (getMainImageUrl() != null) {
                imageUrls.add(getMainImageUrl());
            }
            if (getIconImageUrl() != null) {
                imageUrls.add(getIconImageUrl());
            }
            imageUrls.addAll(getExtrasImageUrls());
            return imageUrls;
        }

        public void prepare(@NonNull View view) {
            this.mImpressionTracker.addView(view, this);
            this.mNativeClickHandler.setOnClickListener(view, (ClickInterface) this);
        }

        public void clear(@NonNull View view) {
            this.mImpressionTracker.removeView(view);
            this.mNativeClickHandler.clearOnClickListener(view);
        }

        public void destroy() {
            this.mImpressionTracker.destroy();
        }

        public void recordImpression(@NonNull View view) {
            notifyAdImpressed();
        }

        public void handleClick(@Nullable View view) {
            notifyAdClicked();
            this.mNativeClickHandler.openClickDestinationUrl(getClickDestinationUrl(), view);
        }
    }

    protected void loadNativeAd(@NonNull Activity activity, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> map) {
        Object json = localExtras.get(DataKeys.JSON_BODY_KEY);
        if (json instanceof JSONObject) {
            try {
                new MoPubStaticNativeAd(activity, (JSONObject) json, new ImpressionTracker(activity), new NativeClickHandler(activity), customEventNativeListener).loadAd();
                return;
            } catch (IllegalArgumentException e) {
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                return;
            }
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
    }
}
