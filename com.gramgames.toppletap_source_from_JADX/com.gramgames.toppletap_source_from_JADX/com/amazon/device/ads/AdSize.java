package com.amazon.device.ads;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.nearby.messages.Strategy;
import com.mopub.common.AdType;
import org.json.simple.parser.Yytoken;

public class AdSize {
    private static final String LOGTAG;
    public static final AdSize SIZE_1024x50;
    public static final AdSize SIZE_300x250;
    public static final AdSize SIZE_320x50;
    public static final AdSize SIZE_600x90;
    public static final AdSize SIZE_728x90;
    public static final AdSize SIZE_AUTO;
    public static final AdSize SIZE_AUTO_NO_SCALE;
    static final AdSize SIZE_INTERSTITIAL;
    static final AdSize SIZE_MODELESS_INTERSTITIAL;
    private int gravity;
    private int height;
    private final MobileAdsLogger logger;
    private int maxWidth;
    private Modality modality;
    private Scaling scaling;
    private SizeType type;
    private int width;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$AdSize$SizeType;

        static {
            $SwitchMap$com$amazon$device$ads$AdSize$SizeType = new int[SizeType.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$AdSize$SizeType[SizeType.EXPLICIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdSize$SizeType[SizeType.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdSize$SizeType[SizeType.INTERSTITIAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private enum Modality {
        MODAL,
        MODELESS
    }

    private enum Scaling {
        CAN_UPSCALE,
        NO_UPSCALE
    }

    enum SizeType {
        EXPLICIT,
        AUTO,
        INTERSTITIAL
    }

    static {
        LOGTAG = AdSize.class.getSimpleName();
        SIZE_320x50 = new AdSize(320, 50);
        SIZE_300x250 = new AdSize((int) Strategy.TTL_SECONDS_DEFAULT, 250);
        SIZE_600x90 = new AdSize(600, 90);
        SIZE_728x90 = new AdSize(728, 90);
        SIZE_1024x50 = new AdSize((int) AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, 50);
        SIZE_AUTO = new AdSize(SizeType.AUTO);
        SIZE_AUTO_NO_SCALE = new AdSize(SizeType.AUTO, Scaling.NO_UPSCALE);
        SIZE_INTERSTITIAL = new AdSize(SizeType.INTERSTITIAL, Modality.MODAL);
        SIZE_MODELESS_INTERSTITIAL = new AdSize(SizeType.INTERSTITIAL);
    }

    public AdSize(int width, int height) {
        this.gravity = 17;
        this.type = SizeType.EXPLICIT;
        this.modality = Modality.MODELESS;
        this.scaling = Scaling.CAN_UPSCALE;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        initialize(width, height);
    }

    AdSize(String width, String height) {
        this.gravity = 17;
        this.type = SizeType.EXPLICIT;
        this.modality = Modality.MODELESS;
        this.scaling = Scaling.CAN_UPSCALE;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        initialize(NumberUtils.parseInt(width, 0), NumberUtils.parseInt(height, 0));
    }

    AdSize(SizeType type) {
        this.gravity = 17;
        this.type = SizeType.EXPLICIT;
        this.modality = Modality.MODELESS;
        this.scaling = Scaling.CAN_UPSCALE;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.type = type;
    }

    AdSize(SizeType type, Modality modality) {
        this(type);
        this.modality = modality;
    }

    AdSize(SizeType type, Scaling scaling) {
        this(type);
        this.scaling = scaling;
    }

    private AdSize deepClone() {
        AdSize adSize = new AdSize(this.type);
        adSize.width = this.width;
        adSize.height = this.height;
        adSize.gravity = this.gravity;
        adSize.modality = this.modality;
        adSize.scaling = this.scaling;
        adSize.maxWidth = this.maxWidth;
        return adSize;
    }

    private void initialize(int width, int height) {
        if (width <= 0 || height <= 0) {
            String msg = "The width and height must be positive integers.";
            this.logger.e(msg);
            throw new IllegalArgumentException(msg);
        }
        this.width = width;
        this.height = height;
        this.type = SizeType.EXPLICIT;
    }

    public AdSize newGravity(int gravity) {
        AdSize adSize = deepClone();
        adSize.gravity = gravity;
        return adSize;
    }

    public int getGravity() {
        return this.gravity;
    }

    public String toString() {
        switch (1.$SwitchMap$com$amazon$device$ads$AdSize$SizeType[this.type.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return getAsSizeString(this.width, this.height);
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "auto";
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return AdType.INTERSTITIAL;
            default:
                return null;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize other = (AdSize) obj;
        if (!this.type.equals(other.type)) {
            return false;
        }
        if ((!this.type.equals(SizeType.EXPLICIT) || (this.width == other.width && this.height == other.height)) && this.gravity == other.gravity && this.maxWidth == other.maxWidth && this.scaling == other.scaling && this.modality == other.modality) {
            return true;
        }
        return false;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isAuto() {
        return this.type == SizeType.AUTO;
    }

    boolean isModal() {
        return Modality.MODAL.equals(this.modality);
    }

    SizeType getSizeType() {
        return this.type;
    }

    public boolean canUpscale() {
        return Scaling.CAN_UPSCALE.equals(this.scaling);
    }

    public AdSize disableScaling() {
        AdSize adSize = deepClone();
        adSize.scaling = Scaling.NO_UPSCALE;
        return adSize;
    }

    AdSize newMaxWidth(int maxWidth) {
        AdSize adSize = deepClone();
        adSize.maxWidth = maxWidth;
        return adSize;
    }

    int getMaxWidth() {
        return this.maxWidth;
    }

    static String getAsSizeString(int w, int h) {
        return Integer.toString(w) + "x" + Integer.toString(h);
    }
}
