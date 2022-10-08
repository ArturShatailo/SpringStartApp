package com.training.springstart.util.mapper;

import com.training.springstart.model.Card;
import com.training.springstart.model.Client;
import com.training.springstart.model.ClientAdmin;
import com.training.springstart.model.dto.CardSaveDTO;
import com.training.springstart.model.dto.clientDTO.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CardMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CardMapper {

    CardSaveDTO toAreaViewDTO(Card object);

    Card toObject(CardSaveDTO dto);

}