package com.vungle.publisher.display.view;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum AlertDialogFactory_Factory implements Factory<AlertDialogFactory> {
    ;

    private AlertDialogFactory_Factory(String str) {
    }

    public final AlertDialogFactory get() {
        return new AlertDialogFactory();
    }

    public static Factory<AlertDialogFactory> create() {
        return INSTANCE;
    }
}
