package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import org.springframework.data.domain.Page;

public interface ClientsSearchValuePageableService {

    Page<Client> findClientsPageByPhoneCode(String phone_code);

    Page<Client> findClientsPageByEmailDomain(Integer page, Integer size, String sort, String email_domain);

}
