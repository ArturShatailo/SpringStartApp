package com.training.springstart.service.card;

import com.training.springstart.model.Card;
import com.training.springstart.model.Client;
import com.training.springstart.repository.CardRepository;
import com.training.springstart.repository.ClientRepository;
import com.training.springstart.service.CrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class CardServiceBean implements CrudService<Card> {

    private final CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getAll() {
        return null;
    }

    @Override
    public Card getById(Integer id) {
        return null;
    }

    @Override
    public Card updateById(Integer id, Card card) {
        return null;
    }

    @Override
    public void removeById(Integer id) {

    }
}
