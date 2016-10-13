package com.vungle.publisher;

import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.protocol.message.RequestAdResponse;

/* compiled from: vungle */
public interface cg<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends cy<Integer> {

    public enum a {
        aware,
        queued,
        downloading,
        downloaded,
        ready,
        failed
    }

    public enum b {
        localVideo,
        postRoll,
        preRoll,
        streamingVideo
    }

    void a(a aVar);

    void b(a aVar);

    A c();

    String d();

    a e();

    b f();
}
