package com.training.springstart.repository;

import com.training.springstart.model.Client;
import com.training.springstart.model.PromoCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@org.springframework.stereotype.Repository
@Component
public interface ClientRepository extends JpaRepository<Client, Integer>{

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


    @Query(value = "SELECT * FROM clients c WHERE deleted=false", nativeQuery = true)
    List<Client> findAllNotDeleted();

    //@Query(value = "select c from Client c where c.phone_number LIKE ':phone_code%' AND c.deleted=false")
    @Query(value = "SELECT * FROM clients c WHERE c.phone_number LIKE ?1% AND deleted=false", nativeQuery = true)
    Page<Client> findPP(String phone_code, Pageable pageable);

    //Page<Client> findClientByPhone_numberStartingWith(String phone_code, Pageable pageable);

    /*@Transactional
    @Query(value = "INSERT INTO clients (name, surname, password, phone_number, deleted, email) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", countQuery = "1",  nativeQuery = true)
    Client saveWithPromo(@Param("customer") Client client, @Param("promo_code") String promoCode);
*/


    @Transactional
    @Modifying
    @Query(value =
            "BEGIN; " +
                    "INSERT INTO clients (name, surname, password, phone_number, deleted, email)" +
                    "VALUES (:#{#client.name}, :#{#client.surname}, :#{#client.password}, " +
                    ":#{#client.phone_number}, false, :#{#client.email});" +
                    "INSERT INTO promo_codes (owner_email, value, status) " +
                    "VALUES (:#{#promo_code.owner_email}, :#{#promo_code.value}, :#{#promo_code.status})  " +
                    ";"+
            "COMMIT;", nativeQuery = true)
    void saveWithPromo(@Param("client") Client client, @Param("promo_code") PromoCode promo_code);

}
