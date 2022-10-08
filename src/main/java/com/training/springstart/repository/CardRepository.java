package com.training.springstart.repository;

import com.training.springstart.model.Card;
import com.training.springstart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CardRepository extends JpaRepository<Card, Integer>{


}
