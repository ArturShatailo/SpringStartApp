package com.training.springstart.service;

import lombok.AllArgsConstructor;

public class AuthLoginServiceBean implements AuthLoginService{

    private final String email;

    private final String password;

    private String request;

    public String getRequest() {
        return request;
    }

    public AuthLoginServiceBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean startCheck() {
        return checkEmail() && checkPassword();
    }

    @Override
    public boolean checkEmail() {
        if (!checkTextField(email)) {
            request = "Email field is incorrect";
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword() {
        if (!checkTextField(password)) {
            request = "Password field is incorrect";
            return false;
        }
        return true;
    }


    @Override
    public boolean checkTextField(String fieldText) {
        String a = fieldText.trim();
        return !a.equals("");
    }
}
