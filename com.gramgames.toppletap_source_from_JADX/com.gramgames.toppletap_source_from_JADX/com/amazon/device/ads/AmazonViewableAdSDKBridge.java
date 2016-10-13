package com.amazon.device.ads;

import com.amazon.device.ads.JavascriptInteractor.Executor;
import com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import org.json.JSONObject;

class AmazonViewableAdSDKBridge implements AdSDKBridge {
    private static final String BRIDGE_NAME = "viewableAdSDKBridge";
    private static final String INSTRUMENTATION_JAVASCRIPT_API;
    private static final String JAVASCRIPT_API;
    private static final String LOGTAG;
    private final AdControlAccessor adControlAccessor;
    private final JavascriptInteractor javascriptInteractor;
    private final MobileAdsLogger logger;
    private SDKEventListener sdkEventListener;
    private final Settings settings;

    private static class AddObserversToViewTree extends JavascriptMethodExecutor {
        private static final String name = "AddObserversToViewTree";
        private final AmazonViewableAdSDKBridge bridge;

        public AddObserversToViewTree(AmazonViewableAdSDKBridge bridge) {
            super(name);
            this.bridge = bridge;
        }

        protected JSONObject execute(JSONObject parameters) {
            this.bridge.registerViewabilityInterest();
            return null;
        }
    }

    private static class GetInstrumentationURL extends JavascriptMethodExecutor {
        private static final String name = "GetInstrumentationURL";
        private final AmazonViewableAdSDKBridge bridge;

        public GetInstrumentationURL(AmazonViewableAdSDKBridge bridge) {
            super(name);
            this.bridge = bridge;
        }

        protected JSONObject execute(JSONObject parameters) {
            JSONObject json = new JSONObject();
            JSONUtils.put(json, "instrumentationPixelUrl", this.bridge.getInstrumentationURL());
            return json;
        }
    }

    private static class RemoveObserversFromViewTree extends JavascriptMethodExecutor {
        private static final String name = "RemoveObserversFromViewTree";
        private final AmazonViewableAdSDKBridge bridge;

        public RemoveObserversFromViewTree(AmazonViewableAdSDKBridge bridge) {
            super(name);
            this.bridge = bridge;
        }

        protected JSONObject execute(JSONObject parameters) {
            this.bridge.deregisterViewabilityInterest();
            return null;
        }
    }

    private static class SDKVersionJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetSDKVersion";
        private final AmazonViewableAdSDKBridge bridge;

        protected SDKVersionJSIF(AmazonViewableAdSDKBridge bridge) {
            super(name);
            this.bridge = bridge;
        }

        public JSONObject execute(JSONObject parameters) {
            JSONObject json = new JSONObject();
            JSONUtils.put(json, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDKVERSION_KEY, this.bridge.getSDKVersion());
            return json;
        }
    }

    static {
        LOGTAG = AmazonViewableAdSDKBridge.class.getSimpleName();
        JAVASCRIPT_API = "(function (window, console) {\n    var is_array = function (obj) {\n        return Object.prototype.toString.call(obj) === '[object Array]';\n    },\n    addViewTreeObservers = function(){\n       viewableAdSDKBridge." + JavascriptInteractor.getExecutorMethodName() + "(\"AddObserversToViewTree\", null);\n" + "    },\n" + "    removeViewTreeObservers = function(){\n" + "       viewableAdSDKBridge." + JavascriptInteractor.getExecutorMethodName() + "(\"RemoveObserversFromViewTree\", null);\n" + "    },\n" + "    forEach = function (array, fn) {\n" + "        var i;\n" + "        for (i = 0; i < array.length; i++) {\n" + "            if (i in array) {\n" + "                fn.call(null, array[i], i);\n" + "            }\n" + "        }\n" + "    },\n" + "    listeners = [],\n" + "    version = 1.1,\n" + "    invokeListeners = function(event, args) {\n" + "        var eventListeners = listeners[event] || [];\n" + "        // fire all the listeners\n" + "        forEach(eventListeners, function(listener){\n" + "            try {\n" + "                listener.apply(null, args);\n" + "            }catch(e){\n" + "                debug(\"Error executing \" + event + \" listener\");\n" + "                debug(e);\n" + "            }\n" + "        });\n" + "    }\n" + "    debug = function(msg) {\n" + "        console.log(\"SDK JS API log: \" + msg);\n" + "    },\n" + "    viewabilityChangeEvent = function(viewable) {\n" + "        debug(\"viewableChange: \" + viewable);\n" + "        var jsonObject = JSON.parse(viewable);\n" + "        var args = [jsonObject];\n" + "        invokeListeners(\"Viewability\", args);\n" + "    }, \n" + "    window.viewableBridge = {\n" + "       viewabilityChange : viewabilityChangeEvent\n" + "    },\n" + "    /* we can add new event properties in future */  \n" + "    window.Event = {\n" + "            Viewability: 'Viewability'\n" + "    },\n" + "    // Define the amazonmobileadsviewablebridge object\n" + "    window.amazonmobileadsviewablebridge = {\n" + "            // Command Flow\n" + "            addEventListener : function(event, listener){\n" + "                var eventListeners = listeners[event] || [],\n" + "                alreadyRegistered = false;\n" + "                \n" + "                //verify the event is one that will actually occur/ verfiy it's supported by SDK\n" + "                if (!Event.hasOwnProperty(event)){\n" + "                    return;\n" + "                }\n" + "                \n" + "                //register first set of listeners for this event\n" + "                if (!is_array(listeners[event])) {\n" + "                    listeners[event] = eventListeners;\n" + "                }\n" + "                \n" + "                forEach(eventListeners, function(l){ \n" + "                    // Listener already registered, so no need to add it.\n" + "                        if (listener === l){\n" + "                            alreadyRegistered = true;\n" + "                        }\n" + "                    }\n" + "                );\n" + "                if (!alreadyRegistered){\n" + "                    listeners[event].push(listener);\n" + "                    if (event = Event.Viewability){ \n" + "                       addViewTreeObservers();  \n" + "                   } \n" + "                }\n" + "            },\n" + "            removeEventListener : function(event, listener){\n" + "                if (listeners.hasOwnProperty(event)) {\n" + "                    var eventListeners = listeners[event];\n" + "                    if (eventListeners) {\n" + "                        var idx = eventListeners.indexOf(listener);\n" + "                        if (idx !== -1) {\n" + "                            eventListeners.splice(idx, 1);\n" + "                            if (event = Event.Viewability){ \n" + "                               removeViewTreeObservers();  \n" + "                             } \n" + "                        }\n" + "                    }\n" + "                }\n" + "            },\n" + "            getSDKVersion: function(){\n" + "               var json = JSON.parse(viewableAdSDKBridge." + JavascriptInteractor.getExecutorMethodName() + "(\"GetSDKVersion\", null));\n" + "               return json.sdkVersion;\n" + "            },\n" + "            getVersion: function(){\n" + "                return version;\n" + "            },\n" + "    };\n" + "})(window, console);\n";
        INSTRUMENTATION_JAVASCRIPT_API = "function(){\n                var json = JSON.parse(viewableAdSDKBridge." + JavascriptInteractor.getExecutorMethodName() + "(\"GetInstrumentationURL\", null));\n" + "                return json.instrumentationPixelUrl;\n" + "            }";
    }

    public AmazonViewableAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor) {
        this(adControlAccessor, javascriptInteractor, Settings.getInstance(), new MobileAdsLoggerFactory());
    }

    AmazonViewableAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor, Settings settings, MobileAdsLoggerFactory loggerFactory) {
        this.adControlAccessor = adControlAccessor;
        this.javascriptInteractor = javascriptInteractor;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.settings = settings;
        this.javascriptInteractor.addMethodExecutor(new AddObserversToViewTree(this));
        this.javascriptInteractor.addMethodExecutor(new RemoveObserversFromViewTree(this));
        this.javascriptInteractor.addMethodExecutor(new GetInstrumentationURL(this));
        this.javascriptInteractor.addMethodExecutor(new SDKVersionJSIF(this));
    }

    public boolean hasNativeExecution() {
        return true;
    }

    public Executor getJavascriptInteractorExecutor() {
        return this.javascriptInteractor.getExecutor();
    }

    public String getName() {
        return BRIDGE_NAME;
    }

    public String getJavascript() {
        return appendViewabilityJavascript();
    }

    private String getViewabilityJavascript() {
        String javascript = this.settings.getString("viewableJSSettingsNameAmazonAdSDK", null);
        if (javascript == null) {
            this.logger.w("Viewability Javascript is null");
            return BuildConfig.FLAVOR;
        }
        return String.format(javascript, new Object[]{INSTRUMENTATION_JAVASCRIPT_API, this.adControlAccessor.getSlotID()});
    }

    private String appendViewabilityJavascript() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(JAVASCRIPT_API);
        stringBuffer.append(getViewabilityJavascript());
        return stringBuffer.toString();
    }

    private String getSDKVersion() {
        return Version.getRawSDKVersion();
    }

    public SDKEventListener getSDKEventListener() {
        if (this.sdkEventListener == null) {
            this.sdkEventListener = new AmazonAdSDKViewableEventListener();
        }
        return this.sdkEventListener;
    }

    private void registerViewabilityInterest() {
        this.adControlAccessor.registerViewabilityInterest();
    }

    private void deregisterViewabilityInterest() {
        this.adControlAccessor.deregisterViewabilityInterest();
    }

    private String getInstrumentationURL() {
        return this.adControlAccessor.getInstrumentationPixelUrl();
    }
}
