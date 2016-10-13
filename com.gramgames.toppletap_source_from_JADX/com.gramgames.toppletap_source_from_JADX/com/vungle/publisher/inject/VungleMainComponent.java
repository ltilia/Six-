package com.vungle.publisher.inject;

import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.VunglePubBase;
import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.fi;
import com.vungle.publisher.fk;
import com.vungle.publisher.fp;
import com.vungle.publisher.image.AssetBitmapFactory;
import com.vungle.publisher.location.AndroidLocation;
import com.vungle.publisher.net.AndroidNetwork;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {fi.class, EndpointModule.class, fk.class})
/* compiled from: vungle */
public interface VungleMainComponent {
    void a(FullScreenAdActivity fullScreenAdActivity);

    void a(VunglePubBase vunglePubBase);

    void a(VideoFragment videoFragment);

    void a(AndroidDevice androidDevice);

    void a(fp fpVar);

    void a(AssetBitmapFactory assetBitmapFactory);

    void a(AndroidLocation androidLocation);

    void a(AndroidNetwork androidNetwork);
}
