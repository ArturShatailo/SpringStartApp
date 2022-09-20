package com.training.springstart.service.client;

import com.training.springstart.model.Client;

public interface GetClientByValueService {

    Client getByEmail(String email);

}
