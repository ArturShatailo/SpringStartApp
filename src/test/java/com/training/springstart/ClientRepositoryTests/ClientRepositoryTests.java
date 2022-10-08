package com.training.springstart.ClientRepositoryTests;

import com.training.springstart.model.Client;
import com.training.springstart.repository.ClientRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientRepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveClientTest() {

        Client client = clientRepository.save(
                Client.builder()
                        .id(91)
                        .name("Adam")
                        .surname("Eva")
                        .password("IH&(*iun87G")
                        .email("Email21@Email.com")
                        .phone_number("567865644356")
                        .deleted(false)
                        .build());

        Client client1 = clientRepository.save(client);

        assertThat(client1).isNotNull();
        assertThat(client1.getId()).isGreaterThan(0);
        assertThat(client1.getName()).isEqualTo("Adam");
    }

    @Test
    @Order(2)
    public void getClientByIdTest() {
        Client client = clientRepository.save(
                Client.builder()
                .id(92)
                .name("Adam")
                .surname("Eva")
                .password("IH&(*iun87G")
                .email("Email22@Email.com")
                .phone_number("567865644356")
                .deleted(false)
                .build());

        Client client1 = clientRepository.findById(client.getId()).orElseThrow();

        assertThat(client1).isNotNull();
        assertThat(client.getEmail()).isEqualTo(client1.getEmail());

    }

    @Test
    @Order(3)
    public void getClientByEmailTest() {
        Client client = clientRepository.save(
                Client.builder()
                        .id(93)
                        .name("Adam")
                        .surname("Eva")
                        .password("IH&(*iun87G")
                        .email("Email15@Email.com")
                        .phone_number("567865644356")
                        .deleted(false)
                        .build());

        Client client1 = clientRepository.findClientByEmail(client.getEmail()).orElseThrow();

        assertThat(client1).isNotNull();
        assertThat(client.getId()).isEqualTo(client1.getId());
    }

    @Test
    @Order(5)
    public void getListOfClientsTest(){

        Client client = clientRepository.save(
                Client.builder()
                        .id(96)
                        .name("Adam")
                        .surname("Eva")
                        .password("IH&(*iun87G")
                        .email("Email25@Email.com")
                        .phone_number("567865644356")
                        .deleted(false)
                        .build());

        Client client1 = clientRepository.save(
                Client.builder()
                        .id(97)
                        .name("AdamNew")
                        .surname("EvaNew")
                        .password("IH&(*ifd3e4we4")
                        .email("rmailNew26@rmailNew.com")
                        .phone_number("44342322436")
                        .deleted(false)
                        .build());

        clientRepository.save(client);
        clientRepository.save(client1);

        List<Client> clientsList = Arrays.asList(client, client1);
        List<Client> clientsList1 = clientRepository.findAll();

        assertThat(clientsList1).isNotNull();
        assertThat(clientsList1).containsAll(clientsList);
        assertThat(clientsList1.size()).isEqualTo(clientsList.size());

    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void updateClientTest() {

        String testPhone = "8976547698";

        Client client = clientRepository.save(
                Client.builder()
                        .id(43)
                        .name("Adam")
                        .surname("Eva")
                        .password("IH&(*iun87G")
                        .email("Email27@Email.com")
                        .phone_number("567865644356")
                        .deleted(false)
                        .build());

        Client client1 = clientRepository.findById(client.getId()).orElseThrow();
        client1.setPhone_number(testPhone);

        Client client2 = clientRepository.save(client1);
        assertThat(client2.getPhone_number()).isEqualTo(testPhone);
    }

    @Ignore
    @Test
    @Order(7)
    @Rollback(value = false)
    public void updateClientByEmailTest() {

        String testName = "testName";
        String testSurname = "testSurname";
        String testPhone = "8765678903";

        Client client = clientRepository.save(
                Client.builder()
                        .id(42)
                        .name("Adam")
                        .surname("Eva")
                        .password("IH&(*iun87G")
                        .email("Email28@Email.com")
                        .phone_number("567865644356")
                        .deleted(false)
                        .build());

        Client client1 = clientRepository.save(client);
        clientRepository.updateClientByEmail(testName, testSurname, testPhone, client1.getEmail());

        assertThat(client1.getName()).isEqualTo(testName);
        assertThat(client1.getSurname()).isEqualTo(testSurname);
        assertThat(client1.getPhone_number()).isEqualTo(testPhone);
    }

}
