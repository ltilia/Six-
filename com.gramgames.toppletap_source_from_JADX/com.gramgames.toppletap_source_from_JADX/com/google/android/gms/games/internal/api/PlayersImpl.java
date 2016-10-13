package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.Players.LoadPlayersResult;
import com.google.android.gms.games.Players.LoadProfileSettingsResult;
import com.google.android.gms.games.Players.LoadXpForGameCategoriesResult;
import com.google.android.gms.games.Players.LoadXpStreamResult;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class PlayersImpl implements Players {

    private static abstract class LoadPlayersImpl extends BaseGamesApiMethodImpl<LoadPlayersResult> {

        class 1 implements LoadPlayersResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadPlayersImpl zzaGR;

            1(LoadPlayersImpl loadPlayersImpl, Status status) {
                this.zzaGR = loadPlayersImpl;
                this.zzZR = status;
            }

            public PlayerBuffer getPlayers() {
                return new PlayerBuffer(DataHolder.zzbI(14));
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        private LoadPlayersImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadPlayersResult zzas(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzas(status);
        }
    }

    class 10 extends LoadPlayersImpl {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, "nearby", this.zzaFQ, this.zzaGL, true, false);
        }
    }

    class 11 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, "played_with", this.zzaFQ, this.zzaGL, false, this.zzaFO);
        }
    }

    class 12 extends LoadPlayersImpl {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, "played_with", this.zzaFQ, this.zzaGL, true, false);
        }
    }

    class 13 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaGL, false, this.zzaFO);
        }
    }

    class 14 extends LoadPlayersImpl {
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaGL, true, false);
        }
    }

    class 15 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzc((zzb) this, this.zzaGL, false, this.zzaFO);
        }
    }

    class 16 extends LoadPlayersImpl {
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzc((zzb) this, this.zzaGL, true, false);
        }
    }

    class 17 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd(this, this.zzaGL, false, this.zzaFO);
        }
    }

    class 18 extends LoadPlayersImpl {
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd(this, this.zzaGL, true, false);
        }
    }

    class 19 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ int zzaGL;
        final /* synthetic */ String zzaGh;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb(this, this.zzaGh, this.zzaGL, false, this.zzaFO);
        }
    }

    class 1 extends LoadPlayersImpl {
        final /* synthetic */ PlayersImpl zzaGK;
        final /* synthetic */ String zzacQ;

        1(PlayersImpl playersImpl, GoogleApiClient x0, String str) {
            this.zzaGK = playersImpl;
            this.zzacQ = str;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzacQ, false);
        }
    }

    class 20 extends LoadPlayersImpl {
        final /* synthetic */ int zzaGL;
        final /* synthetic */ String zzaGh;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb(this, this.zzaGh, this.zzaGL, true, false);
        }
    }

    class 21 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaGC;
        final /* synthetic */ int zzaGL;
        final /* synthetic */ String zzaGM;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaGM, this.zzaGC, this.zzaGL, false, this.zzaFO);
        }
    }

    class 22 extends LoadPlayersImpl {
        final /* synthetic */ String zzaGC;
        final /* synthetic */ int zzaGL;
        final /* synthetic */ String zzaGM;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaGM, this.zzaGC, this.zzaGL, true, false);
        }
    }

    private static abstract class LoadXpForGameCategoriesResultImpl extends BaseGamesApiMethodImpl<LoadXpForGameCategoriesResult> {

        class 1 implements LoadXpForGameCategoriesResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadXpForGameCategoriesResultImpl zzaGT;

            1(LoadXpForGameCategoriesResultImpl loadXpForGameCategoriesResultImpl, Status status) {
                this.zzaGT = loadXpForGameCategoriesResultImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public LoadXpForGameCategoriesResult zzau(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzau(status);
        }
    }

    class 23 extends LoadXpForGameCategoriesResultImpl {
        final /* synthetic */ String zzaGN;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzl(this, this.zzaGN);
        }
    }

    private static abstract class LoadXpStreamResultImpl extends BaseGamesApiMethodImpl<LoadXpStreamResult> {

        class 1 implements LoadXpStreamResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadXpStreamResultImpl zzaGU;

            1(LoadXpStreamResultImpl loadXpStreamResultImpl, Status status) {
                this.zzaGU = loadXpStreamResultImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public LoadXpStreamResult zzav(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzav(status);
        }
    }

    class 24 extends LoadXpStreamResultImpl {
        final /* synthetic */ String zzaGN;
        final /* synthetic */ int zzaGO;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzc((zzb) this, this.zzaGN, this.zzaGO);
        }
    }

    class 25 extends LoadXpStreamResultImpl {
        final /* synthetic */ String zzaGN;
        final /* synthetic */ int zzaGO;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd((zzb) this, this.zzaGN, this.zzaGO);
        }
    }

    private static abstract class LoadProfileSettingsResultImpl extends BaseGamesApiMethodImpl<LoadProfileSettingsResult> {

        class 1 implements LoadProfileSettingsResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadProfileSettingsResultImpl zzaGS;

            1(LoadProfileSettingsResultImpl loadProfileSettingsResultImpl, Status status) {
                this.zzaGS = loadProfileSettingsResultImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        protected LoadProfileSettingsResult zzat(Status status) {
            return new 1(this, status);
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzat(status);
        }
    }

    class 26 extends LoadProfileSettingsResultImpl {
        final /* synthetic */ boolean zzaFO;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzg((zzb) this, this.zzaFO);
        }
    }

    private static abstract class UpdateProfileSettingsResultImpl extends BaseGamesApiMethodImpl<Status> {
        protected Status zzb(Status status) {
            return status;
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    class 27 extends UpdateProfileSettingsResultImpl {
        final /* synthetic */ boolean zzaGP;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzh((zzb) this, this.zzaGP);
        }
    }

    class 2 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ PlayersImpl zzaGK;
        final /* synthetic */ String zzacQ;

        2(PlayersImpl playersImpl, GoogleApiClient x0, String str, boolean z) {
            this.zzaGK = playersImpl;
            this.zzacQ = str;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzacQ, this.zzaFO);
        }
    }

    class 3 extends LoadPlayersImpl {
        final /* synthetic */ String[] zzaGQ;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGQ);
        }
    }

    class 4 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ PlayersImpl zzaGK;
        final /* synthetic */ int zzaGL;

        4(PlayersImpl playersImpl, GoogleApiClient x0, int i, boolean z) {
            this.zzaGK = playersImpl;
            this.zzaGL = i;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGL, false, this.zzaFO);
        }
    }

    class 5 extends LoadPlayersImpl {
        final /* synthetic */ PlayersImpl zzaGK;
        final /* synthetic */ int zzaGL;

        5(PlayersImpl playersImpl, GoogleApiClient x0, int i) {
            this.zzaGK = playersImpl;
            this.zzaGL = i;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGL, true, false);
        }
    }

    class 6 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ PlayersImpl zzaGK;
        final /* synthetic */ int zzaGL;

        6(PlayersImpl playersImpl, GoogleApiClient x0, int i, boolean z) {
            this.zzaGK = playersImpl;
            this.zzaGL = i;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, "played_with", this.zzaGL, false, this.zzaFO);
        }
    }

    class 7 extends LoadPlayersImpl {
        final /* synthetic */ PlayersImpl zzaGK;
        final /* synthetic */ int zzaGL;

        7(PlayersImpl playersImpl, GoogleApiClient x0, int i) {
            this.zzaGK = playersImpl;
            this.zzaGL = i;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, "played_with", this.zzaGL, true, false);
        }
    }

    class 8 extends LoadPlayersImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ PlayersImpl zzaGK;

        8(PlayersImpl playersImpl, GoogleApiClient x0, boolean z) {
            this.zzaGK = playersImpl;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFO);
        }
    }

    class 9 extends LoadPlayersImpl {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ int zzaGL;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, "nearby", this.zzaFQ, this.zzaGL, false, false);
        }
    }

    public Intent getCompareProfileIntent(GoogleApiClient apiClient, Player player) {
        return Games.zzh(apiClient).zza(new PlayerEntity(player));
    }

    public Player getCurrentPlayer(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzwx();
    }

    public String getCurrentPlayerId(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzah(true);
    }

    public Intent getPlayerSearchIntent(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzwH();
    }

    public PendingResult<LoadPlayersResult> loadConnectedPlayers(GoogleApiClient apiClient, boolean forceReload) {
        return apiClient.zza(new 8(this, apiClient, forceReload));
    }

    public PendingResult<LoadPlayersResult> loadInvitablePlayers(GoogleApiClient apiClient, int pageSize, boolean forceReload) {
        return apiClient.zza(new 4(this, apiClient, pageSize, forceReload));
    }

    public PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient apiClient, int pageSize) {
        return apiClient.zza(new 5(this, apiClient, pageSize));
    }

    public PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient apiClient, int pageSize) {
        return apiClient.zza(new 7(this, apiClient, pageSize));
    }

    public PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient apiClient, String playerId) {
        return apiClient.zza(new 1(this, apiClient, playerId));
    }

    public PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient apiClient, String playerId, boolean forceReload) {
        return apiClient.zza(new 2(this, apiClient, playerId, forceReload));
    }

    public PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient apiClient, int pageSize, boolean forceReload) {
        return apiClient.zza(new 6(this, apiClient, pageSize, forceReload));
    }
}
