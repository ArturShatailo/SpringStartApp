package com.training.springstart.util.config;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.*;
import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ClientConverter {

    private final MapperFacade mapperFacade;

    public ClientConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public ClientAreaViewDTO toClientAreaViewDTO (Client client) {
        return mapperFacade.map(client, ClientAreaViewDTO.class);
    }

    public Client toInitial (ClientAreaViewDTO dto) {
        return mapperFacade.map(dto, Client.class);
    }

    public ClientChangePassDTO toClientChangePassDTO (Client client) {
        return mapperFacade.map(client, ClientChangePassDTO.class);
    }

    public Client toInitial (ClientChangePassDTO dto) {
        return mapperFacade.map(dto, Client.class);
    }

    public ClientDateDTO toClientDateDTO (Client client) {
        return mapperFacade.map(client, ClientDateDTO.class);
    }

    public Client toInitial (ClientDateDTO dto) {
        return mapperFacade.map(dto, Client.class);
    }

    public ClientDatePromoDTO toClientDatePromoDTO (Client client) {
        return mapperFacade.map(client, ClientDatePromoDTO.class);
    }

    public Client toInitial (ClientDatePromoDTO dto) {
        return mapperFacade.map(dto, Client.class);
    }

    public ClientLoginDTO toClientLoginDTO (Client client) {
        return mapperFacade.map(client, ClientLoginDTO.class);
    }

    public Client toInitial (ClientLoginDTO dto) {
        return mapperFacade.map(dto, Client.class);
    }

    public ClientAdminDTO toClientAdminDTO (Client client) {
        return mapperFacade.map(client, ClientAdminDTO.class);
    }

    public Client toInitial (ClientAdminDTO dto) {
        return mapperFacade.map(dto, Client.class);
    }

}
