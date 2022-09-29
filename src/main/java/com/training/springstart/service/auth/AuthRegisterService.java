package com.training.springstart.service.auth;

public interface AuthRegisterService extends AuthService{

    boolean checkEmail();

    boolean checkPassword();

    boolean checkPhoneNumber();

}
