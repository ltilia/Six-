package com.mopub.nativeads;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

class VisibilityTracker {
    @VisibleForTesting
    static final int NUM_ACCESSES_BEFORE_TRIMMING = 50;
    private static final int VISIBILITY_THROTTLE_MILLIS = 100;
    private long mAccessCounter;
    private boolean mIsVisibilityScheduled;
    @Nullable
    @VisibleForTesting
    OnPreDrawListener mOnPreDrawListener;
    @NonNull
    @VisibleForTesting
    final WeakReference<View> mRootView;
    @NonNull
    private final Map<View, TrackingInfo> mTrackedViews;
    @NonNull
    private final ArrayList<View> mTrimmedViews;
    @NonNull
    private final VisibilityChecker mVisibilityChecker;
    @NonNull
    private final Handler mVisibilityHandler;
    @NonNull
    private final VisibilityRunnable mVisibilityRunnable;
    @Nullable
    private VisibilityTrackerListener mVisibilityTrackerListener;

    interface VisibilityTrackerListener {
        void onVisibilityChanged(List<View> list, List<View> list2);
    }

    class 1 implements OnPreDrawListener {
        1() {
        }

        public boolean onPreDraw() {
            VisibilityTracker.this.scheduleVisibilityCheck();
            return true;
        }
    }

    static class TrackingInfo {
        long mAccessOrder;
        int mMaxInvisiblePercent;
        int mMinViewablePercent;
        View mRootView;

        TrackingInfo() {
        }
    }

    static class VisibilityChecker {
        private final Rect mClipRect;

        VisibilityChecker() {
            this.mClipRect = new Rect();
        }

        boolean hasRequiredTimeElapsed(long startTimeMillis, int minTimeViewed) {
            return SystemClock.uptimeMillis() - startTimeMillis >= ((long) minTimeViewed);
        }

        boolean isVisible(@Nullable View rootView, @Nullable View view, int minPercentageViewed) {
            if (view == null || view.getVisibility() != 0 || rootView.getParent() == null || !view.getGlobalVisibleRect(this.mClipRect)) {
                return false;
            }
            long visibleViewArea = ((long) this.mClipRect.height()) * ((long) this.mClipRect.width());
            long totalViewArea = ((long) view.getHeight()) * ((long) view.getWidth());
            if (totalViewArea <= 0 || 100 * visibleViewArea < ((long) minPercentageViewed) * totalViewArea) {
                return false;
            }
            return true;
        }
    }

    class VisibilityRunnable implements Runnable {
        @NonNull
        private final ArrayList<View> mInvisibleViews;
        @NonNull
        private final ArrayList<View> mVisibleViews;

        VisibilityRunnable() {
            this.mInvisibleViews = new ArrayList();
            this.mVisibleViews = new ArrayList();
        }

        public void run() {
            VisibilityTracker.this.mIsVisibilityScheduled = false;
            for (Entry<View, TrackingInfo> entry : VisibilityTracker.this.mTrackedViews.entrySet()) {
                View view = (View) entry.getKey();
                int minPercentageViewed = ((TrackingInfo) entry.getValue()).mMinViewablePercent;
                int maxInvisiblePercent = ((TrackingInfo) entry.getValue()).mMaxInvisiblePercent;
                View rootView = ((TrackingInfo) entry.getValue()).mRootView;
                if (VisibilityTracker.this.mVisibilityChecker.isVisible(rootView, view, minPercentageViewed)) {
                    this.mVisibleViews.add(view);
                } else if (!VisibilityTracker.this.mVisibilityChecker.isVisible(rootView, view, maxInvisiblePercent)) {
                    this.mInvisibleViews.add(view);
                }
            }
            if (VisibilityTracker.this.mVisibilityTrackerListener != null) {
                VisibilityTracker.this.mVisibilityTrackerListener.onVisibilityChanged(this.mVisibleViews, this.mInvisibleViews);
            }
            this.mVisibleViews.clear();
            this.mInvisibleViews.clear();
        }
    }

    public VisibilityTracker(@NonNull Activity activity) {
        this(activity, new WeakHashMap(10), new VisibilityChecker(), new Handler());
    }

    @VisibleForTesting
    VisibilityTracker(@NonNull Activity activity, @NonNull Map<View, TrackingInfo> trackedViews, @NonNull VisibilityChecker visibilityChecker, @NonNull Handler visibilityHandler) {
        this.mAccessCounter = 0;
        this.mTrackedViews = trackedViews;
        this.mVisibilityChecker = visibilityChecker;
        this.mVisibilityHandler = visibilityHandler;
        this.mVisibilityRunnable = new VisibilityRunnable();
        this.mTrimmedViews = new ArrayList(NUM_ACCESSES_BEFORE_TRIMMING);
        View rootView = activity.getWindow().getDecorView();
        this.mRootView = new WeakReference(rootView);
        ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.mOnPreDrawListener = new 1();
            viewTreeObserver.addOnPreDrawListener(this.mOnPreDrawListener);
            return;
        }
        MoPubLog.w("Visibility Tracker was unable to track views because the root view tree observer was not alive");
    }

    void setVisibilityTrackerListener(@Nullable VisibilityTrackerListener visibilityTrackerListener) {
        this.mVisibilityTrackerListener = visibilityTrackerListener;
    }

    void addView(@NonNull View view, int minPercentageViewed) {
        addView(view, view, minPercentageViewed);
    }

    void addView(@NonNull View rootView, @NonNull View view, int minPercentageViewed) {
        addView(rootView, view, minPercentageViewed, minPercentageViewed);
    }

    void addView(@NonNull View rootView, @NonNull View view, int minVisiblePercentageViewed, int maxInvisiblePercentageViewed) {
        TrackingInfo trackingInfo = (TrackingInfo) this.mTrackedViews.get(view);
        if (trackingInfo == null) {
            trackingInfo = new TrackingInfo();
            this.mTrackedViews.put(view, trackingInfo);
            scheduleVisibilityCheck();
        }
        int maxInvisiblePercent = Math.min(maxInvisiblePercentageViewed, minVisiblePercentageViewed);
        trackingInfo.mRootView = rootView;
        trackingInfo.mMinViewablePercent = minVisiblePercentageViewed;
        trackingInfo.mMaxInvisiblePercent = maxInvisiblePercent;
        trackingInfo.mAccessOrder = this.mAccessCounter;
        this.mAccessCounter++;
        if (this.mAccessCounter % 50 == 0) {
            trimTrackedViews(this.mAccessCounter - 50);
        }
    }

    private void trimTrackedViews(long minAccessOrder) {
        for (Entry<View, TrackingInfo> entry : this.mTrackedViews.entrySet()) {
            if (((TrackingInfo) entry.getValue()).mAccessOrder < minAccessOrder) {
                this.mTrimmedViews.add(entry.getKey());
            }
        }
        Iterator it = this.mTrimmedViews.iterator();
        while (it.hasNext()) {
            removeView((View) it.next());
        }
        this.mTrimmedViews.clear();
    }

    void removeView(@NonNull View view) {
        this.mTrackedViews.remove(view);
    }

    void clear() {
        this.mTrackedViews.clear();
        this.mVisibilityHandler.removeMessages(0);
        this.mIsVisibilityScheduled = false;
    }

    void destroy() {
        clear();
        View rootView = (View) this.mRootView.get();
        if (!(rootView == null || this.mOnPreDrawListener == null)) {
            ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.mOnPreDrawListener);
            }
            this.mOnPreDrawListener = null;
        }
        this.mVisibilityTrackerListener = null;
    }

    void scheduleVisibilityCheck() {
        if (!this.mIsVisibilityScheduled) {
            this.mIsVisibilityScheduled = true;
            this.mVisibilityHandler.postDelayed(this.mVisibilityRunnable, 100);
        }
    }
}
