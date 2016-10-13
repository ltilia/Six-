package com.amazon.device.ads;

import android.graphics.Rect;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;

class AdListenerExecutor {
    private static final String LOGTAG;
    private final AdListener adListener;
    private final MobileAdsLogger logger;
    private OnAdEventCommand onAdEventCommand;
    private OnAdExpiredCommand onAdExpiredCommand;
    private OnAdReceivedCommand onAdReceivedCommand;
    private OnAdResizedCommand onAdResizedCommand;
    private OnSpecialUrlClickedCommand onSpecialUrlClickedCommand;
    private final ThreadRunner threadRunner;

    class 1 implements Runnable {
        final /* synthetic */ Ad val$ad;
        final /* synthetic */ AdProperties val$adProperties;

        1(Ad ad, AdProperties adProperties) {
            this.val$ad = ad;
            this.val$adProperties = adProperties;
        }

        public void run() {
            AdListenerExecutor.this.getAdListener().onAdLoaded(this.val$ad, this.val$adProperties);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ Ad val$ad;
        final /* synthetic */ AdError val$adError;

        2(Ad ad, AdError adError) {
            this.val$ad = ad;
            this.val$adError = adError;
        }

        public void run() {
            AdListenerExecutor.this.getAdListener().onAdFailedToLoad(this.val$ad, this.val$adError);
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ Ad val$ad;

        3(Ad ad) {
            this.val$ad = ad;
        }

        public void run() {
            AdListenerExecutor.this.getAdListener().onAdExpanded(this.val$ad);
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ Ad val$ad;

        4(Ad ad) {
            this.val$ad = ad;
        }

        public void run() {
            AdListenerExecutor.this.getAdListener().onAdCollapsed(this.val$ad);
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ Ad val$ad;

        5(Ad ad) {
            this.val$ad = ad;
        }

        public void run() {
            AdListenerExecutor.this.getAdListener().onAdDismissed(this.val$ad);
        }
    }

    static {
        LOGTAG = AdListenerExecutor.class.getSimpleName();
    }

    public AdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory loggerFactory) {
        this(adListener, ThreadUtils.getThreadRunner(), loggerFactory);
    }

    AdListenerExecutor(AdListener adListener, ThreadRunner threadRunner, MobileAdsLoggerFactory loggerFactory) {
        this.adListener = adListener;
        this.threadRunner = threadRunner;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
    }

    AdListener getAdListener() {
        return this.adListener;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        execute(new 1(ad, adProperties));
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        execute(new 2(ad, adError));
    }

    public void onAdExpanded(Ad ad) {
        execute(new 3(ad));
    }

    public void onAdCollapsed(Ad ad) {
        execute(new 4(ad));
    }

    public void onAdDismissed(Ad ad) {
        execute(new 5(ad));
    }

    public void onAdResized(Ad ad, Rect positionOnScreen) {
        if (this.onAdResizedCommand == null) {
            this.logger.d("Ad listener called - Ad Resized.");
        } else {
            this.onAdResizedCommand.onAdResized(ad, positionOnScreen);
        }
    }

    public void onAdExpired(Ad ad) {
        if (this.onAdExpiredCommand == null) {
            this.logger.d("Ad listener called - Ad Expired.");
        } else {
            this.onAdExpiredCommand.onAdExpired(ad);
        }
    }

    public void onSpecialUrlClicked(Ad ad, String url) {
        if (this.onSpecialUrlClickedCommand == null) {
            this.logger.d("Ad listener called - Special Url Clicked.");
        } else {
            this.onSpecialUrlClickedCommand.onSpecialUrlClicked(ad, url);
        }
    }

    public ActionCode onAdReceived(Ad ad, AdData adData) {
        if (this.onAdReceivedCommand != null) {
            return this.onAdReceivedCommand.onAdReceived(ad, adData);
        }
        this.logger.d("Ad listener called - Ad Received.");
        return ActionCode.DISPLAY;
    }

    public void onAdEvent(AdEvent adEvent) {
        if (this.onAdEventCommand == null) {
            this.logger.d("Ad listener called - Ad Event: " + adEvent);
        } else {
            this.onAdEventCommand.onAdEvent(adEvent);
        }
    }

    protected void execute(Runnable runnable) {
        this.threadRunner.execute(runnable, ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
    }

    public void setOnAdEventCommand(OnAdEventCommand onAdEventCommand) {
        this.onAdEventCommand = onAdEventCommand;
    }

    public void setOnAdResizedCommand(OnAdResizedCommand onAdResizedCommand) {
        this.onAdResizedCommand = onAdResizedCommand;
    }

    public void setOnAdExpiredCommand(OnAdExpiredCommand onAdExpiredCommand) {
        this.onAdExpiredCommand = onAdExpiredCommand;
    }

    public void setOnSpecialUrlClickedCommand(OnSpecialUrlClickedCommand onSpecialUrlClickedCommand) {
        this.onSpecialUrlClickedCommand = onSpecialUrlClickedCommand;
    }

    public void setOnAdReceivedCommand(OnAdReceivedCommand onAdReceivedCommand) {
        this.onAdReceivedCommand = onAdReceivedCommand;
    }
}
