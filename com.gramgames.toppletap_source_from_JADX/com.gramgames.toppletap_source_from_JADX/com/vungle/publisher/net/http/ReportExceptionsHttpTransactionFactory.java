package com.vungle.publisher.net.http;

import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class ReportExceptionsHttpTransactionFactory extends Factory {
    @Inject
    public ReportExceptionsHttpRequest.Factory a;
    @Inject
    public ReportExceptionsHttpResponseHandler.Factory b;

    public final HttpTransaction a(List<LoggedException> list) throws JSONException {
        HttpRequest a = this.a.a(list);
        ReportExceptionsHttpResponseHandler reportExceptionsHttpResponseHandler = (ReportExceptionsHttpResponseHandler) this.b.a.get();
        reportExceptionsHttpResponseHandler.a = list;
        return super.a(a, reportExceptionsHttpResponseHandler);
    }
}
