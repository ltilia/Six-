package com.google.games.bridge;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.api.PendingResult;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class TokenFragment extends Fragment {
    private static final String FRAGMENT_TAG = "gpg.TokenSupport";
    private static final int RC_ACCT = 9002;
    private static final String TAG = "TokenFragment";
    private static String currentPlayerId;
    private static boolean mIsSelecting;
    private static final List<TokenRequest> pendingTokenRequests;
    private static String selectedAccountName;

    class 1 extends AsyncTask<Object, Integer, TokenRequest> {
        final /* synthetic */ String val$accountName;
        final /* synthetic */ Activity val$theActivity;
        final /* synthetic */ TokenRequest val$tokenRequest;

        1(TokenRequest tokenRequest, String str, Activity activity) {
            this.val$tokenRequest = tokenRequest;
            this.val$accountName = str;
            this.val$theActivity = activity;
        }

        protected TokenRequest doInBackground(Object[] params) {
            int statusCode = 0;
            this.val$tokenRequest.setEmail(this.val$accountName);
            if (this.val$tokenRequest.doAccessToken && this.val$accountName != null) {
                String accessScope = "oauth2:https://www.googleapis.com/auth/plus.me";
                try {
                    Log.d(TokenFragment.TAG, "getting accessToken for " + this.val$accountName);
                    this.val$tokenRequest.setAccessToken(GoogleAuthUtil.getToken(this.val$theActivity, this.val$accountName, accessScope));
                } catch (Throwable th) {
                    Log.e(TokenFragment.TAG, "Exception getting access token", th);
                    statusCode = 8;
                }
            }
            if (!this.val$tokenRequest.doIdToken || this.val$accountName == null) {
                if (this.val$tokenRequest.doIdToken) {
                    Log.e(TokenFragment.TAG, "Skipping ID token: email is empty?");
                }
            } else if (this.val$tokenRequest.getScope() == null || this.val$tokenRequest.getScope().isEmpty()) {
                Log.w(TokenFragment.TAG, "Skipping ID token: scope is empty");
                statusCode = 10;
            } else {
                try {
                    Log.d(TokenFragment.TAG, "Getting ID token.  Scope = " + this.val$tokenRequest.getScope() + " email: " + this.val$accountName);
                    this.val$tokenRequest.setIdToken(GoogleAuthUtil.getToken(this.val$theActivity, this.val$accountName, this.val$tokenRequest.getScope()));
                } catch (Throwable th2) {
                    Log.e(TokenFragment.TAG, "Exception getting access token", th2);
                    statusCode = 8;
                }
            }
            Log.d(TokenFragment.TAG, "Done with tokenRequest status: " + statusCode);
            this.val$tokenRequest.setResult(statusCode);
            return this.val$tokenRequest;
        }

        protected void onCancelled() {
            super.onCancelled();
            this.val$tokenRequest.cancel();
        }

        protected void onPostExecute(TokenRequest tokenPendingResult) {
            Log.d(TokenFragment.TAG, "onPostExecute for the token fetch");
            super.onPostExecute(tokenPendingResult);
        }
    }

    private static class TokenRequest {
        private boolean doAccessToken;
        private boolean doEmail;
        private boolean doIdToken;
        private TokenPendingResult pendingResponse;
        private String rationale;
        private String scope;

        public TokenRequest(boolean fetchEmail, boolean fetchAccessToken, boolean fetchIdToken, String scope) {
            this.pendingResponse = new TokenPendingResult();
            this.doEmail = fetchEmail;
            this.doAccessToken = fetchAccessToken;
            this.doIdToken = fetchIdToken;
            this.scope = scope;
        }

        public PendingResult getPendingResponse() {
            return this.pendingResponse;
        }

        public void setResult(int code) {
            this.pendingResponse.setStatus(code);
        }

        public void setEmail(String email) {
            this.pendingResponse.setEmail(email);
        }

        public void cancel() {
            this.pendingResponse.cancel();
        }

        public String getScope() {
            return this.scope;
        }

        public void setAccessToken(String accessToken) {
            this.pendingResponse.setAccessToken(accessToken);
        }

        public void setIdToken(String idToken) {
            this.pendingResponse.setIdToken(idToken);
        }

        public String getEmail() {
            return this.pendingResponse.result.getEmail();
        }

        public String getIdToken() {
            return this.pendingResponse.result.getIdToken();
        }

        public String getAccessToken() {
            return this.pendingResponse.result.getAccessToken();
        }

        public String getRationale() {
            return this.rationale;
        }

        public void setRationale(String rationale) {
            this.rationale = rationale;
        }

        public String toString() {
            return Integer.toHexString(hashCode()) + " (e:" + this.doEmail + " a:" + this.doAccessToken + " i:" + this.doIdToken + ")";
        }
    }

    static {
        pendingTokenRequests = new ArrayList();
        mIsSelecting = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.common.api.PendingResult fetchToken(android.app.Activity r9, java.lang.String r10, java.lang.String r11, boolean r12, boolean r13, boolean r14, java.lang.String r15) {
        /*
        r2 = new com.google.games.bridge.TokenFragment$TokenRequest;
        r2.<init>(r12, r13, r14, r15);
        r2.setRationale(r11);
        r6 = pendingTokenRequests;
        monitor-enter(r6);
        if (r10 == 0) goto L_0x0015;
    L_0x000d:
        r5 = currentPlayerId;	 Catch:{ all -> 0x0083 }
        r5 = r10.equals(r5);	 Catch:{ all -> 0x0083 }
        if (r5 != 0) goto L_0x001a;
    L_0x0015:
        currentPlayerId = r10;	 Catch:{ all -> 0x0083 }
        r5 = 0;
        selectedAccountName = r5;	 Catch:{ all -> 0x0083 }
    L_0x001a:
        r5 = selectedAccountName;	 Catch:{ all -> 0x0083 }
        if (r5 == 0) goto L_0x004d;
    L_0x001e:
        if (r12 == 0) goto L_0x004d;
    L_0x0020:
        if (r13 != 0) goto L_0x004d;
    L_0x0022:
        if (r14 != 0) goto L_0x004d;
    L_0x0024:
        r5 = "TokenFragment";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r7.<init>();	 Catch:{ all -> 0x0083 }
        r8 = "Returning accountName: ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0083 }
        r8 = selectedAccountName;	 Catch:{ all -> 0x0083 }
        r7 = r7.append(r8);	 Catch:{ all -> 0x0083 }
        r7 = r7.toString();	 Catch:{ all -> 0x0083 }
        android.util.Log.i(r5, r7);	 Catch:{ all -> 0x0083 }
        r5 = selectedAccountName;	 Catch:{ all -> 0x0083 }
        r2.setEmail(r5);	 Catch:{ all -> 0x0083 }
        r5 = 0;
        r2.setResult(r5);	 Catch:{ all -> 0x0083 }
        r5 = r2.getPendingResponse();	 Catch:{ all -> 0x0083 }
        monitor-exit(r6);	 Catch:{ all -> 0x0083 }
    L_0x004c:
        return r5;
    L_0x004d:
        r5 = pendingTokenRequests;	 Catch:{ all -> 0x0083 }
        r5.add(r2);	 Catch:{ all -> 0x0083 }
        monitor-exit(r6);	 Catch:{ all -> 0x0083 }
        r5 = r9.getFragmentManager();
        r6 = "gpg.TokenSupport";
        r0 = r5.findFragmentByTag(r6);
        r0 = (com.google.games.bridge.TokenFragment) r0;
        if (r0 != 0) goto L_0x00b5;
    L_0x0061:
        r5 = "TokenFragment";
        r6 = "Creating fragment";
        android.util.Log.d(r5, r6);	 Catch:{ Throwable -> 0x0086 }
        r1 = new com.google.games.bridge.TokenFragment;	 Catch:{ Throwable -> 0x0086 }
        r1.<init>();	 Catch:{ Throwable -> 0x0086 }
        r5 = r9.getFragmentManager();	 Catch:{ Throwable -> 0x00cc }
        r4 = r5.beginTransaction();	 Catch:{ Throwable -> 0x00cc }
        r5 = "gpg.TokenSupport";
        r4.add(r1, r5);	 Catch:{ Throwable -> 0x00cc }
        r4.commit();	 Catch:{ Throwable -> 0x00cc }
        r0 = r1;
    L_0x007e:
        r5 = r2.getPendingResponse();
        goto L_0x004c;
    L_0x0083:
        r5 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0083 }
        throw r5;
    L_0x0086:
        r3 = move-exception;
    L_0x0087:
        r5 = "TokenFragment";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "Cannot launch token fragment:";
        r6 = r6.append(r7);
        r7 = r3.getMessage();
        r6 = r6.append(r7);
        r6 = r6.toString();
        android.util.Log.e(r5, r6, r3);
        r5 = 13;
        r2.setResult(r5);
        r6 = pendingTokenRequests;
        monitor-enter(r6);
        r5 = pendingTokenRequests;	 Catch:{ all -> 0x00b2 }
        r5.remove(r2);	 Catch:{ all -> 0x00b2 }
        monitor-exit(r6);	 Catch:{ all -> 0x00b2 }
        goto L_0x007e;
    L_0x00b2:
        r5 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00b2 }
        throw r5;
    L_0x00b5:
        r6 = pendingTokenRequests;
        monitor-enter(r6);
        r5 = mIsSelecting;	 Catch:{ all -> 0x00c9 }
        if (r5 != 0) goto L_0x00c7;
    L_0x00bc:
        r5 = "TokenFragment";
        r7 = "Fragment exists.. and not selecting calling processRequests";
        android.util.Log.d(r5, r7);	 Catch:{ all -> 0x00c9 }
        r5 = 0;
        r0.processRequests(r5);	 Catch:{ all -> 0x00c9 }
    L_0x00c7:
        monitor-exit(r6);	 Catch:{ all -> 0x00c9 }
        goto L_0x007e;
    L_0x00c9:
        r5 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00c9 }
        throw r5;
    L_0x00cc:
        r3 = move-exception;
        r0 = r1;
        goto L_0x0087;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.games.bridge.TokenFragment.fetchToken(android.app.Activity, java.lang.String, java.lang.String, boolean, boolean, boolean, java.lang.String):com.google.android.gms.common.api.PendingResult");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processRequests(int r14) {
        /*
        r13 = this;
        r4 = 1;
        r6 = 0;
        r1 = 0;
        r11 = 0;
        if (r14 == 0) goto L_0x0025;
    L_0x0006:
        r2 = pendingTokenRequests;
        monitor-enter(r2);
    L_0x0009:
        r1 = pendingTokenRequests;	 Catch:{ all -> 0x0020 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0023;
    L_0x0011:
        r1 = pendingTokenRequests;	 Catch:{ all -> 0x0020 }
        r3 = 0;
        r1 = r1.remove(r3);	 Catch:{ all -> 0x0020 }
        r0 = r1;
        r0 = (com.google.games.bridge.TokenFragment.TokenRequest) r0;	 Catch:{ all -> 0x0020 }
        r11 = r0;
        r11.setResult(r14);	 Catch:{ all -> 0x0020 }
        goto L_0x0009;
    L_0x0020:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0020 }
        throw r1;
    L_0x0023:
        monitor-exit(r2);	 Catch:{ all -> 0x0020 }
    L_0x0024:
        return;
    L_0x0025:
        r3 = pendingTokenRequests;
        monitor-enter(r3);
        r2 = pendingTokenRequests;	 Catch:{ all -> 0x0068 }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x0068 }
        if (r2 != 0) goto L_0x003b;
    L_0x0030:
        r2 = pendingTokenRequests;	 Catch:{ all -> 0x0068 }
        r5 = 0;
        r2 = r2.get(r5);	 Catch:{ all -> 0x0068 }
        r0 = r2;
        r0 = (com.google.games.bridge.TokenFragment.TokenRequest) r0;	 Catch:{ all -> 0x0068 }
        r11 = r0;
    L_0x003b:
        r9 = selectedAccountName;	 Catch:{ all -> 0x0068 }
        monitor-exit(r3);	 Catch:{ all -> 0x0068 }
        if (r11 == 0) goto L_0x0024;
    L_0x0040:
        if (r9 != 0) goto L_0x006e;
    L_0x0042:
        r2 = pendingTokenRequests;
        monitor-enter(r2);
        r3 = 1;
        mIsSelecting = r3;	 Catch:{ all -> 0x006b }
        monitor-exit(r2);	 Catch:{ all -> 0x006b }
        r3 = new java.lang.String[r4];
        r2 = "com.google";
        r3[r6] = r2;
        r5 = r11.getRationale();
        r2 = r1;
        r6 = r1;
        r7 = r1;
        r8 = r1;
        r10 = com.google.android.gms.common.AccountPicker.newChooseAccountIntent(r1, r2, r3, r4, r5, r6, r7, r8);
        r1 = 9002; // 0x232a float:1.2614E-41 double:4.4476E-320;
        r13.startActivityForResult(r10, r1);
    L_0x0060:
        r1 = "TokenFragment";
        r2 = "Done with processRequests!";
        android.util.Log.d(r1, r2);
        goto L_0x0024;
    L_0x0068:
        r1 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0068 }
        throw r1;
    L_0x006b:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x006b }
        throw r1;
    L_0x006e:
        r2 = pendingTokenRequests;
        monitor-enter(r2);
    L_0x0071:
        r1 = pendingTokenRequests;	 Catch:{ Throwable -> 0x008a }
        r1 = r1.isEmpty();	 Catch:{ Throwable -> 0x008a }
        if (r1 != 0) goto L_0x0099;
    L_0x0079:
        r1 = pendingTokenRequests;	 Catch:{ Throwable -> 0x008a }
        r3 = 0;
        r1 = r1.remove(r3);	 Catch:{ Throwable -> 0x008a }
        r0 = r1;
        r0 = (com.google.games.bridge.TokenFragment.TokenRequest) r0;	 Catch:{ Throwable -> 0x008a }
        r11 = r0;
        if (r11 == 0) goto L_0x0071;
    L_0x0086:
        r13.doGetToken(r11, r9);	 Catch:{ Throwable -> 0x008a }
        goto L_0x0071;
    L_0x008a:
        r12 = move-exception;
        if (r11 == 0) goto L_0x0099;
    L_0x008d:
        r1 = "TokenFragment";
        r3 = "Cannot process request";
        android.util.Log.e(r1, r3, r12);	 Catch:{ all -> 0x009b }
        r1 = 13;
        r11.setResult(r1);	 Catch:{ all -> 0x009b }
    L_0x0099:
        monitor-exit(r2);	 Catch:{ all -> 0x009b }
        goto L_0x0060;
    L_0x009b:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x009b }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.games.bridge.TokenFragment.processRequests(int):void");
    }

    private void doGetToken(TokenRequest tokenRequest, String accountName) {
        Activity theActivity = getActivity();
        Log.d(TAG, "Calling doGetToken for e: " + tokenRequest.doEmail + " a:" + tokenRequest.doAccessToken + " i:" + tokenRequest.doIdToken);
        new 1(tokenRequest, accountName, theActivity).execute(new Object[0]);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + requestCode + ": " + resultCode);
        if (requestCode == RC_ACCT) {
            int status = resultCode;
            String accountName = selectedAccountName;
            if (resultCode == -1) {
                status = 0;
                accountName = data.getStringExtra("authAccount");
            } else if (resultCode == 0) {
                status = 16;
            }
            synchronized (pendingTokenRequests) {
                selectedAccountName = accountName;
                mIsSelecting = false;
            }
            processRequests(status);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onResume() {
        Log.d(TAG, "onResume called");
        processRequests(0);
        super.onResume();
    }
}
