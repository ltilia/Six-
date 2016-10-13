package com.vungle.publisher.util;

import android.content.Intent;
import android.net.Uri;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class IntentFactory {
    @Inject
    IntentFactory() {
    }

    public static Intent a(String str, Uri uri) {
        return new Intent(str, uri);
    }
}
