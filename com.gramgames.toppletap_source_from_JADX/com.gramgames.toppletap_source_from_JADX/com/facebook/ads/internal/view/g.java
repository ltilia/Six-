package com.facebook.ads.internal.view;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.util.o;
import com.facebook.ads.internal.util.u;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.games.GamesStatusCodes;
import java.util.HashMap;
import java.util.Map;

public class g extends LinearLayout {
    private VideoView a;
    private String b;
    private String c;
    private boolean d;
    private int e;
    private Handler f;
    private Handler g;

    private static final class a extends u<g> {
        public a(g gVar) {
            super(gVar);
        }

        public void run() {
            g gVar = (g) a();
            if (gVar == null) {
                return;
            }
            if (gVar.a.getCurrentPosition() > GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCK_FAILURE) {
                new o().execute(new String[]{gVar.getVideoPlayReportURI()});
                return;
            }
            gVar.f.postDelayed(this, 250);
        }
    }

    private static final class b extends u<g> {
        public b(g gVar) {
            super(gVar);
        }

        public void run() {
            g gVar = (g) a();
            if (gVar != null) {
                int currentPosition = gVar.a.getCurrentPosition();
                if (currentPosition > gVar.e) {
                    gVar.e = currentPosition;
                }
                gVar.g.postDelayed(this, 250);
            }
        }
    }

    public g(Context context) {
        super(context);
        c();
    }

    private void c() {
        MediaController mediaController = new MediaController(getContext());
        this.a = new VideoView(getContext());
        mediaController.setAnchorView(this);
        this.a.setMediaController(mediaController);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(10, -1);
        layoutParams.addRule(12, -1);
        layoutParams.addRule(13);
        this.a.setLayoutParams(layoutParams);
        addView(this.a);
        this.g = new Handler();
        this.g.postDelayed(new b(this), 250);
        this.f = new Handler();
        this.f.postDelayed(new a(this), 250);
    }

    private void d() {
        if (!this.d && getVideoTimeReportURI() != null) {
            Map hashMap = new HashMap();
            hashMap.put("time", Integer.toString(this.e / AdError.NETWORK_ERROR_CODE));
            hashMap.put("inline", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            new o(hashMap).execute(new String[]{getVideoTimeReportURI()});
            this.d = true;
            this.e = 0;
        }
    }

    public void a() {
        this.a.start();
    }

    public void b() {
        if (this.a != null) {
            this.a.stopPlayback();
        }
    }

    public String getVideoPlayReportURI() {
        return this.b;
    }

    public String getVideoTimeReportURI() {
        return this.c;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    public void setVideoPlayReportURI(String str) {
        this.b = str;
    }

    public void setVideoTimeReportURI(String str) {
        this.c = str;
    }

    public void setVideoURI(Uri uri) {
        if (uri != null) {
            this.a.setVideoURI(uri);
        }
    }

    public void setVideoURI(String str) {
        if (str != null) {
            setVideoURI(Uri.parse(str));
        }
    }
}
