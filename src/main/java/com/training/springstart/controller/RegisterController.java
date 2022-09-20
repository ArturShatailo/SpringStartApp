package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.service.*;
import com.training.springstart.service.auth.AuthRegisterServiceBean;
import com.training.springstart.service.client.LoginRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/api")
public class RegisterController implements CookieFactory {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    private final LoginRegisterService loginRegisterService;


    @PostMapping("/client-register")
    //@ResponseStatus(HttpStatus.CREATED)
    public String saveClient(Client client, @RequestParam String passwordRepeat, HttpServletResponse response) {

        AuthRegisterServiceBean a =
                new AuthRegisterServiceBean(client.getName(), client.getSurname(), client.getEmail(), client.getPhone_number(), client.getPassword());
        if (!a.startCheck()) return unsuccessfullyRegister(a.getRequest(), response);

        if (!client.getPassword().equals(passwordRepeat))
            return unsuccessfullyRegister("Password id not the same as repeated password", response);

        Client c = loginRegisterService.registerClient(client);

        if (c == null)
            return unsuccessfullyRegister("Unable to register a new account with this email", response);

        return successfullyRegistered(response);
    }

    private String unsuccessfullyRegister(String m, HttpServletResponse response) {
        setCookie(response, "errorMessage", m, 5);
        return "redirect:/registration";
    }

    private String successfullyRegistered(HttpServletResponse response) {
        setCookie(response, "successfulMessage", "Successfully registered", 5);
        return "redirect:/login";
    }

    @Override
    public void setCookie(HttpServletResponse R, String n, String v, int d) {
        Cookie cookie = new Cookie(n, URLEncoder.encode(v, StandardCharsets.UTF_8));
        cookie.setMaxAge(d);
        R.addCookie(cookie);
    }
}
