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
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;

public final class InvitationsImpl implements Invitations {

    private static abstract class LoadInvitationsImpl extends BaseGamesApiMethodImpl<LoadInvitationsResult> {

        class 1 implements LoadInvitationsResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadInvitationsImpl zzaGo;

            1(LoadInvitationsImpl loadInvitationsImpl, Status status) {
                this.zzaGo = loadInvitationsImpl;
                this.zzZR = status;
            }

            public InvitationBuffer getInvitations() {
                return new InvitationBuffer(DataHolder.zzbI(14));
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        private LoadInvitationsImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public LoadInvitationsResult zzaj(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaj(status);
        }
    }

    class 1 extends LoadInvitationsImpl {
        final /* synthetic */ int zzaGl;
        final /* synthetic */ InvitationsImpl zzaGm;

        1(InvitationsImpl invitationsImpl, GoogleApiClient x0, int i) {
            this.zzaGm = invitationsImpl;
            this.zzaGl = i;
            super(null);
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGl);
        }
    }

    class 2 extends LoadInvitationsImpl {
        final /* synthetic */ String zzaFQ;
        final /* synthetic */ int zzaGl;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zze(this, this.zzaFQ, this.zzaGl);
        }
    }

    class 3 extends LoadInvitationsImpl {
        final /* synthetic */ String zzaGn;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzm(this, this.zzaGn);
        }
    }

    public Intent getInvitationInboxIntent(GoogleApiClient apiClient) {
        return Games.zzh(apiClient).zzwC();
    }

    public PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient apiClient) {
        return loadInvitations(apiClient, 0);
    }

    public PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient apiClient, int sortOrder) {
        return apiClient.zza(new 1(this, apiClient, sortOrder));
    }

    public void registerInvitationListener(GoogleApiClient apiClient, OnInvitationReceivedListener listener) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zza(apiClient.zzr(listener));
        }
    }

    public void unregisterInvitationListener(GoogleApiClient apiClient) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzwD();
        }
    }
}
