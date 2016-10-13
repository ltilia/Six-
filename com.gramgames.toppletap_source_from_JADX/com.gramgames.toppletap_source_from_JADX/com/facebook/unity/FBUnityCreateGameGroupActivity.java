package com.facebook.unity;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.model.AppGroupCreationContent.AppGroupPrivacy;
import com.facebook.share.model.AppGroupCreationContent.Builder;
import com.facebook.share.widget.CreateAppGroupDialog;
import com.facebook.share.widget.CreateAppGroupDialog.Result;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Locale;

public class FBUnityCreateGameGroupActivity extends BaseActivity {
    public static String CREATE_GAME_GROUP_PARAMS;

    class 1 implements FacebookCallback<Result> {
        final /* synthetic */ UnityMessage val$response;

        1(UnityMessage unityMessage) {
            this.val$response = unityMessage;
        }

        public void onSuccess(Result result) {
            this.val$response.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, result.getId());
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
        CREATE_GAME_GROUP_PARAMS = "create_game_group_params";
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Builder contentBuilder = new Builder();
        Bundle params = getIntent().getBundleExtra(CREATE_GAME_GROUP_PARAMS);
        UnityMessage response = new UnityMessage("OnGroupCreateComplete");
        if (params.containsKey(Constants.CALLBACK_ID_KEY)) {
            response.put(Constants.CALLBACK_ID_KEY, params.getString(Constants.CALLBACK_ID_KEY));
        }
        if (params.containsKey(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY)) {
            contentBuilder.setName(params.getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY));
        }
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION)) {
            contentBuilder.setDescription(params.getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY));
        }
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_PRIVACY)) {
            String privacyStr = params.getString(ShareConstants.WEB_DIALOG_PARAM_PRIVACY);
            AppGroupPrivacy privacy = AppGroupPrivacy.Closed;
            if (privacyStr.equalsIgnoreCase("closed")) {
                privacy = AppGroupPrivacy.Closed;
            } else if (privacyStr.equalsIgnoreCase(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN)) {
                privacy = AppGroupPrivacy.Open;
            } else {
                response.sendError(String.format(Locale.ROOT, "Unknown privacy setting for group creation: %s", new Object[]{privacyStr}));
                finish();
            }
            contentBuilder.setAppGroupPrivacy(privacy);
        }
        CreateAppGroupDialog dialog = new CreateAppGroupDialog((Activity) this);
        dialog.registerCallback(this.mCallbackManager, new 1(response));
        dialog.show(contentBuilder.build());
    }
}
