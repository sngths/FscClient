package com.tianxing.data;

/**
 * Created by tianxing on 16/7/8.
 */
public class LocalConfig {
    public static final String PreferenceConfig = "config";

    private String refresh_token = "";

    private Boolean isFirstUse = true;

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }
}
