package com.facebook.unity;

import android.os.Bundle;
import com.facebook.CallbackManager;
import org.json.simple.parser.Yytoken;

public class FBUnityLoginActivity extends BaseActivity {
    public static final String LOGIN_PARAMS = "login_params";
    public static final String LOGIN_TYPE = "login_type";

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$unity$FBUnityLoginActivity$LoginType;

        static {
            $SwitchMap$com$facebook$unity$FBUnityLoginActivity$LoginType = new int[LoginType.values().length];
            try {
                $SwitchMap$com$facebook$unity$FBUnityLoginActivity$LoginType[LoginType.READ.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$unity$FBUnityLoginActivity$LoginType[LoginType.PUBLISH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum LoginType {
        READ,
        PUBLISH
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginType type = (LoginType) getIntent().getSerializableExtra(LOGIN_TYPE);
        String loginParams = getIntent().getStringExtra(LOGIN_PARAMS);
        switch (1.$SwitchMap$com$facebook$unity$FBUnityLoginActivity$LoginType[type.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                FBLogin.loginWithReadPermissions(loginParams, this);
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                FBLogin.loginWithPublishPermissions(loginParams, this);
            default:
        }
    }

    public CallbackManager getCallbackManager() {
        return this.mCallbackManager;
    }
}
