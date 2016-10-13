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
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class AchievementsImpl implements Achievements {

    private static abstract class LoadImpl extends BaseGamesApiMethodImpl<LoadAchievementsResult> {

        class 1 implements LoadAchievementsResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadImpl zzaFT;

            1(LoadImpl loadImpl, Status status) {
                this.zzaFT = loadImpl;
                this.zzZR = status;
            }

            public AchievementBuffer getAchievements() {
                return new AchievementBuffer(DataHolder.zzbI(14));
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

        public LoadAchievementsResult zzZ(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzZ(status);
        }
    }

    class 10 extends LoadImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String zzacQ;

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzacQ, this.zzaFQ, this.zzaFO);
        }
    }

    class 1 extends LoadImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ AchievementsImpl zzaFP;

        1(AchievementsImpl achievementsImpl, GoogleApiClient x0, boolean z) {
            this.zzaFP = achievementsImpl;
            this.zzaFO = z;
            super(null);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzc((zzb) this, this.zzaFO);
        }
    }

    private static abstract class UpdateImpl extends BaseGamesApiMethodImpl<UpdateAchievementResult> {
        private final String zzyv;

        class 1 implements UpdateAchievementResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ UpdateImpl zzaFU;

            1(UpdateImpl updateImpl, Status status) {
                this.zzaFU = updateImpl;
                this.zzZR = status;
            }

            public String getAchievementId() {
                return this.zzaFU.zzyv;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public UpdateImpl(String id, GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.zzyv = id;
        }

        public UpdateAchievementResult zzaa(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaa(status);
        }
    }

    class 2 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;

        2(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza(null, this.zzaFR);
        }
    }

    class 3 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;

        3(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFR);
        }
    }

    class 4 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;

        4(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb(null, this.zzaFR);
        }
    }

    class 5 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;

        5(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaFR);
        }
    }

    class 6 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;
        final /* synthetic */ int zzaFS;

        6(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str, int i) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            this.zzaFS = i;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza(null, this.zzaFR, this.zzaFS);
        }
    }

    class 7 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;
        final /* synthetic */ int zzaFS;

        7(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str, int i) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            this.zzaFS = i;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFR, this.zzaFS);
        }
    }

    class 8 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;
        final /* synthetic */ int zzaFS;

        8(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str, int i) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            this.zzaFS = i;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb(null, this.zzaFR, this.zzaFS);
        }
    }

    class 9 extends UpdateImpl {
        final /* synthetic */ AchievementsImpl zzaFP;
        final /* synthetic */ String zzaFR;
        final /* synthetic */ int zzaFS;

        9(AchievementsImpl achievementsImpl, String x0, GoogleApiClient x1, String str, int i) {
            this.zzaFP = achievementsImpl;
            this.zzaFR = str;
            this.zzaFS = i;
            super(x0, x1);
        }

        public void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaFR, this.zzaFS);
        }
    }

    public Intent getAchievementsIntent(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzwA();
    }

    public void increment(GoogleApiClient apiClient, String id, int numSteps) {
        apiClient.zzb(new 6(this, id, apiClient, id, numSteps));
    }

    public PendingResult<UpdateAchievementResult> incrementImmediate(GoogleApiClient apiClient, String id, int numSteps) {
        return apiClient.zzb(new 7(this, id, apiClient, id, numSteps));
    }

    public PendingResult<LoadAchievementsResult> load(GoogleApiClient apiClient, boolean forceReload) {
        return apiClient.zza(new 1(this, apiClient, forceReload));
    }

    public void reveal(GoogleApiClient apiClient, String id) {
        apiClient.zzb(new 2(this, id, apiClient, id));
    }

    public PendingResult<UpdateAchievementResult> revealImmediate(GoogleApiClient apiClient, String id) {
        return apiClient.zzb(new 3(this, id, apiClient, id));
    }

    public void setSteps(GoogleApiClient apiClient, String id, int numSteps) {
        apiClient.zzb(new 8(this, id, apiClient, id, numSteps));
    }

    public PendingResult<UpdateAchievementResult> setStepsImmediate(GoogleApiClient apiClient, String id, int numSteps) {
        return apiClient.zzb(new 9(this, id, apiClient, id, numSteps));
    }

    public void unlock(GoogleApiClient apiClient, String id) {
        apiClient.zzb(new 4(this, id, apiClient, id));
    }

    public PendingResult<UpdateAchievementResult> unlockImmediate(GoogleApiClient apiClient, String id) {
        return apiClient.zzb(new 5(this, id, apiClient, id));
    }
}
