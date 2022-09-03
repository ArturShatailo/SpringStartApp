package com.training.springstart.repository;

import com.training.springstart.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@org.springframework.stereotype.Repository
@Component
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM clients c WHERE c.email=?1 AND deleted=false", nativeQuery = true)
    java.util.Optional<Client> findClientByEmail(String email);

}
