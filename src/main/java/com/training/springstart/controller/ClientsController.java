package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.model.ClientDTO;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.UpdateDataService;
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
    public String updateClient(@RequestParam String name, @RequestParam String surname, @RequestParam String phone_number) {
        HttpSession session = httpSessionFactory.getObject();
        String email = (String) session.getAttribute("email");
        ClientDTO c = new ClientDTO(name, surname, email, phone_number);

        int status = updateData.updateByEmail(c);
        return "redirect:/personal-area";
    }

    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClientById(@PathVariable("id") Integer id) {
        crudService.removeById(id);
    }

    @GetMapping("/get-client")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientByEmail(HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
        try {
            if (clientDTO == null) throw new NullPointerException();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            //setCookie(response, "errorMessage", "You are not logged in", 5);
            response.sendRedirect("/login.html");
        }

        return clientDTO;
    }

}
