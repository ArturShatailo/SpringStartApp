package com.training.springstart.controller;

import com.training.springstart.model.dto.ClientAreaViewDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
        ClientAreaViewDTO cd = (ClientAreaViewDTO) session.getAttribute("client");

        if (cd != null) {
            model.addAttribute("client", cd);
            return "personal-area";
        } else
            return "redirect:/login";
    }

}