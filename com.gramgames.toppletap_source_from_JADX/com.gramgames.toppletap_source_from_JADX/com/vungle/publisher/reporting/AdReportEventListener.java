package com.vungle.publisher.reporting;

import android.content.ContentValues;
import com.google.android.gms.games.Games;
import com.vungle.log.Logger;
import com.vungle.publisher.ad;
import com.vungle.publisher.ad.event.VolumeChangeEvent;
import com.vungle.publisher.ae;
import com.vungle.publisher.ai;
import com.vungle.publisher.al;
import com.vungle.publisher.am;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.at;
import com.vungle.publisher.au;
import com.vungle.publisher.bg;
import com.vungle.publisher.bh;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.AdPlay;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.AdReportEvent;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.et;
import com.vungle.publisher.gh;
import com.vungle.publisher.i;
import com.vungle.publisher.l;
import com.vungle.publisher.m;
import com.vungle.publisher.t;
import com.vungle.publisher.u;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdReportEventListener extends et {
    private static final a[] f;
    @Inject
    public AdServiceReportingHandler a;
    @Inject
    public com.vungle.publisher.db.model.AdReport.Factory b;
    @Inject
    public AdReportManager c;
    @Inject
    public com.vungle.publisher.db.model.LoggedException.Factory d;
    @Inject
    public gh e;
    private Ad<?, ?, ?> g;
    private AdPlay<?, ?, ?, ?, ?, ?> i;
    private AdReport<?, ?, ?, ?, ?, ?> j;
    private int k;
    private final HashSet<a> l;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public AdReportEventListener a;

        @Inject
        Factory() {
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    static {
        f = new a[]{a.play_percentage_0, a.play_percentage_25, a.play_percentage_50, a.play_percentage_75, a.play_percentage_80, a.play_percentage_100};
    }

    @Inject
    AdReportEventListener() {
        this.l = new HashSet();
    }

    public final void a(Ad<?, ?, ?> ad) {
        if (this.g == null || !this.g.a((Ad) ad)) {
            AdReport a;
            Logger.i(Logger.REPORT_TAG, "new ad " + ad.x());
            this.g = ad;
            AdReportManager adReportManager = this.c;
            if (ad instanceof LocalAd) {
                a = adReportManager.c.a((LocalAd) ad);
            } else if (ad instanceof StreamingAd) {
                a = adReportManager.f.a((StreamingAd) ad);
            } else {
                throw new IllegalArgumentException("unknown ad type " + ad);
            }
            this.j = a;
            this.i = this.j.q();
            a();
            return;
        }
        Logger.v(Logger.REPORT_TAG, "same ad " + ad.x());
    }

    private void a() {
        this.k = 0;
        this.l.clear();
    }

    public void onEvent(VolumeChangeEvent event) {
        float f = event.d;
        if ((event.b > event.a ? 1 : null) != null) {
            a(AdReportEvent.a.volumeup, Float.valueOf(f));
        } else {
            a(AdReportEvent.a.volumedown, Float.valueOf(f));
        }
    }

    public void onEvent(au event) {
        a(AdReportEvent.a.volume, Float.valueOf(event.a));
    }

    public void onEvent(at event) {
        if (event.a) {
            a(AdReportEvent.a.muted, null);
            a(a.mute);
            return;
        }
        a(AdReportEvent.a.unmuted, null);
        a(a.unmute);
    }

    public void onEvent(ae aeVar) {
        a(a.postroll_view);
    }

    public void onEvent(aq aqVar) {
        a(a.video_close);
        a(AdReportEvent.a.close, null);
    }

    public void onEvent(as asVar) {
        a(AdReportEvent.a.videoreset, null);
    }

    public void onEvent(i iVar) {
        a(AdReportEvent.a.back, null);
    }

    public void onEvent(m event) {
        Ad a = event.a();
        this.e.a(a, event.a, null, a.g());
    }

    public void onEvent(al startPlayAdEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad start");
            com.vungle.publisher.db.model.AdReport.Factory factory = this.b;
            ContentValues contentValues = new ContentValues();
            contentValues.put(Games.EXTRA_STATUS, AdReport.a.reportable.toString());
            factory.a.getWritableDatabase().update("ad_report", contentValues, "status = ?", new String[]{AdReport.a.playing.toString()});
            com.vungle.publisher.a aVar = startPlayAdEvent.a;
            AdReport adReport = this.j;
            adReport.a(AdReport.a.playing);
            adReport.a(aVar.getExtras());
            boolean isIncentivized = aVar.isIncentivized();
            adReport.b(isIncentivized);
            if (isIncentivized) {
                adReport.b(aVar.getIncentivizedUserId());
            }
            adReport.c(aVar.getPlacement());
            adReport.c(Long.valueOf(startPlayAdEvent.e));
            adReport.w();
        } catch (Throwable e) {
            this.d.a(Logger.REPORT_TAG, "error processing ad start event", e);
        }
    }

    public void onEvent(bh endPlayAdEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad end");
            a(endPlayAdEvent.a());
            a(endPlayAdEvent.b());
        } catch (Throwable e) {
            this.d.a(Logger.REPORT_TAG, "error processing ad end", e);
        }
    }

    public void onEvent(bg destroyedErrorEndPlayEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received destroyed ad end");
            a(destroyedErrorEndPlayEvent.e);
        } catch (Exception e) {
            Logger.w(Logger.REPORT_TAG, "error processing destroyed ad end");
        }
    }

    public void onEvent(t playVideoDurationEvent) {
        try {
            this.j.a(Integer.valueOf(playVideoDurationEvent.a));
        } catch (Throwable e) {
            this.d.a(Logger.REPORT_TAG, "error updating video duration millis", e);
        }
    }

    public void onEvent(am playVideoStartEvent) {
        try {
            this.i.c = Long.valueOf(playVideoStartEvent.e);
            this.i.m();
        } catch (Throwable e) {
            this.d.a(Logger.REPORT_TAG, "error updating play start millis", e);
        }
    }

    public void onEvent(ai playVideoProgressEvent) {
        Object obj = null;
        try {
            int i = playVideoProgressEvent.a;
            Object obj2 = this.k < f.length ? 1 : null;
            boolean z = playVideoProgressEvent instanceof u;
            if (obj2 != null || z) {
                Integer j = this.j.j();
                if (j == null) {
                    Logger.d(Logger.REPORT_TAG, "null video duration millis for " + this.j.x());
                } else if (j.intValue() == 0) {
                    Logger.w(Logger.REPORT_TAG, "video duration millis 0 for " + this.j.x());
                } else {
                    if (obj2 != null) {
                        float intValue = ((float) i) / ((float) j.intValue());
                        a aVar = f[this.k];
                        if (intValue >= aVar.p) {
                            obj = 1;
                        }
                        if (obj != null) {
                            this.k++;
                            a(aVar);
                        }
                    }
                    if (obj != null || z) {
                        try {
                            AdPlay adPlay = this.i;
                            Integer valueOf = Integer.valueOf(playVideoProgressEvent.a);
                            if (adPlay.b == null || (valueOf != null && valueOf.intValue() > adPlay.b.intValue())) {
                                Logger.d(Logger.AD_TAG, "setting watched millis " + valueOf);
                                adPlay.b = valueOf;
                            } else {
                                Logger.w(Logger.AD_TAG, "ignoring decreased watched millis " + valueOf);
                            }
                            this.i.m();
                            this.j.b(Long.valueOf(playVideoProgressEvent.e));
                        } catch (Throwable e) {
                            this.d.a(Logger.REPORT_TAG, "error updating video view progress", e);
                        }
                    }
                }
            }
        } catch (Throwable e2) {
            this.d.a(Logger.REPORT_TAG, "error handling video view progress", e2);
        }
    }

    public void onEvent(l event) {
        a aVar = event.a;
        if (aVar == a.video_click) {
            a(AdReportEvent.a.cta, null);
        } else if (aVar == a.postroll_click) {
            a(a.postroll_click);
        }
        a(AdReportEvent.a.download, null);
    }

    public void onEvent(ad adVar) {
        try {
            a(AdReportEvent.a.replay, null);
            this.i = this.j.q();
        } catch (Throwable e) {
            this.d.a(Logger.REPORT_TAG, "error reporting replay", e);
        }
    }

    private void a(AdReportEvent.a aVar, Object obj) {
        try {
            this.i.a(aVar, obj);
        } catch (Throwable e) {
            this.d.a(Logger.REPORT_TAG, "error reporting event", e);
        }
    }

    private void a(a aVar) {
        if (this.g == null) {
            Logger.w(Logger.REPORT_TAG, "null ad in AdReportingHandler - cannot track event " + aVar);
        } else if (!this.l.contains(aVar)) {
            Logger.v(Logger.REPORT_TAG, "tpat event " + aVar.name());
            gh ghVar = this.e;
            Ad ad = this.g;
            String[] a = this.g.a(aVar);
            Map hashMap = new HashMap();
            hashMap.put("%timestamp%", String.valueOf(System.currentTimeMillis()));
            ghVar.a(ad, aVar, hashMap, a);
            this.l.add(aVar);
        }
    }

    private void a(long j) {
        unregister();
        AdReport adReport = this.j;
        if (adReport == null) {
            Logger.d(Logger.REPORT_TAG, "no current ad report");
        } else {
            adReport.a(AdReport.a.reportable);
            adReport.a(Long.valueOf(j));
            adReport.w();
        }
        this.c.a();
        this.g = null;
        this.j = null;
        this.i = null;
        a();
    }
}
