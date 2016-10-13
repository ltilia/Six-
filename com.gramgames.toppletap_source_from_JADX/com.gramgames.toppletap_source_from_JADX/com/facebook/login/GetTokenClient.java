package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;
import com.google.android.gms.drive.ExecutionOptions;

final class GetTokenClient extends PlatformServiceClient {
    GetTokenClient(Context context, String applicationId) {
        super(context, ExecutionOptions.MAX_TRACKING_TAG_STRING_LENGTH, NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REPLY, NativeProtocol.PROTOCOL_VERSION_20121101, applicationId);
    }

    protected void populateRequestBundle(Bundle data) {
    }
}
