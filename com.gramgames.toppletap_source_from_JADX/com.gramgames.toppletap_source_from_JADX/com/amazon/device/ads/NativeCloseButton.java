package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import com.unity3d.ads.android.R;
import org.json.simple.parser.Yytoken;

class NativeCloseButton {
    private static final int CLOSE_BUTTON_SIZE_DP = 60;
    private static final int CLOSE_BUTTON_TAP_TARGET_SIZE_DP = 80;
    private static final String CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON = "nativeCloseButton";
    private static final String CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_CONTAINER = "nativeCloseButtonContainer";
    private static final String CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_IMAGE = "nativeCloseButtonImage";
    private final AdCloser adCloser;
    private ViewGroup closeButton;
    private ViewGroup closeButtonContainer;
    private ImageView closeButtonImage;
    private boolean hasNativeCloseButton;
    private final ImageViewFactory imageViewFactory;
    private final LayoutFactory layoutFactory;
    private final ThreadRunner threadRunner;
    private final ViewGroup viewGroup;

    class 1 extends MobileAdsAsyncTask<Void, Void, Void> {
        final /* synthetic */ int val$buttonSize;
        final /* synthetic */ RelativePosition val$position;
        final /* synthetic */ boolean val$showImage;
        final /* synthetic */ int val$tapTargetSize;

        1(int i, boolean z, RelativePosition relativePosition, int i2) {
            this.val$tapTargetSize = i;
            this.val$showImage = z;
            this.val$position = relativePosition;
            this.val$buttonSize = i2;
        }

        protected Void doInBackground(Void... empty) {
            NativeCloseButton.this.createButtonIfNeeded(this.val$tapTargetSize);
            return null;
        }

        protected void onPostExecute(Void result) {
            NativeCloseButton.this.addCloseButtonToTapTargetIfNeeded(this.val$showImage, this.val$position, this.val$buttonSize, this.val$tapTargetSize);
        }
    }

    class 2 implements OnClickListener {
        2() {
        }

        public void onClick(View v) {
            NativeCloseButton.this.closeAd();
        }
    }

    class 3 implements OnTouchListener {
        final /* synthetic */ BitmapDrawable val$closeNormal;
        final /* synthetic */ BitmapDrawable val$closePressed;

        3(BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
            this.val$closeNormal = bitmapDrawable;
            this.val$closePressed = bitmapDrawable2;
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View v, MotionEvent event) {
            NativeCloseButton.this.animateCloseButton(event, this.val$closeNormal, this.val$closePressed);
            return false;
        }
    }

    class 4 implements Runnable {
        4() {
        }

        public void run() {
            NativeCloseButton.this.removeNativeCloseButtonOnMainThread();
        }
    }

    class 5 implements Runnable {
        5() {
        }

        public void run() {
            NativeCloseButton.this.hideImageOnMainThread();
        }
    }

    static /* synthetic */ class 6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$RelativePosition;

        static {
            $SwitchMap$com$amazon$device$ads$RelativePosition = new int[RelativePosition.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.BOTTOM_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.BOTTOM_LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.BOTTOM_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.TOP_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.TOP_LEFT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.TOP_RIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public NativeCloseButton(ViewGroup viewGroup, AdCloser adCloser) {
        this(viewGroup, adCloser, ThreadUtils.getThreadRunner(), new LayoutFactory(), new ImageButtonFactory());
    }

    NativeCloseButton(ViewGroup viewGroup, AdCloser adCloser, ThreadRunner threadRunner, LayoutFactory layoutFactory, ImageViewFactory imageViewFactory) {
        this.hasNativeCloseButton = false;
        this.viewGroup = viewGroup;
        this.adCloser = adCloser;
        this.threadRunner = threadRunner;
        this.layoutFactory = layoutFactory;
        this.imageViewFactory = imageViewFactory;
    }

    private Context getContext() {
        return this.viewGroup.getContext();
    }

    public void enable(boolean showImage, RelativePosition position) {
        this.hasNativeCloseButton = true;
        if (this.closeButton == null || this.closeButtonImage == null || !this.viewGroup.equals(this.closeButton.getParent()) || (!this.closeButton.equals(this.closeButtonImage.getParent()) && showImage)) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
            int tapTargetSize = (int) ((80.0f * metrics.density) + 0.5f);
            this.threadRunner.executeAsyncTask(new 1(tapTargetSize, showImage, position, (int) ((60.0f * metrics.density) + 0.5f)), new Void[0]);
        } else if (!showImage) {
            hideImage();
        }
    }

    private void createButtonIfNeeded(int tapTargetSize) {
        boolean createButton = false;
        synchronized (this) {
            if (this.closeButton == null) {
                this.closeButton = this.layoutFactory.createLayout(getContext(), LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON);
                this.closeButtonImage = this.imageViewFactory.createImageView(getContext(), CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_IMAGE);
                createButton = true;
            }
        }
        if (createButton) {
            BitmapDrawable closeNormal = this.imageViewFactory.createBitmapDrawable(getContext().getResources(), Assets.getInstance().getFilePath(Assets.CLOSE_NORMAL));
            BitmapDrawable closePressed = this.imageViewFactory.createBitmapDrawable(getContext().getResources(), Assets.getInstance().getFilePath(Assets.CLOSE_PRESSED));
            this.closeButtonImage.setImageDrawable(closeNormal);
            this.closeButtonImage.setScaleType(ScaleType.FIT_CENTER);
            this.closeButtonImage.setBackgroundDrawable(null);
            OnClickListener onClickListener = new 2();
            this.closeButtonImage.setOnClickListener(onClickListener);
            this.closeButton.setOnClickListener(onClickListener);
            OnTouchListener onTouchListener = new 3(closeNormal, closePressed);
            this.closeButton.setOnTouchListener(onTouchListener);
            this.closeButtonImage.setOnTouchListener(onTouchListener);
            LayoutParams layoutParams = new LayoutParams(tapTargetSize, tapTargetSize);
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            this.closeButtonContainer = this.layoutFactory.createLayout(getContext(), LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_CONTAINER);
            this.closeButtonContainer.addView(this.closeButton, layoutParams);
        }
    }

    private void closeAd() {
        this.adCloser.closeAd();
    }

    private void animateCloseButton(MotionEvent motionEvent, BitmapDrawable closeNormal, BitmapDrawable closePressed) {
        switch (motionEvent.getAction()) {
            case Yylex.YYINITIAL /*0*/:
                this.closeButtonImage.setImageDrawable(closePressed);
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                this.closeButtonImage.setImageDrawable(closeNormal);
            default:
        }
    }

    @SuppressLint({"InlinedApi"})
    private void addCloseButtonToTapTargetIfNeeded(boolean showImage, RelativePosition position, int buttonSize, int tapTargetSize) {
        LayoutParams params;
        if (showImage && !this.closeButton.equals(this.closeButtonImage.getParent())) {
            params = new LayoutParams(buttonSize, buttonSize);
            params.addRule(13);
            this.closeButton.addView(this.closeButtonImage, params);
        } else if (!showImage && this.closeButton.equals(this.closeButtonImage.getParent())) {
            this.closeButton.removeView(this.closeButtonImage);
        }
        if (!this.viewGroup.equals(this.closeButtonContainer.getParent())) {
            this.viewGroup.addView(this.closeButtonContainer, new FrameLayout.LayoutParams(-1, -1));
        }
        params = new LayoutParams(tapTargetSize, tapTargetSize);
        RelativePosition p = position;
        if (position == null) {
            p = RelativePosition.TOP_RIGHT;
        }
        switch (6.$SwitchMap$com$amazon$device$ads$RelativePosition[p.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                params.addRule(12);
                params.addRule(14);
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                params.addRule(12);
                params.addRule(9);
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                params.addRule(12);
                params.addRule(11);
                break;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                params.addRule(13);
                break;
            case Yytoken.TYPE_COMMA /*5*/:
                params.addRule(10);
                params.addRule(14);
                break;
            case Yytoken.TYPE_COLON /*6*/:
                params.addRule(10);
                params.addRule(9);
                break;
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                params.addRule(10);
                params.addRule(11);
                break;
            default:
                params.addRule(10);
                params.addRule(11);
                break;
        }
        this.closeButton.setLayoutParams(params);
        this.closeButtonContainer.bringToFront();
    }

    public void remove() {
        this.hasNativeCloseButton = false;
        this.threadRunner.execute(new 4(), ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    private void removeNativeCloseButtonOnMainThread() {
        this.viewGroup.removeView(this.closeButtonContainer);
    }

    public void showImage(boolean show) {
        if (this.hasNativeCloseButton && this.closeButton != null) {
            if (show) {
                enable(true, null);
            } else {
                hideImage();
            }
        }
    }

    private void hideImage() {
        this.threadRunner.execute(new 5(), ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    private void hideImageOnMainThread() {
        this.closeButton.removeAllViews();
    }
}
