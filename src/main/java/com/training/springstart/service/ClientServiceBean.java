package com.training.springstart.service;

import com.training.springstart.model.ClientDTO;
import com.training.springstart.model.Client;
import com.training.springstart.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ClientServiceBean implements CrudService<Client>, LoginRegisterService, UpdateDataService {

    private ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
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
    public int updateByEmail(ClientDTO c) {
        return clientRepository
                .updateClientByEmail(
                        c.getName(),
                        c.getSurname(),
                        c.getPhone_number(),
                        c.getEmail());
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
    public ClientDTO loginClient(String email, String password) {
        return comparePassword(email, password);
    }

    public ClientDTO comparePassword(String email, String password) {
        Client client = getByEmail(email);
        if (client.getPassword().equals(password)) {
            return client.clientToDTO();
        } else {
            return null;
            //throw new EntityNotFoundException("Login is failed with email " + email);
        }
    }

    @Override
    public Client getByEmail(String email) {
        return clientRepository.findClientByEmail(email).orElse(null);
        /*.orElseThrow(() -> new EntityNotFoundException("Client not found with email = " + email));*/
    }
}
