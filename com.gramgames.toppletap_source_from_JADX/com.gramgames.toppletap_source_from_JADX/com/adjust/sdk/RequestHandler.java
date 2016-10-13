package com.adjust.sdk;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.Locale;
import org.json.JSONException;

public class RequestHandler extends HandlerThread implements IRequestHandler {
    private InternalHandler internalHandler;
    private ILogger logger;
    private IPackageHandler packageHandler;

    private static final class InternalHandler extends Handler {
        private static final int SEND = 72400;
        private final WeakReference<RequestHandler> requestHandlerReference;

        protected InternalHandler(Looper looper, RequestHandler requestHandler) {
            super(looper);
            this.requestHandlerReference = new WeakReference(requestHandler);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            RequestHandler requestHandler = (RequestHandler) this.requestHandlerReference.get();
            if (requestHandler != null) {
                switch (message.arg1) {
                    case SEND /*72400*/:
                        requestHandler.sendInternal(message.obj);
                    default:
                }
            }
        }
    }

    public RequestHandler(IPackageHandler packageHandler) {
        super(Constants.LOGTAG, 1);
        setDaemon(true);
        start();
        this.logger = AdjustFactory.getLogger();
        this.internalHandler = new InternalHandler(getLooper(), this);
        init(packageHandler);
    }

    public void init(IPackageHandler packageHandler) {
        this.packageHandler = packageHandler;
    }

    public void sendPackage(ActivityPackage pack) {
        Message message = Message.obtain();
        message.arg1 = 72400;
        message.obj = pack;
        this.internalHandler.sendMessage(message);
    }

    private void sendInternal(ActivityPackage activityPackage) {
        try {
            requestFinished(Util.readHttpResponse(Util.createPOSTHttpsURLConnection(Constants.BASE_URL + activityPackage.getPath(), activityPackage.getClientSdk(), activityPackage.getParameters()), activityPackage));
        } catch (UnsupportedEncodingException e) {
            sendNextPackage(activityPackage, "Failed to encode parameters", e);
        } catch (SocketTimeoutException e2) {
            closePackage(activityPackage, "Request timed out", e2);
        } catch (IOException e3) {
            closePackage(activityPackage, "Request failed", e3);
        } catch (Throwable e4) {
            sendNextPackage(activityPackage, "Runtime exception", e4);
        }
    }

    private void requestFinished(ResponseData responseData) throws JSONException {
        if (responseData.jsonResponse == null) {
            this.packageHandler.closeFirstPackage(responseData);
        } else {
            this.packageHandler.sendNextPackage(responseData);
        }
    }

    private void closePackage(ActivityPackage activityPackage, String message, Throwable throwable) {
        String packageMessage = activityPackage.getFailureMessage();
        String reasonString = getReasonString(message, throwable);
        String finalMessage = String.format("%s. (%s) Will retry later", new Object[]{packageMessage, reasonString});
        this.logger.error(finalMessage, new Object[0]);
        ResponseData responseData = ResponseData.buildResponseData(activityPackage);
        responseData.message = finalMessage;
        this.packageHandler.closeFirstPackage(responseData);
    }

    private void sendNextPackage(ActivityPackage activityPackage, String message, Throwable throwable) {
        String failureMessage = activityPackage.getFailureMessage();
        String reasonString = getReasonString(message, throwable);
        String finalMessage = String.format("%s. (%s)", new Object[]{failureMessage, reasonString});
        this.logger.error(finalMessage, new Object[0]);
        ResponseData responseData = ResponseData.buildResponseData(activityPackage);
        responseData.message = finalMessage;
        this.packageHandler.sendNextPackage(responseData);
    }

    private String getReasonString(String message, Throwable throwable) {
        if (throwable != null) {
            return String.format(Locale.US, "%s: %s", new Object[]{message, throwable});
        }
        return String.format(Locale.US, "%s", new Object[]{message});
    }
}
