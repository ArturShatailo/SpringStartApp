package com.training.springstart.service.client;

import com.training.springstart.model.Client;

import java.util.List;

public interface GetClientByValueService {

    Client getByEmail(String email);

    List<Client> getAllNotDeleted();

}
