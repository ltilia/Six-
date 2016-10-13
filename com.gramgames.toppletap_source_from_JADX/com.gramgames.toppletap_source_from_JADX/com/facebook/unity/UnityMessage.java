package com.facebook.unity;

import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UnityMessage {
    static final /* synthetic */ boolean $assertionsDisabled;
    private String methodName;
    private Map<String, Serializable> params;

    static {
        $assertionsDisabled = !UnityMessage.class.desiredAssertionStatus();
    }

    public UnityMessage(String methodName) {
        this.params = new HashMap();
        this.methodName = methodName;
    }

    public UnityMessage put(String name, Serializable value) {
        this.params.put(name, value);
        return this;
    }

    public UnityMessage putCancelled() {
        put(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, Boolean.valueOf(true));
        return this;
    }

    public UnityMessage putID(String id) {
        put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, id);
        return this;
    }

    public void sendError(String errorMsg) {
        put(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, errorMsg);
        send();
    }

    public void send() {
        if ($assertionsDisabled || this.methodName != null) {
            String message = new UnityParams(this.params).toString();
            Log.v(FB.TAG, "sending to Unity " + this.methodName + "(" + message + ")");
            try {
                UnityReflection.SendMessage("UnityFacebookSDKPlugin", this.methodName, message);
                return;
            } catch (UnsatisfiedLinkError e) {
                Log.v(FB.TAG, "message not send, Unity not initialized");
                return;
            }
        }
        throw new AssertionError("no method specified");
    }

    public static UnityMessage createWithCallbackFromParams(String methodName, UnityParams params) {
        UnityMessage unityMessage = new UnityMessage(methodName);
        if (params.hasString(Constants.CALLBACK_ID_KEY).booleanValue()) {
            unityMessage.put(Constants.CALLBACK_ID_KEY, params.getString(Constants.CALLBACK_ID_KEY));
        }
        return unityMessage;
    }
}
