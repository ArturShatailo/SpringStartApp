package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.model.dto.ClientChangePassDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface UpdateDataService {

    ClientAreaViewDTO updateByEmail(String email, String name, String surname, String phone_number);

    ClientChangePassDTO updatePasswordByEmail(String email, String password);

}
