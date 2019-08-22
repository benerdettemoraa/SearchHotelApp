package com.example.searchhotelapp.api;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("type")
    private String type;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("access_token")
    private String accessToken;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
