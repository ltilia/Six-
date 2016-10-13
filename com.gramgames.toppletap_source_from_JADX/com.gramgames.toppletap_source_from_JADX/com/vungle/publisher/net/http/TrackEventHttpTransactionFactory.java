package com.vungle.publisher.net.http;

import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

@Singleton
/* compiled from: vungle */
public class TrackEventHttpTransactionFactory extends Factory {
    @Inject
    public TrackEventHttpRequest.Factory a;
    @Inject
    public Lazy<TrackEventHttpResponseHandler> b;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.postroll_click.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.video_click.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Inject
    TrackEventHttpTransactionFactory() {
    }

    public final HttpTransaction a(Ad<?, ?, ?> ad, a aVar, String str) {
        TrackEventHttpResponseHandler trackEventHttpResponseHandler = (TrackEventHttpResponseHandler) this.b.get();
        switch (1.a[aVar.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                trackEventHttpResponseHandler.a = true;
                break;
            default:
                trackEventHttpResponseHandler.i = 1;
                trackEventHttpResponseHandler.h = 10;
                break;
        }
        TrackEventHttpRequest trackEventHttpRequest = (TrackEventHttpRequest) this.a.c();
        trackEventHttpRequest.e = ad;
        trackEventHttpRequest.f = aVar;
        trackEventHttpRequest.b = str;
        return super.a(trackEventHttpRequest, trackEventHttpResponseHandler);
    }
}
