package com.training.springstart.repository;

import com.training.springstart.model.Good;
import com.training.springstart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Repository
@Component
public interface GoodRepository extends JpaRepository<Good, Integer> {

    //some additional and optional methods can be added here
    @Query(value = "SELECT g FROM Good g WHERE g.added_date_time >= :date AND g.deleted=false")
    List<Good> findRecentlyAddedGoods(Long date);
}
