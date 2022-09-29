package com.training.springstart.repository;

import com.training.springstart.model.Client;
import com.training.springstart.model.ClientAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@org.springframework.stereotype.Repository
@Component
public interface AdminRepository extends JpaRepository<ClientAdmin, Integer> {

    @Query(value = "SELECT * FROM admins a WHERE a.email=?1 AND a.deleted=false", nativeQuery = true)
    java.util.Optional<ClientAdmin> findAdminByEmail(String email);

}
