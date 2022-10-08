package com.training.springstart.controller.implementation;

import com.training.springstart.controller.interfaces.ClientApiRepresentable;
import com.training.springstart.model.Card;
import com.training.springstart.model.Client;
import com.training.springstart.model.dto.clientDTO.ClientAreaViewDTO;
import com.training.springstart.model.dto.clientDTO.ClientChangePassDTO;
import com.training.springstart.model.dto.clientDTO.ClientDateDTO;
import com.training.springstart.model.dto.clientDTO.ClientDatePromoDTO;
import com.training.springstart.service.client.ClientServiceBean;
import com.training.springstart.util.PagingEntity.PagingEntity;
import com.training.springstart.util.mapper.ClientMapper;
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
@Tag(name = "Client API", description = "Client API methods")
public class ClientsController implements ClientApiRepresentable {

    private final ClientMapper clientConverter;

    private final ObjectFactory<HttpSession> httpSessionFactory;

    private final ClientServiceBean clientServiceBean;

    @Override
    @PostMapping("/clients/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody @Valid ClientDateDTO clientDateDTO) {
        Client client = clientConverter.toObject(clientDateDTO);
        return clientServiceBean.create(client);
    }

    @Override
    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        return clientServiceBean.getAll();
    }

    @Override
    @GetMapping("/clients/active")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getNotDeletedClients() {
        return clientServiceBean.getAllNotDeleted();
    }

    @Override
    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client getClientById(@PathVariable("id") Integer id) {
        return clientServiceBean.getById(id);
    }

    @Override
    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client refreshClient(@PathVariable("id") Integer id, @RequestBody Client client) {
        return clientServiceBean.updateById(id, client);
    }

    @Override
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

    @Override
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

    @Override
    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClientById(@PathVariable("id") Integer id) {
        clientServiceBean.removeById(id);
    }

    @Override
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

    @Override
    @GetMapping(value = "/clients/table", params = {"page", "size", "sort"})
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientsPage(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam(defaultValue = "id") String sort) {

        PagingEntity pagingEntity = new PagingEntity(page, size, sort);
        return clientServiceBean.getPageAllNotDeleted(pagingEntity);
    }

    @Override
    @GetMapping(value = "/clients/table/p")
    @ResponseStatus(HttpStatus.OK)
    public Page<Client> getClientsPageByPhoneCode(@RequestParam String phone_code) {
        log.info("Start method getClientsPageByPhoneCode with parameter {}", phone_code);
        return clientServiceBean.findClientsPageByPhoneCode(phone_code);
    }

    @Override
    @GetMapping(value = "/clients/phones", params = {"phone_code"})
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientsByPhoneCodes(@RequestParam String phone_code) {
        return clientServiceBean.findClientsByPhoneCode(phone_code);
    }

    @Override
    @PostMapping("/clients/promo-create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody ClientDatePromoDTO clientDatePromoDTO) {
        Client client = clientConverter.toObject(clientDatePromoDTO);
        return clientServiceBean.createWithPromo(client, clientDatePromoDTO.getPromo_code());
    }

}
