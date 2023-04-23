package com.tule.coin.error;

import org.json.JSONObject;

public enum CoinError {
    SUCCESS("0", "Success"),
    NET_TIMEOUT_ERROR("3000", "connection or read data time out");
    private final String errorCode;
    private final String errorMsg;

    CoinError(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMsg;
    }

    public JSONObject toJsonResult() {
        JSONObject json = new JSONObject();
        json.put("error_code", errorCode);
        json.put("error_msg", errorMsg);
        return json;
    }

    @Override
    public String toString() {
        return toJsonResult().toString();
    }
}
