package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.ImpressionListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAd.Image;
import com.facebook.ads.NativeAd.Rating;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.NativeImageHelper.ImageListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacebookNative extends CustomEventNative {
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private static boolean VIDEO_ENABLED = false;
    private static final String VIDEO_ENABLED_KEY = "video_enabled";
    private static Boolean sIsVideoRendererAvailable;

    static class FacebookStaticNativeAd extends StaticNativeAd implements AdListener, ImpressionListener {
        private static final String SOCIAL_CONTEXT_FOR_AD = "socialContextForAd";
        private final Context mContext;
        private final CustomEventNativeListener mCustomEventNativeListener;
        private final NativeAd mNativeAd;

        class 1 implements ImageListener {
            1() {
            }

            public void onImagesCached() {
                FacebookStaticNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(FacebookStaticNativeAd.this);
            }

            public void onImagesFailedToCache(NativeErrorCode errorCode) {
                FacebookStaticNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(errorCode);
            }
        }

        FacebookStaticNativeAd(Context context, NativeAd nativeAd, CustomEventNativeListener customEventNativeListener) {
            this.mContext = context.getApplicationContext();
            this.mNativeAd = nativeAd;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        void loadAd() {
            this.mNativeAd.setAdListener(this);
            this.mNativeAd.setImpressionListener(this);
            this.mNativeAd.loadAd();
        }

        public void onAdLoaded(Ad ad) {
            String str = null;
            if (this.mNativeAd.equals(ad) && this.mNativeAd.isAdLoaded()) {
                setTitle(this.mNativeAd.getAdTitle());
                setText(this.mNativeAd.getAdBody());
                Image coverImage = this.mNativeAd.getAdCoverImage();
                setMainImageUrl(coverImage == null ? null : coverImage.getUrl());
                Image icon = this.mNativeAd.getAdIcon();
                setIconImageUrl(icon == null ? null : icon.getUrl());
                setCallToAction(this.mNativeAd.getAdCallToAction());
                setStarRating(getDoubleRating(this.mNativeAd.getAdStarRating()));
                addExtra(SOCIAL_CONTEXT_FOR_AD, this.mNativeAd.getAdSocialContext());
                Image adChoicesIconImage = this.mNativeAd.getAdChoicesIcon();
                if (adChoicesIconImage != null) {
                    str = adChoicesIconImage.getUrl();
                }
                setPrivacyInformationIconImageUrl(str);
                setPrivacyInformationIconClickThroughUrl(this.mNativeAd.getAdChoicesLinkUrl());
                List<String> imageUrls = new ArrayList();
                if (getMainImageUrl() != null) {
                    imageUrls.add(getMainImageUrl());
                }
                if (getIconImageUrl() != null) {
                    imageUrls.add(getIconImageUrl());
                }
                String privacyInformationIconImageUrl = getPrivacyInformationIconImageUrl();
                if (privacyInformationIconImageUrl != null) {
                    imageUrls.add(privacyInformationIconImageUrl);
                }
                NativeImageHelper.preCacheImages(this.mContext, imageUrls, new 1());
                return;
            }
            this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
        }

        public void onError(Ad ad, AdError adError) {
            if (adError == null) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            } else if (adError.getErrorCode() == AdError.NO_FILL.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
            } else if (adError.getErrorCode() == AdError.INTERNAL_ERROR.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
            } else {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            }
        }

        public void onAdClicked(Ad ad) {
            notifyAdClicked();
        }

        public void onLoggingImpression(Ad ad) {
            notifyAdImpressed();
        }

        public void prepare(View view) {
            this.mNativeAd.registerViewForInteraction(view);
        }

        public void clear(View view) {
            this.mNativeAd.unregisterView();
        }

        public void destroy() {
            this.mNativeAd.destroy();
        }

        private Double getDoubleRating(Rating rating) {
            if (rating == null) {
                return null;
            }
            return Double.valueOf((5.0d * rating.getValue()) / rating.getScale());
        }
    }

    static class FacebookVideoEnabledNativeAd extends BaseNativeAd implements AdListener, ImpressionListener {
        static final double MAX_STAR_RATING = 5.0d;
        static final double MIN_STAR_RATING = 0.0d;
        private static final String SOCIAL_CONTEXT_FOR_AD = "socialContextForAd";
        private final Context mContext;
        private final CustomEventNativeListener mCustomEventNativeListener;
        private final Map<String, Object> mExtras;
        private final NativeAd mNativeAd;
        private Double mStarRating;

        class 1 implements ImageListener {
            1() {
            }

            public void onImagesCached() {
                FacebookVideoEnabledNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(FacebookVideoEnabledNativeAd.this);
            }

            public void onImagesFailedToCache(NativeErrorCode errorCode) {
                FacebookVideoEnabledNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(errorCode);
            }
        }

        FacebookVideoEnabledNativeAd(Context context, NativeAd nativeAd, CustomEventNativeListener customEventNativeListener) {
            this.mContext = context.getApplicationContext();
            this.mNativeAd = nativeAd;
            this.mCustomEventNativeListener = customEventNativeListener;
            this.mExtras = new HashMap();
        }

        void loadAd() {
            this.mNativeAd.setAdListener(this);
            this.mNativeAd.setImpressionListener(this);
            this.mNativeAd.loadAd();
        }

        public final String getTitle() {
            return this.mNativeAd.getAdTitle();
        }

        public final String getText() {
            return this.mNativeAd.getAdBody();
        }

        public final String getMainImageUrl() {
            Image coverImage = this.mNativeAd.getAdCoverImage();
            return coverImage == null ? null : coverImage.getUrl();
        }

        public final String getIconImageUrl() {
            Image icon = this.mNativeAd.getAdIcon();
            return icon == null ? null : icon.getUrl();
        }

        public final String getCallToAction() {
            return this.mNativeAd.getAdCallToAction();
        }

        public final Double getStarRating() {
            return this.mStarRating;
        }

        public final String getPrivacyInformationIconClickThroughUrl() {
            return this.mNativeAd.getAdChoicesLinkUrl();
        }

        public final String getPrivacyInformationIconImageUrl() {
            return this.mNativeAd.getAdChoicesIcon() == null ? null : this.mNativeAd.getAdChoicesIcon().getUrl();
        }

        public void onAdLoaded(Ad ad) {
            if (this.mNativeAd.equals(ad) && this.mNativeAd.isAdLoaded()) {
                setStarRating(getDoubleRating(this.mNativeAd.getAdStarRating()));
                addExtra(SOCIAL_CONTEXT_FOR_AD, this.mNativeAd.getAdSocialContext());
                List<String> imageUrls = new ArrayList();
                String mainImageUrl = getMainImageUrl();
                if (mainImageUrl != null) {
                    imageUrls.add(mainImageUrl);
                }
                String iconImageUrl = getIconImageUrl();
                if (iconImageUrl != null) {
                    imageUrls.add(iconImageUrl);
                }
                String privacyInformationIconImageUrl = getPrivacyInformationIconImageUrl();
                if (privacyInformationIconImageUrl != null) {
                    imageUrls.add(privacyInformationIconImageUrl);
                }
                NativeImageHelper.preCacheImages(this.mContext, imageUrls, new 1());
                return;
            }
            this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
        }

        public void onError(Ad ad, AdError adError) {
            if (adError == null) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            } else if (adError.getErrorCode() == AdError.NO_FILL.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
            } else if (adError.getErrorCode() == AdError.INTERNAL_ERROR.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
            } else {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            }
        }

        public void onAdClicked(Ad ad) {
            notifyAdClicked();
        }

        public void onLoggingImpression(Ad ad) {
            notifyAdImpressed();
        }

        public void prepare(View view) {
            this.mNativeAd.registerViewForInteraction(view);
        }

        public void clear(View view) {
            this.mNativeAd.unregisterView();
        }

        public void destroy() {
            this.mNativeAd.destroy();
        }

        public final Object getExtra(String key) {
            if (NoThrow.checkNotNull(key, "getExtra key is not allowed to be null")) {
                return this.mExtras.get(key);
            }
            return null;
        }

        public final Map<String, Object> getExtras() {
            return new HashMap(this.mExtras);
        }

        public final void addExtra(String key, Object value) {
            if (NoThrow.checkNotNull(key, "addExtra key is not allowed to be null")) {
                this.mExtras.put(key, value);
            }
        }

        public void updateMediaView(MediaView mediaView) {
            if (mediaView != null) {
                mediaView.setNativeAd(this.mNativeAd);
            }
        }

        private void setStarRating(Double starRating) {
            if (starRating == null) {
                this.mStarRating = null;
            } else if (starRating.doubleValue() < MIN_STAR_RATING || starRating.doubleValue() > MAX_STAR_RATING) {
                MoPubLog.d("Ignoring attempt to set invalid star rating (" + starRating + "). Must be " + "between " + MIN_STAR_RATING + " and " + MAX_STAR_RATING + ".");
            } else {
                this.mStarRating = starRating;
            }
        }

        private Double getDoubleRating(Rating rating) {
            if (rating == null) {
                return null;
            }
            return Double.valueOf((MAX_STAR_RATING * rating.getValue()) / rating.getScale());
        }
    }

    static {
        VIDEO_ENABLED = false;
        sIsVideoRendererAvailable = null;
    }

    protected void loadNativeAd(Activity activity, CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> serverExtras) {
        if (extrasAreValid(serverExtras)) {
            String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
            String videoEnabledString = (String) serverExtras.get(VIDEO_ENABLED_KEY);
            boolean videoEnabledFromServer = Boolean.parseBoolean(videoEnabledString);
            if (sIsVideoRendererAvailable == null) {
                try {
                    Class.forName("com.mopub.nativeads.FacebookAdRenderer");
                    sIsVideoRendererAvailable = Boolean.valueOf(true);
                } catch (ClassNotFoundException e) {
                    sIsVideoRendererAvailable = Boolean.valueOf(false);
                }
            }
            if (shouldUseVideoEnabledNativeAd(sIsVideoRendererAvailable.booleanValue(), videoEnabledString, videoEnabledFromServer)) {
                new FacebookVideoEnabledNativeAd(activity, new NativeAd(activity, placementId), customEventNativeListener).loadAd();
                return;
            } else {
                new FacebookStaticNativeAd(activity, new NativeAd(activity, placementId), customEventNativeListener).loadAd();
                return;
            }
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_CONFIGURATION_ERROR);
    }

    public static void setVideoEnabled(boolean videoEnabled) {
        VIDEO_ENABLED = videoEnabled;
    }

    public static void setVideoRendererAvailable(boolean videoRendererAvailable) {
        sIsVideoRendererAvailable = Boolean.valueOf(videoRendererAvailable);
    }

    static boolean shouldUseVideoEnabledNativeAd(boolean isVideoRendererAvailable, String videoEnabledString, boolean videoEnabledFromServer) {
        if (!isVideoRendererAvailable) {
            return false;
        }
        if ((videoEnabledString == null || !videoEnabledFromServer) && (videoEnabledString != null || !VIDEO_ENABLED)) {
            return false;
        }
        return true;
    }

    static Boolean isVideoRendererAvailable() {
        return sIsVideoRendererAvailable;
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
        return placementId != null && placementId.length() > 0;
    }
}
