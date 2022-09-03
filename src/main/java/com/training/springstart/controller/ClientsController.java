package com.training.springstart.controller;

import com.training.springstart.entity.Client;
import com.training.springstart.service.CookieFactory;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.LoginRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientsController{

    private final CrudService<Client> crudService;

    private final LoginRegisterService loginRegisterService;


    @PostMapping("/client-register")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(/*@RequestBody*/ Client client, HttpServletResponse response) {
        response.addCookie(new Cookie("successMessage", "value"));
        return crudService.create(client);
    }

    @PostMapping("/client-login")
    @ResponseStatus(HttpStatus.OK)
    public Client checkClient(@RequestParam String email, @RequestParam String password) {
        return loginRegisterService.loginClient(email, password);
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

    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClientById(@PathVariable("id") Integer id) {
        crudService.removeById(id);
    }

    /*
    @Override
    public void setCookie(HttpServletResponse R, String n, String v, int d) {
        Cookie cookie = new Cookie(n, v);
        cookie.setMaxAge(d);
        R.addCookie(cookie);
    }
*/
}
