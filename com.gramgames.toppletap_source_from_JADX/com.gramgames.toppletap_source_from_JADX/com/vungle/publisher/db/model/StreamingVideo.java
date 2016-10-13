package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingVideo extends Video<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    public com.vungle.publisher.db.model.StreamingAd.Factory a;
    @Inject
    public Factory b;
    public String c;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.Video.Factory<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        private static final b b;
        @Inject
        public Provider<StreamingVideo> a;

        protected final /* synthetic */ Viewable b(Ad ad, RequestAdResponse requestAdResponse) {
            return a((StreamingAd) ad, (RequestStreamingAdResponse) requestAdResponse);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new StreamingVideo[i];
        }

        protected final /* synthetic */ cb c_() {
            return (StreamingVideo) this.a.get();
        }

        static {
            b = b.streamingVideo;
        }

        @Inject
        Factory() {
        }

        protected final b b() {
            return b;
        }

        private StreamingVideo a(StreamingAd streamingAd, RequestStreamingAdResponse requestStreamingAdResponse) {
            StreamingVideo streamingVideo = (StreamingVideo) super.a((Ad) streamingAd, (RequestAdResponse) requestStreamingAdResponse);
            if (streamingVideo != null) {
                streamingVideo.c = requestStreamingAdResponse.m();
                streamingVideo.q = b;
            }
            return streamingVideo;
        }

        private StreamingVideo a(StreamingVideo streamingVideo, Cursor cursor, boolean z) {
            super.a((Video) streamingVideo, cursor, z);
            streamingVideo.c = bs.f(cursor, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
            return streamingVideo;
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

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory B() {
        return this.a;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.b;
    }

    @Inject
    StreamingVideo() {
    }

    public final Uri i() {
        return Uri.parse(this.c);
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.c);
        return a;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.c, false);
        return p;
    }
}
