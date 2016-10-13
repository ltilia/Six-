package com.amazon.device.ads;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ViewabilityOverlapCalculator {
    private static final String LOGTAG;
    private final AdController adController;
    private final MobileAdsLogger logger;
    private View rootView;
    private Rect visibleAdRectangle;

    class Range {
        int high;
        int low;

        public Range(int low, int high) {
            this.low = low;
            this.high = high;
        }

        public boolean isOverlap(Range range) {
            return this.low <= range.high && this.high >= range.low;
        }

        public void mergeRange(Range range) {
            this.low = this.low > range.low ? range.low : this.low;
            this.high = this.high < range.high ? range.high : this.high;
        }
    }

    class Rectangle implements Comparable<Rectangle> {
        private final Rect rect;

        public Rectangle(Rect rect) {
            this.rect = rect;
        }

        public Rectangle(int left, int top, int right, int bottom) {
            this.rect = new Rect();
            this.rect.left = left;
            this.rect.top = top;
            this.rect.right = right;
            this.rect.bottom = bottom;
        }

        public int getLeft() {
            return this.rect.left;
        }

        public int getTop() {
            return this.rect.top;
        }

        public int getRight() {
            return this.rect.right;
        }

        public int getBottom() {
            return this.rect.bottom;
        }

        public boolean intersect(Rectangle rectangle) {
            if (this.rect.width() == 0 || this.rect.height() == 0) {
                return false;
            }
            return this.rect.intersect(rectangle.rect);
        }

        public boolean contains(Rectangle rectangle) {
            return this.rect.contains(rectangle.rect);
        }

        public int getWidth() {
            return this.rect.width();
        }

        public int getHeight() {
            return this.rect.height();
        }

        public int compareTo(Rectangle other) {
            if (this.rect.top < other.rect.top) {
                return 1;
            }
            if (this.rect.top == other.rect.top) {
                return 0;
            }
            return -1;
        }
    }

    static {
        LOGTAG = ViewabilityOverlapCalculator.class.getSimpleName();
    }

    public ViewabilityOverlapCalculator(AdController adController) {
        this(adController, new MobileAdsLoggerFactory());
    }

    ViewabilityOverlapCalculator(AdController adController, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public float calculateViewablePercentage(View adView, Rect visibleAdRect) {
        int totalArea = adView.getWidth() * adView.getHeight();
        if (((float) totalArea) == 0.0f) {
            return 0.0f;
        }
        this.visibleAdRectangle = visibleAdRect;
        if (this.rootView == null) {
            this.rootView = this.adController.getRootView();
        }
        List<Rectangle> overlappingRectangles = new ArrayList();
        ViewGroup adContainer = (ViewGroup) adView.getParent();
        if (adContainer == null) {
            this.logger.d("AdContainer is null");
            return 0.0f;
        }
        findOverlapppingViews(new Rectangle(visibleAdRect), adContainer.indexOfChild(adView) + 1, adContainer, overlappingRectangles, true);
        this.logger.d("Visible area: %s , Total area: %s", Integer.valueOf((visibleAdRect.width() * visibleAdRect.height()) - getTotalAreaOfSetOfRectangles(overlappingRectangles)), Integer.valueOf(totalArea));
        return (((float) ((visibleAdRect.width() * visibleAdRect.height()) - getTotalAreaOfSetOfRectangles(overlappingRectangles))) / ((float) totalArea)) * 100.0f;
    }

    @TargetApi(11)
    private void findOverlapppingViews(Rectangle adRectangle, int index, ViewGroup viewParent, List<Rectangle> overlappingRectangles, boolean goUp) {
        if (viewParent != null && goUp && AndroidTargetUtils.isAdTransparent(viewParent)) {
            overlappingRectangles.add(new Rectangle(this.visibleAdRectangle));
            return;
        }
        for (int i = index; i < viewParent.getChildCount(); i++) {
            View childView = viewParent.getChildAt(i);
            boolean isChildListView = childView != null && (childView instanceof ListView);
            if (childView.isShown() && !(AndroidTargetUtils.isAtLeastAndroidAPI(11) && childView.getAlpha() == 0.0f)) {
                Rectangle childRectangle = getViewRectangle(childView);
                if (childRectangle.intersect(adRectangle)) {
                    if (isChildListView || !(childView instanceof ViewGroup)) {
                        this.logger.d("Overlap found with View: %s", childView);
                        overlappingRectangles.add(childRectangle);
                    } else {
                        findOverlapppingViews(adRectangle, 0, (ViewGroup) childView, overlappingRectangles, false);
                    }
                }
            }
        }
        if (goUp && !this.rootView.equals(viewParent)) {
            ViewGroup parent = (ViewGroup) viewParent.getParent();
            if (parent != null) {
                findOverlapppingViews(adRectangle, parent.indexOfChild(viewParent) + 1, parent, overlappingRectangles, true);
            }
        }
    }

    private Rectangle getViewRectangle(View adView) {
        int[] location = new int[2];
        adView.getLocationOnScreen(location);
        return new Rectangle(location[0], location[1], location[0] + adView.getWidth(), location[1] + adView.getHeight());
    }

    protected int getTotalAreaOfSetOfRectangles(List<Rectangle> rectangles) {
        int i;
        int[] x = new int[(rectangles.size() * 2)];
        for (i = 0; i < rectangles.size(); i++) {
            Rectangle rectangle = (Rectangle) rectangles.get(i);
            int index = i * 2;
            x[index] = rectangle.getLeft();
            x[index + 1] = rectangle.getRight();
        }
        Arrays.sort(x);
        Collections.sort(rectangles);
        int totalArea = 0;
        for (i = 0; i < x.length - 1; i++) {
            int lowX = x[i];
            int highX = x[i + 1];
            if (lowX != highX) {
                Range xRange = new Range(lowX, highX);
                totalArea += computeArea(xRange, getYRanges(xRange, rectangles));
            }
        }
        return totalArea;
    }

    private int computeArea(Range xRange, List<Range> yRanges) {
        int area = 0;
        int width = xRange.high - xRange.low;
        for (int i = 0; i < yRanges.size(); i++) {
            Range yRange = (Range) yRanges.get(i);
            area += (yRange.high - yRange.low) * width;
        }
        return area;
    }

    protected List<Range> getYRanges(Range xRange, List<Rectangle> rectangles) {
        List<Range> yRanges = new ArrayList();
        Range candidate = null;
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rect = (Rectangle) rectangles.get(i);
            if (xRange.low < rect.getRight() && xRange.high > rect.getLeft()) {
                Range yRange = new Range(rect.getTop(), rect.getBottom());
                if (candidate == null) {
                    candidate = yRange;
                    yRanges.add(yRange);
                } else if (yRange.isOverlap(candidate)) {
                    candidate.mergeRange(yRange);
                } else {
                    candidate = yRange;
                    yRanges.add(yRange);
                }
            }
        }
        return yRanges;
    }
}
