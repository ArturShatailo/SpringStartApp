package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;

public interface LoginRegisterService {

    ClientAreaViewDTO loginClient(String email, String password);

    Client registerClient(Client client);

}
