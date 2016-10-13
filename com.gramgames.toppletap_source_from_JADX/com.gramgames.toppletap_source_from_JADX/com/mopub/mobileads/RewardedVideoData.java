package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class RewardedVideoData {
    @NonNull
    private final Set<CustomEventRewardedVideoListener> mAdNetworkListeners;
    @NonNull
    private final Map<String, CustomEventRewardedVideo> mAdUnitToCustomEventMap;
    @NonNull
    private final Map<String, MoPubReward> mAdUnitToRewardMap;
    @NonNull
    private final Map<String, String> mAdUnitToServerCompletionUrlMap;
    @Nullable
    private String mCurrentAdUnitId;
    @NonNull
    private final Map<TwoPartKey, Set<String>> mCustomEventToMoPubIdMap;
    @NonNull
    private final Map<Class<? extends CustomEventRewardedVideo>, MoPubReward> mCustomEventToRewardMap;
    @Nullable
    private String mCustomerId;

    private static class TwoPartKey extends Pair<Class<? extends CustomEventRewardedVideo>, String> {
        @NonNull
        final String adNetworkId;
        @NonNull
        final Class<? extends CustomEventRewardedVideo> customEventClass;

        public TwoPartKey(@NonNull Class<? extends CustomEventRewardedVideo> customEventClass, @NonNull String adNetworkId) {
            super(customEventClass, adNetworkId);
            this.customEventClass = customEventClass;
            this.adNetworkId = adNetworkId;
        }
    }

    RewardedVideoData() {
        this.mAdUnitToCustomEventMap = new TreeMap();
        this.mAdUnitToRewardMap = new TreeMap();
        this.mAdUnitToServerCompletionUrlMap = new TreeMap();
        this.mCustomEventToRewardMap = new HashMap();
        this.mCustomEventToMoPubIdMap = new HashMap();
        this.mAdNetworkListeners = new HashSet();
    }

    @Nullable
    CustomEventRewardedVideo getCustomEvent(@NonNull String moPubId) {
        return (CustomEventRewardedVideo) this.mAdUnitToCustomEventMap.get(moPubId);
    }

    @Nullable
    MoPubReward getMoPubReward(@Nullable String moPubId) {
        return (MoPubReward) this.mAdUnitToRewardMap.get(moPubId);
    }

    @Nullable
    String getServerCompletionUrl(@Nullable String moPubId) {
        if (TextUtils.isEmpty(moPubId)) {
            return null;
        }
        return (String) this.mAdUnitToServerCompletionUrlMap.get(moPubId);
    }

    @Nullable
    MoPubReward getLastShownMoPubReward(@NonNull Class<? extends CustomEventRewardedVideo> customEventClass) {
        return (MoPubReward) this.mCustomEventToRewardMap.get(customEventClass);
    }

    @NonNull
    Set<String> getMoPubIdsForAdNetwork(@NonNull Class<? extends CustomEventRewardedVideo> customEventClass, @Nullable String adNetworkId) {
        if (adNetworkId == null) {
            Set<String> hashSet = new HashSet();
            for (Entry<TwoPartKey, Set<String>> entry : this.mCustomEventToMoPubIdMap.entrySet()) {
                if (customEventClass == ((TwoPartKey) entry.getKey()).customEventClass) {
                    hashSet.addAll((Collection) entry.getValue());
                }
            }
            return hashSet;
        }
        Set<String> set;
        TwoPartKey key = new TwoPartKey(customEventClass, adNetworkId);
        if (this.mCustomEventToMoPubIdMap.containsKey(key)) {
            set = (Set) this.mCustomEventToMoPubIdMap.get(key);
        } else {
            set = Collections.emptySet();
        }
        return set;
    }

    void updateAdUnitCustomEventMapping(@NonNull String moPubId, @NonNull CustomEventRewardedVideo customEvent, @Nullable CustomEventRewardedVideoListener listener, @NonNull String adNetworkId) {
        this.mAdUnitToCustomEventMap.put(moPubId, customEvent);
        this.mAdNetworkListeners.add(listener);
        associateCustomEventWithMoPubId(customEvent.getClass(), adNetworkId, moPubId);
    }

    void updateAdUnitRewardMapping(@NonNull String moPubId, @Nullable String currencyName, @Nullable String currencyAmount) {
        Preconditions.checkNotNull(moPubId);
        if (currencyName == null || currencyAmount == null) {
            this.mAdUnitToRewardMap.remove(moPubId);
            return;
        }
        try {
            int intCurrencyAmount = Integer.parseInt(currencyAmount);
            if (intCurrencyAmount >= 0) {
                this.mAdUnitToRewardMap.put(moPubId, MoPubReward.success(currencyName, intCurrencyAmount));
            }
        } catch (NumberFormatException e) {
        }
    }

    void updateAdUnitToServerCompletionUrlMapping(@NonNull String moPubId, @Nullable String serverCompletionUrl) {
        Preconditions.checkNotNull(moPubId);
        this.mAdUnitToServerCompletionUrlMap.put(moPubId, serverCompletionUrl);
    }

    void updateCustomEventLastShownRewardMapping(@NonNull Class<? extends CustomEventRewardedVideo> customEventClass, @Nullable MoPubReward moPubReward) {
        Preconditions.checkNotNull(customEventClass);
        this.mCustomEventToRewardMap.put(customEventClass, moPubReward);
    }

    void associateCustomEventWithMoPubId(@NonNull Class<? extends CustomEventRewardedVideo> customEventClass, @NonNull String adNetworkId, @NonNull String moPubId) {
        Set<String> moPubIds;
        TwoPartKey newCustomEventMapping = new TwoPartKey(customEventClass, adNetworkId);
        Iterator<Entry<TwoPartKey, Set<String>>> entryIterator = this.mCustomEventToMoPubIdMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Entry<TwoPartKey, Set<String>> entry = (Entry) entryIterator.next();
            if (!((TwoPartKey) entry.getKey()).equals(newCustomEventMapping) && ((Set) entry.getValue()).contains(moPubId)) {
                ((Set) entry.getValue()).remove(moPubId);
                if (((Set) entry.getValue()).isEmpty()) {
                    entryIterator.remove();
                }
                moPubIds = (Set) this.mCustomEventToMoPubIdMap.get(newCustomEventMapping);
                if (moPubIds == null) {
                    moPubIds = new HashSet();
                    this.mCustomEventToMoPubIdMap.put(newCustomEventMapping, moPubIds);
                }
                moPubIds.add(moPubId);
            }
        }
        moPubIds = (Set) this.mCustomEventToMoPubIdMap.get(newCustomEventMapping);
        if (moPubIds == null) {
            moPubIds = new HashSet();
            this.mCustomEventToMoPubIdMap.put(newCustomEventMapping, moPubIds);
        }
        moPubIds.add(moPubId);
    }

    void setCurrentAdUnitId(@Nullable String currentAdUnitId) {
        this.mCurrentAdUnitId = currentAdUnitId;
    }

    @Nullable
    String getCurrentAdUnitId() {
        return this.mCurrentAdUnitId;
    }

    void setCustomerId(@Nullable String customerId) {
        this.mCustomerId = customerId;
    }

    @Nullable
    String getCustomerId() {
        return this.mCustomerId;
    }
}
