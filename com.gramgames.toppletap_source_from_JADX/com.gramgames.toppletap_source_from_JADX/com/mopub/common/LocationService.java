package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.GooglePlayServicesInterstitial;
import java.math.BigDecimal;
import org.json.simple.parser.Yytoken;

public class LocationService {

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$common$LocationService$ValidLocationProvider;

        static {
            $SwitchMap$com$mopub$common$LocationService$ValidLocationProvider = new int[ValidLocationProvider.values().length];
            try {
                $SwitchMap$com$mopub$common$LocationService$ValidLocationProvider[ValidLocationProvider.NETWORK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$common$LocationService$ValidLocationProvider[ValidLocationProvider.GPS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum LocationAwareness {
        NORMAL,
        TRUNCATED,
        DISABLED;

        @Deprecated
        public com.mopub.common.MoPub.LocationAwareness getNewLocationAwareness() {
            if (this == TRUNCATED) {
                return com.mopub.common.MoPub.LocationAwareness.TRUNCATED;
            }
            if (this == DISABLED) {
                return com.mopub.common.MoPub.LocationAwareness.DISABLED;
            }
            return com.mopub.common.MoPub.LocationAwareness.NORMAL;
        }

        @Deprecated
        public static LocationAwareness fromMoPubLocationAwareness(com.mopub.common.MoPub.LocationAwareness awareness) {
            if (awareness == com.mopub.common.MoPub.LocationAwareness.DISABLED) {
                return DISABLED;
            }
            if (awareness == com.mopub.common.MoPub.LocationAwareness.TRUNCATED) {
                return TRUNCATED;
            }
            return NORMAL;
        }
    }

    public enum ValidLocationProvider {
        NETWORK("network"),
        GPS("gps");
        
        @NonNull
        final String name;

        private ValidLocationProvider(@NonNull String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        private boolean hasRequiredPermissions(@NonNull Context context) {
            switch (1.$SwitchMap$com$mopub$common$LocationService$ValidLocationProvider[ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    if (DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                        return true;
                    }
                    return false;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    return DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION");
                default:
                    return false;
            }
        }
    }

    @Nullable
    public static Location getLastKnownLocation(@NonNull Context context, int locationPrecision, @NonNull com.mopub.common.MoPub.LocationAwareness locationAwareness) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(locationAwareness);
        if (locationAwareness == com.mopub.common.MoPub.LocationAwareness.DISABLED) {
            return null;
        }
        Location result = getMostRecentValidLocation(getLocationFromProvider(context, ValidLocationProvider.GPS), getLocationFromProvider(context, ValidLocationProvider.NETWORK));
        if (locationAwareness != com.mopub.common.MoPub.LocationAwareness.TRUNCATED) {
            return result;
        }
        truncateLocationLatLon(result, locationPrecision);
        return result;
    }

    @Nullable
    @VisibleForTesting
    static Location getLocationFromProvider(@NonNull Context context, @NonNull ValidLocationProvider provider) {
        Location location = null;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(provider);
        if (provider.hasRequiredPermissions(context)) {
            try {
                location = ((LocationManager) context.getSystemService(GooglePlayServicesInterstitial.LOCATION_KEY)).getLastKnownLocation(provider.toString());
            } catch (SecurityException e) {
                MoPubLog.d("Failed to retrieve location from " + provider.toString() + " provider: access appears to be disabled.");
            } catch (IllegalArgumentException e2) {
                MoPubLog.d("Failed to retrieve location: device has no " + provider.toString() + " location provider.");
            } catch (NullPointerException e3) {
                MoPubLog.d("Failed to retrieve location: device has no " + provider.toString() + " location provider.");
            }
        }
        return location;
    }

    @Nullable
    @VisibleForTesting
    static Location getMostRecentValidLocation(@Nullable Location a, @Nullable Location b) {
        if (a == null) {
            return b;
        }
        return (b == null || a.getTime() > b.getTime()) ? a : b;
    }

    @Nullable
    @VisibleForTesting
    static void truncateLocationLatLon(@Nullable Location location, int precision) {
        if (location != null && precision >= 0) {
            location.setLatitude(BigDecimal.valueOf(location.getLatitude()).setScale(precision, 5).doubleValue());
            location.setLongitude(BigDecimal.valueOf(location.getLongitude()).setScale(precision, 5).doubleValue());
        }
    }
}
