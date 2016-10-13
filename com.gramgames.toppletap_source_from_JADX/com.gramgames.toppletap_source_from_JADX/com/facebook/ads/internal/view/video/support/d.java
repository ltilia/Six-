package com.facebook.ads.internal.view.video.support;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;

@TargetApi(14)
public class d extends TextureView implements OnBufferingUpdateListener, OnPreparedListener, SurfaceTextureListener, e {
    private static final String i;
    private View a;
    private Uri b;
    private b c;
    private Surface d;
    private MediaPlayer e;
    private boolean f;
    private boolean g;
    private int h;

    static {
        i = d.class.getSimpleName();
    }

    public d(Context context) {
        super(context);
    }

    private void a() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(getContext(), this.b);
            mediaPlayer.setSurface(this.d);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnInfoListener(new c(this.a));
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepareAsync();
            this.e = mediaPlayer;
        } catch (Exception e) {
            mediaPlayer.release();
            Log.e(i, "Cannot prepare media player with SurfaceTexture: " + e);
        }
    }

    public void a(View view, Uri uri) {
        this.a = view;
        this.b = uri;
        setSurfaceTextureListener(this);
    }

    public int getCurrentPosition() {
        return this.e != null ? this.e.getCurrentPosition() : 0;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.c != null) {
            this.c.a(mediaPlayer);
        }
        if (this.g) {
            mediaPlayer.start();
            this.g = false;
        }
        if (this.h > 0) {
            if (this.h >= this.e.getDuration()) {
                this.h = 0;
            }
            this.e.seekTo(this.h);
            this.h = 0;
        }
        this.f = true;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.d = new Surface(surfaceTexture);
        a();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void pause() {
        if (this.e != null) {
            this.h = this.e.getCurrentPosition();
            this.e.stop();
            this.e.reset();
            this.e.release();
        }
        this.e = null;
        this.f = false;
        this.g = false;
    }

    public void setFrameVideoViewListener(b bVar) {
        this.c = bVar;
    }

    public void start() {
        if (this.f) {
            this.e.start();
        } else {
            this.g = true;
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
