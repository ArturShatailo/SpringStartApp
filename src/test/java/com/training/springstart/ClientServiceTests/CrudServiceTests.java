package com.training.springstart.ClientServiceTests;

import com.training.springstart.model.Client;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.client.ClientServiceBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CrudServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceBean clientServiceBean;


    @Test
    public void whenSaveClient_shouldReturnClient() {
        Client client = new Client();
        client.setName("Mark");
        client.setPassword("&Y*guh0jou");

        when(clientRepository
                .save(ArgumentMatchers.any(Client.class)))
                .thenReturn(client);

        Client client1 = clientServiceBean.create(client);

        assertThat(client1.getName()).isSameAs(client.getName());
        assertThat(client1.getPassword()).isSameAs(client.getPassword());
        verify(clientRepository).save(client);
    }

    @Test
    public void whenRemoveClient_shouldBeUpdatedClient() {
        int testId = 1;
        Client client = new Client(
                testId,
                "SomeClient",
                "SomeClientSur",
                "ODUF#$f8w",
                "someE1@someE1.com",
                "473987438793",
                false);

        given(clientRepository.findById(client.getId())).willReturn(Optional.of(client));
        clientServiceBean.removeById(client.getId());
        assertTrue(client.isDeleted());

        verify(clientRepository).findById(client.getId());
        verify(clientRepository).save(client);
    }

    @Test(expected = RuntimeException.class)
    public void WhenUserIsDeleted_shouldThrowException() {
        int testId = 2;
        Client client = new Client(
                testId,
                "SomeClient",
                "SomeClientSur",
                "ODUF#$f8w",
                "someE2@someE2.com",
                "473987438793",
                true);

        given(clientRepository.findById(anyInt())).willReturn(Optional.empty());
        clientServiceBean.removeById(client.getId());
    }

    @Test
    public void whenGivenId_shouldReturnClient_ifFound() {
        int testId = 7;
        Client client = new Client(
                testId,
                "Name",
                "Surname",
                "ODUewdj8w",
                "email3@email3.com",
                "473987438793",
                false);

        when(clientRepository
                .findById(client.getId()/*ArgumentMatchers.any(Client.class).getId()*/))
                .thenReturn(Optional.of(client));

        Client client1 = clientServiceBean.getById(testId);

        assertNotNull("Client with id : "+testId+" not found", client1);
        assertEquals(client.getId(), client1.getId());
        assertEquals(client.getName(), client1.getName());
        assertEquals(client.getPhone_number(), client1.getPhone_number());
        assertEquals(client.getEmail(), client1.getEmail());

        assertThat(client1).isSameAs(client);
        verify(clientRepository).findById(client.getId());
    }

    @Test
    public void whenGetAll_shouldReturnClientsList() {
        int testId = 7,
            testIdA = 8;

        Client client = new Client(testId, "Name", "Surname", "ODUewdj8w",
                "email4@email4.com", "473987438793", false);
        Client clientA = new Client(testIdA, "NameA", "SurnameA", "ODUewdj8wA",
                "emailA4@emailA4.com", "473987438791", false);
        List<Client> clients = Arrays.asList(client, clientA);

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> clients1 = clientServiceBean.getAll();

        assertThat(clients, is(clients1));
        assertThat(clients, hasSize(clients1.size()));
        assertThat(clients.size(), is(clients1.size()));
        assertThat(clients1, containsInAnyOrder(
                hasProperty("name", is(clients.get(0).getName())),
                hasProperty("name", is(clients.get(1).getName()))
        ));
        assertThat(clients, contains(clients1.get(0), clients1.get(1)));

        verify(clientRepository).findAll();
    }

}

