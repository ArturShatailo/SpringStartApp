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
    public void whenUpdateByEmailClient_shouldReturnClientAreaViewDTO() {
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
        ClientAreaViewDTO clientAreaViewDTO = clientServiceBean.updateByEmail(client.getEmail(), newName, newSurname, newPhone);

        assertThat(client.getName()).isSameAs(clientAreaViewDTO.getName());
        assertThat(client.getPhone_number()).isSameAs(clientAreaViewDTO.getPhone_number());
        assertThat(client.getSurname()).isSameAs(clientAreaViewDTO.getSurname());

        verify(clientRepository).updateClientByEmail(newName, newSurname, newPhone, client.getEmail());
    }

}

