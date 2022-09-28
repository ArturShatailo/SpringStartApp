package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;

public interface LoginRegisterService {

    String loginClient(Client client);

    Client registerClient(Client client);

}
