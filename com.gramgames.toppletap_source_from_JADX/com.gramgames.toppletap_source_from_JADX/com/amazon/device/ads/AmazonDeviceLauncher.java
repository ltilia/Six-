package com.amazon.device.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.actions.SearchIntents;

class AmazonDeviceLauncher {
    private static final String WINDOWSHOP_PKG = "com.amazon.windowshop";

    AmazonDeviceLauncher() {
    }

    public boolean isWindowshopPresent(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage(WINDOWSHOP_PKG) != null;
    }

    public boolean isInWindowshopApp(Context context) {
        return context.getPackageName().equals(WINDOWSHOP_PKG);
    }

    public void launchWindowshopDetailPage(Context context, String asin) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(WINDOWSHOP_PKG);
        if (intent != null) {
            intent.putExtra("com.amazon.windowshop.refinement.asin", asin);
            context.startActivity(intent);
        }
    }

    public void launchWindowshopSearchPage(Context context, String keyword) {
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setComponent(new ComponentName(WINDOWSHOP_PKG, "com.amazon.windowshop.search.SearchResultsGridActivity"));
        intent.putExtra(SearchIntents.EXTRA_QUERY, keyword);
        try {
            context.startActivity(intent);
        } catch (RuntimeException e) {
        }
    }
}
