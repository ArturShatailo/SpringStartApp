package com.training.springstart.service.admin;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAdminDTO;
import com.training.springstart.model.dto.ClientAreaViewDTO;

public interface LoginAdminService {

    ClientAdminDTO loginAdmin(String email, String password);

}
