package com.facebook.unity;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.model.AppInviteContent.Builder;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.AppInviteDialog.Result;

public class AppInviteDialogActivity extends BaseActivity {
    public static final String DIALOG_PARAMS = "dialog_params";

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
            this.val$response.sendError(e.getMessage());
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UnityMessage response = new UnityMessage("OnAppInviteComplete");
        Bundle params = getIntent().getBundleExtra(DIALOG_PARAMS);
        Builder contentBuilder = new Builder();
        if (params.containsKey(Constants.CALLBACK_ID_KEY)) {
            response.put(Constants.CALLBACK_ID_KEY, params.getString(Constants.CALLBACK_ID_KEY));
        }
        if (params.containsKey("appLinkUrl")) {
            contentBuilder.setApplinkUrl(params.getString("appLinkUrl"));
        }
        if (params.containsKey("previewImageUrl")) {
            contentBuilder.setPreviewImageUrl(params.getString("previewImageUrl"));
        }
        AppInviteDialog dialog = new AppInviteDialog((Activity) this);
        dialog.registerCallback(this.mCallbackManager, new 1(response));
        dialog.show(contentBuilder.build());
    }
}
