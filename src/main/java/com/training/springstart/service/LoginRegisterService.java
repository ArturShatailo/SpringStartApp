package com.training.springstart.service;

import com.training.springstart.entity.Client;

public interface LoginRegisterService {

    Client loginClient(String email, String password);

    Client getByEmail(String email);

}
