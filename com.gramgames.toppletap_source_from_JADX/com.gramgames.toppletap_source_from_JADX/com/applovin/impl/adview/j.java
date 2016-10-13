package com.applovin.impl.adview;

import gs.gram.mopub.BuildConfig;

class j implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private j(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        try {
            this.a.i.loadDataWithBaseURL("/", "<html></html>", WebRequest.CONTENT_TYPE_HTML, null, BuildConfig.FLAVOR);
        } catch (Exception e) {
        }
    }
}
