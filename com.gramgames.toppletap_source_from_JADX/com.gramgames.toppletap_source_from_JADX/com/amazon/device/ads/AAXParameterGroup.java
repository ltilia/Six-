package com.amazon.device.ads;

import org.json.JSONObject;

abstract class AAXParameterGroup {
    public static final UserIdAAXParameterGroup USER_ID;

    static final class UserIdAAXParameterGroup extends AAXParameterGroup {
        private final SISDeviceIdentifierAAXParameter adIdParameter;
        private final DirectedIdAAXParameter directedIdParameter;
        private final AdvertisingIdentifierAAXParameter idfaParameter;
        private final SHA1UDIDAAXParameter sha1udidParameter;

        UserIdAAXParameterGroup() {
            this(AAXParameterGroupParameter.DIRECTED_ID, AAXParameterGroupParameter.ADVERTISING_IDENTIFIER, AAXParameterGroupParameter.SIS_DEVICE_IDENTIFIER, AAXParameterGroupParameter.SHA1_UDID);
        }

        UserIdAAXParameterGroup(DirectedIdAAXParameter directedIdParameter, AdvertisingIdentifierAAXParameter idfaParameter, SISDeviceIdentifierAAXParameter adIdParameter, SHA1UDIDAAXParameter sha1udidParameter) {
            this.directedIdParameter = directedIdParameter;
            this.idfaParameter = idfaParameter;
            this.adIdParameter = adIdParameter;
            this.sha1udidParameter = sha1udidParameter;
        }

        public void evaluate(ParameterData parameterData, JSONObject jsonObject) {
            if (!this.directedIdParameter.evaluate(parameterData, jsonObject)) {
                if (!this.idfaParameter.evaluate(parameterData, jsonObject)) {
                    this.sha1udidParameter.evaluate(parameterData, jsonObject);
                }
                this.adIdParameter.evaluate(parameterData, jsonObject);
            }
        }
    }

    public abstract void evaluate(ParameterData parameterData, JSONObject jSONObject);

    AAXParameterGroup() {
    }

    static {
        USER_ID = new UserIdAAXParameterGroup();
    }
}
