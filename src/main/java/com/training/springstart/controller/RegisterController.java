package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientDateDTO;
import com.training.springstart.service.client.LoginRegisterService;
import com.training.springstart.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController{

    private final ClientMapper clientConverter;

    private final LoginRegisterService loginRegisterService;

    @PostMapping(value = "/client-register")
    //ResponseStatus(HttpStatus.CREATED)
    public String saveClient(@Valid ClientDateDTO clientDateDTO) {
        Client client = clientConverter.toObject(clientDateDTO);
        return loginRegisterService.registerClient(client, clientDateDTO.getPasswordRepeat());
    }

}
