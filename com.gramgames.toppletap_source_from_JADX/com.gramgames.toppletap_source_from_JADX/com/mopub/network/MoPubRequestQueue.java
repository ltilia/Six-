package com.mopub.network;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.volley.Cache;
import com.mopub.volley.Network;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.RequestQueue.RequestFilter;
import com.mopub.volley.ResponseDelivery;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MoPubRequestQueue extends RequestQueue {
    private static final int CAPACITY = 10;
    @NonNull
    private final Map<Request<?>, DelayedRequestHelper> mDelayedRequests;

    class 1 implements RequestFilter {
        final /* synthetic */ Object val$tag;

        1(Object obj) {
            this.val$tag = obj;
        }

        public boolean apply(Request<?> request) {
            return request.getTag() == this.val$tag;
        }
    }

    class 2 implements RequestFilter {
        final /* synthetic */ Request val$request;

        2(Request request) {
            this.val$request = request;
        }

        public boolean apply(Request<?> _request) {
            return this.val$request == _request;
        }
    }

    class DelayedRequestHelper {
        final int mDelayMs;
        @NonNull
        final Runnable mDelayedRunnable;
        @NonNull
        final Handler mHandler;

        class 1 implements Runnable {
            final /* synthetic */ Request val$request;
            final /* synthetic */ MoPubRequestQueue val$this$0;

            1(MoPubRequestQueue moPubRequestQueue, Request request) {
                this.val$this$0 = moPubRequestQueue;
                this.val$request = request;
            }

            public void run() {
                MoPubRequestQueue.this.mDelayedRequests.remove(this.val$request);
                MoPubRequestQueue.this.add(this.val$request);
            }
        }

        DelayedRequestHelper(@NonNull MoPubRequestQueue this$0, Request<?> request, int delayMs) {
            this(request, delayMs, new Handler());
        }

        @VisibleForTesting
        DelayedRequestHelper(@NonNull Request<?> request, int delayMs, @NonNull Handler handler) {
            this.mDelayMs = delayMs;
            this.mHandler = handler;
            this.mDelayedRunnable = new 1(MoPubRequestQueue.this, request);
        }

        void start() {
            this.mHandler.postDelayed(this.mDelayedRunnable, (long) this.mDelayMs);
        }

        void cancel() {
            this.mHandler.removeCallbacks(this.mDelayedRunnable);
        }
    }

    MoPubRequestQueue(Cache cache, Network network, int threadPoolSize, ResponseDelivery delivery) {
        super(cache, network, threadPoolSize, delivery);
        this.mDelayedRequests = new HashMap(CAPACITY);
    }

    MoPubRequestQueue(Cache cache, Network network, int threadPoolSize) {
        super(cache, network, threadPoolSize);
        this.mDelayedRequests = new HashMap(CAPACITY);
    }

    MoPubRequestQueue(Cache cache, Network network) {
        super(cache, network);
        this.mDelayedRequests = new HashMap(CAPACITY);
    }

    public void addDelayedRequest(@NonNull Request<?> request, int delayMs) {
        Preconditions.checkNotNull(request);
        addDelayedRequest((Request) request, new DelayedRequestHelper(this, request, delayMs));
    }

    @VisibleForTesting
    void addDelayedRequest(@NonNull Request<?> request, @NonNull DelayedRequestHelper delayedRequestHelper) {
        Preconditions.checkNotNull(delayedRequestHelper);
        if (this.mDelayedRequests.containsKey(request)) {
            cancel(request);
        }
        delayedRequestHelper.start();
        this.mDelayedRequests.put(request, delayedRequestHelper);
    }

    public void cancelAll(@NonNull RequestFilter filter) {
        Preconditions.checkNotNull(filter);
        super.cancelAll(filter);
        Iterator<Entry<Request<?>, DelayedRequestHelper>> iterator = this.mDelayedRequests.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Request<?>, DelayedRequestHelper> entry = (Entry) iterator.next();
            if (filter.apply((Request) entry.getKey())) {
                ((Request) entry.getKey()).cancel();
                ((DelayedRequestHelper) entry.getValue()).cancel();
                iterator.remove();
            }
        }
    }

    public void cancelAll(@NonNull Object tag) {
        Preconditions.checkNotNull(tag);
        super.cancelAll(tag);
        cancelAll(new 1(tag));
    }

    public void cancel(@NonNull Request<?> request) {
        Preconditions.checkNotNull(request);
        cancelAll(new 2(request));
    }

    @NonNull
    @Deprecated
    @VisibleForTesting
    Map<Request<?>, DelayedRequestHelper> getDelayedRequests() {
        return this.mDelayedRequests;
    }
}
