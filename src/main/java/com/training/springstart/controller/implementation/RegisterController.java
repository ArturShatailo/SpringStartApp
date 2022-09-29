package com.training.springstart.controller.implementation;

import com.training.springstart.controller.interfaces.ClientRegistrable;
import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientDateDTO;
import com.training.springstart.service.client.LoginRegisterService;
import com.training.springstart.util.mapper.ClientMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Client Register", description = "Client Register API")
public class RegisterController implements ClientRegistrable {

    private final ClientMapper clientConverter;

    private final LoginRegisterService loginRegisterService;

    @Override
    @PostMapping(value = "/client-register")
    public String saveClient(/*@RequestBody*/ @Valid ClientDateDTO clientDateDTO) {
        Client client = clientConverter.toObject(clientDateDTO);
        return loginRegisterService.registerClient(client, clientDateDTO.getPasswordRepeat());
    }

}
