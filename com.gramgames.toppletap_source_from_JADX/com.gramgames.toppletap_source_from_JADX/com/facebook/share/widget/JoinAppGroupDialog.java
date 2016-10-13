package com.facebook.share.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.FacebookCallback;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.Callback;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.internal.FragmentWrapper;
import com.facebook.share.internal.ResultProcessor;
import com.facebook.share.internal.ShareInternalUtility;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.List;

public class JoinAppGroupDialog extends FacebookDialogBase<String, Result> {
    private static final int DEFAULT_REQUEST_CODE;
    private static final String JOIN_GAME_GROUP_DIALOG = "game_group_join";

    class 1 extends ResultProcessor {
        final /* synthetic */ FacebookCallback val$callback;

        1(FacebookCallback callback, FacebookCallback facebookCallback) {
            this.val$callback = facebookCallback;
            super(callback);
        }

        public void onSuccess(AppCall appCall, Bundle results) {
            this.val$callback.onSuccess(new Result(null));
        }
    }

    class 2 implements Callback {
        final /* synthetic */ ResultProcessor val$resultProcessor;

        2(ResultProcessor resultProcessor) {
            this.val$resultProcessor = resultProcessor;
        }

        public boolean onActivityResult(int resultCode, Intent data) {
            return ShareInternalUtility.handleActivityResult(JoinAppGroupDialog.this.getRequestCode(), resultCode, data, this.val$resultProcessor);
        }
    }

    public static final class Result {
        private final Bundle data;

        private Result(Bundle bundle) {
            this.data = bundle;
        }

        public Bundle getData() {
            return this.data;
        }
    }

    private class WebHandler extends ModeHandler {
        private WebHandler() {
            super();
        }

        public boolean canShow(String content, boolean isBestEffort) {
            return true;
        }

        public AppCall createAppCall(String content) {
            AppCall appCall = JoinAppGroupDialog.this.createBaseAppCall();
            Bundle params = new Bundle();
            params.putString(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, content);
            DialogPresenter.setupAppCallForWebDialog(appCall, JoinAppGroupDialog.JOIN_GAME_GROUP_DIALOG, params);
            return appCall;
        }
    }

    static {
        DEFAULT_REQUEST_CODE = RequestCodeOffset.AppGroupJoin.toRequestCode();
    }

    public static boolean canShow() {
        return true;
    }

    public static void show(Activity activity, String groupId) {
        new JoinAppGroupDialog(activity).show(groupId);
    }

    public static void show(Fragment fragment, String groupId) {
        show(new FragmentWrapper(fragment), groupId);
    }

    public static void show(android.app.Fragment fragment, String groupId) {
        show(new FragmentWrapper(fragment), groupId);
    }

    private static void show(FragmentWrapper fragmentWrapper, String groupId) {
        new JoinAppGroupDialog(fragmentWrapper).show(groupId);
    }

    public JoinAppGroupDialog(Activity activity) {
        super(activity, DEFAULT_REQUEST_CODE);
    }

    public JoinAppGroupDialog(Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    public JoinAppGroupDialog(android.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    private JoinAppGroupDialog(FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, DEFAULT_REQUEST_CODE);
    }

    protected void registerCallbackImpl(CallbackManagerImpl callbackManager, FacebookCallback<Result> callback) {
        callbackManager.registerCallback(getRequestCode(), new 2(callback == null ? null : new 1(callback, callback)));
    }

    protected AppCall createBaseAppCall() {
        return new AppCall(getRequestCode());
    }

    protected List<ModeHandler> getOrderedModeHandlers() {
        ArrayList<ModeHandler> handlers = new ArrayList();
        handlers.add(new WebHandler());
        return handlers;
    }
}
