package com.vungle.publisher.exception;

import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.gu;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ExceptionManager {
    @Inject
    public gu a;
    @Inject
    public SdkConfig b;
    @Inject
    public Factory c;

    @Inject
    ExceptionManager() {
    }
}
