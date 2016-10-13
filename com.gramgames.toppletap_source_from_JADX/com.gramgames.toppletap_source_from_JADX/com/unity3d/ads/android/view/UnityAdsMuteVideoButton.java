package com.unity3d.ads.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class UnityAdsMuteVideoButton extends RelativeLayout {
    private RelativeLayout _layout;
    private UnityAdsMuteVideoButtonState _state;

    /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$android$view$UnityAdsMuteVideoButton$UnityAdsMuteVideoButtonState;

        static {
            $SwitchMap$com$unity3d$ads$android$view$UnityAdsMuteVideoButton$UnityAdsMuteVideoButtonState = new int[UnityAdsMuteVideoButtonState.values().length];
            try {
                $SwitchMap$com$unity3d$ads$android$view$UnityAdsMuteVideoButton$UnityAdsMuteVideoButtonState[UnityAdsMuteVideoButtonState.Muted.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$view$UnityAdsMuteVideoButton$UnityAdsMuteVideoButtonState[UnityAdsMuteVideoButtonState.UnMuted.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum UnityAdsMuteVideoButtonState {
        UnMuted,
        Muted
    }

    public UnityAdsMuteVideoButton(Context context) {
        super(context);
        this._state = UnityAdsMuteVideoButtonState.UnMuted;
        this._layout = null;
    }

    public UnityAdsMuteVideoButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this._state = UnityAdsMuteVideoButtonState.UnMuted;
        this._layout = null;
    }

    public UnityAdsMuteVideoButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this._state = UnityAdsMuteVideoButtonState.UnMuted;
        this._layout = null;
    }

    public void setLayout(RelativeLayout relativeLayout) {
        this._layout = relativeLayout;
    }

    public void setState(UnityAdsMuteVideoButtonState unityAdsMuteVideoButtonState) {
        if (unityAdsMuteVideoButtonState != null && !unityAdsMuteVideoButtonState.equals(this._state)) {
            this._state = unityAdsMuteVideoButtonState;
            View findViewById = this._layout.findViewById(R.id.unityAdsMuteButtonSpeakerX);
            View findViewById2 = this._layout.findViewById(R.id.unityAdsMuteButtonSpeakerWaves);
            if (findViewById != null && findViewById2 != null) {
                switch (1.$SwitchMap$com$unity3d$ads$android$view$UnityAdsMuteVideoButton$UnityAdsMuteVideoButtonState[this._state.ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        findViewById.setVisibility(0);
                        findViewById2.setVisibility(8);
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                        findViewById.setVisibility(8);
                        findViewById2.setVisibility(0);
                    default:
                        UnityAdsDeviceLog.debug("Invalid state: " + this._state);
                }
            }
        }
    }
}
