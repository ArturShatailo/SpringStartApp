package com.training.springstart.controller.implementation;

import com.training.springstart.controller.interfaces.AdminLoggable;
import com.training.springstart.model.ClientAdmin;
import com.training.springstart.model.dto.ClientAdminDTO;
import com.training.springstart.service.admin.AdminServiceBean;
import com.training.springstart.util.mapper.ClientMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/control", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Admin Login", description = "Admin Login API")
public class AdminLoginController implements AdminLoggable {

    private final ClientMapper clientConverter;

    private final AdminServiceBean adminServiceBean;


    @Override
    @PostMapping("/admin-login")
    public String checkAndLoginAdmin(/*@RequestBody*/ @Valid ClientAdminDTO clientAdminDTO /*@RequestParam String email, @RequestParam String password*/) {
        ClientAdmin admin = clientConverter.toObject(clientAdminDTO);
        return adminServiceBean.loginAdmin(admin);
    }
}
