package com.training.springstart.service.client;

import com.training.springstart.model.Client;

import java.util.List;

public interface ClientsPhoneService {

    List<Client> findClientsByPhoneCode(String phone_code);

}
