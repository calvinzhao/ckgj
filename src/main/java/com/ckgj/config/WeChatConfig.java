package com.ckgj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// TODO: use application.properities
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {
    private String wxAppId; // wx70c01952742520c8
    private String wxSecret; // de544884eddf11c62af7d2bf0367d40e
    private String wxToken; // hosme
    private String wxAesKey; // hrL6SlnhZJINCEZDAJlHkxKJdFDvVPhQzibe04F7Aul
    private String redirectUrl;

    public String getRedirectUrl() {

        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getWxAesKey() {

        return wxAesKey;
    }

    public void setWxAesKey(String wxAesKey) {
        this.wxAesKey = wxAesKey;
    }

    public String getWxToken() {
        return wxToken;
    }

    public void setWxToken(String wxToken) {
        this.wxToken = wxToken;
    }

    public String getWxSecret() {
        return wxSecret;
    }

    public void setWxSecret(String wxSecret) {
        this.wxSecret = wxSecret;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }
}
