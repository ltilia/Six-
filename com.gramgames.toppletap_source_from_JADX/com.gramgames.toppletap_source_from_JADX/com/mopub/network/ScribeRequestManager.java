package com.mopub.network;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.ScribeRequest.Listener;
import com.mopub.network.ScribeRequest.ScribeRequestFactory;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;

public class ScribeRequestManager extends RequestManager<ScribeRequestFactory> implements Listener {

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            ScribeRequestManager.this.clearRequest();
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ VolleyError val$volleyError;

        2(VolleyError volleyError) {
            this.val$volleyError = volleyError;
        }

        public void run() {
            try {
                ScribeRequestManager.this.mBackoffPolicy.backoff(this.val$volleyError);
                ScribeRequestManager.this.makeRequestInternal();
            } catch (VolleyError e) {
                MoPubLog.d("Failed to Scribe events: " + this.val$volleyError);
                ScribeRequestManager.this.clearRequest();
            }
        }
    }

    public ScribeRequestManager(Looper looper) {
        super(looper);
    }

    @NonNull
    Request<?> createRequest() {
        return ((ScribeRequestFactory) this.mRequestFactory).createRequest(this);
    }

    public void onResponse() {
        MoPubLog.d("Successfully scribed events");
        this.mHandler.post(new 1());
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.mHandler.post(new 2(volleyError));
    }
}
