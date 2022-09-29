package com.training.springstart.service.auth;

import com.training.springstart.service.auth.AuthService;

public interface AuthLoginService extends AuthService {

    boolean checkEmail();

    boolean checkPassword();

}
