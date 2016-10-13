package com.vungle.publisher.file;

import com.vungle.log.Logger;
import com.vungle.publisher.fc;
import com.vungle.publisher.inject.annotations.AdTempDirectory;
import com.vungle.publisher.inject.annotations.OldAdTempDirectory;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class CacheManager {
    @Inject
    @AdTempDirectory
    public Provider<String> a;
    @Inject
    @OldAdTempDirectory
    public Provider<String> b;

    @Inject
    CacheManager() {
    }

    public static void a(String str) {
        try {
            if (new File(str).exists()) {
                fc.a(str);
            } else {
                Logger.v(Logger.FILE_TAG, "ad temp directory does not exist " + str);
            }
        } catch (Exception e) {
            Logger.d(Logger.FILE_TAG, "error deleting ad temp directory " + str);
        }
    }
}
