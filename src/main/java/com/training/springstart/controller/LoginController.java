package com.training.springstart.controller;
import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientLoginDTO;
import com.training.springstart.service.client.ClientServiceBean;
import com.training.springstart.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController{

    private final ClientMapper clientConverter;

    private final ClientServiceBean clientServiceBean;

    @PostMapping(value = "/client-login")
    public String checkAndLoginClient(@Valid ClientLoginDTO clientLoginDTO /*@RequestParam String email, @RequestParam String password,*/) {
        Client client = clientConverter.toObject(clientLoginDTO);
        return clientServiceBean.loginClient(client);
    }


}
