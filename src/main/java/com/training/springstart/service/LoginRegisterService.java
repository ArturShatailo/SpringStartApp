package com.training.springstart.service;

import com.training.springstart.model.ClientDTO;
import com.training.springstart.model.Client;

public interface LoginRegisterService {

    ClientDTO loginClient(String email, String password);

    Client getByEmail(String email);

    Client registerClient(Client client);

}
