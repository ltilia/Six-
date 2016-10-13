package com.vungle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.unity3d.player.UnityPlayer;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.VunglePub;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.inject.Injector;
import gs.gram.mopub.BuildConfig;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class VunglePlugin {
    protected static final String MANAGER_NAME = "VungleManager";
    protected static final String TAG = "Vungle";
    private static VunglePlugin _instance;
    public Activity _activity;

    class 1 implements Runnable {
        final /* synthetic */ Runnable val$r;

        1(Runnable runnable) {
            this.val$r = runnable;
        }

        public void run() {
            try {
                this.val$r.run();
            } catch (Exception e) {
                Log.e(VunglePlugin.TAG, "Exception running command on UI thread: " + e.getMessage());
            }
        }
    }

    class 2 implements EventListener {
        2() {
        }

        public void onAdStart() {
            VunglePlugin.this.UnitySendMessage("OnAdStart", BuildConfig.FLAVOR);
        }

        public void onVideoView(boolean z, int i, int i2) {
            VunglePlugin.this.UnitySendMessage("OnVideoView", String.format("%b-%d-%d", new Object[]{Boolean.valueOf(z), Integer.valueOf(i), Integer.valueOf(i2)}));
        }

        public void onAdUnavailable(String str) {
            Log.i(VunglePlugin.TAG, "onAdUnavailable: " + str);
        }

        public void onAdEnd(boolean z) {
            VunglePlugin.this.UnitySendMessage("OnAdEnd", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }

        public void onAdPlayableChanged(boolean z) {
            if (z) {
                VunglePlugin.this.UnitySendMessage("OnCachedAdAvailable", BuildConfig.FLAVOR);
            }
            VunglePlugin.this.UnitySendMessage("OnAdPlayable", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ boolean val$incentivized;
        final /* synthetic */ String val$user;

        3(boolean z, String str) {
            this.val$incentivized = z;
            this.val$user = str;
        }

        public void run() {
            AdConfig adConfig = new AdConfig();
            adConfig.setIncentivized(this.val$incentivized);
            if (this.val$incentivized && this.val$user != null && this.val$user.length() > 0) {
                adConfig.setIncentivizedUserId(this.val$user);
            }
            VunglePub.getInstance().playAd(adConfig);
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ String val$alerTitle;
        final /* synthetic */ String val$alertClose;
        final /* synthetic */ String val$alertContinue;
        final /* synthetic */ String val$alertText;
        final /* synthetic */ boolean val$incentivized;
        final /* synthetic */ String val$user;

        4(boolean z, String str, String str2, String str3, String str4, String str5) {
            this.val$incentivized = z;
            this.val$user = str;
            this.val$alerTitle = str2;
            this.val$alertText = str3;
            this.val$alertClose = str4;
            this.val$alertContinue = str5;
        }

        public void run() {
            AdConfig adConfig = new AdConfig();
            adConfig.setIncentivized(this.val$incentivized);
            if (this.val$incentivized && this.val$user != null && this.val$user.length() > 0) {
                adConfig.setIncentivizedUserId(this.val$user);
            }
            if (this.val$alerTitle != null && this.val$alerTitle.length() > 0) {
                adConfig.setIncentivizedCancelDialogTitle(this.val$alerTitle);
            }
            if (this.val$alertText != null && this.val$alertText.length() > 0) {
                adConfig.setIncentivizedCancelDialogBodyText(this.val$alertText);
            }
            if (this.val$alertClose != null && this.val$alertClose.length() > 0) {
                adConfig.setIncentivizedCancelDialogCloseButtonText(this.val$alertClose);
            }
            if (this.val$alertContinue != null && this.val$alertContinue.length() > 0) {
                adConfig.setIncentivizedCancelDialogKeepWatchingButtonText(this.val$alertContinue);
            }
            VunglePub.getInstance().playAd(adConfig);
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ String val$opt;

        5(String str) {
            this.val$opt = str;
        }

        public void run() {
            boolean z = false;
            AdConfig adConfig = new AdConfig();
            JSONObject jSONObject = (JSONObject) JSONValue.parse(this.val$opt);
            Object obj = jSONObject.get("incentivized");
            boolean booleanValue = (obj == null || !(obj instanceof Boolean)) ? false : ((Boolean) obj).booleanValue();
            adConfig.setIncentivized(booleanValue);
            obj = jSONObject.get("orientation");
            Orientation orientation = (obj == null || !(obj instanceof Boolean)) ? Orientation.autoRotate : ((Boolean) obj).booleanValue() ? Orientation.matchVideo : Orientation.autoRotate;
            adConfig.setOrientation(orientation);
            String str = (String) jSONObject.get("userTag");
            if (str != null && str.length() > 0) {
                adConfig.setIncentivizedUserId(str);
            }
            str = (String) jSONObject.get("alertTitle");
            if (str != null && str.length() > 0) {
                adConfig.setIncentivizedCancelDialogTitle(str);
            }
            str = (String) jSONObject.get("alertText");
            if (str != null && str.length() > 0) {
                adConfig.setIncentivizedCancelDialogBodyText(str);
            }
            str = (String) jSONObject.get("closeText");
            if (str != null && str.length() > 0) {
                adConfig.setIncentivizedCancelDialogCloseButtonText(str);
            }
            str = (String) jSONObject.get("continueText");
            if (str != null && str.length() > 0) {
                adConfig.setIncentivizedCancelDialogKeepWatchingButtonText(str);
            }
            str = (String) jSONObject.get("placement");
            if (str != null && str.length() > 0) {
                adConfig.setPlacement(str);
            }
            obj = jSONObject.get("immersive");
            if (obj != null && (obj instanceof Boolean)) {
                z = ((Boolean) obj).booleanValue();
            }
            adConfig.setImmersiveMode(z);
            str = (String) jSONObject.get("key1");
            if (str != null && str.length() > 0) {
                adConfig.setExtra1(str);
            }
            str = (String) jSONObject.get("key2");
            if (str != null && str.length() > 0) {
                adConfig.setExtra2(str);
            }
            str = (String) jSONObject.get("key3");
            if (str != null && str.length() > 0) {
                adConfig.setExtra3(str);
            }
            str = (String) jSONObject.get("key4");
            if (str != null && str.length() > 0) {
                adConfig.setExtra4(str);
            }
            str = (String) jSONObject.get("key5");
            if (str != null && str.length() > 0) {
                adConfig.setExtra5(str);
            }
            str = (String) jSONObject.get("key6");
            if (str != null && str.length() > 0) {
                adConfig.setExtra6(str);
            }
            str = (String) jSONObject.get("key7");
            if (str != null && str.length() > 0) {
                adConfig.setExtra7(str);
            }
            String str2 = (String) jSONObject.get("key8");
            if (str2 != null && str2.length() > 0) {
                adConfig.setExtra8(str2);
            }
            VunglePub.getInstance().playAd(adConfig);
        }
    }

    public static VunglePlugin instance() {
        if (_instance == null) {
            _instance = new VunglePlugin();
        }
        return _instance;
    }

    private Activity getActivity() {
        try {
            return UnityPlayer.currentActivity;
        } catch (Exception e) {
            Log.i(TAG, "error getting currentActivity: " + e.getMessage());
            return this._activity;
        }
    }

    private void UnitySendMessage(String str, String str2) {
        if (str2 == null) {
            str2 = BuildConfig.FLAVOR;
        }
        try {
            UnityPlayer.UnitySendMessage(MANAGER_NAME, str, str2);
        } catch (Exception e) {
            Log.i(TAG, "UnitySendMessage: VungleManager, " + str + ", " + str2);
        }
    }

    private void runSafelyOnUiThread(Runnable runnable) {
        getActivity().runOnUiThread(new 1(runnable));
    }

    @SuppressLint({"DefaultLocale"})
    public void init(String str, String str2) {
        Injector instance = Injector.getInstance();
        instance.setWrapperFramework(WrapperFramework.unity);
        instance.setWrapperFrameworkVersion(str2);
        VunglePub.getInstance().init(getActivity(), str);
        VunglePub.getInstance().setEventListeners(new 2());
    }

    public void setAdOrientation(int i) {
        AdConfig globalAdConfig = VunglePub.getInstance().getGlobalAdConfig();
        if (i == 0) {
            globalAdConfig.setOrientation(Orientation.autoRotate);
        } else {
            globalAdConfig.setOrientation(Orientation.matchVideo);
        }
    }

    public void onPause() {
        VunglePub.getInstance().onPause();
    }

    public void onResume() {
        VunglePub.getInstance().onResume();
    }

    public boolean isVideoAvailable() {
        return VunglePub.getInstance().isAdPlayable();
    }

    public void setSoundEnabled(boolean z) {
        VunglePub.getInstance().getGlobalAdConfig().setSoundEnabled(z);
    }

    public boolean isSoundEnabled() {
        return VunglePub.getInstance().getGlobalAdConfig().isSoundEnabled();
    }

    public void playAd(boolean z, String str) {
        runSafelyOnUiThread(new 3(z, str));
    }

    public void playAdEx(boolean z, int i, boolean z2, String str, String str2, String str3, String str4, String str5) {
        runSafelyOnUiThread(new 4(z, str, str2, str3, str4, str5));
    }

    public void playAdEx(String str) {
        runSafelyOnUiThread(new 5(str));
    }
}
