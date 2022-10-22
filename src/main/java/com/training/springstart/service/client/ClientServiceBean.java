package com.training.springstart.service.client;

import com.training.springstart.model.Card;
import com.training.springstart.model.Client;
import com.training.springstart.model.PromoCode;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.auth.AuthLoginServiceBean;
import com.training.springstart.service.auth.AuthRegisterServiceBean;
import com.training.springstart.service.card.CardServiceBean;
import com.training.springstart.util.PagingEntity.PagingEntity;
import com.training.springstart.util.email.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ClientServiceBean implements ClientsTableService, CrudService<Client>, ClientsSearchValuePageableService, GetClientByValueService, LoginRegisterService, UpdateDataService {

    private final ClientRepository clientRepository;

    private final CardServiceBean cardServiceBean;

    private final ObjectFactory<HttpSession> httpSessionFactory;

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getAllNotDeleted() {
        return clientRepository.findAllNotDeleted();
    }

    @Override
    public Client getById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id = " + id));
        //.orElseThrow(ResourceNotFoundException::new);
        if (client.isDeleted()) {
            throw new EntityNotFoundException("Client was deleted with id = " + id);
        }
        return client;
    }

    @Override
    public Client updateById(Integer id, Client client) {
        return clientRepository.findById(id)
                .map(entity -> {
                    entity.setName(client.getName());
                    entity.setSurname(client.getSurname());
                    entity.setEmail(client.getEmail());
                    entity.setPassword(client.getPassword());
                    entity.setPhone_number(client.getPhone_number());
                    entity.setOrders(client.getOrders());
                    return clientRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id = " + id));
        client.setDeleted(true);
        clientRepository.save(client);
    }

    @Override
    public int updateByEmail(Client client) {
        return clientRepository
                .updateClientByEmail(
                        client.getName(),
                        client.getSurname(),
                        client.getPhone_number(),
                        client.getEmail());
    }

    @Override
    public void updatePasswordByEmail(Client client) {
        clientRepository
                .updateClientPassByEmail(
                        client.getPassword(),
                        client.getEmail());
    }

    @Override
    public String registerClient(Client client, String passwordRepeat) {

        if (client == null)
            return unsuccessfullyRegister("Unable to register a new account with this email");

        AuthRegisterServiceBean a = new AuthRegisterServiceBean(
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPhone_number(),
                client.getPassword());

        if (!a.startCheck()) return unsuccessfullyRegister(a.getRequest());

        if (!client.getPassword().equals(passwordRepeat))
            return unsuccessfullyRegister("Password id not the same as repeated password");

        if (getByEmail(client.getEmail()) != null)
            return unsuccessfullyRegister("This email is already registered");

        clientRepository.save(client);
        return successfullyRegistered("Successfully registered");
    }

    private String unsuccessfullyRegister(String m) {
        System.err.println(m);
        return "redirect:/registration";
    }

    private String successfullyRegistered(String m) {
        System.err.println(m);
        return "redirect:/login";
    }

    @Override
    public String loginClient(Client client) {

        if ( client == null ) return unsuccessfullyLoggedIn("Wrong email or password");

        AuthLoginServiceBean b = new AuthLoginServiceBean(client.getEmail(), client.getPassword());

        if (!b.startCheck()) return unsuccessfullyLoggedIn(b.getRequest());

        Client clientEntity = getByEmail(client.getEmail());

        if (!compareClients(client, clientEntity))
            return unsuccessfullyLoggedIn("Wrong email or password");

        return successfullyLoggedIn(clientEntity, "Successfully logged in");
    }

    public boolean compareClients(Client client, Client clientEntity) {
        return (client != null && clientEntity != null)
                && client.getPassword().equals(clientEntity.getPassword());
    }

    private String unsuccessfullyLoggedIn(String m) {
        System.err.println(m);
        return "redirect:/login";
    }

    private String successfullyLoggedIn(Client client, String m) {
        System.err.println(m);
        HttpSession session = httpSessionFactory.getObject();
        session.setAttribute("client", client);
        session.setAttribute("email", client.getEmail());
        return "redirect:/personal-area";
    }


    @Override
    public Client getByEmail(String email) {
        return clientRepository.findClientByEmail(email).orElse(null);
        /*.orElseThrow(() -> new EntityNotFoundException("Client not found with email = " + email));*/
    }

    @Override
    public Page<Client> getPageAllNotDeleted(Integer page, Integer size, String sort) {

        PagingEntity pagingEntity = new PagingEntity(page, size, sort);

        Pageable paging = PageRequest.of(
                pagingEntity.getPage(),
                pagingEntity.getSize(),
                Sort.by(pagingEntity.getSort()));

        return clientRepository.findAllNotDeletedPage(paging);
    }

    @Override
    public Page<Client> findClientsPageByPhoneCode(String phone_code) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").ascending());
        return clientRepository.findPP(phone_code, pageable);
    }

    @Override
    public Page<Client> findClientsPageByEmailDomain(Integer page, Integer size, String sort, String email_domain) {
        PagingEntity pagingEntity = new PagingEntity(page, size, sort);
        Pageable paging = PageRequest.of(
                pagingEntity.getPage(),
                pagingEntity.getSize(),
                Sort.by(pagingEntity.getSort()));

        Page<Client> clientsPage = clientRepository.findClientsWithEmailDomain(email_domain, paging);

        clientsPage.getContent()
                .forEach(client -> new EmailSender(
                        client.getEmail(),
                        "Subject",
                        "Body")
                        .send());

        return clientsPage;
    }

    public void saveCard(Card card) {
        cardServiceBean.create(card);
    }

    public Client updateCards(Client client, Card card) {
        card.setOwner(client);
        saveCard(card);
        return client;
    }

    public Page<Client> findClientsWithNoCardAdded(Integer page, Integer size, String sort) {
        PagingEntity pagingEntity = new PagingEntity(page, size, sort);

        Pageable paging = PageRequest.of(
                pagingEntity.getPage(),
                pagingEntity.getSize(),
                Sort.by(pagingEntity.getSort()));

        return clientRepository.findClientsWithNoCard(paging);
    }

    public Page<Client> findClientsWithCardAdded(Integer page, Integer size, String sort) {
        PagingEntity pagingEntity = new PagingEntity(page, size, sort);

        Pageable paging = PageRequest.of(
                pagingEntity.getPage(),
                pagingEntity.getSize(),
                Sort.by(pagingEntity.getSort()));

        return clientRepository.findClientsWithCard(paging);
    }

}
