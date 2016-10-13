package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.image.AssetBitmapFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fg implements MembersInjector<AssetBitmapFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;

    static {
        a = !fg.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AssetBitmapFactory assetBitmapFactory = (AssetBitmapFactory) obj;
        if (assetBitmapFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        assetBitmapFactory.a = (Context) this.b.get();
    }

    private fg(Provider<Context> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AssetBitmapFactory> a(Provider<Context> provider) {
        return new fg(provider);
    }
}
