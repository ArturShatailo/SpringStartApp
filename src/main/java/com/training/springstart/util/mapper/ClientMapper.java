package com.training.springstart.util.mapper;

import com.training.springstart.model.Client;
import com.training.springstart.model.ClientAdmin;
import com.training.springstart.model.dto.clientDTO.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ClientMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientAreaViewDTO toAreaViewDTO(Client object);

    Client toObject(ClientAreaViewDTO dto);

    ClientChangePassDTO toChangePassDTO(Client object);

    Client toObject(ClientChangePassDTO dto);

    ClientAdminDTO toClientAdminDTO (ClientAdmin clientAdmin);

    ClientAdmin toObject(ClientAdminDTO dto);

    ClientDateDTO toClientDateDTO (Client client);

    Client toObject(ClientDateDTO dto);

    ClientDatePromoDTO toClientDatePromoDTO (Client client);

    Client toObject(ClientDatePromoDTO dto);

    Client toObject(ClientLoginDTO clientLoginDTO);

    Client toClientLoginDTO(Client client);
}