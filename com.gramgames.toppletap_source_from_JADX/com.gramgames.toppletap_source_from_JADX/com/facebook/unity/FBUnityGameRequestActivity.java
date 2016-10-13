package com.facebook.unity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.model.GameRequestContent.ActionType;
import com.facebook.share.model.GameRequestContent.Builder;
import com.facebook.share.model.GameRequestContent.Filters;
import com.facebook.share.widget.GameRequestDialog;
import com.facebook.share.widget.GameRequestDialog.Result;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Arrays;
import java.util.Locale;

public class FBUnityGameRequestActivity extends BaseActivity {
    public static final String GAME_REQUEST_PARAMS = "game_request_params";

    class 1 implements FacebookCallback<Result> {
        final /* synthetic */ UnityMessage val$response;

        1(UnityMessage unityMessage) {
            this.val$response = unityMessage;
        }

        public void onSuccess(Result result) {
            this.val$response.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, result.getRequestId());
            this.val$response.put(ShareConstants.WEB_DIALOG_PARAM_TO, TextUtils.join(",", result.getRequestRecipients()));
            this.val$response.send();
        }

        public void onCancel() {
            this.val$response.putCancelled();
            this.val$response.send();
        }

        public void onError(FacebookException e) {
            this.val$response.sendError(e.getMessage());
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle params = getIntent().getBundleExtra(GAME_REQUEST_PARAMS);
        UnityMessage response = new UnityMessage("OnAppRequestsComplete");
        if (params.containsKey(Constants.CALLBACK_ID_KEY)) {
            response.put(Constants.CALLBACK_ID_KEY, params.getString(Constants.CALLBACK_ID_KEY));
        }
        Builder contentBuilder = new Builder();
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_MESSAGE)) {
            contentBuilder.setMessage(params.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE));
        }
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE)) {
            String actionTypeStr = params.getString(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE);
            try {
                contentBuilder.setActionType(ActionType.valueOf(actionTypeStr));
            } catch (IllegalArgumentException e) {
                response.sendError("Unknown action type: " + actionTypeStr);
                finish();
                return;
            }
        }
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_OBJECT_ID)) {
            contentBuilder.setObjectId(params.getString(ShareConstants.WEB_DIALOG_PARAM_OBJECT_ID));
        }
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_TO)) {
            contentBuilder.setRecipients(Arrays.asList(params.getString(ShareConstants.WEB_DIALOG_PARAM_TO).split(",")));
        }
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_FILTERS)) {
            String filtersStr = params.getString(ShareConstants.WEB_DIALOG_PARAM_FILTERS).toUpperCase(Locale.ROOT);
            try {
                contentBuilder.setFilters(Filters.valueOf(filtersStr));
            } catch (IllegalArgumentException e2) {
                response.sendError("Unsupported filter type: " + filtersStr);
                finish();
                return;
            }
        }
        if (params.containsKey(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY)) {
            contentBuilder.setData(params.getString(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY));
        }
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_TITLE)) {
            contentBuilder.setTitle(params.getString(ShareConstants.WEB_DIALOG_PARAM_TITLE));
        }
        GameRequestContent content = contentBuilder.build();
        GameRequestDialog requestDialog = new GameRequestDialog((Activity) this);
        requestDialog.registerCallback(this.mCallbackManager, new 1(response));
        try {
            requestDialog.show(content);
        } catch (IllegalArgumentException exception) {
            response.sendError("Unexpected exception encountered: " + exception.toString());
            finish();
        }
    }
}
