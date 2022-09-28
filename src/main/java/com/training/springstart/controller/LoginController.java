package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.model.dto.ClientLoginDTO;
import com.training.springstart.service.auth.AuthLoginServiceBean;
import com.training.springstart.service.CookieFactory;
import com.training.springstart.service.client.ClientServiceBean;
import com.training.springstart.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/api")
public class LoginController{

    private final ClientMapper clientConverter;

    private final ClientServiceBean clientServiceBean;

    @PostMapping("/client-login")
    public String checkAndLoginClient(@RequestBody @Valid ClientLoginDTO clientLoginDTO, /*@RequestParam String email, @RequestParam String password,*/ HttpServletResponse response) {
        Client client = clientConverter.toObject(clientLoginDTO);
        return clientServiceBean.loginClient(client);
    }


}
