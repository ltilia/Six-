package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.video.VideoConfiguration;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.games.video.Videos.ListVideosResult;
import com.google.android.gms.games.video.Videos.VideoAvailableResult;
import com.google.android.gms.games.video.Videos.VideoCapabilitiesResult;

public final class VideosImpl implements Videos {

    class 1 extends BaseGamesApiMethodImpl<Status> {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String zzaHU;
        final /* synthetic */ VideoConfiguration zzaHV;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((BaseGamesApiMethodImpl) this, this.zzaFQ, this.zzaHU, this.zzaHV);
        }

        public Status zzb(Status status) {
            return status;
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    private static abstract class CapabilitiesImpl extends BaseGamesApiMethodImpl<VideoCapabilitiesResult> {

        class 1 implements VideoCapabilitiesResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ CapabilitiesImpl zzaHX;

            1(CapabilitiesImpl capabilitiesImpl, Status status) {
                this.zzaHX = capabilitiesImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public VideoCapabilitiesResult zzaP(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaP(status);
        }
    }

    class 2 extends CapabilitiesImpl {
        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzg(this);
        }
    }

    private static abstract class AvailableImpl extends BaseGamesApiMethodImpl<VideoAvailableResult> {

        class 1 implements VideoAvailableResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ AvailableImpl zzaHW;

            1(AvailableImpl availableImpl, Status status) {
                this.zzaHW = availableImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public VideoAvailableResult zzaO(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaO(status);
        }
    }

    class 3 extends AvailableImpl {
        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzh(this);
        }
    }

    private static abstract class ListImpl extends BaseGamesApiMethodImpl<ListVideosResult> {

        class 1 implements ListVideosResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ ListImpl zzaHY;

            1(ListImpl listImpl, Status status) {
                this.zzaHY = listImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public ListVideosResult zzaQ(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaQ(status);
        }
    }

    class 4 extends ListImpl {
        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzi(this);
        }
    }
}
