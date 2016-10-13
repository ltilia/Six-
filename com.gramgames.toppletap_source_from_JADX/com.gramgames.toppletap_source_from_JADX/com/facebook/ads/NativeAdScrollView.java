package com.facebook.ads;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.facebook.ads.NativeAdView.Type;
import java.util.ArrayList;
import java.util.List;

public class NativeAdScrollView extends LinearLayout {
    public static final int DEFAULT_INSET = 20;
    public static final int DEFAULT_MAX_ADS = 10;
    private final Context a;
    private final NativeAdsManager b;
    private final AdViewProvider c;
    private final Type d;
    private final int e;
    private final b f;
    private final NativeAdViewAttributes g;

    public interface AdViewProvider {
        View createView(NativeAd nativeAd, int i);

        void destroyView(NativeAd nativeAd, View view);
    }

    private class a extends PagerAdapter {
        final /* synthetic */ NativeAdScrollView a;
        private List<NativeAd> b;

        public a(NativeAdScrollView nativeAdScrollView) {
            this.a = nativeAdScrollView;
            this.b = new ArrayList();
        }

        public void a() {
            this.b.clear();
            int min = Math.min(this.a.e, this.a.b.getUniqueNativeAdCount());
            for (int i = 0; i < min; i++) {
                NativeAd nextNativeAd = this.a.b.nextNativeAd();
                nextNativeAd.a(true);
                this.b.add(nextNativeAd);
            }
            notifyDataSetChanged();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (i < this.b.size()) {
                if (this.a.d != null) {
                    ((NativeAd) this.b.get(i)).unregisterView();
                } else {
                    this.a.c.destroyView((NativeAd) this.b.get(i), (View) obj);
                }
            }
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return this.b.size();
        }

        public int getItemPosition(Object obj) {
            int indexOf = this.b.indexOf(obj);
            return indexOf >= 0 ? indexOf : -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View render = this.a.d != null ? NativeAdView.render(this.a.a, (NativeAd) this.b.get(i), this.a.d, this.a.g) : this.a.c.createView((NativeAd) this.b.get(i), i);
            viewGroup.addView(render);
            return render;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    private class b extends ViewPager {
        final /* synthetic */ NativeAdScrollView a;

        public b(NativeAdScrollView nativeAdScrollView, Context context) {
            this.a = nativeAdScrollView;
            super(context);
        }

        protected void onMeasure(int i, int i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
            }
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(i3, 1073741824));
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider) {
        this(context, nativeAdsManager, adViewProvider, null, null, DEFAULT_MAX_ADS);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, int i) {
        this(context, nativeAdsManager, adViewProvider, null, null, i);
    }

    private NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        super(context);
        if (!nativeAdsManager.isLoaded()) {
            throw new IllegalStateException("NativeAdsManager not loaded");
        } else if (type == null && adViewProvider == null) {
            throw new IllegalArgumentException("Must provide one of AdLayoutProperties or a CustomAdView");
        } else {
            this.a = context;
            this.b = nativeAdsManager;
            this.g = nativeAdViewAttributes;
            this.c = adViewProvider;
            this.d = type;
            this.e = i;
            PagerAdapter aVar = new a(this);
            this.f = new b(this, context);
            this.f.setAdapter(aVar);
            setInset(DEFAULT_INSET);
            aVar.a();
            addView(this.f);
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type) {
        this(context, nativeAdsManager, null, type, new NativeAdViewAttributes(), DEFAULT_MAX_ADS);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, DEFAULT_MAX_ADS);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, i);
    }

    public void setInset(int i) {
        if (i > 0) {
            DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
            int round = Math.round(((float) i) * displayMetrics.density);
            this.f.setPadding(round, 0, round, 0);
            this.f.setPageMargin(Math.round(displayMetrics.density * ((float) (i / 2))));
            this.f.setClipToPadding(false);
        }
    }
}
