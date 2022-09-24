package com.training.springstart.controller;

import com.training.springstart.model.dto.ClientAdminDTO;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.util.AdminSessionFilter;
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
public class AdminWebController implements AdminSessionFilter {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    @RequestMapping(value = "/admin-login")
    public String loginAdminController() {
        return "/admin/admin-login";
    }

    @RequestMapping(value = "/clients")
    public String clientsAdminController() {
        return checkAdminSession(httpSessionFactory)
                ? "/admin/clients"
                : "redirect:/admin/admin-login";
    }

    @RequestMapping(value = "/orders")
    public String ordersAdminController() {
        return checkAdminSession(httpSessionFactory)
                ? "/admin/orders"
                : "redirect:/admin/admin-login";
    }

    @GetMapping(value = "/admin-area")
    public String greetingController() {
        return checkAdminSession(httpSessionFactory)
                ? "/admin/admin-area"
                : "redirect:/admin/admin-login";
    }

}
