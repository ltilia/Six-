package com.vungle.publisher.display.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AlertDialogFactory {

    class 1 implements OnClickListener {
        final /* synthetic */ a a;
        final /* synthetic */ AlertDialogFactory b;

        1(AlertDialogFactory alertDialogFactory, a aVar) {
            this.b = alertDialogFactory;
            this.a = aVar;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            Logger.d(Logger.AD_TAG, "positive click");
            this.a.a();
        }
    }

    class 2 implements OnClickListener {
        final /* synthetic */ a a;
        final /* synthetic */ AlertDialogFactory b;

        2(AlertDialogFactory alertDialogFactory, a aVar) {
            this.b = alertDialogFactory;
            this.a = aVar;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            Logger.d(Logger.AD_TAG, "negative click");
            this.a.b();
        }
    }

    class 3 implements OnCancelListener {
        final /* synthetic */ a a;
        final /* synthetic */ AlertDialogFactory b;

        3(AlertDialogFactory alertDialogFactory, a aVar) {
            this.b = alertDialogFactory;
            this.a = aVar;
        }

        public final void onCancel(DialogInterface dialogInterface) {
            Logger.d(Logger.AD_TAG, "cancel click");
            this.a.c();
        }
    }

    public interface a {
        void a();

        void b();

        void c();
    }

    @Inject
    AlertDialogFactory() {
    }
}
