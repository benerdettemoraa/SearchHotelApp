package com.example.searchhotelapp.callback;

import com.example.searchhotelapp.api.Token;

public interface AuthTokenCallback {
    void authSuccess(Token token);
    void authFailed(String error);
}
