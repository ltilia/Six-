package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult;

public final class StatsImpl implements Stats {

    private static abstract class LoadsImpl extends BaseGamesApiMethodImpl<LoadPlayerStatsResult> {

        class 1 implements LoadPlayerStatsResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadsImpl zzaHF;

            1(LoadsImpl loadsImpl, Status status) {
                this.zzaHF = loadsImpl;
                this.zzZR = status;
            }

            public PlayerStats getPlayerStats() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        private LoadsImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadPlayerStatsResult zzaH(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaH(status);
        }
    }

    class 1 extends LoadsImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ StatsImpl zzaHE;

        1(StatsImpl statsImpl, GoogleApiClient x0, boolean z) {
            this.zzaHE = statsImpl;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zze((zzb) this, this.zzaFO);
        }
    }

    public PendingResult<LoadPlayerStatsResult> loadPlayerStats(GoogleApiClient apiClient, boolean forceReload) {
        return apiClient.zza(new 1(this, apiClient, forceReload));
    }
}
