package com.training.springstart.controller;


import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.service.auth.AuthLoginServiceBean;
import com.training.springstart.service.CookieFactory;
import com.training.springstart.service.client.LoginRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
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
public class AuthController implements CookieFactory {

    ObjectFactory<HttpSession> httpSessionFactory;

    private final LoginRegisterService loginRegisterService;

    /*@PostMapping("/client-login")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO checkClient(@RequestParam String email, @RequestParam String password) {
        return loginRegisterService.loginClient(email, password);
    }*/

    @PostMapping("/client-login")
    public String checkClient(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        AuthLoginServiceBean b = new AuthLoginServiceBean(email, password);
        ClientAreaViewDTO c = loginRegisterService.loginClient(email, password);

        if ( c == null ) return unsuccessfullyLoggedIn("Wrong email or password", response);

        return b.startCheck()
                ? successfullyLoggedIn(c, response)
                : unsuccessfullyLoggedIn(b.getRequest(), response);
    }

    @Override
    public void setCookie(HttpServletResponse R, String n, String v, int d) {
        Cookie cookie = new Cookie(n, URLEncoder.encode(v, StandardCharsets.UTF_8));
        cookie.setMaxAge(d);
        R.addCookie(cookie);
    }

    private String unsuccessfullyLoggedIn(String m, HttpServletResponse response) {
        setCookie(response, "errorMessage", m, 5);
        return "redirect:/login";
    }

    private String successfullyLoggedIn(ClientAreaViewDTO c, HttpServletResponse response) {
        HttpSession session = httpSessionFactory.getObject();
        session.setAttribute("client", c);
        session.setAttribute("email", c.getEmail());
        setCookie(response, "successfulMessage", "Successfully logged in", 5);
        return "redirect:/personal-area";
    }


}
