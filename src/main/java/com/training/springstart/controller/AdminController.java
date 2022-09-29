package com.training.springstart.controller;

import com.training.springstart.model.dto.ClientAdminDTO;
import com.training.springstart.service.CookieFactory;
import com.training.springstart.service.admin.AdminServiceBean;
import com.training.springstart.service.auth.AuthLoginServiceBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/control", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Admin", description = "Admin API")
public class AdminController implements CookieFactory {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    private final AdminServiceBean adminServiceBean;

    @Operation(summary = "This is endpoint to find admin and get it logged in.", description = "Create request to check admin login data.", tags = {"Admin object"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "LOGGED IN. The admin has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified admin request not found."),
            @ApiResponse(responseCode = "409", description = "")})
    @PostMapping("/admin-login")
    public String checkClient(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        AuthLoginServiceBean b = new AuthLoginServiceBean(email, password);
        ClientAdminDTO c = adminServiceBean.loginAdmin(email, password);

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
        return "redirect:/admin/admin-login";
    }

    private String successfullyLoggedIn(ClientAdminDTO c, HttpServletResponse response) {
        HttpSession session = httpSessionFactory.getObject();
        session.setAttribute("admin", c);
        session.setAttribute("admin-email", c.getEmail());

        setCookie(response, "successfulMessage", "Successfully logged in", 5);
        return "redirect:/admin/admin-area";
    }

}
