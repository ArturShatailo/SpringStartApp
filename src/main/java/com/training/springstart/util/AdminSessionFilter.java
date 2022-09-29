package com.training.springstart.util;

import com.training.springstart.model.dto.ClientAdminDTO;
import org.springframework.beans.factory.ObjectFactory;

import javax.servlet.http.HttpSession;

public interface AdminSessionFilter {

    default boolean checkAdminSession(ObjectFactory<HttpSession> httpSessionFactory) {
        HttpSession session = httpSessionFactory.getObject();
        ClientAdminDTO admin = (ClientAdminDTO) session.getAttribute("admin");
        String adminEmail = (String) session.getAttribute("admin-email");
        return admin != null;
    }

}
