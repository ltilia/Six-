package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.internal.game.Acls.LoadAclResult;

public final class AclsImpl implements Acls {

    static class 1 implements LoadAclResult {
        final /* synthetic */ Status zzZR;

        1(Status status) {
            this.zzZR = status;
        }

        public Status getStatus() {
            return this.zzZR;
        }

        public void release() {
        }
    }

    private static abstract class LoadNotifyAclImpl extends BaseGamesApiMethodImpl<LoadAclResult> {
        public LoadAclResult zzad(Status status) {
            return AclsImpl.zzab(status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzad(status);
        }
    }

    class 2 extends LoadNotifyAclImpl {
        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzj(this);
        }
    }

    private static abstract class UpdateNotifyAclImpl extends BaseGamesApiMethodImpl<Status> {
        public Status zzb(Status status) {
            return status;
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    class 3 extends UpdateNotifyAclImpl {
        final /* synthetic */ String zzaFV;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zzn(this, this.zzaFV);
        }
    }

    private static LoadAclResult zzab(Status status) {
        return new 1(status);
    }
}
