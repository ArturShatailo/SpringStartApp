package com.training.springstart.service.admin;

import com.training.springstart.model.ClientAdmin;
import com.training.springstart.repository.AdminRepository;
import com.training.springstart.service.auth.AuthLoginServiceBean;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class AdminServiceBean implements LoginAdminService {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    private final AdminRepository adminRepository;

    @Override
    public String loginAdmin(ClientAdmin admin) {

        if ( admin == null ) return unsuccessfullyLoggedIn("Wrong email or password");

        AuthLoginServiceBean b = new AuthLoginServiceBean(admin.getEmail(), admin.getPassword());

        if (!b.startCheck()) return unsuccessfullyLoggedIn(b.getRequest());

        ClientAdmin adminEntity = getByEmail(admin.getEmail());

        if (!compareAdmin(admin, adminEntity))
            return unsuccessfullyLoggedIn("Wrong email or password");

        return successfullyLoggedIn(adminEntity, "Successfully logged in");
    }

    public boolean compareAdmin(ClientAdmin admin, ClientAdmin adminEntity) {
        return (admin != null && adminEntity != null)
                && admin.getPassword().equals(adminEntity.getPassword());
    }

    public ClientAdmin getByEmail(String email) {
        return adminRepository.findAdminByEmail(email).orElse(null);
        /*.orElseThrow(() -> new EntityNotFoundException("Client not found with email = " + email));*/
    }

    private String unsuccessfullyLoggedIn(String m) {
        System.err.println(m);
        return "redirect:/admin/admin-login";
    }

    private String successfullyLoggedIn(ClientAdmin c, String m) {
        HttpSession session = httpSessionFactory.getObject();
        session.setAttribute("admin", c);
        session.setAttribute("admin-email", c.getEmail());

        System.err.println(m);
        return "redirect:/admin/admin-area";
    }

}
