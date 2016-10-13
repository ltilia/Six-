package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.VisibleForTesting;

public class NativeClickHandler {
    private boolean mClickInProgress;
    @NonNull
    private final Context mContext;
    @Nullable
    private final String mDspCreativeId;

    class 1 implements OnClickListener {
        final /* synthetic */ ClickInterface val$clickInterface;

        1(ClickInterface clickInterface) {
            this.val$clickInterface = clickInterface;
        }

        public void onClick(View v) {
            this.val$clickInterface.handleClick(v);
        }
    }

    class 2 implements ResultActions {
        final /* synthetic */ SpinningProgressView val$spinningProgressView;
        final /* synthetic */ View val$view;

        2(View view, SpinningProgressView spinningProgressView) {
            this.val$view = view;
            this.val$spinningProgressView = spinningProgressView;
        }

        public void urlHandlingSucceeded(@NonNull String url, @NonNull UrlAction urlAction) {
            removeSpinningProgressView();
            NativeClickHandler.this.mClickInProgress = false;
        }

        public void urlHandlingFailed(@NonNull String url, @NonNull UrlAction lastFailedUrlAction) {
            removeSpinningProgressView();
            NativeClickHandler.this.mClickInProgress = false;
        }

        private void removeSpinningProgressView() {
            if (this.val$view != null) {
                this.val$spinningProgressView.removeFromRoot();
            }
        }
    }

    public NativeClickHandler(@NonNull Context context) {
        this(context, null);
    }

    public NativeClickHandler(@NonNull Context context, @Nullable String dspCreativeId) {
        Preconditions.checkNotNull(context);
        this.mContext = context.getApplicationContext();
        this.mDspCreativeId = dspCreativeId;
    }

    public void setOnClickListener(@NonNull View view, @NonNull ClickInterface clickInterface) {
        if (NoThrow.checkNotNull(view, "Cannot set click listener on a null view") && NoThrow.checkNotNull(clickInterface, "Cannot set click listener with a null ClickInterface")) {
            setOnClickListener(view, new 1(clickInterface));
        }
    }

    private void setOnClickListener(@NonNull View view, @Nullable OnClickListener onClickListener) {
        view.setOnClickListener(onClickListener);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setOnClickListener(viewGroup.getChildAt(i), onClickListener);
            }
        }
    }

    public void clearOnClickListener(@NonNull View view) {
        if (NoThrow.checkNotNull(view, "Cannot clear click listener from a null view")) {
            setOnClickListener(view, (OnClickListener) null);
        }
    }

    public void openClickDestinationUrl(@NonNull String clickDestinationUrl, @Nullable View view) {
        openClickDestinationUrl(clickDestinationUrl, view, new SpinningProgressView(this.mContext));
    }

    @VisibleForTesting
    void openClickDestinationUrl(@NonNull String clickDestinationUrl, @Nullable View view, @NonNull SpinningProgressView spinningProgressView) {
        if (NoThrow.checkNotNull(clickDestinationUrl, "Cannot open a null click destination url")) {
            Preconditions.checkNotNull(spinningProgressView);
            if (!this.mClickInProgress) {
                this.mClickInProgress = true;
                if (view != null) {
                    spinningProgressView.addToRoot(view);
                }
                Builder builder = new Builder();
                if (!TextUtils.isEmpty(this.mDspCreativeId)) {
                    builder.withDspCreativeId(this.mDspCreativeId);
                }
                builder.withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new 2(view, spinningProgressView)).build().handleUrl(this.mContext, clickDestinationUrl);
            }
        }
    }
}
