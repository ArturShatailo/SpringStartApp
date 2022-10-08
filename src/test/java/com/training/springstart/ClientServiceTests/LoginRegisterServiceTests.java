package com.training.springstart.ClientServiceTests;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.clientDTO.ClientAreaViewDTO;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.client.ClientServiceBean;
import com.training.springstart.util.mapper.ClientMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginRegisterServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @Spy
    private ClientMapper clientConverter = Mappers.getMapper(ClientMapper.class);

    @InjectMocks
    private ClientServiceBean clientServiceBean;


    @Test
    public void whenRegisterClient_shouldReturnClient() {
        Client client = new Client(
                72,
                "SomeClient",
                "SomeClientSur",
                "O3UF#$f8w",
                "someE14@someE.com",
                "473987438793",
                false);

        when(clientRepository
                .save(ArgumentMatchers.any(Client.class)))
                .thenReturn(client);

        Client client1 = clientServiceBean.create(client);

        assertThat(client1.getId()).isSameAs(client.getId());
        assertThat(client1.getName()).isSameAs(client.getName());
        assertThat(client1.getPassword()).isSameAs(client.getPassword());
        verify(clientRepository).save(client);
    }

    @Test
    public void whenLoginClient_shouldReturnClientAreaViewDTO() {
        String testEmail = "email15@email.com";
        String testPassword = "JF#@E)8h9UOI";
        Client client = new Client(
                38,
                "SomeClient",
                "SomeClientSur",
                testPassword,
                testEmail,
                "473987438793",
                false);

        when(clientRepository
                .findClientByEmail(testEmail))
                .thenReturn((Optional.of(client)));

        Client client1 = clientServiceBean.getByEmail(testEmail);
        assertThat(client.getPassword()).isSameAs(client1.getPassword());

        ClientAreaViewDTO clientAreaViewDTO = clientConverter.toAreaViewDTO(client1);

        assertThat(clientAreaViewDTO.getName()).isSameAs(client.getName());
        assertThat(clientAreaViewDTO.getSurname()).isSameAs(client.getSurname());
        assertThat(clientAreaViewDTO.getEmail()).isSameAs(client.getEmail());
        assertThat(clientAreaViewDTO.getPhone_number()).isSameAs(client.getPhone_number());

        verify(clientRepository).findClientByEmail(testEmail);
    }

}

