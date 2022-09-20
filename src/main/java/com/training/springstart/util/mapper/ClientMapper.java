package com.training.springstart.util.mapper;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.model.dto.ClientChangePassDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ClientMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientAreaViewDTO toAreaViewDTO(Client object);

    Client toObject(ClientAreaViewDTO dto);

    ClientChangePassDTO toChangePassDTO(Client object);

    Client toObject(ClientChangePassDTO dto);
}