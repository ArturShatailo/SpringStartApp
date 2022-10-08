package com.training.springstart.service.client;

import com.training.springstart.model.Client;

public interface LoginRegisterService {

    String loginClient(Client client);

    String registerClient(Client client, String passwordRepeat);

}
