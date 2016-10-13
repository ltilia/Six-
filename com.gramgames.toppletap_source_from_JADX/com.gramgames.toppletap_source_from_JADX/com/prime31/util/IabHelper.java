package com.prime31.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.prime31.IABConstants;
import gs.gram.mopub.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class IabHelper {
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
    public static final int IABHELPER_BAD_RESPONSE = -1002;
    public static final int IABHELPER_ERROR_BASE = -1000;
    public static final int IABHELPER_INVALID_CONSUMPTION = -1010;
    public static final int IABHELPER_MISSING_TOKEN = -1007;
    public static final int IABHELPER_REMOTE_EXCEPTION = -1001;
    public static final int IABHELPER_SEND_INTENT_FAILED = -1004;
    public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -1009;
    public static final int IABHELPER_UNKNOWN_ERROR = -1008;
    public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -1006;
    public static final int IABHELPER_USER_CANCELLED = -1005;
    public static final int IABHELPER_VERIFICATION_FAILED = -1003;
    public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
    public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
    public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    static final String TAG = "Prime31-IABH";
    public static boolean autoVerifySignatures;
    boolean _asyncInProgress;
    String _asyncOperation;
    Context _context;
    boolean _disposed;
    OnIabPurchaseFinishedListener _purchaseListener;
    String _purchasingItemType;
    int _requestCode;
    IInAppBillingService _service;
    ServiceConnection _serviceConn;
    boolean _setupDone;
    String _signatureBase64;
    boolean _subscriptionsSupported;

    public interface OnIabSetupFinishedListener {
        void onIabSetupFinished(IabResult iabResult);
    }

    public interface QueryInventoryFinishedListener {
        void onQueryInventoryFinished(IabResult iabResult, Inventory inventory);
    }

    public interface OnIabPurchaseFinishedListener {
        void onIabPurchaseCompleteAwaitingVerification(String str, String str2);

        void onIabPurchaseFinished(IabResult iabResult, Purchase purchase);
    }

    public interface OnConsumeFinishedListener {
        void onConsumeFinished(Purchase purchase, IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2);
    }

    class 1 implements ServiceConnection {
        private final /* synthetic */ OnIabSetupFinishedListener val$listener;

        1(OnIabSetupFinishedListener onIabSetupFinishedListener) {
            this.val$listener = onIabSetupFinishedListener;
        }

        public void onServiceDisconnected(ComponentName name) {
            IABConstants.logDebug("Billing service disconnected.");
            IabHelper.this._service = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            if (!IabHelper.this._disposed) {
                IABConstants.logDebug("Billing service connected.");
                IabHelper.this._service = Stub.asInterface(service);
                String packageName = IabHelper.this._context.getPackageName();
                try {
                    IABConstants.logDebug("Checking for in-app billing 3 support.");
                    int response = IabHelper.this._service.isBillingSupported(IabHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, packageName, IabHelper.ITEM_TYPE_INAPP);
                    if (response != 0) {
                        if (this.val$listener != null) {
                            this.val$listener.onIabSetupFinished(new IabResult(response, "Error checking for billing v3 support."));
                        }
                        IabHelper.this._subscriptionsSupported = false;
                        return;
                    }
                    IABConstants.logDebug("In-app billing version 3 supported for " + packageName);
                    response = IabHelper.this._service.isBillingSupported(IabHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, packageName, IabHelper.ITEM_TYPE_SUBS);
                    if (response == 0) {
                        IABConstants.logDebug("Subscriptions AVAILABLE.");
                        IabHelper.this._subscriptionsSupported = true;
                    } else {
                        IABConstants.logDebug("Subscriptions NOT AVAILABLE. Response: " + response);
                    }
                    IabHelper.this._setupDone = true;
                    if (this.val$listener != null) {
                        this.val$listener.onIabSetupFinished(new IabResult(IabHelper.BILLING_RESPONSE_RESULT_OK, "Setup successful."));
                    }
                } catch (RemoteException e) {
                    if (this.val$listener != null) {
                        this.val$listener.onIabSetupFinished(new IabResult(IabHelper.IABHELPER_REMOTE_EXCEPTION, "RemoteException while setting up in-app billing."));
                    }
                    e.printStackTrace();
                }
            }
        }
    }

    class 2 implements Runnable {
        private final /* synthetic */ Handler val$handler;
        private final /* synthetic */ QueryInventoryFinishedListener val$listener;
        private final /* synthetic */ List val$moreSkus;
        private final /* synthetic */ boolean val$querySkuDetails;

        class 1 implements Runnable {
            private final /* synthetic */ Inventory val$inv_f;
            private final /* synthetic */ QueryInventoryFinishedListener val$listener;
            private final /* synthetic */ IabResult val$result_f;

            1(QueryInventoryFinishedListener queryInventoryFinishedListener, IabResult iabResult, Inventory inventory) {
                this.val$listener = queryInventoryFinishedListener;
                this.val$result_f = iabResult;
                this.val$inv_f = inventory;
            }

            public void run() {
                this.val$listener.onQueryInventoryFinished(this.val$result_f, this.val$inv_f);
            }
        }

        2(boolean z, List list, QueryInventoryFinishedListener queryInventoryFinishedListener, Handler handler) {
            this.val$querySkuDetails = z;
            this.val$moreSkus = list;
            this.val$listener = queryInventoryFinishedListener;
            this.val$handler = handler;
        }

        public void run() {
            IabResult result = new IabResult(IabHelper.BILLING_RESPONSE_RESULT_OK, "Inventory refresh successful.");
            Inventory inv = null;
            try {
                inv = IabHelper.this.queryInventory(this.val$querySkuDetails, this.val$moreSkus);
            } catch (IabException ex) {
                result = ex.getResult();
            } catch (Exception ex2) {
                result = new IabResult(IabHelper.BILLING_RESPONSE_RESULT_ERROR, "Exception in queryInventory: " + ex2);
            }
            IabHelper.this.flagEndAsync();
            IabResult result_f = result;
            Inventory inv_f = inv;
            if (!IabHelper.this._disposed && this.val$listener != null) {
                this.val$handler.post(new 1(this.val$listener, result_f, inv_f));
            }
        }
    }

    class 3 implements Runnable {
        private final /* synthetic */ Handler val$handler;
        private final /* synthetic */ OnConsumeMultiFinishedListener val$multiListener;
        private final /* synthetic */ List val$purchases;
        private final /* synthetic */ OnConsumeFinishedListener val$singleListener;

        class 1 implements Runnable {
            private final /* synthetic */ List val$purchases;
            private final /* synthetic */ List val$results;
            private final /* synthetic */ OnConsumeFinishedListener val$singleListener;

            1(OnConsumeFinishedListener onConsumeFinishedListener, List list, List list2) {
                this.val$singleListener = onConsumeFinishedListener;
                this.val$purchases = list;
                this.val$results = list2;
            }

            public void run() {
                this.val$singleListener.onConsumeFinished((Purchase) this.val$purchases.get(IabHelper.BILLING_RESPONSE_RESULT_OK), (IabResult) this.val$results.get(IabHelper.BILLING_RESPONSE_RESULT_OK));
            }
        }

        class 2 implements Runnable {
            private final /* synthetic */ OnConsumeMultiFinishedListener val$multiListener;
            private final /* synthetic */ List val$purchases;
            private final /* synthetic */ List val$results;

            2(OnConsumeMultiFinishedListener onConsumeMultiFinishedListener, List list, List list2) {
                this.val$multiListener = onConsumeMultiFinishedListener;
                this.val$purchases = list;
                this.val$results = list2;
            }

            public void run() {
                this.val$multiListener.onConsumeMultiFinished(this.val$purchases, this.val$results);
            }
        }

        3(List list, OnConsumeFinishedListener onConsumeFinishedListener, Handler handler, OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
            this.val$purchases = list;
            this.val$singleListener = onConsumeFinishedListener;
            this.val$handler = handler;
            this.val$multiListener = onConsumeMultiFinishedListener;
        }

        public void run() {
            List<IabResult> results = new ArrayList();
            for (Purchase purchase : this.val$purchases) {
                try {
                    IabHelper.this.consume(purchase);
                    results.add(new IabResult(IabHelper.BILLING_RESPONSE_RESULT_OK, "Successful consume of sku " + purchase.getSku()));
                } catch (IabException ex) {
                    results.add(ex.getResult());
                }
            }
            IabHelper.this.flagEndAsync();
            if (!(IabHelper.this._disposed || this.val$singleListener == null)) {
                this.val$handler.post(new 1(this.val$singleListener, this.val$purchases, results));
            }
            if (!IabHelper.this._disposed && this.val$multiListener != null) {
                this.val$handler.post(new 2(this.val$multiListener, this.val$purchases, results));
            }
        }
    }

    static {
        autoVerifySignatures = true;
    }

    public IabHelper(Context ctx, String base64PublicKey) {
        this._setupDone = false;
        this._subscriptionsSupported = false;
        this._disposed = false;
        this._asyncInProgress = false;
        this._asyncOperation = BuildConfig.FLAVOR;
        this._signatureBase64 = null;
        this._context = ctx.getApplicationContext();
        this._signatureBase64 = base64PublicKey;
        IABConstants.logDebug("IAB helper created.");
    }

    private Intent getExplicitIapIntent() {
        PackageManager pm = this._context.getPackageManager();
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        List<ResolveInfo> intentServices = pm.queryIntentServices(serviceIntent, BILLING_RESPONSE_RESULT_OK);
        if (intentServices == null || intentServices.isEmpty()) {
            Log.i(TAG, "attempted to locate IAP info and found either 0 or more than 1 result. bailing.");
            return null;
        }
        ResolveInfo serviceInfo = (ResolveInfo) intentServices.get(BILLING_RESPONSE_RESULT_OK);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent iapIntent = new Intent();
        iapIntent.setComponent(component);
        return iapIntent;
    }

    public void startSetup(OnIabSetupFinishedListener listener) {
        checkNotDisposed();
        if (this._setupDone) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        IABConstants.logDebug("Starting in-app billing service");
        this._serviceConn = new 1(listener);
        Intent serviceIntent = getExplicitIapIntent();
        if (serviceIntent != null) {
            this._context.bindService(serviceIntent, this._serviceConn, BILLING_RESPONSE_RESULT_USER_CANCELED);
        } else if (listener != null) {
            listener.onIabSetupFinished(new IabResult(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, "Billing service unavailable on device."));
        }
    }

    public boolean subscriptionsSupported() {
        return this._subscriptionsSupported;
    }

    public void dispose() {
        IABConstants.logDebug("Disposing.");
        try {
            this._setupDone = false;
            if (this._serviceConn != null) {
                IABConstants.logDebug("Unbinding from service.");
                if (this._context != null) {
                    this._context.unbindService(this._serviceConn);
                }
            }
            this._disposed = true;
            this._context = null;
            this._serviceConn = null;
            this._service = null;
            this._purchaseListener = null;
        } catch (Exception e) {
            Log.i(TAG, "Exception disposing: " + e);
        }
    }

    private void checkNotDisposed() {
        if (this._disposed) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener) {
        launchPurchaseFlow(act, sku, requestCode, listener, BuildConfig.FLAVOR);
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) {
        launchPurchaseFlow(act, sku, ITEM_TYPE_INAPP, requestCode, listener, extraData);
    }

    public void launchSubscriptionPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener) {
        launchSubscriptionPurchaseFlow(act, sku, requestCode, listener, BuildConfig.FLAVOR);
    }

    public void launchSubscriptionPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) {
        launchPurchaseFlow(act, sku, ITEM_TYPE_SUBS, requestCode, listener, extraData);
    }

    public boolean launchPurchaseFlow(Activity act, String sku, String itemType, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) {
        checkNotDisposed();
        checkSetupDone("launchPurchaseFlow");
        flagStartAsync("launchPurchaseFlow");
        IabResult result;
        try {
            IABConstants.logDebug("Constructing buy intent for " + sku + ", itemType: " + itemType);
            this._requestCode = requestCode;
            Bundle buyIntentBundle = this._service.getBuyIntent(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, this._context.getPackageName(), sku, itemType, extraData);
            int response = getResponseCodeFromBundle(buyIntentBundle);
            if (response != 0) {
                logError("Unable to buy item, Error response: " + getResponseDesc(response));
                result = new IabResult(response, "Unable to buy item");
                if (listener != null) {
                    listener.onIabPurchaseFinished(result, null);
                }
                flagEndAsync();
                return false;
            }
            PendingIntent pendingIntent = (PendingIntent) buyIntentBundle.getParcelable(RESPONSE_BUY_INTENT);
            IABConstants.logDebug("Launching buy intent for " + sku + ". Request code: " + requestCode);
            this._purchaseListener = listener;
            this._purchasingItemType = itemType;
            act.startIntentSenderForResult(pendingIntent.getIntentSender(), requestCode, new Intent(), Integer.valueOf(BILLING_RESPONSE_RESULT_OK).intValue(), Integer.valueOf(BILLING_RESPONSE_RESULT_OK).intValue(), Integer.valueOf(BILLING_RESPONSE_RESULT_OK).intValue());
            return true;
        } catch (SendIntentException e) {
            logError("SendIntentException while launching purchase flow for sku " + sku);
            e.printStackTrace();
            result = new IabResult(IABHELPER_SEND_INTENT_FAILED, "Failed to send intent.");
            if (listener != null) {
                listener.onIabPurchaseFinished(result, null);
            }
            return false;
        } catch (RemoteException e2) {
            logError("RemoteException while launching purchase flow for sku " + sku);
            e2.printStackTrace();
            result = new IabResult(IABHELPER_REMOTE_EXCEPTION, "Remote exception while starting purchase flow");
            if (listener != null) {
                listener.onIabPurchaseFinished(result, null);
            }
            return false;
        }
    }

    public boolean handleActivityResult(int requestCode, int resultCode, Intent data) {
        JSONException e;
        Object[] objArr = new Object[BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE];
        objArr[BILLING_RESPONSE_RESULT_OK] = Integer.valueOf(requestCode);
        objArr[BILLING_RESPONSE_RESULT_USER_CANCELED] = Integer.valueOf(resultCode);
        objArr[2] = data;
        IABConstants.logEntering(getClass().getSimpleName(), "handleActivityResult", objArr);
        IABConstants.logDebug("current _requestCode: " + this._requestCode);
        if (requestCode != this._requestCode) {
            return false;
        }
        checkNotDisposed();
        checkSetupDone("handleActivityResult");
        flagEndAsync();
        IabResult result;
        if (data == null) {
            logError("Null data in IAB activity result.");
            result = new IabResult(IABHELPER_BAD_RESPONSE, "Null data in IAB result");
            if (this._purchaseListener != null) {
                IABConstants.logDebug("calling purchaseListener.onIabPurchaseFinished");
                this._purchaseListener.onIabPurchaseFinished(result, null);
            }
            return true;
        }
        int responseCode = getResponseCodeFromIntent(data);
        String purchaseData = data.getStringExtra(RESPONSE_INAPP_PURCHASE_DATA);
        String dataSignature = data.getStringExtra(RESPONSE_INAPP_SIGNATURE);
        if (resultCode == -1 && responseCode == 0) {
            IABConstants.logDebug("Successful resultcode from purchase activity.");
            IABConstants.logDebug("Purchase data: " + purchaseData);
            IABConstants.logDebug("Data signature: " + dataSignature);
            IABConstants.logDebug("Extras: " + data.getExtras());
            IABConstants.logDebug("Expected item type: " + this._purchasingItemType);
            if (purchaseData == null || dataSignature == null) {
                logError("BUG: either purchaseData or dataSignature is null.");
                IABConstants.logDebug("Extras: " + data.getExtras().toString());
                result = new IabResult(IABHELPER_UNKNOWN_ERROR, "IAB returned null purchaseData or dataSignature");
                if (this._purchaseListener != null) {
                    this._purchaseListener.onIabPurchaseFinished(result, null);
                }
                return true;
            }
            try {
                Purchase purchase = new Purchase(this._purchasingItemType, purchaseData, dataSignature);
                try {
                    String sku = purchase.getSku();
                    if (this._purchaseListener != null) {
                        this._purchaseListener.onIabPurchaseCompleteAwaitingVerification(purchaseData, dataSignature);
                    }
                    if (!autoVerifySignatures || purchase.isTestSku() || Security.verifyPurchase(this._signatureBase64, purchaseData, dataSignature)) {
                        IABConstants.logDebug("Purchase signature successfully verified.");
                        if (this._purchaseListener != null) {
                            this._purchaseListener.onIabPurchaseFinished(new IabResult(BILLING_RESPONSE_RESULT_OK, "Success"), purchase);
                        }
                    } else {
                        logError("Purchase signature verification FAILED for sku " + sku);
                        result = new IabResult(IABHELPER_VERIFICATION_FAILED, "Signature verification failed for sku " + sku);
                        if (this._purchaseListener != null) {
                            this._purchaseListener.onIabPurchaseFinished(result, purchase);
                        }
                        return true;
                    }
                } catch (JSONException e2) {
                    e = e2;
                    Purchase purchase2 = purchase;
                    logError("Failed to parse purchase data.");
                    e.printStackTrace();
                    result = new IabResult(IABHELPER_BAD_RESPONSE, "Failed to parse purchase data.");
                    if (this._purchaseListener != null) {
                        this._purchaseListener.onIabPurchaseFinished(result, null);
                    }
                    return true;
                }
            } catch (JSONException e3) {
                e = e3;
                logError("Failed to parse purchase data.");
                e.printStackTrace();
                result = new IabResult(IABHELPER_BAD_RESPONSE, "Failed to parse purchase data.");
                if (this._purchaseListener != null) {
                    this._purchaseListener.onIabPurchaseFinished(result, null);
                }
                return true;
            }
        } else if (resultCode == -1) {
            IABConstants.logDebug("Result code was OK but in-app billing response was not OK: " + getResponseDesc(responseCode));
            if (this._purchaseListener != null) {
                this._purchaseListener.onIabPurchaseFinished(new IabResult(responseCode, "Problem purchashing item."), null);
            }
        } else if (resultCode == 0) {
            IABConstants.logDebug("Purchase canceled - Response: " + getResponseDesc(responseCode));
            result = new IabResult(IABHELPER_USER_CANCELLED, "User canceled.");
            if (this._purchaseListener != null) {
                this._purchaseListener.onIabPurchaseFinished(result, null);
            }
        } else {
            logError("Purchase failed. Result code: " + Integer.toString(resultCode) + ". Response: " + getResponseDesc(responseCode));
            result = new IabResult(IABHELPER_UNKNOWN_PURCHASE_RESPONSE, "Unknown purchase response.");
            if (this._purchaseListener != null) {
                this._purchaseListener.onIabPurchaseFinished(result, null);
            }
        }
        return true;
    }

    public Inventory queryInventory(boolean querySkuDetails, List<String> moreSkus) throws IabException {
        return queryInventory(querySkuDetails, moreSkus, null);
    }

    public Inventory queryInventory(boolean querySkuDetails, List<String> moreItemSkus, List<String> list) throws IabException {
        checkNotDisposed();
        checkSetupDone("queryInventory");
        try {
            Inventory inv = new Inventory();
            int r = queryPurchases(inv, ITEM_TYPE_INAPP);
            if (r != 0) {
                throw new IabException(r, "Error refreshing inventory (querying owned items).");
            }
            if (querySkuDetails) {
                r = querySkuDetails(ITEM_TYPE_INAPP, inv, moreItemSkus);
                if (r != 0) {
                    throw new IabException(r, "Error refreshing inventory (querying prices of items).");
                }
            }
            if (this._subscriptionsSupported) {
                r = queryPurchases(inv, ITEM_TYPE_SUBS);
                if (r != 0) {
                    throw new IabException(r, "Error refreshing inventory (querying owned subscriptions).");
                } else if (querySkuDetails) {
                    r = querySkuDetails(ITEM_TYPE_SUBS, inv, moreItemSkus);
                    if (r != 0) {
                        throw new IabException(r, "Error refreshing inventory (querying prices of subscriptions).");
                    }
                }
            }
            return inv;
        } catch (RemoteException e) {
            throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while refreshing inventory.", e);
        } catch (JSONException e2) {
            throw new IabException(IABHELPER_BAD_RESPONSE, "Error parsing JSON response while refreshing inventory.", e2);
        }
    }

    public void queryInventoryAsync(boolean querySkuDetails, List<String> moreSkus, QueryInventoryFinishedListener listener) {
        Handler handler = new Handler();
        checkNotDisposed();
        checkSetupDone("queryInventory");
        flagStartAsync("refresh inventory");
        new Thread(new 2(querySkuDetails, moreSkus, listener, handler)).start();
    }

    public void queryInventoryAsync(QueryInventoryFinishedListener listener) {
        queryInventoryAsync(true, null, listener);
    }

    public void queryInventoryAsync(boolean querySkuDetails, QueryInventoryFinishedListener listener) {
        queryInventoryAsync(querySkuDetails, null, listener);
    }

    void consume(Purchase itemInfo) throws IabException {
        checkNotDisposed();
        checkSetupDone("consume");
        if (itemInfo._itemType.equals(ITEM_TYPE_INAPP)) {
            try {
                String token = itemInfo.getToken();
                String sku = itemInfo.getSku();
                if (token == null || token.equals(BuildConfig.FLAVOR)) {
                    logError("Can't consume " + sku + ". No token.");
                    throw new IabException((int) IABHELPER_MISSING_TOKEN, "PurchaseInfo is missing token for sku: " + sku + " " + itemInfo);
                }
                IABConstants.logDebug("Consuming sku: " + sku + ", token: " + token);
                int response = this._service.consumePurchase(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, this._context.getPackageName(), token);
                if (response == 0) {
                    IABConstants.logDebug("Successfully consumed sku: " + sku);
                    return;
                } else {
                    IABConstants.logDebug("Error consuming consuming sku " + sku + ". " + getResponseDesc(response));
                    throw new IabException(response, "Error consuming sku " + sku);
                }
            } catch (RemoteException e) {
                throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while consuming. PurchaseInfo: " + itemInfo, e);
            }
        }
        throw new IabException((int) IABHELPER_INVALID_CONSUMPTION, "Items of type '" + itemInfo._itemType + "' can't be consumed.");
    }

    public void consumeAsync(Purchase purchase, OnConsumeFinishedListener listener) {
        checkNotDisposed();
        checkSetupDone("consume");
        List<Purchase> purchases = new ArrayList();
        purchases.add(purchase);
        consumeAsyncInternal(purchases, listener, null);
    }

    public void consumeAsync(List<Purchase> purchases, OnConsumeMultiFinishedListener listener) {
        checkNotDisposed();
        checkSetupDone("consume");
        consumeAsyncInternal(purchases, null, listener);
    }

    public static String getResponseDesc(int code) {
        String[] iab_msgs = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] iabhelper_msgs = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (code <= IABHELPER_ERROR_BASE) {
            int index = -1000 - code;
            if (index < 0 || index >= iabhelper_msgs.length) {
                return String.valueOf(code) + ":Unknown IAB Helper Error";
            }
            return iabhelper_msgs[index];
        } else if (code < 0 || code >= iab_msgs.length) {
            return String.valueOf(code) + ":Unknown";
        } else {
            return iab_msgs[code];
        }
    }

    void checkSetupDone(String operation) {
        if (!this._setupDone) {
            logError("Illegal state for operation (" + operation + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + operation);
        }
    }

    int getResponseCodeFromBundle(Bundle b) {
        Object o = b.get(RESPONSE_CODE);
        if (o == null) {
            IABConstants.logDebug("Bundle with null response code, assuming OK (known issue)");
            return BILLING_RESPONSE_RESULT_OK;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            logError("Unexpected type for bundle response code.");
            logError(o.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + o.getClass().getName());
        }
    }

    int getResponseCodeFromIntent(Intent i) {
        Object o = i.getExtras().get(RESPONSE_CODE);
        if (o == null) {
            logError("Intent with no response code, assuming OK (known issue)");
            return BILLING_RESPONSE_RESULT_OK;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            logError("Unexpected type for intent response code.");
            logError(o.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + o.getClass().getName());
        }
    }

    void flagStartAsync(String operation) {
        if (this._asyncInProgress) {
            throw new IllegalStateException("Can't start async operation (" + operation + ") because another async operation(" + this._asyncOperation + ") is in progress.");
        }
        this._asyncOperation = operation;
        this._asyncInProgress = true;
        IABConstants.logDebug("Starting async operation: " + operation);
    }

    void flagEndAsync() {
        IABConstants.logEntering(getClass().getSimpleName(), "flagEndAsync");
        IABConstants.logDebug("Ending async operation: " + this._asyncOperation);
        this._asyncOperation = BuildConfig.FLAVOR;
        this._asyncInProgress = false;
    }

    int queryPurchases(Inventory inv, String itemType) throws JSONException, RemoteException {
        int i;
        IABConstants.logDebug("Querying owned items...");
        IABConstants.logDebug("Package name: " + this._context.getPackageName());
        boolean verificationFailed = false;
        String continueToken = null;
        do {
            IABConstants.logDebug("Calling getPurchases with continuation token: " + continueToken);
            Bundle ownedItems = this._service.getPurchases(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, this._context.getPackageName(), itemType, continueToken);
            int response = getResponseCodeFromBundle(ownedItems);
            IABConstants.logDebug("Owned items response: " + String.valueOf(response));
            if (response != 0) {
                IABConstants.logDebug("getPurchases() failed: " + getResponseDesc(response));
                return response;
            }
            if (ownedItems.containsKey(RESPONSE_INAPP_ITEM_LIST)) {
                if (ownedItems.containsKey(RESPONSE_INAPP_PURCHASE_DATA_LIST)) {
                    if (ownedItems.containsKey(RESPONSE_INAPP_SIGNATURE_LIST)) {
                        ArrayList<String> ownedSkus = ownedItems.getStringArrayList(RESPONSE_INAPP_ITEM_LIST);
                        ArrayList<String> purchaseDataList = ownedItems.getStringArrayList(RESPONSE_INAPP_PURCHASE_DATA_LIST);
                        ArrayList<String> signatureList = ownedItems.getStringArrayList(RESPONSE_INAPP_SIGNATURE_LIST);
                        for (int i2 = BILLING_RESPONSE_RESULT_OK; i2 < purchaseDataList.size(); i2 += BILLING_RESPONSE_RESULT_USER_CANCELED) {
                            String purchaseData = (String) purchaseDataList.get(i2);
                            String signature = (String) signatureList.get(i2);
                            String sku = (String) ownedSkus.get(i2);
                            boolean purchaseIsVerified = true;
                            if (autoVerifySignatures) {
                                IABConstants.logDebug("verified sku: " + sku);
                                purchaseIsVerified = Security.verifyPurchase(this._signatureBase64, purchaseData, signature);
                            }
                            Purchase purchase = new Purchase(itemType, purchaseData, signature);
                            if (purchase.isTestSku()) {
                                purchaseIsVerified = true;
                                Log.i(TAG, "skipping signature verification because this is a test product");
                            }
                            if (purchaseIsVerified) {
                                if (TextUtils.isEmpty(purchase.getToken())) {
                                    logWarn("BUG: empty/null token!");
                                    IABConstants.logDebug("Purchase data: " + purchaseData);
                                }
                                inv.addPurchase(purchase);
                            } else {
                                logWarn("Purchase signature verification **FAILED**. Not adding item.");
                                IABConstants.logDebug("   Purchase data: " + purchaseData);
                                IABConstants.logDebug("   Signature: " + signature);
                                verificationFailed = true;
                            }
                        }
                        continueToken = ownedItems.getString(INAPP_CONTINUATION_TOKEN);
                        IABConstants.logDebug("Continuation token: " + continueToken);
                    }
                }
            }
            logError("Bundle returned from getPurchases() doesn't contain required fields.");
            return IABHELPER_BAD_RESPONSE;
        } while (!TextUtils.isEmpty(continueToken));
        if (verificationFailed) {
            i = IABHELPER_VERIFICATION_FAILED;
        } else {
            i = BILLING_RESPONSE_RESULT_OK;
        }
        return i;
    }

    int querySkuDetails(String itemType, Inventory inv, List<String> moreSkus) throws RemoteException, JSONException {
        Iterator it;
        IABConstants.logDebug("Querying SKU details.");
        ArrayList<String> skuList = new ArrayList();
        skuList.addAll(inv.getAllOwnedSkus());
        if (moreSkus != null) {
            for (String sku : moreSkus) {
                if (!skuList.contains(sku)) {
                    skuList.add(sku);
                }
            }
        }
        if (skuList.size() == 0) {
            IABConstants.logDebug("queryPrices: nothing to do because there are no SKUs.");
            return BILLING_RESPONSE_RESULT_OK;
        }
        ArrayList<ArrayList<String>> packs = new ArrayList();
        int n = skuList.size() / 20;
        int mod = skuList.size() % 20;
        for (int i = BILLING_RESPONSE_RESULT_OK; i < n; i += BILLING_RESPONSE_RESULT_USER_CANCELED) {
            ArrayList<String> tempList = new ArrayList();
            for (String s : skuList.subList(i * 20, (i * 20) + 20)) {
                tempList.add(s);
            }
            packs.add(tempList);
        }
        if (mod != 0) {
            tempList = new ArrayList();
            for (String s2 : skuList.subList(n * 20, (n * 20) + mod)) {
                tempList.add(s2);
            }
            packs.add(tempList);
        }
        it = packs.iterator();
        while (it.hasNext()) {
            ArrayList<String> skuPartList = (ArrayList) it.next();
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList(GET_SKU_DETAILS_ITEM_LIST, skuPartList);
            Bundle skuDetails = this._service.getSkuDetails(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, this._context.getPackageName(), itemType, querySkus);
            if (skuDetails.containsKey(RESPONSE_GET_SKU_DETAILS_LIST)) {
                Iterator it2 = skuDetails.getStringArrayList(RESPONSE_GET_SKU_DETAILS_LIST).iterator();
                while (it2.hasNext()) {
                    SkuDetails d = new SkuDetails(itemType, (String) it2.next());
                    IABConstants.logDebug("Got sku details: " + d);
                    inv.addSkuDetails(d);
                }
            } else {
                int response = getResponseCodeFromBundle(skuDetails);
                if (response != 0) {
                    Log.i(TAG, "getSkuDetails() failed: " + getResponseDesc(response));
                    return response;
                }
                logError("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                return IABHELPER_BAD_RESPONSE;
            }
        }
        return BILLING_RESPONSE_RESULT_OK;
    }

    void consumeAsyncInternal(List<Purchase> purchases, OnConsumeFinishedListener singleListener, OnConsumeMultiFinishedListener multiListener) {
        Handler handler = new Handler();
        flagStartAsync("consume");
        new Thread(new 3(purchases, singleListener, handler, multiListener)).start();
    }

    void logError(String msg) {
        Log.e(TAG, "In-app billing error: " + msg);
    }

    void logWarn(String msg) {
        Log.w(TAG, "In-app billing warning: " + msg);
    }
}
