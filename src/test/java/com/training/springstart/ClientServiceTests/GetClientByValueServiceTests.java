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
public class GetClientByValueServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceBean clientServiceBean;

    @Test
    public void whenGetAll_shouldReturnClientsList() {
        int testId = 7,
            testIdA = 8;

        Client client = new Client(testId, "Name", "Surname", "ODUewdj8w",
                "email@email.com", "473987438793", false);
        Client clientA = new Client(testIdA, "NameA", "SurnameA", "ODUewdj8wA",
                "emailA@emailA.com", "473987438791", false);
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

    @Test
    public void whenGivenEmail_shouldReturnClient_ifFound() {
        String testEmail = "email@email.com";
        Client client = new Client(
                77,
                "Name",
                "Surname",
                "ODUewdj8w",
                testEmail,
                "473987438793",
                false);

        when(clientRepository
                .findClientByEmail(client.getEmail()))
                .thenReturn(Optional.of(client));

        Client client1 = clientServiceBean.getByEmail(testEmail);

        assertNotNull("Client with Email : "+testEmail+" not found", client1);
        assertEquals(client.getEmail(), client1.getEmail());
        assertEquals(client.getId(), client1.getId());
        assertEquals(client.getName(), client1.getName());
        assertEquals(client.getPhone_number(), client1.getPhone_number());

        assertThat(client1).isSameAs(client);
        verify(clientRepository).findClientByEmail(testEmail);
    }

}
