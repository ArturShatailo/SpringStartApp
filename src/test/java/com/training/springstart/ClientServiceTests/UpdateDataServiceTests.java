package com.training.springstart.ClientServiceTests;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.client.ClientServiceBean;
import com.training.springstart.util.mapper.ClientMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateDataServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @Spy
    private ClientMapper clientConverter = Mappers.getMapper(ClientMapper.class);

    @InjectMocks
    private ClientServiceBean clientServiceBean;


    @Test
    public void whenUpdateByEmailClient_shouldReturnClientAreaViewDTO() {
        Client client = new Client(
                88,
                "Name",
                "Surname",
                "ODUewdj8w",
                "email@email.com",
                "473987438793",
                false);

        String newName = "newName";
        String newPhone = "4354643423565675";
        String newSurname = "newSurname";

        ClientAreaViewDTO clientAreaViewDTO = clientConverter.toAreaViewDTO(client);

        when(clientRepository
                .updateClientByEmail(newName, newSurname, newPhone, client.getEmail()))
                .thenReturn(1);
        clientServiceBean.updateByEmail(client.getEmail(), newName, newSurname, newPhone);

        assertThat(client.getName()).isSameAs(clientAreaViewDTO.getName());
        assertThat(client.getPhone_number()).isSameAs(clientAreaViewDTO.getPhone_number());
        assertThat(client.getSurname()).isSameAs(clientAreaViewDTO.getSurname());

        verify(clientRepository).updateClientByEmail(newName, newSurname, newPhone, client.getEmail());
    }

}

