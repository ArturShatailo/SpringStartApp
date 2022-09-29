package com.training.springstart.controller.implementation;

import com.training.springstart.controller.interfaces.ClientLoggable;
import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientLoginDTO;
import com.training.springstart.service.client.ClientServiceBean;
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
@Tag(name = "Client Login", description = "Client Login API")
public class LoginController implements ClientLoggable {

    private final ClientMapper clientConverter;

    private final ClientServiceBean clientServiceBean;

    @Override
    @PostMapping(value = "/client-login")
    public String checkAndLoginClient(/*@RequestBody*/ @Valid ClientLoginDTO clientLoginDTO /*@RequestParam String email, @RequestParam String password,*/) {
        Client client = clientConverter.toObject(clientLoginDTO);
        return clientServiceBean.loginClient(client);
    }


}
