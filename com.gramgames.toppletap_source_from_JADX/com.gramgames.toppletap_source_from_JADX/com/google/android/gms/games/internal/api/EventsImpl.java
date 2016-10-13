package com.google.android.gms.games.internal.api;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.event.Events.LoadEventsResult;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class EventsImpl implements Events {

    private static abstract class LoadImpl extends BaseGamesApiMethodImpl<LoadEventsResult> {

        class 1 implements LoadEventsResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadImpl zzaGe;

            1(LoadImpl loadImpl, Status status) {
                this.zzaGe = loadImpl;
                this.zzZR = status;
            }

            public EventBuffer getEvents() {
                return new EventBuffer(DataHolder.zzbI(14));
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        private LoadImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadEventsResult zzaf(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaf(status);
        }
    }

    class 1 extends LoadImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String[] zzaGa;
        final /* synthetic */ EventsImpl zzaGb;

        1(EventsImpl eventsImpl, GoogleApiClient x0, boolean z, String[] strArr) {
            this.zzaGb = eventsImpl;
            this.zzaFO = z;
            this.zzaGa = strArr;
            super(null);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFO, this.zzaGa);
        }
    }

    class 2 extends LoadImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ EventsImpl zzaGb;

        2(EventsImpl eventsImpl, GoogleApiClient x0, boolean z) {
            this.zzaGb = eventsImpl;
            this.zzaFO = z;
            super(null);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd((zzb) this, this.zzaFO);
        }
    }

    private static abstract class UpdateImpl extends BaseGamesApiMethodImpl<Result> {

        class 1 implements Result {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ UpdateImpl zzaGf;

            1(UpdateImpl updateImpl, Status status) {
                this.zzaGf = updateImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private UpdateImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public Result zzc(Status status) {
            return new 1(this, status);
        }
    }

    class 3 extends UpdateImpl {
        final /* synthetic */ EventsImpl zzaGb;
        final /* synthetic */ String zzaGc;
        final /* synthetic */ int zzaGd;

        3(EventsImpl eventsImpl, GoogleApiClient x0, String str, int i) {
            this.zzaGb = eventsImpl;
            this.zzaGc = str;
            this.zzaGd = i;
            super(null);
        }

        public void zza(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.zzp(this.zzaGc, this.zzaGd);
        }
    }

    @SuppressLint({"MissingRemoteException"})
    public void increment(GoogleApiClient apiClient, String eventId, int incrementAmount) {
        GamesClientImpl zzc = Games.zzc(apiClient, false);
        if (zzc != null) {
            if (zzc.isConnected()) {
                zzc.zzp(eventId, incrementAmount);
            } else {
                apiClient.zzb(new 3(this, apiClient, eventId, incrementAmount));
            }
        }
    }

    public PendingResult<LoadEventsResult> load(GoogleApiClient apiClient, boolean forceReload) {
        return apiClient.zza(new 2(this, apiClient, forceReload));
    }

    public PendingResult<LoadEventsResult> loadByIds(GoogleApiClient apiClient, boolean forceReload, String... eventIds) {
        return apiClient.zza(new 1(this, apiClient, forceReload, eventIds));
    }
}
