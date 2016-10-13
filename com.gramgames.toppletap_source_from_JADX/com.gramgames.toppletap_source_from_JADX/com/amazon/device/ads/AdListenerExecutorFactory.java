package com.amazon.device.ads;

import android.graphics.Rect;

class AdListenerExecutorFactory {
    private final AdListenerExecutorConstructor adListenerExecutorConstructor;
    private final MobileAdsLoggerFactory loggerFactory;

    class 1 implements OnAdResizedCommand {
        final /* synthetic */ AdListenerExecutor val$adListenerExecutor;

        class 1 implements Runnable {
            final /* synthetic */ Ad val$ad;
            final /* synthetic */ Rect val$positionOnScreen;

            1(Ad ad, Rect rect) {
                this.val$ad = ad;
                this.val$positionOnScreen = rect;
            }

            public void run() {
                ((ExtendedAdListener) 1.this.val$adListenerExecutor.getAdListener()).onAdResized(this.val$ad, this.val$positionOnScreen);
            }
        }

        1(AdListenerExecutor adListenerExecutor) {
            this.val$adListenerExecutor = adListenerExecutor;
        }

        public void onAdResized(Ad ad, Rect positionOnScreen) {
            this.val$adListenerExecutor.execute(new 1(ad, positionOnScreen));
        }
    }

    class 2 implements OnAdExpiredCommand {
        final /* synthetic */ AdListenerExecutor val$adListenerExecutor;

        class 1 implements Runnable {
            final /* synthetic */ Ad val$ad;

            1(Ad ad) {
                this.val$ad = ad;
            }

            public void run() {
                ((ExtendedAdListener) 2.this.val$adListenerExecutor.getAdListener()).onAdExpired(this.val$ad);
            }
        }

        2(AdListenerExecutor adListenerExecutor) {
            this.val$adListenerExecutor = adListenerExecutor;
        }

        public void onAdExpired(Ad ad) {
            this.val$adListenerExecutor.execute(new 1(ad));
        }
    }

    static class AdListenerExecutorConstructor {
        AdListenerExecutorConstructor() {
        }

        public AdListenerExecutor createAdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory loggerFactory) {
            return new AdListenerExecutor(adListener, loggerFactory);
        }
    }

    public AdListenerExecutorFactory(MobileAdsLoggerFactory loggerFactory) {
        this(loggerFactory, new AdListenerExecutorConstructor());
    }

    public AdListenerExecutorFactory(MobileAdsLoggerFactory loggerFactory, AdListenerExecutorConstructor adListenerExecutorConstructor) {
        this.loggerFactory = loggerFactory;
        this.adListenerExecutorConstructor = adListenerExecutorConstructor;
    }

    protected MobileAdsLoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public AdListenerExecutor createAdListenerExecutor(AdListener adListener) {
        return createAdListenerExecutor(adListener, this.loggerFactory);
    }

    public AdListenerExecutor createAdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory loggerFactory) {
        AdListenerExecutor executor = this.adListenerExecutorConstructor.createAdListenerExecutor(adListener, loggerFactory);
        if (adListener instanceof ExtendedAdListener) {
            createAdResizedCommand(executor);
            createAdExpiredCommand(executor);
        }
        return executor;
    }

    private void createAdResizedCommand(AdListenerExecutor adListenerExecutor) {
        adListenerExecutor.setOnAdResizedCommand(new 1(adListenerExecutor));
    }

    private void createAdExpiredCommand(AdListenerExecutor adListenerExecutor) {
        adListenerExecutor.setOnAdExpiredCommand(new 2(adListenerExecutor));
    }
}
