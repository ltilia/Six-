package com.google.android.gms.games.internal.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.Notifications.ContactSettingLoadResult;
import com.google.android.gms.games.Notifications.GameMuteStatusChangeResult;
import com.google.android.gms.games.Notifications.GameMuteStatusLoadResult;
import com.google.android.gms.games.Notifications.InboxCountResult;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class NotificationsImpl implements Notifications {

    class 1 extends BaseGamesApiMethodImpl<GameMuteStatusChangeResult> {
        final /* synthetic */ String zzaGC;

        class 1 implements GameMuteStatusChangeResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ 1 zzaGD;

            1(1 1, Status status) {
                this.zzaGD = 1;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd((zzb) this, this.zzaGC, true);
        }

        public GameMuteStatusChangeResult zzao(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzao(status);
        }
    }

    class 2 extends BaseGamesApiMethodImpl<GameMuteStatusChangeResult> {
        final /* synthetic */ String zzaGC;

        class 1 implements GameMuteStatusChangeResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ 2 zzaGE;

            1(2 2, Status status) {
                this.zzaGE = 2;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzd((zzb) this, this.zzaGC, false);
        }

        public GameMuteStatusChangeResult zzao(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzao(status);
        }
    }

    class 3 extends BaseGamesApiMethodImpl<GameMuteStatusLoadResult> {
        final /* synthetic */ String zzaGC;

        class 1 implements GameMuteStatusLoadResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ 3 zzaGF;

            1(3 3, Status status) {
                this.zzaGF = 3;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzo(this, this.zzaGC);
        }

        public GameMuteStatusLoadResult zzap(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzap(status);
        }
    }

    private static abstract class ContactSettingLoadImpl extends BaseGamesApiMethodImpl<ContactSettingLoadResult> {

        class 1 implements ContactSettingLoadResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ ContactSettingLoadImpl zzaGI;

            1(ContactSettingLoadImpl contactSettingLoadImpl, Status status) {
                this.zzaGI = contactSettingLoadImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public ContactSettingLoadResult zzaq(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzaq(status);
        }
    }

    class 4 extends ContactSettingLoadImpl {
        final /* synthetic */ boolean zzaFO;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzi((zzb) this, this.zzaFO);
        }
    }

    private static abstract class ContactSettingUpdateImpl extends BaseGamesApiMethodImpl<Status> {
        public Status zzb(Status status) {
            return status;
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    class 5 extends ContactSettingUpdateImpl {
        final /* synthetic */ boolean zzaGG;
        final /* synthetic */ Bundle zzaGH;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaGG, this.zzaGH);
        }
    }

    private static abstract class InboxCountImpl extends BaseGamesApiMethodImpl<InboxCountResult> {

        class 1 implements InboxCountResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ InboxCountImpl zzaGJ;

            1(InboxCountImpl inboxCountImpl, Status status) {
                this.zzaGJ = inboxCountImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        public InboxCountResult zzar(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzar(status);
        }
    }

    class 6 extends InboxCountImpl {
        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzk(this);
        }
    }

    public void clear(GoogleApiClient apiClient, int notificationTypes) {
        GamesClientImpl zzb = Games.zzb(apiClient, false);
        if (zzb != null) {
            zzb.zzgt(notificationTypes);
        }
    }

    public void clearAll(GoogleApiClient apiClient) {
        clear(apiClient, 31);
    }
}
