package com.training.springstart.util;

import com.training.springstart.model.ClientAdmin;
import com.training.springstart.model.dto.clientDTO.ClientAdminDTO;
import com.training.springstart.util.mapper.ClientMapper;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public interface AdminSessionFilter {

    default boolean checkAdminSession(ObjectFactory<HttpSession> httpSessionFactory, ClientMapper clientMapper) {
        HttpSession session = httpSessionFactory.getObject();
        ClientAdminDTO admin = clientMapper.toClientAdminDTO((ClientAdmin) session.getAttribute("admin"));
        String adminEmail = (String) session.getAttribute("admin-email");
        return admin != null;
    }

}
