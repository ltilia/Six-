package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;
import java.util.List;

public final class TurnBasedMultiplayerImpl implements TurnBasedMultiplayer {

    private static abstract class LoadMatchImpl extends BaseGamesApiMethodImpl<LoadMatchResult> {

        class 1 implements LoadMatchResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadMatchImpl zzaHR;

            1(LoadMatchImpl loadMatchImpl, Status status) {
                this.zzaHR = loadMatchImpl;
                this.zzZR = status;
            }

            public TurnBasedMatch getMatch() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private LoadMatchImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadMatchResult zzaL(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaL(status);
        }
    }

    class 10 extends LoadMatchImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ String zzaHI;

        10(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, String str) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHI = str;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzg((zzb) this, this.zzaHI);
        }
    }

    private static abstract class InitiateMatchImpl extends BaseGamesApiMethodImpl<InitiateMatchResult> {

        class 1 implements InitiateMatchResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ InitiateMatchImpl zzaHP;

            1(InitiateMatchImpl initiateMatchImpl, Status status) {
                this.zzaHP = initiateMatchImpl;
                this.zzZR = status;
            }

            public TurnBasedMatch getMatch() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private InitiateMatchImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public InitiateMatchResult zzaJ(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaJ(status);
        }
    }

    class 11 extends InitiateMatchImpl {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String zzaHI;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzc((zzb) this, this.zzaFQ, this.zzaHI);
        }
    }

    class 12 extends InitiateMatchImpl {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String zzaHI;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd((zzb) this, this.zzaFQ, this.zzaHI);
        }
    }

    private static abstract class LoadMatchesImpl extends BaseGamesApiMethodImpl<LoadMatchesResult> {

        class 1 implements LoadMatchesResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadMatchesImpl zzaHS;

            1(LoadMatchesImpl loadMatchesImpl, Status status) {
                this.zzaHS = loadMatchesImpl;
                this.zzZR = status;
            }

            public LoadMatchesResponse getMatches() {
                return new LoadMatchesResponse(new Bundle());
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        private LoadMatchesImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadMatchesResult zzaM(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaM(status);
        }
    }

    class 13 extends LoadMatchesImpl {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ int zzaHJ;
        final /* synthetic */ int[] zzaHK;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFQ, this.zzaHJ, this.zzaHK);
        }
    }

    class 1 extends InitiateMatchImpl {
        final /* synthetic */ TurnBasedMatchConfig zzaHG;
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;

        1(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, TurnBasedMatchConfig turnBasedMatchConfig) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHG = turnBasedMatchConfig;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaHG);
        }
    }

    class 2 extends InitiateMatchImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ String zzaHI;

        2(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, String str) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHI = str;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzc((zzb) this, this.zzaHI);
        }
    }

    class 3 extends InitiateMatchImpl {
        final /* synthetic */ String zzaGn;
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;

        3(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, String str) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaGn = str;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd((zzb) this, this.zzaGn);
        }
    }

    private static abstract class UpdateMatchImpl extends BaseGamesApiMethodImpl<UpdateMatchResult> {

        class 1 implements UpdateMatchResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ UpdateMatchImpl zzaHT;

            1(UpdateMatchImpl updateMatchImpl, Status status) {
                this.zzaHT = updateMatchImpl;
                this.zzZR = status;
            }

            public TurnBasedMatch getMatch() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private UpdateMatchImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public UpdateMatchResult zzaN(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaN(status);
        }
    }

    class 4 extends UpdateMatchImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ String zzaHI;
        final /* synthetic */ byte[] zzaHL;
        final /* synthetic */ String zzaHM;
        final /* synthetic */ ParticipantResult[] zzaHN;

        4(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHI = str;
            this.zzaHL = bArr;
            this.zzaHM = str2;
            this.zzaHN = participantResultArr;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaHI, this.zzaHL, this.zzaHM, this.zzaHN);
        }
    }

    class 5 extends UpdateMatchImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ String zzaHI;
        final /* synthetic */ byte[] zzaHL;
        final /* synthetic */ ParticipantResult[] zzaHN;

        5(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, String str, byte[] bArr, ParticipantResult[] participantResultArr) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHI = str;
            this.zzaHL = bArr;
            this.zzaHN = participantResultArr;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaHI, this.zzaHL, this.zzaHN);
        }
    }

    private static abstract class LeaveMatchImpl extends BaseGamesApiMethodImpl<LeaveMatchResult> {

        class 1 implements LeaveMatchResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LeaveMatchImpl zzaHQ;

            1(LeaveMatchImpl leaveMatchImpl, Status status) {
                this.zzaHQ = leaveMatchImpl;
                this.zzZR = status;
            }

            public TurnBasedMatch getMatch() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private LeaveMatchImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LeaveMatchResult zzaK(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaK(status);
        }
    }

    class 6 extends LeaveMatchImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ String zzaHI;

        6(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, String str) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHI = str;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zze((zzb) this, this.zzaHI);
        }
    }

    class 7 extends LeaveMatchImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ String zzaHI;
        final /* synthetic */ String zzaHM;

        7(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, String str, String str2) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHI = str;
            this.zzaHM = str2;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaHI, this.zzaHM);
        }
    }

    private static abstract class CancelMatchImpl extends BaseGamesApiMethodImpl<CancelMatchResult> {
        private final String zzyv;

        class 1 implements CancelMatchResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ CancelMatchImpl zzaHO;

            1(CancelMatchImpl cancelMatchImpl, Status status) {
                this.zzaHO = cancelMatchImpl;
                this.zzZR = status;
            }

            public String getMatchId() {
                return this.zzaHO.zzyv;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public CancelMatchImpl(String id, GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.zzyv = id;
        }

        public CancelMatchResult zzaI(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaI(status);
        }
    }

    class 8 extends CancelMatchImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ String zzaHI;

        8(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, String x0, GoogleApiClient x1, String str) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHI = str;
            super(x0, x1);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzf((zzb) this, this.zzaHI);
        }
    }

    class 9 extends LoadMatchesImpl {
        final /* synthetic */ TurnBasedMultiplayerImpl zzaHH;
        final /* synthetic */ int zzaHJ;
        final /* synthetic */ int[] zzaHK;

        9(TurnBasedMultiplayerImpl turnBasedMultiplayerImpl, GoogleApiClient x0, int i, int[] iArr) {
            this.zzaHH = turnBasedMultiplayerImpl;
            this.zzaHJ = i;
            this.zzaHK = iArr;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaHJ, this.zzaHK);
        }
    }

    public PendingResult<InitiateMatchResult> acceptInvitation(GoogleApiClient apiClient, String invitationId) {
        return apiClient.zzb(new 3(this, apiClient, invitationId));
    }

    public PendingResult<CancelMatchResult> cancelMatch(GoogleApiClient apiClient, String matchId) {
        return apiClient.zzb(new 8(this, matchId, apiClient, matchId));
    }

    public PendingResult<InitiateMatchResult> createMatch(GoogleApiClient apiClient, TurnBasedMatchConfig config) {
        return apiClient.zzb(new 1(this, apiClient, config));
    }

    public void declineInvitation(GoogleApiClient apiClient, String invitationId) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzr(invitationId, 1);
        }
    }

    public void dismissInvitation(GoogleApiClient apiClient, String invitationId) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzq(invitationId, 1);
        }
    }

    public void dismissMatch(GoogleApiClient apiClient, String matchId) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzdH(matchId);
        }
    }

    public PendingResult<UpdateMatchResult> finishMatch(GoogleApiClient apiClient, String matchId) {
        return finishMatch(apiClient, matchId, null, (ParticipantResult[]) null);
    }

    public PendingResult<UpdateMatchResult> finishMatch(GoogleApiClient apiClient, String matchId, byte[] matchData, List<ParticipantResult> results) {
        return finishMatch(apiClient, matchId, matchData, results == null ? null : (ParticipantResult[]) results.toArray(new ParticipantResult[results.size()]));
    }

    public PendingResult<UpdateMatchResult> finishMatch(GoogleApiClient apiClient, String matchId, byte[] matchData, ParticipantResult... results) {
        return apiClient.zzb(new 5(this, apiClient, matchId, matchData, results));
    }

    public Intent getInboxIntent(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzwB();
    }

    public int getMaxMatchDataSize(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzwL();
    }

    public Intent getSelectOpponentsIntent(GoogleApiClient apiClient, int minPlayers, int maxPlayers) {
        return Games.zzh(apiClient).zzb(minPlayers, maxPlayers, true);
    }

    public Intent getSelectOpponentsIntent(GoogleApiClient apiClient, int minPlayers, int maxPlayers, boolean allowAutomatch) {
        return Games.zzh(apiClient).zzb(minPlayers, maxPlayers, allowAutomatch);
    }

    public PendingResult<LeaveMatchResult> leaveMatch(GoogleApiClient apiClient, String matchId) {
        return apiClient.zzb(new 6(this, apiClient, matchId));
    }

    public PendingResult<LeaveMatchResult> leaveMatchDuringTurn(GoogleApiClient apiClient, String matchId, String pendingParticipantId) {
        return apiClient.zzb(new 7(this, apiClient, matchId, pendingParticipantId));
    }

    public PendingResult<LoadMatchResult> loadMatch(GoogleApiClient apiClient, String matchId) {
        return apiClient.zza(new 10(this, apiClient, matchId));
    }

    public PendingResult<LoadMatchesResult> loadMatchesByStatus(GoogleApiClient apiClient, int invitationSortOrder, int[] matchTurnStatuses) {
        return apiClient.zza(new 9(this, apiClient, invitationSortOrder, matchTurnStatuses));
    }

    public PendingResult<LoadMatchesResult> loadMatchesByStatus(GoogleApiClient apiClient, int[] matchTurnStatuses) {
        return loadMatchesByStatus(apiClient, 0, matchTurnStatuses);
    }

    public void registerMatchUpdateListener(GoogleApiClient apiClient, OnTurnBasedMatchUpdateReceivedListener listener) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzb(apiClient.zzr(listener));
        }
    }

    public PendingResult<InitiateMatchResult> rematch(GoogleApiClient apiClient, String matchId) {
        return apiClient.zzb(new 2(this, apiClient, matchId));
    }

    public PendingResult<UpdateMatchResult> takeTurn(GoogleApiClient apiClient, String matchId, byte[] matchData, String pendingParticipantId) {
        return takeTurn(apiClient, matchId, matchData, pendingParticipantId, (ParticipantResult[]) null);
    }

    public PendingResult<UpdateMatchResult> takeTurn(GoogleApiClient apiClient, String matchId, byte[] matchData, String pendingParticipantId, List<ParticipantResult> results) {
        return takeTurn(apiClient, matchId, matchData, pendingParticipantId, results == null ? null : (ParticipantResult[]) results.toArray(new ParticipantResult[results.size()]));
    }

    public PendingResult<UpdateMatchResult> takeTurn(GoogleApiClient apiClient, String matchId, byte[] matchData, String pendingParticipantId, ParticipantResult... results) {
        return apiClient.zzb(new 4(this, apiClient, matchId, matchData, pendingParticipantId, results));
    }

    public void unregisterMatchUpdateListener(GoogleApiClient apiClient) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzwE();
        }
    }
}
