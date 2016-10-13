package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;
import com.chartboost.sdk.impl.bk.b;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.mobileads.GooglePlayServicesInterstitial;

public class ae extends ad {
    private static ae d;
    private static String e;

    class 1 implements Runnable {
        final /* synthetic */ a a;
        final /* synthetic */ com.chartboost.sdk.Model.a b;
        final /* synthetic */ ae c;

        class 1 extends b {
            final /* synthetic */ 1 a;

            1(1 1) {
                this.a = 1;
            }

            public void a(bk bkVar) {
                this.a.c.a(this.a.b, CBImpressionError.USER_CANCELLATION);
            }

            public void a(bk bkVar, int i) {
                if (i == 1) {
                    super.h(this.a.b);
                } else {
                    this.a.c.a(this.a.b, CBImpressionError.USER_CANCELLATION);
                }
            }
        }

        1(ae aeVar, a aVar, com.chartboost.sdk.Model.a aVar2) {
            this.c = aeVar;
            this.a = aVar;
            this.b = aVar2;
        }

        public void run() {
            bk.a aVar = new bk.a();
            aVar.a(this.a.e(ShareConstants.WEB_DIALOG_PARAM_TITLE)).b(this.a.e(MimeTypes.BASE_TYPE_TEXT)).d(this.a.e("confirm")).c(this.a.e("cancel"));
            aVar.a(this.c.e(), new 1(this));
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ a a;
        final /* synthetic */ ae b;

        class 1 extends b {
            final /* synthetic */ 2 a;

            1(2 2) {
                this.a = 2;
            }

            public void a(bk bkVar, int i) {
                CBLogging.c(ae.e, "post-popup dismissed");
            }
        }

        2(ae aeVar, a aVar) {
            this.b = aeVar;
            this.a = aVar;
        }

        public void run() {
            bk.a aVar = new bk.a();
            aVar.a(this.a.e(ShareConstants.WEB_DIALOG_PARAM_TITLE)).b(this.a.e(MimeTypes.BASE_TYPE_TEXT)).c(this.a.e("confirm"));
            aVar.a(this.b.e(), new 1(this));
        }
    }

    class 3 implements a {
        final /* synthetic */ ae a;

        3(ae aeVar) {
            this.a = aeVar;
        }

        public void a(com.chartboost.sdk.Model.a aVar) {
            if (c.h() != null) {
                c.h().didClickRewardedVideo(aVar.e);
            }
        }

        public void b(com.chartboost.sdk.Model.a aVar) {
            if (c.h() != null) {
                c.h().didCloseRewardedVideo(aVar.e);
            }
        }

        public void c(com.chartboost.sdk.Model.a aVar) {
            this.a.r(aVar);
            if (c.h() != null) {
                c.h().didDismissRewardedVideo(aVar.e);
            }
        }

        public void d(com.chartboost.sdk.Model.a aVar) {
            if (c.h() != null) {
                c.h().didCacheRewardedVideo(aVar.e);
            }
        }

        public void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError) {
            if (c.h() != null) {
                c.h().didFailToLoadRewardedVideo(aVar.e, cBImpressionError);
            }
        }

        public void e(com.chartboost.sdk.Model.a aVar) {
            if (c.h() != null) {
                c.h().didDisplayRewardedVideo(aVar.e);
            }
        }

        public boolean f(com.chartboost.sdk.Model.a aVar) {
            if (c.h() != null) {
                return c.h().shouldDisplayRewardedVideo(aVar.e);
            }
            return true;
        }

        public boolean g(com.chartboost.sdk.Model.a aVar) {
            return true;
        }

        public boolean h(com.chartboost.sdk.Model.a aVar) {
            if (c.h() != null) {
                return c.v();
            }
            return true;
        }
    }

    static {
        e = "CBRewardedVideo";
    }

    private ae() {
    }

    public static ae k() {
        if (d == null) {
            d = new ae();
        }
        return d;
    }

    protected boolean b(com.chartboost.sdk.Model.a aVar, a aVar2) {
        return true;
    }

    protected com.chartboost.sdk.Model.a a(String str, boolean z) {
        return new com.chartboost.sdk.Model.a(com.chartboost.sdk.Model.a.a.REWARDED_VIDEO, z, str, false, g());
    }

    protected ay e(com.chartboost.sdk.Model.a aVar) {
        ay bcVar;
        if (c.H().booleanValue()) {
            aVar.a = com.chartboost.sdk.Model.a.b.WEB;
            a c = com.chartboost.sdk.b.c();
            bcVar = new bc(c.B());
            bcVar.a("cache_assets", c, bc.a.AD);
            bcVar.a(l.a.HIGH);
            bcVar.a(com.chartboost.sdk.Model.b.f);
            bcVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e, bc.a.AD);
            if (aVar.j) {
                bcVar.a("cache", Boolean.valueOf(true), bc.a.AD);
                bcVar.b(true);
            } else {
                bcVar.a("cache", Boolean.valueOf(false), bc.a.AD);
            }
        } else {
            aVar.a = com.chartboost.sdk.Model.a.b.NATIVE;
            Object j = j();
            bcVar = new ay(c.B());
            bcVar.a("local-videos", j);
            bcVar.a(l.a.HIGH);
            bcVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e);
            if (aVar.j) {
                bcVar.a("cache", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                bcVar.b(true);
            }
            bcVar.a(com.chartboost.sdk.Model.b.b);
        }
        return bcVar;
    }

    public ay l(com.chartboost.sdk.Model.a aVar) {
        ay l = super.l(aVar);
        l.a("/reward/show");
        return l;
    }

    protected void i(com.chartboost.sdk.Model.a aVar) {
    }

    protected void h(com.chartboost.sdk.Model.a aVar) {
        a a = aVar.A().a("ux").a("pre-popup");
        if (a.c() && a.a(ShareConstants.WEB_DIALOG_PARAM_TITLE).d() && a.a(MimeTypes.BASE_TYPE_TEXT).d() && a.a("confirm").d() && a.a("cancel").d() && e() != null) {
            a.post(new 1(this, a, aVar));
        } else {
            super.h(aVar);
        }
    }

    protected void r(com.chartboost.sdk.Model.a aVar) {
        a a = aVar.A().a("ux").a("post-popup");
        if (a.c() && a.a(ShareConstants.WEB_DIALOG_PARAM_TITLE).d() && a.a(MimeTypes.BASE_TYPE_TEXT).d() && a.a("confirm").d() && e() != null && aVar.p) {
            a.post(new 2(this, a));
        }
    }

    public a c() {
        return new 3(this);
    }

    public String f() {
        return String.format("%s-%s", new Object[]{"rewarded-video", h()});
    }
}
