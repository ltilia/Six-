package com.facebook.ads.internal.view.video.support;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import org.json.simple.parser.Yytoken;

public class a extends FrameLayout {
    private e a;
    private f b;
    private View c;
    private Uri d;
    private Context e;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[f.values().length];
            try {
                a[f.TEXTURE_VIEW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[f.VIDEO_VIEW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public a(Context context) {
        super(context);
        this.e = context;
        this.c = b(context);
        this.a = a(context);
        addView(this.c);
    }

    private e a(Context context) {
        if (VERSION.SDK_INT >= 14) {
            this.b = f.TEXTURE_VIEW;
            Object dVar = new d(context);
            dVar.a(this.c, this.d);
            addView(dVar);
            return dVar;
        }
        this.b = f.VIDEO_VIEW;
        dVar = new g(context);
        addView(dVar);
        return dVar;
    }

    private View b(Context context) {
        View view = new View(context);
        view.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
        view.setLayoutParams(new LayoutParams(-1, -1));
        return view;
    }

    public void a() {
        this.a.start();
    }

    public void b() {
        this.a.pause();
    }

    public int getCurrentPosition() {
        return this.a.getCurrentPosition();
    }

    public View getPlaceholderView() {
        return this.c;
    }

    public f getVideoImplType() {
        return this.b;
    }

    public void setFrameVideoViewListener(b bVar) {
        this.a.setFrameVideoViewListener(bVar);
    }

    public void setVideoImpl(f fVar) {
        removeAllViews();
        if (fVar == f.TEXTURE_VIEW && VERSION.SDK_INT < 14) {
            fVar = f.VIDEO_VIEW;
        }
        this.b = fVar;
        Object dVar;
        switch (1.a[fVar.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                dVar = new d(this.e);
                dVar.a(this.c, this.d);
                addView(dVar);
                this.a = dVar;
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                dVar = new g(this.e);
                dVar.a(this.c, this.d);
                addView(dVar);
                this.a = dVar;
                break;
        }
        addView(this.c);
        a();
    }

    public void setup(Uri uri) {
        this.d = uri;
        this.a.a(this.c, uri);
    }
}
