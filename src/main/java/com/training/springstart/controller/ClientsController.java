package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.model.dto.ClientChangePassDTO;
import com.training.springstart.model.dto.ClientDateDTO;
import com.training.springstart.model.dto.ClientDatePromoDTO;
import com.training.springstart.service.client.ClientServiceBean;
import com.training.springstart.util.PagingEntity.PagingEntity;
import com.training.springstart.util.config.ClientConverter;
import com.training.springstart.util.mapper.ClientMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Client", description = "Client API")
public class ClientsController {

    private final ClientMapper clientConverter;

    private final ClientConverter orikaClientConverter;

    private final ObjectFactory<HttpSession> httpSessionFactory;

    private final ClientServiceBean clientServiceBean;

    @Operation(summary = "This is endpoint to find clients by phone code.", description = "Create request to find clients by phone code.", tags = {"Clients list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. The client has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified client request not found."),
            @ApiResponse(responseCode = "409", description = "")})
    @GetMapping(value = "/clients/phones", params = {"phone_code"})
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientsByPhoneCodes(@RequestParam String phone_code) {
        return clientServiceBean.findClientsByPhoneCode(phone_code);
    }

    @PostMapping("/clients/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody @Valid ClientDateDTO clientDateDTO) {
        //Client client = clientConverter.toObject(clientDateDTO);
        Client client = orikaClientConverter.toInitial(clientDateDTO);
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
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@RequestBody @Valid ClientAreaViewDTO clientAreaViewDTO, /*@RequestParam String name, @RequestParam String surname, @RequestParam String phone_number,*/ HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        String email = (String) session.getAttribute("email");

        clientAreaViewDTO.setEmail(email);
        Client client = clientConverter.toObject(clientAreaViewDTO);

        int status = clientServiceBean.updateByEmail(client);
        if (status > 0) session.setAttribute("client", client);

        response.sendRedirect("/personal-area");
    }

    @PostMapping("/clients/update/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateClientPassword(@RequestBody @Valid ClientChangePassDTO clientChangePassDTO, /*@RequestParam String newPassword, @RequestParam String newPasswordRepeat,*/ HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        String email = (String) session.getAttribute("email");

        clientChangePassDTO.setEmail(email);
        Client client = clientConverter.toObject(clientChangePassDTO);

        clientServiceBean.updatePasswordByEmail(client);

        response.sendRedirect("/personal-area");
    }

    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClientById(@PathVariable("id") Integer id) {
        clientServiceBean.removeById(id);
    }

    @GetMapping("/get-client")
    @ResponseStatus(HttpStatus.OK)
    public Client checkClientBySession(HttpServletResponse response) throws IOException {
        HttpSession session = httpSessionFactory.getObject();
        Client client = (Client) session.getAttribute("client");
        try {
            if (client == null) throw new NullPointerException();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            response.sendRedirect("/login.html");
        }

        return client;
    }

    @GetMapping(value = "/clients/table", params = {"page", "size", "sort"})
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientsPage(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam(defaultValue = "id") String sort) {

        PagingEntity pagingEntity = new PagingEntity(page, size, sort);
        return clientServiceBean.getPageAllNotDeleted(pagingEntity);
    }


    @GetMapping(value = "/clients/table/p")
    @ResponseStatus(HttpStatus.OK)
    public Page<Client> getClientsPageByPhoneCode(@RequestParam String phone_code) {
        log.info("Start method getClientsPageByPhoneCode with parameter {}", phone_code);
        return clientServiceBean.findClientsPageByPhoneCode(phone_code);
    }

    @PostMapping("/clients/promo-create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody ClientDatePromoDTO clientDatePromoDTO) {
        Client client = clientConverter.toObject(clientDatePromoDTO);
        return clientServiceBean.createWithPromo(client, clientDatePromoDTO.getPromo_code());
    }



}
