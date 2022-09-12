package com.training.springstart.service;

public interface AuthLoginService extends AuthService{

    boolean checkEmail();

    boolean checkPassword();

}
