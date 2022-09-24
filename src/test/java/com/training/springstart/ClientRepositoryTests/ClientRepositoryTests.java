package com.training.springstart.ClientRepositoryTests;


import com.training.springstart.SpringStartApplication;
import com.training.springstart.model.Client;
import com.training.springstart.repository.ClientRepository;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

        Client client = Client.builder()
                .name("Adam")
                .email("Email@Email.com")
                .password("IH&(*iun87G")
                .build();

        clientRepository.save(client);

        assertThat(client.getId()).isGreaterThan(0);
        assertThat(client.getName()).isEqualTo("Adam");
    }
//
//    @Test
//    @Order(2)
//    public void getClientTest() {
//
//        Client client = Client.builder()
//                .id(88)
//                .name("Adam")
//                .email("Email@Email.com")
//                .password("IH&(*iun87G")
//                .build();
//        clientRepository.save(client);
//
//        Client client1 = clientRepository.findById(client.getId()).orElseThrow();
//
//        assertThat(client.getId()).isEqualTo(client1.getId());
//        assertThat(client.getEmail()).isEqualTo(client1.getEmail());
//
//    }
//
//    @Test
//    @Order(3)
//    public void getListOfEmployeeTest() {
//
//        List<Employee> employeesList = repository.findAll();
//
//        Assertions.assertThat(employeesList.size()).isGreaterThan(0);
//
//    }
//
//    @Test
//    @Order(4)
//    @Rollback(value = false)
//    public void updateEmployeeTest() {
//
//        Employee employee = repository.findById(1).get();
//
//        employee.setName("Martin");
//        Employee employeeUpdated = repository.save(employee);
//
//        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Martin");
//
//    }
//
//    @Test
//    @Order(5)
//    @Rollback(value = false)
//    public void deleteEmployeeTest() {
//
//        Employee employee = repository.findById(1).get();
//
//        repository.delete(employee);
//
//        //repository.deleteById(1L);
//
//        Employee employee1 = null;
//
//        Optional<Employee> optionalAuthor = Optional.ofNullable(repository.findByName("Martin"));
//
//        if (optionalAuthor.isPresent()) {
//            employee1 = optionalAuthor.get();
//        }
//
//        Assertions.assertThat(employee1).isNull();
//    }

}
