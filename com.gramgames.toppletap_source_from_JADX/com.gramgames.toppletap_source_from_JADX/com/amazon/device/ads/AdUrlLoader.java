package com.amazon.device.ads;

import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import com.amazon.device.ads.WebRequest.WebResponse;
import com.mopub.common.Constants;

class AdUrlLoader {
    private static final String LOGTAG;
    private final AdControlAccessor adControlAccessor;
    private final AdWebViewClient adWebViewClient;
    private final DeviceInfo deviceInfo;
    private final MobileAdsLogger logger;
    private final ThreadRunner threadRunner;
    private final WebRequestFactory webRequestFactory;
    private final WebUtils2 webUtils;

    class 1 implements Runnable {
        final /* synthetic */ PreloadCallback val$callback;
        final /* synthetic */ boolean val$shouldPreload;
        final /* synthetic */ String val$url;

        1(String str, boolean z, PreloadCallback preloadCallback) {
            this.val$url = str;
            this.val$shouldPreload = z;
            this.val$callback = preloadCallback;
        }

        public void run() {
            AdUrlLoader.this.loadUrlInThread(this.val$url, this.val$shouldPreload, this.val$callback);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ String val$body;
        final /* synthetic */ PreloadCallback val$callback;
        final /* synthetic */ boolean val$shouldPreload;
        final /* synthetic */ String val$url;

        2(String str, String str2, boolean z, PreloadCallback preloadCallback) {
            this.val$url = str;
            this.val$body = str2;
            this.val$shouldPreload = z;
            this.val$callback = preloadCallback;
        }

        public void run() {
            AdUrlLoader.this.adControlAccessor.loadHtml(this.val$url, this.val$body, this.val$shouldPreload, this.val$callback);
        }
    }

    static {
        LOGTAG = AdUrlLoader.class.getSimpleName();
    }

    public AdUrlLoader(ThreadRunner threadRunner, AdWebViewClient adWebViewClient, WebRequestFactory webRequestFactory, AdControlAccessor adControlAccessor, WebUtils2 webUtils, MobileAdsLoggerFactory loggerFactory, DeviceInfo deviceInfo) {
        this.threadRunner = threadRunner;
        this.adWebViewClient = adWebViewClient;
        this.webRequestFactory = webRequestFactory;
        this.adControlAccessor = adControlAccessor;
        this.webUtils = webUtils;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.deviceInfo = deviceInfo;
    }

    public void putUrlExecutorInAdWebViewClient(String scheme, UrlExecutor executor) {
        this.adWebViewClient.putUrlExecutor(scheme, executor);
    }

    public void setAdWebViewClientListener(AdWebViewClientListener adWebViewClientListener) {
        this.adWebViewClient.setListener(adWebViewClientListener);
    }

    public AdWebViewClient getAdWebViewClient() {
        return this.adWebViewClient;
    }

    public void loadUrl(String url, boolean shouldPreload, PreloadCallback callback) {
        String scheme = this.webUtils.getScheme(url);
        if (scheme.equals(Constants.HTTP) || scheme.equals(Constants.HTTPS)) {
            this.threadRunner.execute(new 1(url, shouldPreload, callback), ExecutionStyle.RUN_ASAP, ExecutionThread.BACKGROUND_THREAD);
        } else {
            openUrl(url);
        }
    }

    private void loadUrlInThread(String url, boolean shouldPreload, PreloadCallback callback) {
        WebRequest webRequest = this.webRequestFactory.createWebRequest();
        webRequest.setExternalLogTag(LOGTAG);
        webRequest.enableLogUrl(true);
        webRequest.setUrlString(url);
        webRequest.putHeader("User-Agent", this.deviceInfo.getUserAgentString());
        WebResponse response = null;
        try {
            response = webRequest.makeCall();
        } catch (WebRequestException e) {
            this.logger.e("Could not load URL (%s) into AdContainer: %s", url, e.getMessage());
        }
        if (response != null) {
            String body = response.getResponseReader().readAsString();
            if (body != null) {
                this.threadRunner.execute(new 2(url, body, shouldPreload, callback), ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
                return;
            }
            this.logger.e("Could not load URL (%s) into AdContainer.", url);
        }
    }

    public void openUrl(String url) {
        this.adWebViewClient.openUrl(url);
    }
}
