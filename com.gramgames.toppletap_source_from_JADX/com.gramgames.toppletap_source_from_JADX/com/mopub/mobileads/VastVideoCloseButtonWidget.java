package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils.TruncateAt;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.CloseButtonDrawable;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.network.Networking;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageContainer;
import com.mopub.volley.toolbox.ImageLoader.ImageListener;
import gs.gram.mopub.BuildConfig;

public class VastVideoCloseButtonWidget extends RelativeLayout {
    @NonNull
    private CloseButtonDrawable mCloseButtonDrawable;
    private final int mEdgePadding;
    @NonNull
    private final ImageLoader mImageLoader;
    private final int mImagePadding;
    @NonNull
    private ImageView mImageView;
    private final int mTextRightMargin;
    @NonNull
    private TextView mTextView;
    private final int mWidgetHeight;

    class 1 implements ImageListener {
        final /* synthetic */ String val$imageUrl;

        1(String str) {
            this.val$imageUrl = str;
        }

        public void onResponse(ImageContainer imageContainer, boolean isImmediate) {
            Bitmap bitmap = imageContainer.getBitmap();
            if (bitmap != null) {
                VastVideoCloseButtonWidget.this.mImageView.setImageBitmap(bitmap);
                return;
            }
            MoPubLog.d(String.format("%s returned null bitmap", new Object[]{this.val$imageUrl}));
        }

        public void onErrorResponse(VolleyError volleyError) {
            MoPubLog.d("Failed to load image.", volleyError);
        }
    }

    public VastVideoCloseButtonWidget(@NonNull Context context) {
        super(context);
        setId((int) Utils.generateUniqueId());
        this.mEdgePadding = Dips.dipsToIntPixels(16.0f, context);
        this.mImagePadding = Dips.dipsToIntPixels(5.0f, context);
        this.mWidgetHeight = Dips.dipsToIntPixels(46.0f, context);
        this.mTextRightMargin = Dips.dipsToIntPixels(7.0f, context);
        this.mCloseButtonDrawable = new CloseButtonDrawable();
        this.mImageLoader = Networking.getImageLoader(context);
        createImageView();
        createTextView();
        LayoutParams layoutParams = new LayoutParams(-2, this.mWidgetHeight);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    private void createImageView() {
        this.mImageView = new ImageView(getContext());
        this.mImageView.setId((int) Utils.generateUniqueId());
        LayoutParams iconLayoutParams = new LayoutParams(this.mWidgetHeight, this.mWidgetHeight);
        iconLayoutParams.addRule(11);
        this.mImageView.setImageDrawable(this.mCloseButtonDrawable);
        this.mImageView.setPadding(this.mImagePadding, this.mImagePadding + this.mEdgePadding, this.mImagePadding + this.mEdgePadding, this.mImagePadding);
        addView(this.mImageView, iconLayoutParams);
    }

    private void createTextView() {
        this.mTextView = new TextView(getContext());
        this.mTextView.setSingleLine();
        this.mTextView.setEllipsize(TruncateAt.END);
        this.mTextView.setTextColor(-1);
        this.mTextView.setTextSize(CloseButton.TEXT_SIZE_SP);
        this.mTextView.setTypeface(CloseButton.TEXT_TYPEFACE);
        this.mTextView.setText(BuildConfig.FLAVOR);
        LayoutParams textLayoutParams = new LayoutParams(-2, -2);
        textLayoutParams.addRule(15);
        textLayoutParams.addRule(0, this.mImageView.getId());
        this.mTextView.setPadding(0, this.mEdgePadding, 0, 0);
        textLayoutParams.setMargins(0, 0, this.mTextRightMargin, 0);
        addView(this.mTextView, textLayoutParams);
    }

    void updateCloseButtonText(@Nullable String text) {
        if (this.mTextView != null) {
            this.mTextView.setText(text);
        }
    }

    void updateCloseButtonIcon(@NonNull String imageUrl) {
        this.mImageLoader.get(imageUrl, new 1(imageUrl));
    }

    void setOnTouchListenerToContent(@Nullable OnTouchListener onTouchListener) {
        this.mImageView.setOnTouchListener(onTouchListener);
        this.mTextView.setOnTouchListener(onTouchListener);
    }

    @Deprecated
    @VisibleForTesting
    ImageView getImageView() {
        return this.mImageView;
    }

    @Deprecated
    @VisibleForTesting
    void setImageView(ImageView imageView) {
        this.mImageView = imageView;
    }

    @Deprecated
    @VisibleForTesting
    TextView getTextView() {
        return this.mTextView;
    }
}
