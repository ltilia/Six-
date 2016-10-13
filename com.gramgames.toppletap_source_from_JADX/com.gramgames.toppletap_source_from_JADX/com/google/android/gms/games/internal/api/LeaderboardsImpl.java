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
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.GamesLog;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

public final class LeaderboardsImpl implements Leaderboards {

    private static abstract class LoadScoresImpl extends BaseGamesApiMethodImpl<LoadScoresResult> {

        class 1 implements LoadScoresResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadScoresImpl zzaGA;

            1(LoadScoresImpl loadScoresImpl, Status status) {
                this.zzaGA = loadScoresImpl;
                this.zzZR = status;
            }

            public Leaderboard getLeaderboard() {
                return null;
            }

            public LeaderboardScoreBuffer getScores() {
                return new LeaderboardScoreBuffer(DataHolder.zzbI(14));
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        private LoadScoresImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadScoresResult zzam(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzam(status);
        }
    }

    class 10 extends LoadScoresImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String zzaGq;
        final /* synthetic */ int zzaGr;
        final /* synthetic */ int zzaGs;
        final /* synthetic */ int zzaGt;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza(this, this.zzaFQ, this.zzaGq, this.zzaGr, this.zzaGs, this.zzaGt, this.zzaFO);
        }
    }

    class 11 extends LoadScoresImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String zzaGq;
        final /* synthetic */ int zzaGr;
        final /* synthetic */ int zzaGs;
        final /* synthetic */ int zzaGt;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb(this, this.zzaFQ, this.zzaGq, this.zzaGr, this.zzaGs, this.zzaGt, this.zzaFO);
        }
    }

    private static abstract class LoadMetadataImpl extends BaseGamesApiMethodImpl<LeaderboardMetadataResult> {

        class 1 implements LeaderboardMetadataResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadMetadataImpl zzaGy;

            1(LoadMetadataImpl loadMetadataImpl, Status status) {
                this.zzaGy = loadMetadataImpl;
                this.zzZR = status;
            }

            public LeaderboardBuffer getLeaderboards() {
                return new LeaderboardBuffer(DataHolder.zzbI(14));
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        private LoadMetadataImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LeaderboardMetadataResult zzak(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzak(status);
        }
    }

    class 1 extends LoadMetadataImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ LeaderboardsImpl zzaGp;

        1(LeaderboardsImpl leaderboardsImpl, GoogleApiClient x0, boolean z) {
            this.zzaGp = leaderboardsImpl;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaFO);
        }
    }

    class 2 extends LoadMetadataImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ LeaderboardsImpl zzaGp;
        final /* synthetic */ String zzaGq;

        2(LeaderboardsImpl leaderboardsImpl, GoogleApiClient x0, String str, boolean z) {
            this.zzaGp = leaderboardsImpl;
            this.zzaGq = str;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaGq, this.zzaFO);
        }
    }

    private static abstract class LoadPlayerScoreImpl extends BaseGamesApiMethodImpl<LoadPlayerScoreResult> {

        class 1 implements LoadPlayerScoreResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadPlayerScoreImpl zzaGz;

            1(LoadPlayerScoreImpl loadPlayerScoreImpl, Status status) {
                this.zzaGz = loadPlayerScoreImpl;
                this.zzZR = status;
            }

            public LeaderboardScore getScore() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private LoadPlayerScoreImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadPlayerScoreResult zzal(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzal(status);
        }
    }

    class 3 extends LoadPlayerScoreImpl {
        final /* synthetic */ LeaderboardsImpl zzaGp;
        final /* synthetic */ String zzaGq;
        final /* synthetic */ int zzaGr;
        final /* synthetic */ int zzaGs;

        3(LeaderboardsImpl leaderboardsImpl, GoogleApiClient x0, String str, int i, int i2) {
            this.zzaGp = leaderboardsImpl;
            this.zzaGq = str;
            this.zzaGr = i;
            this.zzaGs = i2;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, null, this.zzaGq, this.zzaGr, this.zzaGs);
        }
    }

    class 4 extends LoadScoresImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ LeaderboardsImpl zzaGp;
        final /* synthetic */ String zzaGq;
        final /* synthetic */ int zzaGr;
        final /* synthetic */ int zzaGs;
        final /* synthetic */ int zzaGt;

        4(LeaderboardsImpl leaderboardsImpl, GoogleApiClient x0, String str, int i, int i2, int i3, boolean z) {
            this.zzaGp = leaderboardsImpl;
            this.zzaGq = str;
            this.zzaGr = i;
            this.zzaGs = i2;
            this.zzaGt = i3;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGq, this.zzaGr, this.zzaGs, this.zzaGt, this.zzaFO);
        }
    }

    class 5 extends LoadScoresImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ LeaderboardsImpl zzaGp;
        final /* synthetic */ String zzaGq;
        final /* synthetic */ int zzaGr;
        final /* synthetic */ int zzaGs;
        final /* synthetic */ int zzaGt;

        5(LeaderboardsImpl leaderboardsImpl, GoogleApiClient x0, String str, int i, int i2, int i3, boolean z) {
            this.zzaGp = leaderboardsImpl;
            this.zzaGq = str;
            this.zzaGr = i;
            this.zzaGs = i2;
            this.zzaGt = i3;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaGq, this.zzaGr, this.zzaGs, this.zzaGt, this.zzaFO);
        }
    }

    class 6 extends LoadScoresImpl {
        final /* synthetic */ LeaderboardsImpl zzaGp;
        final /* synthetic */ int zzaGt;
        final /* synthetic */ LeaderboardScoreBuffer zzaGu;
        final /* synthetic */ int zzaGv;

        6(LeaderboardsImpl leaderboardsImpl, GoogleApiClient x0, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) {
            this.zzaGp = leaderboardsImpl;
            this.zzaGu = leaderboardScoreBuffer;
            this.zzaGt = i;
            this.zzaGv = i2;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGu, this.zzaGt, this.zzaGv);
        }
    }

    protected static abstract class SubmitScoreImpl extends BaseGamesApiMethodImpl<SubmitScoreResult> {

        class 1 implements SubmitScoreResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ SubmitScoreImpl zzaGB;

            1(SubmitScoreImpl submitScoreImpl, Status status) {
                this.zzaGB = submitScoreImpl;
                this.zzZR = status;
            }

            public ScoreSubmissionData getScoreData() {
                return new ScoreSubmissionData(DataHolder.zzbI(14));
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        protected SubmitScoreImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public SubmitScoreResult zzan(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzan(status);
        }
    }

    class 7 extends SubmitScoreImpl {
        final /* synthetic */ LeaderboardsImpl zzaGp;
        final /* synthetic */ String zzaGq;
        final /* synthetic */ long zzaGw;
        final /* synthetic */ String zzaGx;

        7(LeaderboardsImpl leaderboardsImpl, GoogleApiClient x0, String str, long j, String str2) {
            this.zzaGp = leaderboardsImpl;
            this.zzaGq = str;
            this.zzaGw = j;
            this.zzaGx = str2;
            super(x0);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGq, this.zzaGw, this.zzaGx);
        }
    }

    class 8 extends LoadMetadataImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzc((zzb) this, this.zzaFQ, this.zzaFO);
        }
    }

    class 9 extends LoadMetadataImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String zzaGq;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFQ, this.zzaGq, this.zzaFO);
        }
    }

    public Intent getAllLeaderboardsIntent(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzwz();
    }

    public Intent getLeaderboardIntent(GoogleApiClient apiClient, String leaderboardId) {
        return getLeaderboardIntent(apiClient, leaderboardId, -1);
    }

    public Intent getLeaderboardIntent(GoogleApiClient apiClient, String leaderboardId, int timeSpan) {
        return getLeaderboardIntent(apiClient, leaderboardId, timeSpan, -1);
    }

    public Intent getLeaderboardIntent(GoogleApiClient apiClient, String leaderboardId, int timeSpan, int collection) {
        return Games.zzh(apiClient).zzl(leaderboardId, timeSpan, collection);
    }

    public PendingResult<LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection) {
        return apiClient.zza(new 3(this, apiClient, leaderboardId, span, leaderboardCollection));
    }

    public PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient apiClient, String leaderboardId, boolean forceReload) {
        return apiClient.zza(new 2(this, apiClient, leaderboardId, forceReload));
    }

    public PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient apiClient, boolean forceReload) {
        return apiClient.zza(new 1(this, apiClient, forceReload));
    }

    public PendingResult<LoadScoresResult> loadMoreScores(GoogleApiClient apiClient, LeaderboardScoreBuffer buffer, int maxResults, int pageDirection) {
        return apiClient.zza(new 6(this, apiClient, buffer, maxResults, pageDirection));
    }

    public PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults) {
        return loadPlayerCenteredScores(apiClient, leaderboardId, span, leaderboardCollection, maxResults, false);
    }

    public PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults, boolean forceReload) {
        return apiClient.zza(new 5(this, apiClient, leaderboardId, span, leaderboardCollection, maxResults, forceReload));
    }

    public PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults) {
        return loadTopScores(apiClient, leaderboardId, span, leaderboardCollection, maxResults, false);
    }

    public PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults, boolean forceReload) {
        return apiClient.zza(new 4(this, apiClient, leaderboardId, span, leaderboardCollection, maxResults, forceReload));
    }

    public void submitScore(GoogleApiClient apiClient, String leaderboardId, long score) {
        submitScore(apiClient, leaderboardId, score, null);
    }

    public void submitScore(GoogleApiClient apiClient, String leaderboardId, long score, String scoreTag) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            try {
                zzb.zza(null, leaderboardId, score, scoreTag);
            } catch (RemoteException e) {
                GamesLog.zzz("LeaderboardsImpl", "service died");
            }
        }
    }

    public PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient apiClient, String leaderboardId, long score) {
        return submitScoreImmediate(apiClient, leaderboardId, score, null);
    }

    public PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient apiClient, String leaderboardId, long score, String scoreTag) {
        return apiClient.zzb(new 7(this, apiClient, leaderboardId, score, scoreTag));
    }
}
