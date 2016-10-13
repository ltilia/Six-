package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.appcontent.AppContents;
import com.google.android.gms.games.appcontent.AppContents.LoadAppContentResult;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class AppContentsImpl implements AppContents {

    private static abstract class LoadsImpl extends BaseGamesApiMethodImpl<LoadAppContentResult> {

        class 1 implements LoadAppContentResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ LoadsImpl zzaFZ;

            1(LoadsImpl loadsImpl, Status status) {
                this.zzaFZ = loadsImpl;
                this.zzZR = status;
            }

            public Status getStatus() {
                return this.zzZR;
            }

            public void release() {
            }
        }

        public LoadAppContentResult zzae(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzae(status);
        }
    }

    class 1 extends LoadsImpl {
        final /* synthetic */ boolean zzaFO;
        final /* synthetic */ int zzaFW;
        final /* synthetic */ String zzaFX;
        final /* synthetic */ String[] zzaFY;

        protected void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
            gamesClientImpl.zza((zzb) this, this.zzaFW, this.zzaFX, this.zzaFY, this.zzaFO);
        }
    }
}
