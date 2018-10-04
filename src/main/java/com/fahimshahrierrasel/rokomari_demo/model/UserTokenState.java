package com.fahimshahrierrasel.rokomari_demo.model;

public class UserTokenState {
    private String token;
    private String jwt_token;
    private Long expires_in;

    public UserTokenState() {
        this.token = null;
        this.jwt_token = null;
        this.expires_in = null;
    }

    public UserTokenState(String token, String jwt_token, long expires_in) {
        this.token = token;
        this.jwt_token = jwt_token;
        this.expires_in = expires_in;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJwt_token() {
        return jwt_token;
    }

    public void setJwt_token(String jwt_token) {
        this.jwt_token = jwt_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
}
