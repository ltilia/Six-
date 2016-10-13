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
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.quest.Quests.AcceptQuestResult;
import com.google.android.gms.games.quest.Quests.ClaimMilestoneResult;
import com.google.android.gms.games.quest.Quests.LoadQuestsResult;

public final class QuestsImpl implements Quests {

    private static abstract class AcceptImpl extends BaseGamesApiMethodImpl<AcceptQuestResult> {

        class 1 implements AcceptQuestResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ AcceptImpl zzaHb;

            1(AcceptImpl acceptImpl, Status status) {
                this.zzaHb = acceptImpl;
                this.zzZR = status;
            }

            public Quest getQuest() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private AcceptImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public AcceptQuestResult zzaw(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaw(status);
        }
    }

    class 1 extends AcceptImpl {
        final /* synthetic */ String zzaGV;
        final /* synthetic */ QuestsImpl zzaGW;

        1(QuestsImpl questsImpl, GoogleApiClient x0, String str) {
            this.zzaGW = questsImpl;
            this.zzaGV = str;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzh((zzb) this, this.zzaGV);
        }
    }

    private static abstract class ClaimImpl extends BaseGamesApiMethodImpl<ClaimMilestoneResult> {

        class 1 implements ClaimMilestoneResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ ClaimImpl zzaHc;

            1(ClaimImpl claimImpl, Status status) {
                this.zzaHc = claimImpl;
                this.zzZR = status;
            }

            public Milestone getMilestone() {
                return null;
            }

            public Quest getQuest() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private ClaimImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public ClaimMilestoneResult zzax(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzax(status);
        }
    }

    class 2 extends ClaimImpl {
        final /* synthetic */ String zzaGV;
        final /* synthetic */ QuestsImpl zzaGW;
        final /* synthetic */ String zzaGX;

        2(QuestsImpl questsImpl, GoogleApiClient x0, String str, String str2) {
            this.zzaGW = questsImpl;
            this.zzaGV = str;
            this.zzaGX = str2;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaGV, this.zzaGX);
        }
    }

    private static abstract class LoadsImpl extends BaseGamesApiMethodImpl<LoadQuestsResult> {

        class 1 implements LoadQuestsResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadsImpl zzaHd;

            1(LoadsImpl loadsImpl, Status status) {
                this.zzaHd = loadsImpl;
                this.zzZR = status;
            }

            public QuestBuffer getQuests() {
                return new QuestBuffer(DataHolder.zzbI(this.zzZR.getStatusCode()));
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

        public LoadQuestsResult zzay(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzay(status);
        }
    }

    class 3 extends LoadsImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ QuestsImpl zzaGW;
        final /* synthetic */ int[] zzaGY;
        final /* synthetic */ int zzaGl;

        3(QuestsImpl questsImpl, GoogleApiClient x0, int[] iArr, int i, boolean z) {
            this.zzaGW = questsImpl;
            this.zzaGY = iArr;
            this.zzaGl = i;
            this.zzaFO = z;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGY, this.zzaGl, this.zzaFO);
        }
    }

    class 4 extends LoadsImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ QuestsImpl zzaGW;
        final /* synthetic */ String[] zzaGZ;

        4(QuestsImpl questsImpl, GoogleApiClient x0, boolean z, String[] strArr) {
            this.zzaGW = questsImpl;
            this.zzaFO = z;
            this.zzaGZ = strArr;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzb((zzb) this, this.zzaFO, this.zzaGZ);
        }
    }

    class 5 extends LoadsImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ int[] zzaGY;
        final /* synthetic */ int zzaGl;
        final /* synthetic */ String zzaHa;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFQ, this.zzaHa, this.zzaGY, this.zzaGl, this.zzaFO);
        }
    }

    class 6 extends LoadsImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ String[] zzaGZ;
        final /* synthetic */ String zzaHa;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFQ, this.zzaHa, this.zzaFO, this.zzaGZ);
        }
    }

    public PendingResult<AcceptQuestResult> accept(GoogleApiClient apiClient, String questId) {
        return apiClient.zzb(new 1(this, apiClient, questId));
    }

    public PendingResult<ClaimMilestoneResult> claim(GoogleApiClient apiClient, String questId, String milestoneId) {
        return apiClient.zzb(new 2(this, apiClient, questId, milestoneId));
    }

    public Intent getQuestIntent(GoogleApiClient apiClient, String questId) {
        return Games.zzh(apiClient).zzdI(questId);
    }

    public Intent getQuestsIntent(GoogleApiClient apiClient, int[] questSelectors) {
        return Games.zzh(apiClient).zzb(questSelectors);
    }

    public PendingResult<LoadQuestsResult> load(GoogleApiClient apiClient, int[] questSelectors, int sortOrder, boolean forceReload) {
        return apiClient.zza(new 3(this, apiClient, questSelectors, sortOrder, forceReload));
    }

    public PendingResult<LoadQuestsResult> loadByIds(GoogleApiClient apiClient, boolean forceReload, String... questIds) {
        return apiClient.zza(new 4(this, apiClient, forceReload, questIds));
    }

    public void registerQuestUpdateListener(GoogleApiClient apiClient, QuestUpdateListener listener) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzc(apiClient.zzr(listener));
        }
    }

    public void showStateChangedPopup(GoogleApiClient apiClient, String questId) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzdJ(questId);
        }
    }

    public void unregisterQuestUpdateListener(GoogleApiClient apiClient) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzwF();
        }
    }
}
