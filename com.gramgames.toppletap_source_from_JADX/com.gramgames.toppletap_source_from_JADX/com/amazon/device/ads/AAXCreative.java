package com.amazon.device.ads;

import com.facebook.ads.AdError;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

enum AAXCreative {
    HTML(AdProperties.HTML),
    MRAID1(AdProperties.MRAID1),
    MRAID2(AdProperties.MRAID2),
    INTERSTITIAL(AdProperties.INTERSTITIAL),
    CAN_PLAY_AUDIO1(AdError.NO_FILL_ERROR_CODE),
    CAN_PLAY_AUDIO2(AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE),
    CAN_EXPAND1(AdProperties.CAN_EXPAND1),
    CAN_EXPAND2(AdProperties.CAN_EXPAND2),
    CAN_PLAY_VIDEO(AdProperties.CAN_PLAY_VIDEO),
    VIDEO_INTERSTITIAL(AdProperties.VIDEO_INTERSTITIAL),
    REQUIRES_TRANSPARENCY(AdProperties.REQUIRES_TRANSPARENCY);
    
    private static final HashSet<AAXCreative> primaryCreativeTypes;
    private final int id;

    static {
        primaryCreativeTypes = new HashSet();
        primaryCreativeTypes.add(HTML);
        primaryCreativeTypes.add(MRAID1);
        primaryCreativeTypes.add(MRAID2);
        primaryCreativeTypes.add(INTERSTITIAL);
        primaryCreativeTypes.add(VIDEO_INTERSTITIAL);
    }

    private AAXCreative(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static boolean containsPrimaryCreativeType(Set<AAXCreative> creativeTypes) {
        Iterator i$ = primaryCreativeTypes.iterator();
        while (i$.hasNext()) {
            if (creativeTypes.contains((AAXCreative) i$.next())) {
                return true;
            }
        }
        return false;
    }

    public static AAXCreative getCreativeType(int id) {
        switch (id) {
            case AdError.NO_FILL_ERROR_CODE /*1001*/:
                return CAN_PLAY_AUDIO1;
            case AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE /*1002*/:
                return CAN_PLAY_AUDIO2;
            case AdProperties.CAN_EXPAND1 /*1003*/:
                return CAN_EXPAND1;
            case AdProperties.CAN_EXPAND2 /*1004*/:
                return CAN_EXPAND2;
            case AdProperties.HTML /*1007*/:
                return HTML;
            case AdProperties.INTERSTITIAL /*1008*/:
                return INTERSTITIAL;
            case AdProperties.CAN_PLAY_VIDEO /*1014*/:
                return CAN_PLAY_VIDEO;
            case AdProperties.MRAID1 /*1016*/:
                return MRAID1;
            case AdProperties.MRAID2 /*1017*/:
                return MRAID2;
            case AdProperties.VIDEO_INTERSTITIAL /*1030*/:
                return VIDEO_INTERSTITIAL;
            case AdProperties.REQUIRES_TRANSPARENCY /*1031*/:
                return REQUIRES_TRANSPARENCY;
            default:
                return null;
        }
    }

    static AAXCreative getTopCreative(Set<AAXCreative> creatives) {
        if (creatives.contains(MRAID2)) {
            return MRAID2;
        }
        if (creatives.contains(MRAID1)) {
            return MRAID1;
        }
        if (creatives.contains(HTML)) {
            return HTML;
        }
        return null;
    }
}
