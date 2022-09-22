package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientsPhoneService {

    List<Client> findClientsByPhoneCode(String phone_code);

    Page<Client> findClientsPageByPhoneCode(String phone_code);

}
