package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import com.training.springstart.model.PromoCode;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.auth.AuthLoginServiceBean;
import com.training.springstart.util.PagingEntity.PagingEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ClientServiceBean implements ClientsTableService, CrudService<Client>, ClientsPhoneService, GetClientByValueService, LoginRegisterService, UpdateDataService {

    private final ClientRepository clientRepository;

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
    public Client registerClient(Client client) {
        if (getByEmail(client.getEmail()) == null) {
            return clientRepository.save(client);
        } else {
            return null;
        }
    }

    @Override
    public String loginClient(Client client) {

        if ( client == null ) return unsuccessfullyLoggedIn("Wrong email or password");

        if (!comparePassword(client.getEmail(), client.getPassword()))
            return unsuccessfullyLoggedIn("Wrong email or password");

        AuthLoginServiceBean b = new AuthLoginServiceBean(client.getEmail(), client.getPassword());

        return b.startCheck()
                ? successfullyLoggedIn(client)
                : unsuccessfullyLoggedIn(b.getRequest());
    }


    public boolean comparePassword(String email, String password) {
        Client client = getByEmail(email);
        return client != null
                && client.getPassword().equals(password);
    }

    private String unsuccessfullyLoggedIn(String m) {
        String message = m;
        return "redirect:/login";
    }

    private String successfullyLoggedIn(Client client) {
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
    public List<Client> getPageAllNotDeleted(PagingEntity pagingEntity) {

        Pageable paging = PageRequest.of(
                pagingEntity.getPage(),
                pagingEntity.getSize(),
                Sort.by(pagingEntity.getSort()));

        Page<Client> pageResult = clientRepository.findAll(paging);

        if(pageResult.hasContent()) {
            return pageResult.getContent();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Client> findClientsByPhoneCode(String phone_code) {
        List<Client> clients = clientRepository.findClientsByPhoneCode(phone_code);
        clients.forEach(c -> System.err.println(c.getName() + ", dobroho vechora, my is Ukrainy"));

        return clients;
    }

    @Override
    public Page<Client> findClientsPageByPhoneCode(String phone_code) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").ascending());
        return clientRepository.findPP(phone_code, pageable);


    }

    public Client createWithPromo(Client client, PromoCode promoCode) {
        clientRepository.saveWithPromo(client, promoCode);
        return getByEmail(client.getEmail());
    }
}
