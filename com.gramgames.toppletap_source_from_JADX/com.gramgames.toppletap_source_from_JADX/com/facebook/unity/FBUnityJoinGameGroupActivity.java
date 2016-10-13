package com.facebook.unity;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.widget.JoinAppGroupDialog;
import com.facebook.share.widget.JoinAppGroupDialog.Result;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;

public class FBUnityJoinGameGroupActivity extends BaseActivity {
    public static String JOIN_GAME_GROUP_PARAMS;

    class 1 implements FacebookCallback<Result> {
        final /* synthetic */ UnityMessage val$response;

        1(UnityMessage unityMessage) {
            this.val$response = unityMessage;
        }

        public void onSuccess(Result result) {
            this.val$response.put(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETE_KEY, Boolean.valueOf(true));
            this.val$response.send();
        }

        public void onCancel() {
            this.val$response.putCancelled();
            this.val$response.send();
        }

        public void onError(FacebookException e) {
            this.val$response.sendError(e.getLocalizedMessage());
        }
    }

    static {
        JOIN_GAME_GROUP_PARAMS = "join_game_group_params";
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle params = getIntent().getBundleExtra(JOIN_GAME_GROUP_PARAMS);
        UnityMessage response = new UnityMessage("OnJoinGroupComplete");
        if (params.containsKey(Constants.CALLBACK_ID_KEY)) {
            response.put(Constants.CALLBACK_ID_KEY, params.getString(Constants.CALLBACK_ID_KEY));
        }
        String groupId = BuildConfig.FLAVOR;
        if (params.containsKey(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY)) {
            groupId = params.getString(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
        }
        JoinAppGroupDialog dialog = new JoinAppGroupDialog((Activity) this);
        dialog.registerCallback(this.mCallbackManager, new 1(response));
        dialog.show(groupId);
    }
}
