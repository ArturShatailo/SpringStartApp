package com.training.springstart.ClientServiceTests;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.client.ClientServiceBean;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateDataServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceBean clientServiceBean;


    @Test
    @Ignore
    public void whenUpdateByEmailClient_shouldUpdateClientFields() {
        Client client = new Client(
                83,
                "Name",
                "Surname",
                "ODUewdj8w",
                "email17@email.com",
                "473987438793",
                false);

        String newName = "newName";
        String newPhone = "4354643423565675";
        String newSurname = "newSurname";

        //ClientAreaViewDTO clientAreaViewDTO = clientConverter.toAreaViewDTO(client);

        when(clientRepository
                .updateClientByEmail(newName, newSurname, newPhone, client.getEmail()))
                .thenReturn(1);
        Client updated = new Client(
                83,
                newName,
                newSurname,
                "ODUewdj8w",
                "email17@email.com",
                newPhone,
                false);
        clientServiceBean.updateByEmail(updated);

        assertThat(client.getName()).isSameAs(updated.getName());
        assertThat(client.getPhone_number()).isSameAs(updated.getPhone_number());
        assertThat(client.getSurname()).isSameAs(updated.getSurname());

        verify(clientRepository).updateClientByEmail(newName, newSurname, newPhone, client.getEmail());
    }

}

