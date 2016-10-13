package com.facebook.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.R;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.PermissionsPair;
import com.facebook.internal.Validate;
import com.facebook.login.LoginClient.Request;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class DeviceAuthDialog extends DialogFragment {
    private static final String DEVICE_LOGIN_ENDPOINT = "device/login";
    private static final String DEVICE_LOGIN_STATUS_ENDPOINT = "device/login_status";
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_DECLINED = 1349173;
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_PENDING = 1349174;
    private static final int LOGIN_ERROR_SUBCODE_CODE_EXPIRED = 1349152;
    private static final int LOGIN_ERROR_SUBCODE_EXCESSIVE_POLLING = 1349172;
    private static final String REQUEST_STATE_KEY = "request_state";
    private AtomicBoolean completed;
    private TextView confirmationCode;
    private volatile GraphRequestAsyncTask currentGraphRequestPoll;
    private volatile RequestState currentRequestState;
    private DeviceAuthMethodHandler deviceAuthMethodHandler;
    private Dialog dialog;
    private boolean isBeingDestroyed;
    private ProgressBar progressBar;
    private volatile ScheduledFuture scheduledPoll;

    class 1 implements OnClickListener {
        1() {
        }

        public void onClick(View v) {
            DeviceAuthDialog.this.onCancel();
        }
    }

    class 2 implements Callback {
        2() {
        }

        public void onCompleted(GraphResponse response) {
            if (response.getError() != null) {
                DeviceAuthDialog.this.onError(response.getError().getException());
                return;
            }
            JSONObject jsonObject = response.getJSONObject();
            RequestState requestState = new RequestState();
            try {
                requestState.setUserCode(jsonObject.getString("user_code"));
                requestState.setRequestCode(jsonObject.getString("code"));
                requestState.setInterval(jsonObject.getLong("interval"));
                DeviceAuthDialog.this.setCurrentRequestState(requestState);
            } catch (Throwable ex) {
                DeviceAuthDialog.this.onError(new FacebookException(ex));
            }
        }
    }

    class 3 implements Runnable {
        3() {
        }

        public void run() {
            DeviceAuthDialog.this.poll();
        }
    }

    class 4 implements Callback {
        4() {
        }

        public void onCompleted(GraphResponse response) {
            if (!DeviceAuthDialog.this.completed.get()) {
                FacebookRequestError error = response.getError();
                if (error != null) {
                    switch (error.getSubErrorCode()) {
                        case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_CODE_EXPIRED /*1349152*/:
                        case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_AUTHORIZATION_DECLINED /*1349173*/:
                            DeviceAuthDialog.this.onCancel();
                            return;
                        case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_EXCESSIVE_POLLING /*1349172*/:
                        case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_AUTHORIZATION_PENDING /*1349174*/:
                            DeviceAuthDialog.this.schedulePoll();
                            return;
                        default:
                            DeviceAuthDialog.this.onError(response.getError().getException());
                            return;
                    }
                }
                try {
                    DeviceAuthDialog.this.onSuccess(response.getJSONObject().getString(ServerProtocol.DIALOG_PARAM_ACCESS_TOKEN));
                } catch (Throwable ex) {
                    DeviceAuthDialog.this.onError(new FacebookException(ex));
                }
            }
        }
    }

    class 5 implements Callback {
        final /* synthetic */ String val$accessToken;

        5(String str) {
            this.val$accessToken = str;
        }

        public void onCompleted(GraphResponse response) {
            if (!DeviceAuthDialog.this.completed.get()) {
                if (response.getError() != null) {
                    DeviceAuthDialog.this.onError(response.getError().getException());
                    return;
                }
                try {
                    JSONObject jsonObject = response.getJSONObject();
                    String userId = jsonObject.getString(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
                    PermissionsPair permissions = Utility.handlePermissionResponse(jsonObject);
                    DeviceAuthDialog.this.deviceAuthMethodHandler.onSuccess(this.val$accessToken, FacebookSdk.getApplicationId(), userId, permissions.getGrantedPermissions(), permissions.getDeclinedPermissions(), AccessTokenSource.DEVICE_AUTH, null, null);
                    DeviceAuthDialog.this.dialog.dismiss();
                } catch (Throwable ex) {
                    DeviceAuthDialog.this.onError(new FacebookException(ex));
                }
            }
        }
    }

    private static class RequestState implements Parcelable {
        public static final Creator<RequestState> CREATOR;
        private long interval;
        private long lastPoll;
        private String requestCode;
        private String userCode;

        static class 1 implements Creator<RequestState> {
            1() {
            }

            public RequestState createFromParcel(Parcel in) {
                return new RequestState(in);
            }

            public RequestState[] newArray(int size) {
                return new RequestState[size];
            }
        }

        RequestState() {
        }

        public String getUserCode() {
            return this.userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getRequestCode() {
            return this.requestCode;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public long getInterval() {
            return this.interval;
        }

        public void setInterval(long interval) {
            this.interval = interval;
        }

        public void setLastPoll(long lastPoll) {
            this.lastPoll = lastPoll;
        }

        protected RequestState(Parcel in) {
            this.userCode = in.readString();
            this.requestCode = in.readString();
            this.interval = in.readLong();
            this.lastPoll = in.readLong();
        }

        public boolean withinLastRefreshWindow() {
            if (this.lastPoll != 0 && (new Date().getTime() - this.lastPoll) - (this.interval * 1000) < 0) {
                return true;
            }
            return false;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.userCode);
            dest.writeString(this.requestCode);
            dest.writeLong(this.interval);
            dest.writeLong(this.lastPoll);
        }

        static {
            CREATOR = new 1();
        }
    }

    public DeviceAuthDialog() {
        this.completed = new AtomicBoolean();
        this.isBeingDestroyed = false;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        this.deviceAuthMethodHandler = (DeviceAuthMethodHandler) ((LoginFragment) ((FacebookActivity) getActivity()).getCurrentFragment()).getLoginClient().getCurrentHandler();
        if (savedInstanceState != null) {
            RequestState requestState = (RequestState) savedInstanceState.getParcelable(REQUEST_STATE_KEY);
            if (requestState != null) {
                setCurrentRequestState(requestState);
            }
        }
        return view;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.dialog = new Dialog(getActivity(), R.style.com_facebook_auth_dialog);
        View view = getActivity().getLayoutInflater().inflate(R.layout.com_facebook_device_auth_dialog_fragment, null);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        this.confirmationCode = (TextView) view.findViewById(R.id.confirmation_code);
        ((Button) view.findViewById(R.id.cancel_button)).setOnClickListener(new 1());
        ((TextView) view.findViewById(R.id.com_facebook_device_auth_instructions)).setText(Html.fromHtml(getString(R.string.com_facebook_device_auth_instructions)));
        this.dialog.setContentView(view);
        return this.dialog;
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (!this.isBeingDestroyed) {
            onCancel();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.currentRequestState != null) {
            outState.putParcelable(REQUEST_STATE_KEY, this.currentRequestState);
        }
    }

    public void onDestroy() {
        this.isBeingDestroyed = true;
        this.completed.set(true);
        super.onDestroy();
        if (this.currentGraphRequestPoll != null) {
            this.currentGraphRequestPoll.cancel(true);
        }
        if (this.scheduledPoll != null) {
            this.scheduledPoll.cancel(true);
        }
    }

    public void startLogin(Request request) {
        Bundle parameters = new Bundle();
        parameters.putString(ServerProtocol.DIALOG_PARAM_SCOPE, TextUtils.join(",", request.getPermissions()));
        String redirectUriString = request.getDeviceRedirectUriString();
        if (redirectUriString != null) {
            parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, redirectUriString);
        }
        parameters.putString(ServerProtocol.DIALOG_PARAM_ACCESS_TOKEN, Validate.hasAppID() + "|" + Validate.hasClientToken());
        new GraphRequest(null, DEVICE_LOGIN_ENDPOINT, parameters, HttpMethod.POST, new 2()).executeAsync();
    }

    private void setCurrentRequestState(RequestState currentRequestState) {
        this.currentRequestState = currentRequestState;
        this.confirmationCode.setText(currentRequestState.getUserCode());
        this.confirmationCode.setVisibility(0);
        this.progressBar.setVisibility(8);
        if (currentRequestState.withinLastRefreshWindow()) {
            schedulePoll();
        } else {
            poll();
        }
    }

    private void poll() {
        this.currentRequestState.setLastPoll(new Date().getTime());
        this.currentGraphRequestPoll = getPollRequest().executeAsync();
    }

    private void schedulePoll() {
        this.scheduledPoll = DeviceAuthMethodHandler.getBackgroundExecutor().schedule(new 3(), this.currentRequestState.getInterval(), TimeUnit.SECONDS);
    }

    private GraphRequest getPollRequest() {
        Bundle parameters = new Bundle();
        parameters.putString("code", this.currentRequestState.getRequestCode());
        return new GraphRequest(null, DEVICE_LOGIN_STATUS_ENDPOINT, parameters, HttpMethod.POST, new 4());
    }

    private void onSuccess(String accessToken) {
        Bundle parameters = new Bundle();
        parameters.putString(GraphRequest.FIELDS_PARAM, "id,permissions");
        AccessToken accessToken2 = new AccessToken(accessToken, FacebookSdk.getApplicationId(), AppEventsConstants.EVENT_PARAM_VALUE_NO, null, null, null, null, null);
        new GraphRequest(temporaryToken, "me", parameters, HttpMethod.GET, new 5(accessToken)).executeAsync();
    }

    private void onError(FacebookException ex) {
        if (this.completed.compareAndSet(false, true)) {
            this.deviceAuthMethodHandler.onError(ex);
            this.dialog.dismiss();
        }
    }

    private void onCancel() {
        if (this.completed.compareAndSet(false, true)) {
            if (this.deviceAuthMethodHandler != null) {
                this.deviceAuthMethodHandler.onCancel();
            }
            this.dialog.dismiss();
        }
    }
}
