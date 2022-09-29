package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface UpdateDataService {

    int updateByEmail(Client client);

    void updatePasswordByEmail(Client client);

}
