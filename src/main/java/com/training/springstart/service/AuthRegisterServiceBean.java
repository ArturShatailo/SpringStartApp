package com.training.springstart.service;

public class AuthRegisterServiceBean implements AuthRegisterService{

    private final String name;

    private final String surname;

    private final String email;

    private final String phone_number;

    private final String password;

    private String request;

    public String getRequest() {
        return request;
    }


    public AuthRegisterServiceBean(String name, String surname, String email, String phone_number, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    @Override
    public boolean startCheck() {
        if (password.trim().equals("")
                || email.trim().equals("")
                || name.trim().equals("")
                || surname.trim().equals("")
                || phone_number.trim().equals("")) {
            request = "Empty fields are not allowed";
            return false;
        }
        return checkEmail() && checkPassword() && checkPhoneNumber();
    }

    @Override
    public boolean checkEmail() {
        if (checkTextField(email) || email.contains("@")) {
            String e = email.substring(email.indexOf("@") + 1);
            if (!e.contains(".")) {
                request = "Email field is incorrect";
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkPassword() {
        if (checkTextField(password)) {
            if (password.length() < 6) {
                request = "Password field is too short";
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkPhoneNumber() {
        if (checkTextField(phone_number)) {
            if (phone_number.length() < 11) {
                request = "Phone field is incorrect";
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean checkTextField(String fieldText) {
        String a = fieldText.trim();
        return !a.equals("");
    }
}
