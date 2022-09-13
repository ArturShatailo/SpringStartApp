package com.training.springstart.service;

public interface AuthRegisterService extends AuthService{

    boolean checkEmail();

    boolean checkPassword();

    boolean checkPhoneNumber();

}
