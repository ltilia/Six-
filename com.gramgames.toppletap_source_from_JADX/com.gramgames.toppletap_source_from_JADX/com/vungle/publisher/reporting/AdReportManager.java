package com.vungle.publisher.reporting;

import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.AdReport.Factory;
import com.vungle.publisher.db.model.AdReport.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.1;
import com.vungle.publisher.v;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdReportManager {
    @Inject
    public EventBus a;
    @Inject
    public Factory b;
    @Inject
    public LocalAdReport.Factory c;
    @Inject
    public ProtocolHttpGateway d;
    @Inject
    public SdkState e;
    @Inject
    public StreamingAdReport.Factory f;
    @Inject
    public LoggedException.Factory g;

    @Inject
    AdReportManager() {
    }

    public final LocalAdReport a(LocalAd localAd) {
        return (LocalAdReport) this.c.a(localAd);
    }

    public final void a() {
        String x;
        try {
            List<AdReport> a = this.b.a();
            Logger.i(Logger.REPORT_TAG, "sending " + a.size() + " ad reports");
            for (AdReport adReport : a) {
                x = adReport.x();
                Logger.d(Logger.REPORT_TAG, "sending " + x);
                ProtocolHttpGateway protocolHttpGateway = this.d;
                protocolHttpGateway.e.a(new 1(protocolHttpGateway, adReport), b.reportAd);
            }
            this.a.a(new v());
        } catch (Throwable e) {
            this.g.a(Logger.REPORT_TAG, "error sending " + x, e);
            adReport.a(a.failed);
            adReport.m();
        } catch (Throwable th) {
            this.a.a(new v());
        }
    }
}
