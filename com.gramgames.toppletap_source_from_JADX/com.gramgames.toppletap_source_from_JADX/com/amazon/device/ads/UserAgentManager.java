package com.amazon.device.ads;

import android.content.Context;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;

class UserAgentManager {
    private final ThreadRunner threadRunner;
    private String userAgentStringWithSDKVersion;
    private String userAgentStringWithoutSDKVersion;
    private final WebViewFactory webViewFactory;

    class 1 implements Runnable {
        final /* synthetic */ Context val$context;

        1(Context context) {
            this.val$context = context;
        }

        public void run() {
            UserAgentManager.this.setUserAgentString(UserAgentManager.this.webViewFactory.createWebView(this.val$context).getSettings().getUserAgentString());
        }
    }

    public UserAgentManager() {
        this(new ThreadRunner(), WebViewFactory.getInstance());
    }

    UserAgentManager(ThreadRunner threadRunner, WebViewFactory webViewFactory) {
        this.threadRunner = threadRunner;
        this.webViewFactory = webViewFactory;
    }

    public String getUserAgentString() {
        return this.userAgentStringWithSDKVersion;
    }

    public void setUserAgentString(String newUserAgent) {
        if (newUserAgent != null && !newUserAgent.equals(this.userAgentStringWithoutSDKVersion)) {
            this.userAgentStringWithoutSDKVersion = newUserAgent;
            this.userAgentStringWithSDKVersion = newUserAgent + " " + Version.getUserAgentSDKVersion();
        }
    }

    public void populateUserAgentString(Context context) {
        this.threadRunner.execute(new 1(context), ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }
}
