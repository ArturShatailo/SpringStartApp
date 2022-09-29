package com.training.springstart.service.admin;

import com.training.springstart.model.Client;
import com.training.springstart.model.ClientAdmin;
import com.training.springstart.model.dto.ClientAdminDTO;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.model.dto.ClientChangePassDTO;
import com.training.springstart.repository.AdminRepository;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.client.ClientsPhoneService;
import com.training.springstart.service.client.GetClientByValueService;
import com.training.springstart.service.client.UpdateDataService;
import com.training.springstart.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class AdminServiceBean implements /*GetClientByValueService,*/ LoginAdminService {

    private final AdminRepository adminRepository;

    private final ClientMapper clientConverter;

    @Override
    public ClientAdminDTO loginAdmin(String email, String password) {
        return comparePassword(email, password);
    }

    public ClientAdminDTO comparePassword(String email, String password) {
        ClientAdmin clientAdmin = getByEmail(email);

        if (clientAdmin != null && clientAdmin.getPassword().equals(password)) {
            return clientConverter.toClientAdminDTO(clientAdmin);
        } else {
            return null;
        }
    }

    public ClientAdmin getByEmail(String email) {
        return adminRepository.findAdminByEmail(email).orElse(null);
        /*.orElseThrow(() -> new EntityNotFoundException("Client not found with email = " + email));*/
    }
}
