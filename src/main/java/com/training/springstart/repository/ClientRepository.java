package com.training.springstart.repository;

import com.training.springstart.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Component
public interface ClientRepository extends JpaRepository<Client, Integer>, PagingAndSortingRepository<Client, Integer> {

    @Query(value = "SELECT * FROM clients c WHERE c.email=?1 AND deleted=false", nativeQuery = true)
    java.util.Optional<Client> findClientByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Client c SET c.name = :name, c.surname = :surname, c.phone_number = :phone_number WHERE c.email = :email")
    int updateClientByEmail
            (@Param("name") String name,
             @Param("surname") String surname,
             @Param("phone_number") String phone_number,
             @Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Client c SET c.password = :password WHERE c.email = :email")
    int updateClientPassByEmail
            (@Param("password") String password,
             @Param("email") String email);

    @Query(value = "SELECT * FROM clients c WHERE phone_number LIKE ?% AND deleted=false", nativeQuery = true)
    List<Client> findClientsByPhoneCode(String phone_code);


    @Query(value = "SELECT * FROM clients c WHERE deleted=false", nativeQuery = true)
    List<Client> findAllNotDeleted();



}
