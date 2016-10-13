package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent;
import com.google.android.exoplayer.ExoPlayer.Listener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.simple.parser.Yytoken;

final class ExoPlayerImpl implements ExoPlayer {
    private static final String TAG = "ExoPlayerImpl";
    private final Handler eventHandler;
    private final ExoPlayerImplInternal internalPlayer;
    private final CopyOnWriteArraySet<Listener> listeners;
    private int pendingPlayWhenReadyAcks;
    private boolean playWhenReady;
    private int playbackState;
    private final int[] selectedTrackIndices;
    private final MediaFormat[][] trackFormats;

    class 1 extends Handler {
        1() {
        }

        public void handleMessage(Message msg) {
            ExoPlayerImpl.this.handleEvent(msg);
        }
    }

    @SuppressLint({"HandlerLeak"})
    public ExoPlayerImpl(int rendererCount, int minBufferMs, int minRebufferMs) {
        Log.i(TAG, "Init 1.5.6");
        this.playWhenReady = false;
        this.playbackState = 1;
        this.listeners = new CopyOnWriteArraySet();
        this.trackFormats = new MediaFormat[rendererCount][];
        this.selectedTrackIndices = new int[rendererCount];
        this.eventHandler = new 1();
        this.internalPlayer = new ExoPlayerImplInternal(this.eventHandler, this.playWhenReady, this.selectedTrackIndices, minBufferMs, minRebufferMs);
    }

    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public int getPlaybackState() {
        return this.playbackState;
    }

    public void prepare(TrackRenderer... renderers) {
        Arrays.fill(this.trackFormats, null);
        this.internalPlayer.prepare(renderers);
    }

    public int getTrackCount(int rendererIndex) {
        return this.trackFormats[rendererIndex] != null ? this.trackFormats[rendererIndex].length : 0;
    }

    public MediaFormat getTrackFormat(int rendererIndex, int trackIndex) {
        return this.trackFormats[rendererIndex][trackIndex];
    }

    public void setSelectedTrack(int rendererIndex, int trackIndex) {
        if (this.selectedTrackIndices[rendererIndex] != trackIndex) {
            this.selectedTrackIndices[rendererIndex] = trackIndex;
            this.internalPlayer.setRendererSelectedTrack(rendererIndex, trackIndex);
        }
    }

    public int getSelectedTrack(int rendererIndex) {
        return this.selectedTrackIndices[rendererIndex];
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        if (this.playWhenReady != playWhenReady) {
            this.playWhenReady = playWhenReady;
            this.pendingPlayWhenReadyAcks++;
            this.internalPlayer.setPlayWhenReady(playWhenReady);
            Iterator i$ = this.listeners.iterator();
            while (i$.hasNext()) {
                ((Listener) i$.next()).onPlayerStateChanged(playWhenReady, this.playbackState);
            }
        }
    }

    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    public boolean isPlayWhenReadyCommitted() {
        return this.pendingPlayWhenReadyAcks == 0;
    }

    public void seekTo(long positionMs) {
        this.internalPlayer.seekTo(positionMs);
    }

    public void stop() {
        this.internalPlayer.stop();
    }

    public void release() {
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages(null);
    }

    public void sendMessage(ExoPlayerComponent target, int messageType, Object message) {
        this.internalPlayer.sendMessage(target, messageType, message);
    }

    public void blockingSendMessage(ExoPlayerComponent target, int messageType, Object message) {
        this.internalPlayer.blockingSendMessage(target, messageType, message);
    }

    public long getDuration() {
        return this.internalPlayer.getDuration();
    }

    public long getCurrentPosition() {
        return this.internalPlayer.getCurrentPosition();
    }

    public long getBufferedPosition() {
        return this.internalPlayer.getBufferedPosition();
    }

    public int getBufferedPercentage() {
        long j = 100;
        long bufferedPosition = getBufferedPosition();
        long duration = getDuration();
        if (bufferedPosition == -1 || duration == -1) {
            return 0;
        }
        if (duration != 0) {
            j = (100 * bufferedPosition) / duration;
        }
        return (int) j;
    }

    void handleEvent(Message msg) {
        Iterator i$;
        switch (msg.what) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                System.arraycopy(msg.obj, 0, this.trackFormats, 0, this.trackFormats.length);
                this.playbackState = msg.arg1;
                i$ = this.listeners.iterator();
                while (i$.hasNext()) {
                    ((Listener) i$.next()).onPlayerStateChanged(this.playWhenReady, this.playbackState);
                }
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                this.playbackState = msg.arg1;
                i$ = this.listeners.iterator();
                while (i$.hasNext()) {
                    ((Listener) i$.next()).onPlayerStateChanged(this.playWhenReady, this.playbackState);
                }
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                this.pendingPlayWhenReadyAcks--;
                if (this.pendingPlayWhenReadyAcks == 0) {
                    i$ = this.listeners.iterator();
                    while (i$.hasNext()) {
                        ((Listener) i$.next()).onPlayWhenReadyCommitted();
                    }
                }
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                ExoPlaybackException exception = msg.obj;
                i$ = this.listeners.iterator();
                while (i$.hasNext()) {
                    ((Listener) i$.next()).onPlayerError(exception);
                }
            default:
        }
    }
}
