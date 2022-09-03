package com.training.springstart.repository;

import com.training.springstart.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@org.springframework.stereotype.Repository
@Component
public interface GoodRepository extends JpaRepository<Good, Integer> {

    //some additional and optional methods can be added here

}
