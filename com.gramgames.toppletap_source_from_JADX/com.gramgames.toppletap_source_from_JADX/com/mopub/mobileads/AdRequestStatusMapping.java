package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import java.util.Map;
import java.util.TreeMap;

public class AdRequestStatusMapping {
    @NonNull
    private final Map<String, AdRequestStatus> mAdUnitToAdRequestStatus;

    private static class AdRequestStatus {
        @Nullable
        private String mClickUrl;
        @Nullable
        private String mFailUrl;
        @Nullable
        private String mImpressionUrl;
        @NonNull
        private LoadingStatus mLoadingStatus;

        public AdRequestStatus(@NonNull LoadingStatus loadingStatus) {
            this(loadingStatus, null, null, null);
        }

        public AdRequestStatus(@NonNull LoadingStatus loadingStatus, @Nullable String failUrl, @Nullable String impressionUrl, @Nullable String clickUrl) {
            Preconditions.checkNotNull(loadingStatus);
            this.mLoadingStatus = loadingStatus;
            this.mFailUrl = failUrl;
            this.mImpressionUrl = impressionUrl;
            this.mClickUrl = clickUrl;
        }

        @NonNull
        private LoadingStatus getStatus() {
            return this.mLoadingStatus;
        }

        private void setStatus(@NonNull LoadingStatus loadingStatus) {
            this.mLoadingStatus = loadingStatus;
        }

        @Nullable
        private String getFailurl() {
            return this.mFailUrl;
        }

        @Nullable
        private String getImpressionUrl() {
            return this.mImpressionUrl;
        }

        private void setImpressionUrl(@Nullable String impressionUrl) {
            this.mImpressionUrl = impressionUrl;
        }

        @Nullable
        private String getClickUrl() {
            return this.mClickUrl;
        }

        private void setClickUrl(@Nullable String clickUrl) {
            this.mClickUrl = clickUrl;
        }

        public boolean equals(Object o) {
            boolean z = true;
            if (o == null) {
                return false;
            }
            if (this == o) {
                return true;
            }
            if (!(o instanceof AdRequestStatus)) {
                return false;
            }
            AdRequestStatus that = (AdRequestStatus) o;
            if (!(this.mLoadingStatus.equals(that.mLoadingStatus) && TextUtils.equals(this.mFailUrl, that.mFailUrl) && TextUtils.equals(this.mImpressionUrl, that.mImpressionUrl) && TextUtils.equals(this.mClickUrl, that.mClickUrl))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            int hashCode;
            int i = 0;
            int ordinal = (((this.mLoadingStatus.ordinal() + 899) * 31) + (this.mFailUrl != null ? this.mFailUrl.hashCode() : 0)) * 31;
            if (this.mImpressionUrl != null) {
                hashCode = this.mImpressionUrl.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode = (ordinal + hashCode) * 31;
            if (this.mClickUrl != null) {
                i = this.mClickUrl.hashCode();
            }
            return hashCode + i;
        }
    }

    private enum LoadingStatus {
        LOADING,
        LOADED,
        PLAYED
    }

    public AdRequestStatusMapping() {
        this.mAdUnitToAdRequestStatus = new TreeMap();
    }

    void markFail(@NonNull String adUnitId) {
        this.mAdUnitToAdRequestStatus.remove(adUnitId);
    }

    void markLoading(@NonNull String adUnitId) {
        this.mAdUnitToAdRequestStatus.put(adUnitId, new AdRequestStatus(LoadingStatus.LOADING));
    }

    void markLoaded(@NonNull String adUnitId, @Nullable String failUrlString, @Nullable String impressionTrackerUrlString, @Nullable String clickTrackerUrlString) {
        this.mAdUnitToAdRequestStatus.put(adUnitId, new AdRequestStatus(LoadingStatus.LOADED, failUrlString, impressionTrackerUrlString, clickTrackerUrlString));
    }

    void markPlayed(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)).setStatus(LoadingStatus.PLAYED);
        } else {
            this.mAdUnitToAdRequestStatus.put(adUnitId, new AdRequestStatus(LoadingStatus.PLAYED));
        }
    }

    boolean canPlay(@NonNull String adUnitId) {
        AdRequestStatus adRequestStatus = (AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId);
        return adRequestStatus != null && LoadingStatus.LOADED.equals(adRequestStatus.getStatus());
    }

    boolean isLoading(@NonNull String adUnitId) {
        if (!this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return false;
        }
        return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)).getStatus() == LoadingStatus.LOADING;
    }

    @Nullable
    String getFailoverUrl(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)).getFailurl();
        }
        return null;
    }

    @Nullable
    String getImpressionTrackerUrlString(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)).getImpressionUrl();
        }
        return null;
    }

    @Nullable
    String getClickTrackerUrlString(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)).getClickUrl();
        }
        return null;
    }

    void clearImpressionUrl(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)).setImpressionUrl(null);
        }
    }

    void clearClickUrl(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)).setClickUrl(null);
        }
    }
}
