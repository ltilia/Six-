package com.facebook.unity;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FBLogin {

    static class 1 implements FacebookCallback<LoginResult> {
        final /* synthetic */ String val$callbackID;
        final /* synthetic */ UnityMessage val$unityMessage;

        1(String str, UnityMessage unityMessage) {
            this.val$callbackID = str;
            this.val$unityMessage = unityMessage;
        }

        public void onSuccess(LoginResult loginResult) {
            FBLogin.sendLoginSuccessMessage(loginResult.getAccessToken(), this.val$callbackID);
        }

        public void onCancel() {
            this.val$unityMessage.putCancelled();
            this.val$unityMessage.send();
        }

        public void onError(FacebookException e) {
            this.val$unityMessage.sendError(e.getMessage());
        }
    }

    public static void loginWithReadPermissions(String params, FBUnityLoginActivity activity) {
        login(params, activity, false);
    }

    public static void loginWithPublishPermissions(String params, FBUnityLoginActivity activity) {
        login(params, activity, true);
    }

    public static void sendLoginSuccessMessage(AccessToken token, String callbackID) {
        UnityMessage unityMessage = new UnityMessage("OnLoginComplete");
        addLoginParametersToMessage(unityMessage, token, callbackID);
        unityMessage.send();
    }

    public static void addLoginParametersToMessage(UnityMessage unityMessage, AccessToken token, String callbackID) {
        unityMessage.put("key_hash", FB.getKeyHash());
        unityMessage.put("opened", Boolean.valueOf(true));
        unityMessage.put(ServerProtocol.DIALOG_PARAM_ACCESS_TOKEN, token.getToken());
        unityMessage.put("expiration_timestamp", Long.valueOf(token.getExpires().getTime() / 1000).toString());
        unityMessage.put(AccessToken.USER_ID_KEY, token.getUserId());
        unityMessage.put(NativeProtocol.RESULT_ARGS_PERMISSIONS, TextUtils.join(",", token.getPermissions()));
        unityMessage.put("declined_permissions", TextUtils.join(",", token.getDeclinedPermissions()));
        if (token.getLastRefresh() != null) {
            unityMessage.put("last_refresh", Long.valueOf(token.getLastRefresh().getTime() / 1000).toString());
        }
        if (callbackID != null && !callbackID.isEmpty()) {
            unityMessage.put(Constants.CALLBACK_ID_KEY, callbackID);
        }
    }

    private static void login(String params, FBUnityLoginActivity activity, boolean isPublishPermLogin) {
        if (FacebookSdk.isInitialized()) {
            UnityMessage unityMessage = new UnityMessage("OnLoginComplete");
            unityMessage.put("key_hash", FB.getKeyHash());
            UnityParams unity_params = UnityParams.parse(params, "couldn't parse login params: " + params);
            Collection permissions = null;
            if (unity_params.hasString(ServerProtocol.DIALOG_PARAM_SCOPE).booleanValue()) {
                permissions = new ArrayList(Arrays.asList(unity_params.getString(ServerProtocol.DIALOG_PARAM_SCOPE).split(",")));
            }
            String callbackIDString = null;
            if (unity_params.has(Constants.CALLBACK_ID_KEY)) {
                callbackIDString = unity_params.getString(Constants.CALLBACK_ID_KEY);
                unityMessage.put(Constants.CALLBACK_ID_KEY, callbackIDString);
            }
            LoginManager.getInstance().registerCallback(activity.getCallbackManager(), new 1(callbackIDString, unityMessage));
            if (isPublishPermLogin) {
                LoginManager.getInstance().logInWithPublishPermissions((Activity) activity, permissions);
                return;
            } else {
                LoginManager.getInstance().logInWithReadPermissions((Activity) activity, permissions);
                return;
            }
        }
        Log.w(FB.TAG, "Facebook SDK not initialized. Call init() before calling login()");
    }
}
