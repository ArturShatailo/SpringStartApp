package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.model.dto.ClientChangePassDTO;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.client.GetClientByValueService;
import com.training.springstart.service.client.UpdateDataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientsController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;


    private final GetClientByValueService getClientByValueService;

    private final CrudService<Client> crudService;

    private final UpdateDataService updateData;

    @PostMapping("/clients/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(/*@RequestBody*/ Client client, HttpServletResponse response) {
        response.addCookie(new Cookie("successMessage", "value"));
        return crudService.create(client);
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        return crudService.getAll();
    }

    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client getClientById(@PathVariable("id") Integer id) {
        return crudService.getById(id);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client refreshClient(@PathVariable("id") Integer id, @RequestBody Client client) {
        return crudService.updateById(id, client);
    }

    @PostMapping("/clients/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientAreaViewDTO updateClient(@RequestParam String name, @RequestParam String surname, @RequestParam String phone_number, HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        String email = (String) session.getAttribute("email");

        ClientAreaViewDTO c = updateData.updateByEmail(email, name, surname, phone_number);
        if (c != null) session.setAttribute("client", c);

        response.sendRedirect("/personal-area");
        return c;
//        int status = updateData.updateByEmail(client);
//        return "redirect:/personal-area";
    }

    @PostMapping("/clients/updatePassword")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateClientPassword(@RequestParam String newPassword, @RequestParam String newPasswordRepeat, HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        String email = (String) session.getAttribute("email");
        //Client client = getClientByValueService.getByEmail(email);

        updateData.updatePasswordByEmail(email, newPassword);

        response.sendRedirect("/personal-area");
//        int status = updateData.updateByEmail(client);
//        return "redirect:/personal-area";
    }

    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClientById(@PathVariable("id") Integer id) {
        crudService.removeById(id);
    }

    @GetMapping("/get-client")
    @ResponseStatus(HttpStatus.OK)
    public ClientAreaViewDTO checkClientBySession(HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        ClientAreaViewDTO clientAreaViewDTO = (ClientAreaViewDTO) session.getAttribute("client");
        try {
            if (clientAreaViewDTO == null) throw new NullPointerException();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            //setCookie(response, "errorMessage", "You are not logged in", 5);
            response.sendRedirect("/login.html");
        }

        return clientAreaViewDTO;
    }

}
