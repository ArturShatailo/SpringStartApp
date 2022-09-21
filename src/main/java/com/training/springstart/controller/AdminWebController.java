package com.training.springstart.controller;

import com.training.springstart.model.dto.ClientAdminDTO;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminWebController {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    @RequestMapping(value = "/admin-login")
    public String loginAdminController() {
        return "/admin/admin-login";
    }

    @RequestMapping(value = "/clients")
    public String clientsAdminController() {
        return "/admin/clients";
    }

    @GetMapping(value = "/admin-area")
    public String greetingController(Model model) {

        HttpSession session = httpSessionFactory.getObject();
        ClientAdminDTO admin = (ClientAdminDTO) session.getAttribute("admin");
        String adminEmail = (String) session.getAttribute("admin-email");

        if (admin != null) {
            model.addAttribute("admin", admin);
            return "/admin/admin-area";
        } else
            return "redirect:/admin/admin-login";
    }

}
