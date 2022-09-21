package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.service.client.ClientServiceBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Client", description = "Client API")
public class ClientsController {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    private final ClientServiceBean clientServiceBean;

    @Operation(summary = "This is endpoint to find clients by phone code.", description = "Create request to find clients by phone code.", tags = {"Clients list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. The client has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified client request not found."),
            @ApiResponse(responseCode = "409", description = "")})
    //@GetMapping("/clients/phones/{phone_code}")
    @GetMapping(value = "/clients/phones", params = {"phone_code"})
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientsByPhoneCodes(@RequestParam String phone_code) {
        return clientServiceBean.findClientsByPhoneCode(phone_code);
    }

    @PostMapping("/clients/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(/*@RequestBody*/ Client client, HttpServletResponse response) {
        response.addCookie(new Cookie("successMessage", "value"));
        return clientServiceBean.create(client);
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        return clientServiceBean.getAll();
    }

    @GetMapping("/clients/active")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getNotDeletedClients() {
        return clientServiceBean.getAllNotDeleted();
    }

    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client getClientById(@PathVariable("id") Integer id) {
        return clientServiceBean.getById(id);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client refreshClient(@PathVariable("id") Integer id, @RequestBody Client client) {
        return clientServiceBean.updateById(id, client);
    }

    @PostMapping("/clients/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientAreaViewDTO updateClient(@RequestParam String name, @RequestParam String surname, @RequestParam String phone_number, HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        String email = (String) session.getAttribute("email");

        ClientAreaViewDTO c = clientServiceBean.updateByEmail(email, name, surname, phone_number);
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

        clientServiceBean.updatePasswordByEmail(email, newPassword);

        response.sendRedirect("/personal-area");
//        int status = updateData.updateByEmail(client);
//        return "redirect:/personal-area";
    }

    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClientById(@PathVariable("id") Integer id) {
        clientServiceBean.removeById(id);
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
