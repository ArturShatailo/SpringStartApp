package com.training.springstart.controller;

import com.training.springstart.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class WebController {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    @RequestMapping(value = "/registration")
    public String registrationController() {
        return "registration";
    }

    @RequestMapping(value = "/login")
    public String loginController() {
        return "login";
    }

    @GetMapping(value = "/personal-area")
    public String greetingController(Model model) {

        HttpSession session = httpSessionFactory.getObject();
        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "personal-area";
        } else
            return "redirect:/login";
    }

}