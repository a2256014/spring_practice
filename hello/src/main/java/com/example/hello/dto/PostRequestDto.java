package com.example.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequestDto {
    private String account;
    private String email;
    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("OTP")
    private String OTP;

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", OTP='" + OTP + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
